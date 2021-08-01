package survey;

import com.alibaba.excel.util.CollectionUtils;
import com.google.common.base.Strings;
import com.google.common.collect.Lists;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class AnswerExcel {
    private static final List<String> title = Lists.newArrayList("序号", "提交答卷时间", "所用时间", "来自IP");

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
    private static final String SKIP = "(跳过)";
    private static final String OTHER = "other";
    private static final String OTHER_FORMATTER = "%s〖%s〗";


    public List<String> head(List<SurveyQuestion> questionList) {
        List<String> result = new ArrayList<>(title);
        questionList.stream()
                .map(SurveyQuestion::getTitle)
                .forEach(result::add);
        return result;
    }

    public List<List<String>> data(List<SurveyAnswer> answerList, List<SurveyQuestion> questionList) {
        List<List<String>> result = new ArrayList<>();
        List<String> head = head(questionList);
        result.add(head);
        for (SurveyAnswer answer : answerList) {
            List<String> row = row(questionList, answer);
            result.add(row);
        }
        return result;
    }

    private List<String> row(List<SurveyQuestion> questionList, SurveyAnswer answer) {
        List<String> result = new ArrayList<>();
        result.add(answer.getUserId().toString());
        result.add(answer.getSubmitTime().format(formatter));
        result.add(answer.getDurationSecond().toString() + "秒");
        result.add(answer.getIp());

        result.addAll(answerData(questionList, answer));
        return result;
    }

    private List<String> answerData(List<SurveyQuestion> questionList, SurveyAnswer answer) {
        List<String> result = new ArrayList<>();
        for (SurveyQuestion question : questionList) {
            result.add(answerCell(question.getOptionList(), answer.getAnswerMap().get(question.getId())));
        }
        return result;
    }

    private String answerCell(List<SurveyOption> optionList, SurveyOptionAnswer option) {
        if (option == null) {
            return SKIP;
        }

        String text = option.getText();
        if (CollectionUtils.isEmpty(option.getOptionId()) && Strings.isNullOrEmpty(text)) {
            return SKIP;
        }


        // mapped title
        List<String> optionTitleList = optionList.stream()
                .filter(opt -> option.getOptionId().contains(opt.getId()))
                .map(SurveyOption::getTitle).collect(Collectors.toList());

        // text
        if (text != null && !text.isEmpty()) {
            Optional<String> otherOpt = optionList.stream()
                    .map(SurveyOption::getId)
                    .filter(id -> id.equals(OTHER))
                    .findAny();
            String optionTitle;
            if (otherOpt.isPresent()) {
                optionTitle = String.format(OTHER_FORMATTER, otherOpt.get(), text);
            } else {
                optionTitle = text;
            }
            optionTitleList.add(optionTitle);
        }

        // other id
        if (optionTitleList.isEmpty()) {
            optionTitleList.addAll(option.getOptionId());
        }

        return String.join("┋", optionTitleList.toArray(new String[]{}));
    }
}
