package com.example.desafioeasytecno.controller;

import android.content.Context;

import com.example.desafioeasytecno.model.Post;
import com.example.desafioeasytecno.model.PostModel;
import java.util.List;
public class PostController {
    private PostModel postModel;
    Context context;

    public PostController(Context context){
        this.context = context;
        postModel = new PostModel(context);
    }

    public void insertAllPosts() {
        postModel.insertAllPosts();
    }

    public List<Post> getAllPosts(){
        return postModel.getAllPosts();
    }

    public void updatePostInDatabase(Post post) {
        postModel.updatePostInDatabase(post);
    }

    public void deletePostFromDatabase(long id) {
        postModel.deletePostFromDatabase(id);
    }
}
