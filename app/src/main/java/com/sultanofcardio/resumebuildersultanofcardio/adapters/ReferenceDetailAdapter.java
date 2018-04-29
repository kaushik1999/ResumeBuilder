package com.sultanofcardio.resumebuildersultanofcardio.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.sultanofcardio.resumebuildersultanofcardio.R;
import com.sultanofcardio.resumebuildersultanofcardio.models.Reference;

import java.util.List;

/**
 * @author sultanofcardio
 */

public class ReferenceDetailAdapter extends RecyclerView.Adapter<ReferenceDetailAdapter.ReferenceHolder> {
    private List<Reference> referenceList;

    public ReferenceDetailAdapter(List<Reference> referenceList) {
        this.referenceList = referenceList;
    }

    @Override
    public ReferenceHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ReferenceHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.reference_detail_item,parent,false));
    }

    @Override
    public void onBindViewHolder(ReferenceHolder holder, int position) {
        final Reference reference = referenceList.get(holder.getAdapterPosition());
        holder.name.setText(reference.getName());
        holder.title.setText(reference.getTitle());
        holder.number.setText(reference.getNumber());
        holder.email.setText(reference.getEmail());
    }

    @Override
    public int getItemCount() {
        return referenceList.size();
    }

    public void update(List<Reference> educationList){
        this.referenceList = educationList;
        notifyDataSetChanged();
    }

    public List<Reference> getReferenceList(){
        return referenceList;
    }

    class ReferenceHolder extends RecyclerView.ViewHolder {
        TextView name, title, number, email;

        ReferenceHolder(View itemView) {
            super(itemView);

            name = (TextView) itemView.findViewById(R.id.name);
            title = (TextView) itemView.findViewById(R.id.title);
            number = (TextView) itemView.findViewById(R.id.number);
            email = (TextView) itemView.findViewById(R.id.email);
        }
    }
}
