package org.foi.emp.collegecoursemanager;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.foi.emp.core.Database.Database;
import org.foi.emp.core.Entities.ElementModelaPracenja;
import org.foi.emp.core.Entities.ElementiNaModeluPracenja;
import org.foi.emp.core.Entities.Kolegij;
import org.foi.emp.core.Entities.ModelPracenja;

public class DodavanjeKolegija extends AppCompatActivity {
    Button spremi;
    EditText nazivKolegija;
    EditText modelPracenja;
    EditText kolokvij1, kolokvij2, aktivnost, labosi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dodavanje_kolegija);
        spremi = findViewById(R.id.btnSpremiKolegij);
        nazivKolegija = findViewById(R.id.txtNazivKolegija);
        modelPracenja = findViewById(R.id.txtModelPracenja);
        kolokvij1 = findViewById(R.id.txtKolokvij1MoguciBodovi);
        kolokvij2 = findViewById(R.id.txtKolokvij2MoguciBodovi);
        aktivnost = findViewById(R.id.txtAktivnostMoguciBodovi);
        labosi = findViewById(R.id.txtLabosiMoguciBrojBodova);
        spremi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!nazivKolegija.getText().toString().isEmpty()){
                    final String naziv = nazivKolegija.getText().toString();
                    String model = modelPracenja.getText().toString();
                    ModelPracenja noviModel = new ModelPracenja();
                    noviModel.setNaziv(model);
                    int idModela = (int) Database.getInstance(getApplicationContext()).getModelPracenjaDAO().unosModelaPracenja(noviModel)[0];
                    Kolegij noviKolegij = new Kolegij();
                    noviKolegij.setModelPracenja(idModela);
                    noviKolegij.setNazivKolegija(naziv);
                    Database.getInstance(getApplicationContext()).getKolegijDAO().unosKolegija(noviKolegij);

                    int maksimalnibrojBodovaKol1 = Integer.parseInt(kolokvij1.getText().toString());
                    ElementModelaPracenja emp = new ElementModelaPracenja();
                    emp.setMaksimalniBrojBodova(maksimalnibrojBodovaKol1);
                    emp.setNaziv("Kolokvij 1");
                    emp.setOstvareniBodovi(0);
                    int idElementa = (int) Database.getInstance(getApplicationContext()).getModelPracenjaDAO().unosElementaModelaPracenja(emp)[0];
                    ElementiNaModeluPracenja empi = new ElementiNaModeluPracenja();
                    empi.setElementModelaPracenja(idElementa);
                    empi.setModelPracenja(idModela);
                    Database.getInstance(getApplicationContext()).getModelPracenjaDAO().unosElementaNaModeluPracenja(empi);

                    int maksimalnibrojBodovaKol2 = Integer.parseInt(kolokvij2.getText().toString());
                    ElementModelaPracenja emp1 = new ElementModelaPracenja();
                    emp1.setMaksimalniBrojBodova(maksimalnibrojBodovaKol2);
                    emp1.setNaziv("Kolokvij 2");
                    emp1.setOstvareniBodovi(0);
                    int idElementa1 = (int) Database.getInstance(getApplicationContext()).getModelPracenjaDAO().unosElementaModelaPracenja(emp1)[0];
                    ElementiNaModeluPracenja empi1 = new ElementiNaModeluPracenja();
                    empi1.setElementModelaPracenja(idElementa1);
                    empi1.setModelPracenja(idModela);
                    Database.getInstance(getApplicationContext()).getModelPracenjaDAO().unosElementaNaModeluPracenja(empi1);

                    int maksimalnibrojBodovaLab = Integer.parseInt(labosi.getText().toString());
                    ElementModelaPracenja emp2 = new ElementModelaPracenja();
                    emp2.setMaksimalniBrojBodova(maksimalnibrojBodovaLab);
                    emp2.setNaziv("Laboratorijske vjezbe");
                    emp2.setOstvareniBodovi(0);
                    int idElementa2 = (int) Database.getInstance(getApplicationContext()).getModelPracenjaDAO().unosElementaModelaPracenja(emp2)[0];
                    ElementiNaModeluPracenja empi2 = new ElementiNaModeluPracenja();
                    empi2.setElementModelaPracenja(idElementa2);
                    empi2.setModelPracenja(idModela);
                    Database.getInstance(getApplicationContext()).getModelPracenjaDAO().unosElementaNaModeluPracenja(empi2);

                    int maksimalnibrojBodovaAktivnost = Integer.parseInt(aktivnost.getText().toString());
                    ElementModelaPracenja emp3 = new ElementModelaPracenja();
                    emp3.setMaksimalniBrojBodova(maksimalnibrojBodovaAktivnost);
                    emp3.setNaziv("Aktivnost");
                    emp3.setOstvareniBodovi(0);
                    int idElementa3 = (int) Database.getInstance(getApplicationContext()).getModelPracenjaDAO().unosElementaModelaPracenja(emp3)[0];
                    ElementiNaModeluPracenja empi3 = new ElementiNaModeluPracenja();
                    empi3.setElementModelaPracenja(idElementa3);
                    empi3.setModelPracenja(idModela);
                    Database.getInstance(getApplicationContext()).getModelPracenjaDAO().unosElementaNaModeluPracenja(empi3);
                }
                else{
                    Toast.makeText(getApplicationContext(),"Niste unijeli naziv kolegija",Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

}