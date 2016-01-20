package org.example.micurso.app;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import org.apache.http.protocol.HTTP;


public class SendMail extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_mail);

        Account[] accounts = AccountManager.get(SendMail.this).getAccounts();

        Toast.makeText(SendMail.this, accounts[0].toString(), Toast.LENGTH_SHORT).show();
        Intent emailIntent = new Intent(Intent.ACTION_SEND);
        // The intent does not have a URI, so declare the "text/plain" MIME type
        emailIntent.setData(Uri.parse("mailto:"));
        emailIntent.setType(HTTP.PLAIN_TEXT_TYPE);
        emailIntent.putExtra(Intent.EXTRA_EMAIL, new String[] {"pgbquimica.unachi@gmail.com"}); // recipients
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "TAREA");
        //emailIntent.putExtra(Intent.EXTRA_TEXT, "Escribir mensaje aqui");
        emailIntent.putExtra(Intent.EXTRA_TEXT, "TAREA");

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
