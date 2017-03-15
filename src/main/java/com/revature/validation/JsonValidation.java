package com.revature.validation;


import com.revature.domain.Batch;
import com.revature.domain.Eval;
import com.revature.domain.EvalComment;
import com.revature.domain.Person;
import com.revature.domain.PersonRole;
import com.revature.domain.QuestionComment;
import com.revature.domain.QuestionEval;
import com.revature.domain.QuestionPool;
import com.revature.repositories.BatchRepository;
import com.revature.repositories.EvalRepository;
import com.revature.repositories.EvalTypeRepository;
import com.revature.repositories.PersonRepository;
import com.revature.repositories.PersonRoleRepository;
import com.revature.repositories.QuestionCommentRepository;
import com.revature.repositories.QuestionEvalRepository;
import com.revature.repositories.QuestionRepository;
import com.revature.repositories.SubjectRepository;
import javax.validation.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class JsonValidation {

    @Autowired
    private QuestionRepository questionDao;

    @Autowired
    private SubjectRepository subjectDao;

    @Autowired
    private EvalRepository evalDao;

    @Autowired
    private QuestionEvalRepository questionEvalDao;

    @Autowired
    private QuestionCommentRepository questionCommentDao;

    @Autowired
    private EvalTypeRepository evalTypeDao;

    @Autowired
    private PersonRepository personDao;

    @Autowired
    private BatchRepository batchDao;
    
    @Autowired
	private PersonRoleRepository personRoleDao;
    

    public void validateQuestionPoolFields(QuestionPool question) {

        validateIdNotSpecified(question.getId());

        if (question.getMaxCommunicationScore() == null) {
			throw new ConstraintViolationException("Missing required field "
                    + "maxCommunicationScore (Integer)", null);
		}

		if (question.getMaxKnowledgeScore() == null) {
			throw new ConstraintViolationException("Missing required field "
                    + "maxKnowledgeScore (Integer)", null);
		}

		if (question.getQuestionText() == null) {
			throw new ConstraintViolationException("Missing required field "
                    + "questionText (String)", null);
		}

		if (question.getSubject() == null || question.getSubject().getId() == null) {
			throw new ConstraintViolationException("Missing required field "
                    + "subject.id (Integer)", null);
		}
        if (subjectDao.findOne(question.getSubject().getId()) == null){
            throw new ConstraintViolationException("Subject with id "
                    + question.getSubject().getId() + " does not exist", null);
        }
    }

    public void validateEvalFields(Eval eval) {

        validateIdNotSpecified(eval.getId());

		if(eval.getTrainee() == null || eval.getTrainee().getId() == 0){
			throw new ConstraintViolationException("Missing required field "
                    + "trainee.id (Integer)", null);
		}
        validateTraineeExists(eval.getTrainee().getId());

		if(eval.getWeek() == null){
			throw new ConstraintViolationException("Missing required field "
                    + "week (Integer)", null);
		}
        validateWeek(eval.getWeek());

		if(eval.getEvalType() == null || eval.getEvalType().getId() == 0){
			throw new ConstraintViolationException("Missing required field "
                    + "evalType.id (Integer)", null);
		}
        validateEvalTypeExists(eval.getEvalType().getId());

		if(eval.getBatch() == null || eval.getBatch().getId() == 0){
			throw new ConstraintViolationException("Missing required field "
                    + "batch.id (Integer)", null);
		}
        validateBatchExists(eval.getBatch().getId());
    }

    public void validateEvalCommentFields(EvalComment comment) {

        validateIdNotSpecified(comment.getId());

		if (comment.getCommentText() == null) {
			throw new ConstraintViolationException("Missing required field "
                    + "commentText (String)", null);
		}
    }

    public void validateQuestionCommentFields(QuestionComment comment) {

        validateIdNotSpecified(comment.getId());

		if (comment.getCommentText() == null) {
			throw new ConstraintViolationException("Missing required field "
                    + "commentText (String)", null);
		}
    }

    public void validateQuestionEvaluationFields(QuestionEval question) {

        validateIdNotSpecified(question.getId());

        if(
                question.getQuestionPool() == null
                || question.getQuestionPool().getId() == null
                || question.getQuestionPool().getId() == 0
        ){
			throw new ConstraintViolationException("Missing required field "
                    + "questionPool.id (Integer)", null);
		}
        validateQuestionPoolExists(question.getQuestionPool().getId());

        validateScores(question);
    }

    public void validateQuestionEvaluationFieldsExistingEval(QuestionEval question, Integer evalId) {

        if(
            evalId == null
            || evalId == 0
        ){
            throw new ConstraintViolationException("Missing required field "
                    + "eval.id (Integer)", null);
		}

        if (evalDao.findOne(evalId) == null) {
            throw new ConstraintViolationException("Evaluation with id "
                    + evalId + " does not exist", null);
        }

        validateQuestionEvaluationFields(question);
    }

    public void validateScores(QuestionEval question) {

        QuestionPool qp = questionDao.findOne(question.getQuestionPool().getId());

        if (question.getCommunicationScore() == null) {
            throw new ConstraintViolationException("Missing required field "
                    + "communicationScore (Integer) [QuestionPool id: "
                    + qp.getId() + "]", null);
        }

        if (question.getCommunicationScore() < 0) {
            throw new ConstraintViolationException("Communication score cannot "
                    + "be negative [QuestionPool id: " + qp.getId() + "]", null);
        }

        if (question.getCommunicationScore() > qp.getMaxCommunicationScore()) {
            throw new ConstraintViolationException("Communication score cannot "
                    + "be greater than maximum score " + qp.getMaxKnowledgeScore()
                    + " [QuestionPool id: " + qp.getId() + "]", null);
        }

        if (question.getKnowledgeScore() == null) {
            throw new ConstraintViolationException("Missing required field "
                    + "knowledgeScore (Integer) [QuestionPool id: " + qp.getId()
                    + "]", null);
        }

        if (question.getKnowledgeScore() < 0) {
            throw new ConstraintViolationException("Knowledge score cannot be "
                    + "negative [QuestionPool id: " + qp.getId() + "]", null);
        }

        if (question.getKnowledgeScore() > qp.getMaxKnowledgeScore()) {
            throw new ConstraintViolationException("Knowledge score cannot be "
                    + "greater than maximum score " + qp.getMaxKnowledgeScore()
                    + " [QuestionPool id: " + qp.getId() + "]", null);
        }
    }
	
	public void validateBatchFields(Batch batch) {
		
		validateIdNotSpecified(batch.getId());
		
		if (batch.getName() == null) {
			throw new ConstraintViolationException("Missing required field "
                    + "name (String)", null);
		}
	}
	
	public void validatePersonFields(Person person){
		
		if (person.getFirstName() == null || "".equals(person.getFirstName())) {
			throw new ConstraintViolationException("Missing required field firstName (String)", null);
		}
		
		if (person.getLastName() == null || "".equals(person.getLastName())) {
			throw new ConstraintViolationException("Missing required field lastName (String)", null);
		}
		
		if (person.getPersonRole() == null) {
			throw new ConstraintViolationException("Missing required field personRole.id (Integer)", null);
		}
		
		validatePersonRoleExists(person.getPersonRole().getId());
    	
    }
    
    public void validateIdNotSpecified(Integer id) {
        if(id != null && id != 0) {
			throw new ConstraintViolationException("Id is automatically "
                    + "generated - do not include in post request", null);
		}
    }

    public void validateBatchExists(Integer batchId) {
        if (batchDao.findOne(batchId) == null) {
            throw new ConstraintViolationException("Batch with id " + batchId
                    + " does not exist", null);
        }
    }

    public void validateTraineeExists(Integer traineeId) {
        if (personDao.findOne(traineeId) == null) {
            throw new ConstraintViolationException("Trainee with id " + traineeId
                    + " does not exist", null);
        }
    }

    public void validateEvalExists(Integer evalId) {
        if (evalDao.findOne(evalId) == null) {
			throw new ConstraintViolationException("Evaluation with id "
                    + evalId + " does not exist", null);
		}
    }

    public void validateWeek(Integer weekNumber) {
        if(weekNumber <= 0){
            throw new ConstraintViolationException("Week number must be positive"
                    , null);
        }
    }

    public void validateEvalTypeExists(Integer evalTypeId){
        if(evalTypeDao.findOne(evalTypeId) == null){
            throw new ConstraintViolationException("Eval Type with id "
                    + evalTypeId + " does not exist", null);
        }
    }
    
    public void validateQuestionEvalExists(Integer qEvalId) {
        if (questionEvalDao.findOne(qEvalId) == null) {
			throw new ConstraintViolationException("Question Evaluation "
                    + "with id " + qEvalId + " does not exist", null);
		}
    }

    public void validateQuestionCommentExists(Integer qCommentId) {
        if (questionCommentDao.findOne(qCommentId) == null) {
			throw new ConstraintViolationException("Question Comment with id "
                    + qCommentId + " does not exist", null);
		}
    }

    public void validateQuestionPoolExists(Integer qpId) {
        if (questionDao.findOne(qpId) == null) {
            throw new ConstraintViolationException(
                    "Question with id " + qpId + " does not exist",null);
        }
    }

	public void validatePersonRoleExists(Integer id) {
		if (personRoleDao.findOne(id) == null) {
			throw new ConstraintViolationException("PersonRole with id " 
					+ id + "does not exist", null);
		}
	}
	
    public void validatePersonRole(PersonRole personRole){
    	
    	if(personRole == null){
			throw new ConstraintViolationException("Invalid PersonRole Field", null);
		}		
    }
	
    
    public void validatePersonById(int id){
        if (personDao.findOne(id) == null) {
            throw new ConstraintViolationException(
                    "Person with id " + id + " does not exist",null);
        }
    }

	public void validateTraineeInBatch(Integer personId, Integer batchId) {
		Person person = personDao.findOne(personId);
		Batch batch = batchDao.findOne(batchId);
        if (!batch.getPersons().contains(person)) {
			throw new ConstraintViolationException("Trainee with id " + personId
                    + " is not in batch " + batchId, null); 
		}
	}
	
	public void validateIntegerArray(Integer[] integers) {
		if (integers.length == 0) {
			throw new ConstraintViolationException("Array can not be empty",null);
		} 
		for (Integer integer : integers) {
			if (integer == null) {
				throw new ConstraintViolationException("Id can not be null",null);
			}
		}
	}

}
