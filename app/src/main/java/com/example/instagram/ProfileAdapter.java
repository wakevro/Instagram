package com.example.instagram;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.example.instagram.Fragments.PostsFragment;
import com.parse.ParseFile;

import org.parceler.Parcels;

import java.util.Date;
import java.util.List;



public class ProfileAdapter extends RecyclerView.Adapter<ProfileAdapter.ViewHolder> {

    private Context context;
    private List<Post> posts;


    public ProfileAdapter(Context context, List<Post> posts) {
        this.context = context;
        this.posts = posts;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.profile_item_post, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Post post = posts.get(position);
        holder.bind(post);
    }

    @Override
    public int getItemCount() {
        return posts.size();
    }

    // Clean all elements of the recycler
    public void clear() {
        posts.clear();
        notifyDataSetChanged();
    }

    // Add a list of items -- change to type used
    public void addAll(List<Post> post) {
        posts.addAll(post);
        notifyDataSetChanged();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView ivProfileFeedImage;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            ivProfileFeedImage = itemView.findViewById(R.id.ivProfileFeedImage);

            itemView.setOnClickListener((v) ->  {
                int position = getAdapterPosition();
                if (position != RecyclerView.NO_POSITION) {
                    Post post = posts.get(position);

                    Intent intent = new Intent(context, DetailsActivity.class);

                    intent.putExtra(Post.class.getSimpleName(), Parcels.wrap(post));

                    context.startActivity(intent);
                }
            });
        }

        public void bind(Post post) {

            ParseFile image = post.getImage();
            ParseFile profilePicture = post.getProfilePicture();
            if (image != null) {
                Glide.with(context).load(image.getUrl()).into(ivProfileFeedImage);

            }

        }
    }
}
