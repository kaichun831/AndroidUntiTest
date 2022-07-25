package www.bizpro.com.tw.app.unittest;

public class StaticClassTestBox {

    static StaticClassTestBox getInstance() {
        return HolderClassStatic.instance;
    }

    private static class HolderClassStatic {
        static StaticClassTestBox instance = new StaticClassTestBox();
    }

    public static String sayStaticHello() {
        return "HHHHHLLLLOOO";
    }
}
