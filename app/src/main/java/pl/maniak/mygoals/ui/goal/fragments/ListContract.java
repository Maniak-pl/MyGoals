package pl.maniak.mygoals.ui.goal.fragments;

import java.util.List;

import pl.maniak.mygoals.model.Goal;
import pl.maniak.mygoals.ui.BaseContract;

public interface ListContract {

    interface View extends BaseContract.View {
        void refreshList(List<Goal> goals);
    }

    interface Router extends BaseContract.Router {
        void navigationToEditGoal(Long goalId);
    }

    interface Presenter extends BaseContract.Presenter<View, Router> {

        void onItemLongClicked(Goal goal);
        void onAddProgressButtonClicked(Goal goal);
        void onResumed();
    }
}