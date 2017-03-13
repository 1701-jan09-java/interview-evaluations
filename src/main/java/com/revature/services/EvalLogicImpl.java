package com.revature.services;

import com.revature.domain.Eval;
import com.revature.domain.EvalComment;
import com.revature.domain.QuestionEval;
import com.revature.repositories.BatchRepository;
import com.revature.repositories.EvalCommentRepository;
import com.revature.repositories.EvalRepository;
import com.revature.repositories.PersonRepository;
import com.revature.validation.JsonValidation;
import com.revature.validation.exceptions.NotFoundException;
import java.sql.Date;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly=false, isolation=Isolation.READ_COMMITTED)
public class EvalLogicImpl implements EvalLogic {

	@Autowired
	private EvalRepository dao;
	
	@Autowired
	private EvalCommentRepository commentDao;

    @Autowired
    private PersonRepository personDao;

    @Autowired
    private BatchRepository batchDao;

    @Autowired
    private QuestionLogic questionLogic;

    @Autowired
    private JsonValidation validation;
	
	@PersistenceContext
    private EntityManager entityManager;
	

//CREATE----------------------------
	@Override
	public Eval createEval(Eval eval) {
		
		System.out.println(eval);
		
		if(eval.getId() != null && eval.getId() != 0) {
			throw new ConstraintViolationException("Id is automatically generated - do not include in post request", null);
		}
		
		if(eval.getTrainee() == null || eval.getTrainee().getId() == 0){
			throw new ConstraintViolationException("Missing required field trainee.id (Integer)", null);
		}
		
		if(eval.getWeek() == null || eval.getWeek() == 0){
			throw new ConstraintViolationException("Missing required field week (Integer)", null);
		}
		
		if(eval.getEvalType() == null || eval.getEvalType().getId() == 0){
			throw new ConstraintViolationException("Missing required field evalType.id (Integer)", null);
		}
		
		if(eval.getBatch() == null || eval.getBatch().getId() == 0){
			throw new ConstraintViolationException("Missing required field batch.id (Integer)", null);
		}

        Date date = new Date((new java.util.Date()).getTime());
		eval.setDate(date);
		
		// Set eval property of questions and qEval property of question comments
		// This is required because these fields are not auto-generated in json mapping
		if (eval.getQuestions() != null) {
			eval.getQuestions().forEach((question) -> {
				question.setEval(eval);
				// also set qEval of question comments
				if (question.getComments() != null) {
					question.getComments().forEach((comment) -> {
						comment.setQuestionEval(question);
					});
				}

                if (question.getQuestionPool() == null) {
                    throw new ConstraintViolationException("Missing required field questionPool.id (Integer) "
                            + "in questionEval with id " + question.getId(), null);
                }

                // increment question usage count and update date last used
                questionLogic.updateQuestionUsed(question.getQuestionPool().getId());

                // verify that scores are valid
                validation.validateScores(question);

			});
		}
		
		// Set eval property of comments - required for cascade
		if (eval.getComments() != null) {
			eval.getComments().forEach((comment) -> {
				comment.setEval(eval);
			});
		}
		
		dao.saveAndFlush(eval);
		
		// Sync cache with database for fresh version with all fields filled in
		entityManager.refresh(eval);

        calculateTotalScores(eval);

		return eval;
	}
	
	@Override
	@Transactional
	public EvalComment createComment(EvalComment comment, Integer evalId) {
		
		if(comment.getId() != null && comment.getId() != 0) {
			throw new ConstraintViolationException("Id is automatically generated - do not include in post request", null);
		}
		
		if (comment.getCommentText() == null) {
			throw new ConstraintViolationException("Missing required field commentText (String)", null);
		}
		
		comment.setEval(dao.findOne(evalId));
		System.out.println(comment.getEval());
		if (comment.getEval() == null) {
			throw new ConstraintViolationException("Evaluation " + evalId + " does not exist", null);
		}
		
		return commentDao.save(comment);
	}

//RETRIEVE--------------------------
	@Override
	public Eval getEvalById(Integer id) {

        Eval eval = dao.findOne(id);

        if (eval == null) {
            throw new NotFoundException("Evaluation with id " + id + " does not exist");
        }

        // get total and max scores
        calculateTotalScores(eval);

        return eval;
	}
	
	@Override
	public Page<Eval> getEvalsByBatch(Pageable pageable, Integer batchId, String evalType){

        checkBatch(batchId);
        Page<Eval> evals;
		if ("both".equalsIgnoreCase(evalType)) {
			evals = dao.findAllByBatchId(pageable, batchId);
		} else {
			evals = dao.findAllByBatchIdAndEvalTypeDescriptionIgnoreCase(pageable, batchId, evalType);
		}
        calculateAllTotalScores(evals);
        return evals;
	}
	
	@Override
	public Page<Eval> getEvalsByWeek(Pageable pageable, Integer batchId, Integer num, String evalType){

        checkBatch(batchId);

		if ("both".equalsIgnoreCase(evalType)) {
			return dao.findAllByBatchIdAndWeek(pageable, batchId, num);
		} else {
			return dao.findAllByBatchIdAndWeekAndEvalTypeDescriptionIgnoreCase(pageable, batchId, num, evalType);
		}
	}
	
	@Override
	public Page<Eval> getEvalsByPerson(Pageable pageable, Integer id, String evalParam) {
        
        checkTrainee(id);

		if ("both".equalsIgnoreCase(evalParam)) {
			return dao.findAllByTraineeId(pageable, id);
		} else {
			return dao.findAllByTraineeIdAndEvalTypeDescriptionIgnoreCase(pageable, id, evalParam);
		}
	}
	
	@Override
	public Page<Eval> getPersonEvalsByWeek(Pageable pageable, Integer traineeId, Integer num, String evalParam) {

        checkTrainee(traineeId);

		if ("both".equalsIgnoreCase(evalParam)) {
			return dao.findAllByTraineeIdAndWeek(pageable, traineeId, num);
		} else {
			return dao.findAllByTraineeIdAndWeekAndEvalTypeDescriptionIgnoreCase(pageable, traineeId, num, evalParam);
		}
	}
	
	@Override
	public EvalComment getCommentById(Integer id) {

        EvalComment comment = commentDao.findOne(id);

        if(comment == null){
	        throw new NotFoundException("Comment with id " + id + " not found");
		}

		return comment;
	}
	
//UPDATE-------------------------------	
	@Override
	@Transactional
	public Eval updateEval(Eval eval, Integer id) {
		Eval currEval = getEvalById(id);
		
		if(eval.getBatch() != null){
			currEval.setBatch(eval.getBatch());
		}
		if(eval.getDate() != null){
			currEval.setDate(eval.getDate());
		}
		if(eval.getEvalType() != null){
			currEval.setEvalType(eval.getEvalType());
		}
		if(eval.getTrainee() != null){
			currEval.setTrainee(eval.getTrainee());
		}
		if(eval.getWeek() != null){
			currEval.setWeek(eval.getWeek());
		}
        
        dao.saveAndFlush(currEval);
        entityManager.refresh(currEval);
        return currEval;
	}
	
	@Override
	@Transactional
	public EvalComment updateComment(EvalComment comment, Integer id) {
		
		EvalComment currComment = getCommentById(id);
		
		if(comment.getCommentText() != null){
			currComment.setCommentText(comment.getCommentText());
		} else {
			throw new ConstraintViolationException("Missing required field commentText (String)", null);
		}
		
		return commentDao.save(currComment);
	}
	
//DELETE-------------------------------
	@Override
	@Transactional
	public String deleteEval(Integer id) {
		Eval eval = getEvalById(id);

		dao.delete(eval);
		return "Evaluation: " + id + " - DELETED";
	}
	
	@Override
	@Transactional
	public String deleteComment(Integer id) {
		EvalComment comment = getCommentById(id);
				
		commentDao.delete(comment);
		return "Evaluation Comment: " + id + " - DELETED";
	}

    // private utility methods
    private void checkTrainee(Integer id) {
        if (personDao.findOne(id) == null) {
            throw new ConstraintViolationException("Trainee with id " + id + " does not exist", null);
        }
    }
    
    private void checkBatch(Integer id) {
        if (batchDao.findOne(id) == null) {
            throw new ConstraintViolationException("Batch with id " + id + " does not exist", null);
        }
    }

    private void calculateTotalScores(Eval eval) {
        int communicationScore = 0;
        int knowledgeScore = 0;
        int maxCommunicationScore = 0;
        int maxKnowledgeScore = 0;
        for (QuestionEval question : eval.getQuestions()) {
            communicationScore += question.getCommunicationScore();
            knowledgeScore += question.getKnowledgeScore();
            maxCommunicationScore += question.getQuestionPool().getMaxCommunicationScore();
            maxKnowledgeScore += question.getQuestionPool().getMaxKnowledgeScore();
        }

        eval.setEvalCommunicationScore(communicationScore);
        eval.setEvalKnowledgeScore(knowledgeScore);
        eval.setEvalMaxCommunicationScore(maxCommunicationScore);
        eval.setEvalMaxKnowledgeScore(maxKnowledgeScore);
    }

    private void calculateAllTotalScores(Page<Eval> evals) {
        evals.forEach((eval) -> {
            calculateTotalScores(eval);
        });
    }

}
