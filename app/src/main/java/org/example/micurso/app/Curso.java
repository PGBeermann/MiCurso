package org.example.micurso.app;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;


public class Curso extends ActionBarActivity {
    JSONObject jsonObject;
    HttpConn ht;
    TextView txt1;
    TextView txt2;
    TextView txt3;
    TextView txt4;
    Session session=new Session();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_curso);
        txt1=(TextView) findViewById(R.id.txt1);
        txt2=(TextView) findViewById(R.id.txt2);
        txt3=(TextView) findViewById(R.id.txt3);
        txt4=(TextView) findViewById(R.id.txt4);

        ht=new HttpConn(Curso.this);
        String url = "http://pgbcursos.1apps.com/curso.txt";
        ht.Open(url);
        ht.setCustomObjectListener(new MyCustomObjectListener() {
            @Override
            public void onObjectReady(String result) {

                try {
                    jsonObject = new JSONObject(result);
                    String abrev=jsonObject.getString("ABREV");
                    String denom=jsonObject.getString("DENOM");
                    String escuela=jsonObject.getString("ESCUELA");
                    String url=jsonObject.getString("URL");

                    session.abrev=abrev;
                    session.denom=denom;
                    session.escuela=escuela;
                    session.url=url;

                    txt1.setText(denom);
                    txt2.setText(abrev);
                    txt3.setText(escuela);
                    txt4.setText(url);

                }catch(JSONException e){

                }




            }
        });


    }


      public void Iniciar(View v){
        Toast.makeText(getBaseContext(),"Iniciando Sesion",Toast.LENGTH_LONG).show();
          Intent i=new Intent(Curso.this,MenuCurso2.class);
          startActivity(i);
       }




}
