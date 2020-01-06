package com.example.myapis;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ShareCompat;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private EditText mWebsiteEditText;
    private EditText mLocationEditText;
    private EditText mShareTextEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mWebsiteEditText = findViewById(R.id.website_edittext);
        mLocationEditText = findViewById(R.id.location_edittext);
        mShareTextEditText = findViewById(R.id.share_edittext);


    }

    public void openWebsite(View view) {
        // Get the URL text
        String url = mWebsiteEditText.getText().toString();

        // Parse the URI and create the intent.
        Uri webpage = Uri.parse(url);
        Intent intent = new Intent(Intent.ACTION_VIEW, webpage);
        if (intent.resolveActivity(getPackageManager()) != null){ // This request that matches your Intent
            // action and data with the Intent filters for installed apps on the device.
            startActivity(intent);
        }
        else {
            Log.d("ImplicitIntents", "Can't handle this!");
        }

    }

    public void openLocation(View view) {
        String loc = mLocationEditText.getText().toString();

        Uri addressUri = Uri.parse("geo:0,0?q=" + loc);
        Intent intent = new Intent(Intent.ACTION_VIEW, addressUri);
        // Find an activity to handle the intent, and start that activity.
        if (intent.resolveActivity(getPackageManager())!= null){
            startActivity(intent);
        }
        else {
            Log.d("ImplicitIntents", "Can't handle this location!");
        }


    }

    public void shareText(View view) {
        String txt = mShareTextEditText.getText().toString();

        String mimeType = "text/plain";
        ShareCompat.IntentBuilder.from(this)//The Activity that launches this share Intent (this).
                .setType(mimeType) //The MIME type of the item to be shared.
                .setChooserTitle("Share this text with: ") //The title that appears on the system app chooser.
                .setText(txt) //The actual text to be shared
                .startChooser(); //Show the system app chooser and send the Intent.
    }

    public void camera(View view) {
        Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
        startActivity(intent);
    }
}
