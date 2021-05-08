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
@Entity(tableName = "BodoviNaKolegiju", foreignKeys = {@ForeignKey(entity = Kolegij.class, parentColumns = "id", childColumns = "kolegij"), @ForeignKey(entity = Bodovi.class, parentColumns = "id", childColumns = "bodovi")})
public class BodoviNaKolegiju {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private int kolegij;
    private int bodovi;
}
