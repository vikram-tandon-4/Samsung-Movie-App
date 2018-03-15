package movieapp.samsung.com;

import movieapp.samsung.com.utils.AppConstants;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by vikra on 8/27/2016.
 */
public class ApiClient {

    private static Retrofit retrofit = null;


    public static Retrofit getClient() {
        if (retrofit==null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(AppConstants.BASE_URL)
                  .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }



}