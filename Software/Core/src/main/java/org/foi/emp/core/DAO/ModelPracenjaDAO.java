package org.foi.emp.core.DAO;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import org.foi.emp.core.Entities.ElementModelaPracenja;
import org.foi.emp.core.Entities.ElementiNaModeluPracenja;
import org.foi.emp.core.Entities.ModelPracenja;

import java.util.List;
@Dao
public interface ModelPracenjaDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public long[] unosModelaPracenja(ModelPracenja... modeliPracenja);

    @Update
    public void azuriranjeModelaPracenja(ModelPracenja... modelPracenja);

    @Delete
    public void brisanjeModelaPracenja(ModelPracenja... modelPracenja);

    @Query("SELECT * FROM ModelPracenja")
    public List<ModelPracenja> dohvatiSveModelePracenja();

    @Query("SELECT * FROM ElementiModelaPracenja enmp where enmp.modelPracenja=:modelPracenja")
    public List<ElementiNaModeluPracenja> dohvatiElementeModelaPracenja(int modelPracenja);

    @Query("SELECT * FROM ElementModelaPracenja where id=:modelPracenja")
    public ElementModelaPracenja dohvatiElementModelaPracenja(int modelPracenja);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public long[] unosElementaModelaPracenja(ElementModelaPracenja... elementModelaPracenjas);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void unosElementaNaModeluPracenja(ElementiNaModeluPracenja... elementiNaModeluPracenjas);



}
