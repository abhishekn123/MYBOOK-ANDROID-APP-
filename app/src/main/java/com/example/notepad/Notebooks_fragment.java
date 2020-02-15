package com.example.notepad;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Environment;
import android.text.Layout;
import android.view.ActionMode;
import android.view.ContextMenu;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.Toast;
import java.io.File;
import java.io.IOException;
import java.util.List;
public class Notebooks_fragment extends Fragment  {
    public Notebooks_fragment() {
        // Required empty public constructor
    }
    Toolbar toolbar;
    ActionMode actionMode;
    List<NatureModel> lst = NatureModel.getObjectList();
    @Override
    public void onViewCreated(final View view , @Nullable Bundle savedInstanceState)
    {
        toolbar = (Toolbar)getView().findViewById(R.id.toolbar);
        toolbar.inflateMenu(R.menu.notebook_menu);
        toolbar.setTitle("Notebooks");
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch(item.getItemId()){
                    case R.id.add:
                        startAlert();
                        return true;
                    default:
                        return false;
                }
            }
        });
    }
    private void startAlert() {
        final EditText text=new EditText(getActivity());
        text.setHint("Notebook name");
        AlertDialog.Builder adb = new AlertDialog.Builder(getActivity());
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
    private void createfile(String fname)
    {

        if(fname.isEmpty())
        {
            Toast.makeText(getActivity(),"Notebook Name can't be Empty",Toast.LENGTH_LONG).show();
            return ;
        }
        File file = new File(Environment.getExternalStorageDirectory() + "/yamraj/" + fname+"/" );
        if (file.mkdir()) {
            NatureModel n =new NatureModel();
            n.setFoldername(fname);
            lst.add(n);
            Toast.makeText(getActivity(), "Notebook Created", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(getActivity(), "Notebook already exists", Toast.LENGTH_LONG).show();
        }
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final ActionMode actionMode;
        View layout = inflater.inflate(R.layout.fragment_notebooks_fragment2, container, false);
        LinearLayout linearLayout =(LinearLayout)layout.findViewById(R.id.linear);
        RecyclerView recyclerView =(RecyclerView) layout.findViewById(R.id.Folderrecycle);
        Folderadapter adapter = new Folderadapter(getActivity(),lst);
        adapter.notifyDataSetChanged();
        recyclerView.setAdapter(adapter);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        return layout;
    }

}
