package www.bizpro.com.tw.app.unittest.api;

import io.reactivex.rxjava3.core.Single;
import retrofit2.Response;
import retrofit2.http.POST;
import www.bizpro.com.tw.app.unittest.response.LoginResponse;

public interface ApiService {
    @POST("webapi/login")
    Single<Response<LoginResponse>> doLogin(String account,String password);
}
