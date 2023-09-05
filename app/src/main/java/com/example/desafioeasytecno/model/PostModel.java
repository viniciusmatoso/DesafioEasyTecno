package com.example.desafioeasytecno.model;

import android.content.Context;
import android.util.Log;

import com.example.desafioeasytecno.database.AppDatabase;
import com.example.desafioeasytecno.network.ApiClient;
import com.example.desafioeasytecno.network.ApiService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PostModel {
    private String id;
    private String title;
    private String body;
    private String userId;

    private PostDao postDao;
    private ApiService apiService;
    private AppDatabase db;
    List<Post> posts;
    Context context;

    public PostModel(Context context){
        db = AppDatabase.getInstance(context);
        postDao = db.postDao();
        posts = new ArrayList<>();
        this.context = context;
    }

    public List<Post> getAllPosts(){
        return postDao.getAllPosts();
    }

    public void updatePostInDatabase(Post post) {
        postDao.updatePost(post);
    }

    public void deletePostFromDatabase(long id) {
        AppDatabase db = AppDatabase.getInstance(context);
        postDao = db.postDao();
        postDao.deletePost(id);
    }

    public void insertAllPosts() {
        apiService = ApiClient.getApiService();
        Call<List<Post>> call = apiService.getPosts();

        call.enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                if (response.isSuccessful()) {
                    posts = response.body();

                    if(postDao.getAllPosts().isEmpty() || postDao.getAllPosts() == null){
                        postDao.insertAllPosts(posts);
                    }

                } else {
                    Log.d("Erro", this.toString());
                }
            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {
                Log.d("Erro", this.toString());
            }
        });
    }
}

