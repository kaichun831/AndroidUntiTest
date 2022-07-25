package www.bizpro.com.tw.app.unittest;

import android.util.Log;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableEmitter;
import io.reactivex.rxjava3.core.ObservableOnSubscribe;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;

class ObservableTest {

   public void useObservable() {
      //簡單使用
/*      Observable
              .just(1, 2, 3)
              .subscribe(new Observer<Integer>() {
                 @Override
                 public void onSubscribe(@NonNull Disposable d) {
                    Log.d("KAI", "Disposable");
                 }

                 @Override
                 public void onNext(@NonNull Integer s) {
                    Log.d("KAI", s.toString());
                 }

                 @Override
                 public void onError(@NonNull Throwable e) {
                    Log.d("KAI", "ERROR");
                 }

                 @Override
                 public void onComplete() {
                    Log.d("KAI", "Complete");
                 }
              });*/

      /*Observable.create(new ObservableOnSubscribe<Integer>() {
         @Override
         public void subscribe(@NonNull ObservableEmitter<Integer> emitter) throws Throwable {
            try {
               emitter.onNext(1);
               emitter.onNext(2);
               emitter.onNext(3);
            } catch (Exception e) {
               emitter.onError(e);
            }
            emitter.onComplete();
         }
      }).subscribe(new Observer<Integer>() {
          @Override
          public void onSubscribe(@NonNull Disposable d) {
              Log.d("Kai","Start");
          }

          @Override
          public void onNext(@NonNull Integer integer) {
              Log.d("Kai",integer.toString());
          }

          @Override
          public void onError(@NonNull Throwable e) {
              Log.d("Kai","Error");
          }

          @Override
          public void onComplete() {
              Log.d("Kai","Done");
          }
      });*/

      //制定靜態使用lamda
//        Observable.create(emitter ->{
//            try {
//                Log.d("KAI","1222");
//                emitter.onNext(1);
//                emitter.onNext(2);
//                emitter.onNext(3);
//            }catch (Exception e){
//                emitter.onError(e);
//            }
//            emitter.onComplete();
//        } ).subscribe();


   }

    public void useLamdaObservalbe() {

      Observable ob = Observable.just(1, 2);

//        ob.subscribe(com ->{
//            Log.d("KAI",com.toString());
//        });
      ob.subscribe(next -> {
         Log.d("KAI", next.toString());
      }, error -> {
      }, () -> {
         Log.d("KAI", "onComplete");
      });
   }

    public void userObserver() {
      /*
       * Consumer
       * Action
       * Subscrible
       * */

      //基礎
      Observer observer = new Observer<Integer>() {
         @Override
         public void onSubscribe(@NonNull Disposable d) {

         }

         @Override
         public void onNext(@NonNull Integer o) {
            Log.d("KAI", o.toString());
         }

         @Override
         public void onError(@NonNull Throwable e) {

         }

         @Override
         public void onComplete() {

         }
      };

      observer.onNext(1);
      observer.onNext(123);


   }

}
