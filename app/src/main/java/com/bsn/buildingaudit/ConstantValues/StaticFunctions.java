package com.bsn.buildingaudit.ConstantValues;

import android.app.Activity;
import android.app.Dialog;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bsn.buildingaudit.Adapters.AdpterResonsApproved;
import com.bsn.buildingaudit.R;

import java.util.ArrayList;

public class StaticFunctions {
    private static Dialog dialog;
    public static void showDialogApprove(Activity activity, ArrayList<String> myImageNameList) {

        dialog = new Dialog(activity);
        // dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.approved_button_card);

        Button btndialog = (Button) dialog.findViewById(R.id.approovedBtnDialoge);
        ImageView btndialogClose = (ImageView) dialog.findViewById(R.id.dilogeCloseBtnApprove);
        btndialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dialog.dismiss();
            }
        });
        btndialogClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dialog.dismiss();
            }
        });


        RecyclerView recyclerView = dialog.findViewById(R.id.recycleForReason);
        AdpterResonsApproved adapterRe = new AdpterResonsApproved(activity, myImageNameList);
        recyclerView.setAdapter(adapterRe);
        recyclerView.setLayoutManager(new LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false));
        dialog.show();
    }


    public static void showDialogReject(Activity activity, ArrayList<String> myImageNameList) {

        dialog = new Dialog(activity);
        // dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.rejection_button_card);

        Button btndialog = (Button) dialog.findViewById(R.id.rejectdBtnDialoge);
        ImageView btndialogClose = (ImageView) dialog.findViewById(R.id.dilogeCloseBtn);
        btndialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dialog.dismiss();
            }
        });
        btndialogClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dialog.dismiss();
            }
        });


        RecyclerView recyclerView = dialog.findViewById(R.id.recycleForReasonreject);
        AdpterResonsApproved adapterRe = new AdpterResonsApproved(activity, myImageNameList);
        recyclerView.setAdapter(adapterRe);
        recyclerView.setLayoutManager(new LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false));
        dialog.show();
    }
}
