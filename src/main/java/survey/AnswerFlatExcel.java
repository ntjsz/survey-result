package survey;

import com.google.common.collect.Lists;
import survey.flat.FlatExcel;
import survey.flat.FlatExcelSelector;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class AnswerFlatExcel {
    private static final List<String> title = Lists.newArrayList("序号", "提交答卷时间", "所用时间", "来自IP");

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");

    private final FlatExcelSelector selector = new FlatExcelSelector();

    public List<String> head(List<SurveyQuestion> questionList) {
        List<String> result = new ArrayList<>(title);
        for (SurveyQuestion surveyQuestion : questionList) {
            FlatExcel flatExcel = selector.getFlatExcel(surveyQuestion.getType());
            result.addAll(flatExcel.title(surveyQuestion));
        }
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
            FlatExcel flatExcel = selector.getFlatExcel(question.getType());
            result.addAll(flatExcel.answer(question, answer));
        }
        return result;
    }
}
