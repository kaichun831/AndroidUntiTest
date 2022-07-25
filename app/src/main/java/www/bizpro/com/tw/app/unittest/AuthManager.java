package www.bizpro.com.tw.app.unittest;

import org.mockito.junit.MockitoJUnitRunner;


public class AuthManager {
    private  LoginService service;
    AuthManager (LoginService service){
        this.service = service;
    }
    public boolean login(String account,String password){
        return  service.login(account,password);
    }
}
