package com.sultanofcardio.resumebuildersultanofcardio.adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;
import com.sultanofcardio.resumebuildersultanofcardio.R;
import com.sultanofcardio.resumebuildersultanofcardio.activities.ResumeDetailActivity;
import com.sultanofcardio.resumebuildersultanofcardio.models.Resume;
import com.sultanofcardio.resumebuildersultanofcardio.util.ResumeUtils;
import com.sultanofcardio.resumebuildersultanofcardio.util.ScreenUtils;

import java.io.File;
import java.util.List;
import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Handler;

/**
 * @author sultanofcardio
 */

public class SavedResumesAdapter extends RecyclerView.Adapter<SavedResumesAdapter.SavedResumeHolder> {
    private List<Resume> resumes;
    private Context context;

    public SavedResumesAdapter(List<Resume> resumes, Context context) {
        this.resumes = resumes;
        this.context = context;
    }

    @Override
    public SavedResumeHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new SavedResumeHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.saved_resume_item, parent, false));
    }

    @Override
    public void onBindViewHolder(final SavedResumeHolder holder, int position) {
        final Resume resume = resumes.get(holder.getAdapterPosition());
        File snapshot = new File(new File(context.getFilesDir(), "Photos"), String.format(Locale.US,
                "%d_snapshot.png", resume.getId()));
        Picasso.with(context).load(snapshot).into(holder.thumbnail);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ResumeDetailActivity.class);
                intent.putExtra("resume_id", resume.getId());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return resumes.size();
    }

    class SavedResumeHolder extends RecyclerView.ViewHolder{
        ImageView thumbnail;

        SavedResumeHolder(View itemView) {
            super(itemView);
            thumbnail = (ImageView) itemView.findViewById(R.id.thumbnail);
        }
    }
}
