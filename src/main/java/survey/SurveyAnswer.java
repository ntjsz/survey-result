package survey;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;

public class SurveyAnswer {
    private Long id;
    private Long surveyId;
    private Long userId;
    private Integer durationSecond;
    private String ip;
    private LinkedHashMap<String, SurveyOptionAnswer> answerMap;
    private LocalDateTime submitTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getSurveyId() {
        return surveyId;
    }

    public void setSurveyId(Long surveyId) {
        this.surveyId = surveyId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Integer getDurationSecond() {
        return durationSecond;
    }

    public void setDurationSecond(Integer durationSecond) {
        this.durationSecond = durationSecond;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public LinkedHashMap<String, SurveyOptionAnswer> getAnswerMap() {
        return answerMap;
    }

    public void setAnswerMap(LinkedHashMap<String, SurveyOptionAnswer> answerMap) {
        this.answerMap = answerMap;
    }

    public LocalDateTime getSubmitTime() {
        return submitTime;
    }

    public void setSubmitTime(LocalDateTime submitTime) {
        this.submitTime = submitTime;
    }
}
