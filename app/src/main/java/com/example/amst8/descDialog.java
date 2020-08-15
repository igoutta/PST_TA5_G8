package com.example.amst8;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;


public class descDialog {

    public descDialog() {
    }

    public void showDialog(View view, String msg){
        ViewGroup viewGroup = view.findViewById(android.R.id.content);
        View dialogView = LayoutInflater.from(view.getContext()).inflate(R.layout.custom_desc, viewGroup, false);
        AlertDialog alertDialog = new AlertDialog.Builder(view.getContext()).create();
        alertDialog.setView(dialogView);

        TextView text = dialogView.findViewById(R.id.text_dialog);
        text.setText(msg);

        alertDialog.show();
    }
}
