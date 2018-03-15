package movieapp.samsung.com.activitiy;

import android.app.Dialog;
import android.content.Context;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

import movieapp.samsung.com.ApiClient;
import movieapp.samsung.com.R;
import movieapp.samsung.com.fragment.MovieListFragment;
import movieapp.samsung.com.interfaces.ApiInterface;
import movieapp.samsung.com.model.FinalData;
import movieapp.samsung.com.model.MovieDataResponseBean;
import movieapp.samsung.com.utils.Utils;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private Dialog mProgressDialog;
    private TextView toolBarHeader;
    private RelativeLayout noNetworkLayout;
    private AppBarLayout appBar;
    private Button btnTryAgain;
    private Context mContext;
    private FinalData finalData;
    private String TAG="MOVIE LIST ACTIVITY";

    private boolean isThisDataUpcomingMovies = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie);

        mContext = this;
        initializeViews();
    }


    /*****
     *
     * initialize views
     * *****/


    private void initializeViews() {

        viewPager = (ViewPager) findViewById(R.id.viewpager);
        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);

        noNetworkLayout = (RelativeLayout) findViewById(R.id.noNetworkLayout);
        appBar = (AppBarLayout) findViewById(R.id.appBar);
        btnTryAgain = (Button) findViewById(R.id.btnTryAgain);


        /*
        *
        * initializing toolbar
        *
        * */
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolBarHeader = (TextView) findViewById(R.id.toolBarHeader);

        checkNetworkAndSetData();
    }


    private void checkNetworkAndSetData() {

        if (Utils.isNetworkAvailable(mContext)) {
            noNetworkLayout.setVisibility(View.GONE);
            appBar.setVisibility(View.VISIBLE);
            viewPager.setVisibility(View.VISIBLE);
            mProgressDialog = Utils.showProgressDialog(mContext);
            populateData();

        } else {
            noNetworkLayout.setVisibility(View.VISIBLE);
            appBar.setVisibility(View.GONE);
            viewPager.setVisibility(View.GONE);
        }
    }

    private void populateData() {
   callUpcomingMoviesApi();
        callNowPlayingApi();
    }

    private void callNowPlayingApi() {

        ApiInterface apiService =
                ApiClient.getClient().create(ApiInterface.class);

      Utils.showProgressDialog(mContext);

        Call<MovieDataResponseBean> call = apiService.getNowPlayingMovies();
        call.enqueue(new Callback<MovieDataResponseBean>() {
            @Override
            public void onResponse(Call<MovieDataResponseBean> call, Response<MovieDataResponseBean> response) {
                Utils.cancelProgressDialog(mProgressDialog);

                if( response.body().getResults()!=null &&  response.body().getResults().size()>0)
                response.body().getResults();

            }

            @Override
            public void onFailure(Call<MovieDataResponseBean> call, Throwable t) {
                // Log error here since request failed
               Utils.cancelProgressDialog(mProgressDialog);
                Log.e("API RESPONSE", t.toString());
            }
        });
    }

    private void callUpcomingMoviesApi() {
    }


    private void setupViewPager(ViewPager viewPager, FinalData finalData) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());


            adapter.addFragment(MovieListFragment.newInstance(finalData.getNowPlaying().getResults(),getString(R.string.now_playing)),getString(R.string.now_playing));
        adapter.addFragment(MovieListFragment.newInstance(finalData.getUpcoming().getResults(),getString(R.string.upcoming_movies)),getString(R.string.upcoming_movies));

        viewPager.setAdapter(adapter);
    }

    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final ArrayList<Fragment> mFragmentList = new ArrayList<>();
        private final ArrayList<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }
}
