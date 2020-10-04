package com.example.mad_group_pro;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class User_profileDash_Activity extends AppCompatActivity {
    private Button addUserbtn,changeProfilebtn,logoutbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile_dash_);

        addUserbtn = findViewById(R.id.add_profile_details_btn);
        changeProfilebtn = findViewById(R.id.profile_details_maintain_btn);
        logoutbtn = findViewById(R.id.logout_btn);

        addUserbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(User_profileDash_Activity.this,Add_user_detail_activity.class);
                startActivity(intent);
            }
        });

        changeProfilebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(User_profileDash_Activity.this,Add_user_detail_activity.class);
                startActivity(intent);
            }
        });
        logoutbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(User_profileDash_Activity.this,MainActivity.class);
                startActivity(intent);
            }
        });

    }
}