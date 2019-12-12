package android.eservices.webrequests.data.repository.search;

import android.eservices.webrequests.data.db.dao.BookDAO;
import android.eservices.webrequests.data.db.entity.BookEntity;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Flowable;

public class BookSearchLocalDataSource {


    private BookDAO bookDAO;

    public BookSearchLocalDataSource(BookDAO bookDAO) {
        this.bookDAO=bookDAO;
    }

    public Flowable<List<BookEntity>> getAllBooks(){
        return this.bookDAO.getAllBooks();
    }

    public Completable insertBook(BookEntity bookEntity){
        return this.bookDAO.insertBook(bookEntity);
    }

    public Completable deleteBook(BookEntity bookEntity){
        return this.bookDAO.deleteBook(bookEntity);
    }


}


