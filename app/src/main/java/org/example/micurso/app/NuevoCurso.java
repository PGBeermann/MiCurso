package org.example.micurso.app;

import android.app.Activity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;


public class NuevoCurso extends Activity {
    EditText txt1;
    EditText txt2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nuevo_curso);
        txt1=(EditText)findViewById(R.id.webtxt);
        txt2=(EditText)findViewById(R.id.abrevtxt);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.nuevo_curso, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // action with ID action_refresh was selected
            case R.id.action_regresar:
                Intent i1=new Intent(NuevoCurso.this,Curso.class);
                startActivity(i1);
                break;
            // action with ID action_settings was selected
           default:
                break;
        }
        return true;
    }


    public void Ejecutar(View v){

        //Abrimos la base de datos 'DBUsuarios' en modo escritura
        UsuariosSQLiteHelper usdbh =
                new UsuariosSQLiteHelper(this, "DBUsuarios", null, 3);

        SQLiteDatabase db = usdbh.getWritableDatabase();

        //Si hemos abierto correctamente la base de datos
        if(db != null)
        {

            String website  = txt1.getText().toString();
            String abrev=txt2.getText().toString();

            //Insertamos los datos en la tabla Usuarios
            db.execSQL("INSERT INTO Usuarios (website, abrev) " + "VALUES ('" + website + "', '" + abrev +"')");

            Toast.makeText(getBaseContext(), "El curso ha sido ingresado..", Toast.LENGTH_SHORT).show();
            //Cerramos la base de datos
            db.close();

        }

        Intent resultIntent=new Intent();
        String website=txt1.getText().toString();
        String abrev=txt2.getText().toString();

        resultIntent.putExtra("WEBSITE",website);
        resultIntent.putExtra("ABREV",abrev);
        setResult(Activity.RESULT_OK, resultIntent);
        finish();


    }

}
