package org.example.micurso.app;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;


public class MenuCurso2 extends ActionBarActivity {


    Session session=new Session();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_curso2);

        ImageView imageView = (ImageView) findViewById(R.id.imageView);

          //Loading image from below url into imageView



    }

    public void Programa(View v){
        String url="http://pgbcursos.1apps.com/programa.html";

        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse(url));
        startActivity(i);
    }

    public void Documentos(View v){
        //String url="http://pgbcursos.1apps.com/documentos2.html";

        String url="https://drive.google.com/folderview?id=0B9nFwumYtUw9ZWpDUkQ1SWJ0Z2s&usp=sharing";

        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse(url));
        startActivity(i);
    }

    public void Tareas(View v){

        //String url="http://pgbcursos.1apps.com/programa.html";

        //Intent i = new Intent(Intent.ACTION_VIEW);
        //i.setData(Uri.parse(url));
        //startActivity(i);
        Intent i=new Intent(MenuCurso2.this,SendMail.class);
        startActivity(i);

    }

    public void GoogleDrive(View v){
        //String url="http://pgbcursos.1apps.com/documentos2.html"
        String url="https://drive.google.com/?tab=mo&authuser=0";
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse(url));
        startActivity(i);
    }


    public void Calificaciones(View v){
        String url="http://pgbcursos.1apps.com/programa.html";

        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse(url));
        startActivity(i);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_curso2, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
