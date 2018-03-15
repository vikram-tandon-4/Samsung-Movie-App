package movieapp.samsung.com.utils;

/**
 * Created by vikra on 3/15/2018.
 */

public interface AppConstants {

    String AUTHORIZATION ="";

    String START_DATE_UPCOMING ="2018-03-16";
    String END_DATE_UPCOMING ="2018-03-30";
    String START_DATE_NOW_PLAYING ="2018-03-01";
    String END_DATE_NOW_PLAYING ="2018-03-15";
    String BASE_URL="http://api.themoviedb.org/4/";
    String START_DATE_QUERY="primary_release_date.gte=";
    String END_DATE_QUERY="primary_release_date.lte=";
    String HEADER_CONTENT_TYPE = "Content-Type: application/x-www-form-urlencoded";
    String HEADER_AUTHORIZATION = "Authorization: Bearer "+ AUTHORIZATION;

}
