package org.example.micurso.app;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;


public class MenuCurso2 extends Activity {


    Session session=new Session();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_curso2);

        ImageView imageView = (ImageView) findViewById(R.id.imageView);

        Picasso.with(MenuCurso2.this).load(R.drawable.menu).into(imageView);
        setTitle("Menú");
    }

    public void Programa(View v){
        //Ver el temario del curso que se encuentra en programa.html
        //String url="http://pgbcursos.1apps.com/programa.html";
        String url=session.url+ "/"+ session.cronograma;
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse(url));
        startActivity(i);
    }

    public void Documentos(View v){
        //Ir a la carpeta compartida de GoogleDrive del Profesor

        String url=session.googledrive;

        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse(url));
        startActivity(i);
    }

    public void Tareas(View v){

        //Envia las tareas a traves del email mediante un attachment
        //La tarea debera estar alojada en el GoogleDrive del estudiante
        Intent i=new Intent(MenuCurso2.this,SendMail.class);
        startActivity(i);

    }

    public void GoogleDrive(View v){
        //Abre el GoogleDrive del usuario. cada usuario sólo puede ver su Repositorio de GoogleDrive personal
        String url="https://drive.google.com/?tab=mo&authuser=0";
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse(url));
        startActivity(i);
    }


    public void Calificaciones(View v){
        //Ver las calificaciones del Estudiante
        //Cada estudiante sólo puede ver sus calificaciones

        String url="http://pgbcursos.1apps.com/Calificaciones.html";

        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse(url));
        startActivity(i);
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

                Intent i1=new Intent(MenuCurso2.this,Curso.class);
                startActivity(i1);
                break;
            // action with ID action_settings was selected
            default:
                break;
        }
        return true;
    }

}
