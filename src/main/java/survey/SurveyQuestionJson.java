package survey;

import java.util.List;

public class SurveyQuestionJson {
    private String questionId;
    private String questionTitle;
    private Integer questionType;

    private SurveyQuestionOptionJson otherOption;
    private List<SurveyQuestionOptionJson> options;

    public Integer getQuestionType() {
        return questionType;
    }

    public void setQuestionType(Integer questionType) {
        this.questionType = questionType;
    }

    public String getQuestionId() {
        return questionId;
    }

    public void setQuestionId(String questionId) {
        this.questionId = questionId;
    }

    public SurveyQuestionOptionJson getOtherOption() {
        return otherOption;
    }

    public void setOtherOption(SurveyQuestionOptionJson otherOption) {
        this.otherOption = otherOption;
    }

    public List<SurveyQuestionOptionJson> getOptions() {
        return options;
    }

    public void setOptions(List<SurveyQuestionOptionJson> options) {
        this.options = options;
    }

    public String getQuestionTitle() {
        return questionTitle;
    }

    public void setQuestionTitle(String questionTitle) {
        this.questionTitle = questionTitle;
    }

    public static class SurveyQuestionOptionJson {
        private String answerId;
        private String answerTitle;

        public String getAnswerId() {
            return answerId;
        }

        public void setAnswerId(String answerId) {
            this.answerId = answerId;
        }

        public String getAnswerTitle() {
            return answerTitle;
        }

        public void setAnswerTitle(String answerTitle) {
            this.answerTitle = answerTitle;
        }
    }
}
