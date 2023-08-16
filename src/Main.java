import java.util.Scanner;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        YearlyReport yearlyReport = new YearlyReport();
        AllMonthlyReport allMonthlyReport = new AllMonthlyReport();
        ReportEngine reportEngine = new ReportEngine(yearlyReport, allMonthlyReport);

        while (true) {
            printMenu();
            int command = scanner.nextInt();

            if (command == 1) {
                allMonthlyReport.loadAllFile();
                System.out.println("Загрузили");
                System.out.println ("-----------------------------------------------------------------");

            } else if (command == 2) {
                yearlyReport.loadFile();
                System.out.println("Загрузили");
                System.out.println ("-----------------------------------------------------------------");

            } else if (command == 3) {
                if (yearlyReport.isUsed & allMonthlyReport.isUsed) {
                    if (reportEngine.checkReports()) {
                        System.out.println("Сверка успешна");
                    }
                    else {
                        System.out.println("Сверка не успешна");
                    }
                }
                else {
                    System.out.println("Загрузите вначале годовой и месячные отчеты");
                }
                System.out.println ("-----------------------------------------------------------------");

            } else if (command == 4) {
                if (allMonthlyReport.isUsed) {
                    reportEngine.allMonthlyReportInfo();
                }
                else {
                    System.out.println("Не загрузили месячные отчеты");
                }
                System.out.println ("-----------------------------------------------------------------");

            } else if (command == 5) {
                if (yearlyReport.isUsed) {
                    reportEngine.yearlyReportInfo();
                }
                else {
                    System.out.println("Не загрузили годовой отчет");
                }
                System.out.println ("-----------------------------------------------------------------");

            } else if (command == 0) {
                System.out.println("Выход");
                break;
            } else {
                System.out.println("Извините, такой команды пока нет.");
                System.out.println ("-----------------------------------------------------------------");
            }
        }
    }

    public static void printMenu() {
        System.out.println("Что вы хотите сделать? ");
        System.out.println("1 - Считать все месячные отчёты");
        System.out.println("2 - Считать годовой отчёт");
        System.out.println("3 - Сверить отчёты");
        System.out.println("4 - Вывести информацию обо всех месячных отчётах");
        System.out.println("5 - Вывести информацию о годовом отчёте");
        System.out.println("0 - Выход");
    }
}

/*
Консольный интерфейс:
- Считать все месячные отчёты — прочитать данные из файлов месячных отчётов, сохранить их в память программы.
  (Содержимое файлов должно приводиться к объектам приложения для дальнейшей обработки.)
- Считать годовой отчёт — прочитать данные из файла годового отчёта, сохранить их в память программы.
  (Содержимое файлов должно приводиться к объектам приложения для дальнейшей обработки.)
- Сверить отчёты — по сохранённым данным проверить, сходятся ли отчёты за месяцы и за год.
  1) Проверить, что месячные и годовой отчёты были считаны из файлов. В случае если этого не было сделано, нужно предложить сначала считать данные.
  2) Подсчитать суммы доходов и расходов по каждому из месяцев.
  3) Сверить полученные суммы с суммой доходов и расходов в отчёте по году.
  4) При обнаружении несоответствия программа должна вывести месяц, где оно обнаружено.
  5) Если несоответствий не обнаружено, приложение должно вывести только информацию об успешном завершении операции.
- Вывести информацию обо всех месячных отчётах — по сохранённым данным вывести в консоль имеющуюся информацию.
  1) название месяца;
  2) самый прибыльный товар, название товара и сумму;
  3) самую большую трату, название товара и сумму.
- Вывести информацию о годовом отчёте — по сохранённым данным вывести в консоль имеющуюся информацию.
  1) рассматриваемый год;
  2) прибыль по каждому месяцу;
  3) средний расход за все имеющиеся операции в году;
  4) средний доход за все имеющиеся операции в году.
 */