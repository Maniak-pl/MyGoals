package pl.maniak.mygoals.repository.goal;

import java.util.List;

import pl.maniak.mygoals.model.Goal;

public interface GoalRepository {
    Goal getGoalById(Long id);
    List<Goal> getAllGoals();
    void saveGoal(Goal goal);
    void deleteGoalById(Long id);
}