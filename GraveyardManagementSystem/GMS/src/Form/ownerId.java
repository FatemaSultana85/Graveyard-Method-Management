
package Form;

public class ownerId {
    private int generalGraveId;

    
     public ownerId(int generalGraveId) 
   {
       this.generalGraveId=generalGraveId;
      
   }
      
       public String getgeneralGraveId() {
           String s = Integer.toString(generalGraveId);
           return s;
       }
      
}
