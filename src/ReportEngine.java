import java.util.ArrayList;
import java.util.HashMap;

public class ReportEngine {
    public YearlyReport yearlyReport;
    public AllMonthlyReport allMonthlyReport;

    public ReportEngine(YearlyReport yearlyReport, AllMonthlyReport allMonthlyReport) {
        this.yearlyReport = yearlyReport;
        this.allMonthlyReport = allMonthlyReport;
    }

    public String getMonthName(String monthNumber) {
        HashMap<String, String> month = new HashMap<>();
        month.put("01", "Январе");
        month.put("02", "Феврале");
        month.put("03", "Марте");

        return month.get(monthNumber);
    }

    public boolean checkReports() {
         boolean check = true;


        // Convert allMonthlyReport to allMonthlyReportConverted // Приводим к одинакому виду для сравнения
        HashMap<String, HashMap<Boolean, Integer>> allMonthlyReportConverted = new HashMap<>(); // <month: <profit||spend: sum>>


        for (String month : allMonthlyReport.allMonthReport.keySet()){
            HashMap<Boolean, Integer> MonthlyReportConverted = new HashMap<>();
            ArrayList<MonthReportLine> MonthReportLines = new ArrayList<>();
            MonthReportLines = allMonthlyReport.allMonthReport.get(month);
            int sumSpend = 0;
            int sumProfit = 0;
            for (MonthReportLine monthReportLine : MonthReportLines) {
                if (monthReportLine.isExpense) sumSpend += monthReportLine.quantity*monthReportLine.unitPrice;
                else sumProfit += monthReportLine.quantity*monthReportLine.unitPrice;
            }
            MonthlyReportConverted.put(true, sumSpend);
            MonthlyReportConverted.put(false, sumProfit);
            allMonthlyReportConverted.put(month, MonthlyReportConverted);

        }

        // Convert yearlyReport to yearlyReportConverted // Приводим к одинакому виду для сравнения
        HashMap<String, HashMap<Boolean, Integer>> yearlyReportConverted = new HashMap<>(); // <month: <profit||spend: sum>>

        for ( YearlyReportLine yearLine : yearlyReport.yearLines) {
            if (!yearlyReportConverted.containsKey(yearLine.month)) {
                yearlyReportConverted.put(yearLine.month, new HashMap<>());
                HashMap<Boolean, Integer> yearReportLineConverted = new HashMap<>();
                yearReportLineConverted.put(yearLine.isExpense, yearLine.amount);
                yearlyReportConverted.put(yearLine.month, yearReportLineConverted);
            } else {
                HashMap<Boolean, Integer> yearReportLineConverted = new HashMap<>();
                yearReportLineConverted = yearlyReportConverted.get(yearLine.month);
                yearReportLineConverted.put(yearLine.isExpense, yearLine.amount);
                yearlyReportConverted.put(yearLine.month, yearReportLineConverted);
            }
        }

        // Check allMonthlyReportConverted and yearlyReportConverted // Сравниваем отчеты
        for (String month : allMonthlyReportConverted.keySet()) {
            HashMap<Boolean, Integer> allMonthlyLine = allMonthlyReportConverted.get(month);
            HashMap<Boolean, Integer> yearlyReportLine = yearlyReportConverted.get(month);
            if (!allMonthlyLine.get(true).equals(yearlyReportLine.get(true)) || !allMonthlyLine.get(false).equals(yearlyReportLine.get(false))) {
                System.out.println("Обнаружении несоответствия в  " + getMonthName(month));
                check = false;
            }
        }

        return check;


    }


    public void allMonthlyReportInfo() {
        for (String month : allMonthlyReport.allMonthReport.keySet()){
            System.out.println("В " + getMonthName(month));

            String maxSpendName = "";
            String maxProfitName = "";
            int maxSpend = 0;
            int maxProfit = 0;

            ArrayList<MonthReportLine> monthReportsLines =  allMonthlyReport.allMonthReport.get(month);
            for (MonthReportLine monthReportsLine : monthReportsLines) {
                if (monthReportsLine.isExpense) {
                    if (monthReportsLine.quantity * monthReportsLine.unitPrice > maxSpend) {
                        maxSpendName = monthReportsLine.item_name;
                        maxSpend = monthReportsLine.quantity * monthReportsLine.unitPrice;
                    }
                } else {
                    if (monthReportsLine.quantity * monthReportsLine.unitPrice > maxProfit) {
                        maxProfitName = monthReportsLine.item_name;
                        maxProfit = monthReportsLine.quantity * monthReportsLine.unitPrice;

                    }
                }
            }
            System.out.println("Cамый прибыльный товар: " + maxProfitName + ". Сумма: " + maxProfit);
            System.out.println("Cамая большая трата: " + maxSpendName + ". Сумма: " + maxSpend);
        }

    }
    public void yearlyReportInfo() {
        System.out.println("2021");
        for ( YearlyReportLine yearLine : yearlyReport.yearLines) {
            if (!yearLine.isExpense) System.out.println("Прибыль в " + getMonthName(yearLine.month) + " составила "+ yearLine.amount);
        }


        int sumSpend = 0;
        int sumProfit = 0;
        for ( YearlyReportLine yearLine : yearlyReport.yearLines) {
            if (yearLine.isExpense) sumSpend += yearLine.amount;
            else sumProfit += yearLine.amount;
        }
        double avgSpend = (double)sumSpend/yearlyReport.yearLines.size();
        double avgProfit = (double)sumProfit/yearlyReport.yearLines.size();

        System.out.println("Cредний расход за все имеющиеся операции в году: " + avgSpend);
        System.out.println("Cредний доход за все имеющиеся операции в году: " + avgProfit);


    }


}


