package survey.flat;

import survey.SurveyAnswer;
import survey.SurveyQuestion;

import java.util.List;

public interface FlatExcel {
    List<String> title(SurveyQuestion question);

    List<String> answer(SurveyQuestion question, SurveyAnswer answer);
}
