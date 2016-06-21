package main;

import java.util.List;

/**
 * Created by songshaoying on 16/6/5.
 */
public class EffectiveLines {
    public int[] getLines(List<String> source){
        int[] result = {0,0,0,0};

        result[0] = source.size();

        for (String temp : source){
            temp = temp.trim();
            //注释行数
            if(temp.startsWith("//") || temp.startsWith("/**")
                    || temp.startsWith("*")||temp.startsWith("**/")) {result[2]++;continue;}
            //空行
            if(temp.equals("")){result[3]++;continue;}
            //有效代码
            result[1]++;
        }

        return result;
    }
}
