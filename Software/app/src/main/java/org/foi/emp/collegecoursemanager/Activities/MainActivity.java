package org.foi.emp.collegecoursemanager.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import org.foi.emp.collegecoursemanager.Adapters.KolegijAdapter;
import org.foi.emp.collegecoursemanager.R;
import org.foi.emp.collegecoursemanager.viewModels.KolegijViewModel;
import org.foi.emp.core.Database.Database;
import org.foi.emp.core.Entities.Kolegij;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private Database database;
    private RecyclerView recyclerView;
    private KolegijViewModel kolegijViewModel;
    private KolegijAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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
        brisiKolegij(recyclerView, adapter);
    }

    private KolegijAdapter PostaviRecycleView() {
        this.recyclerView = (RecyclerView) findViewById(R.id.recyleViewPopisKolegija);
        this.adapter = new KolegijAdapter(this, this);
        this.kolegijViewModel.dohvatiSveKolegijeLIVE();
        this.adapter.postaviKolegije(kolegijViewModel.dohvatiSveKolegijeLIVE().getValue());
        this.kolegijViewModel.kolegijiLIVEData.observe(this, new Observer<List<Kolegij>>() {
            @Override
            public void onChanged(List<Kolegij> kolegijs) {
                adapter.postaviKolegije(kolegijs);
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

    public void brisiKolegij(final RecyclerView recyclerView, final KolegijAdapter kolegijAdapter) {
        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                final Kolegij kolegij = kolegijAdapter.getKolegijAtPosition(viewHolder.getAdapterPosition());
                kolegijAdapter.removeKolegijAtPosition(viewHolder.getAdapterPosition());
                kolegijViewModel.izbrisiKolegij(kolegij);
                Snackbar.make(recyclerView, "Izbrisan je kolegij " + kolegij.getNazivKolegija(), Snackbar.LENGTH_SHORT).setAction("Action", null).show();
            }
        }).attachToRecyclerView(recyclerView);
    }
}