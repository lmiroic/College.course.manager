package org.foi.emp.collegecoursemanager.viewModels;

import android.app.Application;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import org.foi.emp.core.DAO.KolegijDAO;
import org.foi.emp.core.Database.Database;
import org.foi.emp.core.Entities.ElementModelaPracenja;
import org.foi.emp.core.Entities.ElementiNaModeluPracenja;
import org.foi.emp.core.Entities.Kolegij;
import org.foi.emp.core.Entities.ModelPracenja;

import java.util.List;

public class KolegijViewModel extends AndroidViewModel {
    private Context context;
    private Kolegij kolegij;
    public LiveData<List<Kolegij>> kolegijiLIVEData;

    public KolegijViewModel(@NonNull Application application) {
        super(application);
        this.context = application;
    }

    public LiveData<List<Kolegij>> dohvatiSveKolegijeLIVE() {
        this.kolegijiLIVEData = Database.getInstance(context).getKolegijDAO().dohvatiSveKolegijeLIVE();
        return kolegijiLIVEData;
    }

    public void izbrisiKolegij(final Kolegij kolegij) {
        Database.getInstance(context).getKolegijDAO().brisanjeKolegija(kolegij);
    }

    public void unosKolegija(final Kolegij kolegij) {
        Database.getInstance(context).getKolegijDAO().unosKolegija(kolegij);
    }

    public Kolegij dohvatiKolegijPoID(final int id) {
        kolegij = Database.getInstance(context).getKolegijDAO().dohvatiKolegij(id);
        return kolegij;
    }

    public int unosElementaModelaPracenja(final ElementModelaPracenja emp) {
        return (int) Database.getInstance(context).getModelPracenjaDAO().unosElementaModelaPracenja(emp)[0];
    }

    public void unosElementaNaModeluPracenja(final ElementiNaModeluPracenja empi) {
        Database.getInstance(context).getModelPracenjaDAO().unosElementaNaModeluPracenja(empi);
    }

    public int unosModelaPracenja(final ModelPracenja noviModel) {
        return (int) Database.getInstance(context).getModelPracenjaDAO().unosModelaPracenja(noviModel)[0];
    }
}
