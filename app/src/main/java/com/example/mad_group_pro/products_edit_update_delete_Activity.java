package com.example.mad_group_pro;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.HashMap;

public class products_edit_update_delete_Activity extends AppCompatActivity {

    private Button ApplyChangesButton,DeleteButton;
    private EditText name,price,description;
    private ImageView imageView;
    private String productID = "";
    private DatabaseReference productRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_products_edit_update_delete_);

        productID = getIntent().getStringExtra("pid");
        productRef = FirebaseDatabase.getInstance().getReference().child("Product").child(productID);

        ApplyChangesButton = (Button) findViewById(R.id.apply_changes_btn);
        name = (EditText) findViewById(R.id.product_name_maintain);
        description = (EditText) findViewById(R.id.product_description_maintain);
        price = (EditText) findViewById(R.id.product_price_maintain);
        imageView = (ImageView) findViewById(R.id.product_image_maintain);
        DeleteButton = (Button) findViewById(R.id.delete_product_btn);

        displaySpecificProductInfor();

        ApplyChangesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) 
            {
             applyChanges();   
            }
        });
        
        DeleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteProducts();
            }
        });
    }



    private void deleteProducts()
    {
        productRef.removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task)
            {
                Intent intent = new Intent(products_edit_update_delete_Activity.this, Admin_category_veiw_Activity.class);
                startActivity(intent);
                finish();
                 Toast.makeText(products_edit_update_delete_Activity.this, "The Product is Deleted Succssefully", Toast.LENGTH_SHORT).show();

            }
        });
    }

    private void applyChanges()
    {
        String pName = name.getText().toString();
        String pPrice = price.getText().toString();
        String pDescription = description.getText().toString();
        if(pName.equals("")) {
            Toast.makeText(this, "Please Write Product Name", Toast.LENGTH_SHORT).show();
        }else if(pPrice.equals("")) {
            Toast.makeText(this,"Please Write Product Price.", Toast.LENGTH_SHORT).show();
        }
        else if(pDescription.equals("")) {
            Toast.makeText(this,"Please enter Product Description",Toast.LENGTH_SHORT).show();
        } else{
            HashMap<String, Object> productMap = new HashMap<>();
            productMap.put("pid", productID);
            productMap.put("description", pDescription);
            productMap.put("price", pPrice);
            productMap.put("pname", pName);
            productRef.updateChildren(productMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task)
                {
                    if(task.isSuccessful())
                    {
                        Toast.makeText(products_edit_update_delete_Activity.this, "Changes Applied Successfully.. ", Toast.LENGTH_SHORT).show();

                        Intent intent = new Intent(products_edit_update_delete_Activity.this, Admin_category_veiw_Activity.class);
                        startActivity(intent);
                        finish();
                    }
                }
            });
        }



    }

    private void displaySpecificProductInfor()
    {
        productRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot)
            {
             if(snapshot.exists())
             {
                 String ProName = snapshot.child("pname").getValue().toString();
                 String ProPrice = snapshot.child("price").getValue().toString();
                 String ProDescreption = snapshot.child("description").getValue().toString();
                 String ProImage = snapshot.child("image").getValue().toString();

                 name.setText(ProName);
                 price.setText(ProPrice);
                 description.setText(ProDescreption);
                 Picasso.get().load(ProImage).into(imageView);
             }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}