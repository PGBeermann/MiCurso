package org.example.micurso.app;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.ImageView;


public class MenuCurso2 extends ActionBarActivity {


    Session session=new Session();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_curso2);

        ImageView imageView = (ImageView) findViewById(R.id.imageView);


    }

    public void Programa(View v){
        //Ver el temario del curso que se encuentra en programa.html
        String url="http://pgbcursos.1apps.com/programa.html";

        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse(url));
        startActivity(i);
    }

    public void Documentos(View v){
        //Ir a la carpeta compartida de GoogleDrive del Profesor

        String url="https://drive.google.com/folderview?id=0B9nFwumYtUw9ZWpDUkQ1SWJ0Z2s&usp=sharing";

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

        String url="http://pgbcursos.1apps.com/programa.html";

        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse(url));
        startActivity(i);
    }



}
