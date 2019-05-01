package seleniumTesting;


import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.Test;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;

public class EXCELmanipulation {

    @Test
    public void readFile() throws IOException {
        FileInputStream fis = new FileInputStream("/home/volodymyr/IdeaProjects/Hunko/src/test/resources/TestDataEXCEL.xlsx");
        XSSFWorkbook book = new XSSFWorkbook(fis);
        XSSFSheet sheet = book.getSheetAt(0);
        Row r = sheet.getRow(0);
        Cell c = r.getCell(1);
        System.out.println(c);
    }

    @Test
    public void wrightFile() throws IOException{
        FileInputStream fis = new FileInputStream("/home/volodymyr/IdeaProjects/Hunko/src/test/resources/TestDataEXCEL.xlsx");
        XSSFWorkbook book = new XSSFWorkbook(fis);
        XSSFSheet sheet =book.getSheetAt(0);
        int lastRow = sheet.getLastRowNum();
        System.out.println("Last row: "+lastRow);
        Row row = sheet.createRow(lastRow+1);
        Cell cell = row.createCell(1, CellType.STRING);
        cell.setCellValue("Max "+ new Date());

        FileOutputStream fos = new FileOutputStream("/home/volodymyr/IdeaProjects/Hunko/src/test/resources/TestDataEXCEL.xlsx");
        book.write(fos);
        fos.close();
        System.out.println("Sheet updated");
    }

}

