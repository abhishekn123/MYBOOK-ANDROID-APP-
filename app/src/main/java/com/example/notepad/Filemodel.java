package com.example.notepad;

import android.os.Environment;

import java.io.File;
import java.util.ArrayList;
import java.util.List;


public class Filemodel
{

    String Filename;
    public String getFilename() {
        return Filename;
    }
    public void setFilename(String filename) {
        Filename = filename;
    }
    public static List<Filemodel> getFilelist(String str)
    {
       List<Filemodel> lst = new ArrayList<Filemodel>() ;
        File file = new File(Environment.getExternalStorageDirectory() + "/yamraj/" + str + "/");
        File[] fl = file.listFiles();
        for(File f : fl)
        {
            Filemodel  filenamemodel = new Filemodel();
            filenamemodel.setFilename(f.getName());
            lst.add(filenamemodel);
        }
        return lst;
    }
}
