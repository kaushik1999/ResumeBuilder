package com.sultanofcardio.resumebuildersultanofcardio.adapters;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.preference.PreferenceManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.activeandroid.query.Select;
import com.squareup.picasso.Picasso;
import com.sultanofcardio.resumebuildersultanofcardio.R;
import com.sultanofcardio.resumebuildersultanofcardio.activities.AboutYouActivity;
import com.sultanofcardio.resumebuildersultanofcardio.models.Resume;

/**
 * @author sultanofcardio
 */

public class TemplateAdapter extends RecyclerView.Adapter<TemplateAdapter.TemplateHolder> {
    private String[] templates;
    private Context context;
    private Resume resume;

    public TemplateAdapter(String[] templates, Context context) {
        this.templates = templates;
        this.context = context;
        resume = new Resume();
    }

    @Override
    public TemplateHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new TemplateHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.template_item, parent, false));
    }

    @Override
    public void onBindViewHolder(TemplateHolder holder, int position) {
        String template = templates[holder.getAdapterPosition()];
        holder.templateNumber = Integer.valueOf(template);
        String name = "resume_" + template;
        String uri = "android.resource://com.sultanofcardio.resumebuildersultanofcardio/drawable/"
                + name;
        Picasso.with(context).load(Uri.parse(uri)).into(holder.template);
    }

    @Override
    public int getItemCount() {
        return templates.length;
    }

    public void update(String[] templates){
        this.templates = templates;
        notifyDataSetChanged();
    }

    class TemplateHolder extends RecyclerView.ViewHolder {
        ImageView template;
        int templateNumber = 0;

        TemplateHolder(View itemView) {
            super(itemView);

            template = (ImageView) itemView.findViewById(R.id.template);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    PreferenceManager.getDefaultSharedPreferences(context)
                            .edit().putInt("template_number", templateNumber).apply();
                    resume.setResumeType(templateNumber);
                    resume.save();
                    long id = new Select().from(Resume.class).orderBy("id DESC")
                            .executeSingle().getId();
                    Intent intent = new Intent(context, AboutYouActivity.class);
                    intent.putExtra("resume_id", id);
                    context.startActivity(intent);
                }
            });
        }
    }
}
