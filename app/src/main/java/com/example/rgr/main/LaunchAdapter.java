package com.example.rgr.main;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.rgr.R;
import com.example.rgr.model.Launch;

import java.util.Collections;
import java.util.List;

public class LaunchAdapter extends RecyclerView.Adapter<LaunchAdapter.LaunchViewHolder>
        implements View.OnClickListener {

    private List<Launch> launches = Collections.emptyList();

    private LaunchListener listener;

    public LaunchAdapter(LaunchListener listener) {
        setHasStableIds(true);
        this.listener = listener;
    }

    public void setRepositories(List<Launch> launches) {
        this.launches = launches;
        notifyDataSetChanged();
    }

    @Override
    public long getItemId(int position) {
        return launches.get(position).getFlightNumber();
    }

    @NonNull
    @Override
    public LaunchViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View root = inflater.inflate(R.layout.item_launch, parent, false);
        return new LaunchViewHolder(root, this);
    }

    @Override
    public void onBindViewHolder(@NonNull LaunchViewHolder holder, int position) {
        Launch launch = launches.get(position);
        holder.missionNameTextView.setText(launch.getMissionName());
        holder.itemView.setTag(launch);
    }

    @Override
    public int getItemCount() {
        return launches.size();
    }

    @Override
    public void onClick(View v) {
        Launch repository = (Launch) v.getTag();
        listener.onLaunchChosen(repository);
    }

    static class LaunchViewHolder extends RecyclerView.ViewHolder {
        private TextView missionNameTextView;
        public LaunchViewHolder(@NonNull View itemView, View.OnClickListener listener) {
            super(itemView);
            missionNameTextView = itemView.findViewById(R.id.missionName);
            itemView.setOnClickListener(listener);
        }
    }

    public interface LaunchListener {
        void onLaunchChosen(Launch repository);
    }
}
