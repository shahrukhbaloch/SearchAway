package com.shahrukhbaloch.searchaway.util;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.util.Log;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

/**
 * Created by shahrukh.baloch on 4/9/15.
 */
public class FileUtils {

    private static final String DIRECTORY_NAME = "Search Away Photos";

    public static boolean saveBitmapToFile(Bitmap bitmap, Context context) {
        if (isExternalStorageWritable()) {
            final String fileName = generateFileName();
            final File directory = getDownloadsStorageDir(DIRECTORY_NAME);
            final File bitmapFile = new File(directory, fileName);

            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.PNG, 0 /* ignored for PNG */, bos);
            byte[] bitmapdata = bos.toByteArray();

            // write the bytes in file
            try {
                FileOutputStream fos = new FileOutputStream(bitmapFile);
                fos.write(bitmapdata);
                fos.flush();
                fos.close();
                refreshGallery(bitmapFile, context);
                return true;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    private static void refreshGallery(File bitmapFile, Context context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Intent mediaScanIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
            Uri contentUri = Uri.fromFile(bitmapFile);
            mediaScanIntent.setData(contentUri);
            context.sendBroadcast(mediaScanIntent);
        } else {
            context.sendBroadcast(new Intent(Intent.ACTION_MEDIA_MOUNTED, Uri.parse("file://" + getDownloadsStorageDir(DIRECTORY_NAME))));
        }
    }


    private static String generateFileName() {
        String fileName = null;
        SimpleDateFormat sdf = new SimpleDateFormat("MMdyyyy_HHmmss", Locale.getDefault());
        fileName = sdf.format(Calendar.getInstance().getTime());
        fileName = fileName.concat(".png");
        return fileName;
    }

    private static boolean isExternalStorageWritable() {
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state)) {
            return true;
        }
        return false;
    }

    private static File getDownloadsStorageDir(String directoryName) {
        File file = new File(Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_DOWNLOADS), directoryName);
        if (file.mkdirs()) Log.d(FileUtils.class.getSimpleName(), "Directory created");
        return file;
    }
}
