package pl.maniak.mygoals.ui.goal;

import pl.maniak.mygoals.ui.BaseContract;

public interface GoalContract {

    interface View extends BaseContract.View {
        void showGoalsFragment();
        void showHistoryFragment();
    }

    interface Router extends BaseContract.Router {
        void navigationToEditGoal(Long goalId);
    }

    interface Presenter extends BaseContract.Presenter<View, Router> {
        void onMenuButtonClicked();
        void onListNavigationItemSelected();
        void onHistoryNavigationItemSelected();
    }
}
