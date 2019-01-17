package pl.maniak.mygoals.ui.goal;

import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import pl.maniak.mygoals.R;
import pl.maniak.mygoals.model.Goal;
import pl.maniak.mygoals.repository.goal.GoalRepository;
import pl.maniak.mygoals.ui.BaseActivity;
import pl.maniak.mygoals.ui.goal.adapters.GoalsAdapter;
import pl.maniak.mygoals.utils.di.goal.DaggerGoalComponent;
import pl.maniak.mygoals.utils.di.goal.GoalModule;

public class GoalActivity extends BaseActivity {

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    @Inject
    GoalRepository repository;

    @Override
    protected void init() {

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

//        repository.saveGoal(new Goal("Notebooks with English", new Date(), 20, 0, ProgressColor.yellow));
//        repository.saveGoal(new Goal("Pokemon", new Date(), 9, 0, ProgressColor.yellow));
//        repository.saveGoal(new Goal("Book", new Date(), 20, 2, ProgressColor.red));

        List<Goal> list = repository.getAllGoals();
//        list.add(new Goal("eTutor - Lesson", new Date(), 50, 22, ProgressColor.blue));
//        list.add(new Goal("Book", new Date(), 20, 2, ProgressColor.red));
//        list.add(new Goal("Notebooks with English", new Date(), 20, 1, ProgressColor.yellow));
//
//        repository.saveGoal(new Goal("eTutor - Lesson", new Date(), 50, 22, ProgressColor.blue));

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new GoalsAdapter(list));
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

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}