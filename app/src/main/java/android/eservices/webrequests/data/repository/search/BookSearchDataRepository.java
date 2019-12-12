package android.eservices.webrequests.data.repository.search;


import android.eservices.webrequests.data.api.model.Book;
import android.eservices.webrequests.data.api.model.BookSearchResponse;
import android.eservices.webrequests.data.db.entity.BookEntity;


import androidx.room.TypeConverter;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.CompletableSource;
import io.reactivex.Flowable;
import io.reactivex.Single;
import io.reactivex.functions.Function;

public class BookSearchDataRepository {


    private BookSearchRemoteDataSource bookSearchRemoteDataSource;
    private BookSearchLocalDataSource bookSearchLocalDataSource;

    public BookSearchDataRepository(BookSearchRemoteDataSource bookSearchRemoteDataSource) {
        this.bookSearchRemoteDataSource=bookSearchRemoteDataSource;
    }

    public Single<BookSearchResponse> getBooks(){
        return this.bookSearchRemoteDataSource.getBooks();
    }


    public Flowable<List<BookEntity>> getAllBooks(){
        return this.bookSearchLocalDataSource.getAllBooks();
    }

    public Completable addBookToFavorite(String id){
        return this.bookSearchRemoteDataSource.getBook(id).flatMapCompletable(new Function<Book, CompletableSource>() {
            @Override
            public CompletableSource apply(Book book) throws Exception {
                return bookSearchLocalDataSource.insertBook();
            }
        });
    }

    public Completable deleteBookFromFavorite(String id){
        return bookSearchLocalDataSource.deleteBook();
    }



}
