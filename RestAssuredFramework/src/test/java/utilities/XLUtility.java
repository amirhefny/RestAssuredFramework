package utilities;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class XLUtility {
    public static FileInputStream fi;
    public static FileOutputStream fo;
    public static XSSFWorkbook wb;
    public static XSSFSheet ws;
    public static XSSFRow row;
    public static XSSFCell cell;
    public static CellStyle style;
String path = null;

public XLUtility(String path) {
    this.path = path;
}

public int getRowCount(String sheetName) throws IOException {
    fi = new FileInputStream(path);
    wb = new XSSFWorkbook(fi);
    ws = wb.getSheet(sheetName);
    int rowCount = ws.getLastRowNum();
    wb.close();
    fi.close();
    return rowCount;
}
public int getCellCount(String sheetName, int rowNum) throws IOException{
    fi = new FileInputStream(path);
    wb = new XSSFWorkbook(fi);
    ws = wb.getSheet(sheetName);
    row = ws.getRow(rowNum);
    int columnCount = row.getLastCellNum();
    wb.close();
    fi.close();
    return columnCount;
}
public String getCellData(String sheetName, int rowNum, int columnNum) throws IOException{
    fi = new FileInputStream(path);
    wb = new XSSFWorkbook(fi);
    ws = wb.getSheet(sheetName);
    row = ws.getRow(rowNum);
    cell = row.getCell(columnNum);
    DataFormatter formatter = new DataFormatter();
    String data;
    try {
        data = formatter.formatCellValue(cell); // Returns the formatted value of a cell as a String regardless of the class
    } catch (Exception e) {
        data = "";
    }
    wb.close();
    fi.close();
    return data;
}
public void setCellData(String sheetName, int rowNum, int columnNum, String data) throws IOException {
    fi = new FileInputStream(path);
    wb = new XSSFWorkbook(fi);
    ws = wb.getSheet(sheetName);
    row = ws.getRow(rowNum);
    cell = row.createCell(columnNum);
    cell.setCellValue(data);
    fo = new FileOutputStream(path);
    wb.write(fo);
    wb.close();
    fo.close();
    fi.close();
}

public void fillGreenColor(String sheetName, int rowNum, int columnNum) throws IOException {
    fi = new FileInputStream(path);
    wb = new XSSFWorkbook(fi);
    ws = wb.getSheet(sheetName);
    row = ws.getRow(rowNum);
    cell = row.createCell(columnNum);
    style = wb.createCellStyle();
    style.setFillForegroundColor(IndexedColors.GREEN.getIndex());
    style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
    cell.setCellStyle(style);
    wb.write(fo);
    wb.close();
    fo.close();
    fi.close();
}
}
