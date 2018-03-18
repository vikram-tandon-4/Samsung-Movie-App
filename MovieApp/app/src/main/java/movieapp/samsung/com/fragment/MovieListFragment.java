package movieapp.samsung.com.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import movieapp.samsung.com.R;
import movieapp.samsung.com.adapter.MovieAdapter;
import movieapp.samsung.com.model.Results;


public class MovieListFragment extends Fragment {

    private TextView tvNoData;
    private View view;
    private RecyclerView rvMovieList;
    private static String MOVIES ="movies";

    public static MovieListFragment newInstance(ArrayList<Results> movies) {
        MovieListFragment fragment = new MovieListFragment();
        Bundle bundleMessageList = new Bundle();
        bundleMessageList.putSerializable(MOVIES, movies);
        fragment.setArguments(bundleMessageList);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_movie, container, false);
        tvNoData = (TextView) view.findViewById(R.id.tvNoData);
        rvMovieList = (RecyclerView) view.findViewById(R.id.rvMovieList);
        tvNoData.setText(R.string.no_data_available);
          setupMovieList();
        return view;
    }
/****
 *
 * populate list
 *
 * ****/
    private void setupMovieList() {
        ArrayList<Results> moviesList = new ArrayList<>();
        moviesList= (ArrayList<Results>) getArguments().get(MOVIES);
        if (moviesList != null && moviesList.size()>0) {
            RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
            rvMovieList.setLayoutManager(mLayoutManager);
            rvMovieList.setItemAnimator(new DefaultItemAnimator());
            MovieAdapter mAdapter = new MovieAdapter(moviesList,getActivity());
            rvMovieList.setAdapter(mAdapter);
        } else {
            tvNoData.setVisibility(View.VISIBLE);
        }
    }
}
