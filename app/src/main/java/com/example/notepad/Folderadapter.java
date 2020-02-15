package com.example.notepad;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Environment;
import android.view.ActionMode;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.io.File;
import java.util.List;

public class Folderadapter extends RecyclerView.Adapter<Folderadapter.MyViewHolder>
{
    private ActionMode actionMode;
    private LayoutInflater inflater;
    private Context context;
    private List<NatureModel> objectList;
    public Folderadapter( Context context,List<NatureModel> objectList) {
        inflater = LayoutInflater.from(context);
        this.context = context;
        this.objectList=objectList;
    }
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view =  inflater.inflate(R.layout.folder_item,parent,false);
        MyViewHolder holder =new MyViewHolder(view);
        return holder;
    }
    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, final int position) {
        NatureModel current = objectList.get(position);
        holder.setdata(current,position);
        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context,MyNotebooks.class);
                intent.putExtra("item",holder.txt.getText());
                context.startActivity(intent);
            }
        });
        holder.linearLayout.setOnLongClickListener(new View.OnLongClickListener(){
            public boolean onLongClick(View view)
            {
                AlertDialog.Builder built = new AlertDialog.Builder(context);
                built.setTitle("Are you sure to delete");
                built.setPositiveButton("OK",new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        String foldername = holder.txt.getText().toString();
                        Toast.makeText(context,foldername,Toast.LENGTH_LONG).show();
                        File file=new File(Environment.getExternalStorageDirectory()+"/yamraj/");
                          File[] fl=file.listFiles();
                          for(File f : fl) {
                              if(f.getName().equals(foldername)) {
                                  File files=new File(Environment.getExternalStorageDirectory()+"/yamraj/"+foldername+"/");
                                  File[] flist =files.listFiles();
                                  for(File fls : flist)
                                  {
                                      fls.delete();
                                  }
                                 // Toast.makeText(context,f.getName(),Toast.LENGTH_LONG).show();
                                  if (f.delete()) {
                                      Toast.makeText(context, "Item Removed", Toast.LENGTH_LONG).show();
                                      objectList.remove(position);
                                      notifyItemRemoved(position);
                                  }
                              }
                          }
                    }
                });
                built.setNegativeButton("CANCEL",new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
                built.create();
                built.show();


                return true;
            }

        });
    }
    @Override
    public int getItemCount() {

        return objectList.size();
    }
    public class MyViewHolder extends RecyclerView.ViewHolder
    {
        TextView txt;
        private NatureModel currentObject;
        private int position;
       // Notebooks_fragment notebooks_fragment;
        private Button btn;
        private CardView cardView;
        private LinearLayout linearLayout;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            txt=(TextView)itemView.findViewById(R.id.Foldertext);
            linearLayout =(LinearLayout)itemView.findViewById(R.id.linear);
            cardView =(CardView)itemView.findViewById(R.id.Foldercard);
        }
        public void setdata(NatureModel currentObject,int position) {
            this.txt.setText(currentObject.getFoldername());
            this.position = position;
            this.currentObject = currentObject;
        }
    }
}
