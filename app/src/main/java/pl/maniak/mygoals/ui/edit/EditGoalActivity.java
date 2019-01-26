package pl.maniak.mygoals.ui.edit;

import android.widget.Button;
import android.widget.EditText;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;
import pl.maniak.mygoals.R;
import pl.maniak.mygoals.model.Goal;
import pl.maniak.mygoals.ui.BaseActivity;
import pl.maniak.mygoals.utils.Constants;
import pl.maniak.mygoals.utils.di.edit.DaggerEditGoalComponent;
import pl.maniak.mygoals.utils.di.edit.EditGoalModule;

public class EditGoalActivity extends BaseActivity implements EditGoalContract.View, EditGoalContract.Router {

    @BindView(R.id.editgoal_label)
    EditText titleEt;

    @Inject
    EditGoalContract.Presenter presenter;

    @Override
    protected void init() {
        presenter.attachView(this);
        presenter.attachRouter(this);
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
    public void setData(Goal goal) {
        titleEt.setText(goal.getTitle());
    }

    @OnClick(R.id.editgoalSaveBtn)
    public void OnSaveButtonClick() {
        presenter.onSaveButtonClicked(titleEt.getText().toString());
    }

    @Override
    public void navigateBack() {
        finish();
    }
}
