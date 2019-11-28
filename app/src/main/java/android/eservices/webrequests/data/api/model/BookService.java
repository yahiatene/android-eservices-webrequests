package android.eservices.webrequests.data.api.model;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface BookService {

    @GET("volumes")
    Single<BookSearchResponse> getBookSearchResponse(@Query("q") String keywords, @Query("key") String apiKey);

}
