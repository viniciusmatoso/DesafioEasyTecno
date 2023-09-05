package com.example.desafioeasytecno.network;

import static com.example.desafioeasytecno.network.ApiClient.BASE_URL;
import com.example.desafioeasytecno.model.Post;

import java.util.List;
import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiService {
        @GET(BASE_URL)
        Call<List<Post>> getPosts();
}