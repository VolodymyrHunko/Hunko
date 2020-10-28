package seleniumTesting;


import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.Test;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.Iterator;

public class EXCELmanipulation {
    String base = System.getProperty("user.dir");

    @Test
    public void readFile() throws IOException {

        FileInputStream fis = new FileInputStream(base + "/src/test/resources/Output.xlsx");
        Workbook book = new XSSFWorkbook(fis);
        Sheet sheet = book.getSheetAt(0);

        for (Row nextRow : sheet) {
            Iterator<Cell> cellIterator = nextRow.cellIterator();
            while (cellIterator.hasNext()) {
                Cell cell1 = cellIterator.next();
                String currentCell = String.valueOf(cell1);
                System.out.println(currentCell);
            }
            System.err.println("Row had read.");
        }
    }

    @Test
    public void wrightFile() throws IOException{
        FileInputStream fis = new FileInputStream(base+"/src/test/resources/TestDataEXCEL.xlsx");
        XSSFWorkbook book = new XSSFWorkbook(fis);
        XSSFSheet sheet =book.getSheetAt(0);
        int lastRow = sheet.getLastRowNum();
        System.out.println("Last row: "+lastRow);
        Row row = sheet.createRow(lastRow+1);
        Cell cell = row.createCell(1, CellType.STRING);
        cell.setCellValue("Max "+ new Date());

        FileOutputStream fos = new FileOutputStream(base+"/src/test/resources/TestDataEXCEL.xlsx");
        book.write(fos);
        fos.close();
        System.out.println("Sheet updated");
    }

}

