package pl.maniak.mygoals.ui.goal.fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import pl.maniak.mygoals.App;
import pl.maniak.mygoals.R;
import pl.maniak.mygoals.model.Goal;
import pl.maniak.mygoals.ui.BaseFragment;
import pl.maniak.mygoals.ui.edit.EditGoalActivity;
import pl.maniak.mygoals.ui.goal.adapters.GoalsAdapter;
import pl.maniak.mygoals.utils.Constants;
import pl.maniak.mygoals.utils.di.goal.DaggerListFragmentComponent;
import pl.maniak.mygoals.utils.di.goal.ListFragmentModule;

public class ListFragment extends BaseFragment implements ListContract.View, ListContract.Router {

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    @BindView(R.id.fab)
    FloatingActionButton fab;

    @Inject
    ListContract.Presenter presenter;

    @Inject
    GoalsAdapter adapter;

    @Inject
    LinearLayoutManager layoutManager;

    public static ListFragment newInstance() {
        Bundle args = new Bundle();
        ListFragment fragment = new ListFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected void initDaggerComponent() {
        DaggerListFragmentComponent.builder()
                .appComponent(App.getAppComponent())
                .listFragmentModule(new ListFragmentModule(this))
                .build()
                .inject(this);
    }

    @Override
    protected void setFragmentCallback(Context context) {
    }

    @Override
    protected void init() {
        presenter.attachView(this);
        presenter.attachRouter(this);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.onMenuButtonClicked();
            }
        });
    }

    @Override
    public void onStart() {
        initRecyclerView();
        super.onStart();
    }

    private void initRecyclerView() {
        recyclerView.setLayoutManager(layoutManager);
        adapter.setClickListener(new GoalsAdapter.OnItemClickListener() {
            @Override
            public void onItemClicked(Goal goal) {
                presenter.onItemClicked(goal);
            }
        });
        adapter.setAddProgressListener(new GoalsAdapter.OnAddProgressClickListener() {
            @Override
            public void onAddProgressClicked(Goal goal) {
                presenter.onAddProgressButtonClicked(goal);
            }
        });
        recyclerView.setAdapter(adapter);
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener(){
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy){
                if (dy<0 && !fab.isShown())
                    fab.show();
                else if(dy>0 && fab.isShown())
                    fab.hide();
            }

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }
        });
    }

    @Override
    protected void clear() {
        presenter.detachView();
        presenter.detachRouter();
    }


    @Override
    public void onResume() {
        super.onResume();
        presenter.onResumed();
    }

    @Override
    protected int getContentViewId() {
        return R.layout.fragment_goals;
    }

    @Override
    public void refreshList(List<Goal> goals) {
        adapter.updateData(goals);
    }

    @Override
    public void navigationToEditGoal(Long goalId) {
        Intent intent = new Intent(getActivity(), EditGoalActivity.class);
        intent.putExtra(Constants.INTENT_GOAL_ID, goalId);
        startActivity(intent);
    }
}