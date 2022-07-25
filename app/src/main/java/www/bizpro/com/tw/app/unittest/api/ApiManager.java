package www.bizpro.com.tw.app.unittest.api;

import androidx.annotation.NonNull;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiManager {
    private static ApiService apiService;
    public static ApiService getApiService() {
        if (apiService == null) {
            OkHttpClient client = new OkHttpClient().newBuilder()
                    // .addInterceptor(new ChuckInterceptor(MyApplication.getInstance()))
                    .addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                    .callTimeout(60, TimeUnit.SECONDS)
                    .readTimeout(60, TimeUnit.SECONDS)
                    .addInterceptor(new Interceptor() {

                        @NonNull
                        @Override
                        public Response intercept(@NonNull Chain chain) throws IOException {
                            Request request = chain.request();
                            request.newBuilder().header("keyAuthorization", "Data").build();
                            return chain.proceed(request);
                        }

                    })
                    .build();
            Retrofit retrofit = new Retrofit.Builder() //.baseUrl(BuildConfig.BASE_URL)
                    .baseUrl("http://")
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                    .client(client)
                    .build();
            apiService = retrofit.create(ApiService.class);
        }
        return apiService;
    }

}
