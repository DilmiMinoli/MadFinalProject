package com.example.mad_group_pro;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;


public class Add_Product_Activity extends AppCompatActivity {


    private String CategoryName, Description, Price, pName, saveCurrentDate, SaveCurrentTime ;
    private Button AddNewProductButton;
    private ImageView InputProductImage;
    private EditText InputProductName, InputProductDescription, InputProductPrice;
    private static final int GalleryPick=1;
    private Uri ImageUri;
    private String ProductRandomKey, downloadImageUri;
    private StorageReference ProductImageRef;
    private DatabaseReference ProductsRef;
    private ProgressDialog loadingBar;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_add__product_);

    CategoryName = getIntent().getExtras().get("category").toString();
    ProductImageRef=FirebaseStorage.getInstance().getReference().child("Product Images");
    ProductsRef = FirebaseDatabase.getInstance().getReference().child("Product");

    AddNewProductButton = (Button) findViewById(R.id.Add_new_Product);
    InputProductImage = (ImageView) findViewById(R.id.ProductImage);
    InputProductName = (EditText) findViewById(R.id.Products_name);
    InputProductDescription = (EditText) findViewById(R.id.Product_description);
    InputProductPrice = (EditText) findViewById(R.id.Product_Price);
    loadingBar = new ProgressDialog(this);


    Toast.makeText(this,CategoryName,Toast.LENGTH_SHORT).show();

    InputProductImage.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            openGallery();
        }


    });

    AddNewProductButton.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            ValidateProduct();
        }
    });
     }
    private void openGallery() {
      Intent galleryIntent = new Intent();
      galleryIntent.setAction(Intent.ACTION_GET_CONTENT);
      galleryIntent.setType("image/*");
      startActivityForResult(galleryIntent, GalleryPick);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == GalleryPick && resultCode==RESULT_OK && data !=null)
        {
            ImageUri = data.getData();
            InputProductImage.setImageURI(ImageUri);
        }
    }
    private void ValidateProduct(){

      Description = InputProductDescription.getText().toString();
      Price = InputProductPrice.getText().toString();
      pName = InputProductName.getText().toString();

      if(ImageUri == null)
      {
          Toast.makeText(this,"Product image is mandatory...", Toast.LENGTH_SHORT).show();

      }else if(TextUtils.isEmpty(Description))
      {
          Toast.makeText(this,"Please Write Product Description...",Toast.LENGTH_SHORT).show();
      }else if(TextUtils.isEmpty(Price))
      {
          Toast.makeText(this,"please write the the product price",Toast.LENGTH_SHORT).show();
      }
    else if(TextUtils.isEmpty(pName))
    {
        Toast.makeText(this,"please write the the product name",Toast.LENGTH_SHORT).show();
    }else{
        StoreProductInformation();
      }

    }

    private void StoreProductInformation() {

      loadingBar.setTitle("Adding new product");
      loadingBar.setMessage("Please wait,while we are checking the new product");
      loadingBar.setCanceledOnTouchOutside(false);
      loadingBar.show();
      Calendar calender = Calendar.getInstance();

      SimpleDateFormat currentDate = new SimpleDateFormat("MMM dd, yyyy");
      saveCurrentDate = currentDate.format(calender.getTime());

        SimpleDateFormat currentTime = new SimpleDateFormat("HH:mm:ss a");
        SaveCurrentTime = currentTime.format(calender.getTime());

        ProductRandomKey = saveCurrentDate + SaveCurrentTime;

        final StorageReference filepath =ProductImageRef.child(ImageUri.getLastPathSegment()+ProductRandomKey);

        final UploadTask uploadTask =filepath.putFile(ImageUri);

        uploadTask.addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

                String message=e.toString();
                Toast.makeText(Add_Product_Activity.this, "Error:", Toast.LENGTH_SHORT).show();
                loadingBar.dismiss();

            }
        }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                Toast.makeText(Add_Product_Activity.this,"Image uploaded successfully",Toast.LENGTH_SHORT).show();

                Task<Uri> uriTask = uploadTask.continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
                    @Override
                    public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception {
                        if(!task.isSuccessful())
                        {
                            throw task.getException();

                        }

                        downloadImageUri = filepath.getDownloadUrl().toString();
                        return  filepath.getDownloadUrl();
                   }
                }).addOnCompleteListener(new OnCompleteListener<Uri>() {
                    @Override
                    public void onComplete(@NonNull Task<Uri> task) {

                        if(task.isSuccessful()) {

                            downloadImageUri = task.getResult().toString();

                            Toast.makeText(Add_Product_Activity.this, "Product image save to Database SuccessFully", Toast.LENGTH_SHORT).show();

                            SaveProductInforToDatabase();
                        }

                    }
                });
            }
        });

  }

    private void SaveProductInforToDatabase() {
      HashMap<String, Object> productMap = new HashMap<>();

        productMap.put("pid",ProductRandomKey);
        productMap.put("description",Description);
        productMap.put("date",saveCurrentDate);
        productMap.put("time",SaveCurrentTime);
        productMap.put("image",downloadImageUri);
        productMap.put("price",Price);
        productMap.put("pname",pName);
        productMap.put("category",CategoryName);

        ProductsRef.child(ProductRandomKey).updateChildren(productMap)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {

                        if (task.isSuccessful()) {
                            Intent intent = new Intent(Add_Product_Activity.this, Admin_category_veiw_Activity.class);
                            startActivity(intent);

                            loadingBar.dismiss();
                            Toast.makeText(Add_Product_Activity.this, "Product is added successfully", Toast.LENGTH_SHORT).show();


                        } else {
                            loadingBar.dismiss();
                            String message = task.getException().toString();

                            Toast.makeText(Add_Product_Activity.this, "Error: " + message, Toast.LENGTH_SHORT).show();
                        }
                    }
                });

    }

}