package www.bizpro.com.tw.app.unittest.viewModel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import retrofit2.Response;
import www.bizpro.com.tw.app.unittest.api.ApiService;
import www.bizpro.com.tw.app.unittest.api.IApiCallback;
import www.bizpro.com.tw.app.unittest.dataModel.LoginDataModel;
import www.bizpro.com.tw.app.unittest.response.LoginResponse;

public class LoginViewModel extends ViewModel {
    public MutableLiveData<String> name = new MutableLiveData<>();
    public MutableLiveData<Integer> age = new MutableLiveData<>();
    public MutableLiveData<String> errorMsg = new MutableLiveData<>();
    private LoginDataModel model;
    public LoginViewModel(LoginDataModel dataModel) {
        this.model =dataModel;
    }

    public void viewModelLogin(String account, String password) {
        model.requestLogin(account, password, new IApiCallback<LoginResponse>() {
            @Override
            public void setSuccessResponse(Response<LoginResponse> response) {
                name.postValue(response.body().getName());
                age.postValue(response.body().getAge());
            }
            @Override
            public void setFailResponse(Throwable throwable) {
                errorMsg.postValue(throwable.getMessage());
            }
        });
    }
}
