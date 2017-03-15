package com.revature.services;

import com.revature.domain.Eval;
import com.revature.domain.EvalComment;
import com.revature.domain.QuestionEval;
import com.revature.repositories.EvalCommentRepository;
import com.revature.repositories.EvalRepository;
import com.revature.validation.JsonValidation;
import com.revature.validation.exceptions.NotFoundException;
import java.sql.Date;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
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
    private QuestionLogic questionLogic;

    @Autowired
    private JsonValidation validation;
	
	@PersistenceContext
    private EntityManager entityManager;
	

//CREATE----------------------------
	@Override
	public Eval createEval(Eval eval) {
		
		System.out.println(eval);
		
		validation.validateEvalFields(eval);

        Date date = new Date((new java.util.Date()).getTime());
		eval.setDate(date);
		
		// Set eval property of questions and qEval property of question comments
		// This is required because these fields are not auto-generated in json mapping
		if (eval.getQuestions() != null) {
			eval.getQuestions().forEach((question) -> {
				question.setEval(eval);
				validation.validateQuestionEvaluationFields(question);

                // also set qEval of question comments
				if (question.getComments() != null) {
					question.getComments().forEach((comment) -> {
						comment.setQuestionEval(question);
					});
				}

                // increment question usage count and update date last used
                questionLogic.updateQuestionUsed(question.getQuestionPool().getId());
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
		
		comment.setEval(dao.findOne(evalId));

        validation.validateEvalExists(evalId);
		validation.validateEvalCommentFields(comment);
		
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
	public Page<Eval> getEvalsByBatch(Pageable pageable, Integer batchId,
            String evalType){

        validation.validateBatchExists(batchId);
        
        Page<Eval> evals;
		if ("both".equalsIgnoreCase(evalType)) {
			evals = dao.findAllByBatchId(pageable, batchId);
		} else {
			evals = dao.findAllByBatchIdAndEvalTypeDescriptionIgnoreCase(
                    pageable, batchId, evalType);
		}
        calculateAllTotalScores(evals);
        return evals;
	}
	
	@Override
	public Page<Eval> getEvalsByWeek(Pageable pageable, Integer batchId,
            Integer num, String evalType){

        validation.validateBatchExists(batchId);

		Page<Eval> evals;
        if ("both".equalsIgnoreCase(evalType)) {
			evals = dao.findAllByBatchIdAndWeek(pageable, batchId, num);
		} else {
			evals = dao.findAllByBatchIdAndWeekAndEvalTypeDescriptionIgnoreCase(
                    pageable, batchId, num, evalType);
		}
        calculateAllTotalScores(evals);
        return evals;
	}
	
	@Override
	public Page<Eval> getEvalsByPerson(Pageable pageable, Integer traineeId, String evalParam) {
        
        validation.validateTraineeExists(traineeId);

        Page<Eval> evals;
		if ("both".equalsIgnoreCase(evalParam)) {
			evals = dao.findAllByTraineeId(pageable, traineeId);
		} else {
			evals = dao.findAllByTraineeIdAndEvalTypeDescriptionIgnoreCase(
                    pageable, traineeId, evalParam);
		}
        calculateAllTotalScores(evals);
        return evals;
	}
	
	@Override
	public Page<Eval> getPersonEvalsByWeek(Pageable pageable, Integer traineeId,
            Integer num, String evalParam) {

        validation.validateTraineeExists(traineeId);
        
        Page<Eval> evals;
		if ("both".equalsIgnoreCase(evalParam)) {
			evals = dao.findAllByTraineeIdAndWeek(pageable, traineeId, num);
		} else {
			evals = dao.findAllByTraineeIdAndWeekAndEvalTypeDescriptionIgnoreCase(
                    pageable, traineeId, num, evalParam);
		}
        calculateAllTotalScores(evals);
        return evals;
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
            validation.validateBatchExists(eval.getBatch().getId());
			currEval.setBatch(eval.getBatch());
		}
		if(eval.getDate() != null){
			currEval.setDate(eval.getDate());
		}
		if(eval.getEvalType() != null){
            validation.validateEvalTypeExists(eval.getEvalType().getId());
			currEval.setEvalType(eval.getEvalType());
		}
		if(eval.getTrainee() != null){
            validation.validateTraineeExists(eval.getTrainee().getId());
			currEval.setTrainee(eval.getTrainee());
		}
		if(eval.getWeek() != null){
            validation.validateWeek(eval.getWeek());
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
		}
		
		return commentDao.save(currComment);
	}
	
//DELETE-------------------------------
	@Override
	@Transactional
	public String deleteEval(Integer id) {
		Eval eval = getEvalById(id);

		dao.delete(eval);
		return "{\"text\":\"Evaluation: " + id + " - DELETED\"}";
	}
	
	@Override
	@Transactional
	public String deleteComment(Integer id) {
		EvalComment comment = getCommentById(id);
				
		commentDao.delete(comment);
		return "{\"text\":\"Evaluation Comment: " + id + " - DELETED\"}";
	}

    // private utility methods
    
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
