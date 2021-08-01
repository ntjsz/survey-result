package survey;

import util.ReadFile;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class SurveySqlReaderTab {
    public List<SurveyAnswerRaw> read() {
        String read = ReadFile.read("survey/s.txt");
        if (read == null) {
            return new ArrayList<>();
        }
        String[] split = read.split("\r\n");
        return Arrays.stream(split).filter(Objects::nonNull)
                .map(String::trim)
                .filter(s -> !s.isEmpty())
                .map(this::convert)
                .collect(Collectors.toList());
    }

    private SurveyAnswerRaw convert(String rawStr) {
        String[] split = rawStr.split("\t");

        SurveyAnswerRaw result = new SurveyAnswerRaw();
        result.setId(Long.valueOf(split[0]));
        result.setSurveyId(Long.valueOf(split[1]));
        result.setUserId(Long.valueOf(split[2]));
        result.setDuration(Integer.valueOf(split[3]));
        result.setIp(split[4]);
        result.setAnswer(split[5]);
        result.setCreateTime(Long.valueOf(split[split.length - 2]));
        return result;
    }
}
