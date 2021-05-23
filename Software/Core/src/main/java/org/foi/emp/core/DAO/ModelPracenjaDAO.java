package org.foi.emp.core.DAO;

import androidx.lifecycle.LiveData;
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

    @Query("DELETE FROM ModelPracenja WHERE id = :modelPracenja")
    public void izbrisiModelPracenjaKolegija(int modelPracenja);

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

    @Query("SELECT * FROM ElementModelaPracenja where id=:modelPracenja")
    public List<ElementModelaPracenja> dohvatiListuElemenataModelaPracenjaPoModeluPracenja(int modelPracenja);

    @Query("SELECT * FROM ElementModelaPracenja where id=:modelPracenja")
    public LiveData<List<ElementModelaPracenja>> dohvatiListuElemenataModelaPracenjaPoModeluPracenjaLIVE(int modelPracenja);

    @Query("SELECT * FROM ElementModelaPracenja where id=:modelPracenja and naziv=:nazivElementa")
    public ElementModelaPracenja dohvatiElementModelaPoIdINazivu(int modelPracenja,String nazivElementa);

    @Update
    public void ažuriranjeElementaModelaPracenja(ElementModelaPracenja... elementModelaPracenjas);

    @Query("SELECT * FROM ElementiModelaPracenja enmp where enmp.modelPracenja=:modelPracenja")
    public LiveData<List<ElementiNaModeluPracenja>> dohvatiElementeModelaPracenjaLIVE(int modelPracenja);

    @Query("SELECT * FROM ElementModelaPracenja emp JOIN ElementiModelaPracenja empi ON emp.id=empi.elementModelaPracenja where empi.modelPracenja=:modelPracenja")
    public LiveData<List<ElementModelaPracenja>> dohvatiElementeModelaLIVE(int modelPracenja);


}
