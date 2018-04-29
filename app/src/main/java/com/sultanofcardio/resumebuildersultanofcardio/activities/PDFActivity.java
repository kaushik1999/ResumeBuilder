package com.sultanofcardio.resumebuildersultanofcardio.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.github.barteksc.pdfviewer.PDFView;
import com.github.barteksc.pdfviewer.listener.OnLoadCompleteListener;
import com.github.barteksc.pdfviewer.listener.OnPageChangeListener;
import com.github.barteksc.pdfviewer.scroll.DefaultScrollHandle;
import com.shockwave.pdfium.PdfDocument;
import com.sultanofcardio.resumebuildersultanofcardio.R;

import java.util.List;

public class PDFActivity extends AppCompatActivity implements OnPageChangeListener,
        OnLoadCompleteListener {

    private PDFView pdfView;
    final String SAMPLE_FILE = "resume.pdf";
    int pageNumber = 0;
    String pdfFileName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pdf);

        pdfView = (PDFView) findViewById(R.id.pdfview);
        displayFromAsset(SAMPLE_FILE);


    }

    private void displayFromAsset(String assetFileName){
        pdfView.fromAsset(assetFileName)
                .defaultPage(pageNumber)
                .enableSwipe(true)
                .swipeHorizontal(false)
                .onPageChange(this)
                .enableAnnotationRendering(true)
                .onLoad(this)
                .scrollHandle(new DefaultScrollHandle(this))
                .load();
    }

    public void printBookmarksTree(List<PdfDocument.Bookmark> tree, String sep){
        for(PdfDocument.Bookmark b: tree){
            Log.e("TAG", String.format("%s, %s, p %d", sep, b.getTitle(), b.getPageIdx()));
            if(b.hasChildren())
                printBookmarksTree(b.getChildren(), sep + "-");
        }
    }

    @Override
    public void loadComplete(int nbPages) {
        PdfDocument.Meta meta = pdfView.getDocumentMeta();
        printBookmarksTree(pdfView.getTableOfContents(), "-");
    }

    @Override
    public void onPageChanged(int page, int pageCount) {
        pageNumber = page;
        setTitle(String.format("%s %s / %s", pdfFileName, page + 1, pageCount));
    }
}