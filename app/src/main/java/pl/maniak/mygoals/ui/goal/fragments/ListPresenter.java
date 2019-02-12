package pl.maniak.mygoals.ui.goal.fragments;

import lombok.RequiredArgsConstructor;
import pl.maniak.mygoals.model.Goal;
import pl.maniak.mygoals.repository.goal.GoalRepository;

@RequiredArgsConstructor
public class ListPresenter implements ListContract.Presenter {

    private final GoalRepository repository;

    ListContract.View view;
    ListContract.Router router;

    @Override
    public void attachView(ListContract.View view) {
        this.view = view;
    }

    @Override
    public void attachRouter(ListContract.Router router) {
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
    public void onItemLongClicked(Goal goal) {
        navigationToEditGoal(goal.getId());
    }

    @Override
    public void onAddProgressButtonClicked(Goal goal) {
        repository.saveGoal(goal);
        refreshList();
    }

    @Override
    public void onResumed() {
        refreshList();
    }

    private void refreshList() {
        if (view != null) {
            view.refreshList(repository.getAllGoals());
        }
    }

    private void navigationToEditGoal(Long goalId) {
        if (router != null) {
            router.navigationToEditGoal(goalId);
        }
    }
}