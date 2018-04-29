package com.sultanofcardio.resumebuildersultanofcardio.activities;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.telephony.PhoneNumberFormattingTextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.widget.EditText;
import android.widget.ImageView;

import com.activeandroid.query.Select;
import com.squareup.picasso.Picasso;
import com.sultanofcardio.resumebuildersultanofcardio.R;
import com.sultanofcardio.resumebuildersultanofcardio.models.Resume;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Locale;

public class AboutYouActivity extends AppCompatActivity {
    private Resume resume;
    private ImageView thumbnail, editThumbnail;
    private EditText name, number, email, website, address, summary;
    private Toolbar toolbar;
    final int SELECT_IMAGE_CODE = 4421;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_you);

        long id = getIntent().getLongExtra("resume_id", 0);
        resume = new Select().from(Resume.class).where("id = ?", id).executeSingle();

        initViews();

        setUpViews();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_about_you, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case R.id.next:
                save();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @SuppressWarnings("ResultOfMethodCallIgnored")
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            if(requestCode == SELECT_IMAGE_CODE){
                Uri uri = data.getData();
                CropImage.activity(uri)
                        .setGuidelines(CropImageView.Guidelines.ON)
                        .setAllowRotation(true)
                        .setAspectRatio(3, 4)
                        .setInitialRotation(90)
                        .start(this);
            } else if(requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE){
                CropImage.ActivityResult result = CropImage.getActivityResult(data);
                Uri cropped_uri = result.getUri();
//                resume.setThumbnail(cropped_uri.toString());
                Picasso.with(this).load(cropped_uri).into(thumbnail);

                //Copy photo for future use
                File file = new File(getFilesDir(), "Photos");
                file.mkdirs();
                String filename = String.format(Locale.US, "%d.jpg", resume.getId());
                File photo = new File(file, filename);
                savePhoto(photo, cropped_uri);
                resume.setThumbnail(Uri.fromFile(photo).toString());
            }
        }
    }

    private void initViews(){
        thumbnail = (ImageView) findViewById(R.id.thumbnail);
        editThumbnail = (ImageView) findViewById(R.id.edit_thumbnail);
        name = (EditText) findViewById(R.id.name);
        number = (EditText) findViewById(R.id.number);
        email = (EditText) findViewById(R.id.email);
        website = (EditText) findViewById(R.id.website);
        address = (EditText) findViewById(R.id.address);
        summary = (EditText) findViewById(R.id.summary);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
    }

    private void setUpViews(){
        setSupportActionBar(toolbar);
        number.addTextChangedListener(new PhoneNumberFormattingTextWatcher());

        thumbnail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent getIntent = new Intent(Intent.ACTION_GET_CONTENT);
                getIntent.setType("image/*");

                Intent pickIntent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                pickIntent.setType("image/*");

                Intent chooserIntent = Intent.createChooser(getIntent, "Select Image");
                chooserIntent.putExtra(Intent.EXTRA_INITIAL_INTENTS, new Intent[] {pickIntent});

                startActivityForResult(chooserIntent, SELECT_IMAGE_CODE);
            }
        });

        populateFields();
    }

    private void populateFields(){
        name.setText(resume.getName());
        number.setText(resume.getNumber());
        email.setText(resume.getEmail());
        website.setText(resume.getWebsite());
        address.setText(resume.getAddress());
        summary.setText(resume.getSummary());

        File photo = new File(new File(getFilesDir(), "Photos"), String.format(Locale.US,
                "%d.jpg", resume.getId()));
        Picasso.with(this).load(photo).into(thumbnail);
    }

    @SuppressWarnings({"ResultOfMethodCallIgnored", "ConstantConditions"})
    private void savePhoto(File photo, Uri uri){
        try {
            InputStream inputStream = getContentResolver().openInputStream(uri);

            if (!photo.exists())
                photo.createNewFile();

            FileOutputStream outputStream = new FileOutputStream(photo);

            byte[] buffer = new byte[1024 * 100];
            int len;
            while ((len = inputStream.read(buffer)) != -1) {
                outputStream.write(Arrays.copyOfRange(buffer, 0, Math.max(0, len)));
            }

            inputStream.close();
            outputStream.close();
        } catch (IOException e) {
//            Snackbar.make(root, "An error occurred. Try again later", Snackbar.LENGTH_LONG).show();
            e.printStackTrace();
        }
    }

    public void save(){
        resume.setName(name.getText().toString())
                .setNumber(number.getText().toString())
                .setEmail(email.getText().toString())
                .setWebsite(website.getText().toString())
                .setAddress(address.getText().toString())
                .setSummary(summary.getText().toString());
        resume.save();
        Intent intent = new Intent(this, ExperienceActivity.class);
        intent.putExtra("resume_id", resume.getId());
        startActivity(intent);
    }
}
