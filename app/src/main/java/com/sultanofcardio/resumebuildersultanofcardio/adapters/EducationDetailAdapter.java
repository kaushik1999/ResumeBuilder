package com.sultanofcardio.resumebuildersultanofcardio.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.sultanofcardio.resumebuildersultanofcardio.R;
import com.sultanofcardio.resumebuildersultanofcardio.models.Education;

import java.util.List;

/**
 * @author sultanofcardio
 */

public class EducationDetailAdapter extends RecyclerView.Adapter<EducationDetailAdapter.EducationHolder> {
    private List<Education> educationList;

    public EducationDetailAdapter(List<Education> educationList) {
        this.educationList = educationList;
    }

    @Override
    public EducationHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new EducationHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.education_detail_item,parent,false));
    }

    @Override
    public void onBindViewHolder(EducationHolder holder, int position) {
        final int newPosition = holder.getAdapterPosition();
        final Education education = educationList.get(newPosition);

        holder.institution.setText(education.getInstitution());
        holder.major.setText(education.getMajor());
        holder.gpa.setText(education.getGpa());
    }

    @Override
    public int getItemCount() {
        return educationList.size();
    }

    public void update(List<Education> educationList){
        this.educationList = educationList;
        notifyDataSetChanged();
    }

    public void addEducation(){
        educationList.add(0, new Education());
        notifyDataSetChanged();
    }

    public List<Education> getEducationList(){
        return educationList;
    }

    public void removeEducation(){
        if(educationList.size() > 0)
            educationList.remove(0);
        notifyDataSetChanged();
    }

    class EducationHolder extends RecyclerView.ViewHolder {
        TextView institution, major, gpa;

        EducationHolder(View itemView) {
            super(itemView);

            institution = (TextView) itemView.findViewById(R.id.institution);
            major = (TextView) itemView.findViewById(R.id.major);
            gpa = (TextView) itemView.findViewById(R.id.gpa);
        }
    }
}
