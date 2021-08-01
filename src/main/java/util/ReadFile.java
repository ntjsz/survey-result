package util;

import org.apache.ibatis.io.Resources;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;

public class ReadFile {
    public static String read(String fileName) {
        try {
            Reader reader = Resources.getResourceAsReader(fileName);
            BufferedReader reader1 = new BufferedReader(reader);
            StringBuilder sb = new StringBuilder();
            String s;
            while ((s = reader1.readLine()) != null) {
                sb.append(s);
                sb.append("\r\n");
            }
            return sb.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String read() {
        return read("f1.txt");
    }
}
