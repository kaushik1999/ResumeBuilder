package com.sultanofcardio.resumebuildersultanofcardio.util;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.util.Pair;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

/**
 * @author sultanofcardio
 */

@SuppressWarnings("ResultOfMethodCallIgnored")
public class FileUtils {

    private static final String CUSTOM_FILES = "CustomFiles";
    private static final String APP_NAME = "ResumeBuilder";

    private FileUtils(){}

    public static void fileCopy(File src, File dst) throws IOException {
        InputStream in = new FileInputStream(src);
        OutputStream out = new FileOutputStream(dst);

        // Transfer bytes from in to out
        byte[] buf = new byte[1024 * 100];
        int len;
        while ((len = in.read(buf)) > 0) {
            out.write(buf, 0, len);
        }
        in.close();
        out.close();
    }

    public static File createFile(String filename, Context context) throws IOException{
        File file = new File(getCustomFilesDir(context), filename);
        file.createNewFile();
        return file;
    }

    public static File createFile(String filename, File parent, Context context) throws IOException{
        File file = new File(parent, filename);
        file.createNewFile();
        return file;
    }

    public static File createPublicFile(String filename) throws IOException{
        File publicDir = createPublicDirectory(APP_NAME);
        File file = new File(publicDir, filename);
        file.createNewFile();
        return file;
    }

    public static File createDirectory(String directoryName, Context context) throws IOException{
        File directory = new File(context.getFilesDir(), directoryName);
        directory.mkdirs();
        return directory;
    }

    private static File createPublicDirectory(String directoryName) throws IOException{
        String sdcard = Environment.getExternalStorageDirectory().getAbsolutePath();
        File directory = new File(sdcard + File.separator + directoryName);
        directory.mkdirs();
        return directory;
    }

    private static File getCustomFilesDir(Context context){
        File directory = new File(context.getFilesDir(), CUSTOM_FILES);
        directory.mkdirs();
        return directory;
    }

    public static Pair<Integer, Integer> getImageSize(String image){
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;

        BitmapFactory.decodeFile(image, options);
        int width = options.outWidth;
        int height = options.outHeight;
        return new Pair<>(width, height);
    }

    public static String openTextFile(Context context, String fileName) throws IOException{
        File file = new File(getCustomFilesDir(context), fileName);
        FileInputStream fileInputStream = new FileInputStream(file);
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(fileInputStream));
        String output = "";
        String line = bufferedReader.readLine();

        while(line != null){
            output += line;
            line = bufferedReader.readLine();
        }

        bufferedReader.close();
        return output;
    }

    public static void saveTextFile(Context context, String fileName, String text) throws IOException {
        File file = new File(getCustomFilesDir(context), fileName);
        OutputStreamWriter writer = new OutputStreamWriter(new FileOutputStream(file));
        writer.write(text);
        writer.flush();
        writer.close();
    }

    public static void deleteFile(Context context, String filename) {
        File file = new File(getCustomFilesDir(context), filename);
        if(file.exists())
            file.delete();
    }

    public static boolean fileExists(Context context, String fileName){
        return new File(getCustomFilesDir(context), fileName).exists();
    }
}