package com.example.nihal.medeasy.widget;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatDialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import com.example.nihal.medeasy.R;

public class CustomDialog extends AppCompatDialogFragment {

    EditText syndrome , drug ;

    CustomDialogListener listener ;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity()) ;
        LayoutInflater inflater  =  getActivity().getLayoutInflater() ;
        View view = inflater.inflate(R.layout.synder_dialog_model,null) ;
        builder.setView(view)
                .setPositiveButton("conform", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String syndromeText = syndrome.getText().toString() ;
                        String drugText = drug.getText().toString() ;
                        listener.applyText(syndromeText,drugText) ;
                    }
                })
                .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                }) ;


            syndrome = view.findViewById(R.id.syndromeFromDialog) ;
            drug = view.findViewById(R.id.drugFromDialog) ;

        return builder.create() ;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            listener = (CustomDialogListener) context ;
        } catch (ClassCastException e) {
            throw  new  ClassCastException(context.toString()+"Error") ;
        }
    }

    public interface CustomDialogListener {
        void applyText (String syndrome , String drug ) ;
    }
}
