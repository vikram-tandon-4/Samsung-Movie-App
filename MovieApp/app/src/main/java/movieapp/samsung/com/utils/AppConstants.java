package movieapp.samsung.com.utils;

/**
 * Created by vikra on 3/15/2018.
 */

public interface AppConstants {

    /*
    Add AUTHORIZATION and API_KEY before running the app
     */
    String AUTHORIZATION ="";
    String API_KEY= "";

    String BASE_URL="http://api.themoviedb.org/";

    /*
    Set dates before the current date for now playing movies
     */
    int DAYS_PRIOR = 15;
    /*
  Set dates after the current date for Upcoming movies
   */
    int DAYS_AHEAD = 45;

    String IMAGE_BASE_URL="http://image.tmdb.org/t/p/";
    String IMAGE_QUALITY="w185/";

    String START_DATE_QUERY="primary_release_date.gte";
    String END_DATE_QUERY="primary_release_date.lte";
    String HEADER_CONTENT_TYPE = "Content-Type: application/x-www-form-urlencoded";
    String HEADER_AUTHORIZATION = "Authorization: Bearer "+ AUTHORIZATION;

    String MOVIE_DATA="movieData";

}
