
package Form;

public class Owner {
    private int serialNo,ownerId;
    private String ownerName;
    private int ownerPhoneNumber;
    private String ownerNidOrBirthCertificate,ownerAddress,sellingStatus;
    private int generalGraveId;
    private String availablility;
    public Owner(int serialNo,int ownerId,String ownerName,int ownerPhoneNumber,String ownerNidOrBirthCertificate,
            String ownerAddress,String sellingStatus,int generalGraveId,String availablility)
    {
       this.serialNo=serialNo;
       this.ownerId=ownerId;
       this.ownerName=ownerName;
       this.ownerPhoneNumber=ownerPhoneNumber;
       this.ownerNidOrBirthCertificate=ownerNidOrBirthCertificate;
       this.ownerAddress=ownerAddress;
       this.sellingStatus=sellingStatus;
       this.generalGraveId=generalGraveId;
       this.availablility=availablility;
    }
       public int getserialNo() {return serialNo;}
       public int getownerId() { return ownerId;}
       public String getownerName() {return ownerName;}
       public int getownerPhoneNumber(){ return ownerPhoneNumber;}
       public String getownerNidOrBirthCertificate(){ return ownerNidOrBirthCertificate;}
       public String getownerAddress(){return ownerAddress;}
       public String getsellingStatus(){ return sellingStatus;}
       public int getgeneralGraveId(){ return generalGraveId;}
       public String getavailablility(){return availablility;}
    
}
