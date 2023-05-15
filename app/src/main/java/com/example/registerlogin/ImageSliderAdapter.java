package com.example.registerlogin;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ImageSliderAdapter extends RecyclerView.Adapter<ImageSliderAdapter.ViewHolder> {
    private Context context;

    // Add your website URLs here
    private String[] urls = {"https://kidshyundai.tistory.com/902", "https://www.newspenguin.com/news/articleView.html?idxno=13163", "https://intlweloveu.org/ko/2021wed-bebravechallenge/"};

    // Add your image resource IDs here
    private int[] imageIds = {R.drawable.image1, R.drawable.image2, R.drawable.image3};

    public ImageSliderAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.image_slider_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.imageView.setImageResource(imageIds[position % imageIds.length]);
        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Open the corresponding website in a browser
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(urls[position % urls.length]));
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        // Return a very large number to enable infinite scrolling
        return Integer.MAX_VALUE;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.image_view);
        }
    }
}

