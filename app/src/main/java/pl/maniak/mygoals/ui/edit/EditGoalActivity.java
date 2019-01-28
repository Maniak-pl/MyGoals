package pl.maniak.mygoals.ui.edit;

import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;
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
    }

    @Override
    public void refreshTemplate(Goal goal) {
        tmpTitle.setText(goal.getTitle());
        tmpDate.setText(DateHelper.parseDateToString(goal.getDate()));

        tmpProgressBar.setProgressColor(goal.getColor());
        tmpProgressBar.setProgress(goal.getCurrentStep(), goal.getMaxStep());
    }

    @OnClick(R.id.editgoalSaveBtn)
    public void OnSaveButtonClick() {
        presenter.onSaveButtonClicked();
    }

    @Override
    public void navigateBack() {
        finish();
    }
}
