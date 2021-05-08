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
@Entity(tableName = "ModelPracenja")
public class ModelPracenja {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String naziv;
}
