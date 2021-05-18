package org.foi.emp.collegecoursemanager;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.room.util.StringUtil;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.foi.emp.core.Database.Database;
import org.foi.emp.core.Entities.ElementModelaPracenja;
import org.foi.emp.core.Entities.ElementiNaModeluPracenja;
import org.foi.emp.core.Entities.Kolegij;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class DodavanjeBodovaKolegiju extends AppCompatActivity {
    private static final String INTENT_KOLEGIJ_ID = "ID_kolegija";
    private Spinner spListaElemenataKolegija;
    private EditText ostvareniBodovi;
    private Button spremiBodove;
    private TextView nazivKolegija;
    private List<ElementModelaPracenja> elementiModelaPracenja = new ArrayList<>();

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dodavanje_bodova_kolegiju);
        Intent i = getIntent();
        this.ostvareniBodovi = findViewById(R.id.txtOstvareniBodovi);
        this.ostvareniBodovi.setInputType(InputType.TYPE_CLASS_NUMBER);
        this.spListaElemenataKolegija = findViewById(R.id.spElementiModelaPracenja);
        this.spremiBodove = findViewById(R.id.btnSpremiBodove);
        this.nazivKolegija = findViewById(R.id.tvNazivKolegijaZaBodove);
        if (i != null && i.getIntExtra(INTENT_KOLEGIJ_ID, 0) != 0) {
            Kolegij kolegij = Database.getInstance(getApplicationContext()).getKolegijDAO().dohvatiKolegij(i.getIntExtra(INTENT_KOLEGIJ_ID, 0));
            elementiModelaPracenja = DohvatiListuElemenataModelaPracenja(kolegij);
            //Database.getInstance(getApplicationContext()).getModelPracenjaDAO().dohvatiListuElemenataModelaPracenjaPoModeluPracenjaLIVE(kolegij.getModelPracenja());

            this.nazivKolegija.setText(kolegij.getNazivKolegija());
            PostaviSpinner();
            spListaElemenataKolegija.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    ElementModelaPracenja emp = elementiModelaPracenja.get(position);
                    ostvareniBodovi.setText(String.valueOf(emp.getOstvareniBodovi()));
                    spremiBodove.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if(Integer.parseInt(ostvareniBodovi.getText().toString())<=emp.getMaksimalniBrojBodova()){
                                emp.setOstvareniBodovi(Integer.parseInt(ostvareniBodovi.getText().toString()));
                                Database.getInstance(getApplicationContext()).getModelPracenjaDAO().ažuriranjeElementaModelaPracenja(emp);
                                Toast.makeText(getApplicationContext(),"Uspjesno unesni bodovi za "+emp.getNaziv(),Toast.LENGTH_SHORT).show();
                            }
                            else{
                                Toast.makeText(getApplicationContext(),"Ostvareni bodovi ne mogu biti veci od "+String.valueOf(emp.getMaksimalniBrojBodova()),Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
                @Override
                public void onNothingSelected(AdapterView<?> parent) {
                }
            });
        }
    }

    private void PostaviSpinner() {
        List<String> elementi = new ArrayList<>();
        for (ElementModelaPracenja e : elementiModelaPracenja) {
            elementi.add(e.getNaziv());
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.simple_spinner_item, elementi);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spListaElemenataKolegija.setAdapter(adapter);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private List<ElementModelaPracenja> DohvatiListuElemenataModelaPracenja(Kolegij kolegij) {
        return Database.getInstance(getApplicationContext()).getModelPracenjaDAO().dohvatiElementeModelaPracenja(kolegij.getModelPracenja())
                .stream()
                .map(elementiNaModeluPracenja -> elementiNaModeluPracenja.getElementModelaPracenja())
                .map(emnp -> {
                    return Database.getInstance(getApplicationContext()).getModelPracenjaDAO().dohvatiElementModelaPracenja(emnp);
                }).collect(Collectors.toList());
    }
}