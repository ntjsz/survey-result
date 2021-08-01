package survey;

import com.alibaba.fastjson.JSONObject;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.stream.Collectors;

public class SurveySource {

    public List<SurveyAnswer> convert(List<SurveyAnswerRaw> rawList) {
        return rawList.stream().map(this::convert).collect(Collectors.toList());
    }

    private SurveyAnswer convert(SurveyAnswerRaw raw) {
        SurveyAnswer result = new SurveyAnswer();
        result.setId(raw.getId());
        result.setSurveyId(raw.getSurveyId());
        result.setUserId(raw.getUserId());
        result.setIp(raw.getIp());
        result.setDurationSecond(raw.getDuration());
        result.setSubmitTime(LocalDateTime.ofInstant(Instant.ofEpochMilli(raw.getCreateTime()), ZoneId.systemDefault()));
        result.setAnswerMap(convert(raw.getAnswer()));

        return result;
    }

    private LinkedHashMap<String, SurveyOptionAnswer> convert(String answer) {
        LinkedHashMap<String, SurveyOptionAnswer> result = new LinkedHashMap<>();

        JSONObject ans = JSONObject.parseObject(answer);
        for (String key : ans.keySet()) {
            result.put(key, convert(ans.getJSONObject(key)));
        }
        return result;
    }

    private SurveyOptionAnswer convert(JSONObject option) {
        SurveyOptionAnswer result = new SurveyOptionAnswer();

        if (option.containsKey("option")) {
            String s = option.getString("option");
            List<String> collect = Arrays.stream(s.split(",")).collect(Collectors.toList());
            result.setOptionId(collect);
        }

        if (option.containsKey("text")) {
            result.setText(option.getString("text"));
        }

        return result;
    }
}
