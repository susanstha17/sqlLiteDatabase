package com.example.databaseproject;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class AddActivity extends AppCompatActivity {
    EditText name, address, phone;
    Button save;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_data);

        name = findViewById(R.id.add_name);
        address = findViewById(R.id.add_address);
        phone = findViewById(R.id.add_phone);
        save = findViewById(R.id.btnsave);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (valid()) {
                    StudentModel s = new StudentModel();
                    s.setName(name.getText().toString());
                    s.setAddress( address.getText().toString());
                    s.setPhone(phone.getText().toString());
                    DatabaseHelper db = new DatabaseHelper(AddActivity.this);
                    db.add_data(s);

                    Toast.makeText(AddActivity.this,"Data Inserted Successfully",Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    private boolean valid() {
        boolean validation = true;
        if (TextUtils.isEmpty(name.getText().toString())) {
            name.setError("name cannot be empty");
            validation = false;
        }
        if (TextUtils.isEmpty(address.getText().toString())) {
            address.setError("address cannot be empty");
            validation = false;
        }
        if (TextUtils.isEmpty(phone.getText().toString())) {
            phone.setError("phone num cannot be empty");
            validation = false;
        }
        return validation;
    }
}
