package android.eservices.webrequests.data.di;

import android.content.Context;
import android.eservices.webrequests.data.api.model.BookService;
import android.eservices.webrequests.data.db.BookDataBase;
import android.eservices.webrequests.data.repository.search.BookSearchDataRepository;
import android.eservices.webrequests.data.repository.search.BookSearchRemoteDataSource;

import androidx.room.Room;

import com.facebook.stetho.okhttp3.StethoInterceptor;
import com.google.gson.Gson;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Please never do that in a production app. Ever.
 * For the purpose of our course, this is the best option to cover interesting topics as
 * we don't have time to dig into Dependency Injection frameworks such as the famous Dagger.
 * Singleton are compulsory for some classes, such as the one here. If you don't know why, then ask me.
 * Note that this god object doesn't handle Scopes nor component lifecycles so this really shouldn't be
 * the way to go when you master the craft of your software.
 */
public class FakeDependencyInjection {

    private static Retrofit retrofit;
    private static Gson gson;
    private static Context applicationContext;
    private static BookService bookService;
    private static BookSearchRemoteDataSource bookSearchRemoteDataSource;
    private static BookSearchDataRepository bookSearchDataRepository;
    private static BookDataBase bookDataBase;

    public static BookSearchDataRepository getBookRepository(){
        if (bookSearchDataRepository == null) {
            bookSearchDataRepository = new BookSearchDataRepository(getBookDataSource());
        }
        return bookSearchDataRepository;
    }

    public static BookSearchRemoteDataSource getBookDataSource(){
        if (bookSearchRemoteDataSource == null) {
            bookSearchRemoteDataSource = new BookSearchRemoteDataSource(getBookService());
        }
        return bookSearchRemoteDataSource;
    }

    public static BookService getBookService(){
        if (bookService == null) {
            bookService = getRetrofit().create(BookService.class);
        }
        return bookService;
    }

    public static Retrofit getRetrofit() {
        if (retrofit == null) {
            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            OkHttpClient client = new OkHttpClient.Builder()
                    .addInterceptor(interceptor)
                    .addNetworkInterceptor(new StethoInterceptor())
                    .build();

            retrofit = new Retrofit.Builder()
                    .baseUrl("https://www.googleapis.com/books/v1/")
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .client(client)
                    .addConverterFactory(GsonConverterFactory.create(getGson()))
                    .build();
        }
        return retrofit;
    }

    public static Gson getGson() {
        if (gson == null) {
            gson = new Gson();
        }
        return gson;
    }

    public static void setContext(Context context) {
        applicationContext = context;
    }

    public static BookDataBase getBookDatabase(){

        if (bookDataBase == null) {
            bookDataBase = Room.databaseBuilder(applicationContext.getApplicationContext(),
                    BookDataBase.class, "database-name").build();
        }

        return bookDataBase;
    }

}
