package android.eservices.webrequests.data.repository.search;


import android.eservices.webrequests.data.api.model.BookSearchResponse;


import io.reactivex.Single;

public class BookSearchDataRepository {


    private BookSearchRemoteDataSource bookSearchRemoteDataSource;

    public BookSearchDataRepository(BookSearchRemoteDataSource bookSearchRemoteDataSource) {
        this.bookSearchRemoteDataSource=bookSearchRemoteDataSource;
    }

    public Single<BookSearchResponse> getBooks(){
        return this.bookSearchRemoteDataSource.getBooks();
    }
}
