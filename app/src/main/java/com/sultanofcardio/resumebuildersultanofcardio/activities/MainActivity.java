package com.sultanofcardio.resumebuildersultanofcardio.activities;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.activeandroid.query.Select;
import com.sultanofcardio.resumebuildersultanofcardio.R;
import com.sultanofcardio.resumebuildersultanofcardio.models.Resume;
import com.sultanofcardio.resumebuildersultanofcardio.util.ResumeUtils;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {
    private WebView webView;
    private Resume resume;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        webView = (WebView) findViewById(R.id.webview);
        long id = getIntent().getLongExtra("resume_id", 0);
        resume = new Select().from(Resume.class).where("id = ?", id).executeSingle();

        new Task().execute();

    }

    class Task extends AsyncTask<Void, Void, String>{

        @Override
        protected String doInBackground(Void... params) {
            return ResumeUtils.createResume(resume, MainActivity.this);
        }

        @Override
        protected void onPostExecute(String html) {
            super.onPostExecute(html);
            webView.getSettings().setLoadWithOverviewMode(true);
            webView.getSettings().setUseWideViewPort(true);
            webView.setInitialScale(50);
            webView.setWebViewClient(new WebViewClient(){
                @Override
                public void onPageFinished(WebView view, String url) {
                    new Timer().schedule(new TimerTask() {
                        @Override
                        public void run() {
                            webView.buildDrawingCache();
                            Bitmap bitmap = webView.getDrawingCache();
                            ResumeUtils.takeSnapshot(bitmap, MainActivity.this, resume);
                            MainActivity.this.runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    ResumeUtils.printResume(webView, MainActivity.this);
                                }
                            });
                        }
                    }, 500);
                    super.onPageFinished(view, url);
                }
            });
            webView.loadDataWithBaseURL("file:///android_asset/.", html, "text/html", "UTF-8", null);
        }
    }
}
