package coreJava.filesJob;



import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.Test;

import java.io.*;

import java.util.Iterator;


public class Assignment {
    //Specify the path of the file
    String base = System.getProperty("user.dir");
    XSSFSheet sheet;
    int record =0;

    void readFile() {
        try {
            //read the .xlsx file
            FileInputStream fis = new FileInputStream(new File(base+"/src/test/resources/Output.xlsx"));
            //Create Workbook instance
            XSSFWorkbook workbook = new XSSFWorkbook(fis);
            System.out.println("WorkBook has sheets: "+workbook.getNumberOfSheets());
            //get First sheet
            sheet = workbook.getSheetAt(0);
            System.out.println("Sheet name is: "+ sheet.getSheetName());
            for(Row sh : sheet){
                record++;
            }
            System.out.println("Total rows: "+ record);
            //close file
            workbook.close();
            fis.close();
        } catch (FileNotFoundException fnfe) {
            System.out.println("The specified file not found...");
            fnfe.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    void test_4 () throws Exception{
        readFile();
        //iterate through all rows start from 1
        for(int r=1; r<(record); r++) {
            double capacity=0;
            double truckType=0;
            double onwardWeight=0;
            double returnWeight=0;
            String enroutePoint="";
            Row row = sheet.getRow(r);
            //iterate through all columns
            for (int i = 0; i < 14; i++) {
                switch (i) {
                    case 2:
                        capacity = row.getCell(2).getNumericCellValue();
                        break;
                    case 3:
                        truckType = row.getCell(3).getNumericCellValue();
                        break;
                    case 4:
                        onwardWeight = row.getCell(4).getNumericCellValue();
                        break;
                    case 5:
                        returnWeight = row.getCell(5).getNumericCellValue();
                        break;
                    case 6:
                        enroutePoint = row.getCell(6).getStringCellValue();
                        enroutePoint = enroutePoint.replace("[", "");
                        enroutePoint = enroutePoint.replace("]", "");
                        break;
                    default:
                        break;
                }
            }
            // validate condition by requirements
            if (truckType == 1.0 && onwardWeight == 0.0 && enroutePoint.equals("")) {
                double expectedReturn=capacity*0.2;
                if(returnWeight>=expectedReturn) {
                    System.out.println("Record #"+r+" has failed. Expected return weight less than: "+expectedReturn+", actual: "+returnWeight);
                }
            }
        }
    }
}

