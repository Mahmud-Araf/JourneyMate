package Classes;

public class Booking {
    
    private String BookingID;
    private String ClientName;
    private String PersonNumber;
    private String BookedPackage;
    private String TotalPrice;
    private String Paid;
    private String Due;

    public Booking()
    {
        this.BookingID = null;
        this.ClientName = null;
        this.PersonNumber = null;
        this.BookedPackage = null;
        this.TotalPrice = null;
        this.Paid = null;
        this.Due =null;
    }
    
    public Booking(String BookingID, String ClientName, String PersonName, String BookedPackage, String TotalPrice, String Paid, String Due) {
        this.BookingID = BookingID;
        this.ClientName = ClientName;
        this.PersonNumber = PersonName;
        this.BookedPackage = BookedPackage;
        this.TotalPrice = TotalPrice;
        this.Paid = Paid;
        this.Due = Due;
    }

    // Getter methods
    public String getBookingID() {
        return BookingID;
    }

    public String getClientName() {
        return ClientName;
    }

    public String getPersonNumber() {
        return PersonNumber;
    }

    public String getBookedPackage() {
        return BookedPackage;
    }

    public String getTotalPrice() {
        return TotalPrice;
    }

    public String getPaid() {
        return Paid;
    }

    public String getDue() {
        return Due;
    }

    // Setter methods
    public void setBookingID(String BookingID) {
        this.BookingID = BookingID;
    }

    public void setClientName(String ClientName) {
        this.ClientName = ClientName;
    }

    public void setPersonNumber(String PersonName) {
        this.PersonNumber = PersonName;
    }

    public void setBookedPackage(String BookedPackage) {
        this.BookedPackage = BookedPackage;
    }

    public void setTotalPrice(String TotalPrice) {
        this.TotalPrice = TotalPrice;
    }

    public void setPaid(String Paid) {
        this.Paid = Paid;
    }

    public void setDue(String Due) {
        this.Due = Due;
    }
}
