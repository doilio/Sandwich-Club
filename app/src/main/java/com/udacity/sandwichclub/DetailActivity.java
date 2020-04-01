package com.udacity.sandwichclub;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.view.View;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.udacity.sandwichclub.databinding.ActivityDetailBinding;
import com.udacity.sandwichclub.model.Sandwich;
import com.udacity.sandwichclub.utils.JsonUtils;

import org.json.JSONException;

public class DetailActivity extends AppCompatActivity {

    public static final String EXTRA_POSITION = "extra_position";
    private static final int DEFAULT_POSITION = -1;
    private ActivityDetailBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_detail);

        Intent intent = getIntent();
        if (intent == null) {
            closeOnError();
        }

        int position = intent.getIntExtra(EXTRA_POSITION, DEFAULT_POSITION);
        if (position == DEFAULT_POSITION) {
            // EXTRA_POSITION not found in intent
            closeOnError();
            return;
        }

        String[] sandwiches = getResources().getStringArray(R.array.sandwich_details);
        String json = sandwiches[position];
        Sandwich sandwich = null;
        try {
            sandwich = JsonUtils.parseSandwichJson(json);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        if (sandwich == null) {
            // Sandwich data unavailable
            closeOnError();
            return;
        }

        populateUI(sandwich);
        Picasso.get()
                .load(sandwich.getImage())
                .into(binding.imageIv);

        setTitle(sandwich.getMainName());
    }

    private void closeOnError() {
        finish();
        Toast.makeText(this, R.string.detail_error_message, Toast.LENGTH_SHORT).show();
    }

    private void populateUI(Sandwich sandwich) {

        binding.descriptionTv.setText(sandwich.getDescription());
        binding.originTv.setText(sandwich.getPlaceOfOrigin());

        for (String ingredient : sandwich.getIngredients()) {
            binding.ingredientsTv.append("* " + ingredient + "\n");
        }

        for (String name : sandwich.getAlsoKnownAs()) {
            binding.alsoKnownTv.append("- " + name + "\n");
        }

        // Set visibility of Also Known's CardView
        if (sandwich.getAlsoKnownAs().isEmpty()){
            binding.cardview4.setVisibility(View.GONE);
        }

        // Set visibility of Place of Origin's CardView
        if (sandwich.getPlaceOfOrigin().isEmpty()){
            binding.cardview5.setVisibility(View.GONE);
        }

    }
}
