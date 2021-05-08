package org.foi.emp.core.Database;

import android.content.Context;

import androidx.room.Room;
import androidx.room.RoomDatabase;

import org.foi.emp.core.Entities.Bodovi;
import org.foi.emp.core.Entities.BodoviNaKolegiju;
import org.foi.emp.core.Entities.ElementModelaPracenja;
import org.foi.emp.core.Entities.ElementiNaModeluPracenja;
import org.foi.emp.core.Entities.Kolegij;
import org.foi.emp.core.Entities.ModelPracenja;

@androidx.room.Database(version = Database.VERSION,entities = {Bodovi.class, BodoviNaKolegiju.class, Kolegij.class, ModelPracenja.class, ElementModelaPracenja.class, ElementiNaModeluPracenja.class},exportSchema = false)
public abstract class Database extends RoomDatabase {

    public static final int VERSION = 1;
    public static final String NAME = "CollegeCourseManager";
    private static Database INSTANCE = null;

    public synchronized static Database getInstance(final Context context){
        if(INSTANCE == null){
            INSTANCE = Room.databaseBuilder(
                    context.getApplicationContext(),
                    Database.class,
                    Database.NAME
            ).allowMainThreadQueries().build();
        }
        return INSTANCE;
    }

}
