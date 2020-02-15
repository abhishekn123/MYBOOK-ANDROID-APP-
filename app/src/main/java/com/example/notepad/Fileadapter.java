package com.example.notepad;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import java.io.File;
import java.util.List;
public class Fileadapter extends RecyclerView.Adapter<Fileadapter.MyViewHolder> {
    private Context context;
    private LayoutInflater inflater;
    private List<Filemodel> filename;
    public Fileadapter(Context context, List<Filemodel> filename)
    {
        inflater = LayoutInflater.from(context);
        this.filename = filename;
        this.context = context;
    }
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view =inflater.inflate(R.layout.file_item,parent,false);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }
    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, int position)
    {
        Filemodel filemodel = filename.get(position);
        holder.setdata(filemodel,position);
         holder.linearLayout.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 Intent intent = new Intent(context,Notestaking.class);
                 intent.putExtra("filename",holder.text.getText());
                 intent.putExtra("foldername",holder.toolbar.getTitle());
                 context.startActivity(intent);
             }
         });
    }
    @Override
    public int getItemCount() {
        return filename.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView text;
        private int position;
        private Filemodel filenamemodel;
        LinearLayout linearLayout;
        Toolbar toolbar ;
        private CardView cardView;
        public MyViewHolder(@NonNull View itemView)
        {
            super(itemView);
            text = (TextView)itemView.findViewById(R.id.Filetext);
            linearLayout =(LinearLayout)itemView.findViewById(R.id.Filelinear);
            toolbar = (Toolbar)itemView.findViewById(R.id.toolbarFiles);
            cardView =(CardView)itemView.findViewById(R.id.Filecard);
        }
        public void setdata(Filemodel filenamemodel, int position)
        {
            this.text.setText(filenamemodel.getFilename());
            this.position = position;
            this.filenamemodel = filenamemodel;
        }
    }
}
