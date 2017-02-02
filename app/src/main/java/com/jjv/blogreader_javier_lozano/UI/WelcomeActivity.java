package com.jjv.blogreader_javier_lozano.UI;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.jjv.blogreader_javier_lozano.R;

public class WelcomeActivity extends AppCompatActivity {
    public static final String NUMENTRADAS = "nentradas";
    EditText txt_nentradas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        txt_nentradas = (EditText) findViewById(R.id.txt_nentradas);
    }

    public void leer(View v) {
        if (txt_nentradas.getText().toString().equals("")) {
            Toast.makeText(this, getString(R.string.campos_vacios), Toast.LENGTH_SHORT).show();
        } else {
            try {
                int num = Integer.parseInt(txt_nentradas.getText().toString());
                if (num > 0) {
                    Intent i = new Intent(this, MainActivity.class);
                    i.putExtra(NUMENTRADAS, num);
                    startActivity(i);
                } else {
                    Toast.makeText(this, getString(R.string.toast_nvalido), Toast.LENGTH_SHORT).show();
                }
            } catch (NumberFormatException e) {
                Toast.makeText(this, getString(R.string.toast_nvalido), Toast.LENGTH_SHORT).show();
            }
        }


    }
}
