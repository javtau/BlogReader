package com.jjv.blogreader_javier_lozano.UI;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.os.Bundle;

/**
 * Created by javi0 on 15/12/2016.
 */

public class AlertDialogFragment extends DialogFragment {
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Context context = getActivity();
        AlertDialog.Builder builder = new AlertDialog.Builder(context)
                .setTitle("Oops, Lo sentimos, hemos sufrido un error...")
                .setMessage("Vuelva ha intentarlo")
                .setPositiveButton("OK", null);
        AlertDialog dialog = builder.create();
        return dialog;

    }
}
