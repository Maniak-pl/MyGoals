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
import pl.maniak.mygoals.ui.goal.fragments.ListContract;
import pl.maniak.mygoals.ui.goal.fragments.ListPresenter;
import pl.maniak.mygoals.ui.goal.adapters.GoalsAdapter;
import pl.maniak.mygoals.ui.goal.fragments.ListFragment;

@Module
@RequiredArgsConstructor
public class ListFragmentModule {

    private final ListFragment fragment;

    @Provides
    Context provideContext() {
        return fragment.getContext();
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
    LinearLayoutManager provideLinearLayoutManager(Context context) {
        return new LinearLayoutManager(context);
    }

    @Provides
    ListContract.Presenter provideGoalPresenter(GoalRepository repository) {
        return new ListPresenter(repository);
    }
}