package pl.maniak.mygoals.model;

import pl.maniak.mygoals.App;
import pl.maniak.mygoals.R;


public enum ProgressColor implements EnumSpinner {
    RED("Red"),
    BLUE("Blue"),
    GREEN("Green"),
    ORANGE("Orange"),
    YELLOW("Yellow");

    private String colorName;

    ProgressColor(String name) {
        this.colorName = name;
    }

    @Override
    public String toString() {
        return colorName;
    }

    @Override
    public String getLabel() {
        return colorName;
    }

    public int getColor() {
        switch (this) {
            case RED:
                return App.getInstance().getColor(R.color.red);
            case BLUE:
                return App.getInstance().getColor(R.color.blue);
            case GREEN:
                return App.getInstance().getColor(R.color.green);
            case ORANGE:
                return App.getInstance().getColor(R.color.orange);
            case YELLOW:
                return App.getInstance().getColor(R.color.yellow);
        }
        return -1;
    }
}