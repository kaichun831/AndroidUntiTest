package www.bizpro.com.tw.app.unittest;

public class PrivateTestBox {
    public  String monSay(){
        return  getSomething("HHH",1);
    }
    private String  getSomething(String hello,int count){
        return  hello+count;
    }
}
