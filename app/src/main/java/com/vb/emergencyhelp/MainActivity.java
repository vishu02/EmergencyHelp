package com.vb.emergencyhelp;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements OnClickListener {

    EditText name, address, phone, emName1, emNumber1, emNumber2, emName2;
    Spinner blood;
    Button save, cancel;
    DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        name = (EditText) findViewById(R.id.home_name_field);
        address = (EditText) findViewById(R.id.home_address_field);
        phone = (EditText) findViewById(R.id.home_phone_field);
        emName1 = (EditText) findViewById(R.id.Emergency_contacts_name);
        emNumber1 = (EditText) findViewById(R.id.Emergency_contacts_number1);
        emNumber2 = (EditText) findViewById(R.id.Emergency_contacts_number2);
        emName2 = (EditText) findViewById(R.id.Emergency_contacts_name2);
        blood = (Spinner) findViewById(R.id.blood_type_spinner);
        save = (Button) findViewById(R.id.save_button);
        cancel = (Button) findViewById(R.id.cancel_button);
        save.setOnClickListener(this);
        cancel.setOnClickListener(this);
        databaseHelper = new DatabaseHelper(this);
    }


    @Override
    public void onBackPressed() {
        this.finish();
    }

    @Override
    public void onClick(View v) {

        if (v.getId() == R.id.save_button) {
            name.setTextColor(Color.BLACK);
            address.setTextColor(Color.BLACK);
            phone.setTextColor(Color.BLACK);
            emName1.setTextColor(Color.BLACK);
            emNumber1.setTextColor(Color.BLACK);
            emNumber2.setTextColor(Color.BLACK);
            emName2.setTextColor(Color.BLACK);
            if (name.getText().toString().isEmpty() || address.getText().toString().isEmpty() || phone.getText().toString().isEmpty() || emName1.getText().toString().isEmpty() || emNumber1.getText().toString().isEmpty() || emNumber2.getText().toString().isEmpty() || emName2.getText().toString().isEmpty() || blood.getSelectedItemPosition() == 0) {
                Toast.makeText(getBaseContext(), getString(R.string.fill), Toast.LENGTH_SHORT).show();
            } else if (phone.getText().toString().length() < 10 || phone.getText().toString().length() > 10) {
                Toast.makeText(this, getString(R.string.phn), Toast.LENGTH_SHORT).show();
                phone.setTextColor(Color.RED);
                phone.requestFocus();
            } else if (emNumber1.getText().toString().length() < 10 || emNumber1.getText().toString().length() > 10) {
                Toast.makeText(this, getString(R.string.phn), Toast.LENGTH_SHORT).show();
                emNumber1.setTextColor(Color.RED);
                emNumber1.requestFocus();
            } else if (emNumber2.getText().toString().length() < 10 || emNumber2.getText().toString().length() > 10) {
                Toast.makeText(this, getString(R.string.phn), Toast.LENGTH_SHORT).show();
                emNumber2.setTextColor(Color.RED);
                emNumber2.requestFocus();
            } else {
                int i = blood.getSelectedItemPosition();
                String s = (String) blood.getItemAtPosition(i);
                String nm = name.getText().toString();
                String add = address.getText().toString();
                String phn = phone.getText().toString();
                String enm1 = emName1.getText().toString();
                String eno1 = emNumber1.getText().toString();
                String eno2 = emNumber2.getText().toString();
                String enm2 = emName2.getText().toString();

                databaseHelper.addDetails(new Details(nm, add, phn, s, enm1, eno1, enm2, eno2));
                Intent in = new Intent(this, Home.class);
                /*Bundle b=new Bundle();
                b.putString("name",nm);
				in.putExtras(b);*/
                startActivity(in);
                this.finish();
            }

        }
        if (v.getId() == R.id.cancel_button) {
            this.finish();
        }

    }
}
