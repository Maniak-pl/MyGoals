package pl.maniak.mygoals.utils.di.edit;

import dagger.Module;
import dagger.Provides;
import lombok.RequiredArgsConstructor;
import pl.maniak.mygoals.R;
import pl.maniak.mygoals.model.ProgressColor;
import pl.maniak.mygoals.repository.DBHelper;
import pl.maniak.mygoals.repository.goal.GoalRepository;
import pl.maniak.mygoals.repository.goal.GoalRepositoryImpl;
import pl.maniak.mygoals.ui.edit.EditGoalActivity;
import pl.maniak.mygoals.ui.edit.EditGoalContract;
import pl.maniak.mygoals.ui.edit.EditGoalPresenter;
import pl.maniak.mygoals.ui.edit.adapters.EnumAdapter;

@Module
@RequiredArgsConstructor
public class EditGoalModule {
    private final EditGoalActivity activity;

    @Provides
    GoalRepository provideGoalRepository(DBHelper helper) {
        return new GoalRepositoryImpl(helper);
    }

    @Provides
    EditGoalContract.Presenter provideGoalPresenter(GoalRepository repository) {
        return new EditGoalPresenter(repository);
    }

    @Provides
    EnumAdapter provideEnumAdapter() {
        return new EnumAdapter(activity, R.layout.spinner_color, ProgressColor.values());
    }
}
