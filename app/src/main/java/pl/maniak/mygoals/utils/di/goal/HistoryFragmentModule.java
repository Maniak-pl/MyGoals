package pl.maniak.mygoals.utils.di.goal;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;

import java.util.ArrayList;

import dagger.Module;
import dagger.Provides;
import lombok.RequiredArgsConstructor;
import pl.maniak.mygoals.model.History;
import pl.maniak.mygoals.ui.goal.adapters.HistoryAdapter;
import pl.maniak.mygoals.ui.goal.fragments.HistoryFragment;

@Module
@RequiredArgsConstructor
public class HistoryFragmentModule {

    private final HistoryFragment fragment;

    @Provides
    Context provideContext() {
        return fragment.getContext();
    }

    @Provides
    HistoryAdapter provideHistoryAdapter() {
        return new HistoryAdapter(new ArrayList<History>());
    }

    @Provides
    LinearLayoutManager provideLinearLayoutManager(Context context) {
        return new LinearLayoutManager(context);
    }
}