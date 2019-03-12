package pl.maniak.mygoals.ui.goal;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class GoalPresenter implements GoalContract.Presenter {

    GoalContract.View view;
    GoalContract.Router router;

    @Override
    public void attachView(GoalContract.View view) {
        this.view = view;
        view.showGoalsFragment();
    }

    @Override
    public void attachRouter(GoalContract.Router router) {
        this.router = router;
    }

    @Override
    public void detachRouter() {
        router = null;
    }

    @Override
    public void detachView() {
        view = null;
    }

    @Override
    public void onListNavigationItemSelected() {
        if (view != null) {
            view.showGoalsFragment();
        }
    }

    @Override
    public void onHistoryNavigationItemSelected() {
        if (view != null) {
            view.showHistoryFragment();
        }
    }

    private void navigationToEditGoal(Long goalId) {
        if (router != null) {
            router.navigationToEditGoal(goalId);
        }
    }
}
