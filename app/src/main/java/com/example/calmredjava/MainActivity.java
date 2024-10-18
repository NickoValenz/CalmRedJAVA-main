package com.example.calmredjava;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.firebase.Firebase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class MainActivity extends AppCompatActivity {
    static final int REQUEST_IMAGE_CAPTURE = 1;
    private StorageReference mStorage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        mStorage = FirebaseStorage.getInstance().getReference();
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
    public void login(View v){

        EditText campo1nombre = this.findViewById(R.id.nombre);
        String nombre = campo1nombre.getText().toString();


        EditText campo3contrasenia = this.findViewById(R.id.contrasenia);
        String contrasenia = campo3contrasenia.getText().toString();

        if(contrasenia.equals("admin") && nombre.equals("admin"))
        {

        Intent i = new Intent(this,FeedPrincipal.class);
        startActivity(i);
    }else{
            Toast.makeText(this, "Error de credenciales", Toast.LENGTH_SHORT).show();}
    }
    public void irRegistro(View v){
        Intent i = new Intent(this,LoginSesion.class);
        startActivity(i);
    }
    public void tomar_foto (View v){
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if(takePictureIntent.resolveActivity(getPackageManager())!=null){
            startActivityForResult(takePictureIntent,REQUEST_IMAGE_CAPTURE);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK){
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            ImageView imFoto = (ImageView) findViewById(R.id.ivFoto);
            imFoto.setImageBitmap(imageBitmap);
        }
    }
}