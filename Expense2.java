import java.util.Comparator;

/*
 *Expense 2 class to Override compare 2 method
 */
public class Expense2 extends Expense {

    public Expense2(String expenseID, String expenseName, String expenseType, String date, String time, double cost){
       super(expenseID,expenseName,expenseType,date,time,cost);

    }

    @Override
    public int compareTo(Expense otherExpense) {
        if (this.equals(otherExpense)) {
            return 0;
        } else if (this.getExpenseName().compareTo(otherExpense.getExpenseName()) < 0) {
            return -1;
        } else if (this.getExpenseName().compareTo(otherExpense.getExpenseName()) > 0) {
            return 1;
        } else {
            if (this.getExpenseID().equals(otherExpense.getExpenseID())) {
                if (this.getExpenseType().compareTo(otherExpense.getExpenseType()) < 0) {
                    return -1;
                } else {
                    return 1;
                }
            }
        }
        return -1;
    }
}
