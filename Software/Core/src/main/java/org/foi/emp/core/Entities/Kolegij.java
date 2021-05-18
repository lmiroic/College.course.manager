package org.foi.emp.core.Entities;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(tableName = "Kolegij",foreignKeys = {@ForeignKey(entity = ModelPracenja.class,parentColumns = "id",childColumns = "modelPracenja")})
public class Kolegij {
    @PrimaryKey(autoGenerate = true)
    private int id;

    private String nazivKolegija;
    private int modelPracenja;

    public Kolegij() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNazivKolegija() {
        return nazivKolegija;
    }

    public void setNazivKolegija(final String nazivKolegija) {
        this.nazivKolegija = nazivKolegija;
    }

    public int getModelPracenja() {
        return modelPracenja;
    }

    public void setModelPracenja(final int modelPracenja) {
        this.modelPracenja = modelPracenja;
    }
}
