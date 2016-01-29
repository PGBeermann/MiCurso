package org.example.micurso.app;


import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;


public class MisCursos extends ListActivity {
    String[] est;

    ArrayList<String> lst=new ArrayList<String>();
    ArrayList<myCurso>  mc=new ArrayList<myCurso>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        lst.clear();


        String[] est=new String[10];

        for (int i = 0; i < 10; i++) {
            est[i] = "QM230" ;

        }

        SQLiteDatabase mydatabase = openOrCreateDatabase("DBUsuarios",MODE_PRIVATE,null);
        Cursor resultSet = mydatabase.rawQuery("Select * from Usuarios",null);
        resultSet.moveToFirst();
        while(resultSet.isAfterLast() == false){
            //array_list.add(res.getString(res.getColumnIndex(CONTACTS_COLUMN_NAME)));
            mc.add(new myCurso(resultSet.getInt(0),resultSet.getString(1),resultSet.getString(2)));
            lst.add(resultSet.getString(2));
            resultSet.moveToNext();
        }



        setListAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, lst));

    }

    public void onListItemClick(ListView parent, View v, int position, long id)
    {

        //String item = (String) getListAdapter().getItem(position);
        //Toast.makeText(this, item + " seleccionado", Toast.LENGTH_LONG).show();
        Intent resultIntent=new Intent();
        int ID=mc.get(position).id;
        String website=mc.get(position).website;
        String abrev=mc.get(position).abrev;
        resultIntent.putExtra("ID",ID);
        resultIntent.putExtra("USER", position);
        resultIntent.putExtra("WEBSITE",website);
        resultIntent.putExtra("ABREV",abrev);
        //Toast.makeText(getBaseContext(),"ID="+ ID, Toast.LENGTH_SHORT).show();
        setResult(Activity.RESULT_OK, resultIntent);
        finish();

    }




}