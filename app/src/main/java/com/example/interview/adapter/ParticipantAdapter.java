package com.example.interview.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.interview.R;
import com.example.interview.model.User;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ParticipantAdapter extends RecyclerView.Adapter<ParticipantAdapter.MyViewHolder> {

    private List<User> participantList;
    private Context context;

    public ParticipantAdapter(List<User> participantList, Context context) {
        this.participantList = participantList;
        this.context = context;
    }

    @NonNull
    @Override
    public ParticipantAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.event_participants_rv_layout, parent, false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ParticipantAdapter.MyViewHolder holder, int position) {

        User user = participantList.get(position);

        holder.participantName.setText(user.getName());
        Picasso.get().load(user.getProfilePicture()).into(holder.participantPP);
    }

    @Override
    public int getItemCount() {
        return participantList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView participantName;
        ImageView participantPP;

        MyViewHolder(@NonNull View itemView) {
            super(itemView);
            participantName = itemView.findViewById(R.id.participant_name);
            participantPP = itemView.findViewById(R.id.participant_profile_picture);
        }
    }
}
