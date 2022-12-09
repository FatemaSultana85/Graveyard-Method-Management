
package Form;

public class WholeTable {
   int  plotNo;
   String plotSize;
    int corpseId;
 String type,name,gender,father,mother,dob, dod,tod,cod,
         phn,relation,address,relativeName,nid,burried,recylce;
 int fee;
 public WholeTable(int plotNo,String plotSize,int corpseId,
         String type,String name,String gender,String father,String mother,
         String dob,String dod,String tod,String cod,
         String phn,String relation,String address,String relativeName,
         String nid,String burried,String recylce,int fee)
    {
        this.plotNo=plotNo;this.plotSize=plotSize;this.corpseId =corpseId ;this.type= type;this.name =name ;
        this.gender =gender ;this.father = father;this.mother = mother;this.dob = dob;this.dod = dod;
        this.tod= tod; this.cod =cod ;this.phn =phn ;this.relation =relation ;this.address = address;this.relativeName = relativeName;
        this.nid = nid;this.burried = burried;this.recylce = recylce;this.fee = fee;
      
    }
       public int getplotNo() {return plotNo ;}
       public String getplotSize() {return plotSize;}
       public int getcorpseId() {return corpseId;}
       public String gettype() {return type;}
       public String getname() {return name;}
       public String getgender() {return gender;}
       public String getfather() {return father;}
       public String getmother() {return mother;}
       public String getdob() {return dob;}
       public String getdod() {return dod;}
       public String gettod() {return tod;}
       public String getcod() {return cod;}
       public String getphn() {return phn;}
       public String getrelation() {return relation;}
       public String getaddress() {return address;}
       public String getrelativeName() {return relativeName;}
       public String getnid() {return nid;}
       public String getburried() {return burried;}
       public String getrecylce() {return recylce;}
       public int getfee() {return fee;}
       
}
