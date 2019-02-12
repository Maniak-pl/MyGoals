package pl.maniak.mygoals.utils.di;

import javax.inject.Singleton;

import dagger.Component;
import pl.maniak.mygoals.repository.DBHelper;
import pl.maniak.mygoals.repository.history.HistoryRepository;

@Singleton
@Component(modules = AppModule.class)
public interface AppComponent {
    DBHelper getDBHelper();
    HistoryRepository getHistoryRepository();
}
