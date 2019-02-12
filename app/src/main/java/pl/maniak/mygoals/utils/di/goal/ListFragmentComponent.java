package pl.maniak.mygoals.utils.di.goal;

import dagger.Component;
import pl.maniak.mygoals.ui.goal.fragments.ListFragment;
import pl.maniak.mygoals.utils.di.AppComponent;
import pl.maniak.mygoals.utils.di.RuntimeScope;

@RuntimeScope
@Component(
        dependencies = AppComponent.class,
        modules = ListFragmentModule.class
)
public interface ListFragmentComponent {
    void inject(ListFragment fragment);
}