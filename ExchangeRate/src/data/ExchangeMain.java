package data;

import com.jniwrapper.win32.jexcel.Workbook;
import entity.ExchangeData;
import entity.ExchangeDataKind;
import org.apache.poi.hssf.usermodel.*;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.jniwrapper.win32.jexcel.ui.JWorkbook.i;

/**
 * Created by songshaoying on 16/6/8.
 */
public class ExchangeMain {
    public List<ExchangeData> computeRate(List<String> table) {
        List<ExchangeData> result  = new ArrayList<ExchangeData>();



        for(String tr : table){
            ExchangeData dollar = new ExchangeData("", ExchangeDataKind.DOLLAR,0,0f) ,
                    euro = new ExchangeData("", ExchangeDataKind.EURO,0,0f),
                    hk = new ExchangeData("", ExchangeDataKind.HK,0,0f);
            String[] td = tr.split(",");
            dollar.setDate(td[0]);
            dollar.setModelPrice(Float.parseFloat(td[1]));
            dollar.setRate(Float.parseFloat(td[1])/100);
            result.add(dollar);

            euro.setDate(td[0]);
            euro.setModelPrice(Float.parseFloat(td[2]));
            euro.setRate(Float.parseFloat(td[2])/100);
            result.add(euro);

            hk.setDate(td[0]);
            hk.setModelPrice(Float.parseFloat(td[3]));
            hk.setRate(Float.parseFloat(td[3])/100);
            result.add(hk);
        }
        return result;
    }

    public void outPutInExcel(List<ExchangeData> list,String filename){
        // 第一步，创建一个webbook，对应一个Excel文件
        HSSFWorkbook wb = new HSSFWorkbook();
        // 第二步，在webbook中添加一个sheet,对应Excel文件中的sheet
        HSSFSheet sheet = wb.createSheet("汇率");
        // 第三步，在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制short
        HSSFRow row = sheet.createRow((int) 0);
        // 第四步，创建单元格，并设置值表头 设置表头居中
        HSSFCellStyle style = wb.createCellStyle();
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 创建一个居中格式

        HSSFCell cell = row.createCell((short) 0);
        cell.setCellValue("日期");
        cell.setCellStyle(style);
        cell = row.createCell((short) 1);
        cell.setCellValue("美元");
        cell.setCellStyle(style);
        cell = row.createCell((short) 2);
        cell.setCellValue("欧元");
        cell.setCellStyle(style);
        cell = row.createCell((short) 3);
        cell.setCellValue("港币");
        cell.setCellStyle(style);
        for(int i = 0; i < list.size(); i += 3){
            //System.out.print(i);
            row = sheet.createRow((int) i + 1);
            ExchangeData ed = list.get(i);
            // 第四步，创建单元格，并设置值
            row.createCell((short) 0).setCellValue(ed.getDate());
            row.createCell((short) 1).setCellValue(ed.getRate());
            ed = list.get(i+1);
            row.createCell((short)2).setCellValue(ed.getRate());
            ed= list.get(i+2);
            row.createCell((short)3).setCellValue(ed.getRate());
        }

        // 第六步，将文件存到指定位置
        try
        {
            FileOutputStream fout = new FileOutputStream(filename);
            wb.write(fout);
            fout.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public static void main(String args[]) throws IOException {
        DownloadData downloadData = new DownloadData();
        String rep = downloadData.connect("http://www.safe.gov.cn/AppStructured/view/project_RMBQuery.action","&projectBean.startDate=2016-05-17&projectBean.endDate=2016-06-17&queryYN=true");
        List<String> table = downloadData.parseHtml(rep);
        //System.out.println(table.size());
        ExchangeMain exchangeMain = new ExchangeMain();
        List<ExchangeData> exchangeDataList = exchangeMain.computeRate(table);
        //for(ExchangeData e : exchangeDataList){
        //    System.out.println(e.getDate()+","+e.getRate()+","+exchangeDataList.size());
        //}
        String filename = "/Users/songshaoying/Desktop/rate.xls";
        exchangeMain.outPutInExcel(exchangeDataList,filename);
    }
}
