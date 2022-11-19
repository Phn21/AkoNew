package com.example.akonew;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.akonew.Recycler.DBAdapter;
import com.example.akonew.Room.Db;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    public static final int ADD_DATA_REQUEST = 1;
    public static final int EDIT_DATA_REQUEST = 2;
    DbViewModel dbViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FloatingActionButton buttonAdd = findViewById(R.id.buttonAdd);

        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AddEditDataActivity.class);
                startActivityForResult(intent, ADD_DATA_REQUEST);
            }
        });

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        DBAdapter dbAdapter = new DBAdapter();
        recyclerView.setAdapter(dbAdapter);

        dbViewModel = ViewModelProviders.of(MainActivity.this).get(DbViewModel.class);
        dbViewModel.getAllInfo().observe(this, new Observer<List<Db>>() {
            @Override
            public void onChanged(List<Db> dbs) {
                dbAdapter.setData(dbs);
            }
        });


        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,
                ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                dbViewModel.delete(dbAdapter.getDataAt(viewHolder.getAdapterPosition()));
                Toast.makeText(MainActivity.this, "Data Deleted!", Toast.LENGTH_SHORT).show();
            }
        }).attachToRecyclerView(recyclerView);



        dbAdapter.setOnItemClickListener(new DBAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Db db) {
                Intent intent = new Intent(MainActivity.this, AddEditDataActivity.class);
                intent.putExtra(AddEditDataActivity.EXTRA_ID, db.getId());
                intent.putExtra(AddEditDataActivity.EXTRA_NAME, db.getName());
                intent.putExtra(AddEditDataActivity.EXTRA_SURNAME, db.getSurname());
                startActivityForResult(intent, EDIT_DATA_REQUEST);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == ADD_DATA_REQUEST && resultCode == RESULT_OK){
            String name = data.getStringExtra(AddEditDataActivity.EXTRA_NAME);
            String surname = data.getStringExtra(AddEditDataActivity.EXTRA_SURNAME);

            Db db = new Db(name, surname);
            dbViewModel.insert(db);
            Toast.makeText(this, "Saved Successfully!", Toast.LENGTH_SHORT).show();
        }else if (requestCode == EDIT_DATA_REQUEST && resultCode == RESULT_OK){

            int id = data.getIntExtra(AddEditDataActivity.EXTRA_ID, -1);
            if (id == -1){
                Toast.makeText(this, "Data Can not updated!", Toast.LENGTH_SHORT).show();
                return;
            }
            String name = data.getStringExtra(AddEditDataActivity.EXTRA_NAME);
            String surname = data.getStringExtra(AddEditDataActivity.EXTRA_SURNAME);
            Db db = new Db(name, surname);
            db.setId(id);
            dbViewModel.update(db);
            Toast.makeText(this, "Data Updated!", Toast.LENGTH_SHORT).show();
        }
        else Toast.makeText(this, "Not saved.", Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.delete_all, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.deleteAll:
                dbViewModel.deleteAll();
                Toast.makeText(this, "Deleted All Data!", Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}