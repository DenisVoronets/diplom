package com.arhiser.todolist.screens.main;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.arhiser.todolist.App;
import com.arhiser.todolist.R;
import com.arhiser.todolist.model.MyUserData;
import com.arhiser.todolist.screens.details.PersonalDetailsActivity;

import java.util.List;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public  class StartActivity extends AppCompatActivity {
    private EditText editTextUserEmail,editTextUserPassword;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        editTextUserEmail = findViewById(R.id.username);
        editTextUserPassword = findViewById(R.id.password);
    }

    public void LogIn(View view) {
        switch (view.getId()){
            case R.id.button_login:
                String email = editTextUserEmail.getText().toString();
                String password = editTextUserPassword.getText().toString();
                List<MyUserData> personalEmail = App.getInstance().getNoteDao().getEmail(email);
                List<MyUserData> personalPassword = App.getInstance().getNoteDao().getPassword(password);
                for (int i = 0; i < personalEmail.size(); i++) {
                   String currentEmail = String.valueOf(personalEmail.get(i).mail);
                   String currentPassword = String.valueOf(personalPassword.get(i).password);
                    if (currentEmail.equals(email)&&currentPassword.equals(password)){
                       Intent intent = new Intent(StartActivity.this,MainActivity.class);
                       startActivity(intent);
                    } else  System.out.println("error");
                }
                break;
            case R.id.button_registre:
                Intent intent = new Intent(StartActivity.this, PersonalDetailsActivity.class);
                startActivity(intent);
        }
    }
}
