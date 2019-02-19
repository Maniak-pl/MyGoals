package pl.maniak.mygoals.ui.edit;

import pl.maniak.mygoals.model.Goal;
import pl.maniak.mygoals.model.ProgressColor;
import pl.maniak.mygoals.ui.BaseContract;

public interface EditGoalContract {

    interface View extends BaseContract.View {
        void fillLayout(Goal goal);
        void refreshTemplate(Goal goal);

        void changeTextButton(boolean isCreated);
    }

    interface Router extends BaseContract.Router {

        void navigateBack();
    }

    interface Presenter extends BaseContract.Presenter<View, Router> {
        void onResumed(Long goalId);
        void onSaveButtonClicked();
        void onTitleTextChanged(String title);
        void onCurrentStepTextChanged(String currentStep);
        void onMaxStepTextChanged(String maxStep);
        void onColorChanged(ProgressColor color);
        void onDeleteButtonClicked();
        void onSwitchChanged(boolean isPercentage);
    }
}
