
package Form;


public class boughtByOneOwnerPer {
 private int ownerId;
 private String ownerName;
   private int NumberOfPerson;
   public boughtByOneOwnerPer(int ownerId,String ownerName,int NumberOfPerson){
       this.ownerId=ownerId;
       this.ownerName=ownerName;
       this.NumberOfPerson=NumberOfPerson;
   }
     public int getownerId() {return ownerId;}
     public String getownerName() {return ownerName;}
     public int getNumberOfPerson() {return NumberOfPerson;}      
}
