package com.example.foodrecipe.Listeners;

import com.example.foodrecipe.Models.SimilarRecipeResponse;

import java.util.List;

public interface SimilarRecipiesListener {
    void didFetch(List<SimilarRecipeResponse> response, String message);
    void didError(String message);
}
