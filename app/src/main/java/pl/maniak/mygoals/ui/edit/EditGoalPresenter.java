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
        fillLayout(goalId);
    }

    @Override
    public void onSaveButtonClicked() {
        repository.saveGoal(goal);
        navigateBack();
    }

    @Override
    public void onTitleTextChanged(String title) {
        goal.setTitle(title);
        refreshTemplate();
    }

    @Override
    public void onCurrentStepTextChanged(String currentStep) {
        goal.setCurrentStep(!currentStep.isEmpty() ? Integer.parseInt(currentStep) : 0);
        refreshTemplate();
    }

    @Override
    public void onMaxStepTextChanged(String maxStep) {
        goal.setMaxStep(!maxStep.isEmpty() ? Integer.parseInt(maxStep) : 0);
        refreshTemplate();
    }

    @Override
    public void onColorChanged(ProgressColor color) {
        goal.setColor(color);
        refreshTemplate();
    }

    @Override
    public void onDeleteButtonClicked() {
        repository.deleteGoalById(goal.getId());
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

    private void fillLayout(Long id) {
        if (view != null) {
            goal = id != 0 ? repository.getGoalById(id) : initDefaultGoal();
            view.fillLayout(goal);
            view.refreshTemplate(goal);
            view.changeTextButton(goal.getId() == null ? true : false);
        }
    }

    private void navigateBack() {
        if (router != null) {
            router.navigateBack();
        }
    }

    private void refreshTemplate() {
        if (view != null) {
            view.refreshTemplate(goal);
        }
    }

    private static Goal initDefaultGoal() {
        return new Goal("Task", new Date(), 0, 10, ProgressColor.GREEN);
    }
}
