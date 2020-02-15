package com.example.notepad;

import android.os.Environment;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class NatureModel {
    String foldername;
    public String getFoldername() {
        return foldername;
    }
    public void setFoldername(String foldername) {
        this.foldername = foldername;
    }
    public static List<NatureModel> getObjectList()
    {
        List<NatureModel> dataList = new ArrayList<>();
        File file=new File(Environment.getExternalStorageDirectory()+"/yamraj/");
        File[] fl = file.listFiles();
        for(File f : fl)
        {
            NatureModel nature =  new NatureModel();
            nature.setFoldername(f.getName());
            dataList.add(nature);
        }
        return dataList;

    }
}
