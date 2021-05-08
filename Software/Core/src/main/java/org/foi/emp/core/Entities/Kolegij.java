package org.foi.emp.core.Entities;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity(tableName = "Kolegij",foreignKeys = {@ForeignKey(entity = ModelPracenja.class,parentColumns = "id",childColumns = "modelPracenja")})
public class Kolegij {
    @PrimaryKey(autoGenerate = true)
    private int id;

    private String nazivKolegija;
    private int modelPracenja;
}
