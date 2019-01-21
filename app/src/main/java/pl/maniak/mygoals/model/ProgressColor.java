package pl.maniak.mygoals.model;

public enum  ProgressColor {
    red("RED"), green("GREEN"), blue("BLUE"), orange("ORANGE"), yellow("YELLOW");
    private String colorName;

    private ProgressColor(String name){
        this.colorName = name;
    }

    @Override public String toString(){
        return colorName;
    }

}