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
import com.sultanofcardio.resumebuildersultanofcardio.models.Education;
import com.sultanofcardio.resumebuildersultanofcardio.models.EducationList;

import java.util.List;

/**
 * @author sultanofcardio
 */

public class EducationAdapter extends RecyclerView.Adapter<EducationAdapter.EducationHolder> {
    private EducationList educationList;
    private Context context;

    public EducationAdapter(EducationList educationList, Context context) {
        this.educationList = educationList;
        this.context = context;
    }

    @Override
    public EducationHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new EducationHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.education_item,parent,false), new CustomTextWatcher(),
                new CustomTextWatcher(), new CustomTextWatcher());
    }

    @Override
    public void onBindViewHolder(EducationHolder holder, int position) {
        final int newPosition = holder.getAdapterPosition();
        final Education education = educationList.get(newPosition);
        holder.institution.setText(education.getInstitution());
        holder.textWatcher1.position = newPosition;
        holder.textWatcher1.attribute = 1;

        holder.major.setText(education.getMajor());
        holder.textWatcher2.position = newPosition;
        holder.textWatcher2.attribute = 2;

        holder.gpa.setText(education.getGpa());
        holder.textWatcher3.position = newPosition;
        holder.textWatcher3.attribute = 3;
    }

    @Override
    public int getItemCount() {
        return educationList.size();
    }

    public void update(EducationList educationList){
        this.educationList = educationList;
        notifyDataSetChanged();
    }

    public void addEducation(){
        educationList.add(0, new Education());
        notifyDataSetChanged();
    }

    public EducationList getEducationList(){
        return educationList;
    }

    public void removeEducation(){
        if(educationList.size() > 0)
            educationList.remove(0);
        notifyDataSetChanged();
    }

    class EducationHolder extends RecyclerView.ViewHolder {
        EditText institution, major, gpa;
        CustomTextWatcher textWatcher1, textWatcher2, textWatcher3;

        EducationHolder(View itemView, CustomTextWatcher textWatcher1, CustomTextWatcher textWatcher2,
                        CustomTextWatcher textWatcher3) {
            super(itemView);

            this.textWatcher1 = textWatcher1;
            this.textWatcher2 = textWatcher2;
            this.textWatcher3 = textWatcher3;

            institution = (EditText) itemView.findViewById(R.id.institution);
            institution.addTextChangedListener(this.textWatcher1);
            major = (EditText) itemView.findViewById(R.id.major);
            major.addTextChangedListener(this.textWatcher2);
            gpa = (EditText) itemView.findViewById(R.id.gpa);
            gpa.addTextChangedListener(this.textWatcher3);
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
            Education education = educationList.get(position);
            switch (attribute){
                case 1:
                    education.setInstitution(s.toString());
                    break;
                case 2:
                    education.setMajor(s.toString());
                    break;
                case 3:
                    education.setGpa(s.toString());
                    break;
            }

            educationList.add(position, education);
            educationList.remove(position + 1);

        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    }
}
