package com.example.desafioeasytecno.model;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface PostDao {

    @Query("SELECT * FROM posts")
    List<Post> getAllPosts();

    @Insert
    void insertPost(Post post);

    @Insert
    void insertAllPosts(List<Post> posts);

    @Update
    void updatePost(Post post);

    @Query("DELETE FROM posts WHERE id = :id")
    void deletePost(long id);


}
