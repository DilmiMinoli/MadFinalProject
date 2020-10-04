package com.example.mad_group_pro;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mad_group_pro.VeiwHolder.ProductViewHolder;
import com.example.mad_group_pro.data.model.Products;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

public class Home_Activity extends AppCompatActivity {


    private Button FloatingActionButton;
    private DatabaseReference ProductsRef;
    private RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;

    private String type = "";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_);

       Intent intent = getIntent();
       Bundle bundle = intent.getExtras();
       if(bundle != null){
           type = getIntent().getExtras().get("Admin").toString();
       }


        ProductsRef = FirebaseDatabase.getInstance().getReference().child("Product");

        recyclerView = findViewById(R.id.recycler_menu);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        com.google.android.material.floatingactionbutton.FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.flo_Btn);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Home_Activity.this, Cart_Activity.class);
                startActivity(intent);
            }
        });





        BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation);
        bottomNav.setOnNavigationItemSelectedListener(navLister);

    }

    @Override
    protected void onStart() {
        super.onStart();

        FirebaseRecyclerOptions<Products> options =
                new FirebaseRecyclerOptions.Builder<Products>()
                        .setQuery(ProductsRef, Products.class)
                        .build();

        FirebaseRecyclerAdapter<Products, ProductViewHolder> adapter =
                new FirebaseRecyclerAdapter<Products, ProductViewHolder>(options) {
                    @Override
                    protected void onBindViewHolder(@NonNull ProductViewHolder productViewHolder, int i, @NonNull final Products model) {

                        productViewHolder.txtProductName.setText(model.getPname());
                        productViewHolder.txtProductPrice.setText( "price = " + model.getPrice() + "$");
                        productViewHolder.txtProductDescription.setText(model.getDescription());
                        Picasso.get().load(model.getImage()).into(productViewHolder.imageView);



                        productViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {

                                Intent intent = new Intent(Home_Activity.this, Product_details_veiw_Activity.class);
                                intent.putExtra("pid",model.getPid());
                                startActivity(intent);
                            }
                        });

                        productViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v)
                            {
                                if(type.equals("Admin")){
                                    Intent intent = new Intent(Home_Activity.this,products_edit_update_delete_Activity.class);
                                    intent.putExtra("pid",model.getPid());
                                    startActivity(intent);


                                }else{

                                    Intent intent = new Intent(Home_Activity.this,Product_details_veiw_Activity.class);
                                    intent.putExtra("pid",model.getPid());
                                    startActivity(intent);


                                }

                            }
                        });
                    }

                    @NonNull
                    @Override
                    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.product_item_layout,parent,false);
                        ProductViewHolder holder = new ProductViewHolder(view);
                        return holder;
                    }
                };

        recyclerView.setAdapter(adapter);
        adapter.startListening();



    }

    private BottomNavigationView.OnNavigationItemSelectedListener navLister =
          new BottomNavigationView.OnNavigationItemSelectedListener() {
              @Override
              public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                  Fragment selectedFragment = null;

                  switch (item.getItemId()){

                      case R.id.categories:
                          selectedFragment = new CategoryFragment();
                                  break;
                      case R.id.addToCart_btn:
                          selectedFragment= new CartFragment();
                          break;
                      case R.id.searchFunction:
                          selectedFragment = new SearchFragment();
                          break;
                      case R.id.profile:
                          selectedFragment = new ProfileFragment();
                          break;

                  }

                  getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                          selectedFragment).commit();

                  return true;



              }
          };


}