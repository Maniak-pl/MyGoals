package pl.maniak.mygoals.ui.edit;

import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;
import pl.maniak.mygoals.R;
import pl.maniak.mygoals.model.Goal;
import pl.maniak.mygoals.model.ProgressColor;
import pl.maniak.mygoals.ui.BaseActivity;
import pl.maniak.mygoals.ui.edit.adapters.EnumAdapter;
import pl.maniak.mygoals.utils.Constants;
import pl.maniak.mygoals.utils.di.edit.DaggerEditGoalComponent;
import pl.maniak.mygoals.utils.di.edit.EditGoalModule;
import pl.maniak.mygoals.utils.helpers.DateHelper;
import pl.maniak.mygoals.utils.views.GoalEditText;
import pl.maniak.mygoals.utils.views.GoalProgress;

public class EditGoalActivity extends BaseActivity implements EditGoalContract.View, EditGoalContract.Router {

    @BindView(R.id.editGoalLabel)
    GoalEditText title;

    @BindView(R.id.editGoalCurrentStep)
    GoalEditText currentStep;

    @BindView(R.id.editGoalMaxStep)
    GoalEditText maxStep;

    @BindView(R.id.editgoalSpinner)
    Spinner spinner;

    @BindView(R.id.goalTitle)
    TextView tmpTitle;

    @BindView(R.id.createDate)
    TextView tmpDate;

    @BindView(R.id.goalProgress)
    GoalProgress tmpProgressBar;

    @BindView(R.id.editgoalSwitch)
    Switch percentageSwitch;

    @BindView(R.id.editgoalSaveBtn)
    Button saveBtn;

    @BindView(R.id.editgoalDeleteBtn)
    Button deleteBtn;

    @Inject
    EditGoalContract.Presenter presenter;

    @Inject
    EnumAdapter spinnerAdapter;

    @Override
    protected void init() {
        presenter.attachView(this);
        presenter.attachRouter(this);

        title.setListener(new GoalEditText.OnTextChangeListener() {
            @Override
            public void onTextChanged(String str) {
                presenter.onTitleTextChanged(str);
            }
        });

        currentStep.setListener(new GoalEditText.OnTextChangeListener() {
            @Override
            public void onTextChanged(String str) {
                presenter.onCurrentStepTextChanged(str);
            }
        });

        maxStep.setListener(new GoalEditText.OnTextChangeListener() {
            @Override
            public void onTextChanged(String str) {
                presenter.onMaxStepTextChanged(str);
            }
        });

        percentageSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                presenter.onSwitchChanged(b);
            }
        });

        initSpinner();
    }

    private void initSpinner() {
        spinner.setAdapter(spinnerAdapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                ProgressColor color = (ProgressColor) adapterView
                        .getItemAtPosition(position);
                presenter.onColorChanged(color);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    @Override
    protected void initDaggerComponent() {
        DaggerEditGoalComponent.builder()
                .appComponent(getAppComponent())
                .editGoalModule(new EditGoalModule(this))
                .build()
                .inject(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_edit;
    }

    @Override
    protected void clear() {
        presenter.detachView();
        presenter.detachRouter();
    }

    @Override
    protected void onResume() {
        super.onResume();
        Long id = getIntent().getLongExtra(Constants.INTENT_GOAL_ID, 0);
        presenter.onResumed(id);
    }

    @Override
    public void fillLayout(Goal goal) {
        title.setText(goal.getTitle());
        currentStep.setText(String.valueOf(goal.getCurrentStep()));
        maxStep.setText(String.valueOf(goal.getMaxStep()));
        spinner.setSelection(goal.getColor().ordinal());
        percentageSwitch.setChecked(goal.isPercentageProgress());
    }

    @Override
    public void refreshTemplate(Goal goal) {
        tmpTitle.setText(goal.getTitle());
        tmpDate.setText(DateHelper.parseDateToString(goal.getDate()));

        tmpProgressBar.setProgressColor(goal.getColor());
        tmpProgressBar.setProgress(goal.getCurrentStep(), goal.getMaxStep(), goal.isPercentageProgress());
    }

    @Override
    public void changeTextButton(boolean isCreated) {
        if (isCreated) {
            saveBtn.setText(R.string.create);
            deleteBtn.setText(R.string.cancel);
        } else {
            saveBtn.setText(R.string.save);
            deleteBtn.setText(R.string.delete);
        }
    }

    @OnClick(R.id.editgoalSaveBtn)
    public void OnSaveButtonClick() {
        presenter.onSaveButtonClicked();
    }

    @OnClick(R.id.editgoalDeleteBtn)
    public void OnDeleteButtonClick() {
        presenter.onDeleteButtonClicked();
    }

    @Override
    public void navigateBack() {
        finish();
    }
}
