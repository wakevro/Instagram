package com.example.instagram;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import org.parceler.Parcels;
import org.w3c.dom.Text;

import java.util.Date;

public class DetailsActivity extends AppCompatActivity {

    Post post;
    TextView tvDetailsUsername;
    TextView tvDetailsDescription;
    TextView tvDetailsCreatedAt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        post = (Post) Parcels.unwrap(getIntent().getParcelableExtra(Post.class.getSimpleName()));

        Date createdAt = post.getCreatedAt();
        String timeAgo = Post.calculateTimeAgo(createdAt);

        tvDetailsUsername = findViewById(R.id.tvDetailsUsername);
        tvDetailsDescription = findViewById(R.id.tvDetailsDescription);
        tvDetailsCreatedAt = findViewById(R.id.tvDetailsCreatedAt);

        tvDetailsUsername.setText(post.getUser().getUsername());
        tvDetailsDescription.setText(post.getDescription());
        tvDetailsCreatedAt.setText(timeAgo);



    }
}