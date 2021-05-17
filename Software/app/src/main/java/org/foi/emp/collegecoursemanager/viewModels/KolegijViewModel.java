package org.foi.emp.collegecoursemanager.viewModels;

import android.app.Application;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import org.foi.emp.core.DAO.KolegijDAO;
import org.foi.emp.core.Database.Database;
import org.foi.emp.core.Entities.Kolegij;

import java.util.List;

public class KolegijViewModel extends AndroidViewModel {
    private Context context;
    public LiveData<List<Kolegij>>kolegijiLIVEData;
    public KolegijViewModel(@NonNull Application application) {
        super(application);
        this.context=application;
    }
    public LiveData<List<Kolegij>> dohvatiSveKolegijeLIVE(){
        kolegijiLIVEData= Database.getInstance(context).getKolegijDAO().dohvatiSveKolegijeLIVE();
        return  kolegijiLIVEData;
    }
}
