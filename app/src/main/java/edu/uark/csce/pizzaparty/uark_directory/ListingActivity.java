package edu.uark.csce.pizzaparty.uark_directory;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;
import com.koushikdutta.ion.ProgressCallback;

import java.io.File;
import java.util.ArrayList;

import me.grantland.widget.AutofitHelper;

public class ListingActivity extends AppCompatActivity {

    private static final String TAG = "Listing";
    private int appId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listing);

        // Retrieve references to all the views in the activity
        TextView appNameView = (TextView) findViewById(R.id.app_title);
        TextView appVersionView = (TextView) findViewById(R.id.app_version);
        TextView appDeveloperView = (TextView) findViewById(R.id.app_developer);
        TextView descriptionBoxView = (TextView) findViewById(R.id.description_box);
        ImageView appThumbnailView = (ImageView) findViewById(R.id.app_thumbnail);
        Button downloadButton = (Button) findViewById(R.id.download_button);
        LinearLayout imageScrollview = (LinearLayout) findViewById(R.id.scrollview_inner_layout);

        // Retrieve values based on the appId received from BrowseDirectoryActivity
        Intent intent = getIntent();
        final App app = (App) intent.getSerializableExtra(BrowseDirectoryActivity.APP_ID);

        // Set all the retrieved data into the activity views
        appNameView.setText(app.getName());
        String appVersionString = getResources().getString(R.string.listing_app_version_prefix) + app.getVersion();
        appVersionView.setText(appVersionString);
        String appDeveloperString = getResources().getString(R.string.listing_developer_prefix) + app.getDeveloper();
        appDeveloperView.setText(appDeveloperString);
        descriptionBoxView.setText(app.getDescription());
        descriptionBoxView.setMovementMethod(new ScrollingMovementMethod()); // Setting description box scroll movement
        new ImageDownloaderTask(appThumbnailView).execute(app.getThumbURL());

        final File file = new File(this.getFilesDir(), app.getName() + getString(R.string.apk_extension));
        Log.i(TAG, file.getAbsolutePath());
        downloadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Ion.with(ListingActivity.this)
                        .load(app.getApkURL())
                        .progress(new ProgressCallback() {
                            @Override
                            public void onProgress(long downloaded, long total) {
                                System.out.println("" + downloaded + " / " + total);
                            }
                        })
                        .write(file)
                        .setCallback(new FutureCallback<File>() {
                            @Override
                            public void onCompleted(Exception e, File file) {
                                if (file != null) {
                                    Log.i(TAG, "on complete: " + file.getAbsolutePath());
                                    /*Intent promptInstall = new Intent(Intent.ACTION_VIEW)
                                            .setDataAndType(Uri.parse("file://" + file.getAbsolutePath()),
                                                    "application/vnd.android.package-archive");
                                    startActivity(promptInstall);*/
                                }
                                if (e != null) {
                                    Log.e(TAG, "error downloading", e);
                                }
                                Log.i(TAG, "download complete");
                            }
                        });
            }
        });

        //Getting all the thumbnail images for the horizontal scrollview.
        final ArrayList<String> imageScrollViewUrls = new ArrayList<>();
        for (String url : app.getScreenShotUrls()) {
            imageScrollViewUrls.add(url);
        }
        for (int i = 0; i < imageScrollViewUrls.size(); i++) {
            final ImageView imageView = new ImageView(this);
            imageView.setId(i);
            imageView.setPadding(2, 2, 2, 2);
            new ImageDownloaderTask(imageView).execute(imageScrollViewUrls.get(i));
            imageView.setScaleType(ImageView.ScaleType.FIT_CENTER); //scales down maintaining aspect ratio
            final int finalI = i;
            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    showImage(imageView.getDrawable());
                }
            });
            imageScrollview.addView(imageView);
        }

    }

    public void onClickTitle(View v) {
        TextView titleView = (TextView) findViewById(R.id.app_title);
        AutofitHelper.create(titleView);

        //TODO: Support click again to resize back to original
    }

    public void onClickDev(View view) {
        TextView devView = (TextView) findViewById(R.id.app_developer);
        AutofitHelper.create(devView);

        //TODO: Support click again to resize back to original
    }


    public void showImage(Drawable img) {
        Dialog builder = new Dialog(this);
        builder.requestWindowFeature(Window.FEATURE_NO_TITLE);
        builder.getWindow().setBackgroundDrawable(
                new ColorDrawable(android.graphics.Color.TRANSPARENT));
        builder.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialogInterface) {
                Log.i(TAG, "Closed screenshot blowup.");
            }
        });

        ImageView imageView = new ImageView(this);
        imageView.setImageDrawable(img);

        builder.addContentView(imageView, new RelativeLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT));
        builder.show();
        Log.i(TAG, "Showed fullscreen screenshot.");
    }



}
