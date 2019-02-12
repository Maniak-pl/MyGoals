package pl.maniak.mygoals.ui.goal.fragments;

import android.content.Context;
import android.os.Bundle;

import pl.maniak.mygoals.R;
import pl.maniak.mygoals.ui.BaseFragment;

public class HistoryFragment extends BaseFragment {
    @Override
    protected void setFragmentCallback(Context context) { }

    public static HistoryFragment newInstance() {
        Bundle args = new Bundle();
        HistoryFragment fragment = new HistoryFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected void init() { }

    @Override
    protected int getContentViewId() {
        return R.layout.fragment_history;
    }
}