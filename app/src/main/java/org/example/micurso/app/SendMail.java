package org.example.micurso.app;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.widget.Toast;


public class SendMail extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_mail);

        String body="NOMBRE:<INPUT TYPE='TEXT' VALUE=''><br>";
        body+="CEDULA:000-0000-00000";


        Account[] accounts = AccountManager.get(SendMail.this).getAccounts();


        Intent emailIntent = new Intent(Intent.ACTION_SEND);
        emailIntent.setData(Uri.parse("mailto:"));
        emailIntent.setType("text/html");
        emailIntent.putExtra(Intent.EXTRA_EMAIL, new String[] {"pgbcursos@gmail.com"}); // recipients
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "QM230-TAREAXX");
        emailIntent.putExtra(Intent.EXTRA_TEXT, body);
        emailIntent.putExtra(android.content.Intent.EXTRA_TEXT, Html.fromHtml(body));


        try {
            startActivity(Intent.createChooser(emailIntent, "Enviar email..."));
            finish();
            Log.i("Finaliza el envio del email...", "");
        }
        catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(SendMail.this, "No existe aplicación para enviar el email...", Toast.LENGTH_SHORT).show();
        }







    }




}
