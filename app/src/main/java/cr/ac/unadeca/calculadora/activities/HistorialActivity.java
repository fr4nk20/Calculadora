package cr.ac.unadeca.calculadora.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.raizlabs.android.dbflow.sql.language.SQLite;

import org.sufficientlysecure.htmltextview.HtmlResImageGetter;

import java.util.List;

import cr.ac.unadeca.calculadora.R;
import cr.ac.unadeca.calculadora.database.models.CalculadoraTable;
import cr.ac.unadeca.calculadora.subclase.CalculadoraViewHolder;

/**
 * Created by pc on 7/4/2018.
 */

public class HistorialActivity extends AppCompatActivity {
    private static Context QuickContext;
    private RecyclerView lista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mostrar);
        QuickContext = this;

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        lista = findViewById(R.id.lista);
        lista.setLayoutManager(new LinearLayoutManager(this));
        List<CalculadoraTable>info =SQLite.select().from(CalculadoraTable.class).queryList();
        lista.setAdapter(new CalculadoraAdapter(info));
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            List<CalculadoraTable> info = SQLite.select().from(CalculadoraTable.class).queryList();
            lista.setAdapter(new CalculadoraAdapter(info));
        }
        return super.onOptionsItemSelected(item);
    }
    public static class CalculadoraAdapter extends RecyclerView.Adapter<CalculadoraViewHolder> {
        private final List<CalculadoraTable> listCalculadoraTable;
        private final LayoutInflater inflater;

        public CalculadoraAdapter(List<CalculadoraTable> listToDoTables) {
            this.inflater = LayoutInflater.from(QuickContext);
            this.listCalculadoraTable = listToDoTables;
        }
        @Override
        public CalculadoraViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = inflater.inflate(R.layout.objecto, parent, false);
            return new CalculadoraViewHolder(view);
        }
        public void animateTo(List<CalculadoraTable> models) {
            applyAndAnimateRemovals(models);
            applyAndAnimateAdditions(models);
            applyAndAnimateMovedItems(models);
        }

        private void applyAndAnimateRemovals(List<CalculadoraTable> newModels) {
            for (int i = listCalculadoraTable.size() - 1; i >= 0; i--) {
                final CalculadoraTable model = listCalculadoraTable.get(i);
                if (!newModels.contains(model)) {
                    removeItem(i);
                }
            }
        }

        private void applyAndAnimateAdditions(List<CalculadoraTable> newModels) {
            for (int i = 0, count = newModels.size(); i < count; i++) {
                final CalculadoraTable model = newModels.get(i);
                if (!listCalculadoraTable.contains(model)) {
                    addItem(i, model);
                }
            }
        }

        private void applyAndAnimateMovedItems(List<CalculadoraTable> newModels) {
            for (int toPosition = newModels.size() - 1; toPosition >= 0; toPosition--) {
                final CalculadoraTable model = newModels.get(toPosition);
                final int fromPosition = listCalculadoraTable.indexOf(model);
                if (fromPosition >= 0 && fromPosition != toPosition) {
                    moveItem(fromPosition, toPosition);
                }
            }
        }

        public CalculadoraTable removeItem(int position) {
            final CalculadoraTable model = listCalculadoraTable.remove(position);
            notifyItemRemoved(position);
            return model;
        }

        public void addItem(int position, CalculadoraTable model) {
            listCalculadoraTable.add(position, model);
            notifyItemInserted(position);
        }

        public void moveItem(int fromPosition, int toPosition) {
            final CalculadoraTable model = listCalculadoraTable.remove(fromPosition);
            listCalculadoraTable.add(toPosition, model);
            notifyItemMoved(fromPosition, toPosition);
        }
        @Override
        public void onBindViewHolder(final CalculadoraViewHolder holder, final int position) {
            final CalculadoraTable current = listCalculadoraTable.get(position);
            holder.html.setHtml(ActividadAString(current),
                    new HtmlResImageGetter(holder.html));
            holder.html.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {

                }
            });

        }
        private  String ActividadAString(CalculadoraTable todo){
            String html= "<a><big><b> <font color =\""+"\">"+todo.integer1+todo.operador+todo.integer2+"="+todo.resultado + "</b></big>";
            return html;
        }
        @Override
        public int getItemCount() {
            return listCalculadoraTable.size();
        }

    }
}
