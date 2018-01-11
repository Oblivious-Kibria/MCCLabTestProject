package abohawya.com.mcclabtestproject;

/**
 * Created by USER15 on 1/11/2018.
 */

public class User {

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return Age;
    }

    public void setAge(int age) {
        Age = age;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    private String name;
    private int Age;
    private String Email;
    private String phone;


    public User(String name, int age, String email, String phone) {
        this.name = name;
        Age = age;
        Email = email;
        this.phone = phone;
    }
}
