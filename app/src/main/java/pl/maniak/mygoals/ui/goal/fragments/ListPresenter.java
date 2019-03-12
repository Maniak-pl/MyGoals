package pl.maniak.mygoals.ui.goal.fragments;

import java.util.Date;

import lombok.RequiredArgsConstructor;
import pl.maniak.mygoals.model.Goal;
import pl.maniak.mygoals.model.History;
import pl.maniak.mygoals.repository.goal.GoalRepository;
import pl.maniak.mygoals.repository.history.HistoryRepository;

@RequiredArgsConstructor
public class ListPresenter implements ListContract.Presenter {

    private final GoalRepository goalRepository;
    private final HistoryRepository historyRepository;

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
    public void onItemClicked(Goal goal) {
        navigationToEditGoal(goal.getId());
    }

    @Override
    public void onAddProgressButtonClicked(Goal goal) {
        checkGoalHasBeenCompleted(goal);
    }

    private void checkGoalHasBeenCompleted(Goal goal) {
        if(goal.getCurrentStep() == goal.getMaxStep()) {
            historyRepository.saveHistory(new History(goal.getTitle(), new Date(), goal.getMaxStep()));
            goalRepository.deleteGoalById(goal.getId());
        } else {
            goalRepository.saveGoal(goal);
        }
        refreshList();
    }

    @Override
    public void onResumed() {
        refreshList();
    }

    private void refreshList() {
        if (view != null) {
            view.refreshList(goalRepository.getAllGoals());
        }
    }

    private void navigationToEditGoal(Long goalId) {
        if (router != null) {
            router.navigationToEditGoal(goalId);
        }
    }

    @Override
    public void onMenuButtonClicked() {
        navigationToEditGoal(null);
    }
}