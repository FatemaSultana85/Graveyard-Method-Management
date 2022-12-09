
package Form;

public class UpdateAll {
private int serialNo,ownerId;
private String ownerName;
private int ownerPhoneNumber;
private String ownerNidOrBirthCertificate,ownerAddress,SellingStatus,availablility;
private int permanentCorpseId,permanentGraveId;
private String permanentGraveSize,RelationWithOwner,corpseName,corpseGender,corpseFatherName,corpseMotherName,
        corpseDateOfBirth,corpseDateOfDeath,corpseTimeOfDeath,corpseCauseOfDeath,corpseNidOrBirthCertificate,
        burriedDate,RecycleDate;
private int fee,paymentid;

public UpdateAll(int serialNo,int ownerId,String ownerName,int ownerPhoneNumber,
        String ownerNidOrBirthCertificate,
            String ownerAddress,String SellingStatus,String availablility,int permanentCorpseId,
            int permanentGraveId,String permanentGraveSize,
            String RelationWithOwner,
            String corpseName,String corpseGender,
            String corpseFatherName,String corpseMotherName,
            String corpseDateOfBirth,String corpseDateOfDeath,
            String corpseTimeOfDeath,String corpseCauseOfDeath,
            String corpseNidOrBirthCertificate,
            String burriedDate,String RecycleDate,int fee,int paymentid)
        {
       this.serialNo=serialNo;
       this.ownerId=ownerId;
       this.ownerName=ownerName;
       this.ownerPhoneNumber=ownerPhoneNumber;
       this.ownerNidOrBirthCertificate=ownerNidOrBirthCertificate;
       this.ownerAddress=ownerAddress;
       this.SellingStatus=SellingStatus;
       this.availablility=availablility;
       this.permanentCorpseId=permanentCorpseId;
       this.permanentGraveId=permanentGraveId;
       this.permanentGraveSize=permanentGraveSize;
       this.RelationWithOwner=RelationWithOwner;
       this.corpseName=corpseName;
       this.corpseGender=corpseGender;
       this.corpseFatherName=corpseFatherName;
       this.corpseMotherName=corpseMotherName;
       this.corpseDateOfBirth=corpseDateOfBirth;
       this.corpseDateOfDeath=corpseDateOfDeath;
       this.corpseTimeOfDeath=corpseTimeOfDeath;
       this.corpseCauseOfDeath=corpseCauseOfDeath;
       this.corpseNidOrBirthCertificate=corpseNidOrBirthCertificate;
       this.burriedDate=burriedDate;
       this.RecycleDate=RecycleDate;
       this.fee=fee;
       this.paymentid=paymentid;
       
    }
       public int getserialNo() {return serialNo;}
       public int getownerId() { return ownerId;}
       public String getownerName() {return ownerName;}
       public int getownerPhoneNumber(){ return ownerPhoneNumber;}
       public String getownerNidOrBirthCertificate(){ return ownerNidOrBirthCertificate;}
       public String getownerAddress(){return ownerAddress;}
       public String getSellingStatus(){ return SellingStatus;}
       public String getavailablility(){return availablility;}
       public int getpermanentCorpseId() {return permanentCorpseId;}
       public int getpermanentGraveId() {return permanentGraveId;}
       public String getpermanentGraveSize() { return permanentGraveSize;}
       public String getRelationWithOwner() { return RelationWithOwner;}
       public String getcorpseName(){return corpseName;}
       public String getcorpseGender(){ return corpseGender;}
       public String getcorpseFatherName(){ return corpseFatherName;}
       public String getcorpseMotherName(){ return corpseMotherName;}
       public String getcorpseDateOfBirth(){ return corpseDateOfBirth;}
       public String getcorpseDateOfDeath(){ return corpseDateOfDeath;}
       public String getcorpseTimeOfDeath(){ return corpseTimeOfDeath;}
       public String getcorpseCauseOfDeath(){ return corpseCauseOfDeath;}
       public String getcorpseNidOrBirthCertificate(){ return corpseNidOrBirthCertificate;}
       public String getburriedDate(){ return burriedDate;}
       public String getRecycleDate(){ return RecycleDate;}
       public int getfee(){ return fee;}
       public int getpaymentid(){ return paymentid;}
    
}
