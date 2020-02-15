package com.example.notepad;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Environment;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class Notestaking extends AppCompatActivity {
    Filemodel filemodel;
    MyNotebooks  note ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notestaking);
        Toolbar toolbar=(Toolbar)findViewById(R.id.toolbarfiles);
        Bundle extras = getIntent().getExtras();
        String Foldername = extras.getString("foldername");
        String Filename = extras.getString("filename");
        Toast.makeText(Notestaking.this,Foldername+" "+Filename,Toast.LENGTH_LONG).show();
        toolbar.setTitle("FileName");
        toolbar.inflateMenu(R.menu.file_saveitem);
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {

                switch(item.getItemId())
                {
                    case R.id.save123:
                        Toolbar toolbar=(Toolbar)findViewById(R.id.toolbarfiles);
                        String filename = toolbar.getTitle().toString();
                            update();
                        return true;
                    default:
                        return false;
                }
            }
        });
    }
    private void update( )  {
        Bundle extras = getIntent().getExtras();
        final String str = extras.getString("Foldername");
        EditText edit =(EditText)findViewById(R.id.editText123);
        String content=edit.getText().toString();
        Toolbar toolbar=(Toolbar)findViewById(R.id.toolbarfiles);
        String filename= toolbar.getTitle().toString();
        File file = new File(Environment.getExternalStorageDirectory()+"/yamraj/"+str+"/"+filename+".txt");
        FileWriter fw ;
        try {
            fw = new FileWriter(file);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(content);
            bw.close();
            Toast.makeText(Notestaking.this,"SAVED",Toast.LENGTH_LONG).show();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


}
