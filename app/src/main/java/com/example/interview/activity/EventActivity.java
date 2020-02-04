package com.example.interview.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.example.interview.MockedData;
import com.example.interview.R;
import com.example.interview.adapter.ParticipantAdapter;
import com.example.interview.model.User;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class EventActivity extends AppCompatActivity {

    private List<User> participantList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //Widget Initialisation
        TextView eventDate = findViewById(R.id.event_date);
        TextView eventTime = findViewById(R.id.event_time);
        RecyclerView participantsList = findViewById(R.id.event_participants_list);
        participantsList.setLayoutManager(new LinearLayoutManager(EventActivity.this));
        participantsList.setAdapter(new ParticipantAdapter(participantList, EventActivity.this));

        MockedData mockedData = new MockedData();
        int userId = getIntent().getIntExtra("userId", 2);

        try {
            JSONArray jsonArray = new JSONArray(mockedData.getMeetingsForUser(new User(userId)));
            JSONObject jsonObject = jsonArray.getJSONObject(0);

            eventDate.setText(jsonObject.getString("date"));
            eventTime.setText(jsonObject.getString("time"));

            JSONArray jsonString = jsonObject.getJSONArray("participants");
            for (int i = 0; i < jsonString.length(); i++) {
//                stringList.add(jsonString.getJSONArray(i));
                Log.d("Json_Array_List_Value", jsonString.getString(i));
                JSONObject userData = new JSONObject(jsonString.getString(i));
                String name = userData.getString("name");
                String pp = userData.getString("profilePictures");

                participantList.add(new User(name, pp));

            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
}
