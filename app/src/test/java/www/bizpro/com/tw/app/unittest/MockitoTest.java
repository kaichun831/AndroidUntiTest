package www.bizpro.com.tw.app.unittest;


import static org.mockito.internal.verification.VerificationModeFactory.times;

import android.util.Log;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.internal.verification.Times;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.api.support.membermodification.MemberMatcher;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import io.reactivex.rxjava3.core.Single;
import retrofit2.Response;
import www.bizpro.com.tw.app.unittest.api.ApiService;
import www.bizpro.com.tw.app.unittest.api.IApiCallback;
import www.bizpro.com.tw.app.unittest.dataModel.LoginDataModel;
import www.bizpro.com.tw.app.unittest.response.LoginResponse;
import www.bizpro.com.tw.app.unittest.viewModel.LoginViewModel;

@RunWith(PowerMockRunner.class)
@PrepareForTest({FinalClassTestBox.class, StaticClassTestBox.class, PrivateTestBox.class})
//@RunWith(MockitoJUnitRunner.class)
//
public class MockitoTest {
    @Rule
    RxSchedulerRule rule = new RxSchedulerRule();   //RxJava若有切換Thread,透過此類別進行規範在非UI Thread
    @Rule
    InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule(); //Thread,透過此類別進行規範在非UI Thread

    @Mock
    LoginService service;

    @Mock
    FinalClassTestBox box;


    @Test
    public void testLogin() {
        //實際生成物件
        LoginService service = new LoginService();
        AuthManager authManager = new AuthManager(service);
        boolean loginResult = authManager.login("1256", "3456");
        Assert.assertEquals(false, loginResult);
    }

    @Test
    public void testMockLogin() {
        //模擬生成物件
//        LoginService service = Mockito.mock(LoginService.class);
        AuthManager authManager = new AuthManager(service);
        //設定service.login的強制回傳實際結果
        Mockito.when(service.login("1234", "1234")).thenReturn(true);
        //執行呼叫service.login (此時無論如何result皆為true)
        boolean result = authManager.login("1234", "1234");
        //驗證service.login 是否有被呼叫
        Mockito.verify(service).login(Mockito.anyString(), Mockito.anyString());
        //驗證結果 (預測結果,實際結果)
        Assert.assertEquals(true, result);
    }

    @Test
    public void testMockitoWithFinal() {
        //FinalClassTestBox box = PowerMockito.mock(FinalClassTestBox.class);  //可以使用Annotation
        Mockito.when(box.helloMethod()).thenReturn("Success");
        TestFinalBoxManager boxManager = new TestFinalBoxManager(box);
        String result = boxManager.sayHello();
        Mockito.verify(box).helloMethod();
        Assert.assertEquals("Success", result);
    }

    @Test
    public void testMockitoWithStatic() throws Exception {
        //arrange
        PowerMockito.mockStatic(StaticClassTestBox.class);
        Mockito.when(StaticClassTestBox.sayStaticHello()).thenReturn("OK");
        TestStaticBoxManager manager = new TestStaticBoxManager();
        //act
        String result = manager.sayHello2();
        //assert
        PowerMockito.verifyStatic(StaticClassTestBox.class, times(1)); //驗證跑的類僅執行一次
        StaticClassTestBox.sayStaticHello(); //確認跑的內容是這個函式
        Assert.assertEquals("OK", result);
    }

    @Test

    public void testMockitoWithPrivate() throws Exception {
        //arrange
        PrivateTestBox box = PowerMockito.spy(new PrivateTestBox());
        TestPrivateBoxManager manager = new TestPrivateBoxManager(box);
        PowerMockito.when(box, MemberMatcher.method(PrivateTestBox.class, "getSomething", String.class, int.class))
                .withArguments(Mockito.anyString(), Mockito.anyInt())
                .thenReturn("OK");
        //act
        String result = manager.sayHello();
        //assert
        PowerMockito.verifyPrivate(box).invoke("getSomething", Mockito.anyString(), Mockito.anyInt());
        Assert.assertEquals("OK", result);
    }

    @Test

    public void testMockitoDataModelRxFlowRequestApi() {
        Response<LoginResponse> loginResponseResponse = Response.success(new LoginResponse());
        ApiService service = Mockito.mock(ApiService.class);
        Mockito.when(service.doLogin(Mockito.anyString(), Mockito.anyString()))
                .thenReturn(Single.just(loginResponseResponse));
        LoginDataModel model = new LoginDataModel(service);
        model.requestLogin("", "", new IApiCallback<LoginResponse>() {
            @Override
            public void setSuccessResponse(Response<LoginResponse> response) {
                //do nothing
            }

            @Override
            public void setFailResponse(Throwable throwable) {
                //do nothing
            }
        });
        Mockito.verify(service).doLogin(Mockito.anyString(), Mockito.anyString());

    }

    @Test
    public void testMockitoWithApiCallBack() {
        final  boolean isSuccess = false;
        Exception exception;
        //Arrange
        LoginResponse fakeData =new LoginResponse();
        fakeData.setSuccess(isSuccess);
        if(isSuccess) {
            fakeData.setMessage("");
        }else{
            fakeData.setMessage("Error");
            exception = new Exception();
        }
        fakeData.setAge(20);
        fakeData.setName("KG");

        Response<LoginResponse> loginResponse = Response.success(fakeData);
        LoginDataModel dataModel =Mockito.mock(LoginDataModel.class);
        LoginViewModel viewModel =new LoginViewModel(dataModel);

        Mockito.doAnswer(invocation -> {
            IApiCallback apiCallback =invocation.getArgument(2); //取的when的函式,索引2的Callback介面轉型
            if(isSuccess)
                apiCallback.setSuccessResponse(loginResponse); //設置我們預先使用的假資料
            else
                apiCallback.setFailResponse(new Exception());
            return null;
        }).when(dataModel).requestLogin(Mockito.anyString(),Mockito.anyString(),Mockito.any(IApiCallback.class));

        //Act
        viewModel.viewModelLogin("", ""); //實際執行
        //Assert 驗證我們mock的dataModel
        Mockito.verify(dataModel,new Times(1)).requestLogin(Mockito.anyString(),Mockito.anyString(),Mockito.any(IApiCallback.class));
        if(isSuccess) {
            Assert.assertEquals(viewModel.name.getValue(), fakeData.getName());
        }else{
            Assert.assertEquals(viewModel.errorMsg.getValue(),exception.getMessage());
        }
    }

    @Test
    public void testObservable(){
        ObservableTest test =Mockito.mock(ObservableTest.class);
        test.useObservable();
    }
}
