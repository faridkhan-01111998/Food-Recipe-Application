package com.example.foodrecipe.Listeners;

import com.example.foodrecipe.Models.InstructionsResponse;

import java.util.List;

public interface InstructionsListner {
    void didFeatch(List<InstructionsResponse> response, String message);
    void didError(String message);
}
