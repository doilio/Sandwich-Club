package com.udacity.sandwichclub.utils;

import android.util.Log;

import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JsonUtils {

    private static final String TAG = JsonUtils.class.getSimpleName();

    public static Sandwich parseSandwichJson(String json) throws JSONException {
        Log.d(TAG, "parseSandwichJson: " + json);

        JSONObject sandwish = new JSONObject(json);
        JSONObject name = sandwish.getJSONObject("name");

        String mainName = name.getString("mainName");
        JSONArray alsoKnownAs = name.getJSONArray("alsoKnownAs");
        List<String> alsoKnownAsList = new ArrayList<>();

        if (alsoKnownAs.length() > 0) {
            for (int i = 0; i < alsoKnownAs.length(); i++) {
                alsoKnownAsList.add(alsoKnownAs.get(i).toString());
            }
        }

        Log.d(TAG, "parseSandwichJson: " + mainName);

        String placeOfOrigin = sandwish.getString("placeOfOrigin");
        Log.d(TAG, "parseSandwichJson: " + placeOfOrigin);

        String description = sandwish.getString("description");
        Log.d(TAG, "parseSandwichJson: " + description);

        String imageUrl = sandwish.getString("image");
        Log.d(TAG, "parseSandwichJson: " + imageUrl);

        JSONArray ingredients = sandwish.getJSONArray("ingredients");
        List<String> ingredientsList = new ArrayList<>();

        if (ingredients.length() > 0) {
            for (int i = 0; i < ingredients.length(); i++) {
                ingredientsList.add(ingredients.get(i).toString());
            }
        }


        return new Sandwich(mainName, alsoKnownAsList, placeOfOrigin, description, imageUrl, ingredientsList);
    }


}
