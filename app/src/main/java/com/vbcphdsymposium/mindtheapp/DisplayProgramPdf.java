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

        Intent inputIntent = getIntent();


        Context context = getApplicationContext();
        final File file = new File(context.getFilesDir(), inputIntent.getStringExtra("kind"));

        if (!file.exists()) {
            AssetManager assets=getResources().getAssets();

            try {
                copy(assets.open(inputIntent.getStringExtra("kind")), file);
            }
            catch (IOException e) {
                Log.e("FileProvider", "Exception copying from assets", e);
            }
        }


// let the FileProvider generate an URI for this private file
        final Uri uri = FileProvider.getUriForFile(this, "com.vbcphdsymposium.mindtheapp.fileprovider", file);
// create an intent, so the user can choose which application he/she wants to use to share this file

        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_VIEW);
        intent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        intent.setDataAndType(uri, "application/pdf");

        try {
            startActivity(intent);
        }
        catch (ActivityNotFoundException e) {
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
