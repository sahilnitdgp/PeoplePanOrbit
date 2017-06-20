package com.example.sahil.peoplepanorbit;


import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class Medicine extends Fragment {

    String phone = "8016721609";

    DataBaseHelper mydb;

    private ListView listView;

    DataBaseHelperSos mydbsos;
   public static int set = 0;

    //String name = "Dharmvir";
    //String age = "24";



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_medicine, container, false);

        Button yes, no;


              if(set == 0) {

           AlertDialog.Builder mBuilder = new AlertDialog.Builder(view.getContext());

            final View mView = getActivity().getLayoutInflater().inflate(R.layout.new_user, null);

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

                    View updateView = getActivity().getLayoutInflater().inflate(R.layout.update_name_age, null);


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

                            View sosView = getActivity().getLayoutInflater().inflate(R.layout.dialog_sos_setting, null);
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
                                        Toast.makeText(getActivity(), "SOS Setting  Successfully", Toast.LENGTH_LONG).show();
                                    else
                                        Toast.makeText(getActivity(), "Details Not Inserted !!!", Toast.LENGTH_LONG).show();


                                    //  Toast.makeText(getActivity(),"phone user",Toast.LENGTH_LONG).show();

                                    sosdialog.dismiss();
                                }
                            });

                            Toast.makeText(getActivity(), "User Successfully", Toast.LENGTH_LONG).show();

                            updatedialog.dismiss();
                        }
                    });


                    dialog.dismiss();


                }
            });

            no = (Button) mView.findViewById(R.id.user_no);

            no.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    dialog.dismiss();

                    //Toast.makeText(getActivity(),"nooooo",Toast.LENGTH_LONG).show();

                }
            });
                  set=set+1;
              }





            listView = (ListView) view.findViewById(R.id.listview2);

            mydb = new DataBaseHelper(getActivity());

            mydbsos = new DataBaseHelperSos(getActivity());

            //Retrieve phone number
            Cursor data1 = mydbsos.getAllDataSos();

            if (data1.moveToFirst()) {
                do {

                    phone = data1.getString(0);
                } while (data1.moveToNext());
            }


            Cursor data = mydb.getAllData2();

            ArrayList<String> listData = new ArrayList<>();

            while (data.moveToNext()) {
                listData.add(data.getString(0));
                listData.add(data.getString(2));
                // listData.add(data.getString(3));
            }

            ListAdapter listAdapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, listData);

            listView.setAdapter(listAdapter);


        //notification

        android.support.v7.app.NotificationCompat.Builder notificationBuilder = (android.support.v7.app.NotificationCompat.Builder) new android.support.v7.app.NotificationCompat.Builder(getActivity())
                .setSmallIcon(android.R.drawable.stat_notify_error)
                .setLargeIcon(BitmapFactory.decodeResource(getResources(),R.mipmap.ic_launcher))
                .setContentTitle("Notifcation About Medicine")
                .setContentText("You have take medicine today");
        notificationBuilder.setDefaults(
                android.app.Notification.DEFAULT_SOUND | android.app.Notification.DEFAULT_LIGHTS | android.app.Notification.DEFAULT_VIBRATE);
        NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(getActivity());
        notificationManagerCompat.notify(1,notificationBuilder.build());





            return view;
        }


    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_sos2, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // handle item selection
        switch (item.getItemId()) {
            case R.id.sos:
                // do s.th.
                Intent callIntent = new Intent(Intent.ACTION_CALL);
                callIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                callIntent.setData(Uri.parse("tel:" + phone));
                startActivity(callIntent);

                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


}
