package pl.maniak.mygoals.repository.history;

import java.util.List;

import pl.maniak.mygoals.model.History;

public interface HistoryRepository {
    List<History> getAllHistory();
    void saveHistory(History history);
    void deleteHistoryById(Long id);
}