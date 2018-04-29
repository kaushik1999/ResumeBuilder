package com.sultanofcardio.resumebuildersultanofcardio.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.sultanofcardio.resumebuildersultanofcardio.R;

import java.util.List;

/**
 * @author sultanofcardio
 */

public class SkillsDetailAdapter extends RecyclerView.Adapter<SkillsDetailAdapter.SkillHolder> {
    private List<String> skills;

    public SkillsDetailAdapter(List<String> skills) {
        this.skills = skills;
        System.out.println("" + skills.size() + " skills");
    }

    @Override
    public SkillHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new SkillHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.skill_detail_item,parent,false));
    }

    @Override
    public void onBindViewHolder(SkillHolder holder, int position) {
        String skill = skills.get(holder.getAdapterPosition());
        holder.skill.setText(skill);
    }

    @Override
    public int getItemCount() {
        return skills.size();
    }

    public void update(List<String> skills){
        this.skills = skills;
        notifyDataSetChanged();
    }

    public List<String> getSkills(){
        return skills;
    }

    class SkillHolder extends RecyclerView.ViewHolder {
        TextView skill;

        SkillHolder(View itemView) {
            super(itemView);

            skill = (TextView) itemView.findViewById(R.id.skill);
        }
    }
}
