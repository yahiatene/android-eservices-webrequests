package android.eservices.webrequests.data.api.model;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface BookService {

    @GET("volumes")
    Single<BookSearchResponse> getBookSearchResponse(@Query("q") String keywords, @Query("key") String apiKey);

    @GET("volumes/{volumeId}")
    Single<Book> getBookDetails(@Path("volumeId") String id, @Query("key") String apiKey);


    // presenter attend un completable (c'est fait ou pas) c'est fonctionnel, le coté technique est gere par le SearchDataRepository
}
