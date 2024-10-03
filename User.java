/*
 * Defines the User object.
 * @author Ezekiel Chow
 *  CIS 22C, Final Project
 */

public class User {

    String name;
    String username;
    String password;
    String textFile;


    public User(){
        name = "unknown name";
        username = "unknown username";
        password = "unknown password";
        textFile = "unknown textfile";
    }

    public User(String name, String username, String password, String textFile){

        this.name = name;
        this.username = username;
        this.password = password;
        this.textFile = textFile;

    }

    public String getName(){
        return name;
    }

    public String getUsername(){
        return username;
    }

    public String getPassword(){
        return password;
    }

    public String getTextFile(){
        return textFile;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setTextFile(String textFile) {
        this.textFile = textFile;
    }


    @Override public String toString(){
        String result = "";
        result += name +"\n" + username + "\n" + password + "\n" + textFile;
        return result;
    }

   /*
    @Override public int hashCode(){
        String key = username+password;
        int hashcode = 0;
        for(int i = 0; i < key.length(); i++){
            hashcode += (int) key.charAt(i);
        }
        return hashcode;
    }
    */
  //  @Override public int compareTo(User otherUser){
  //      if(name)
  //   }




}
