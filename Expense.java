/*
 * Defines Expense.
 * @author Ezekiel Chow
 * CIS 22C, Final Project
 */

public class Expense implements Comparable<Expense> {


   private String expenseID;
   private String expenseName;
   private String expenseType;
   private String date;
   private String time;
   private double cost;

   public Expense(){
       expenseID = "unknown ID";
       expenseName = "unknown name";
       expenseType = "unknown type";
       date = "unknown date";
       time = "unknown time";
       cost = -1;
   }

   public Expense(String expenseID, String expenseName, String expenseType, String date, String time, double cost){
       this.expenseID = expenseID;
       this.expenseName = expenseName;
       this.expenseType = expenseType;
       this.date = date;
       this.time = time;
       this.cost = cost;
   }


   public String getExpenseID(){
       return expenseID;
   }

   public String getExpenseType(){
       return expenseType;
   }

   public String getDate(){
       return date;
   }

   public String getTime(){
       return time;
   }

   public String getExpenseName(){
       return expenseName;
   }

   public double getCost(){
       return cost;
   }

    public void setExpenseID(String expenseID) {
        this.expenseID = expenseID;
    }

    public void setExpenseType(String expenseType) {
        this.expenseType = expenseType;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setExpenseName(String expenseName) {
        this.expenseName = expenseName;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }
    @Override public String toString(){
        String result = "";
        result += "Expense ID: " + expenseID +"\nExpense Name: " +  expenseName +"\nExpense Type: " + expenseType + "\nDate: " + date + "\nTime: " + time + "\nCost: " + cost + "\n";
        return result;
    }

    @Override
    public int compareTo(Expense otherExpense) {
        if (this.equals(otherExpense)) {
            return 0;
        } else if (this.expenseID.compareTo(otherExpense.expenseID) < 0) {
            return -1;
        } else if (this.expenseID.compareTo(otherExpense.expenseID) > 0) {
            return 1;
        } else {
            if (this.expenseID.equals(otherExpense.expenseID)) {
                if (this.expenseType.compareTo(otherExpense.expenseType) < 0) {
                    return -1;
                } else {
                    return 1;
                }
            }
        }
        return -1;
    }



    @Override public int hashCode(){
       String key = expenseID +expenseName;
       int hashcode = 0;
       for(int i = 0; i < key.length(); i++){
           hashcode += (int) key.charAt(i);
       }
       return hashcode;
    }

}


