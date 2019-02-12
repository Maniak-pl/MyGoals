package pl.maniak.mygoals.ui.goal.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnLongClick;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import pl.maniak.mygoals.R;
import pl.maniak.mygoals.model.History;
import pl.maniak.mygoals.utils.helpers.DateHelper;

@RequiredArgsConstructor
public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.HistoryHolder> {

    private final List<History> list;

    @Setter
    HistoryAdapter.OnLongItemClickListener listener;

    @NonNull
    @Override
    public HistoryHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.view_history, viewGroup, false);

        HistoryAdapter.HistoryHolder holder = new HistoryAdapter.HistoryHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull HistoryHolder holder, int position) {
        History history = list.get(position);
        holder.setItem(history);
        holder.longItemClickListener = listener;
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void updateData(List<History> historyList) {
        list.clear();
        list.addAll(historyList);
        notifyDataSetChanged();
    }

    class HistoryHolder extends RecyclerView.ViewHolder {

        private History history;

        @BindView(R.id.historyTitle)
        TextView title;

        @BindView(R.id.historyEndDate)
        TextView date;

        @BindView(R.id.historyStep)
        TextView step;

        @Setter
        OnLongItemClickListener longItemClickListener;

        public HistoryHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void setItem(History history) {
            this.history = history;
            title.setText(history.getTitle());
            step.setText(String.valueOf(history.getStep()));
            date.setText(DateHelper.parseDateToTwoPartString(history.getDate()));
        }

        @OnLongClick
        public boolean longClicked() {
            if (longItemClickListener != null) {
                longItemClickListener.onLongItemClicked(history);
            }
            return true;
        }
    }

    public interface OnLongItemClickListener {
        void onLongItemClicked(History history);
    }
}