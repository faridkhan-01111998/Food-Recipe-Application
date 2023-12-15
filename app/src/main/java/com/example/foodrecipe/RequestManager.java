package com.example.foodrecipe;

import android.content.Context;

import com.example.foodrecipe.Listeners.InstructionsListner;
import com.example.foodrecipe.Listeners.RandomRecipeResponseListener;
import com.example.foodrecipe.Listeners.RecipeDetailsListner;
import com.example.foodrecipe.Listeners.SimilarRecipiesListener;
import com.example.foodrecipe.Models.InstructionsResponse;
import com.example.foodrecipe.Models.RandomRecieApiResponse;
import com.example.foodrecipe.Models.RecipeDetailsResponse;
import com.example.foodrecipe.Models.SimilarRecipeResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public class RequestManager {
    Context context;
    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://api.spoonacular.com")
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    public RequestManager(Context context) {
        this.context = context;
    }

    public void getRandomRecipies(RandomRecipeResponseListener listener, List<String> tags){
        CallRandomRecipies callRandomRecipies = retrofit.create(CallRandomRecipies.class);

        Call<RandomRecieApiResponse> call = callRandomRecipies.callRandomRecipe(context.getString(R.string.api_key), "10", tags);
        call.enqueue(new Callback<RandomRecieApiResponse>() {
            @Override
            public void onResponse(Call<RandomRecieApiResponse> call, Response<RandomRecieApiResponse> response) {
                if(!response.isSuccessful()){
                    listener.didError(response.message());
                    return;
                }
                listener.didFetch(response.body(), response.message());
            }

            @Override
            public void onFailure(Call<RandomRecieApiResponse> call, Throwable t) {

                listener.didError(t.getMessage());
            }
        });
    }

    public void getRecipeDetails(RecipeDetailsListner listner, int id){
        CallRecipeDetails callRecipeDetails = retrofit.create(CallRecipeDetails.class);
        Call<RecipeDetailsResponse> call = callRecipeDetails.callRecipeDetails(id, context.getString(R.string.api_key));
        call.enqueue(new Callback<RecipeDetailsResponse>() {
            @Override
            public void onResponse(Call<RecipeDetailsResponse> call, Response<RecipeDetailsResponse> response) {
                if (!response.isSuccessful()){
                    listner.didError(response.message());
                    return;
                }
                listner.didFetch(response.body(), response.message());
            }

            @Override
            public void onFailure(Call<RecipeDetailsResponse> call, Throwable t) {

                listner.didError(t.getMessage());
            }
        });
    }

    public void getSimilarRecipies(SimilarRecipiesListener listener, int id){
        CallSimilarRecipies callSimilarRecipies = retrofit.create(CallSimilarRecipies.class);
        Call<List<SimilarRecipeResponse>> call = callSimilarRecipies.callSimilarRecipe(id, "10", context.getString(R.string.api_key));
        call.enqueue(new Callback<List<SimilarRecipeResponse>>() {
            @Override
            public void onResponse(Call<List<SimilarRecipeResponse>> call, Response<List<SimilarRecipeResponse>> response) {
                if(!response.isSuccessful()){
                    listener.didError(response.message());
                    return;
                }
                listener.didFetch(response.body(), response.message());
            }

            @Override
            public void onFailure(Call<List<SimilarRecipeResponse>> call, Throwable t) {
                listener.didError(t.getMessage());
            }
        });
    }

    public void getInstructions(InstructionsListner listner, int id){
        CallInstructions callInstructions = retrofit.create(CallInstructions.class);
        Call<List<InstructionsResponse>> call = callInstructions.callInstructions(id, context.getString(R.string.api_key));
        call.enqueue(new Callback<List<InstructionsResponse>>() {
            @Override
            public void onResponse(Call<List<InstructionsResponse>> call, Response<List<InstructionsResponse>> response) {
                if(!response.isSuccessful()) {
                    listner.didError(response.message());
                    return;
                }
                listner.didFeatch(response.body(),response.message());
            }

            @Override
            public void onFailure(Call<List<InstructionsResponse>> call, Throwable t) {
                listner.didError(t.getMessage());
            }
        });
    }


       private interface CallRandomRecipies{
        @GET("recipes/random")
        Call<RandomRecieApiResponse> callRandomRecipe(
                @Query("apiKey") String apiKey,
                @Query("number") String number,
                @Query("tags")List<String> tags
                );
    }

    private interface CallRecipeDetails{
        @GET("recipes/{id}/information")
        Call<RecipeDetailsResponse> callRecipeDetails(
                @Path("id") int id,
                @Query("apiKey") String apiKey
        );
    }

    private interface CallSimilarRecipies{
        @GET("recipes/{id}/similar")
        Call<List<SimilarRecipeResponse>> callSimilarRecipe(
                @Path("id") int id,
                @Query("number") String number,
                @Query("apiKey") String apiKe
        );
    }

    private interface CallInstructions{
        @GET("recipes/{id}/analyzedInstructions")
        Call<List<InstructionsResponse>> callInstructions(
                @Path("id") int id,
                @Query("apiKey") String apiKey
        );
    }
}
