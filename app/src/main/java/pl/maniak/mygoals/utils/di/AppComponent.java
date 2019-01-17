package pl.maniak.mygoals.utils.di;

import javax.inject.Singleton;

import dagger.Component;
import pl.maniak.mygoals.repository.DBHelper;

@Singleton
@Component(modules = AppModule.class)
public interface AppComponent {
    DBHelper getDBHelper();
}
