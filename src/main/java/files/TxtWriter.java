package files;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class TxtWriter {

    private String filePath;

    private TxtWriter(){}

    public TxtWriter(String filePath) throws IOException {
        this.filePath = filePath;
    }

    public boolean writeTxtFile(List<String> lines) {
        try(BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(filePath))) {
            for (String line : lines)
                bufferedWriter.write(line + "\n");
        }
        catch (IOException e){
            e.printStackTrace();
            return false;
        }

        return true;
    }

}
