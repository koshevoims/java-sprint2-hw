public class YearlyReportLine {
    public String month;
    public int amount;
    public boolean isExpense;

    public YearlyReportLine(String month, int amount, boolean isExpense) {
        this.month = month;
        this.amount = amount;
        this.isExpense = isExpense;
    }
}
