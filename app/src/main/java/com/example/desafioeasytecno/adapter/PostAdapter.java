package com.example.desafioeasytecno.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.desafioeasytecno.model.Post;
import com.example.desafioeasytecno.R;

import java.util.List;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.ViewHolder> {

    private List<Post> postList;
    private Context context;
    private SelectListener listener;

    public PostAdapter(List<Post> postList, Context context, SelectListener listener) {
        this.postList = postList;
        this.context = context;
        this.listener = listener;
    }

    public void setUpdateList(List<Post> postList){
        this.postList = postList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_post, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Post post = postList.get(position);
        holder.textViewTitle.setText("Title: " + post.getTitle());
        holder.textViewBody.setText("Body: " +post.getBody());
        holder.textViewUserId.setText("User Id: " +post.getUserId());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onItemClicked(post, holder.getAdapterPosition());
            }
        });
    }



    @Override
    public int getItemCount() {
        return postList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView textViewTitle;
        TextView textViewBody;
        TextView textViewUserId;

        public ViewHolder(View itemView) {
            super(itemView);
            textViewTitle = itemView.findViewById(R.id.textViewTitle);
            textViewBody = itemView.findViewById(R.id.textViewBody);
            textViewUserId = itemView.findViewById(R.id.textViewUserId);
        }
    }
}
