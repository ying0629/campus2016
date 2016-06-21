package util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by songshaoying on 16/6/5.
 */
public class FileUtil {
    public static String readFile(String path){
        StringBuffer result = new StringBuffer();
        File file = new File(path);
        BufferedReader reader = null;
        try {

            reader = new BufferedReader(new FileReader(file));
            String tempString = null;
            int line = 1;
            // 一次读入一行，直到读入null为文件结束
            while ((tempString = reader.readLine()) != null) {
                // 显示行号
                result.append(tempString);
                line++;
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e1) {
                }
            }
        }
        return result.toString();
    }

    public static LinkedList<String> getAllFile(String path){
        LinkedList<String> filename = new LinkedList<String>();
        File file = new File(path);

        System.out.println(file.getPath());

        if(file.isDirectory()){
            File[] subFile = file.listFiles();
            for (File temp : subFile){
                filename.addAll(getAllFile(temp.getPath()));
            }

        }
        if(file.isFile()){
            filename.add(file.getPath());
        }
        return filename;
    }
}
