package www.bizpro.com.tw.app.unittest.api;

import retrofit2.Response;

public interface IApiCallback<T> {
    void setSuccessResponse(Response<T> response);
    void setFailResponse(Throwable throwable);
}
