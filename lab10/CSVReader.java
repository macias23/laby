
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
public class CSVReader {
    private List<String[]> rows = new ArrayList<>();
    private String fileName;
    private String separator;
    public CSVReader(String fileName, String separator) {
        this.fileName = fileName;
        this.separator = separator;
    }
    public List<String[]> read() throws IOException{
        try (BufferedReader buffer = new BufferedReader(new
                FileReader(this.fileName))) {
            String extension = this.fileName.substring(this.fileName.lastIndexOf('.')+1,this.fileName.length());
            if(!extension.equals("csv")){
                throw new WrongFileFormatExeption("The file given is not in CSV format");
            }
            String line;
            while ((line = buffer.readLine()) != null) {
                rows.add(line.split(this.separator));
            }
            if(rows.get(0).length!=rows.get(1).length) throw new NoColumnNameException("Some columns are not named.");
            for (String[]checked:rows){
                if(rows.get(0).length!=checked.length) {
                    throw new DifferentNumberOfColumnsException("Row: "+rows.indexOf(checked)+" contains different number of columns");
                }
            }
            return rows;
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<String[]> readWithoutResources() {
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(fileName));
            String line;
            while ((line = br.readLine()) != null) {
                rows.add(line.split(separator));
            }
            return rows;
        } catch (IOException ioe) {
            ioe.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        rows.forEach(row -> {
            sb.append(String.join(":", row));
            sb.append(System.lineSeparator());
        });
        return sb.toString();
    }
    class WrongFileFormatExeption extends IOException{
        WrongFileFormatExeption(String message){
            super(message);
        }
    }
    class NoColumnNameException extends IOException{
        NoColumnNameException(String message){
            super(message);
        }
    }
    class DifferentNumberOfColumnsException extends IOException{
        DifferentNumberOfColumnsException(String message){
            super(message);
        }
    }
    public static void main(String[] args) throws IOException {
        CSVReader csvReader = new CSVReader("lab10/oscar_age_male.csv", ",");
        csvReader.read();
        System.out.println(csvReader);
    }
}

