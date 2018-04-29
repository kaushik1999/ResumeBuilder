package com.sultanofcardio.resumebuildersultanofcardio.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.activeandroid.query.Select;
import com.squareup.picasso.Picasso;
import com.sultanofcardio.resumebuildersultanofcardio.R;
import com.sultanofcardio.resumebuildersultanofcardio.adapters.EducationDetailAdapter;
import com.sultanofcardio.resumebuildersultanofcardio.adapters.ExperienceDetailAdapter;
import com.sultanofcardio.resumebuildersultanofcardio.adapters.ReferenceDetailAdapter;
import com.sultanofcardio.resumebuildersultanofcardio.adapters.SkillsDetailAdapter;
import com.sultanofcardio.resumebuildersultanofcardio.models.Resume;

import java.io.File;
import java.util.Locale;

public class ResumeDetailActivity extends AppCompatActivity {
    private Resume resume;
    private ImageView thumbnail;
    private TextView name, number, email, website, address, summary;
    private RecyclerView experiences, education, skills, references;
    private ExperienceDetailAdapter experienceAdapter;
    private EducationDetailAdapter educationAdapter;
    private SkillsDetailAdapter skillsAdapter;
    private ReferenceDetailAdapter referenceAdapter;
    private FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resume_detail);

        long id = getIntent().getLongExtra("resume_id", 0);
        resume = new Select().from(Resume.class).where("id = ?", id).executeSingle();

        initViews();

        setUpViews();

        thumbnail.requestFocus();
    }

    private void initViews(){
        experiences = (RecyclerView) findViewById(R.id.experience_recyclerview);
        education = (RecyclerView) findViewById(R.id.education_recyclerview);
        skills = (RecyclerView) findViewById(R.id.skills_recyclerview);
        references = (RecyclerView) findViewById(R.id.references_recyclerview);
        fab = (FloatingActionButton) findViewById(R.id.fab);
        thumbnail = (ImageView) findViewById(R.id.thumbnail);
        name = (TextView) findViewById(R.id.name);
        number = (TextView) findViewById(R.id.number);
        email = (TextView) findViewById(R.id.email);
        website = (TextView) findViewById(R.id.website);
        address = (TextView) findViewById(R.id.address);
        summary = (TextView) findViewById(R.id.summary);
    }

    private void setUpViews() {
        File photo = new File(new File(getFilesDir(), "Photos"), String.format(Locale.US,
                "%d.jpg", resume.getId()));
        Picasso.with(this).load(photo).into(thumbnail);

        name.setText(resume.getName());
        number.setText(resume.getNumber());
        email.setText(resume.getEmail());
        website.setText(resume.getWebsite());
        address.setText(resume.getAddress());
        summary.setText(resume.getSummary());

        setUpExperienceRecyclerView();
        setUpEducationRecyclerView();
        setUpSkillsRecyclerView();
        setUpReferenceRecyclerView();

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ResumeDetailActivity.this, AboutYouActivity.class);
                intent.putExtra("resume_id", resume.getId());
                startActivity(intent);
            }
        });
    }

    private void setUpExperienceRecyclerView(){
        experiences.setLayoutManager(new LinearLayoutManager(this));
        experienceAdapter = new ExperienceDetailAdapter(resume.getExperiences());
        experiences.setNestedScrollingEnabled(false);
        experiences.setAdapter(experienceAdapter);
    }

    private void setUpEducationRecyclerView(){
        education.setLayoutManager(new LinearLayoutManager(this));
        educationAdapter = new EducationDetailAdapter(resume.getEducation());
        education.setNestedScrollingEnabled(false);
        education.setAdapter(educationAdapter);
    }

    private void setUpSkillsRecyclerView(){
        skills.setLayoutManager(new LinearLayoutManager(this));
        skillsAdapter = new SkillsDetailAdapter(resume.getSkills());
        skills.setNestedScrollingEnabled(false);
        skills.setAdapter(skillsAdapter);
    }

    private void setUpReferenceRecyclerView(){
        references.setLayoutManager(new LinearLayoutManager(this));
        referenceAdapter = new ReferenceDetailAdapter(resume.getReferenceList());
        references.setNestedScrollingEnabled(false);
        references.setAdapter(referenceAdapter);
    }
}
