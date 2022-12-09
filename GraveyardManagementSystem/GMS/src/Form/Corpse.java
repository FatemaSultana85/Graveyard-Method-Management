
package Form;

class Corpse {
   private int generalGraveId;
   private String generalGraveSize;
   private int generalCorpseId;
   private String corpseType,corpseName,corpseGender,corpseFatherName,
           corpseMotherName,corpseDateOfBirth,corpseDateOfDeath,
           corpseTimeOfDeath,corpseCauseOfDeath,guardianContactNo,
           guardianRelation,guardianAddress,guardianName,corpseNidNo,
           corpseBirthCertificateNo,burriedDate,RecycleDate; 
   public Corpse(int generalGraveId,String generalGraveSize,int generalCorpseId,
           String corpseType,String corpseName,String corpseGender,String corpseFatherName,
           String corpseMotherName,String corpseDateOfBirth,String corpseDateOfDeath,
           String corpseTimeOfDeath,String corpseCauseOfDeath,String guardianContactNo,
           String guardianRelation,String guardianAddress,String guardianName,String corpseNidNo,
           String corpseBirthCertificateNo,String burriedDate,String RecycleDate) 
   {
       this.generalGraveId=generalGraveId;
       this.generalGraveSize=generalGraveSize;
       this.generalCorpseId=generalCorpseId;
       this.corpseType=corpseType;
       this.corpseName=corpseName;
       this.corpseGender=corpseGender;
       this.corpseFatherName=corpseFatherName;
       this.corpseMotherName=corpseMotherName;
       this.corpseDateOfBirth=corpseDateOfBirth;
       this.corpseDateOfDeath=corpseDateOfDeath;
       this.corpseTimeOfDeath=corpseTimeOfDeath;
       this.corpseCauseOfDeath=corpseCauseOfDeath;
       this.guardianContactNo=guardianContactNo;
       this.guardianRelation=guardianRelation;
       this.guardianAddress=guardianAddress;
       this.guardianName=guardianName;
       this.corpseNidNo=corpseNidNo;
       this.corpseBirthCertificateNo=corpseBirthCertificateNo;
       this.burriedDate=burriedDate;
       this.RecycleDate=RecycleDate;
   }
      
       public int getgeneralGraveId() {return generalGraveId;}
       
       public String getgeneralGraveSize() { return generalGraveSize;}
       
       public int getgeneralCorpseId() {return generalCorpseId;}
       public String getcorpseType(){ return corpseType;}
       public String getcorpseName(){return corpseName;}
       public String getcorpseGender(){ return corpseGender;}
       public String getcorpseFatherName(){ return corpseFatherName;}
       public String getcorpseMotherName(){ return corpseMotherName;}
       public String getcorpseDateOfBirth(){ return corpseDateOfBirth;}
       public String getcorpseDateOfDeath(){ return corpseDateOfDeath;}
       public String getcorpseTimeOfDeath(){ return corpseTimeOfDeath;}
       public String getcorpseCauseOfDeath(){ return corpseCauseOfDeath;}
       public String getguardianContactNo(){ return guardianContactNo;}
       public String getguardianRelation(){ return guardianRelation;}
       public String getguardianAddress(){ return guardianAddress;}
       public String getguardianName(){ return guardianName;}
       public String getcorpseNidNo(){ return corpseNidNo;}
       public String getcorpseBirthCertificateNo(){ return corpseBirthCertificateNo;}
       public String getburriedDate(){ return burriedDate;}
       public String getRecycleDate(){ return RecycleDate;}
  
}
