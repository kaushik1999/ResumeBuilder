package com.sultanofcardio.resumebuildersultanofcardio.util;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;

/**
 * @author sultanofcardio
 */

public class PermissionUtils {
    public static final String EXTERNAL_STORAGE = "android.permission.WRITE_EXTERNAL_STORAGE";
    public static final int EXTERNAL_STORAGE_CODE = 200;

    // TODO: Be sure to override onRequestPermissionsResult method in Activity class

    @SuppressLint("NewApi")
    public static boolean needRequest(AppCompatActivity context, String permission){
        return Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP_MR1 &&
                context.checkSelfPermission(permission) != PackageManager.PERMISSION_GRANTED;
    }

    @TargetApi(Build.VERSION_CODES.M)
    public static void requestExternalStorage(final AppCompatActivity context, String reason){
        final String[] permissions = new String[]{EXTERNAL_STORAGE};

        ScreenUtils.showDialog(context, "External Storage Needed", reason,
                "OK", "Decline", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        context.requestPermissions(permissions, EXTERNAL_STORAGE_CODE);
                    }
                }, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
    }

}