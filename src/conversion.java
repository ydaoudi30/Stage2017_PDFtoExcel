import java.util.List;
import  java.io.*;
import  org.apache.poi.hssf.usermodel.HSSFSheet;
import  org.apache.poi.hssf.usermodel.HSSFWorkbook;
import  org.apache.poi.hssf.usermodel.HSSFRow;
import  org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.*;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Locale;



public class conversion {


    HSSFWorkbook workbook = new HSSFWorkbook();
    HSSFSheet sheet = workbook.createSheet("FirstSheet");
    HSSFRow rowhead = sheet.createRow(0);
    CellStyle stylewraptext = workbook.createCellStyle();



    public conversion(String path, List<String> nom , List<String> tel, List<String> abo, List<String> conso, List<String> stabo , List<String> stconso, List<String> total) throws IOException{
        try{
            String filename = path+"\\facture.xls"; ;

            rowhead.createCell(0).setCellValue("Nom");
            rowhead.createCell(1).setCellValue("Telephone");
            rowhead.createCell(2).setCellValue("Abonnements");
            rowhead.createCell(3).setCellValue("Sous-Total");
            rowhead.createCell(4).setCellValue("Consommation");
            rowhead.createCell(5).setCellValue("Sous-Total");
            rowhead.createCell(6).setCellValue("Total");

            sheet.autoSizeColumn(0);
            sheet.autoSizeColumn(1);
            sheet.autoSizeColumn(2);
            sheet.autoSizeColumn(3);
            sheet.autoSizeColumn(4);
            sheet.autoSizeColumn(5);
            sheet.autoSizeColumn(6);


            for (int i=0;i<nom.size();i++){
            HSSFRow row = sheet.createRow(i+1);
            stylewraptext.setWrapText(true);
            stylewraptext.setAlignment(HSSFCellStyle.ALIGN_LEFT);

                Cell cell1 = row.createCell(0);
                cell1.setCellStyle(stylewraptext);
                cell1.setCellValue(nom.get(i));

                Cell cell2 = row.createCell(1);
                cell2.setCellStyle(stylewraptext);
                cell2.setCellValue(tel.get(i));

                Cell cell3 = row.createCell(2);
                CellStyle style3 = workbook.createCellStyle();
                style3.setWrapText(true);
                cell3.setCellStyle(stylewraptext);
                cell3.setCellValue(abo.get(i));

                Cell cell4 = row.createCell(3);
                CellStyle style4 = workbook.createCellStyle();
                style4.setWrapText(true);
                cell4.setCellStyle(stylewraptext);
                cell4.setCellValue(stabo.get(i));

                Cell cell5 = row.createCell(4);
                CellStyle style5 = workbook.createCellStyle();
                style5.setWrapText(true);
                cell5.setCellStyle(stylewraptext);
                cell5.setCellValue(conso.get(i));

                Cell cell6 = row.createCell(5);
                CellStyle style6 = workbook.createCellStyle();
                style6.setWrapText(true);
                cell6.setCellStyle(stylewraptext);
                cell6.setCellValue(stconso.get(i));

                Cell cell7 = row.createCell(6);
                CellStyle style7 = workbook.createCellStyle();
                style7.setWrapText(true);
                cell7.setCellStyle(stylewraptext);
                cell7.setCellValue(total.get(i));

                sheet.autoSizeColumn(0);
                sheet.autoSizeColumn(1);
                sheet.autoSizeColumn(2);
                sheet.autoSizeColumn(3);
                sheet.autoSizeColumn(4);
                sheet.autoSizeColumn(5);
                sheet.autoSizeColumn(6);

        }

            FileOutputStream fileOut = new FileOutputStream(filename);
        workbook.write(fileOut);
        fileOut.close();

        } catch(IOException e) {
            e.printStackTrace();
        }

    }


}
