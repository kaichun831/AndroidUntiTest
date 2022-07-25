package www.bizpro.com.tw.app.unittest;

public class TestFinalBoxManager {
    FinalClassTestBox box;
    TestFinalBoxManager(FinalClassTestBox box){
        this.box = box;
    }
    public String sayHello(){
        return  box.helloMethod();
    }
}
