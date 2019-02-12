package pl.maniak.mygoals.repository.history;

import android.util.Log;

import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import lombok.RequiredArgsConstructor;
import pl.maniak.mygoals.model.History;
import pl.maniak.mygoals.repository.DBHelper;

@RequiredArgsConstructor
public class HistoryRepositoryImpl implements HistoryRepository {

    private final DBHelper helper;

    @Override
    public List<History> getAllHistory() {
        List<History> list = new ArrayList<>();
        try {
            list = getDao().queryForAll();
        } catch (SQLException e) {
            Log.e("Maniak", "HistoryRepositoryImpl.getAllHistory() ", e);
        }
        return list;
    }

    @Override
    public void saveHistory(History history) {
        try {
            getDao().createOrUpdate(history);
        } catch (SQLException e) {
            Log.e("Maniak", "HistoryRepositoryImpl.saveHistory() ", e);
        }
    }

    @Override
    public void deleteHistoryById(Long id) {
        try {
            getDao().deleteById(id);
        } catch (SQLException e) {
            Log.e("Maniak", "HistoryRepositoryImpl.deleteHistoryById() ", e);
        }
    }

    private Dao<History, Long> getDao() throws SQLException {
        return helper.getHistoryDao();
    }
}