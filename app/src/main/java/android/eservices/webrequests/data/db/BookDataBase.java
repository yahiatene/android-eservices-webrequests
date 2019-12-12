package android.eservices.webrequests.data.db;

import android.eservices.webrequests.data.db.dao.BookDAO;
import android.eservices.webrequests.data.db.entity.BookEntity;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {BookEntity.class}, version = 1)
public abstract class BookDataBase extends RoomDatabase {
    public abstract BookDAO bookDAO();
}
