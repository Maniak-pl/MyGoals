package pl.maniak.mygoals.ui.goal;

import java.util.List;

import pl.maniak.mygoals.model.Goal;
import pl.maniak.mygoals.ui.BaseContract;

public interface GoalContract {

    interface View extends BaseContract.View {
        void refreshList(List<Goal> goals);
        void showGoalDialog(Goal goal);
    }

    interface Router extends BaseContract.Router {

    }

    interface Presenter extends BaseContract.Presenter<View, Router> {

        void onSaveDialogButtonClicked(Goal goal);
        void onDeleteDialogButtonClicked(Goal goal);
        void onItemLongClicked(Goal goal);
        void onAddButtonClicked();
    }
}
