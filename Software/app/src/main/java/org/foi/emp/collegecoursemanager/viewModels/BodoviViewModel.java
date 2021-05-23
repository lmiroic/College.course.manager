package org.foi.emp.collegecoursemanager.viewModels;

import android.app.Application;
import android.content.Context;
import android.os.Build;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.lifecycle.AndroidViewModel;

import org.foi.emp.core.Database.Database;
import org.foi.emp.core.Entities.ElementModelaPracenja;
import org.foi.emp.core.Entities.Kolegij;

import java.util.List;
import java.util.stream.Collectors;

public class BodoviViewModel extends AndroidViewModel {
    private Context context;

    public BodoviViewModel(@NonNull Application application) {
        super(application);
        this.context = application;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public List<ElementModelaPracenja> DohvatiListuElemenataModelaPracenja(final Kolegij kolegij) {
        return Database.getInstance(context).getModelPracenjaDAO().dohvatiElementeModelaPracenja(kolegij.getModelPracenja())
                .stream()
                .map(elementiNaModeluPracenja -> elementiNaModeluPracenja.getElementModelaPracenja())
                .map(emnp -> {
                    return Database.getInstance(context).getModelPracenjaDAO().dohvatiElementModelaPracenja(emnp);
                }).collect(Collectors.toList());
    }

    public void azuriranjeElementaModelaPracenja(final ElementModelaPracenja emp) {
        Database.getInstance(context).getModelPracenjaDAO().ažuriranjeElementaModelaPracenja(emp);
    }
}
