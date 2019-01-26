package pl.maniak.mygoals.ui.goal;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import pl.maniak.mygoals.R;
import pl.maniak.mygoals.model.Goal;
import pl.maniak.mygoals.repository.goal.GoalRepository;
import pl.maniak.mygoals.ui.BaseActivity;
import pl.maniak.mygoals.ui.edit.EditGoalActivity;
import pl.maniak.mygoals.ui.goal.adapters.GoalsAdapter;
import pl.maniak.mygoals.ui.goal.dialogs.GoalDialog;
import pl.maniak.mygoals.utils.Constants;
import pl.maniak.mygoals.utils.di.goal.DaggerGoalComponent;
import pl.maniak.mygoals.utils.di.goal.GoalModule;

public class GoalActivity extends BaseActivity implements GoalContract.View, GoalContract.Router {

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    @Inject
    GoalRepository repository;

    @Inject GoalContract.Presenter presenter;

    @Inject
    GoalsAdapter adapter;

    @Inject
    LinearLayoutManager layoutManager;

    @Override
    protected void init() {

        presenter.attachView(this);
        presenter.attachRouter(this);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.onAddButtonClicked();
            }
        });

        initRecyclerView();
    }

    private void initRecyclerView() {
        recyclerView.setLayoutManager(layoutManager);
        adapter.setLongClickListener(new GoalsAdapter.OnItemLongClickListener() {
            @Override
            public void onItemLongClicked(Goal goal) {
                presenter.onItemLongClicked(goal);
            }
        });
        adapter.setAddProgressListener(new GoalsAdapter.OnAddProgressClickListener() {
            @Override
            public void onAddProgressClicked(Goal goal) {
                presenter.onAddProgressButtonClicked(goal);
            }
        });
        recyclerView.setAdapter(adapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.onResumed();
    }

    @Override
    protected void initDaggerComponent() {
        DaggerGoalComponent.builder()
                .appComponent(getAppComponent())
                .goalModule(new GoalModule(this))
                .build()
                .inject(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void clear() {
        presenter.detachView();
        presenter.detachRouter();
    }

    @Override
    public void refreshList(List<Goal> goals) {
        adapter.updateData(goals);
    }

    @Override
    public void showGoalDialog(Goal goal) {
        GoalDialog dialog = GoalDialog.newInstance(goal);
        dialog.setSaveListener(new GoalDialog.OnSaveGoalClickedListener() {
            @Override
            public void onSaveGoalClicked(Goal goal) {
                presenter.onSaveDialogButtonClicked(goal);
            }
        });
        dialog.setDeleteListener(new GoalDialog.OnDeleteGoalClickedListener() {
            @Override
            public void onDeleteGoalClicked(Goal goal) {
               presenter.onDeleteDialogButtonClicked(goal);
            }
        });
        dialog.show(getSupportFragmentManager(), "Goal Tag");
    }

    @Override
    public void navigationToEditGoal(Long goalId) {
        Intent intent = new Intent(this, EditGoalActivity.class);
        intent.putExtra(Constants.INTENT_GOAL_ID, goalId);
        startActivity(intent);
    }
}