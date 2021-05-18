package org.foi.emp.collegecoursemanager;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import org.foi.emp.collegecoursemanager.Adapters.KolegijAdapter;
import org.foi.emp.collegecoursemanager.viewModels.KolegijViewModel;
import org.foi.emp.core.Database.Database;
import org.foi.emp.core.Entities.Kolegij;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private Database database;
    private RecyclerView recyclerView;
    private KolegijViewModel kolegijViewModel;
    private KolegijAdapter adapter;
    private View view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        this.kolegijViewModel = ViewModelProviders.of(this).get(KolegijViewModel.class);
        this.database = Database.getInstance(this);
        FloatingActionButton fab = findViewById(R.id.btnDodajKolegij);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, DodavanjeKolegija.class));
            }
        });
        PostaviRecycleView();
    }

    private KolegijAdapter PostaviRecycleView() {

        this.recyclerView = (RecyclerView) findViewById(R.id.recyleViewPopisKolegija);
        this.adapter = new KolegijAdapter(this);
        this.kolegijViewModel.dohvatiSveKolegijeLIVE();
        this.adapter.setKolegiji(kolegijViewModel.dohvatiSveKolegijeLIVE().getValue());
        this.kolegijViewModel.kolegijiLIVEData.observe(this, new Observer<List<Kolegij>>() {
            @Override
            public void onChanged(List<Kolegij> kolegijs) {
                adapter.setKolegiji(kolegijs);
                recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                recyclerView.setHasFixedSize(false);
                recyclerView.setAdapter(adapter);
            }
        });
        return adapter;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}