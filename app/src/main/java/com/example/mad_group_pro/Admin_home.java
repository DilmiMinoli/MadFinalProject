package com.example.mad_group_pro;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class Admin_home extends AppCompatActivity {

    Button addProductButton, MaintainProductButton, logoutButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_home);



        addProductButton = findViewById(R.id.Add_Product_button);
        MaintainProductButton = findViewById(R.id.Maintain_product_button);






        addProductButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Admin_home.this, Admin_category_veiw_Activity.class);
                intent.putExtra("Admin","Admin");
                startActivity(intent);
                finish();
            }
        });




        MaintainProductButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Admin_home.this, products_edit_update_delete_Activity.class);
                intent.putExtra("Admin","Admin");
                startActivity(intent);
                finish();
            }
        });

        logoutButton = findViewById(R.id.Log_Out_button);

        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Admin_home.this, Login_Activity.class);
                startActivity(intent);
            }
        });

    }

    public void AdminCategoryView(View view){
        Intent intent = new Intent(Admin_home.this,Admin_home.class);
        startActivity(intent);


    }

}