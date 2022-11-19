package com.example.akonew;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

public class AddEditDataActivity extends AppCompatActivity {

    public static final String EXTRA_NAME =
            "com.example.akonew.EXTRA_NAME";
    public static final String EXTRA_SURNAME =
            "com.example.akonew.EXTRA_SURNAME";
    public static final String EXTRA_ID =
            "com.example.akonew.EXTRA_ID";
    private EditText name, surname;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_data);

        name = findViewById(R.id.nameET);
        surname = findViewById(R.id.surnameET);


        getSupportActionBar().setHomeAsUpIndicator(R.drawable.close);

        Intent intent = getIntent();

        if (intent.hasExtra(EXTRA_ID)){
            setTitle("Edit Data");
            name.setText(intent.getStringExtra(EXTRA_NAME));
            surname.setText(intent.getStringExtra(EXTRA_SURNAME));
        }else setTitle("Add Data");
    }

    private void saveData(){
        String nameS = name.getText().toString();
        String surnameS = surname.getText().toString();

        if (nameS.trim().isEmpty() || surnameS.trim().isEmpty()){
            Toast.makeText(this, "Fill the fields!", Toast.LENGTH_SHORT).show();
            return;
        }

        Intent intent = new Intent();
        intent.putExtra(EXTRA_NAME, nameS);
        intent.putExtra(EXTRA_SURNAME, surnameS);

        int id = getIntent().getIntExtra(EXTRA_ID, -1);
        if (id!=-1){
            intent.putExtra(EXTRA_ID, id);
        }

        setResult(RESULT_OK, intent);
        finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.add_data_menu, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.save:
                saveData();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }
}