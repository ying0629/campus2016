package main;

import util.FileUtil;

import java.io.IOException;
import java.util.List;


/**
 * Created by songshaoying on 16/6/5.
 */
public class Main {
    //test
    public static void main(String args[]) throws IOException {
        List<String> source = FileUtil.readFileByLine("/Users/songshaoying/IdeaProjects/EffectiveLines/src/main/Main.java");
        int[] result = (new EffectiveLines()).getLines(source);
        System.out.println("total:"+result[0]);
        System.out.println("effectiveLine:"+result[1]);
        System.out.println("notes:"+result[2]);
        System.out.println("null:"+result[3]);
    }
}
