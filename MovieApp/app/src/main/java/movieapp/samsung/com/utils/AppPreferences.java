package movieapp.samsung.com.utils;


import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import movieapp.samsung.com.model.Genre;

public class AppPreferences {

    private static String GENRE_LIST = "genreList";
    private static String FIRST_TIME = "firstTime";



    /*
    *
    * Saving and Retrieving Genre List
    * */

    public static void setGenreList(Context context, ArrayList<Genre> list) {
        Gson gson = new Gson();
        String json = gson.toJson(list);
        SharedPreferences _sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = _sharedPreferences.edit();
        editor.putString(GENRE_LIST, json);
        editor.commit();
    }

    public static ArrayList<Genre> getGenreList(Context context) {
        Gson gson = new Gson();
        ArrayList<Genre> genreList;
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(context);
        String jsonPreferences = sharedPref.getString(GENRE_LIST, "");

        Type type = new TypeToken<List<Genre>>() {
        }.getType();
        genreList = gson.fromJson(jsonPreferences, type);

        return genreList;
    }

    /*
*
* To save data for the first time
*
*
* */
    public static boolean isFirstTime(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context).getBoolean(FIRST_TIME, true);
    }

    public static void setFirstTime(Context context, boolean gender) {
        SharedPreferences _sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = _sharedPreferences.edit();
        editor.putBoolean(FIRST_TIME, gender);
        editor.commit();
    }
}
