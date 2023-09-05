package com.example.desafioeasytecno.view;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.example.desafioeasytecno.adapter.PostAdapter;
import com.example.desafioeasytecno.adapter.SelectListener;
import com.example.desafioeasytecno.controller.PostController;
import com.example.desafioeasytecno.R;
import com.example.desafioeasytecno.model.Post;

public class MainActivity extends AppCompatActivity implements SelectListener {

    private RecyclerView recyclerView;
    private PostAdapter adapter;
    private PostController postController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().setTitle("Posts");

        instances();
        adapterList();
    }

    private void adapterList() {
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new PostAdapter(postController.getAllPosts(), this, this);
        recyclerView.setAdapter(adapter);
    }

    private void instances() {
        postController = new PostController(this);
    }


    @Override
    public void onItemClicked(Post post, int position) {
        Intent intent = new Intent(this, PostManagerActivity.class);
        intent.putExtra("position", position);
        intent.putExtra("post", post);
        startActivityForResult(intent, 0);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == 1) {
            int position = data.getIntExtra("position", -1);
            Post post = (Post) data.getSerializableExtra("post");
            if(position != -1){
                adapter.notifyItemChanged(position, post);
                adapter.setUpdateList(postController.getAllPosts());
            }
        }

        if (resultCode == 2) {
            int position = data.getIntExtra("position", -1);
            if(position != -1){
                adapter.notifyItemRemoved(position);
                postController.getAllPosts();
            }
        }
    }
}

