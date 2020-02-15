package com.example.notepad;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.FrameLayout;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    private BottomNavigationView bnv;
    private FrameLayout fl;
    private Notebooks_fragment notes;
    private SearchFragment   search;
    private StickyNotes sticky;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fl=(FrameLayout)findViewById(R.id.framelayout);
        bnv=(BottomNavigationView)findViewById(R.id.bn1);
        notes = new Notebooks_fragment();
        search = new SearchFragment() ;
        sticky = new StickyNotes();
        setFragment(notes);
        bnv.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId())
                {
                    case R.id.nav_Notebooks:
                        setFragment(notes);
                        return true;
                    case R.id.nav_search:
                        setFragment(search);
                        return true;
                    case R.id.nav_sticky:
                        setFragment(sticky);
                        return true;
                    default:
                        return false;
                }
            }
        });

    }
    private void setFragment(Fragment fragment) {
        FragmentTransaction ft=getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.framelayout,fragment);
        ft.commit();

    }
}
