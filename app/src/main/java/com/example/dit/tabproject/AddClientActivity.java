package com.example.dit.tabproject;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class AddClientActivity extends AppCompatActivity {
    SQLiteDatabaseManager sqLiteDatabaseManager;
    EditText clientname , clientemail ;
    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_client);
        clientname = (EditText)findViewById(R.id.name);
        clientemail = (EditText)findViewById(R.id.email);
        textView = (TextView) findViewById(R.id.ecran);
        sqLiteDatabaseManager = new SQLiteDatabaseManager(this);
    }

    public void addClient(View view){
        String nameVal = clientname.getText().toString();
        String emailVal = clientemail.getText().toString();
        Client client = new Client(nameVal,emailVal);
        long id = sqLiteDatabaseManager.insertClient(client);
        if(id<0){
            Toast.makeText(this,"FAILED",Toast.LENGTH_LONG).show();
        }else {
            Toast.makeText(this,"SUCCECED",Toast.LENGTH_LONG).show();
        }
    }

    public void showClient(View view){
        List<Client> clients = sqLiteDatabaseManager.getAllClient();

        for (Client cli : clients){
            Log.i("Member name: ", "Id: " + cli.getId() + " ; Name: " + cli.getNom() + " ; Email: " + cli.getEmail() + "\n");
        }
           // textView.setText(log);
            //Toast.makeText(this,log,Toast.LENGTH_LONG).show();
        }
    }
