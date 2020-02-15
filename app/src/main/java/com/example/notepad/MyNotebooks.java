package com.example.notepad;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class MyNotebooks extends AppCompatActivity {
   Toolbar toolbar;
    EditText editText ;
    Fileadapter adapter;
    List<Filemodel> lst;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_notebooks);
        Bundle extras = getIntent().getExtras();
         String Foldername = extras.getString("item");
        toolbar = (Toolbar)findViewById(R.id.toolbarFiles);
        lst = Filemodel.getFilelist(Foldername);
        toolbar.inflateMenu(R.menu.notes_menu);
         toolbar.setTitle(Foldername);
         toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
             @Override
             public boolean onMenuItemClick(MenuItem item) {
                 switch(item.getItemId()){
                     case R.id.addFile:
                         startAlert();
                         return true;
                     default:
                         return false;
                 }
             }
         });
        RecyclerView recyclerView =(RecyclerView)findViewById(R.id.Filerecycle);
        adapter = new Fileadapter(MyNotebooks.this,lst);
        recyclerView.setAdapter(adapter);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);

    }

    private void startAlert() {
        final EditText text=new EditText(MyNotebooks.this);
        text.setHint("Notebook name");
        android.app.AlertDialog.Builder adb = new android.app.AlertDialog.Builder(MyNotebooks.this);
        adb.setTitle("Create a new noteboook");
        adb.setView(text);

        adb.setPositiveButton("CREATE", new DialogInterface.OnClickListener(){
            public void onClick(DialogInterface arg0,int arg1)
            {
                final String Foldername = text.getText().toString();
                createfile(Foldername);
            }
        });
        adb.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
            }
        });
        AlertDialog dailog= adb.create();
        dailog.show();

    }

    private void createfile(String fname) {
        if(fname.isEmpty())
        {
            Toast.makeText(MyNotebooks.this,"FileName can't be Empty",Toast.LENGTH_LONG).show();
            return ;
        }
        File file = new File(Environment.getExternalStorageDirectory() + "/yamraj/" + fname+"/" );
        if (file.mkdir()) {
            Filemodel n =new Filemodel();
            n.setFilename(fname);
            lst.add(n);
            Toast.makeText(MyNotebooks.this, "File Created", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(MyNotebooks.this, "File already exists", Toast.LENGTH_LONG).show();
        }
    }


}
