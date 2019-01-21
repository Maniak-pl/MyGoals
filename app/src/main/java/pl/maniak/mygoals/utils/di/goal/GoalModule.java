package pl.maniak.mygoals.utils.di.goal;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;

import java.util.ArrayList;

import dagger.Module;
import dagger.Provides;
import lombok.RequiredArgsConstructor;
import pl.maniak.mygoals.model.Goal;
import pl.maniak.mygoals.repository.DBHelper;
import pl.maniak.mygoals.repository.goal.GoalRepository;
import pl.maniak.mygoals.repository.goal.GoalRepositoryImpl;
import pl.maniak.mygoals.ui.goal.GoalActivity;
import pl.maniak.mygoals.ui.goal.adapters.GoalsAdapter;

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

    @Provides
    GoalsAdapter provideGoalAdapter() {
        return new GoalsAdapter(new ArrayList<Goal>());
    }

    @Provides
    LinearLayoutManager provideLinearLayoutManager() {
        return new LinearLayoutManager(activity);
    }
}