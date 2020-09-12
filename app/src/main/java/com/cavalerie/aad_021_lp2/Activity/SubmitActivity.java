package com.cavalerie.aad_021_lp2.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.toolbox.StringRequest;
import com.cavalerie.aad_021_lp2.R;
import com.cavalerie.aad_021_lp2.Util.MySingleton;

import java.util.HashMap;
import java.util.Map;

public class SubmitActivity extends AppCompatActivity {

    private androidx.appcompat.widget.Toolbar toolbar;
    private ImageView arrowBack;
    private TextView form_firstName;
    private TextView form_lastName;
    private TextView form_emailAddress;
    private TextView form_projectLink;
    private Button form_submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_submit);



        initID();
        initAction();
    }

    @SuppressLint("RestrictedApi")
    private void initAction() {

        //for inflate toolbar
        setSupportActionBar(toolbar);

        arrowBack.setOnClickListener(view -> finish());
        form_submit.setOnClickListener(view -> {
            if(Validate())
                submitForm();
        });

    }

    private void submitForm() {

        String Firstname = form_firstName.getText().toString().trim();
        String Lastname = form_lastName.getText().toString().trim();
        String Email = form_emailAddress.getText().toString().trim();
        String Github = form_projectLink.getText().toString().trim();

        StringRequest update = new StringRequest(Request.Method.POST, "https://docs.google.com/forms/d/e/1FAIpQLSf9d1TcNU6zc6KR8bSEM41Z1g1zl35cwZr2xyjIhaMAz8WChQ/formResponse",
                response -> {
                    //make success dialog here
                    Toast toast = Toast.makeText(getApplicationContext(),"Submission was successful", Toast.LENGTH_SHORT);
                    toast.show();
                },
                error -> {
            //make error dialog here
                    Toast toast = Toast.makeText(getApplicationContext(),"Submission failed", Toast.LENGTH_SHORT);
                    toast.show();
                    error.printStackTrace();
                }) {

            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();

                params.put("entry.1877115667", Firstname);
                params.put("entry.2006916086", Lastname);
                params.put("entry.1824927963", Email);
                params.put("entry.284483984", Github);

                return params;
            }
        };

        MySingleton.getInstance(this).addToRequestqueue(update);

    }

    private boolean Validate() {
        boolean valid = true;

        if (form_firstName.getText().toString().trim().length() == 0){
            form_firstName.setError("first name required");
            valid = false;
        }else if (form_lastName.getText().toString().trim().length() == 0){
            form_lastName.setError("last name required");
            valid = false;
        }else if (form_emailAddress.getText().toString().trim().length() == 0) {
            form_emailAddress.setError("email address required");
            valid = false;
        }else if (form_projectLink.getText().toString().trim().length() == 0) {
            form_projectLink.setError("Please input project link");
            valid = false;
        }

        //try app and do the same for other case

        return valid;
    }

    private void initID() {

        toolbar = (androidx.appcompat.widget.Toolbar) findViewById(R.id.toolbarSubmit);
        arrowBack = (ImageView) findViewById(R.id.arrowBack);
        form_firstName = (TextView) findViewById(R.id.form_firstName);
        form_lastName = (TextView) findViewById(R.id.form_lastName);
        form_emailAddress = (TextView) findViewById(R.id.form_emailAddress);
        form_projectLink = (TextView) findViewById(R.id.form_projectLink);
        form_submit = (Button) findViewById(R.id.form_submit);
    }

    @Override
    public boolean onNavigateUp(){
        finish();
        return true;
    }


}