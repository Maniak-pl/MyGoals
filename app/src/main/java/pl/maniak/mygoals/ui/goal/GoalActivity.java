package pl.maniak.mygoals.ui.goal;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.MenuItem;
import android.view.View;

import javax.inject.Inject;

import butterknife.BindView;
import pl.maniak.mygoals.R;
import pl.maniak.mygoals.ui.BaseActivity;
import pl.maniak.mygoals.ui.edit.EditGoalActivity;
import pl.maniak.mygoals.ui.goal.fragments.HistoryFragment;
import pl.maniak.mygoals.ui.goal.fragments.ListFragment;
import pl.maniak.mygoals.utils.Constants;
import pl.maniak.mygoals.utils.di.goal.DaggerGoalComponent;
import pl.maniak.mygoals.utils.di.goal.GoalModule;

public class GoalActivity extends BaseActivity implements GoalContract.View, GoalContract.Router {

    @BindView(R.id.navigation)
    BottomNavigationView navigation;

    @Inject
    ListFragment listFragment;

    @Inject
    HistoryFragment historyFragment;

    @Inject
    GoalContract.Presenter presenter;

    @Override
    protected void init() {

        presenter.attachView(this);
        presenter.attachRouter(this);

        navigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.navigation_goals:
                        presenter.onListNavigationItemSelected();
                        return true;
                    case R.id.navigation_history:
                        presenter.onHistoryNavigationItemSelected();
                        return true;
                }
                return false;
            }
        });
    }

    private void replaceFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment, fragment).commit();
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
        return R.layout.activity_goal;
    }

    @Override
    protected void clear() {
        presenter.detachView();
        presenter.detachRouter();
    }

    @Override
    public void navigationToEditGoal(Long goalId) {
        Intent intent = new Intent(this, EditGoalActivity.class);
        intent.putExtra(Constants.INTENT_GOAL_ID, goalId);
        startActivity(intent);
    }

    @Override
    public void showGoalsFragment() {
        replaceFragment(listFragment);
    }

    @Override
    public void showHistoryFragment() {
        replaceFragment(historyFragment);
    }
}