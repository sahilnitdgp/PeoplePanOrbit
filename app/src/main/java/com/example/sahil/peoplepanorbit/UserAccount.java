package com.example.sahil.peoplepanorbit;


import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.preference.DialogPreference;
import android.support.v4.app.Fragment;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.SupportActivity;
import android.support.v4.content.SharedPreferencesCompat;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import de.hdodenhof.circleimageview.CircleImageView;


/**
 * A simple {@link Fragment} subclass.
 */
public class UserAccount extends Fragment {

    Button addmedicine,sossetting;
    CircleImageView profileomage;
  public   TextView text_update_name,text_update_age;
    String phone = "";

    DataBaseHelper mydb;

    DataBaseHelperSos mydbsos;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_user_account, container, false);

        addmedicine = (Button)view.findViewById(R.id.add_medicine);
        sossetting = (Button)view.findViewById(R.id.sos_settings);
        profileomage = (CircleImageView)view.findViewById(R.id.profile_image);
       text_update_name = (TextView)view.findViewById(R.id.edit_name);
        text_update_age  = (TextView)view.findViewById(R.id.edit_age);

        text_update_name.setText("Dharmvir");
        text_update_age.setText("24");

        //get value from another fragment



        /*String name = ((EditText) getActivity().findViewById(R.id.update_name)).getText().toString();
        String age = ((EditText) getActivity().findViewById(R.id.update_age)).getText().toString();

        text_update_name.setText(name);
        text_update_age.setText(age);*/


        mydb = new DataBaseHelper(getActivity());
        mydbsos  = new DataBaseHelperSos(getActivity());

          //Retrieve phone number
        Cursor data = mydbsos. getAllDataSos();

        if(data.moveToFirst()){
            do{

                phone = data.getString(0);
            }while (data.moveToNext());
        }






        profileomage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog.Builder  updateBuilder = new AlertDialog.Builder(view.getContext());

               View updateView = getActivity().getLayoutInflater().inflate(R.layout.update_name_age,null);




               final EditText edit_user_name = (EditText)updateView.findViewById(R.id.update_name);
               final EditText edit_user_age = (EditText)updateView.findViewById(R.id.update_age);


                Button btn_update = (Button)updateView.findViewById(R.id.update);

                edit_user_name.setText(text_update_name.getText().toString());
                edit_user_age.setText(text_update_age.getText().toString());

                updateBuilder.setView(updateView);
                final AlertDialog updatedialog = updateBuilder.create();
                updatedialog.show();



                btn_update.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        //update here name and age

                        text_update_name.setText(edit_user_name.getText().toString());
                        text_update_age.setText(edit_user_age.getText().toString());


                        Toast.makeText(getActivity(),"Update Successfully",Toast.LENGTH_LONG).show();

                        updatedialog.dismiss();
                    }
                });



            }
        });

        sossetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog.Builder  sosBuilder = new AlertDialog.Builder(view.getContext());

                 View sosView = getActivity().getLayoutInflater().inflate(R.layout.dialog_sos_setting,null);
                final EditText edt_sos_user_name = (EditText)sosView.findViewById(R.id.sos_user_name);
                final EditText edt_sos_phone = (EditText)sosView.findViewById(R.id.sos_phone_number);
                Button btn_ok = (Button)sosView.findViewById(R.id.sos_ok);


                sosBuilder.setView(sosView);
                final AlertDialog sosdialog = sosBuilder.create();
                sosdialog.show();

                btn_ok.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        //code here to save details to database;


                        boolean isInserted =   mydbsos.insertData(edt_sos_user_name.getText().toString(),edt_sos_phone.getText().toString());

                        if(isInserted = true)
                            Toast.makeText(getActivity(),"SOS Setting  Successfully",Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(getActivity(),"Details Not Inserted !!!",Toast.LENGTH_LONG).show();


                      //  Toast.makeText(getActivity(),"phone user",Toast.LENGTH_LONG).show();

                        sosdialog.dismiss();
                    }
                });


            }
        });

        addmedicine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                AlertDialog.Builder  mBuilder = new AlertDialog.Builder(view.getContext());

                final View mView = getActivity().getLayoutInflater().inflate(R.layout.diaolog_add_medicine,null);

                 final EditText edit_name = (EditText)mView.findViewById(R.id.medicine_name);
                final EditText edit_frequency = (EditText)mView.findViewById(R.id.dose_frequency);
                final  EditText edit_quantity = (EditText)mView.findViewById(R.id.quantity);
               final EditText edit_per_day = (EditText)mView.findViewById(R.id.dose_per_day);
               final EditText edit_purchased = (EditText)mView.findViewById(R.id.purchase_medicine);


                Button save = (Button)mView.findViewById(R.id.save);

                mBuilder.setView(mView);
                final AlertDialog dialog = mBuilder.create();
                dialog.show();

                save.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        AlertDialog.Builder popup = new AlertDialog.Builder(view.getContext());
                        popup.setTitle("Check Exipiry Date !!!");
                        popup.setMessage("Do you want to Check Exipiry date before Saving. ?");

                        popup.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                                //nothing
                            }
                        });

                        popup.setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                                //code here to save details database
                             //   String name = edit_name.getText().toString();

                                boolean isInserted =   mydb.insertData(edit_name.getText().toString(),edit_frequency.getText().toString(),
                                          edit_quantity.getText().toString(),edit_per_day.getText().toString(),edit_purchased.getText().toString());

                                if(isInserted = true)
                                    Toast.makeText(getActivity(),"Details Inserted Successfully",Toast.LENGTH_LONG).show();
                                else
                                    Toast.makeText(getActivity(),"Details Not Inserted !!!",Toast.LENGTH_LONG).show();

                              //  DataBaseHelper db = DataBaseHelper();

                                 // Toast.makeText(getActivity(),""+name,Toast.LENGTH_LONG).show();
                                dialogInterface.cancel();
                                dialog.dismiss();
                            }
                        });

                        AlertDialog innerdialog =  popup.create();
                        innerdialog.show();

                       // Toast.makeText(getActivity(),"hhiiii",Toast.LENGTH_LONG).show();
                       // dialog.dismiss();
                    }
                });

              //  mBuilder.setView(mView);
               // AlertDialog dialog = mBuilder.create();
                //dialog.show();

            }
        });


        return  view;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_sos2, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.sos:
               // System.exit(1);

                Intent callIntent = new Intent(Intent.ACTION_CALL);
                callIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                callIntent.setData(Uri.parse("tel:" + phone));
                startActivity(callIntent);

              //  Toast.makeText(getActivity(),"heeee",Toast.LENGTH_LONG).show();
                return  true;

        }
        return true;

    }


}
