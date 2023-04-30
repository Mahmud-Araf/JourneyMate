package Classes;


//@SuppressWarnings("unused")

public class Client {

   
   private  String Name;
   private  String MobileNumber;
   private  String Address;

   public Client()
   {
      Name=null;
      MobileNumber=null;
      Address = null;
   }

   public Client(String Name,String MobileNumber,String Address)
   {
      this.Name=Name;
      this.MobileNumber=MobileNumber;
      this.Address =Address;
   }

   public   void setName(String name)
   {
      Name = name;
   }

   public  void setMobileNumber(String mobileNumber)
   {
      MobileNumber = mobileNumber;
   }

   public  void setAddress(String address)
   {
      Address = address;
   }

   public  String getName()
   {
      return Name;
   }

   public  String getMobileNumber()
   {
      return MobileNumber;
   }

   public  String getAddress()
   {
      return Address;
   }

}
