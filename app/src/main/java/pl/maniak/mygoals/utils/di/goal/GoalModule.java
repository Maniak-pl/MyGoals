package pl.maniak.mygoals.utils.di.goal;

import android.content.Context;

import dagger.Module;
import dagger.Provides;
import lombok.RequiredArgsConstructor;
import pl.maniak.mygoals.repository.DBHelper;
import pl.maniak.mygoals.repository.goal.GoalRepository;
import pl.maniak.mygoals.repository.goal.GoalRepositoryImpl;
import pl.maniak.mygoals.ui.goal.GoalActivity;

@Module
@RequiredArgsConstructor
public class GoalModule {
    private final GoalActivity activity;

    @Provides
    Context provideContext() {
        return activity.getBaseContext();
    }

    @Provides
    GoalRepository provideGoalRepository(DBHelper helper) {
        return new GoalRepositoryImpl(helper);
    }
}
