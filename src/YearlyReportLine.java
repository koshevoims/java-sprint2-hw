public class YearlyReportLine {
    public String month;
    public int amount;
    public boolean is_expense;

    public YearlyReportLine(String month, int amount, boolean is_expense) {
        this.month = month;
        this.amount = amount;
        this.is_expense = is_expense;
    }
}
