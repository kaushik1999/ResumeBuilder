package com.sultanofcardio.resumebuildersultanofcardio.activities;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Rect;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.activeandroid.query.Select;
import com.sultanofcardio.resumebuildersultanofcardio.R;
import com.sultanofcardio.resumebuildersultanofcardio.adapters.SavedResumesAdapter;
import com.sultanofcardio.resumebuildersultanofcardio.models.Resume;
import com.sultanofcardio.resumebuildersultanofcardio.util.PermissionUtils;

import java.util.List;

public class SavedResumesActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private RecyclerView recyclerView;
    private SavedResumesAdapter adapter;
    private List<Resume> resumes;
    private View addSomeItems;
    private FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saved_resumes);

        resumes = new Select().all().from(Resume.class).orderBy("id").execute();

        initViews();

        setUpViews();

    }

    private void initViews(){
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        addSomeItems = findViewById(R.id.add_some_items);
        fab = (FloatingActionButton) findViewById(R.id.fab);
    }

    private void setUpViews(){
        setSupportActionBar(toolbar);

        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        adapter = new SavedResumesAdapter(resumes, this);
        recyclerView.setAdapter(adapter);
        recyclerView.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
                if(parent.getChildLayoutPosition(view) % 2 != 0)
                    outRect.set(4, 4, 4, 4);
            }
        });
        recyclerView.addOnChildAttachStateChangeListener(new RecyclerView.OnChildAttachStateChangeListener() {
            @Override
            public void onChildViewAttachedToWindow(View view) {
                if(recyclerView.getChildCount() > 0)
                    addSomeItems.setVisibility(View.GONE);
            }

            @Override
            public void onChildViewDetachedFromWindow(View view) {
                if(recyclerView.getChildCount() == 0)
                    addSomeItems.setVisibility(View.VISIBLE);
            }
        });

        View.OnClickListener createResume = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SavedResumesActivity.this, TemplateActivity.class));
            }
        };

        addSomeItems.setOnClickListener(createResume);
        fab.setOnClickListener(createResume);
    }
}
