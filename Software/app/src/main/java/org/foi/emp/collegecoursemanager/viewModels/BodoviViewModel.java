package org.foi.emp.collegecoursemanager.viewModels;

import android.app.Application;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import org.foi.emp.core.Database.Database;
import org.foi.emp.core.Entities.Kolegij;

public class BodoviViewModel extends AndroidViewModel {
    private Context context;
    public BodoviViewModel(@NonNull Application application,Context context) {
        super(application);
        this.context=context;
    }
    public void izbrisiElementeKolegija(Kolegij kolegij){
        Database.getInstance(context).getModelPracenjaDAO();
    }
}
