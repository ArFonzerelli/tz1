import entry.DatesEntry;
import files.CsvReader;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

public class DatesEntryTest {

    private static String testDataFilePath = "C:\\dates.csv";

    private static CsvReader csvReader;

    private static List<DatesEntry> testData;

    @BeforeClass
    public static void getTestData(){
        try {
            csvReader = new CsvReader(testDataFilePath);
            testData = csvReader.readFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Test
    public void getBusinessHoursSholdReturnCorrectValueWithFirstLine(){
        int correctValue = 0;
        Assert.assertEquals(correctValue, testData.get(0).getBusinessHours());
    }

    @Test
    public void getBusinessHoursSholdReturnCorrectValueWithSecondLine(){
        int correctValue = 166;
        Assert.assertEquals(correctValue, testData.get(1).getBusinessHours());
    }

    @Test
    public void getBusinessHoursSholdReturnCorrectValueWithThirdtLine(){
        int correctValue = 115;
        Assert.assertEquals(correctValue, testData.get(2).getBusinessHours());
    }

    @Test
    public void getBusinessHoursSholdReturnCorrectValueWithFourthtLine(){
        int correctValue = 134;
        Assert.assertEquals(correctValue, testData.get(3).getBusinessHours());
    }

    @Test
    public void getBusinessHoursSholdReturnCorrectValueWithFifthLine(){
        int correctValue = 142;
        Assert.assertEquals(correctValue, testData.get(4).getBusinessHours());
    }

}
