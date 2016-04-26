package edu.uark.csce.pizzaparty.uark_directory;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class BrowseDirectoryActivity extends AppCompatActivity {

    public static final String APP_ID = "appId";
    private static final String TAG = "TODOITEMLIST";
    private ArrayList<DirectoryListItem> itemArrayList;
    private ArrayAdapter<DirectoryListItem> itemArrayAdapter;
    private Map<Integer, App> appMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_browse_directory);

        appMap = new HashMap<>();
        itemArrayList = new ArrayList<>();
        itemArrayAdapter = new DirectoryListItemAdapter(
                this, R.layout.directory_list_item_layout, itemArrayList);
        final ListView itemListView = (ListView) findViewById(R.id.directory_list);
        itemListView.setAdapter(itemArrayAdapter);

        loadAllDirectoryListItems();

        //listener to get to the ListingActivity
        itemListView.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {

                    @Override
                    public void onItemClick(AdapterView<?> arg0, View view,
                                            int position, long id) {
                        Object item = itemListView.getItemAtPosition(position);
                        Log.i(TAG, item.getClass().toString());
                        DirectoryListItem itemToView = ((DirectoryListItem) item);
                        int itemToViewId = itemToView.getAppId();
                        listingActivity(itemToViewId);
                    }
                }
        );
    }

    private void loadAllDirectoryListItems() {
        Ion.with(this)
                .load(getString(R.string.base_uri) + ":8080" + getString(R.string.get_all_apps))
                .asJsonArray()
                .setCallback(new FutureCallback<JsonArray>() {
                    @Override
                    public void onCompleted(Exception e, JsonArray result) {
                        if (e != null) {
                            Log.e(TAG, "Error getting apps list", e);
                        } else {
                            for (int i = 0; i < result.size(); i++) {
                                JsonObject jsonObject = result.get(i).getAsJsonObject();
                                App app = new App();
                                app.setId(jsonObject.get("id").getAsInt());
                                app.setName(jsonObject.get("name").getAsString());
                                app.setDeveloper(jsonObject.get("developer").getAsString());
                                app.setVersion(jsonObject.get("version").getAsString());
                                app.setDescription(jsonObject.get("description").getAsString());
                                app.setCreateDate(new Date(jsonObject.get("createDate").getAsLong()));
                                app.setNumImages(jsonObject.get("numImages").getAsInt());
                                app.setThumbURL(getString(R.string.base_uri) + "/" + app.getName() + getString(R.string.png_extension));
                                app.setApkURL(getString(R.string.base_uri) + "/" + jsonObject.get("apkName").getAsString());
                                app.setApkName(jsonObject.get("apkName").getAsString());
                                app.setScreenShotUrls(new ArrayList<String>());
                                for (int j = 1; j <= app.getNumImages(); j++) {
                                    app.getScreenShotUrls().add(getString(R.string.base_uri) + "/" + app.getName() + j + getString(R.string.png_extension));
                                }
                                Log.i(TAG, app.toString());
                                appMap.put(app.getId(), app);
                            }
                            for (App app : appMap.values()) {
                                DirectoryListItem item = new DirectoryListItem(app.getId(), app.getThumbURL(), app.getName(), app.getDeveloper());
                                itemArrayList.add(item);
                            }
                            itemArrayAdapter.notifyDataSetChanged();
                        }
                    }
                });
    }

    private void listingActivity(int appId) {
        Intent intent = new Intent(getApplicationContext(), ListingActivity.class);
        intent.putExtra(APP_ID, appMap.get(appId));
        startActivity(intent);
    }
}
