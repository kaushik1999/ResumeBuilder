package com.sultanofcardio.resumebuildersultanofcardio.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.telephony.PhoneNumberFormattingTextWatcher;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.sultanofcardio.resumebuildersultanofcardio.R;
import com.sultanofcardio.resumebuildersultanofcardio.models.Education;
import com.sultanofcardio.resumebuildersultanofcardio.models.Reference;
import com.sultanofcardio.resumebuildersultanofcardio.models.ReferenceList;

import java.util.List;

/**
 * @author sultanofcardio
 */

public class ReferenceAdapter extends RecyclerView.Adapter<ReferenceAdapter.ReferenceHolder> {
    private ReferenceList referenceList;
    private Context context;

    public ReferenceAdapter(ReferenceList referenceList, Context context) {
        this.referenceList = referenceList;
        this.context = context;
    }

    @Override
    public ReferenceHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ReferenceHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.reference_item,parent,false), new CustomTextWatcher(),
                new CustomTextWatcher(), new CustomTextWatcher(), new CustomTextWatcher());
    }

    @Override
    public void onBindViewHolder(ReferenceHolder holder, int position) {
        final int newPosition = holder.getAdapterPosition();
        final Reference reference = referenceList.get(newPosition);
        holder.name.setText(reference.getName());
        holder.textWatcher1.position = newPosition;
        holder.textWatcher1.attribute = 1;

        holder.title.setText(reference.getTitle());
        holder.textWatcher2.position = newPosition;
        holder.textWatcher2.attribute = 2;

        holder.number.setText(reference.getNumber());
        holder.textWatcher3.position = newPosition;
        holder.textWatcher3.attribute = 3;

        holder.email.setText(reference.getEmail());
        holder.textWatcher4.position = newPosition;
        holder.textWatcher4.attribute = 4;
    }

    @Override
    public int getItemCount() {
        return referenceList.size();
    }

    public void update(ReferenceList educationList){
        this.referenceList = educationList;
        notifyDataSetChanged();
    }

    public void addReference(){
        referenceList.add(0, new Reference());
        notifyDataSetChanged();
    }

    public ReferenceList getReferenceList(){
        return referenceList;
    }

    public void removeReference(){
        if(referenceList.size() > 0)
            referenceList.remove(0);
        notifyDataSetChanged();
    }

    class ReferenceHolder extends RecyclerView.ViewHolder {
        EditText name, title, number, email;
        CustomTextWatcher textWatcher1, textWatcher2, textWatcher3, textWatcher4;

        ReferenceHolder(View itemView, CustomTextWatcher textWatcher1, CustomTextWatcher textWatcher2,
                        CustomTextWatcher textWatcher3, CustomTextWatcher textWatcher4) {
            super(itemView);

            this.textWatcher1 = textWatcher1;
            this.textWatcher2 = textWatcher2;
            this.textWatcher3 = textWatcher3;
            this.textWatcher4 = textWatcher4;

            name = (EditText) itemView.findViewById(R.id.name);
            name.addTextChangedListener(this.textWatcher1);
            title = (EditText) itemView.findViewById(R.id.title);
            title.addTextChangedListener(this.textWatcher2);
            number = (EditText) itemView.findViewById(R.id.number);
            number.addTextChangedListener(this.textWatcher3);
            number.addTextChangedListener(new PhoneNumberFormattingTextWatcher());
            email = (EditText) itemView.findViewById(R.id.email);
            email.addTextChangedListener(this.textWatcher4);
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
            Reference reference = referenceList.get(position);
            switch (attribute){
                case 1:
                    reference.setName(s.toString());
                    break;
                case 2:
                    reference.setTitle(s.toString());
                    break;
                case 3:
                    reference.setNumber(s.toString());
                    break;
                case 4:
                    reference.setEmail(s.toString());
                    break;
            }

            referenceList.add(position, reference);
            referenceList.remove(position + 1);

        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    }
}
