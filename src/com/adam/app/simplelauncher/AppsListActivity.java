/**
 * ===========================================================================
 * Copyright Adam Sample code
 * All Rights Reserved
 * ===========================================================================
 * 
 * File Name: AppsListActivity.java
 * Brief: This file implements App list ui
 * 
 * Author: AdamChen
 * Create Date: 2018/1/17
 */

package com.adam.app.simplelauncher;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * <h1>AppsListActivity</h1>
 * 
 * @autor AdamChen
 * @since 2018/1/17
 */
public class AppsListActivity extends Activity {

    private PackageManager mPackageManager;
    private List<AppDetail> mApps;
    private ListView mList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apps_list);

        // Load app information
        loadApps();

        // Craete list view
        loadListView();

        // Register the list listner
        addClickListener();
    }

    /**
     * 
     * <h1>loadApps</h1>
     * 
     * @return NoThing
     * 
     */
    private void loadApps() {
        mPackageManager = getPackageManager();
        mApps = new ArrayList<AppDetail>();

        Intent appintent = new Intent();
        appintent.setAction(Intent.ACTION_MAIN);
        appintent.addCategory(Intent.CATEGORY_LAUNCHER);

        List<ResolveInfo> availableActivities = mPackageManager
                .queryIntentActivities(appintent, 0);
        // Add app information in the list
        for (ResolveInfo ri : availableActivities) {
            AppDetail app = new AppDetail();
            app.label = ri.loadLabel(mPackageManager);
            app.name = ri.activityInfo.packageName;
            app.icon = ri.activityInfo.loadIcon(mPackageManager);

            // Add app list.
            mApps.add(app);
        }
    }

    /**
     * 
     * <h1>loadListView</h1>
     * 
     * @return No thing
     * 
     */
    private void loadListView() {

        mList = (ListView) findViewById(R.id.apps_list);

        ArrayAdapter<AppDetail> adapter = new ListAdapter(this,
                R.layout.list_item, mApps);

        mList.setAdapter(adapter);
    }

    /**
     * 
     * <h1>addClickListener</h1>
     *
     * @return No thing
     *
     */
    private void addClickListener() {
        mList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> av, View v, int pos, long id) {
                
                Intent i = mPackageManager.getLaunchIntentForPackage(mApps
                        .get(pos).name.toString());
                // Start the selected activity
                AppsListActivity.this.startActivity(i);
            }
        });
    }

    /**
     * 
     * <h1>ListAdapter</h1>
     * 
     * @autor AdamChen
     * @since 2018/1/17
     */
    private final class ListAdapter extends ArrayAdapter<AppDetail> {
        
        /**
         * 
         * Constructor description
         *
         * @param context
         * @param textViewResourceId
         * @param objects
         */
        private ListAdapter(Context context, int textViewResourceId,
                List<AppDetail> objects) {
            super(context, textViewResourceId, objects);
        }

        /**
         * 
         * <h1>getView</h1>
         *
         * @param position
         * @param convertView
         * @param parent
         * @return No thing
         */
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            if (convertView == null) {
                convertView = getLayoutInflater().inflate(R.layout.list_item,
                        null);
            }

            ImageView appIcon = (ImageView) convertView
                    .findViewById(R.id.item_app_icon);
            appIcon.setImageDrawable(mApps.get(position).icon);

            TextView appLabel = (TextView) convertView
                    .findViewById(R.id.item_app_label);
            appLabel.setText(mApps.get(position).label);

            TextView appName = (TextView) convertView
                    .findViewById(R.id.item_app_name);
            appName.setText(mApps.get(position).name);

            return convertView;
        }
    }

    /**
     * 
     * <h1>AppDetail</h1>
     * 
     * @autor AdamChen
     * @since 2018/1/18
     */
    private class AppDetail {

        CharSequence label;
        CharSequence name;
        Drawable icon;
    }

}
