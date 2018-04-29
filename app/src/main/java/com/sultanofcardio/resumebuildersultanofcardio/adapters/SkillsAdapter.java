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
import com.sultanofcardio.resumebuildersultanofcardio.models.SkillsList;

import java.util.List;

/**
 * @author sultanofcardio
 */

public class SkillsAdapter extends RecyclerView.Adapter<SkillsAdapter.SkillHolder> {
    private SkillsList skills;
    private Context context;

    public SkillsAdapter(SkillsList skills, Context context) {
        this.skills = skills;
        this.context = context;
    }

    @Override
    public SkillHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new SkillHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.skill_item,parent,false), new CustomTextWatcher());
    }

    @Override
    public void onBindViewHolder(SkillHolder holder, int position) {
        int newPosition = holder.getAdapterPosition();
        String skill = skills.get(newPosition);
        holder.skill.setText(skill);
        holder.textWatcher.position = newPosition;
    }

    @Override
    public int getItemCount() {
        return skills.size();
    }

    public void update(SkillsList skills){
        this.skills = skills;
        notifyDataSetChanged();
    }

    public void addSkill(){
        skills.add(0, "");
        notifyDataSetChanged();
    }

    public SkillsList getSkills(){
        return skills;
    }

    public void removeSkill(){
        if(skills.size() > 0)
            skills.remove(0);
        notifyDataSetChanged();
    }

    class SkillHolder extends RecyclerView.ViewHolder {
        EditText skill;
        CustomTextWatcher textWatcher;

        SkillHolder(View itemView, CustomTextWatcher textWatcher) {
            super(itemView);

            this.textWatcher = textWatcher;

            skill = (EditText) itemView.findViewById(R.id.skill);
            skill.addTextChangedListener(this.textWatcher);
        }
    }

    class CustomTextWatcher implements TextWatcher {

        private int position;

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            String skill = s.toString();
            skills.add(position, skill);
            skills.remove(position + 1);

        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    }
}
