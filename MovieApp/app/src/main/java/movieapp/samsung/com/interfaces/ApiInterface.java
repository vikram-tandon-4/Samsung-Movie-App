package movieapp.samsung.com.interfaces;

import movieapp.samsung.com.model.MovieDataResponseBean;
import movieapp.samsung.com.utils.AppConstants;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;

/**
 * Created by vikra on 8/27/2016.
 */
public interface ApiInterface {

    @Headers({AppConstants.HEADER_CONTENT_TYPE, AppConstants.HEADER_AUTHORIZATION})
    @GET("discover/movie?"+AppConstants.START_DATE_QUERY+ AppConstants.START_DATE_NOW_PLAYING+"&"+AppConstants.END_DATE_QUERY+AppConstants.END_DATE_NOW_PLAYING)
    Call<MovieDataResponseBean> getNowPlayingMovies();

    @Headers({AppConstants.HEADER_CONTENT_TYPE, AppConstants.HEADER_AUTHORIZATION})
    @GET("discover/movie?"+AppConstants.START_DATE_QUERY+ AppConstants.START_DATE_UPCOMING+"&"+AppConstants.END_DATE_QUERY+AppConstants.END_DATE_UPCOMING)
    Call<MovieDataResponseBean> getUpcomingMovies();
}