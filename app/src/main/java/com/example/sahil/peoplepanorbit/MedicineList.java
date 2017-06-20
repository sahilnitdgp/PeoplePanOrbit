package com.example.sahil.peoplepanorbit;


import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class MedicineList extends Fragment {

    String phone = "8016721609";

    DataBaseHelper mydb;

    private ListView listView;

    DataBaseHelperSos mydbsos;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view =  inflater.inflate(R.layout.fragment_medicine_list, container, false);

        listView = (ListView)view.findViewById(R.id.listview);

        mydb = new DataBaseHelper(getActivity());

        mydbsos  = new DataBaseHelperSos(getActivity());

        //Retrieve phone number
        Cursor data1 = mydbsos. getAllDataSos();

        if(data1.moveToFirst()){
            do{

                phone = data1.getString(0);
            }while (data1.moveToNext());
        }


        Cursor data = mydb.getAllData();

        ArrayList<String> listData = new ArrayList<>();

        while (data.moveToNext()){
            listData.add(data.getString(0));
        }

        ListAdapter listAdapter = new ArrayAdapter<>(getActivity(),android.R.layout.simple_expandable_list_item_1,listData);

        listView.setAdapter(listAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
               // Toast.makeText(getActivity(),"click",Toast.LENGTH_LONG).show();

                //update medicine


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

                        Toast.makeText(getActivity(),"Update Succesfully",Toast.LENGTH_LONG).show();
                    }
                });

                //  mBuilder.setView(mView);
                // AlertDialog dialog = mBuilder.create();
                //dialog.show();

            }
        });


        return view;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_sos, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.sos:
                // do s.th.

                Intent callIntent = new Intent(Intent.ACTION_CALL);
                callIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                callIntent.setData(Uri.parse("tel:" + phone));
                startActivity(callIntent);

            //   Toast.makeText(getActivity(),"heeeee", Toast.LENGTH_LONG).show();
                return true;
            case R.id.search_list:

                Toast.makeText(getActivity(),"search", Toast.LENGTH_LONG).show();

                SearchView searchView = (SearchView)item.getActionView();

                searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                    @Override
                    public boolean onQueryTextSubmit(String query) {
                        return false;
                    }

                    @Override
                    public boolean onQueryTextChange(String newText) {


                        return false;
                    }
                });
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }



    }




