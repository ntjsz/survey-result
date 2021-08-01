package survey;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import util.ReadFile;

import java.util.ArrayList;
import java.util.List;

public class SurveySqlReaderJson {
    public List<SurveyAnswerRaw> read() {
        String read = ReadFile.read("survey/export_result.json");
        if (read == null) {
            return new ArrayList<>();
        }

        JSONObject jo = JSON.parseObject(read);
        JSONArray data = jo.getJSONArray("data");

        List<SurveyAnswerRaw> result = new ArrayList<>();
        for (int i = 0; i < data.size(); i++) {
            JSONObject answer = data.getJSONObject(i);
            result.add(convert(answer));

        }
        return result;
    }

    private SurveyAnswerRaw convert(JSONObject answer) {
        SurveyAnswerRaw result = new SurveyAnswerRaw();
        result.setId(answer.getLong("id"));
        result.setSurveyId(answer.getLong("survey_id"));
        result.setUserId(answer.getLong("user_id"));
        result.setDuration(answer.getInteger("duration"));
        result.setIp(answer.getString("ip"));
        result.setAnswer(answer.getString("answer"));
        result.setCreateTime(answer.getLong("create_time"));
        return result;
    }
}
