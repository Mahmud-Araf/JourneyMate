package Classes;

public class User {

    private String Name,Email,Password;

    public User(String Name, String Email, String Password)
    {
        this.Name=Name;
        this.Email=Email;
        this.Password = Password;
    }

    public void setInfo(String Name, String Email, String Password)
    {
        this.Name=Name;
        this.Email=Email;
        this.Password = Password;
    }

    public User getInfo() {
        return this;
    }

}
