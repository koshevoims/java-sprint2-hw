public class MonthReportLine {
    public String itemName;
    public boolean isExpense ;
    public int quantity;
    public int unitPrice;


    public MonthReportLine(String itemName, boolean isExpense, int quantity, int unitPrice) {
        this.itemName = itemName;
        this.isExpense = isExpense;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
    }
}
