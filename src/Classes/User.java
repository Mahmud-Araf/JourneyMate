package Classes;

public class User {

    public static String Name,Email,Password; 

    public User()
    {
        User.Name=null;
        User.Email=null;
        User.Password=null;
    }

    public User(String Name, String Email, String Password)
    {
        User.Name=Name;
        User.Email=Email;
        User.Password = Password;
    }

    public static void setInfo(String Name, String Email, String Password)
    {
        User.Name=Name;
        User.Email=Email;
        User.Password = Password;
    }

    public static String getName()
    {
        return Name;
    }

    public static String getEmail()
    {
        return Email;
    }

    public static String getPassword()
    {
       return Password;
    } 

}
