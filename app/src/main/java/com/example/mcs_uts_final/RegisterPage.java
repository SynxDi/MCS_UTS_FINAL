package com.example.mcs_uts_final;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.mcs_uts_final.databinding.ActivityRegisterPageBinding;

public class RegisterPage extends AppCompatActivity {

    ActivityRegisterPageBinding binding;
    DatabaseHelper databaseHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRegisterPageBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        databaseHelper = new DatabaseHelper(this);

        binding.buttonSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = binding.editTextNameRegister.getText().toString();
                String password = binding.editTextRegisterPassword.getText().toString();
                String confirmpass = binding.editTextRegisterPassword2.getText().toString();

                if(email.equals("")||password.equals(""))
                    Toast.makeText(RegisterPage.this, "All Fields cannot be empty", Toast.LENGTH_SHORT).show();
                else {
                    if (password.equals(confirmpass)){
                        Boolean checkUserEmail = databaseHelper.checkEmail(email);

                        if(checkUserEmail == false){
                            Boolean insert = databaseHelper.insertData(email,password);
                            if (insert == true){
                                Toast.makeText(RegisterPage.this, "Register Succes", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                                startActivity(intent);
                            } else {
                                Toast.makeText(RegisterPage.this, "Register Failed", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(RegisterPage.this, "User already exist", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(RegisterPage.this, "Invalid Password", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}