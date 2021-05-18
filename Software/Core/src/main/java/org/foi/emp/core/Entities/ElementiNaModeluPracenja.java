package org.foi.emp.core.Entities;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity(tableName = "ElementiModelaPracenja",foreignKeys = {@ForeignKey(entity = ModelPracenja.class,parentColumns = "id",childColumns = "modelPracenja",onDelete = ForeignKey.CASCADE),@ForeignKey(entity = ElementModelaPracenja.class,parentColumns = "id",childColumns = "elementModelaPracenja",onDelete = ForeignKey.CASCADE)})
public class ElementiNaModeluPracenja {
    @PrimaryKey(autoGenerate = true)
    private int id;

    private int modelPracenja;
    private int elementModelaPracenja;

    public ElementiNaModeluPracenja() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getModelPracenja() {
        return modelPracenja;
    }

    public void setModelPracenja(final int modelPracenja) {
        this.modelPracenja = modelPracenja;
    }

    public int getElementModelaPracenja() {
        return elementModelaPracenja;
    }

    public void setElementModelaPracenja(final int elementModelaPracenja) {
        this.elementModelaPracenja = elementModelaPracenja;
    }
}
