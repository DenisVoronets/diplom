package com.arhiser.todolist.screens.details;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.arhiser.todolist.App;
import com.arhiser.todolist.R;
import com.arhiser.todolist.model.MyUserData;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class PersonalDetailsActivity extends AppCompatActivity {

    private static final String EXTRA_PERSONALDATA = "PersonalDetailsActivity.EXTRA_PERSONALDATA";

    private MyUserData myUserData;
    private EditText editTextEmail,editTextPassword,editTextConfimPassword;
    private Button buttonDone;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        buttonDone = findViewById(R.id.button_registration_done);
        editTextEmail = findViewById(R.id.editText_email_registration);
        editTextPassword = findViewById(R.id.editText_password_registration);
        editTextConfimPassword = findViewById(R.id.editText_password_double);
        myUserData = new MyUserData();

        buttonDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String password = editTextPassword.getText().toString();
                String confimPassword = editTextConfimPassword.getText().toString();
                if (editTextEmail.getText().length() > 0 && password.equals(confimPassword)) {
                    myUserData.mail = editTextEmail.getText().toString();
                    myUserData.password = (editTextPassword.getText().toString());
                    if (getIntent().hasExtra(EXTRA_PERSONALDATA)) {
                        App.getInstance().getNoteDao().update(myUserData);
                    } else {
                        App.getInstance().getNoteDao().insert(myUserData);
                    }
                    finish();
                }else System.out.println("error");
            }
        });
    }

}
