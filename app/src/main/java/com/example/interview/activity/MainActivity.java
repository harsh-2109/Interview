package com.example.interview.activity;

import android.content.Context;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.interview.MockedData;
import com.example.interview.R;
import com.example.interview.adapter.EventAdapter;
import com.example.interview.model.User;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<String> eventList = new ArrayList<>();
    private int id;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        Intent intent = new Intent(this, WebviewActivity.class);
//        startActivityForResult(intent, 0);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //ViewWidgets
        ImageView mProfilePicture = findViewById(R.id.content_iv_profile);
        TextView mProfileName = findViewById(R.id.content_tv_profile);
        RecyclerView mMeetingList = findViewById(R.id.content_rv_meetings);

        MockedData data = new MockedData();

        Log.e("current_user_data: ", data.getCurrentUser());

        //CurrentUser Profile Content
        try {
            JSONObject object = new JSONObject(data.getCurrentUser());
            id = object.getInt("id");
            mProfileName.setText(object.getString("name"));
            Picasso.get().load(object.getString("profilePicture")).resize((int) convertDpToPixel(150, MainActivity.this), (int) convertDpToPixel(150, MainActivity.this)).into(mProfilePicture);

        } catch (JSONException e) {
            e.printStackTrace();
        }

        //RecyclerView
        try {
            JSONArray jsonArray = new JSONArray(data.getMeetingsForUser(new User(id)));
            JSONObject eventObject = jsonArray.getJSONObject(0);

            Log.d("eventList", eventObject.getString("name"));
            eventList.add(eventObject.getString("name"));

            mMeetingList.setLayoutManager(new LinearLayoutManager(MainActivity.this));
            mMeetingList.setAdapter(new EventAdapter(eventList, id, MainActivity.this));

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public static float convertDpToPixel(float dp, Context context) {
        return dp * ((float) context.getResources().getDisplayMetrics().densityDpi / DisplayMetrics.DENSITY_DEFAULT);
    }
}
