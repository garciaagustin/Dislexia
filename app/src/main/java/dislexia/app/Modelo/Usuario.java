package dislexia.app.Modelo;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.LinkedList;
import java.util.UUID;
import java.util.concurrent.Executor;

import dislexia.app.Login;
import dislexia.app.Registrar;

public class Usuario {

    String idPersona;
    String password;
    String email;
    String userId;

    public String getIdPersona() {
        return idPersona;
    }

    public void setIdPersona(String idPersona) {
        this.idPersona = idPersona;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }


    public void registrarUsuario(final String email, final String password, final Persona persona, FirebaseAuth mAuth, final DatabaseReference database, final Context context) {


        //Chequea si son correctos email y contrasena ingresados
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener((Activity) context, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Usuario usuario = new Usuario();
                            usuario.setUserId(UUID.randomUUID().toString());
                            usuario.setEmail(email);
                            usuario.setIdPersona(persona.getIdPersona());
                            usuario.setPassword(password);
                            database.child("usuario").child(usuario.getUserId()).setValue(usuario);
                            database.child("Persona").child(persona.getIdPersona()).setValue(persona);
                            // Sign in success, update UI with the signed-in user's information
                            Toast.makeText(context, "El usuario ha sido registrado correctamente", Toast.LENGTH_SHORT).show();


                        } else {
                            // Guarda el error obtenido para despues chequearlo
                            String errorCode = ((FirebaseAuthException) task.getException()).getErrorCode();
                            Log.e("", "" + errorCode);
                            switch (errorCode) {

                                case "ERROR_EMAIL_ALREADY_IN_USE": // El usuario ingreso contrasena incorrecta

                                    Toast.makeText(context, "Ya existe un usuario registrado con ese email", Toast.LENGTH_LONG).show();
                                    break;


                                case "ERROR_INVALID_EMAIL":
                                    Toast.makeText(context, "No es un email valido", Toast.LENGTH_LONG).show();
                                    break;

                            }
                        }

                    }
                });
    }



    public void readData(final FirebaseCallBackidPersona firebaseCallBack, final String email, DatabaseReference databaseReference, final LinkedList<String> resultado){

        databaseReference.child("usuario").orderByChild("email").equalTo(email).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {

                    String idPersona = dataSnapshot1.child("idPersona").getValue().toString();


                    resultado.add(idPersona);

                }



                firebaseCallBack.onCallback(resultado);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public interface FirebaseCallBackidPersona{

        void onCallback(LinkedList<String> resultado);


    }

}













