package pl.maniak.mygoals;

import android.app.Application;

import pl.maniak.mygoals.utils.di.AppComponent;
import pl.maniak.mygoals.utils.di.AppModule;
import pl.maniak.mygoals.utils.di.DaggerAppComponent;

public class App extends Application {

    private static App instance = new App();
    private static AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        appComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .build();
    }

    public static App getInstance() {
        return instance;
    }

    public static AppComponent getAppComponent() {
        return appComponent;
    }
}