
package Form;

public class pay {
    private int paymentid,serialNo,ownerId,generalGraveId,fee;
    public pay(int paymentid,int serialNo,int ownerId,int generalGraveId,int fee)
    {
        this.paymentid=paymentid;
        this.serialNo=serialNo;
       this.ownerId=ownerId;
       this.generalGraveId=generalGraveId;
       this.fee=fee;
      
    }
       public int getpaymentid() {return paymentid;}
       public int getserialNo() { return serialNo;}
       public int getownerId() {return ownerId;}
       public int getgeneralGraveId(){ return generalGraveId;}
       public int getfee(){ return fee;}
}
