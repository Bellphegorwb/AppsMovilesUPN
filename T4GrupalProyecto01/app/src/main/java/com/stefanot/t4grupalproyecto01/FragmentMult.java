package com.stefanot.t4grupalproyecto01;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragmentMult#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentMult extends Fragment {

    View vista;

    EditText txtN1, txtN2;
    TextView lblRespuesta;
    Button btnCalcular;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public FragmentMult() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FragmentMult.
     */
    // TODO: Rename and change types and number of parameters
    public static FragmentMult newInstance(String param1, String param2) {
        FragmentMult fragment = new FragmentMult();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        vista=inflater.inflate(R.layout.fragment_mult, container, false);
        asignarReferencias();
        return vista;
    }
    private void asignarReferencias(){
        btnCalcular=vista.findViewById(R.id.btnCalcular);
        lblRespuesta=vista.findViewById(R.id.lblRespuesta);
        txtN1=vista.findViewById(R.id.txtN1);
        txtN2=vista.findViewById(R.id.txtN2);
        btnCalcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean valida=true;
                int n1,n2,res;
                if(txtN1.getText().toString().equals("")){
                    txtN1.setError("Obligatorio");
                    valida=false;
                }
                if(txtN2.getText().toString().equals("")){
                    txtN2.setError("Obligatorio");
                    valida=false;
                }
                if(valida) {
                    n1 = Integer.parseInt(txtN1.getText().toString());
                    n2 = Integer.parseInt(txtN2.getText().toString());
                    res=n1*n2;
                    lblRespuesta.setText(res+"");
                }

            }
        });
    }
}