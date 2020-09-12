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
import com.cavalerie.aad_021_lp2.Model.SkillIq;
import com.cavalerie.aad_021_lp2.R;

import java.util.List;

public class SkillIQLeaderAdapter extends RecyclerView.Adapter<SkillIQLeaderAdapter.SkillViewHolder> {

    Context context;
    List<SkillIq> leaderList;

    public SkillIQLeaderAdapter(Context context, List<SkillIq> leaderList) {
        this.context = context;
        this.leaderList = leaderList;
    }
    @NonNull
    @Override
    public SkillIQLeaderAdapter.SkillViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_skill_i_q_leader_model, parent, false);

        return new SkillViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SkillIQLeaderAdapter.SkillViewHolder holder, int position) {
        SkillIq skillIq = leaderList.get(position);
        holder.bind(skillIq);

    }

    @Override
    public int getItemCount() {
        return leaderList.size();
    }

    public class SkillViewHolder extends RecyclerView.ViewHolder {

        private ImageView img_top_leader;
        private TextView leader_name, leader_description;


        public SkillViewHolder(@NonNull View itemView) {
            super(itemView);
            img_top_leader = (ImageView) itemView.findViewById(R.id.img_top_leader);
            leader_name = (TextView) itemView.findViewById(R.id.leader_name);
            leader_description = (TextView) itemView.findViewById(R.id.leader_descripion);
        }

        public void bind(SkillIq skillIq) {
            // i use Glide library for load image url into ImageView
            Glide.with(context).load(skillIq.getBadgeUrl()).into(img_top_leader);

            leader_name.setText(skillIq.getName());
            leader_description.setText(skillIq.getScore() + " skill IQ Score, " + skillIq.getCountry());

        }
    }
}
