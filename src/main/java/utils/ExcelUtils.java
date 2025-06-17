package utils;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.FileInputStream;

public class ExcelUtils {
    private static Sheet sheet;

    public static void setExcelFile(String path, String sheetName) throws Exception {
        FileInputStream fis = new FileInputStream(path);
        Workbook workbook = new XSSFWorkbook(fis);
        sheet = workbook.getSheet(sheetName);
    }

    public static String getCellData(int rowNum, int colNum) {
        Cell cell = sheet.getRow(rowNum).getCell(colNum);
        return cell.toString();
    }

    public static int getRowCount() {
        return sheet.getPhysicalNumberOfRows();
    }

    public static Object[][] getSheetDataAsArray() {
        int rowCount = sheet.getPhysicalNumberOfRows();
        int colCount = sheet.getRow(0).getPhysicalNumberOfCells();

        Object[][] data = new Object[rowCount - 1][colCount];

        for (int i = 1; i < rowCount; i++) {
            Row row = sheet.getRow(i);
            for (int j = 0; j < colCount; j++) {
                if (row == null) {
                    data[i - 1][j] = "";
                    continue;
                }
                Cell cell = row.getCell(j);
                data[i - 1][j] = (cell != null) ? cell.toString() : "";
            }
        }

        return data;
    }
}
