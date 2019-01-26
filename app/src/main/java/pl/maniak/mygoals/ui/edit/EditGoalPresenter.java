package pl.maniak.mygoals.ui.edit;

import java.util.Date;

import lombok.RequiredArgsConstructor;
import pl.maniak.mygoals.model.Goal;
import pl.maniak.mygoals.model.ProgressColor;
import pl.maniak.mygoals.repository.goal.GoalRepository;

@RequiredArgsConstructor
public class EditGoalPresenter implements EditGoalContract.Presenter {

    EditGoalContract.View view;
    EditGoalContract.Router router;

    private final GoalRepository repository;
    private static Goal goal = initDefaultGoal();

    @Override
    public void onResumed(Long goalId) {
        setData(goalId);
    }

    @Override
    public void onSaveButtonClicked(String title) {
        goal.setTitle(title);
        repository.saveGoal(goal);
        navigateBack();
    }

    @Override
    public void attachView(EditGoalContract.View view) {
        this.view = view;
    }

    @Override
    public void attachRouter(EditGoalContract.Router router) {
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

    private void setData(Long id) {
        if (view != null) {
            goal = id != 0 ? repository.getGoalById(id) : initDefaultGoal();
            view.setData(goal);
        }
    }

    private void navigateBack() {
        if (router != null) {
            router.navigateBack();
        }
    }

    private static Goal initDefaultGoal() {
        return new Goal("Task", new Date(), 0, 10, ProgressColor.green);
    }
}
