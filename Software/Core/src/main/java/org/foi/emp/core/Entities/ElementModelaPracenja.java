package org.foi.emp.core.Entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(tableName = "ElementModelaPracenja")
public class ElementModelaPracenja {
    @PrimaryKey(autoGenerate = true)
    private int id;

    private String naziv;
    private int maksimalniBrojBodova;
    private int ostvareniBodovi;

    public ElementModelaPracenja() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public int getMaksimalniBrojBodova() {
        return maksimalniBrojBodova;
    }

    public void setMaksimalniBrojBodova(int maksimalniBrojBodova) {
        this.maksimalniBrojBodova = maksimalniBrojBodova;
    }

    public int getOstvareniBodovi() {
        return ostvareniBodovi;
    }

    public void setOstvareniBodovi(int ostvareniBodovi) {
        this.ostvareniBodovi = ostvareniBodovi;
    }
}
