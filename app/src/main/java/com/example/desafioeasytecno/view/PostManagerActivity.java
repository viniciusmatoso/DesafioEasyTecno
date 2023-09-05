package com.example.desafioeasytecno.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.desafioeasytecno.R;
import com.example.desafioeasytecno.controller.PostController;
import com.example.desafioeasytecno.model.Post;

public class PostManagerActivity extends AppCompatActivity {

    TextView textTitle, textBody;
    Button btnSave, btnDelete;
    PostController postController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager_post);

        getSupportActionBar().show();
        getSupportActionBar().setTitle("Gerenciar Post");

        postController = new PostController(this);

        int position = getIntent().getIntExtra("position", 0);
        Post post = (Post) getIntent().getSerializableExtra("post");
        getDataExtras(post);

        getActionButtons(post, position);
    }

    private void getActionButtons(Post post, int position) {
        btnSave = findViewById(R.id.btnSave);
        btnDelete = findViewById(R.id.btnDelete);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Post postUpdated = new Post(post.getId(), textTitle.getText().toString(), textBody.getText().toString(), post.getUserId());
                postController.updatePostInDatabase(postUpdated);
                Intent intent = new Intent();
                intent.putExtra("position", position);
                intent.putExtra("post", post);
                setResult(1, intent);
                finish();
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                postController.deletePostFromDatabase(post.id);
                Intent intent = new Intent();
                intent.putExtra("position", position);
                setResult(2, intent);
                finish();
            }
        });
    }

    private void getDataExtras(Post post) {
        textTitle = findViewById(R.id.editTextTitle);
        textBody = findViewById(R.id.editTextBody);

        textTitle.setText(post.getTitle());
        textBody.setText(post.getBody());
    }
}