package www.bizpro.com.tw.app.unittest;

public class LoginService implements ILoginService {
    @Override
    public boolean login(String account, String password) {
        return account.length() >= 6 || password.length() >= 6;
    }
    public boolean hello(){
        return  false;
    }
}
