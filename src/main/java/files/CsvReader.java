package files;

import entry.DatesEntry;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class CsvReader {

    private BufferedReader bufferedReader;

    private CsvReader() {
    }

    public CsvReader(String filePath) throws FileNotFoundException {
        bufferedReader = new BufferedReader(new FileReader(filePath));
    }

    public List<DatesEntry> readFile() throws IOException {
        List<DatesEntry> datesEntries = new LinkedList<DatesEntry>();

        while (true){
            String line = bufferedReader.readLine();

            if (line == null)
                break;

            datesEntries.add(new DatesEntry(line));

        }

        return datesEntries;
    }
}
