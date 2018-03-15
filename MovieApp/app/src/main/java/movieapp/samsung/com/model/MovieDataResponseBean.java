package movieapp.samsung.com.model;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by vikra on 3/15/2018.
 */

public class MovieDataResponseBean implements Serializable {

    ArrayList<Results> results;

    public ArrayList<Results> getResults() {
        return results;
    }

    public void setResults(ArrayList<Results> results) {
        this.results = results;
    }
}
