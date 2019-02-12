package pl.maniak.mygoals.utils.di.goal;

import dagger.Component;
import pl.maniak.mygoals.ui.goal.fragments.HistoryFragment;
import pl.maniak.mygoals.utils.di.AppComponent;
import pl.maniak.mygoals.utils.di.RuntimeScope;

@RuntimeScope
@Component(
        dependencies = AppComponent.class,
        modules = HistoryFragmentModule.class
)
public interface HistoryFragmentComponent {
    void inject(HistoryFragment fragment);
}