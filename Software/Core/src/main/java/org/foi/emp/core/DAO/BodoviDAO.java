package org.foi.emp.core.DAO;

import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import org.foi.emp.core.Entities.Bodovi;
import org.foi.emp.core.Entities.BodoviNaKolegiju;

import java.util.List;

public interface BodoviDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public long[] unosBodova(Bodovi... bodovi);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public long[] unosBodovaNaKolegiju(BodoviNaKolegiju... bodoviNaKolegiju);

    @Update
    public void azurirajBodove(Bodovi... bodovi);

    @Update
    public void azurirajBodoveNaKolegiju(BodoviNaKolegiju... bodoviNaKolegiju);

    @Query("SELECT * FROM Bodovi")
    public List<Bodovi> dohvatiSveBodove();
}
