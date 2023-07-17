package com.saadahmedsoft.springbootecommerce.utils;

import android.content.Context;
import android.widget.Toast;

import androidx.annotation.NonNull;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * This class hase been made by Saad Ahmed on 15-Sep-2022
 * The reason behind making this class to simplify api calls
 */

public class ApiCall {

    /**
     * Interface to get successful response
     * @param <T> is List of Items
     */

    public interface OnResponseGet<T> {
        void onSuccessful(T data);
    }

    /**
     * The function to call api and get response
     * @param context for Toast and Progress dialog
     * @param call will be called
     * @param listener to post the body after successful response
     * @param <T> is List of Items
     */

    public static <T> void enqueue(Context context, Call<T> call, OnResponseGet<T> listener) {
        ProgressDialog progressDialog = ProgressDialog.getInstance(context);
        progressDialog.show();
        call.enqueue(new Callback<T>() {
            @Override
            public void onResponse(@NonNull Call<T> call, @NonNull Response<T> response) {
                if (response.body() != null) {
                    listener.onSuccessful(response.body());
                } else {
                    if (response.errorBody() != null) {
                        Toast.makeText(context, ConstructErrorBody.getMessage(response.errorBody()), Toast.LENGTH_SHORT).show();
                    }
                    else Toast.makeText(context, "An error occurred, try again later", Toast.LENGTH_SHORT).show();
                }
                progressDialog.dismiss();
            }

            @Override
            public void onFailure(@NonNull Call<T> call, @NonNull Throwable t) {
                Toast.makeText(context, t.getMessage(), Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();
            }
        });
    }

    public static <T> void enqueueNoProgress(Context context, Call<T> call, OnResponseGet<T> listener) {
        call.enqueue(new Callback<T>() {
            @Override
            public void onResponse(@NonNull Call<T> call, @NonNull Response<T> response) {
                if (response.body() != null) {
                    listener.onSuccessful(response.body());
                } else {
                    if (response.errorBody() != null) {
                        Toast.makeText(context, ConstructErrorBody.getMessage(response.errorBody()), Toast.LENGTH_SHORT).show();
                    }
                    else Toast.makeText(context, "An error occurred, try again later", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(@NonNull Call<T> call, @NonNull Throwable t) {
                Toast.makeText(context, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
