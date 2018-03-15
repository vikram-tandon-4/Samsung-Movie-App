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
import movieapp.samsung.com.model.Results;


public class MovieListFragment extends Fragment {

    private TextView tvNoData;
    private View view;
    private RecyclerView rvMovieList;
    private static String MOVIES ="movies";
    private static String TITLE_HEADING = "TitleHeading";

public static MovieListFragment newInstance(ArrayList<Results> movies, String title) {
    MovieListFragment fragment = new MovieListFragment();
    Bundle bundleMessageList = new Bundle();
    bundleMessageList.putSerializable(MOVIES, movies);
    bundleMessageList.putString(TITLE_HEADING, title);
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
        ArrayList<String> messagesList = new ArrayList<>();
        messagesList= (ArrayList<String>) getArguments().get(MOVIES);
        if (messagesList != null && messagesList.size()>0) {
            RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
            rvMovieList.setLayoutManager(mLayoutManager);
            rvMovieList.setItemAnimator(new DefaultItemAnimator());
//            MessageListAdapter mAdapter = new MessageListAdapter(messagesList,getActivity(),);
//            rvMovieList.setAdapter(mAdapter);
        } else {
            tvNoData.setVisibility(View.VISIBLE);
        }
    }
}
