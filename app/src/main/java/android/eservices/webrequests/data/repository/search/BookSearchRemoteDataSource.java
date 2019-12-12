package android.eservices.webrequests.data.repository.search;

import android.eservices.webrequests.BookApplication;
import android.eservices.webrequests.data.api.model.Book;
import android.eservices.webrequests.data.api.model.BookSearchResponse;
import android.eservices.webrequests.data.api.model.BookService;

import io.reactivex.Single;

public class BookSearchRemoteDataSource {

    private BookService bookService;

    public BookSearchRemoteDataSource(BookService bookService) {
        this.bookService=bookService;
    }

    public Single<BookSearchResponse> getBooks(){
        return this.bookService.getBookSearchResponse("book", BookApplication.API_KEY);
    }

    public Single<Book> getBook(String id){
        return this.bookService.getBookDetails(id, BookApplication.API_KEY);
    }
}
