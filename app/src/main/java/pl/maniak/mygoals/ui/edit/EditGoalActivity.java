package pl.maniak.mygoals.ui.edit;

import android.widget.TextView;

import javax.inject.Inject;

import butterknife.BindView;
import pl.maniak.mygoals.R;
import pl.maniak.mygoals.model.Goal;
import pl.maniak.mygoals.ui.BaseActivity;
import pl.maniak.mygoals.utils.di.edit.DaggerEditGoalComponent;
import pl.maniak.mygoals.utils.di.edit.EditGoalModule;

public class EditGoalActivity extends BaseActivity implements EditGoalContract.View, EditGoalContract.Router {

    @BindView(R.id.editgoal_label)
    TextView label;

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
        presenter.onResumed(null);
    }

    @Override
    public void setData(Goal goal) {
        label.setText(goal.getTitle());
    }
}
