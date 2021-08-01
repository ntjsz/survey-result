package survey;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import util.ReadFile;

import java.util.ArrayList;
import java.util.List;

public class SurveyContentReader {

    public List<SurveyQuestion> read() {
        String text = ReadFile.read("survey/c.json");
        return convert(text);
    }

    private List<SurveyQuestion> convert(String s) {
        List<SurveyQuestion> result = new ArrayList<>();

        JSONObject jo = JSONObject.parseObject(s);
        JSONArray content = jo.getJSONArray("content");

        for (int i = 0; i < content.size(); i++) {
            JSONObject questionArea = content.getJSONObject(i);
            JSONArray questions = questionArea.getJSONArray("questions");
            for (int j = 0; j < questions.size(); j++) {
                JSONObject q = questions.getJSONObject(j);
                result.add(convert(q));
            }
        }

        return result;
    }

    private SurveyQuestion convert(JSONObject q) {
        SurveyQuestionJson questionJson = q.toJavaObject(SurveyQuestionJson.class);

        SurveyQuestion result = new SurveyQuestion();
        result.setId(questionJson.getQuestionId());
        result.setTitle(questionJson.getQuestionTitle());
        result.setType(questionJson.getQuestionType());
        List<SurveyOption> optionList = new ArrayList<>();
        result.setOptionList(optionList);

        List<SurveyQuestionJson.SurveyQuestionOptionJson> options = questionJson.getOptions();
        if (options != null) {
            for (SurveyQuestionJson.SurveyQuestionOptionJson option : options) {
                SurveyOption op = new SurveyOption();
                op.setId(option.getAnswerId());
                op.setTitle(option.getAnswerTitle());
                optionList.add(op);
            }
        }

        SurveyQuestionJson.SurveyQuestionOptionJson otherOption = questionJson.getOtherOption();
        if (otherOption != null) {
            SurveyOption op = new SurveyOption();
            op.setId(otherOption.getAnswerId());
            op.setTitle(otherOption.getAnswerTitle());
            optionList.add(op);
        }

        return result;
    }
}
