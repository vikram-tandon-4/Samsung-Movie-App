package movieapp.samsung.com.activitiy;

import android.content.Context;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import java.util.ArrayList;

import movieapp.samsung.com.R;
import movieapp.samsung.com.fragment.MovieListFragment;
import movieapp.samsung.com.model.FinalData;
import movieapp.samsung.com.utils.AppConstants;

public class MovieActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private TextView toolBarHeader;
    private Context mContext;
    private FinalData finalData;

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

        finalData = (FinalData) getIntent().getSerializableExtra(AppConstants.MOVIE_DATA);

        viewPager = (ViewPager) findViewById(R.id.viewpager);
        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);

        /*
        *
        * initializing toolbar
        *
        * */
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolBarHeader = (TextView) findViewById(R.id.toolBarHeader);
        toolBarHeader.setText(R.string.movies);
        populateData();

    }

    private void populateData() {

        setupViewPager(viewPager, finalData);
    }


    private void setupViewPager(ViewPager viewPager, FinalData finalData) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());


        adapter.addFragment(MovieListFragment.newInstance(finalData.getNowPlaying().getResults()), getString(R.string.now_playing));
        adapter.addFragment(MovieListFragment.newInstance(finalData.getUpcoming().getResults()), getString(R.string.upcoming_movies));

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
