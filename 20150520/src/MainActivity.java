package it.bogliaccino.hellopininfarina;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class MainActivity extends ActionBarActivity {

    //qui dichiaro gli attributi, 2 di tipo edittext e 1 button
    EditText username, password;
    Button btnLogin;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        username = (EditText) findViewById(R.id.etUsername);
        password = (EditText) findViewById(R.id.etPassword);
        btnLogin = (Button) findViewById(R.id.btnLogin);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Toast.makeText(this,"Hai cliccato settaggi!", Toast.LENGTH_SHORT).show();
            return true;
        }

        if (id == R.id.action_settings2) {
            Toast.makeText(this,"Hai cliccato prova Enaip!", Toast.LENGTH_SHORT).show();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    //obbligatoriamente: metodo pubblico, non deve avere tipo di ritorno,
    // ammette un solo argomento che è la view su cui hai fatto clic
    public void faiLogin(View v){

        if(
                username.getText().toString().equals("mauro") &&
                        password.getText().toString().equals("12345")
                )
        {

            Toast t = Toast.makeText(this, "Benvenuto  " +
                    username.getText().toString(), Toast.LENGTH_SHORT);
            t.setGravity(Gravity.CENTER, 0, 0);
            t.show();

            Intent i = new Intent(this, AreaRiservata.class);
            startActivity(i);
        }

            else
        Toast.makeText(this,"Non hai inserito correttamente username e password!", Toast.LENGTH_SHORT).show();
    }
}
