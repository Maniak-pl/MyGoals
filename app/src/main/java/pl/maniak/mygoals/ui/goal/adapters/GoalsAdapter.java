package pl.maniak.mygoals.ui.goal.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnLongClick;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import pl.maniak.mygoals.R;
import pl.maniak.mygoals.model.Goal;
import pl.maniak.mygoals.utils.helpers.DateHelper;
import pl.maniak.mygoals.utils.views.GoalProgress;


@RequiredArgsConstructor
public class GoalsAdapter extends RecyclerView.Adapter<GoalsAdapter.GoalViewHolder> {

    private final List<Goal> list;

    @Setter
    OnItemLongClickListener listener;

    @NonNull
    @Override
    public GoalViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.view_goal, viewGroup, false);

        GoalViewHolder holder = new GoalViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull GoalViewHolder holder, int position) {
        Goal goal = list.get(position);
        holder.setItem(goal);
        holder.listener = listener;
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void updateData(List<Goal> goals) {
        list.clear();
        list.addAll(goals);
        notifyDataSetChanged();
    }

    class GoalViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.goalTitle)
        TextView title;

        @BindView(R.id.createDate)
        TextView date;

        @BindView(R.id.goalProgress)
        GoalProgress progressBar;

        @BindView(R.id.goalAddButton)
        ImageView button;

        @Setter
        OnItemLongClickListener listener;

        @Setter
        private Goal goal;

        public GoalViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void setItem(Goal goal) {
            this.goal = goal;
            title.setText(goal.getTitle());
            date.setText(DateHelper.parseDateToString(goal.getDate()));

            progressBar.setProgressColor(goal.getColor());
            progressBar.setProgress(goal.getCurrentStep(), goal.getMaxStep());

            if(goal.getCurrentStep()>=goal.getMaxStep()) {
                button.setVisibility(View.GONE);
            } else {
                button.setVisibility(View.VISIBLE);
            }
        }

        @OnClick(R.id.goalAddButton)
        public void addButtonClicked() {
            goal.setCurrentStep(goal.getCurrentStep() + 1);
            notifyDataSetChanged();
        }

        @OnLongClick(R.id.goalLayout)
        public boolean longClicked() {
            if (listener != null) {
                listener.onItemLongClicked(goal);
            }
            return true;
        }
    }

    public interface OnItemLongClickListener {
        void onItemLongClicked(Goal goal);
    }
}