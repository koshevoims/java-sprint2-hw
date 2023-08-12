import java.util.ArrayList;
import java.util.HashMap;

public class AllMonthlyReport {
    public ArrayList<MonthReportLine> monthReportsLines; ; //Структура для разбивки строк месячного отчета
    public HashMap<String, ArrayList<MonthReportLine>> allMonthReport = new HashMap<>(); // Структура для всех месячных отчетов
    FileReader fileReader = new FileReader(); //fileName – имя файла в папке resources
    boolean isUsed = false;
    public void loadAllFile(){ // Загружаем все отчеты


        for (int i = 1; i < 4; i++) {
            String fileName = "m.20210" + i + ".csv";

            ArrayList<String> lines = fileReader.readFileContents(fileName);

            monthReportsLines  = new ArrayList<>();
            for (int j = 1; j < lines.size(); j++) {
                String[] lineContents = lines.get(j).split(",");
                String item_name = lineContents[0];
                boolean isExpense = Boolean.parseBoolean(lineContents[1]);
                int quantity = Integer.parseInt(lineContents[2]);
                int unitPrice = Integer.parseInt(lineContents[3]);

                MonthReportLine reportLine = new MonthReportLine(item_name, isExpense, quantity, unitPrice);
                monthReportsLines.add(reportLine);

            }
            allMonthReport.put(("0"+ i), monthReportsLines);

        }
        isUsed = true;

    }
}
