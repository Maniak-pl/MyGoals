package pl.maniak.mygoals.ui.edit;

import pl.maniak.mygoals.model.Goal;
import pl.maniak.mygoals.ui.BaseContract;

public interface EditGoalContract {

    interface View extends BaseContract.View {
        void setData(Goal goal);
    }

    interface Router extends BaseContract.Router {

        void navigateBack();
    }

    interface Presenter extends BaseContract.Presenter<View, Router> {
        void onResumed(Long goalId);

        void onSaveButtonClicked(String title);
    }
}
