package org.foi.emp.collegecoursemanager.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.foi.emp.collegecoursemanager.R;
import org.foi.emp.core.Database.Database;
import org.foi.emp.core.Entities.ElementModelaPracenja;
import org.foi.emp.core.Entities.ElementiNaModeluPracenja;
import org.foi.emp.core.Entities.Kolegij;
import org.foi.emp.core.Entities.ModelPracenja;

public class DodavanjeKolegija extends AppCompatActivity {
    private Button spremi;
    private EditText nazivKolegija, modelPracenja, kolokvij1, kolokvij2, aktivnost, labosi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dodavanje_kolegija);
        this.spremi = findViewById(R.id.btnSpremiBodove);
        this.nazivKolegija = findViewById(R.id.txtNazivKolegija);
        this.modelPracenja = findViewById(R.id.txtModelPracenja);
        this.kolokvij1 = findViewById(R.id.txtKolokvij1MoguciBodovi);
        this.kolokvij2 = findViewById(R.id.txtKolokvij2MoguciBodovi);
        this.aktivnost = findViewById(R.id.txtOstvareniBodovi);
        this.labosi = findViewById(R.id.txtLabosiMoguciBrojBodova);
        this.kolokvij1.setInputType(InputType.TYPE_CLASS_NUMBER);
        this.kolokvij2.setInputType(InputType.TYPE_CLASS_NUMBER);
        this.aktivnost.setInputType(InputType.TYPE_CLASS_NUMBER);
        this.labosi.setInputType(InputType.TYPE_CLASS_NUMBER);
        this.spremi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int maksimalnibrojBodovaKol1 = 0;
                int maksimalnibrojBodovaKol2 = 0;
                int maksimalnibrojBodovaLab = 0;
                int maksimalnibrojBodovaAktivnost = 0;
                if (provjeraPraznihUnosa(kolokvij1.getText().toString(), kolokvij2.getText().toString(), aktivnost.getText().toString(), labosi.getText().toString(), nazivKolegija.getText().toString(), modelPracenja.getText().toString())) {
                    maksimalnibrojBodovaKol1 = Integer.parseInt(kolokvij1.getText().toString());
                    maksimalnibrojBodovaKol2 = Integer.parseInt(kolokvij2.getText().toString());
                    maksimalnibrojBodovaLab = Integer.parseInt(labosi.getText().toString());
                    maksimalnibrojBodovaAktivnost = Integer.parseInt(aktivnost.getText().toString());
                    if ((maksimalnibrojBodovaKol1 + maksimalnibrojBodovaKol2 + maksimalnibrojBodovaLab + maksimalnibrojBodovaAktivnost) == 100) {
                        final String naziv = nazivKolegija.getText().toString();
                        final String model = modelPracenja.getText().toString();
                        final int idModela = unosModelaPracenja(model);
                        unosKolegija(idModela, naziv);
                        addElementModelaPracenjaNaModelPracenja(idModela, unosElementaModelaPracenja(maksimalnibrojBodovaKol1, "Kolokvij 1"));
                        addElementModelaPracenjaNaModelPracenja(idModela, unosElementaModelaPracenja(maksimalnibrojBodovaKol2, "Kolokvij 2"));
                        addElementModelaPracenjaNaModelPracenja(idModela, unosElementaModelaPracenja(maksimalnibrojBodovaLab, "Laboratorijske vjezbe"));
                        addElementModelaPracenjaNaModelPracenja(idModela, unosElementaModelaPracenja(maksimalnibrojBodovaAktivnost, "Aktivnost"));
                        Toast.makeText(getApplicationContext(), "Uspješno ste unijeli kolegij", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getApplicationContext(), "Zbroj mogucih bodova nije jednak 100", Toast.LENGTH_SHORT).show();
                    }
                } else
                    Toast.makeText(getApplicationContext(), "Mjesta za unos moraju biti popunjena", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void addElementModelaPracenjaNaModelPracenja(int idModela, int unosElementaModelaPracenja) {
        unosElementaNaModeluPracenja(unosElementaModelaPracenja, idModela);
    }

    private int unosModelaPracenja(final String nazivModela) {
        final ModelPracenja noviModel = new ModelPracenja();
        noviModel.setNaziv(nazivModela);
        return (int) Database.getInstance(getApplicationContext()).getModelPracenjaDAO().unosModelaPracenja(noviModel)[0];
    }

    private void unosKolegija(final int idModela, String naziv) {
        final Kolegij noviKolegij = new Kolegij();
        noviKolegij.setModelPracenja(idModela);
        noviKolegij.setNazivKolegija(naziv);
        Database.getInstance(getApplicationContext()).getKolegijDAO().unosKolegija(noviKolegij);
    }

    private int unosElementaModelaPracenja(final int maksimalnibrojBodovaKol1, String nazivElementa) {
        final ElementModelaPracenja emp = new ElementModelaPracenja();
        emp.setMaksimalniBrojBodova(maksimalnibrojBodovaKol1);
        emp.setNaziv(nazivElementa);
        emp.setOstvareniBodovi(0);
        return (int) Database.getInstance(getApplicationContext()).getModelPracenjaDAO().unosElementaModelaPracenja(emp)[0];
    }

    private void unosElementaNaModeluPracenja(final int idElementa, final int idModela) {
        final ElementiNaModeluPracenja empi = new ElementiNaModeluPracenja();
        empi.setElementModelaPracenja(idElementa);
        empi.setModelPracenja(idModela);
        Database.getInstance(getApplicationContext()).getModelPracenjaDAO().unosElementaNaModeluPracenja(empi);
    }

    private boolean provjeraPraznihUnosa(String kol1, String kol2, String aktivnost, String labosi, String nazivKolegija, String nazivModela) {
        if (kol1.isEmpty() || kol2.isEmpty() || aktivnost.isEmpty() || labosi.isEmpty() || nazivKolegija.isEmpty() || nazivModela.isEmpty()) {
            return false;
        }
        return true;
    }
}