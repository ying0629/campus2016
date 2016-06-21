package util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by songshaoying on 16/6/5.
 */
public class FileUtil {
    public static List<String> readFileByLine(String path) throws IOException {
        List<String>  result = new ArrayList<String>();
        BufferedReader br = new BufferedReader(new FileReader(path));
        String temp = br.readLine();
        while (temp != null) {
            result.add(temp);
            temp = br.readLine();
        }
        return result;
    }
}
