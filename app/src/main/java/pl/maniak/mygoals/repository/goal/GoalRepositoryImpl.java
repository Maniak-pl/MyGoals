package pl.maniak.mygoals.repository.goal;

import android.util.Log;

import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import lombok.RequiredArgsConstructor;
import pl.maniak.mygoals.model.Goal;
import pl.maniak.mygoals.repository.DBHelper;

@RequiredArgsConstructor
public class GoalRepositoryImpl implements GoalRepository {

    private final DBHelper helper;

    @Override
    public Goal getGoalById(Long id) {
        Goal goal = new Goal();
        try {
            goal = getDao().queryForId(id);
        }catch (SQLException e) {
            Log.e("Maniak", "GoalRepositoryImpl.getGoalById() ", e);
        }
        return goal;
    }

    @Override
    public List<Goal> getAllGoals() {
        List<Goal> list = new ArrayList<>();
        try {
            list = getDao().queryForAll();
        } catch (SQLException e) {
            Log.e("Maniak", "GoalRepositoryImpl.getAllGoals() ", e);
        }
        return list;
    }

    @Override
    public void saveGoal(Goal goal) {
        try {
            getDao().createOrUpdate(goal);
        } catch (SQLException e) {
            Log.e("Maniak", "GoalRepositoryImpl.saveGoal() ", e );
        }
    }

    @Override
    public void deleteGoalById(Long id) {
        try {
            getDao().deleteById(id);
        } catch (SQLException e) {
            Log.e("Maniak", "GoalRepositoryImpl.deleteGoalById() ", e);
        }
    }

    private Dao<Goal, Long> getDao() throws SQLException {
        return helper.getGoalsDao();
    }
}
