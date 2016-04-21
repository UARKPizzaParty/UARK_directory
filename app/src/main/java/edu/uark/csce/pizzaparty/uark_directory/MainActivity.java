package edu.uark.csce.pizzaparty.uark_directory;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button browseDirectoryButton = (Button) findViewById(R.id.browse_directory_button);
        browseDirectoryButton.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                browseDirectoryActivity();
            }
        });
    }

    void browseDirectoryActivity() {
        Intent intent = new Intent(getApplicationContext(), BrowseDirectoryActivity.class);
        startActivity(intent);
    }
}
