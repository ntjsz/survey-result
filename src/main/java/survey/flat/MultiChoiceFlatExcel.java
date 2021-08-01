package survey.flat;

import com.google.common.base.Strings;
import survey.SurveyAnswer;
import survey.SurveyOption;
import survey.SurveyOptionAnswer;
import survey.SurveyQuestion;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class MultiChoiceFlatExcel implements FlatExcel{
    @Override
    public List<String> title(SurveyQuestion question) {
        return question.getOptionList().stream()
                .map(option -> question.getId() + "(" + option.getTitle() + ")")
                .collect(Collectors.toList());
    }

    @Override
    public List<String> answer(SurveyQuestion question, SurveyAnswer answer) {
        List<String> result = new ArrayList<>();
        SurveyOptionAnswer option = answer.getAnswerMap().get(question.getId());

        if (option == null || option.isEmpty()) {
            int size = question.getOptionList().size();
            for (int i = 0; i < size; i++) {
                result.add(FlatExcelSelector.SKIP);
            }
            return result;
        }

        Set<String> optionList = new HashSet<>(option.getOptionId());
        if(!Strings.isNullOrEmpty(option.getText())){
            optionList.add(FlatExcelSelector.OTHER);
        }

        return question.getOptionList().stream()
                .map(SurveyOption::getId)
                .map(optId -> {
                    if (optionList.contains(optId)) {
                        return FlatExcelSelector.SELECTED;
                    } else {
                        return FlatExcelSelector.UNSELECTED;
                    }
                })
                .collect(Collectors.toList());
    }
}
