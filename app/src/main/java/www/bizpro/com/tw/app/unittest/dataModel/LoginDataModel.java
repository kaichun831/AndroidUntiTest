package www.bizpro.com.tw.app.unittest.dataModel;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.SingleObserver;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;
import retrofit2.Response;
import www.bizpro.com.tw.app.unittest.api.ApiManager;
import www.bizpro.com.tw.app.unittest.api.ApiService;
import www.bizpro.com.tw.app.unittest.api.IApiCallback;
import www.bizpro.com.tw.app.unittest.response.LoginResponse;

public class LoginDataModel {
    public ApiService service;
    public LoginDataModel(ApiService service){
        this.service = service;
    }
    public boolean requestLogin(String account, String password, IApiCallback<LoginResponse> callback) {
        service.doLogin(account, password)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<Response<LoginResponse>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onSuccess(@NonNull Response<LoginResponse> loginResponseResponse) {
                        callback.setSuccessResponse(loginResponseResponse);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        callback.setFailResponse(e);
                    }
                });
        return  true;
    }
}
