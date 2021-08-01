package survey;

import com.google.common.base.Strings;
import org.apache.commons.collections4.CollectionUtils;

import java.util.List;

public class SurveyOptionAnswer {
    private List<String> optionId;
    private String text;

    public boolean isEmpty() {
        return CollectionUtils.isEmpty(optionId) && Strings.isNullOrEmpty(text);
    }

    public List<String> getOptionId() {
        return optionId;
    }

    public void setOptionId(List<String> optionId) {
        this.optionId = optionId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
