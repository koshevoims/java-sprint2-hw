public class MonthReportLine {
    public String item_name;
    public boolean is_expense ;
    public int quantity;
    public int unit_price;


    public MonthReportLine(String item_name, boolean is_expense, int quantity, int unit_price) {
        this.item_name = item_name;
        this.is_expense = is_expense;
        this.quantity = quantity;
        this.unit_price = unit_price;
    }
}
