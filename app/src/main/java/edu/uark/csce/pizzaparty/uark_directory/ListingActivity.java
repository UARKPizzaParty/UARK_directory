package edu.uark.csce.pizzaparty.uark_directory;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class ListingActivity extends AppCompatActivity {

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
        appId = intent.getIntExtra(BrowseDirectoryActivity.DIRECTORY_ITEM_ID, 0);
        // TODO: Query using appId to get below dummy data from server instead.
        String thumbnailUrl = "http://www.gannett-cdn.com/-mm-/4b0861cca58dd031b99a34aff70078af7249293f/c=1-0-2160-1623&r=x404&c=534x401/local/-/media/2015/01/12/USATODAY/USATODAY/635566745150462086-XXX-Pizza-Hut-gluten-free-pizza.jpg";
        String appName = "Test App Set Through Dummy data awe oh well";
        String appVersion = "1.2.3";
        String appDeveloper = "Test developer set through dummy data too bad i guess";
        String appDescription = "This is the description for the dummy app I guess.  This is the description for the dummy app I guess.  This is the description for the dummy app I guess.  This is the description for the dummy app I guess.  This is the description for the dummy app I guess.  This is the description for the dummy app I guess.  This is the description for the dummy app I guess.  This is the description for the dummy app I guess.  This is the description for the dummy app I guess.  This is the description for the dummy app I guess.  ";


        // Set all the retrieved data into the activity views
        appNameView.setText(appName);
        String appVersionString = getResources().getString(R.string.listing_app_version_prefix) + appVersion;
            appVersionView.setText(appVersionString);
        String appDeveloperString = getResources().getString(R.string.listing_developer_prefix) + appDeveloper;
            appDeveloperView.setText(appDeveloperString);
        descriptionBoxView.setText(appDescription);
            descriptionBoxView.setMovementMethod(new ScrollingMovementMethod()); // Setting description box scroll movement
        new ImageDownloaderTask(appThumbnailView).execute(thumbnailUrl);
//        downloadButton // Not sure how we want to handle download button yet

        //Getting all the thumbnail images for the horizontal scrollview.
        final ArrayList<String> imageScrollViewUrls = new ArrayList<>();
            imageScrollViewUrls.add("http://www.cicis.com/media/1138/pizza_trad_pepperoni.png");
            imageScrollViewUrls.add("http://www.mysticpizza.com/admin/resources/pizza-pepperoni-w857h456.jpg");
            imageScrollViewUrls.add("http://static.comicvine.com/uploads/original/11114/111144184/4791207-9790062099-Pizza.jpg");
            imageScrollViewUrls.add("http://thehillnews.org/wp-content/uploads/2016/03/Delicious-Pizza-Pictures.jpg");
            imageScrollViewUrls.add("http://www.cicis.com/media/1137/pizza_trad_alfredo.png");
        for (int i = 0; i < imageScrollViewUrls.size(); i++) {
            final ImageView imageView = new ImageView(this);
            imageView.setId(i);
            imageView.setPadding(2, 2, 2, 2);
            new ImageDownloaderTask(imageView).execute(imageScrollViewUrls.get(i));
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP); //scales down maintaining aspect ratio
            final int finalI = i;
            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    showImage(imageScrollViewUrls.get(finalI));
                }
            });
            imageScrollview.addView(imageView);
        }

    }

    public void showImage(String url) {
        Dialog builder = new Dialog(this);
        builder.requestWindowFeature(Window.FEATURE_NO_TITLE);
        builder.getWindow().setBackgroundDrawable(
                new ColorDrawable(android.graphics.Color.TRANSPARENT));
        builder.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialogInterface) {
                //nothing;
            }
        });

        ImageView imageView = new ImageView(this);
        new ImageDownloaderTask(imageView).execute(url);

        //TODO: Make this refer to the existing download of the screenshot, instead of redowloading it!
        builder.addContentView(imageView, new RelativeLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT));
        builder.show();
    }


}
