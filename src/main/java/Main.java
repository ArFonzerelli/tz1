import entry.DatesEntry;
import files.CsvReader;
import files.TxtWriter;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;

public class Main {

    private static String [] fileNames = new String[2];

    public static void main(String[] args) throws IOException {
        getFileNames();

        CsvReader csvReader = null;

        try {
            if (fileNames[0] != null)
                csvReader = new CsvReader(fileNames[0]);
        } catch (FileNotFoundException e) {
            System.out.println("Файл не найден");
            return;
        }

        List<DatesEntry> datesEntries = null;
        datesEntries = csvReader.readFile();

        List<String> resultList = new LinkedList<>();

        for (DatesEntry datesEntry : datesEntries)
            resultList.add("For dates from " + datesEntry.getFirstDateTime() + " till " + datesEntry.getSecondDateTime() + " result is " + datesEntry.getBusinessHours());

        for (DatesEntry datesEntry : datesEntries)
            System.out.println("For dates from " + datesEntry.getFirstDateTime() + " till " + datesEntry.getSecondDateTime() + " result of total business hours is " + datesEntry.getBusinessHours());

        TxtWriter txtWriter;

        if (fileNames[1] != null) {
            txtWriter = new TxtWriter(fileNames[1]);
            txtWriter.writeTxtFile(resultList);
        }
        else {
            System.out.println("Неверный путь файла для вывода результатов");
            return;
        }

        boolean result = txtWriter.writeTxtFile(resultList);

        if (result)
            System.out.println("Файл с результатами расчетов успешно записан");
        else
            System.out.println("Возникли проблемы с записью файла");


    }

    private static void getFileNames() throws IOException {
        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in))){
            System.out.println("Введите путь к исходному файлу c расширением .csv (Например С:\\file.csv)");

            fileNames[0] = bufferedReader.readLine();

            System.out.println("Введите путь к файлу, куда будут сохранены результаты (Например C:\\result.txt)");

            fileNames[1] = bufferedReader.readLine();
        }
    }

}
