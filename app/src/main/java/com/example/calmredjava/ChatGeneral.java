package com.example.calmredjava;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;

public class ChatGeneral extends AppCompatActivity {
    static final int REQUEST_IMAGE_CAPTURE = 1;
    static final int GALLERY_INTENT = 2;
    private StorageReference mStorage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_chat_general);
        mStorage = FirebaseStorage.getInstance().getReference();
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    public void cargar_imagen(View v){
        Permisos permiso = new  Permisos(getApplicationContext());
        if(permiso.checkPermissionREAD_EXTERNAL_STORAGE(this)){
            Intent openPictureIntent = new Intent(Intent.ACTION_PICK);
            openPictureIntent.setType("image/*");
            startActivityForResult(openPictureIntent, GALLERY_INTENT);
        }
    }

    public void mostrar_imagen(View v){
        //Recuperar la uri desde la base de datos
        ImageView imFoto = (ImageView) findViewById(R.id.ivFoto);
        Glide.with(this)
            .load("https://firebasestorage.googleapis.com/v0/b/javacalmred.appspot.com/o/Descargas%2Fperfil1.jpg?alt=media&token=61b77ce9-c294-4e9d-9dfd-bec6e8f32785")
            .fitCenter()
            .centerCrop()
            .into(imFoto);
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
        }else if(requestCode == GALLERY_INTENT && resultCode == RESULT_OK){
            Uri uri = data.getData();
            StorageReference filePath = mStorage.child("fotos").child(uri.getLastPathSegment());
            filePath.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    Toast.makeText(getApplicationContext(), "Exito al hacer comentario con foto", Toast.LENGTH_SHORT).show();

                    filePath.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                        @Override
                        public void onSuccess(Uri uri) {
                            // Tomar la uri y guardarla en la base de datos
                            ImageView imFoto = (ImageView) findViewById(R.id.ivFoto);
                            Glide.with(ChatGeneral.this)
                                    .load(uri)
                                    .fitCenter()
                                    .centerCrop()
                                    .into(imFoto);
                        }
                    });
                }
            });
        }
    }

    public void irPerfilUsuario1(View v){
        Intent i = new Intent(this,PerfilUsuario1.class);
        startActivity(i);}

    public void irPerfilExter2(View v){
        Intent i = new Intent(this,PerfilExter2.class);
        startActivity(i);}
}