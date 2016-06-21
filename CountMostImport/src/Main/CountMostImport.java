package Main;

import util.FileUtil;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by songshaoying on 16/6/5.
 */
public class CountMostImport {
    List<ImportSave> countList = new ArrayList<ImportSave>();
    HashMap<String,Integer> countBuf = new HashMap<String, Integer>();

    public ImportSave getCountMostImport(String path){
        LinkedList<String> fileList = FileUtil.getAllFile(path);
        String source = "";
        LinkedList<String> importClassList = new LinkedList<String>();
        for (String filepath : fileList) {
            source = FileUtil.readFile(filepath);
            importClassList = getFileImportCount(source);
            for (String importClass : importClassList) {
                if(countBuf.containsKey(importClass))
                    countBuf.put(importClass,countBuf.get(importClass)+1);
                else
                    countBuf.put(importClass,1);

            }
        }

        Iterator<Map.Entry<String, Integer>> entries = countBuf.entrySet().iterator();

        while (entries.hasNext()) {

            Map.Entry<String, Integer> entry = entries.next();

            countList.add(new ImportSave(entry.getKey(),entry.getValue()));
        }

        Collections.sort(countList);
        if(countList.size() > 0)
            return countList.get(countList.size() - 1);
        else
            return null;
    }

    public static LinkedList<String> getFileImportCount(String source){
        String pattern = "(import[\\s])([a-z.A-Z]*)(;)";
        //String pattern = "(import[\\s])(.*)(;)";  //可以匹配java.util.*
        Pattern r = Pattern.compile(pattern);

        Matcher m = r.matcher(source);

        LinkedList<String> result = new LinkedList<String>();

        while (m.find()){
            //System.out.println(m.group(2));
            result.add(m.group(2));
        }

        return result;
    }


    public static void main(String args[]){
        CountMostImport countMostImport =  new CountMostImport();
        ImportSave importSave = countMostImport.getCountMostImport("/Users/songshaoying/IdeaProjects/CountMostImport/src");
        System.out.println(importSave.getClassName()+" appear "+importSave.getCount());
    }
}
