
package Form;

public class permanent {
private int permanentGraveId ;
private String permanentGraveSize;
private int serialNo;
private int ownerId; 
private String RelationWithOwner,availablility;
private int permanentCorpseId;
private String corpseName,corpseGender,corpseFatherName,corpseMotherName,corpseDateOfBirth,corpseDateOfDeath,corpseTimeOfDeath,corpseCauseOfDeath,corpseNidOrBirthCertificate,burriedDate,RecycleDate;

    public permanent(int permanentGraveId,String permanentGraveSize,int serialNo,
            int ownerId,String RelationWithOwner,String availablility,
            int permanentCorpseId,String corpseName,String corpseGender,
            String corpseFatherName,String corpseMotherName,
            String corpseDateOfBirth,String corpseDateOfDeath,
            String corpseTimeOfDeath,String corpseCauseOfDeath,
            String corpseNidOrBirthCertificate,
            String burriedDate,String RecycleDate)
    {
       this.permanentGraveId=permanentGraveId;
       this.permanentGraveSize=permanentGraveSize;
       this.serialNo=serialNo;
       this.ownerId=ownerId;
       this.RelationWithOwner=RelationWithOwner;
       this.availablility=availablility;
       this.permanentCorpseId=permanentCorpseId;
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
    }
       public int getpermanentGraveId() {return permanentGraveId;}
       public String getpermanentGraveSize() { return permanentGraveSize;}
       public int getserialNo() {return serialNo;}
        public int getownerId() {return ownerId;}
       public String getRelationWithOwner() { return RelationWithOwner;}
       public String getavailablility() {return availablility;}
       public int getpermanentCorpseId() {return permanentCorpseId;}
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
      
}
