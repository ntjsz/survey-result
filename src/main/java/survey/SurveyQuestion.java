package survey;

import java.util.List;

public class SurveyQuestion {
    private String id;
    private String title;
    private Integer type;
    private List<SurveyOption> optionList;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<SurveyOption> getOptionList() {
        return optionList;
    }

    public void setOptionList(List<SurveyOption> optionList) {
        this.optionList = optionList;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }
}
