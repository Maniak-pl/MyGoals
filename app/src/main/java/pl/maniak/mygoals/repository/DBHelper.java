package pl.maniak.mygoals.repository;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;

import pl.maniak.mygoals.model.Goal;

public class DBHelper extends OrmLiteSqliteOpenHelper {

    private static final String DATABASE_NAME = "MyGoals-Database";
    private static final int DATABASE_VERSION = 1;

    private Dao<Goal, Long> goalsDao = null;

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database, ConnectionSource connectionSource) {
        try {
            TableUtils.createTable(connectionSource, Goal.class);
        } catch (SQLException e) {
            Log.e("Maniak", "DBHelper.onCreate() ", e);
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, ConnectionSource connectionSource, int oldVersion, int newVersion) {
        try {
            TableUtils.dropTable(connectionSource, Goal.class, true);
            onCreate(database, connectionSource);
        } catch (SQLException e) {
            Log.e("Maniak", "DBHelper.onUpgrade() ", e);
        }
    }

    public Dao<Goal, Long> getGoalsDao() throws SQLException {
        if(goalsDao == null) {
            goalsDao = getDao(Goal.class);
        }
        return goalsDao;
    }
}
