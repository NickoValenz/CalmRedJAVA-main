package com.example.calmredjava;

import static com.example.calmredjava.MainActivity.REQUEST_IMAGE_CAPTURE;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.gms.auth.api.signin.internal.Storage;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;

public class PerfilUsuario1 extends AppCompatActivity {
    static final int REQUEST_IMAGE_CAPTURE = 1;
    private StorageReference mStorage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_perfil_usuario1);
        mStorage = FirebaseStorage.getInstance().getReference();
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
    public void irDiario(View v){
        Intent i = new Intent(this,Diario.class);
        startActivity(i);
    }
    public void irGrupos(View v){
        Intent i = new Intent(this,Grupos.class);
        startActivity(i);}

    public void irPerfilUsuario1(View v){
        Intent i = new Intent(this,PerfilUsuario1.class);
        startActivity(i);}

    public void irPerfilExter2(View v){
        Intent i = new Intent(this,PerfilExter2.class);
        startActivity(i);}

    public void irRecordatorios(View v){
        Intent i = new Intent(this,RecordatoriosConfig.class);
        startActivity(i);}

    public void irInsignias(View v){
        Intent i = new Intent(this,Insignias.class);
        startActivity(i);}

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
            StorageReference fotoRef = mStorage.child("Descargas/perfil1.jpg");
            // Generar un arreglo de bytes
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            // Convertit el bitmap al formato y calidad que queramos
            imageBitmap.compress(Bitmap.CompressFormat.JPEG, 100,baos);
            byte[] data1 = baos.toByteArray();
            // Subir a Firebase la foto
            UploadTask uploadTask = fotoRef.putBytes(data1);
        }
    }
}