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
    public static final String MIME_TYPE_EMAIL = "message/rfc822";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_mail);

        String body="NOMBRE:<INPUT TYPE='TEXT' VALUE=''><br>";
        body+="CEDULA:000-0000-00000";


        Account[] accounts = AccountManager.get(SendMail.this).getAccounts();


        Intent emailIntent = new Intent(Intent.ACTION_SEND);
        // The intent does not have a URI, so declare the "text/plain" MIME type
        emailIntent.setData(Uri.parse("mailto:"));
        //emailIntent.setType(HTTP.PLAIN_TEXT_TYPE);
        emailIntent.setType("text/html");
        emailIntent.putExtra(Intent.EXTRA_EMAIL, new String[] {"pgbcursos@gmail.com"}); // recipients
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "QM230-TAREAXX");
        //emailIntent.putExtra(Intent.EXTRA_TEXT, "Escribir mensaje aqui");

        emailIntent.putExtra(Intent.EXTRA_TEXT, body);
        emailIntent.putExtra(android.content.Intent.EXTRA_TEXT, Html.fromHtml(body));


        try {
            startActivity(Intent.createChooser(emailIntent, "Send mail..."));
            finish();
            Log.i("Finished sending email...", "");
        }
        catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(SendMail.this, "There is no email client installed.", Toast.LENGTH_SHORT).show();
        }







    }




}
