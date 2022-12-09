/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Form;

public class AvailableGraves {
    private int generalGraveId;
    private String recycleDate;
    
     public AvailableGraves(int generalGraveId,String recycleDate) 
   {
       this.generalGraveId=generalGraveId;
       this.recycleDate=recycleDate;
   }
      
       public String getgeneralGraveId() {
           String s = Integer.toString(generalGraveId);
           return s;
       }
       public String getburriedDate(){ return recycleDate;}
       
}
