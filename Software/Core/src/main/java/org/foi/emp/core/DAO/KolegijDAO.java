package org.foi.emp.core.DAO;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import org.foi.emp.core.Entities.Kolegij;

import java.util.List;
@Dao
public interface KolegijDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public long[] unosKolegija(Kolegij... kolegiji);

    @Update
    public void azuriranjeKolegija(Kolegij... kolegiji);

    @Delete
    public void brisanjeKolegija(Kolegij... kolegiji);

    @Query("SELECT * FROM Kolegij")
    public List<Kolegij> dohvatiSveKolegije();

    @Query("SELECT * FROM Kolegij WHERE id = :id")
    public Kolegij dohvatiKolegij(int id);

    @Query("SELECT * FROM Kolegij")
    public LiveData<List<Kolegij>> dohvatiSveKolegijeLIVE();

    @Query("SELECT * FROM Kolegij WHERE id = :id")
    public LiveData<Kolegij> dohvatiKolegijLIVE(int id);

    @Query("DELETE FROM Kolegij")
    public void izbrisiSveKolegije();
}
