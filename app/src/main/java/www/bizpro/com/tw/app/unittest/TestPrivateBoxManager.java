package www.bizpro.com.tw.app.unittest;

public class TestPrivateBoxManager {
    PrivateTestBox box;
    TestPrivateBoxManager (PrivateTestBox box){
        this.box = box;
    }
    public String  sayHello(){
        return box.monSay();
    }
}
