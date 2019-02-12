package pl.maniak.mygoals.model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@DatabaseTable
public class History {

    @DatabaseField(generatedId = true)
    protected Long id;
    @DatabaseField
    private String Title;
    @DatabaseField
    private Date date;
    @DatabaseField
    private int step;

    public History() {
    }

    public History(String title, Date date, int step) {
        Title = title;
        this.date = date;
        this.step = step;
    }
}