package org.foi.emp.core.Entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity(tableName = "Bodovi")
public class Bodovi {
    @PrimaryKey(autoGenerate = true)
    private int id;

    private int brojBodova;
    private int maxBrojBodova;
}
