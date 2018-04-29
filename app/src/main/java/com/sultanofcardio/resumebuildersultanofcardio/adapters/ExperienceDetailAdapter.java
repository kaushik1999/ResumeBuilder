package com.sultanofcardio.resumebuildersultanofcardio.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.sultanofcardio.resumebuildersultanofcardio.R;
import com.sultanofcardio.resumebuildersultanofcardio.models.Experience;

import java.util.List;
import java.util.Locale;

/**
 * @author sultanofcardio
 */

public class ExperienceDetailAdapter extends RecyclerView.Adapter<ExperienceDetailAdapter.ExperienceHolder> {

    private List<Experience> experienceList;

    public ExperienceDetailAdapter(List<Experience> experienceList) {
        this.experienceList = experienceList;
    }

    @Override
    public ExperienceHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ExperienceHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.experience_detail_item,parent,false));
    }

    @Override
    public void onBindViewHolder(ExperienceHolder holder, int position) {
        final int newPosition = holder.getAdapterPosition();

        final Experience experience = experienceList.get(newPosition);
        holder.company.setText(experience.getCompany());
        holder.role.setText(experience.getRole());
        String duration = String.format(Locale.US, "%s %s - %s %s", experience.getStartMonth(),
                experience.getStartYear(), experience.getEndMonth(), experience.getEndYear());
        holder.duration.setText(duration);
        holder.description.setText(experience.getDescription());
    }

    @Override
    public int getItemCount() {
        return experienceList.size();
    }

    public void update(List<Experience> experienceList){
        this.experienceList = experienceList;
        notifyDataSetChanged();
    }

    public List<Experience> getExperienceList(){
        return experienceList;
    }

    class ExperienceHolder extends RecyclerView.ViewHolder{
        TextView company, role, duration, description;

        ExperienceHolder(View itemView) {
            super(itemView);

            company = (TextView) itemView.findViewById(R.id.company);
            role = (TextView) itemView.findViewById(R.id.role);
            duration = (TextView) itemView.findViewById(R.id.duration);
            description = (TextView) itemView.findViewById(R.id.description);
        }
    }
}
