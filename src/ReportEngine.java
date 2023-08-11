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
                if (monthReportLine.is_expense) sumSpend += monthReportLine.quantity*monthReportLine.unit_price;
                else sumProfit += monthReportLine.quantity*monthReportLine.unit_price;
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
                yearReportLineConverted.put(yearLine.is_expense, yearLine.amount);
                yearlyReportConverted.put(yearLine.month, yearReportLineConverted);
            } else {
                HashMap<Boolean, Integer> yearReportLineConverted = new HashMap<>();
                yearReportLineConverted = yearlyReportConverted.get(yearLine.month);
                yearReportLineConverted.put(yearLine.is_expense, yearLine.amount);
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
        //System.out.println ("-----------------------------------------------------------------");
        return check;

        /*

        - Сверить отчёты — по сохранённым данным проверить, сходятся ли отчёты за месяцы и за год.
        1)+ Проверить, что месячные и годовой отчёты были считаны из файлов. В случае если этого не было сделано, нужно предложить сначала считать данные.
        2)+ Подсчитать суммы доходов и расходов по каждому из месяцев.
        3)+ Сверить полученные суммы с суммой доходов и расходов в отчёте по году.
        4)+ При обнаружении несоответствия программа должна вывести месяц, где оно обнаружено.
        5)+ Если несоответствий не обнаружено, приложение должно вывести только информацию об успешном завершении операции. */
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
                if (monthReportsLine.is_expense) {
                    if (monthReportsLine.quantity * monthReportsLine.unit_price > maxSpend) {
                        maxSpendName = monthReportsLine.item_name;
                        maxSpend = monthReportsLine.quantity * monthReportsLine.unit_price;
                    }
                } else {
                    if (monthReportsLine.quantity * monthReportsLine.unit_price > maxProfit) {
                        maxProfitName = monthReportsLine.item_name;
                        maxProfit = monthReportsLine.quantity * monthReportsLine.unit_price;

                    }
                }
            }
            System.out.println("Cамый прибыльный товар: " + maxProfitName + ". Сумма: " + maxProfit);
            System.out.println("Cамая большая трата: " + maxSpendName + ". Сумма: " + maxSpend);
        }
        //System.out.println ("-----------------------------------------------------------------");


       /* - Вывести информацию обо всех месячных отчётах — по сохранённым данным вывести в консоль имеющуюся информацию.
        1)+ название месяца;
        2)+ самый прибыльный товар, название товара и сумму;
        3)+ самую большую трату, название товара и сумму. */


    }
    public void yearlyReportInfo() {
        System.out.println("2021");
        for ( YearlyReportLine yearLine : yearlyReport.yearLines) {
            if (!yearLine.is_expense) System.out.println("Прибыль в " + getMonthName(yearLine.month) + " составила "+ yearLine.amount);
        }


        int sumSpend = 0;
        int sumProfit = 0;
        for ( YearlyReportLine yearLine : yearlyReport.yearLines) {
            if (yearLine.is_expense) sumSpend += yearLine.amount;
            else sumProfit += yearLine.amount;
        }
        double avgSpend = (double)sumSpend/yearlyReport.yearLines.size();
        double avgProfit = (double)sumProfit/yearlyReport.yearLines.size();

        System.out.println("Cредний расход за все имеющиеся операции в году: " + avgSpend);
        System.out.println("Cредний доход за все имеющиеся операции в году: " + avgProfit);

        //System.out.println("-----------------------------------------------------------------");

        /* - Вывести информацию о годовом отчёте — по сохранённым данным вывести в консоль имеющуюся информацию.
        1)+ рассматриваемый год;
        2)+ прибыль по каждому месяцу;
        3)+ средний расход за все имеющиеся операции в году;
        4)+ средний доход за все имеющиеся операции в году.*/
    }


}


