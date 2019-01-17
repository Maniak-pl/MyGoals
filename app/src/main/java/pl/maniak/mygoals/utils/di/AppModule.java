package pl.maniak.mygoals.utils.di;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import lombok.RequiredArgsConstructor;
import pl.maniak.mygoals.App;
import pl.maniak.mygoals.repository.DBHelper;

@Module
@RequiredArgsConstructor
public class AppModule {

    private final App app;

    @Provides
    @Singleton
    Context provideAppContext() {
        return app;
    }

    @Singleton
    @Provides
    public DBHelper getDBHelper() {
        return new DBHelper(app);
    }
}