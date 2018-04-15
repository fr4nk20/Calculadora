package cr.ac.unadeca.calculadora.subclase;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import org.sufficientlysecure.htmltextview.HtmlTextView;

import cr.ac.unadeca.calculadora.R;

/**
 * Created by pc on 8/4/2018.
 */

public class CalculadoraViewHolder extends RecyclerView.ViewHolder {
    public HtmlTextView html;
    public ImageView borrar;
    public CalculadoraViewHolder(View itemView) {
        super(itemView);
        html = itemView.findViewById(R.id.html_text);
        borrar = itemView.findViewById(R.id.delete);

    }
}
