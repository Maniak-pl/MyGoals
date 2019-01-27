package pl.maniak.mygoals.ui.goal;

import lombok.RequiredArgsConstructor;
import pl.maniak.mygoals.model.Goal;
import pl.maniak.mygoals.repository.goal.GoalRepository;

@RequiredArgsConstructor
public class GoalPresenter implements GoalContract.Presenter {

    private final GoalRepository repository;

    GoalContract.View view;
    GoalContract.Router router;

    @Override
    public void attachView(GoalContract.View view) {
        this.view = view;
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
    public void onSaveDialogButtonClicked(Goal goal) {
        repository.saveGoal(goal);
        refreshList();
    }

    @Override
    public void onDeleteDialogButtonClicked(Goal goal) {
        repository.deleteGoalById(goal.getId());
        refreshList();
    }

    @Override
    public void onItemLongClicked(Goal goal) {
//        showDialog(goal);
        navigationToEditGoal(goal.getId());
    }

    @Override
    public void onAddProgressButtonClicked(Goal goal) {
        repository.saveGoal(goal);
        refreshList();
    }

    @Override
    public void onAddButtonClicked() {
        showDialog(null);
//        navigationToEditGoal(null);
    }

    @Override
    public void onResumed() {
        refreshList();
    }

    private void refreshList() {
        if(view!=null) {
            view.refreshList(repository.getAllGoals());
        }
    }

    private void showDialog(Goal goal) {
        if(view != null) {
            view.showGoalDialog(goal);
        }
    }

    private void navigationToEditGoal(Long goalId) {
        if(router!=null) {
            router.navigationToEditGoal(goalId);
        }
    }
}
