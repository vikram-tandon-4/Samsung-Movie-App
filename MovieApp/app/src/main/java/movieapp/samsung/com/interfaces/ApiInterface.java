package movieapp.samsung.com.interfaces;

import movieapp.samsung.com.model.GenreResponseModel;
import movieapp.samsung.com.model.MovieDataResponseBean;
import movieapp.samsung.com.utils.AppConstants;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;

/**
 * Created by vikra on 8/27/2016.
 */
public interface ApiInterface {

    @Headers({AppConstants.HEADER_CONTENT_TYPE, AppConstants.HEADER_AUTHORIZATION})
    @GET("4/discover/movie")
    Call<MovieDataResponseBean> getMovies(@Query(AppConstants.START_DATE_QUERY) String query,@Query(AppConstants.END_DATE_QUERY) String query2);

    @GET("3/genre/movie/list")
    Call<GenreResponseModel> getGenres(@Query("api_key") String apiKey);
}