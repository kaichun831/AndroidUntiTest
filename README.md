# Android Unit Test (Java)


### Tool
- junit (Android Default implementation)
- Mockito
- PowerMock


### dependencies
- testImplementation 'org.mockito:mockito-core:3.1.0'
- testImplementation "androidx.arch.core:core-testing:2.1.0"
- testImplementation "org.powermock:powermock-module-junit4:2.0.7"
- testImplementation "org.powermock:powermock-module-junit4-rule:2.0.7"
- testImplementation "org.powermock:powermock-api-mockito2:2.0.7"
- testImplementation "org.powermock:powermock-classloading-xstream:1.6.6"


### Description
3A
- Arrange (布置)
  - 準備測試環境(建立假資料,建立假物件,模擬回傳結果等等)
- Act (行為)
  - 執行目標函式 (依上方所建立的資料執行函式)
- Assert (斷言)
  - 比較測試結果是否如預期
    
### Other
- RxSchedulerRule.class
    - 用於測試環境將RxJava所切換的Thread避免使用到UIThread
- InstantTaskExecutorRule.class
    - 同理,是本身用到new Thread()
    
### Special Description
- 使用到static、private、final時需要搭配PowerMock Library
