package movieapp.samsung.com.adapter;


import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;


import com.squareup.picasso.Picasso;
import java.util.ArrayList;
import java.util.HashMap;

import movieapp.samsung.com.R;
import movieapp.samsung.com.model.Genre;
import movieapp.samsung.com.model.Results;
import movieapp.samsung.com.utils.AppConstants;
import movieapp.samsung.com.utils.AppPreferences;


public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MyViewHolder> {

    private ArrayList<Results> movieData;
    private Context mContext;
    HashMap<Integer,String>  genre;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView tvMovieTitle, tvPopularity, tvGenre;
        CardView container;
        ImageView ivPoster;

        public MyViewHolder(View view) {
            super(view);
            tvMovieTitle = (TextView) view.findViewById(R.id.tvTitle);
            container =(CardView)view.findViewById(R.id.cvContainer);
            tvPopularity = (TextView) view.findViewById(R.id.tvPopularity);
            ivPoster =(ImageView) view.findViewById(R.id.ivPoster);
            tvGenre = (TextView) view.findViewById(R.id.tvGenre);
        }

        public void clearAnimation() {
            container.clearAnimation();
        }
    }


    public MovieAdapter(ArrayList<Results> movieData, Context mContext) {
        this.movieData = movieData;
        this.mContext = mContext;
        this.genre= getGenres();
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.movie_item, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {

        holder.tvMovieTitle.setText(movieData.get(position).getTitle());
        holder.tvPopularity.setText(mContext.getString(R.string.popularity)+String.valueOf(movieData.get(position).getPopularity()));

        Picasso.with(mContext).load(AppConstants.IMAGE_BASE_URL + AppConstants.IMAGE_QUALITY + movieData.get(position).getPoster_path()).placeholder(R.drawable.ic_movie_black_24dp).error(R.drawable.ic_movie_black_24dp).into(holder.ivPoster);

        /*
        get cached Genre from Shared preferences
        match them with ids and put that category in string
         */
        StringBuilder sb = new StringBuilder();
        for(int i=0;i<movieData.get(position).getGenre_ids().size();i++){

            sb.append(genre.get(movieData.get(position).getGenre_ids().get(i)));
            sb.append(", ");

        }
        String gn= sb.toString();
        if(gn!=null && gn.length()>3){
            holder.tvGenre.setText(gn.substring(0,gn.length()-2));
        }


        setAnimation(holder.container, position);

    }

    @Override
    public int getItemCount() {
        return movieData.size();
    }


    private void setAnimation(View viewToAnimate, int position)
    {
        // If the bound view wasn't previously displayed on screen, it's animated
            Animation animation = AnimationUtils.loadAnimation(mContext, android.R.anim.slide_in_left);
            viewToAnimate.startAnimation(animation);
    }
    @Override
    public void onViewDetachedFromWindow(MyViewHolder holder) {
        super.onViewDetachedFromWindow(holder);
        (holder).clearAnimation();
    }

    /*
    Method used to put genres date in hashmap
    for easy access of ids against Genre name
     */

    private HashMap<Integer,String> getGenres(){

        HashMap<Integer,String> hm=new HashMap<Integer,String>();
        ArrayList<Genre> genre= AppPreferences.getGenreList(mContext);

        for(Genre gen : genre){

            hm.put(gen.getId(),gen.getName());
        }
        return hm;
    }
}
