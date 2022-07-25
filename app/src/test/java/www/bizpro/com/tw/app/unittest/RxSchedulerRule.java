package www.bizpro.com.tw.app.unittest;

import org.junit.rules.TestWatcher;
import org.junit.runner.Description;

import java.util.concurrent.Callable;

import io.reactivex.rxjava3.android.plugins.RxAndroidPlugins;
import io.reactivex.rxjava3.core.Scheduler;
import io.reactivex.rxjava3.functions.Function;
import io.reactivex.rxjava3.functions.Supplier;
import io.reactivex.rxjava3.internal.schedulers.TrampolineScheduler;
import io.reactivex.rxjava3.plugins.RxJavaPlugins;

class RxSchedulerRule extends TestWatcher {
   @Override
   protected void starting(Description description) {
      super.starting(description);
      RxJavaPlugins.setInitIoSchedulerHandler(new Function<Supplier<Scheduler>, Scheduler>() {
         @Override
         public Scheduler apply(Supplier<Scheduler> schedulerSupplier) throws Throwable {
            return TrampolineScheduler.instance();
         }
      });
      RxJavaPlugins.setInitComputationSchedulerHandler(new Function<Supplier<Scheduler>, Scheduler>() {
         @Override
         public Scheduler apply(Supplier<Scheduler> schedulerSupplier) throws Throwable {
            return TrampolineScheduler.instance();
         }
      });
      RxJavaPlugins.setInitNewThreadSchedulerHandler(new Function<Supplier<Scheduler>, Scheduler>() {
         @Override
         public Scheduler apply(Supplier<Scheduler> schedulerSupplier) throws Throwable {
            return TrampolineScheduler.instance();
         }
      });
      RxJavaPlugins.setIoSchedulerHandler(new Function<Scheduler, Scheduler>() {
         @Override
         public Scheduler apply(Scheduler scheduler) throws Throwable {
            return TrampolineScheduler.instance();
         }
      });
      RxJavaPlugins.setInitSingleSchedulerHandler(new Function<Supplier<Scheduler>, Scheduler>() {
         @Override
         public Scheduler apply(Supplier<Scheduler> schedulerSupplier) throws Throwable {
            return TrampolineScheduler.instance();
         }
      });
      RxAndroidPlugins.setInitMainThreadSchedulerHandler(new Function<Callable<Scheduler>, Scheduler>() {
         @Override
         public Scheduler apply(Callable<Scheduler> schedulerCallable) throws Throwable {
            return TrampolineScheduler.instance();
         }
      });
   }

   @Override
   protected void finished(Description description) {
      super.finished(description);
      RxJavaPlugins.reset();
      RxAndroidPlugins.reset();
   }
}
