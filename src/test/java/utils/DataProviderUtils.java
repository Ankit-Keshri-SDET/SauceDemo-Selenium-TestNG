package utils;

import org.testng.annotations.DataProvider;

public class DataProviderUtils {
    @DataProvider(name = "loginData")
    public Object[][] loginExcelData() throws Exception {
        ExcelUtils.setExcelFile(ConfigReader.get("loginDetailsPath"), ConfigReader.get("sheetName2"));
        return ExcelUtils.getSheetDataAsArray();
    }

    @DataProvider(name = "checkoutData")
    public Object[][] checkoutExcelData() throws Exception {
        ExcelUtils.setExcelFile(ConfigReader.get("userDetailsSheetPath"), ConfigReader.get("sheetName1"));
        return ExcelUtils.getSheetDataAsArray();
    }
}
