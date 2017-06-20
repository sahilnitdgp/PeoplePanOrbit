package com.example.sahil.peoplepanorbit;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import static android.R.attr.fragment;

public class NewUser extends AppCompatActivity {

    DataBaseHelperSos mydbsos;
    DataBaseHelper mydb;
    private Fragment fragment;
    private FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



        mydb = new DataBaseHelper(this);

        mydbsos = new DataBaseHelperSos(this);



         Button yes,no;

        AlertDialog.Builder mBuilder = new AlertDialog.Builder(this);

            final View mView = getLayoutInflater().inflate(R.layout.new_user, null);

            yes = (Button) mView.findViewById(R.id.user_yes);


            mBuilder.setView(mView);
            final AlertDialog dialog = mBuilder.create();
            dialog.show();
            dialog.setCancelable(false);

            yes.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    //  Toast.makeText(getActivity(),"yessss",Toast.LENGTH_LONG).show();

                    AlertDialog.Builder updateBuilder = new AlertDialog.Builder(view.getContext());

                    View updateView = getLayoutInflater().inflate(R.layout.update_name_age, null);


                    final EditText edit_user_name = (EditText) updateView.findViewById(R.id.update_name);
                    final EditText edit_user_age = (EditText) updateView.findViewById(R.id.update_age);


                    // final String name = edit_user_name.getText().toString();final String age = edit_user_age.getText().toString();


                    Button btn_update = (Button) updateView.findViewById(R.id.update);

                    updateBuilder.setView(updateView);
                    final AlertDialog updatedialog = updateBuilder.create();
                    updatedialog.show();


                    btn_update.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            //update here name and age

                            //something


                            //sos settings


                            AlertDialog.Builder sosBuilder = new AlertDialog.Builder(view.getContext());

                            View sosView = getLayoutInflater().inflate(R.layout.dialog_sos_setting, null);
                            final EditText edt_sos_user_name = (EditText) sosView.findViewById(R.id.sos_user_name);
                            final EditText edt_sos_phone = (EditText) sosView.findViewById(R.id.sos_phone_number);
                            Button btn_ok = (Button) sosView.findViewById(R.id.sos_ok);


                            sosBuilder.setView(sosView);
                            final AlertDialog sosdialog = sosBuilder.create();
                            sosdialog.show();

                            btn_ok.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {

                                    //code here to save details to database;


                                    boolean isInserted = mydbsos.insertData(edt_sos_user_name.getText().toString(), edt_sos_phone.getText().toString());

                                    if (isInserted = true)
                                        Toast.makeText(NewUser.this, "SOS Setting  Successfully", Toast.LENGTH_LONG).show();
                                    else
                                        Toast.makeText(NewUser.this, "Details Not Inserted !!!", Toast.LENGTH_LONG).show();


                                    //  Toast.makeText(getActivity(),"phone user",Toast.LENGTH_LONG).show();


                                    fragmentManager = getSupportFragmentManager();
                                    fragment = new Medicine();
                                    final FragmentTransaction transaction = fragmentManager.beginTransaction();
                                    transaction.add(R.id.main_container, fragment).commit();

                                    sosdialog.dismiss();
                                }
                            });


                            fragmentManager = getSupportFragmentManager();
                            fragment = new Medicine();
                            final FragmentTransaction transaction = fragmentManager.beginTransaction();
                            transaction.add(R.id.main_container, fragment).commit();

                            Toast.makeText(NewUser.this, "User Successfully", Toast.LENGTH_LONG).show();

                            updatedialog.dismiss();
                        }
                    });

                    fragmentManager = getSupportFragmentManager();
                    fragment = new Medicine();
                    final FragmentTransaction transaction = fragmentManager.beginTransaction();
                    transaction.add(R.id.main_container, fragment).commit();


                    dialog.dismiss();


                }
            });

            no = (Button) mView.findViewById(R.id.user_no);

            no.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {


                    fragmentManager = getSupportFragmentManager();
                    fragment = new Medicine();
                    final FragmentTransaction transaction = fragmentManager.beginTransaction();
                    transaction.add(R.id.main_container, fragment).commit();


                    dialog.dismiss();

                    //Toast.makeText(getActivity(),"nooooo",Toast.LENGTH_LONG).show();

                }
            });


    }


}
