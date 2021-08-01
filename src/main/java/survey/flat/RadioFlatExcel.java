package survey.flat;

import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import survey.SurveyAnswer;
import survey.SurveyOptionAnswer;
import survey.SurveyQuestion;

import java.util.ArrayList;
import java.util.List;

public class RadioFlatExcel implements FlatExcel {
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

        List<String> optionList = new ArrayList<>(option.getOptionId());
        if (!Strings.isNullOrEmpty(option.getText())) {
            optionList.add(FlatExcelSelector.OTHER);
        }

        result.add(String.join(",", optionList));
        return result;
    }
}
