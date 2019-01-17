package pl.maniak.mygoals.model;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@AllArgsConstructor
public class Goal {

    private String Title;
    private Date date;
    private int maxStep;
    @Setter
    private int currentStep;
}
