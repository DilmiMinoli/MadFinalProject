package com.example.mad_group_pro;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class CirnfirmFinalOrder_Activity extends AppCompatActivity {

    private EditText nameEditTxt, phoneEditTxt,addressEditTxt,cityEditTxt;
    private Button ConfirmOrder;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cirnfirm_final_order_);

        ConfirmOrder = (Button) findViewById(R.id.conferm_btn);
        nameEditTxt = (EditText) findViewById(R.id.shipment_name);
        addressEditTxt = (EditText) findViewById(R.id.shipment_Address);
        cityEditTxt= (EditText) findViewById(R.id.shipment_city);
        phoneEditTxt = (EditText) findViewById(R.id.shipment_number);
    }
}