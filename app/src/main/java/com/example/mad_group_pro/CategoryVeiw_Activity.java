package com.example.mad_group_pro;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class CategoryVeiw_Activity extends AppCompatActivity {
    TextView category;
    ImageView shirts, frocks, phone, menShoes, womenShoes, Rings ;
    TextView Shirtsxt,Frockstxt,Phonesxt,MenShoestxt,WomenShoestxt,Ringstxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_veiw_);

        category = findViewById(R.id.textView);
        category.setText(R.string.category);

        shirts = findViewById(R.id.shirt_image);
        frocks = findViewById(R.id.frock_image);
        phone = findViewById(R.id.mphone_image);
        menShoes = findViewById(R.id.MenShoe_image);
        womenShoes = findViewById(R.id.WomenShoe_image);
        Rings = findViewById(R.id.Ring_image);

        Shirtsxt = findViewById(R.id.shirt_txt);
        Frockstxt = findViewById(R.id.Frock_txt);
        Phonesxt = findViewById(R.id.mphone_txt);
        MenShoestxt = findViewById(R.id.MenShoe_txt);
        WomenShoestxt = findViewById(R.id.WomenShoe_txt);
        Ringstxt = findViewById(R.id.Ring_txt);

        shirts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CategoryVeiw_Activity.this, Cart_Activity.class);
                startActivity(intent);
            }
        });

        frocks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CategoryVeiw_Activity.this,Cart_Activity.class);
                startActivity(intent);
            }
        });

        phone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CategoryVeiw_Activity.this,Cart_Activity.class);
                startActivity(intent);
            }
        });

        menShoes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CategoryVeiw_Activity.this,Cart_Activity.class);
                startActivity(intent);
            }
        });

        womenShoes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CategoryVeiw_Activity.this,Cart_Activity.class);
                startActivity(intent);
            }
        });

        Rings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CategoryVeiw_Activity.this,Cart_Activity.class);
                startActivity(intent);
            }
        });


        Button cart = (Button) findViewById(R.id.addToCart_btn);
        cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CategoryVeiw_Activity.this, Cart_Activity.class);
                startActivity(intent);
            }
        });

    }

    public void catDetailView(View view){
        Intent intent01 = new Intent(CategoryVeiw_Activity.this, Cart_Activity.class);
        startActivity(intent01);

    }



}