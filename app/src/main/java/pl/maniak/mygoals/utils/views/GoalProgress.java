package pl.maniak.mygoals.utils.views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import pl.maniak.mygoals.R;
import pl.maniak.mygoals.model.ProgressColor;

public class GoalProgress extends RelativeLayout {

    TextView progressLabel;
    ProgressBar progressBar;

    public GoalProgress(Context context) {
        this(context, null);
    }

    public GoalProgress(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public GoalProgress(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.view_goal_progress, this, true);

        progressLabel = (TextView) view.findViewById(R.id.goalProgressLabel);
        progressBar = (ProgressBar) view.findViewById(R.id.goalProgressBar);
    }

    public void setProgress(int current, int max, boolean percentageProgres) {
        progressBar.setMax(max);
        progressBar.setProgress(current);
        if(percentageProgres) {
            progressLabel.setText(calculatePercentage(current, max));
        } else {
            progressLabel.setText(current + " / " + max);
        }
    }

    private String calculatePercentage(int current, int max) {
        int percentage = 0;
        if(max!=0) {
            percentage = (current*100)/max;
        }
        return percentage + "%";
    }

    public void setProgressColor(ProgressColor color) {
        progressBar.setProgressDrawable(getContext().getResources().getDrawable(getDrawableRes(color), null));
    }

    private int getDrawableRes(ProgressColor color) {
        switch (color) {
            case RED:
                return R.drawable.goal_progressbar_red;
            case GREEN:
                return R.drawable.goal_progressbar_green;
            case ORANGE:
                return R.drawable.goal_progressbar_orange;
            case BLUE:
                return R.drawable.goal_progressbar_blue;
            case YELLOW:
                return R.drawable.goal_progressbar_yellow;
            default:
                return R.drawable.goal_progressbar_blue;
        }
    }
}