package pl.maniak.mygoals.utils.di.goal;

import dagger.Module;
import dagger.Provides;
import lombok.RequiredArgsConstructor;
import pl.maniak.mygoals.ui.goal.GoalActivity;
import pl.maniak.mygoals.ui.goal.GoalContract;
import pl.maniak.mygoals.ui.goal.GoalPresenter;
import pl.maniak.mygoals.ui.goal.fragments.HistoryFragment;
import pl.maniak.mygoals.ui.goal.fragments.ListFragment;

@Module
@RequiredArgsConstructor
public class GoalModule {
    private final GoalActivity activity;

    @Provides
    ListFragment provideListFragment() {
        return ListFragment.newInstance();
    }

    @Provides
    HistoryFragment provideHistoryFragment() {
        return HistoryFragment.newInstance();
    }

    @Provides
    GoalContract.Presenter provideGoalPresenter() {
        return new GoalPresenter();
    }
}
