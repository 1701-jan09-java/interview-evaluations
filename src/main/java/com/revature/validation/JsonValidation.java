package com.revature.validation;

import com.revature.domain.QuestionEval;
import com.revature.domain.QuestionPool;
import com.revature.repositories.QuestionRepository;
import javax.validation.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class JsonValidation {

    @Autowired
    private QuestionRepository questionDao;

    public void validateQuestionEvaluationFields(QuestionEval question) {
        if(question.getCommunicationScore() == null){
			throw new ConstraintViolationException("Missing required field "
                    + "communicationScore (Integer)", null);
		}
		if(question.getKnowledgeScore() == null){
			throw new ConstraintViolationException("Missing required field "
                    + "knowledgeScore (Integer)", null);
		}
		if(
                question.getQuestionPool() == null
                || question.getQuestionPool().getId() == null
                || question.getQuestionPool().getId() == 0
        ){
			throw new ConstraintViolationException("Missing required field "
                    + "questionPool.id (Integer)", null);
		}

        validateScores(question);
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
}
