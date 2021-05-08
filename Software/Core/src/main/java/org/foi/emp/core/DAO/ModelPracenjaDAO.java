package org.foi.emp.core.DAO;

import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import org.foi.emp.core.Entities.Kolegij;
import org.foi.emp.core.Entities.ModelPracenja;

import java.util.List;

public interface ModelPracenjaDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public long[] unosModelaPracenja(ModelPracenja... modeliPracenja);

    @Update
    public void azuriranjeModelaPracenja(ModelPracenja... modelPracenja);

    @Delete
    public void brisanjeModelaPracenja(ModelPracenja... modelPracenja);

    @Query("SELECT * FROM ModelPracenja")
    public List<Kolegij> dohvatiSveModelePracenja();
}
