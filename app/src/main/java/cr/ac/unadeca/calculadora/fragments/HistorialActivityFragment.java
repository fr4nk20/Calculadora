package cr.ac.unadeca.calculadora.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import cr.ac.unadeca.calculadora.R;


public class HistorialActivityFragment extends Fragment{
public HistorialActivityFragment(){

    }
    public View  onCreateView(LayoutInflater inflater, ViewGroup container,
                                  Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_mostrar, container, false);
    }
}
