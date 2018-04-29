package com.sultanofcardio.resumebuildersultanofcardio.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.sultanofcardio.resumebuildersultanofcardio.R;
import com.sultanofcardio.resumebuildersultanofcardio.models.Experience;
import com.sultanofcardio.resumebuildersultanofcardio.models.ExperienceList;

import java.util.List;

/**
 * @author sultanofcardio
 */

public class ExperienceAdapter extends RecyclerView.Adapter<ExperienceAdapter.ExperienceHolder> {

    private ExperienceList experienceList;

    public ExperienceAdapter(ExperienceList experienceList) {
        this.experienceList = experienceList;
    }

    @Override
    public ExperienceHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ExperienceHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.experience_item,parent,false), new CustomTextWatcher(),
                new CustomTextWatcher(), new CustomTextWatcher(), new CustomTextWatcher(),
                new CustomTextWatcher(), new CustomTextWatcher(), new CustomTextWatcher());
    }

    @Override
    public void onBindViewHolder(ExperienceHolder holder, int position) {
        final int newPosition = holder.getAdapterPosition();
        final Experience experience = experienceList.get(newPosition);
        holder.company.setText(experience.getCompany());
        holder.textWatcher1.position = newPosition;
        holder.textWatcher1.attribute = 1;

        holder.role.setText(experience.getRole());
        holder.textWatcher2.position = newPosition;
        holder.textWatcher2.attribute = 2;

        holder.startMonth.setText(experience.getStartMonth());
        holder.textWatcher3.position = newPosition;
        holder.textWatcher3.attribute = 3;

        holder.startYear.setText(experience.getStartYear());
        holder.textWatcher4.position = newPosition;
        holder.textWatcher4.attribute = 4;

        holder.endMonth.setText(experience.getEndMonth());
        holder.textWatcher5.position = newPosition;
        holder.textWatcher5.attribute = 5;

        holder.endYear.setText(experience.getEndYear());
        holder.textWatcher6.position = newPosition;
        holder.textWatcher6.attribute = 6;

        holder.description.setText(experience.getDescription());
        holder.textWatcher7.position = newPosition;
        holder.textWatcher7.attribute = 7;
    }

    @Override
    public int getItemCount() {
        return experienceList.size();
    }

    public void update(ExperienceList experienceList){
        this.experienceList = experienceList;
        notifyDataSetChanged();
    }

    public void addExperience(){
        experienceList.add(0, new Experience());
        notifyDataSetChanged();
    }

    public ExperienceList getExperienceList(){
        return experienceList;
    }

    public void removeExperience(){
        if(experienceList.size() > 0)
            experienceList.remove(0);
        notifyDataSetChanged();
    }

    class ExperienceHolder extends RecyclerView.ViewHolder{
        EditText company, role, startMonth, startYear, endMonth, endYear, description;
        CustomTextWatcher textWatcher1, textWatcher2, textWatcher3, textWatcher4, textWatcher5,
                textWatcher6, textWatcher7;

        ExperienceHolder(View itemView, CustomTextWatcher textWatcher1, CustomTextWatcher textWatcher2,
                         CustomTextWatcher textWatcher3, CustomTextWatcher textWatcher4,
                         CustomTextWatcher textWatcher5, CustomTextWatcher textWatcher6,
                         CustomTextWatcher textWatcher7) {
            super(itemView);

            this.textWatcher1 = textWatcher1;
            this.textWatcher2 = textWatcher2;
            this.textWatcher3 = textWatcher3;
            this.textWatcher4 = textWatcher4;
            this.textWatcher5 = textWatcher5;
            this.textWatcher6 = textWatcher6;
            this.textWatcher7 = textWatcher7;

            company = (EditText) itemView.findViewById(R.id.company);
            company.addTextChangedListener(this.textWatcher1);
            role = (EditText) itemView.findViewById(R.id.role);
            role.addTextChangedListener(this.textWatcher2);
            startMonth = (EditText) itemView.findViewById(R.id.start_month);
            startMonth.addTextChangedListener(this.textWatcher3);
            startYear = (EditText) itemView.findViewById(R.id.start_year);
            startYear.addTextChangedListener(this.textWatcher4);
            endMonth = (EditText) itemView.findViewById(R.id.end_month);
            endMonth.addTextChangedListener(this.textWatcher5);
            endYear= (EditText) itemView.findViewById(R.id.end_year);
            endYear.addTextChangedListener(this.textWatcher6);
            description = (EditText) itemView.findViewById(R.id.description);
            description.addTextChangedListener(this.textWatcher7);
        }
    }

    class CustomTextWatcher implements TextWatcher {

        private int position, attribute;

        public void setPosition(int position){
            this.position = position;
        }

        public CustomTextWatcher setAttribute(int attribute) {
            this.attribute = attribute;
            return this;
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            Experience experience = experienceList.get(position);
            switch (attribute){
                case 1:
                    experience.setCompany(s.toString());
                    break;
                case 2:
                    experience.setRole(s.toString());
                    break;
                case 3:
                    experience.setStartMonth(s.toString());
                    break;
                case 4:
                    experience.setStartYear(s.toString());
                    break;
                case 5:
                    experience.setEndMonth(s.toString());
                    break;
                case 6:
                    experience.setEndYear(s.toString());
                    break;
                case 7:
                    experience.setDescription(s.toString());
                    break;
            }

            experienceList.add(position, experience);
            experienceList.remove(position + 1);

        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    }
}
