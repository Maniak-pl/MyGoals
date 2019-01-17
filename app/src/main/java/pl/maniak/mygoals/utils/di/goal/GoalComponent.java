package pl.maniak.mygoals.utils.di.goal;

import dagger.Component;
import pl.maniak.mygoals.ui.goal.GoalActivity;
import pl.maniak.mygoals.utils.di.AppComponent;
import pl.maniak.mygoals.utils.di.RuntimeScope;

@RuntimeScope
@Component(dependencies = AppComponent.class,
        modules = GoalModule.class
)
public interface GoalComponent {
    void inject(GoalActivity activity);
}
