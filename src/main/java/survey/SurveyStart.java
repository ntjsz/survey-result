package survey;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.write.metadata.WriteSheet;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class SurveyStart {
    public static void main(String[] args) {
        SurveySqlReaderJson reader = new SurveySqlReaderJson();
        List<SurveyAnswerRaw> read = reader.read();
        SurveySource surveySource = new SurveySource();
        List<SurveyAnswer> answerList = surveySource.convert(read);

        SurveyContentReader contentReader = new SurveyContentReader();
        List<SurveyQuestion> questionList = contentReader.read();

        SurveyStart start = new SurveyStart();
        start.write(answerList, questionList);
    }

    private void write(List<SurveyAnswer> answerList, List<SurveyQuestion> questionList) {
        AnswerExcel answerExcel = new AnswerExcel();
        AnswerFlatExcel flatExcel = new AnswerFlatExcel();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd-HHmmss");
        String now = LocalDateTime.now().format(formatter);

        String fileName = "/Users/tyc/test/问卷" + now + ".xlsx";
        try {
            Files.createFile(Paths.get(fileName));
        } catch (IOException e) {
            e.printStackTrace();
        }
        ExcelWriter excelWriter = null;
        try {
            excelWriter = EasyExcel.write(fileName).build();
            WriteSheet writeSheet = EasyExcel.writerSheet("Sheet1").build();
            excelWriter.write(answerExcel.data(answerList, questionList), writeSheet);

            WriteSheet writeSheet2 = EasyExcel.writerSheet("Sheet2").build();
            excelWriter.write(flatExcel.data(answerList, questionList), writeSheet2);
        } finally {
            // 千万别忘记finish 会帮忙关闭流
            if (excelWriter != null) {
                excelWriter.finish();
            }
        }
    }
}
