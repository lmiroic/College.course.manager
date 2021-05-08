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
@Entity(tableName = "ElementiModelaPracenja",foreignKeys = {@ForeignKey(entity = ModelPracenja.class,parentColumns = "id",childColumns = "modelPracenja"),@ForeignKey(entity = ElementModelaPracenja.class,parentColumns = "id",childColumns = "elementModelaPracenja")})
public class ElementiNaModeluPracenja {
    @PrimaryKey(autoGenerate = true)
    private int id;

    private int modelPracenja;
    private int elementModelaPracenja;


}
