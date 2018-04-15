package cr.ac.unadeca.calculadora.activities;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import cr.ac.unadeca.calculadora.R;
import cr.ac.unadeca.calculadora.database.models.CalculadoraTable;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    EditText n1, n2;
    Button s, r, m, d, p;
    TextView resultado;
    String signo;
    double result;

    MediaPlayer mp;

    


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mp =MediaPlayer.create(this, R.raw.bambu_1);
        n1 = findViewById(R.id.n1);
        n2 = findViewById(R.id.n2);

        s= findViewById(R.id.s);
        r= findViewById(R.id.r);
        m= findViewById(R.id.m);
        d= findViewById(R.id.d);

        resultado = findViewById(R.id.resultado);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent actividad = new Intent(getApplicationContext(), HistorialActivity.class);
                getApplicationContext().startActivity(actividad);
            }
        });
        //creo los eventos

        s.setOnClickListener(this);
        r.setOnClickListener(this);
        m.setOnClickListener(this);
        d.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {

        String Cifra1 = n1.getText().toString();
        String Cifra2 = n2.getText().toString();

        double int1=Double.parseDouble(Cifra1);
        double int2=Double.parseDouble(Cifra2);

        double rta=0;

        switch (v.getId()){
            case R.id.s:
                rta=int1+int2;
                result = rta;
                signo="+";
                guardar();
                mp.start();
                break;
            case R.id.r:
                rta=int1-int2;
                result = rta;
                signo="-";
                guardar();
                mp.start();
                break;
            case R.id.m:
                rta=int1*int2;
                result = rta;
                signo="*";
                guardar();
                mp.start();
                break;
            case R.id.d:
                rta=int1/int2;
                result = rta;
                signo="/";
                mp.start();
                guardar();
                break;
        }
        resultado.setText(""+rta);
    }
    private void guardar(){
        CalculadoraTable registro = new CalculadoraTable();
        registro.integer1 =n1.getText().toString();
        registro.integer2 = n2.getText().toString();
        registro.operador = signo;
        registro.resultado = result;
        registro.save();
    }
    
}