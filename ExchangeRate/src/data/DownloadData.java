package data;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by songshaoying on 16/6/8.
 */
public class DownloadData {

    public String connect(String path,String para) throws IOException {
        StringBuffer rep = new StringBuffer();
        URL url = new URL(path);
        HttpURLConnection conn = (HttpURLConnection)url.openConnection();
        conn.setRequestMethod("POST");
        conn.setDoInput(true);
        conn.setDoOutput(true);
        conn.setUseCaches(false);
        conn.setConnectTimeout(5000);
        conn.setReadTimeout(5000);

        conn.connect();

        OutputStreamWriter outputStreamWriter = new OutputStreamWriter(conn.getOutputStream(),"utf-8");
        outputStreamWriter.write(para);
        outputStreamWriter.flush();
        outputStreamWriter.close();

        BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(),"UTF-8"));
        String line = null;
        while ((line = br.readLine()) != null){
            rep.append(line);
        }
        br.close();
        return rep.toString().replaceAll("\\s*","");
    }


    public List<String> parseHtml(String str){
        List<String> result = new LinkedList<String>();


        String regEx_script="<script[^>]*?>[\\s\\S]*?<\\/script>"; //定义script的正则表达式
        String regEx_style="<style[^>]*?>[\\s\\S]*?<\\/style>"; //定义style的正则表达式
        String regEx_html="<[^>]+>"; //定义HTML标签的正则表达式

        String pattern = "(<tableclass=\"list\"id=\"InfoTable\".*>)(.*)(</table>)";

        Pattern r = Pattern.compile(pattern);
        Matcher m = r.matcher(str);
        String table = null;
        while(m.find()){
            table = m.group(1);
        }


        if(table != null){
            table = table.replaceAll(regEx_style,"").replaceAll(regEx_script,"").replaceAll(regEx_html,"");
            //将html代码中的&NBSP;替换成,
            table = table.replaceAll("&nbsp;",",");
            table = table.replaceAll("日期","日期,");
            String[] tableArray = table.split(",");
            //System.out.println(Arrays.toString(tableArray));
            StringBuffer tr = new StringBuffer();
            for(int i = 13; i < tableArray.length ; i++){
                if( (i+1) % 13 == 0){
                    result.add(tr.toString());
                    tr = tr.delete(0,tr.length());
                }
                else{
                    int j = i%13;
                    //System.out.println(i+","+j);
                    if(j == 0 || j == 1 || j == 2 || j == 4) {
                        tr = tr.append(tableArray[i]).append(",");
                        //System.out.println(tableArray[i]);
                    }
                }
            }
        }

        return result;
    }



    public static void main(String[] args) throws IOException {
        DownloadData downloadData = new DownloadData();
        String rep = downloadData.connect("http://www.safe.gov.cn/AppStructured/view/project_RMBQuery.action","&projectBean.startDate=2016-05-08&projectBean.endDate=2016-06-08&queryYN=true");
        downloadData.parseHtml(rep);
    }
}
