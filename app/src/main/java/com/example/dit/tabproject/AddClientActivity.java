package com.example.dit.tabproject;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.View.OnClickListener;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.List;

public class AddClientActivity extends AppCompatActivity {
    SQLiteDatabaseManager sqLiteDatabaseManager;
    EditText clientname, clientemail;
    public static final int IMAGE_GALLERY_REQUEST = 20;
    ImageView monImage;
    Uri imageUri;
    TextView textView;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_client);
        clientname = (EditText) findViewById(R.id.name);
        clientemail = (EditText) findViewById(R.id.email);
        monImage = (ImageView) findViewById(R.id.imageGallery);
        sqLiteDatabaseManager = new SQLiteDatabaseManager(this);


        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.

    }

    public void addClient(View view) {

        //for hiding clavier
        InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(clientname.getWindowToken(), 0);
        imm.hideSoftInputFromWindow(clientemail.getWindowToken(), 0);
        //recuperer les données de l'EditText
        String nameVal = clientname.getText().toString();
        String emailVal = clientemail.getText().toString();
        Client client = new Client(nameVal, emailVal);
        //insertion dans la base de données et se diriger vers la listView
        long id = sqLiteDatabaseManager.insertClient(client);
        if (id < 0 || nameVal.equals("") || emailVal.equals("")) {
            Toast.makeText(this, "FAILED: check name and email", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(this, "SUCCECED", Toast.LENGTH_LONG).show();
            clientemail.setText("");
            clientname.setText("");
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }
    }

    //afficher tous les clients dans le LOG
    public void showClient(View view) {
        List<Client> clients = sqLiteDatabaseManager.getAllClient();

        for (Client cli : clients) {
            Log.i("Member name: ", "Id: " + cli.getId() + " ; Name: " + cli.getNom() + " ; Email: " + cli.getEmail() + "\n");
        }
        // textView.setText(log);
        //Toast.makeText(this,log,Toast.LENGTH_LONG).show();
    }

    //afficher tous les noms des clients dans le LOG
    public void showNames(View view) {
        String[] nom = sqLiteDatabaseManager.getNames();

        for (String cliName : nom) {
            Log.i("Member name: ", cliName + "\n");
        }
        //Toast.makeText(this,log,Toast.LENGTH_LONG).show();
    }

    //supprimer tous les clients
    public void deleteAll(View view) {
        AlertDialog myQuittingDialogBox = new AlertDialog.Builder(this)
                .setTitle("Delete")
                .setMessage("Do you want to Delete")
                .setPositiveButton("Delete", new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int whichButton) {
                        sqLiteDatabaseManager.deleteAll();
                        Log.d("DELETE", "DELEEEEEEETEEEEED");
                        dialog.dismiss();
                    }
                })
                .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .create();
        myQuittingDialogBox.show();
    }


    //go to the gallery
    public void addImageFromGallery(View v) {
        // invoke the image gallery using an implict intent.
        Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);

        // where do we want to find the data?
        File pictureDirectory = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
        String pictureDirectoryPath = pictureDirectory.getPath();
        // finally, get a URI representation
        Uri data = Uri.parse(pictureDirectoryPath);

        // set the data and type.  Get all image types.
        photoPickerIntent.setDataAndType(data, "image/*");

        // we will invoke this activity, and get something back from it.
        startActivityForResult(photoPickerIntent, IMAGE_GALLERY_REQUEST);
    }
     @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
         super.onActivityResult(requestCode, resultCode, data);
         if (resultCode == RESULT_OK) {
             // if we are here, everything processed successfully.
             if (requestCode == IMAGE_GALLERY_REQUEST) {
                 imageUri = data.getData();
                 monImage.setImageURI(imageUri);

             }
         }


         /**
          * ATTENTION: This was auto-generated to implement the App Indexing API.
          * See https://g.co/AppIndexing/AndroidStudio for more information.
          */


     }
}

