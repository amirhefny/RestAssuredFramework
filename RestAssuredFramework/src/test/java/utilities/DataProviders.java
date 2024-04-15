package utilities;

import org.testng.annotations.DataProvider;

import java.io.IOException;

public class DataProviders {
    @DataProvider(name = "Data")
    Object [] [] getAllData() throws IOException {
        String path = "E:\\Testing\\New folder (2)\\RestAssuredFramework\\testData\\UsreData.xlsx";
        XLUtility xlUtility = new XLUtility(path);
        int totalRow = xlUtility.getRowCount("Sheet1");
        int totalColumn = xlUtility.getCellCount("Sheet1", 1);
        String apiData[][] = new String[totalRow][totalColumn];
        for (int i = 1; i <= totalRow; i++) {
            for (int j = 0; j < totalColumn; j++) {
                apiData[i - 1][j] = xlUtility.getCellData("Sheet1", i, j);
            }
        }
        return apiData;
    }




    @DataProvider(name = "UserNames")
    public Object [] getUserNames() throws IOException {
        String path = "E:\\Testing\\New folder (2)\\RestAssuredFramework\\testData\\UsreData.xlsx";
        XLUtility xlUtility = new XLUtility(path);
        int totalRow = xlUtility.getRowCount("Sheet1");
        String apiData[] = new String[totalRow];
        for (int i = 1; i <= totalRow; i++) {
            apiData[i - 1] = xlUtility.getCellData("Sheet1", i, 1);
        }
        return apiData;
    }
}