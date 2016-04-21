package edu.uark.csce.pizzaparty.uark_directory;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by silas on 4/11/2016.
 */
public class DirectoryListItemAdapter extends ArrayAdapter<DirectoryListItem> {


    public DirectoryListItemAdapter(Context context, int resource, List<DirectoryListItem> itemList) {
        super(context, resource, itemList);
    }

    public View getView(int position, View view, ViewGroup parent) {
        ImageView thumbnailImageView;
        TextView appNameView;
        TextView appDeveloperView;
        if(view == null) {
            LayoutInflater layoutInflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = layoutInflater.inflate(R.layout.directory_list_item_layout, null);
        }

        //step 1: retrieve the data
        DirectoryListItem item = getItem(position);


        //step 2: show the data on the UI
        if (item != null) {
            String appNameString = item.getAppName();
            String appDeveloperString = item.getAppDeveloper();

            thumbnailImageView = (ImageView) view.findViewById((R.id.thumbnailImage));
            appNameView = (TextView) view.findViewById(R.id.appName);
            appDeveloperView = (TextView) view.findViewById(R.id.appDeveloper);

            if (thumbnailImageView != null) {
                new ImageDownloaderTask(thumbnailImageView).execute(item.getThumbnailUrl());
            }
            appNameView.setText(appNameString);
            appDeveloperView.setText(appDeveloperString);

        }

        return view;
    }




}
