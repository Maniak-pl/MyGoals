package pl.maniak.mygoals.utils.di.edit;

import dagger.Component;
import pl.maniak.mygoals.ui.edit.EditGoalActivity;
import pl.maniak.mygoals.utils.di.AppComponent;
import pl.maniak.mygoals.utils.di.RuntimeScope;

@RuntimeScope
@Component(dependencies = AppComponent.class,
        modules = EditGoalModule.class
)
public interface EditGoalComponent {
    void inject(EditGoalActivity activity);
}
