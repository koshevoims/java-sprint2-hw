public class MonthReportLine {
    public String item_name;
    public boolean isExpense ;
    public int quantity;
    public int unitPrice;


    public MonthReportLine(String item_name, boolean is_expense, int quantity, int unitPrice) {
        this.item_name = item_name;
        this.isExpense = isExpense;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
    }
}
