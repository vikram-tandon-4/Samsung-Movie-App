package movieapp.samsung.com.activitiy;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import movieapp.samsung.com.ApiClient;
import movieapp.samsung.com.R;
import movieapp.samsung.com.interfaces.ApiInterface;
import movieapp.samsung.com.model.FinalData;
import movieapp.samsung.com.model.Genre;
import movieapp.samsung.com.model.GenreResponseModel;
import movieapp.samsung.com.model.MovieDataResponseBean;
import movieapp.samsung.com.utils.AppConstants;
import movieapp.samsung.com.utils.AppPreferences;
import movieapp.samsung.com.utils.Utils;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by vikra on 3/16/2018.
 */

public class SplashActivity extends AppCompatActivity {

    FinalData finalData;
    boolean isNowPlayingDataRecieved = false;
    boolean isUpcomingDataRecieved =false;
    private Context mContext;
    private RelativeLayout noNetworkLayout;
    private Button btnTryAgain;
    private ImageView ivLogo;
    int k=1;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        mContext=this;
        finalData = new FinalData();
        initializeViews();

    }

    /*
    Initializing all the views
     */

    private void initializeViews() {
        noNetworkLayout = (RelativeLayout) findViewById(R.id.noNetworkLayout);

        btnTryAgain = (Button) findViewById(R.id.btnTryAgain);
        ivLogo = (ImageView)findViewById(R.id.ivLogo);

        btnTryAgain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkNetworkCallApi();
            }
        });
        checkNetworkCallApi();
    }

    /*
    check network and call api
     */
    private void checkNetworkCallApi() {

        if (Utils.isNetworkAvailable(mContext)) {
            noNetworkLayout.setVisibility(View.GONE);
            ivLogo.setVisibility(View.VISIBLE);
            if(AppPreferences.isFirstTime(mContext)){
                getMovieGenres();
            }
            callMoviesApi(getDate(AppConstants.DAYS_PRIOR*(-1)),getDate(0), true);
            callMoviesApi(getDate(1),getDate(AppConstants.DAYS_AHEAD), false);
        } else {
            noNetworkLayout.setVisibility(View.VISIBLE);
            ivLogo.setVisibility(View.GONE);
        }
    }

    /*

    Call this method with startdate and end date
    get data from the network call
    Used for both Upcoming movies and Now playing
     */
    private void callMoviesApi(String startDate, String endDate, final boolean isNowPlaying) {

        ApiInterface apiService =
                ApiClient.getClient().create(ApiInterface.class);

        Call<MovieDataResponseBean> call = apiService.getMovies(startDate,endDate);
        call.enqueue(new Callback<MovieDataResponseBean>() {
            @Override
            public void onResponse(Call<MovieDataResponseBean> call, Response<MovieDataResponseBean> response) {

                if( response.body().getResults()!=null &&  response.body().getResults().size()>0) {

                    if (isNowPlaying) {
                        finalData.setNowPlaying(response.body());
                        isNowPlayingDataRecieved=true;
                    }else{
                        finalData.setUpcoming(response.body());
                        isUpcomingDataRecieved =true;
                    }
                }
                checkAllDataRecieved();
            }

            @Override
            public void onFailure(Call<MovieDataResponseBean> call, Throwable t) {
                Log.e("API RESPONSE", t.toString());
                if (isNowPlaying) {
                    isNowPlayingDataRecieved=true;
                }else{
                    isUpcomingDataRecieved =true;
                }
            }
        });
    }

/*
Get movie Genres and cache it in shared preferences
 */
    private void getMovieGenres() {

        ApiInterface apiService =
                ApiClient.getClient().create(ApiInterface.class);
        Call<GenreResponseModel> call = apiService.getGenres(AppConstants.API_KEY);
        call.enqueue(new Callback<GenreResponseModel>() {
            @Override
            public void onResponse(Call<GenreResponseModel> call, Response<GenreResponseModel> response) {

                if(response.body().getGenres()!=null && response.body().getGenres().size()>0){
                    ArrayList<Genre> moviesGenre = response.body().getGenres();
                    AppPreferences.setFirstTime(mContext,false);
                    AppPreferences.setGenreList(mContext,moviesGenre);
                }

            }

            @Override
            public void onFailure(Call<GenreResponseModel> call, Throwable t) {
            }
        });
    }

    /*

    check if data is recieved from both apps
    if yes then proceed to Movie screen
     */
    private void checkAllDataRecieved() {

        if(isUpcomingDataRecieved && isNowPlayingDataRecieved){
            Intent intent = new Intent(SplashActivity.this,MovieActivity.class);
            intent.putExtra(AppConstants.MOVIE_DATA, finalData);
            startActivity(intent);
            finish();
        }
    }

    /*
    get adjusted date in yyy-MM-dd format

     */
    private String getDate(int days){

        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, days);

        SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd");
        return s.format(new Date(cal.getTimeInMillis()));
    }
}
