package Classes;


public class TourPackage {
    
     private String PackageName;

     private String District;

     private String SpotName;

     private String SpotPrice;

     private String TotalPrice;

     public TourPackage()
     {
        PackageName=null;
        District=null;
        SpotName=null;
        SpotPrice=null;
        TotalPrice=null;
     }

     public TourPackage(String PackageName,String District, String SpotName, String SpotPrice, String TotalPrice)
     {
        this.PackageName=PackageName;
        this.District = District;
        this.SpotName =SpotName;
        this.SpotPrice = SpotPrice;
        this.TotalPrice = TotalPrice;
     }

     public void setPackageName(String PackageName)
     {
        this.PackageName = PackageName;
     }

     public void setDistrict(String District)
     {
        this.District = District;
     }

     public void setSpotName(String SpotName)
     {
        this.SpotName = SpotName;
     }

     public void setSpotPrice(String SpotPrice)
     {
        this.SpotPrice = SpotPrice;
     }

     public void setTotalPrice()
     {
        TotalPrice="0";
     }

     public String getPackageName()
     {
       return PackageName;
     }

     public String getDistrict()
     {
        return District;
     }

     public String  getSpotName()
     {
        return SpotName;
     }

     public String getSpotPrice()
     {
        return SpotPrice;
     }

     public String getTotalPrice()
     {
        return TotalPrice;
     }
}
