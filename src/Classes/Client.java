package Classes;


@SuppressWarnings("unused")

public class Client {

   
   private String Name;
   private String Address;
   private String MobileNumber;
   public static int ClientsNumber;

   public Client(String Name,String MobileNumber,String Address) {
      this.Name = Name;
      this.Address = Address;
      this.MobileNumber = MobileNumber;
   }
   
   public void set(String Name,String MobileNumber,String Address) {
      this.Name = Name;
      this.Address = Address;
      this.MobileNumber = MobileNumber;
   }

   public void setName(String Name)
   {
      this.Name = Name;
   }

   public void setMobileNumber(String MobileNumber)
   {
      this.MobileNumber = MobileNumber;
   }

   public void setAddress(String Address)
   {
      this.Address = Address;
   }

   public Client getInfo()
   {
      return this;
   }
}
