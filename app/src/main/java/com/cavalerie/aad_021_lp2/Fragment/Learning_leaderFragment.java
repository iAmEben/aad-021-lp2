package com.cavalerie.aad_021_lp2.Fragment;

import android.app.ProgressDialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.Request;
import com.android.volley.toolbox.StringRequest;
import com.cavalerie.aad_021_lp2.Adapter.Learning_leaderAdapter;
import com.cavalerie.aad_021_lp2.Model.Leader;
import com.cavalerie.aad_021_lp2.R;
import com.cavalerie.aad_021_lp2.Util.MySingleton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Learning_leaderFragment extends Fragment {

    private RecyclerView recyclerViewLeader;
    private Learning_leaderAdapter adapter;
    private List<Leader> leaderList = new ArrayList<>();

    //loading dialog
    private ProgressDialog progressDialog;

    public Learning_leaderFragment() {
        // Required empty public constructor
    }

    public static Learning_leaderFragment newInstance() {
        return new Learning_leaderFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_learning_leader, container, false);

        initId(v);
        loadLeader();

        return  v;
    }

    private void loadLeader() {

        System.out.println("start request");

        progressDialog = new ProgressDialog(getContext());
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.setMessage("Loading, please wait ...");
        progressDialog.show();

        StringRequest stringRequest = new StringRequest(Request.Method.GET, "https://gadsapi.herokuapp.com/api/hours",
                response -> {
                    try {
                        System.out.println("response : " + response);
                        JSONArray jsonArray = new JSONArray(response);
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject jsonObject = jsonArray.getJSONObject(i);
                            leaderList.add(new Leader(
                                    jsonObject.getString("name"),
                                    jsonObject.getString("hours"),
                                    jsonObject.getString("country"),
                                    jsonObject.getString("badgeUrl")
                            ));
                            System.out.println(jsonObject.getString("name"));
                        }

                        adapter = new Learning_leaderAdapter(getContext(), leaderList);
                        recyclerViewLeader.setAdapter(adapter);

                        //after binding i stop progress dialog
                        progressDialog.dismiss();

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                },
                error -> {
                    error.printStackTrace();
                    //if i have any error i stop progress dialog
                    progressDialog.dismiss();
                });

        // you need this for execute request (-_-)
        MySingleton.getInstance(getContext()).addToRequestqueue(stringRequest);

    }

    private void initId(View v) {

        recyclerViewLeader = v.findViewById(R.id.recyclerViewLeader);
        recyclerViewLeader.setLayoutManager(new LinearLayoutManager((getContext())));
        recyclerViewLeader.setHasFixedSize(true);
    }
}