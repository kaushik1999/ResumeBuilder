package com.sultanofcardio.resumebuildersultanofcardio.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.sultanofcardio.resumebuildersultanofcardio.R;
import com.sultanofcardio.resumebuildersultanofcardio.adapters.TemplateAdapter;

public class TemplateActivity extends AppCompatActivity {
    private String[] templates;
    private TemplateAdapter adapter;
    private RecyclerView recyclerView;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_template);

        templates = getResources().getStringArray(R.array.resume_templates);

        initViews();

        setUpViews();
    }

    private void initViews(){
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        adapter = new TemplateAdapter(templates, this);
    }

    private void setUpViews(){
        setSupportActionBar(toolbar);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
    }
}
