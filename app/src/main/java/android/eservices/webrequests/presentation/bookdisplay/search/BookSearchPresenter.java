package android.eservices.webrequests.presentation.bookdisplay.search;

import android.eservices.webrequests.data.api.model.BookSearchResponse;
import android.eservices.webrequests.data.repository.search.BookSearchDataRepository;
import android.eservices.webrequests.presentation.bookdisplay.search.mapper.BookToViewModelMapper;


import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

public class BookSearchPresenter implements BookSearchContract.Presenter {

    private BookSearchContract.View view;
    private CompositeDisposable compositeDisposable;
    private BookSearchDataRepository bookSearchDataRepository;
    private BookToViewModelMapper bookToViewModelMapper = new BookToViewModelMapper();

    public BookSearchPresenter(BookSearchDataRepository bookSearchDataRepository) {
        this.compositeDisposable = new CompositeDisposable();
        this.bookSearchDataRepository = bookSearchDataRepository;
    }

    @Override
    public void searchBooks(String keywords) {

        compositeDisposable.add(bookSearchDataRepository.getBooks()
                                    .subscribeOn(Schedulers.io())
                                    .observeOn(AndroidSchedulers.mainThread())
                                    .subscribeWith(new DisposableSingleObserver<BookSearchResponse>() {

                                    @Override
                                    public void onSuccess(BookSearchResponse bookSearchResponse) {
                                        view.displayBooks(bookToViewModelMapper.map(bookSearchResponse.getBookList()));
                                    }

                                    @Override
                                    public void onError(Throwable e) {
                                        // handle the error case
                                    }
                                }));

    }

    @Override
    public void attachView(BookSearchContract.View view) {
        this.view=view;
    }

    @Override
    public void cancelSubscription() {
        this.compositeDisposable.clear();
    }

    @Override
    public void addBookToFavorite(String bookId) {

    }

    @Override
    public void removeBookFromFavorites(String bookId) {

    }

    @Override
    public void detachView() {

        this.view = null;
        compositeDisposable.dispose();

    }
}
