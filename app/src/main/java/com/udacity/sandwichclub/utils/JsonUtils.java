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
    private static final String NAME = "name";
    private static final String MAIN_NAME = "mainName";
    private static final String ALSO_KNOWN_AS = "alsoKnownAs";
    private static final String PLACE_OF_ORIGIN = "placeOfOrigin";
    private static final String DESCRIPTION = "description";
    private static final String IMAGE = "image";
    private static final String INGREDIENTS = "ingredients";

    public static Sandwich parseSandwichJson(String json) throws JSONException {
        Log.d(TAG, "parseSandwichJson: " + json);

        JSONObject sandwish = new JSONObject(json);
        JSONObject name = sandwish.getJSONObject(NAME);

        String mainName = name.getString(MAIN_NAME);
        JSONArray alsoKnownAs = name.getJSONArray(ALSO_KNOWN_AS);
        List<String> alsoKnownAsList = new ArrayList<>();

        if (alsoKnownAs.length() > 0) {
            for (int i = 0; i < alsoKnownAs.length(); i++) {
                alsoKnownAsList.add(alsoKnownAs.get(i).toString());
            }
        }

        Log.d(TAG, "parseSandwichJson: " + mainName);

        String placeOfOrigin = sandwish.getString(PLACE_OF_ORIGIN);
        Log.d(TAG, "parseSandwichJson: " + placeOfOrigin);

        String description = sandwish.getString(DESCRIPTION);
        Log.d(TAG, "parseSandwichJson: " + description);

        String imageUrl = sandwish.getString(IMAGE);
        Log.d(TAG, "parseSandwichJson: " + imageUrl);

        JSONArray ingredients = sandwish.getJSONArray(INGREDIENTS);
        List<String> ingredientsList = new ArrayList<>();

        if (ingredients.length() > 0) {
            for (int i = 0; i < ingredients.length(); i++) {
                ingredientsList.add(ingredients.get(i).toString());
            }
        }


        return new Sandwich(mainName, alsoKnownAsList, placeOfOrigin, description, imageUrl, ingredientsList);
    }


}
