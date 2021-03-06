package com.VBCPhDSymposium.MindTheApp;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.content.FileProvider;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by sommerc on 10/25/2016.
 */


public class DisplayProgramPdf extends Activity
{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // get intent and "kind" = the pdf
        Intent inputIntent = getIntent();

        Context context = getApplicationContext();
        final File file = new File(context.getFilesDir(), inputIntent.getStringExtra("kind"));

        // if file hasn't been copied from asset folder, do it
        if (!file.exists()) {
            // copy from assets folder
            AssetManager assets=getResources().getAssets();
            try {
                copy(assets.open(inputIntent.getStringExtra("kind")), file);
            }
            catch (IOException e) {
                Log.e("FileProvider", "Exception copying from assets", e);
            }
        }

        // create Uri
        final Uri uri = FileProvider.getUriForFile(this, "com.VBCPhDSymposium.MindTheApp.fileprovider", file);

        // start ACTION_VIEW intent for pdf
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_VIEW);
        intent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        intent.setDataAndType(uri, "application/pdf");

        // start activity
        try {
            startActivity(intent);
        }
        catch (ActivityNotFoundException e) {
            // if no pdf viewer installed, refer to play store
            Intent playStoreIntent = new Intent(Intent.ACTION_VIEW,
            Uri.parse("https://play.google.com/store/apps/details?id=com.adobe.reader&hl=en"));
            startActivity(playStoreIntent);
        }
        finish();

    }
    static private void copy(InputStream in, File dst) throws IOException {
        FileOutputStream out=new FileOutputStream(dst);
        byte[] buf=new byte[1024];
        int len;

        while ((len=in.read(buf)) > 0) {
            out.write(buf, 0, len);
        }

        in.close();
        out.close();
    }

}
