package pl.maniak.mygoals.model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@DatabaseTable
public class Goal {

    @DatabaseField(generatedId = true)
    protected Long id;
    @DatabaseField
    private String Title;
    @DatabaseField
    private Date date;
    @DatabaseField
    private int maxStep;
    @DatabaseField
    private int currentStep;
    @DatabaseField
    private ProgressColor color;

    public Goal() { }

    public Goal(String title, Date date, int currentStep, int maxStep, ProgressColor color) {
        Title = title;
        this.date = date;
        this.maxStep = maxStep;
        this.currentStep = currentStep;
        this.color = color;
    }
}