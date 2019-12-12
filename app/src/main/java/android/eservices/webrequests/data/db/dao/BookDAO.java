package android.eservices.webrequests.data.db.dao;

import android.eservices.webrequests.data.db.entity.BookEntity;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;


import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Flowable;

@Dao
public interface BookDAO {

    @Query("SELECT * FROM BookEntity")
    Flowable<List<BookEntity>> getAllBooks();

    @Insert
    Completable insertBook(BookEntity bookEntity);

    @Delete
    Completable deleteBook(BookEntity bookEntity);
}
