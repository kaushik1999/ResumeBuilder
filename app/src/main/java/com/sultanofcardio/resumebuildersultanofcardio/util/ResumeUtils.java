package com.sultanofcardio.resumebuildersultanofcardio.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Build;
import android.print.PrintAttributes;
import android.print.PrintDocumentAdapter;
import android.print.PrintJob;
import android.print.PrintManager;
import android.text.TextUtils;
import android.webkit.WebView;

import com.sultanofcardio.resumebuildersultanofcardio.R;
import com.sultanofcardio.resumebuildersultanofcardio.models.Education;
import com.sultanofcardio.resumebuildersultanofcardio.models.Experience;
import com.sultanofcardio.resumebuildersultanofcardio.models.Reference;
import com.sultanofcardio.resumebuildersultanofcardio.models.Resume;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Locale;

/**
 * @author sultanofcardio
 */

public class ResumeUtils {

    public static String createResume(Resume resume, Context context){
        String html = "";
        int templateNumber = resume.getResumeType();
        String prefix = "resume_" + templateNumber;
        String template = prefix + ".html";
        try {
            Document document = Jsoup.parse(context.getAssets().open(template), "UTF-8", "");
            document.getElementById("name").html(resume.getName());
            if(!TextUtils.isEmpty(resume.getThumbnail()))
                document.getElementById("thumbnail").attr("src", resume.getThumbnail());
            else
                document.getElementById("thumbnail").remove();
            document.getElementById("number").html(resume.getNumber());
            document.getElementById("email").attr("href", "mailto:" + resume.getEmail()).html(resume.getEmail());
            if(!TextUtils.isEmpty(resume.getWebsite()))
                document.getElementById("website").attr("href", "http://" + resume.getWebsite()).html(resume.getWebsite());
            else {
                document.getElementById("site").remove();
                document.getElementById("website").remove();
            }
            document.getElementById("address").html(resume.getAddress());
            document.getElementById("summary").html(resume.getSummary());

            for(Experience experience: resume.getExperiences()) {
                String experienceTemplate = prefix + "_experience.html";
                Document experienceDoc = Jsoup.parse(context.getAssets().open(experienceTemplate), "UTF-8", "");
                Element role = experienceDoc.getElementById("role").html(experience.getRole());
                Element startMonth = experienceDoc.getElementById("start_month").html(experience.getStartMonth());
                Element startYear = experienceDoc.getElementById("start_year").html(experience.getStartYear());
                Element endMonth = experienceDoc.getElementById("end_month").html(experience.getEndMonth());
                Element endYear = experienceDoc.getElementById("end_year").html(experience.getEndYear());
                Element company = experienceDoc.getElementById("company");
                company.html(experience.getCompany())
                        .append(" " +  role.outerHtml())
                        .append(" " +  startMonth.outerHtml())
                        .append(" " +  startYear.outerHtml())
                        .append(" - " +  endMonth.outerHtml())
                        .append(" " +  endYear.outerHtml());
                experienceDoc.getElementById("description").html(experience.getDescription());
                document.getElementById("experience").append(experienceDoc.outerHtml());
            }

            for(Education education: resume.getEducation()) {
                String educationTemplate = prefix + "_education.html";
                Document educationDoc = Jsoup.parse(context.getAssets().open(educationTemplate), "UTF-8", "");
                educationDoc.getElementById("institution").html(education.getInstitution());
                educationDoc.getElementById("major").html(education.getMajor());
                educationDoc.getElementById("gpa").html(education.getGpa());
                document.getElementById("education").append(educationDoc.outerHtml());
            }

            for(String skill: resume.getSkills()) {
                document.getElementById("skills").append(new Element("li").html(skill).outerHtml());
            }

            for(Reference reference: resume.getReferenceList()) {
                String referenceTemplate = prefix + "_reference.html";
                Document referenceDoc = Jsoup.parse(context.getAssets().open(referenceTemplate), "UTF-8", "");
                Element name = referenceDoc.getElementById("name");
                Element title = referenceDoc.getElementById("title");
                title.html(reference.getTitle());
                name.html(reference.getName()).append(" " + title.outerHtml());
                referenceDoc.getElementById("number").html(reference.getNumber());
                referenceDoc.getElementById("email").attr("href", "mailto:" + reference.getEmail()).html(reference.getEmail());
                document.getElementById("references").append(referenceDoc.outerHtml());
            }

            String stylesheet = prefix + ".css";
            document.head().appendElement("link").attr("rel", "stylesheet").attr("type", "text/css").attr("href", stylesheet);
            html = document.outerHtml();
        } catch (IOException e){
            e.printStackTrace();
        }

        return html;
    }


    public static void printResume(WebView webView, Context context){
        PrintManager printManager = (PrintManager) context.getSystemService(Context.PRINT_SERVICE);
        PrintDocumentAdapter printAdapter = null;

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            printAdapter = webView.createPrintDocumentAdapter("Resume");
        } else if(android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT){
            printAdapter = webView.createPrintDocumentAdapter();
        }

        String jobName = context.getString(R.string.app_name) + " Resume";
        PrintJob printJob = printManager.print(jobName, printAdapter, new PrintAttributes.Builder().build());
    }

    public static void takeSnapshot(Bitmap bitmap, Context context, Resume resume){

        FileOutputStream outputStream = null;
        try{
            File file = FileUtils.createFile(String.format(Locale.US, "%d_snapshot.png",
                    resume.getId()), FileUtils.createDirectory("Photos", context), context);
            outputStream = new FileOutputStream(file);
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, outputStream);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if(outputStream != null)
                    outputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
