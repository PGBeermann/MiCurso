package org.example.micurso.app;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;


public class Curso extends Activity {
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


    }

    @Override
    protected void onResume() {
        super.onResume();

        if(session.activa) {
            txt1.setText(session.denom);
            txt2.setText(session.abrev);
            txt3.setText(session.escuela);
            txt4.setText(session.url);
        }
        else{
            txt1.setText("SESION INACTIVA");
            txt2.setText("Escoja un curso");
            txt3.setText("");
            txt4.setText("");
        }

    }

    void LeerDatos(String url){
        ht=new HttpConn(Curso.this);
        url += "/curso.txt";
        //Toast.makeText(getBaseContext(),"Leyendo:"+url,Toast.LENGTH_SHORT).show();
        if(ht.isConnected()) {

            Toast.makeText(getBaseContext(),"Conectado",Toast.LENGTH_LONG).show();
            // Intent i=new Intent(Curso.this,MisCursos.class);
            // startActivityForResult(i,1);





            ht.Open(url);
            ht.setCustomObjectListener(new MyCustomObjectListener() {
                @Override
                public void onObjectReady(String result) {

                    try {
                        jsonObject = new JSONObject(result);
                        String abrev = jsonObject.getString("ABREV");
                        String denom = jsonObject.getString("DENOM");
                        String escuela = jsonObject.getString("ESCUELA");
                        String url = jsonObject.getString("URL");
                        String cronograma = jsonObject.getString("CRONOGRAMA");
                        String googledrive = jsonObject.getString("GOOGLEDRIVE");
                        session.abrev = abrev;
                        session.denom = denom;
                        session.escuela = escuela;
                        session.url = url;
                        session.cronograma=cronograma;
                        session.googledrive=googledrive;
                        session.activa=true;
                        txt1.setText(denom);
                        txt2.setText(abrev);
                        txt3.setText(escuela);
                        txt4.setText(url);


                    } catch (JSONException e) {
                        session.activa=false;
                        txt1.setText("SESION INACTIVA");
                        txt2.setText("Escoja un curso válido");
                        txt3.setText("");
                        txt4.setText("");
                    }


                }
            });
        }else{
            Toast.makeText(getBaseContext(),"No esta conectado a internet...",Toast.LENGTH_LONG).show();
            session.activa=false;
            //finish();
        }
    }






    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == 1) {
            // Make sure the request was successful
            if (resultCode == RESULT_OK) {

                String website=data.getStringExtra("WEBSITE");

                LeerDatos(website);


                // Do something with the contact here (bigger example below)
            }
        }

        if (requestCode == 2) {

            if (resultCode == RESULT_OK) {

                int id=data.getIntExtra("ID",0);
                session.id=id;
                String website=data.getStringExtra("WEBSITE");
                String abrev=data.getStringExtra("ABREV");
                boolean op=false;


                AlertDialog alertDialog = new AlertDialog.Builder(Curso.this).create();
                alertDialog.setTitle("Cuidado");
                alertDialog.setMessage("Va a eliminar un curso");
                alertDialog.setIcon(R.drawable.ic_launcher);
                alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "OK",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {

                                UsuariosSQLiteHelper usH = new UsuariosSQLiteHelper(Curso.this, "DBUsuarios", null, 3);
                                usH.eliminaCurso(session.id);
                                dialog.dismiss();
                            }
                        });
                alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "Cancelar",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                               dialog.cancel();
                            }
                        });

                alertDialog.show();


                //UsuariosSQLiteHelper usH = new UsuariosSQLiteHelper(this, "DBUsuarios", null, 3);
                //usH.eliminaCurso(id);

            }
        }
        if (requestCode == 3) {

            if (resultCode == RESULT_OK) {

                String website=data.getStringExtra("WEBSITE");
                String abrev=data.getStringExtra("ABREV");

                LeerDatos(website);

            }
        }
    }

     @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu1, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // action with ID action_refresh was selected
            case R.id.action_escoger:
                Intent i1=new Intent(Curso.this,MisCursos.class);
                startActivityForResult(i1,1);
                break;
            // action with ID action_settings was selected
            case R.id.action_iniciar:
                if(session.activa) {
                    Toast.makeText(getBaseContext(), "Iniciando Sesion", Toast.LENGTH_LONG).show();
                    Intent i = new Intent(Curso.this, MenuCurso2.class);
                    startActivity(i);
                }else{
                    Toast.makeText(getBaseContext(), "No has iniciado sesion", Toast.LENGTH_LONG).show();
                }
                break;
            case R.id.action_eliminar:

                Intent i2=new Intent(Curso.this,MisCursos.class);
                startActivityForResult(i2,2);

                break;
            case R.id.action_nuevo:

                Toast.makeText(getBaseContext(),"Nuevo curso",Toast.LENGTH_LONG).show();
                Intent nc=new Intent(Curso.this,NuevoCurso.class);
                startActivityForResult(nc, 3);
                break;


            default:
                break;
        }
        return true;
    }







}
