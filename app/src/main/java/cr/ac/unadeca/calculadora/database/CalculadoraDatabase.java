package cr.ac.unadeca.calculadora.database;

import com.raizlabs.android.dbflow.annotation.Database;

import cr.ac.unadeca.calculadora.database.models.CalculadoraTable;

/**
 * Created by pc on 6/4/2018.
 */
@Database(name= CalculadoraDatabase.NAME, version = CalculadoraDatabase.VERSION)
public class CalculadoraDatabase {
    public static final String NAME="Calculadora";
    public static final int VERSION=1;
}
