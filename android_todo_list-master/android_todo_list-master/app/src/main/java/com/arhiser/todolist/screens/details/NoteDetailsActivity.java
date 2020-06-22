package com.arhiser.todolist.screens.details;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.arhiser.todolist.App;
import com.arhiser.todolist.R;
import com.arhiser.todolist.model.Note;

public class NoteDetailsActivity extends AppCompatActivity {

    private static final String EXTRA_NOTE = "NoteDetailsActivity.EXTRA_NOTE";

    private Note note;
    private EditText editText,editTextPrioritet,editTextTag;

    public static void start(Activity caller, Note note) {
        Intent intent = new Intent(caller, NoteDetailsActivity.class);
        if (note != null) {
            intent.putExtra(EXTRA_NOTE, note);
        }
        caller.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_note_details);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        setTitle(getString(R.string.note_details_title));

        editText = findViewById(R.id.text);
        editTextPrioritet = findViewById(R.id.editText_prioritet);
        editTextTag = findViewById(R.id.editText_tag);

        if (getIntent().hasExtra(EXTRA_NOTE)) {
            note = getIntent().getParcelableExtra(EXTRA_NOTE);
            editText.setText(note.text);
            editTextTag.setText(note.tag);
            editTextPrioritet.setText(note.prioritet);
        } else {
            note = new Note();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_details, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
            case R.id.action_save:

                if (editText.getText().length() > 0 && editTextPrioritet.getText().length()>0 &&editTextTag.getText().length()>0) {
                    note.text = editText.getText().toString();
                    note.prioritet = Integer.parseInt(editTextPrioritet.getText().toString());
                    note.tag = editTextTag.getText().toString();
                    note.done = false;
                    note.timestamp = System.currentTimeMillis();
                    if (getIntent().hasExtra(EXTRA_NOTE)) {
                        App.getInstance().getNoteDao().update(note);
                    } else {
                        App.getInstance().getNoteDao().insert(note);
                    }
                    finish();
                } else System.out.println("error");
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
