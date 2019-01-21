package pl.maniak.mygoals.utils.views;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Rect;
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

        progressLabel = (TextView)view.findViewById(R.id.goalProgressLabel);
        progressBar = (ProgressBar)view.findViewById(R.id.goalProgressBar);
    }

    public void setProgress(int current, int max) {
        progressLabel.setText(current + " / " + max);
        progressBar.setProgress(current);
        progressBar.setMax(max);
    }

    public void setProgressColor(ProgressColor color) {
        Resources res = getContext().getResources();
        Rect bounds = progressBar.getProgressDrawable().getBounds();
        progressBar.setProgressDrawable(res.getDrawable(getDrawableRes(color), null));
        progressBar.getProgressDrawable().setBounds(bounds);
    }

    private int getDrawableRes(ProgressColor color) {
        switch (color) {
            case red:
                return R.drawable.goal_progressbar_red;
            case green:
                return R.drawable.goal_progressbar_green;
            case orange:
                return R.drawable.goal_progressbar_orange;
            case blue:
                return R.drawable.goal_progressbar_blue;
            case yellow:
                return R.drawable.goal_progressbar_yellow;
            default:
                return R.drawable.goal_progressbar_blue;
        }
    }
}