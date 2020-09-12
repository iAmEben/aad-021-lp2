package com.cavalerie.aad_021_lp2.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.Request;
import com.android.volley.toolbox.StringRequest;
import com.cavalerie.aad_021_lp2.Adapter.SkillIQLeaderAdapter;
import com.cavalerie.aad_021_lp2.Model.SkillIq;
import com.cavalerie.aad_021_lp2.R;
import com.cavalerie.aad_021_lp2.Util.MySingleton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class SkillIQLeaderFragment extends Fragment {

    private RecyclerView iQRecyclerView;
    private SkillIQLeaderAdapter adapter;
    private List<SkillIq> leaderList = new ArrayList<SkillIq>();

    //for loading progress dialog
//    private ProgressDialog mprogressDialog;

    public SkillIQLeaderFragment(){

    }

    public static SkillIQLeaderFragment newInstance() {
        return new SkillIQLeaderFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_skill_i_q_leader, container, false);

        initD(v);
        LoadLead();

        return v;
    }

    private void LoadLead() {
        System.out.println("started request");

//        mprogressDialog = new ProgressDialog(getContext());
//        mprogressDialog.setCanceledOnTouchOutside(false);
//        mprogressDialog.setMessage("Loading, please wait ...");
//        mprogressDialog.show();

        StringRequest stringRequest = new StringRequest(Request.Method.GET, "https://gadsapi.herokuapp.com/api/skilliq",
                response -> {
                    try {
                        System.out.println("response : " + response);
                        JSONArray jsonArray = new JSONArray(response);
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject jsonObject = jsonArray.getJSONObject(i);
                            leaderList.add(new SkillIq(
                                    jsonObject.getString("name"),
                                    jsonObject.getString("score"),
                                    jsonObject.getString("country"),
                                    jsonObject.getString("badgeUrl")
                            ));
                            System.out.println(jsonObject.getString("score"));
                        }

                        adapter = new SkillIQLeaderAdapter(getContext(), leaderList);
                        iQRecyclerView.setAdapter(adapter);

                        //after binding i stop progress dialog
//                        mprogressDialog.dismiss();

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                },
                error -> {
                    error.printStackTrace();
                    //if i have any error i stop progress dialog
//                    mprogressDialog.dismiss();
                });

        // you need this for execute request (-_-)
        MySingleton.getInstance(getContext()).addToRequestqueue(stringRequest);


    }

    private void initD(View v) {
        iQRecyclerView = v.findViewById(R.id.iqRecyclerView);
        iQRecyclerView.setLayoutManager(new LinearLayoutManager((getContext())));
        iQRecyclerView.setHasFixedSize(true);
    }
}