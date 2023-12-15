package com.example.foodrecipe.Listeners;

import com.example.foodrecipe.Models.RandomRecieApiResponse;

public interface RandomRecipeResponseListener {

    void didFetch(RandomRecieApiResponse response, String message);
    void didError(String message);
}
