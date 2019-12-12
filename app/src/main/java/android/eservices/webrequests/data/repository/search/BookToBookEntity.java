package android.eservices.webrequests.data.repository.search;

import android.eservices.webrequests.data.api.model.Book;
import android.eservices.webrequests.data.db.entity.BookEntity;
import android.text.TextUtils;

public class BookToBookEntity {

    private BookEntity bookEntity;

    public BookToBookEntity(Book book){

        bookEntity = new BookEntity();

        this.bookEntity.setBookAuthors(TextUtils.join(" ",book.getVolumeInfo().getAuthorList()));
        this.bookEntity.setBookId(book.getId());
        this.bookEntity.setBookTitle(book.getVolumeInfo().getTitle());

    }



}
