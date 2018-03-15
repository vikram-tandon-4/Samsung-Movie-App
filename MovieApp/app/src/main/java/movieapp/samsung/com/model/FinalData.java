package movieapp.samsung.com.model;

import java.io.Serializable;

/**
 * Created by vikra on 3/15/2018.
 */

public class FinalData implements Serializable {

    MovieDataResponseBean upcoming;
    MovieDataResponseBean nowPlaying;

    public MovieDataResponseBean getUpcoming() {
        return upcoming;
    }

    public void setUpcoming(MovieDataResponseBean upcoming) {
        this.upcoming = upcoming;
    }

    public MovieDataResponseBean getNowPlaying() {
        return nowPlaying;
    }

    public void setNowPlaying(MovieDataResponseBean nowPlaying) {
        this.nowPlaying = nowPlaying;
    }
}
