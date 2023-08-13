import java.util.ArrayList;


public class YearlyReport {
    public ArrayList<YearlyReportLine> yearLines = new ArrayList<>();
    // Структура для годового отчета
    FileReader fileReader = new FileReader(); //fileName – имя файла в папке resources
    boolean isUsed = false;
    public void loadFile() { // Загружаем все отчеты


        ArrayList<String> lines = fileReader.readFileContents("y.2021.csv");


        for (int j = 1; j < lines.size(); j++) {
            String[] lineContents = lines.get(j).split(",");
            String month = lineContents[0];
            int amount = Integer.parseInt(lineContents[1]);
            boolean isExpense = Boolean.parseBoolean(lineContents[2]);

            YearlyReportLine reportLine;
            reportLine = new YearlyReportLine(month, amount, isExpense);
            yearLines.add(reportLine);

        }
        isUsed = true;
    }
}
