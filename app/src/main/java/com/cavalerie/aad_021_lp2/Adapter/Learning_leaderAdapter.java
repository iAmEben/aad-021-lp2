package com.cavalerie.aad_021_lp2.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.cavalerie.aad_021_lp2.Model.Leader;
import com.cavalerie.aad_021_lp2.R;

import java.util.List;

public class Learning_leaderAdapter extends RecyclerView.Adapter<Learning_leaderAdapter.LeaderViewHolder> {

    Context context;
    List<Leader> leaderList;

    public Learning_leaderAdapter(Context context, List<Leader> leaderList) {
        this.context = context;
        this.leaderList = leaderList;
    }

    @NonNull
    @Override
    public LeaderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_skill_i_q_leader_model, parent, false);

        return new LeaderViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LeaderViewHolder holder, int position) {
        Leader leader = leaderList.get(position);
        holder.bind(leader);
    }

    @Override
    public int getItemCount() {
        return leaderList.size();
    }

    public class LeaderViewHolder extends RecyclerView.ViewHolder {

        private ImageView img_top_leader;
        private TextView leader_name, leader_descripion;

        public LeaderViewHolder(@NonNull View itemView) {
            super(itemView);

            img_top_leader = (ImageView) itemView.findViewById(R.id.img_top_leader);
            leader_name = (TextView) itemView.findViewById(R.id.leader_name);
            leader_descripion = (TextView) itemView.findViewById(R.id.leader_descripion);
        }

        public void bind(Leader leader) {

            // i use Glide library for load image url into ImageView
            Glide.with(context).load(leader.getBadgeUrl()).into(img_top_leader);

            leader_name.setText(leader.getName());
            leader_descripion.setText(leader.getHours() + " learning hours, " + leader.getCountry());

        }
    }
}
