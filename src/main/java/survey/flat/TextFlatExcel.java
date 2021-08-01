package survey.flat;

import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import survey.SurveyAnswer;
import survey.SurveyOptionAnswer;
import survey.SurveyQuestion;

import java.util.ArrayList;
import java.util.List;

public class TextFlatExcel implements FlatExcel {
    @Override
    public List<String> title(SurveyQuestion question) {
        String title = question.getTitle();
        return Lists.newArrayList(title);
    }

    @Override
    public List<String> answer(SurveyQuestion question, SurveyAnswer answer) {
        List<String> result = new ArrayList<>();
        SurveyOptionAnswer option = answer.getAnswerMap().get(question.getId());

        if (option == null || option.isEmpty()) {
            result.add(FlatExcelSelector.SKIP);
            return result;
        }

        if (Strings.isNullOrEmpty(option.getText())) {
            result.add(FlatExcelSelector.UNSELECTED);
        } else {
            result.add(FlatExcelSelector.SELECTED);
        }
        return result;
    }
}
