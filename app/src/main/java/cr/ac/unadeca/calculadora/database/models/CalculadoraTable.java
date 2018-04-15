package cr.ac.unadeca.calculadora.database.models;

import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.Database;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.structure.BaseModel;

import cr.ac.unadeca.calculadora.database.CalculadoraDatabase;

/**
 * Created by pc on 7/4/2018.
 */
@Table(database = CalculadoraDatabase.class)
public class CalculadoraTable extends BaseModel{
   @PrimaryKey(autoincrement = true)
    public long id;
   @Column
    public  String integer1;
    @Column
    public String operador;
   @Column
    public  String integer2;
   @Column
    public double resultado;
}
