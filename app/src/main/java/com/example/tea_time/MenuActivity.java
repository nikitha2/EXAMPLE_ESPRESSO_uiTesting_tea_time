/*
* Copyright (C) 2017 The Android Open Source Project
*
* Licensed under the Apache License, Version 2.0 (the "License");
* you may not use this file except in compliance with the License.
* You may obtain a copy of the License at
*
*  	http://www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS,
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
* See the License for the specific language governing permissions and
* limitations under the License.
*/

package com.example.tea_time;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.test.espresso.IdlingResource;

import com.example.tea_time.IdlingResource.ImageDownloader;
import com.example.tea_time.IdlingResource.SimpleIdlingResource;
import com.example.tea_time.model.Tea;

import java.util.ArrayList;

public class MenuActivity extends AppCompatActivity implements ImageDownloader.DelayerCallback {
    Intent mTeaIntent;
    TeaMenuAdapter adapter;
    // The Idling Resource which will be null in production.
    @Nullable private SimpleIdlingResource mIdlingResource;

    @Override
    protected void onResume() {
        super.onResume();
        ImageDownloader.downloadImage(this,this,mIdlingResource);
    }

    public final static String EXTRA_TEA_NAME = "com.example.android.teatime.EXTRA_TEA_NAME";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
//        Toolbar menuToolbar = (Toolbar) findViewById(R.id.menu_toolbar);
//        setSupportActionBar(menuToolbar);
        getSupportActionBar().setTitle(getString(R.string.menu_title));

        // Create an ArrayList of teas
        final ArrayList<Tea> teas = new ArrayList();
        teas.add(new Tea(getString(R.string.black_tea_name), R.drawable.black_tea));
        teas.add(new Tea(getString(R.string.green_tea_name), R.drawable.green_tea));
        teas.add(new Tea(getString(R.string.white_tea_name), R.drawable.white_tea));
        teas.add(new Tea(getString(R.string.oolong_tea_name), R.drawable.oolong_tea));
        teas.add(new Tea(getString(R.string.honey_lemon_tea_name), R.drawable.honey_lemon_tea));
        teas.add(new Tea(getString(R.string.chamomile_tea_name), R.drawable.chamomile_tea));

        // Create a {@link TeaAdapter}, whose data source is a list of {@link Tea}s.
        // The adapter know how to create grid items for each item in the list.
        GridView gridview = (GridView) findViewById(R.id.tea_grid_view);
        adapter = new TeaMenuAdapter(this, R.layout.grid_item_layout, new ArrayList());
        gridview.setAdapter(adapter);
// need to create an instance of  mIdlingResource here as @Before runs after activity is created. if we need delay after activity is creates
// we dont need line 76 as @Before in test IdlingResourceActivityTest will create an instance.
        getIdlingResource();
        ImageDownloader.downloadImage(this,this,mIdlingResource);

        // Set a click listener on that View
        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {

                Tea item = (Tea) adapterView.getItemAtPosition(position);
                // Set the intent to open the {@link OrderActivity}
                mTeaIntent = new Intent(MenuActivity.this, OrderActivity.class);
                String teaName = item.getTeaName();
                mTeaIntent.putExtra(EXTRA_TEA_NAME, teaName);
                startActivity(mTeaIntent);

            }
        });
    }




    /**
     * Only called from test, creates and returns a new {@link SimpleIdlingResource}.
     */
    @VisibleForTesting
    @NonNull
    public IdlingResource getIdlingResource() {
        if (mIdlingResource == null) {
            mIdlingResource = new SimpleIdlingResource();
        }
        return mIdlingResource;
    }

    @Override
    public void onDone(ArrayList<Tea> teas) {
        adapter.setData(teas);
    }
}