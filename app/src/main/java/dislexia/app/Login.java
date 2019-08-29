package dislexia.app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseAuthInvalidUserException;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.SignInMethodQueryResult;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import dislexia.app.Modelo.Usuario;

public class Login extends AppCompatActivity {

    private EditText password, emailtxt;
    private Button ingresarBoton;
    private Button registrarBoton;
    private FirebaseAuth mAuth;


    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        updateUI(currentUser);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);


        password = (EditText) findViewById(R.id.pass);
        emailtxt = (EditText) findViewById(R.id.emailTxt);
        ingresarBoton = (Button) findViewById(R.id.buttonIngresar);
        registrarBoton = (Button) findViewById(R.id.botonRegistarLogin);



        inicializarFirebase();

    }


    private void inicializarFirebase() {
        FirebaseApp.initializeApp(this);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();
        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();

    }

    private void updateUI(FirebaseUser user) {

        if (user != null) {

        } else {

        }
    }

    public void ingresar(View v) {


        String pass = password.getText().toString();
        String email = emailtxt.getText().toString();

        if(TextUtils.isEmpty(email)){
            emailtxt.setError("Campo obligatorio.");
            Toast.makeText(Login.this,"Ingrese email ",Toast.LENGTH_LONG).show();

        }
        else if(TextUtils.isEmpty(pass)){
            password.setError("");
            Toast.makeText(Login.this, "Ingrese contrasena", Toast.LENGTH_SHORT).show();

        }else{//El usuario completo los campos

            //Chequea si son correctos email y contrasena ingresados
            mAuth.signInWithEmailAndPassword(email, pass)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                // Sign in success, update UI with the signed-in user's information
                                Toast.makeText(Login.this, "Bienvenido", Toast.LENGTH_SHORT).show();
                                //Inicia ventana Actividades
                                Intent i = new Intent(Login.this,ActividadesPantalla.class);
                                startActivity(i);


                            } else {
                                // Guarda el error obtenido para despues chequearlo
                                String errorCode = ((FirebaseAuthException) task.getException()).getErrorCode();
                                Log.e("",""+errorCode);
                                switch(errorCode){

                                    case "ERROR_WRONG_PASSWORD": // El usuario ingreso contrasena incorrecta

                                        Toast.makeText(Login.this,"Contrasena incorrecta",Toast.LENGTH_LONG).show();
                                        break;

                                    case "ERROR_USER_NOT_FOUND": // El usuario no esta registrado.

                                        Toast.makeText(Login.this, "El usuario no esta registrado",Toast.LENGTH_LONG).show();
                                        break;

                                    case "ERROR_INVALID_EMAIL":
                                        Toast.makeText(Login.this,"No es un email valido",Toast.LENGTH_LONG).show();
                                        break;

                                }






                            }

                            // ...
                        }
                    });







        }




        }



    public void registrarLogin(View v) {

        Intent intent = new Intent(this, Registrar.class);
        startActivity(intent);

    }



}






