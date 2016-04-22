package edu.uark.csce.pizzaparty.uark_directory;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

import static android.provider.BaseColumns._ID;

public class BrowseDirectoryActivity extends AppCompatActivity {

    private static final String TAG = "TODOITEMLIST";

    public static final String DIRECTORY_ITEM_ID = "directory_item_id";

    private ArrayList<DirectoryListItem> itemArrayList;
    private ArrayAdapter<DirectoryListItem> itemArrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_browse_directory);

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


    private void loadAllDirectoryListItems () {
        String sortOrder =  _ID + " ASC";


        /** TODO: remove enclosed dummy data **/
        ///DUMBY DATA PLS DELETO
        int appId = 1000;
        String dumbyThumbnailUrl = null; //null url should use the default image
        String dumbyAppName = "Test App";
        String dumbyAppDev = "Test dev";
        DirectoryListItem dummyItem = new DirectoryListItem(1000, dumbyThumbnailUrl, dumbyAppName, dumbyAppDev);
        Log.i(TAG, "First dumb bitmap: " + dumbyThumbnailUrl);

        ///DUMBY DATA PLS DELETO
        int appId2 = 1001;
        String dumbyThumbnailUrl2 = "http://www.gannett-cdn.com/-mm-/4b0861cca58dd031b99a34aff70078af7249293f/c=1-0-2160-1623&r=x404&c=534x401/local/-/media/2015/01/12/USATODAY/USATODAY/635566745150462086-XXX-Pizza-Hut-gluten-free-pizza.jpg";
        String dumbyAppName2 = "Test App2";
        String dumbyAppDev2 = "Test dev2";
        DirectoryListItem dummyItem2 = new DirectoryListItem(1001, dumbyThumbnailUrl2, dumbyAppName2, dumbyAppDev2);
        Log.i(TAG, "Second dumb bitmap: " + dumbyThumbnailUrl2);

        itemArrayList.add(dummyItem);
        itemArrayList.add(dummyItem2);
        itemArrayAdapter.notifyDataSetChanged();
        //** TODO: remove above enclosed dummy data **//


        //We will probably need to use some kind of cursor to parse through data that we retrieve
        //from server.  Below is the itemToDo list code that we can use as a skeleton to build the code

//        Cursor cursor = itemDB.query(TABLE_NAME,
//                null, null, null, null, null, sortOrder);
//        if(cursor!=null && cursor.getCount()>0) {
//            cursor.moveToFirst();
//            Log.d(TAG, "Loading: total items = " + cursor.getCount());
//            toDoItemArrayList.clear();
//            while (!cursor.isAfterLast()) {
//                ToDoItem item = new ToDoItem(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_TITLE)),
//                        cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_CONTENT)),
//                        cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_DATE)));
//                toDoItemArrayList.add(item);
//                Log.d(TAG, "Loading item " + cursor.getInt(cursor.getColumnIndexOrThrow(_ID)));
//                cursor.moveToNext();
//            }
//            toDoItemArrayAdapter.notifyDataSetChanged();
//            cursor.close();
//        }
    }

    private void listingActivity(int appId) {
        Intent intent = new Intent(getApplicationContext(), ListingActivity.class);
        intent.putExtra(DIRECTORY_ITEM_ID, appId);
        startActivity(intent);
    }
}
