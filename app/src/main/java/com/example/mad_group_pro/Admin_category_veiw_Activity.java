package com.example.mad_group_pro;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class Admin_category_veiw_Activity extends AppCompatActivity {

    private ImageView tshirts,frock,mobile,menShoe;
    private ImageView womenShoe,ring,laptop,bag;
    private Button maintainProductsBtn,logoutBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_category_veiw_);

        maintainProductsBtn = (Button) findViewById(R.id.maintain_btn);
        logoutBtn = (Button) findViewById(R.id.logout_btn);

        maintainProductsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Admin_category_veiw_Activity.this, Home_Activity.class);
             intent.putExtra("Admin", "Admin");
                startActivity(intent);


            }
        });

        tshirts = (ImageView) findViewById(R.id.shirt);
        frock = (ImageView) findViewById(R.id.frock);
        mobile = (ImageView) findViewById(R.id.mphone);
        menShoe = (ImageView) findViewById(R.id.men_shoe);
        womenShoe = (ImageView) findViewById(R.id.women_shoe);
        ring = (ImageView) findViewById(R.id.ring);
        laptop = (ImageView) findViewById(R.id.laptop);
        bag = (ImageView) findViewById(R.id.bag);

        tshirts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Admin_category_veiw_Activity.this, Add_Product_Activity.class);
                intent.putExtra("category","tshirts");
                startActivity(intent);
            }
        });

        frock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Admin_category_veiw_Activity.this,Add_Product_Activity.class);
                intent.putExtra("category","frock");
                startActivity(intent);
            }
        });

        mobile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Admin_category_veiw_Activity.this,Add_Product_Activity.class);
                intent.putExtra("category","mobile");
                startActivity(intent);
            }
        });

        menShoe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Admin_category_veiw_Activity.this,Add_Product_Activity.class);
                intent.putExtra("category","menShoe");
                startActivity(intent);
            }
        });

        womenShoe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Admin_category_veiw_Activity.this,Add_Product_Activity.class);
                intent.putExtra("category","womenShoe");
                startActivity(intent);
            }
        });

        ring.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Admin_category_veiw_Activity.this,Add_Product_Activity.class);
                intent.putExtra("category","ring");
                startActivity(intent);
            }
        });

        laptop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Admin_category_veiw_Activity.this,Add_Product_Activity.class);
                intent.putExtra("category","laptop");
                startActivity(intent);
            }
        });

        bag.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Admin_category_veiw_Activity.this,Add_Product_Activity.class);
                intent.putExtra("category","bag");
                startActivity(intent);
            }
        });
    }
}