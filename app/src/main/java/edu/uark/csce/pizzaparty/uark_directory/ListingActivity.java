package edu.uark.csce.pizzaparty.uark_directory;

import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ListingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listing);

        // Setting description box scroll movement
        TextView descriptionBox = (TextView) findViewById(R.id.listing_description_box);
        descriptionBox.setMovementMethod(new ScrollingMovementMethod());

        LinearLayout layout = (LinearLayout) findViewById(R.id.scrollview_inner_layout);
        for (int i = 0; i < 10; i++) {
            ImageView imageView = new ImageView(this);
            imageView.setId(i);
            imageView.setPadding(2, 2, 2, 2);
            imageView.setImageBitmap(BitmapFactory.decodeResource(
                    getResources(), R.drawable.placeholder));
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);

            layout.addView(imageView);
            imageView.getLayoutParams().height = 300;
            imageView.getLayoutParams().width = 225;
        }

    }
}
