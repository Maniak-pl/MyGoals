package pl.maniak.mygoals.ui.goal.dialogs;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import lombok.Setter;
import pl.maniak.mygoals.R;
import pl.maniak.mygoals.model.Goal;
import pl.maniak.mygoals.model.ProgressColor;

public class GoalDialog extends DialogFragment {

    @BindView(R.id.dialogGoalTitle)
    EditText title;

    @BindView(R.id.dialogGoalCurrentProgress)
    EditText currentProgress;

    @BindView(R.id.dialogGoalMaxProgress)
    EditText maxProgress;

    @BindView(R.id.dialogGoalColor)
    Spinner spinner;

    private static Goal defaultGoal = initDefaultGoal();

    @NonNull
    private static Goal initDefaultGoal() {
        return new Goal("Task", new Date(), 0, 10, ProgressColor.green);
    }

    @Setter
    private OnSaveGoalClickedListener saveListener;
    @Setter
    private OnDeleteGoalClickedListener deleteListener;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        super.onCreateDialog(savedInstanceState);


        LayoutInflater i = getActivity().getLayoutInflater();
        View view = i.inflate(R.layout.dialog_goal, null);

        ButterKnife.bind(this, view);


        spinner.setAdapter(new ArrayAdapter(getContext(), android.R.layout.simple_spinner_item, ProgressColor.values()));

        Dialog alertDialog = new Dialog(getActivity());
        alertDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        alertDialog.setContentView(view);
        alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        setFields();
        return alertDialog;
    }

    private void setFields() {
        title.setText(defaultGoal.getTitle());
        currentProgress.setText(String.valueOf(defaultGoal.getCurrentStep()));
        maxProgress.setText(String.valueOf(defaultGoal.getMaxStep()));
        selectSpinnerItemByValue(spinner, defaultGoal.getColor());
    }

    public static void selectSpinnerItemByValue(Spinner spnr, ProgressColor value) {
        ArrayAdapter adapter = (ArrayAdapter) spnr.getAdapter();
        for (int position = 0; position < adapter.getCount(); position++) {
            if (value.equals(adapter.getItemId(position))) {
                spnr.setSelection(position);
                return;
            }
        }
    }

    public static GoalDialog newInstance(Goal goal) {
        defaultGoal = goal != null ? goal : initDefaultGoal();
        GoalDialog fragment = new GoalDialog();
        return fragment;
    }


    @OnClick(R.id.dialogGoalSaveButton)
    public void onButtonClicked() {

        defaultGoal.setTitle(title.getText().toString());
        defaultGoal.setCurrentStep(Integer.parseInt(currentProgress.getText().toString()));
        defaultGoal.setMaxStep(Integer.parseInt(maxProgress.getText().toString()));
        defaultGoal.setColor((ProgressColor) spinner.getSelectedItem());

        if (saveListener != null) {
            saveListener.onSaveGoalClicked(defaultGoal);
        }
        dismiss();
    }

    @OnClick(R.id.dialogGoalDeleteButton)
    public void onDeleteButtonClicked() {
        if (deleteListener != null) {
            deleteListener.onDeleteGoalClicked(defaultGoal);
        }
        dismiss();
    }

    public interface OnSaveGoalClickedListener {
        void onSaveGoalClicked(Goal goal);
    }

    public interface OnDeleteGoalClickedListener {
        void onDeleteGoalClicked(Goal goal);
    }
}
