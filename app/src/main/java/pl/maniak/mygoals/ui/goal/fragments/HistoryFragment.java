package pl.maniak.mygoals.ui.goal.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import javax.inject.Inject;

import butterknife.BindView;
import pl.maniak.mygoals.App;
import pl.maniak.mygoals.R;
import pl.maniak.mygoals.model.History;
import pl.maniak.mygoals.repository.history.HistoryRepository;
import pl.maniak.mygoals.ui.BaseFragment;
import pl.maniak.mygoals.ui.goal.adapters.HistoryAdapter;
import pl.maniak.mygoals.utils.di.goal.DaggerHistoryFragmentComponent;
import pl.maniak.mygoals.utils.di.goal.HistoryFragmentModule;

public class HistoryFragment extends BaseFragment {

    @BindView(R.id.historyRecyclerView)
    RecyclerView recyclerView;

    @Inject
    HistoryAdapter adapter;

    @Inject
    LinearLayoutManager layoutManager;

    @Inject
    HistoryRepository repository;

    @Override
    protected void setFragmentCallback(Context context) {
    }

    public static HistoryFragment newInstance() {
        Bundle args = new Bundle();
        HistoryFragment fragment = new HistoryFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected void initDaggerComponent() {
        DaggerHistoryFragmentComponent.builder()
                .appComponent(App.getAppComponent())
                .historyFragmentModule(new HistoryFragmentModule(this))
                .build()
                .inject(this);
    }

    @Override
    public void onStart() {
        initRecyclerView();
        super.onStart();
    }

    private void initRecyclerView() {
        recyclerView.setLayoutManager(layoutManager);
        adapter.setListener(new HistoryAdapter.OnLongItemClickListener() {
            @Override
            public void onLongItemClicked(History history) {
                repository.deleteHistoryById(history.getId());
                adapter.updateData(repository.getAllHistory());
            }
        });
        recyclerView.setAdapter(adapter);

        adapter.updateData(repository.getAllHistory());
    }

    @Override
    protected void init() {
    }

    @Override
    protected int getContentViewId() {
        return R.layout.fragment_history;
    }
}