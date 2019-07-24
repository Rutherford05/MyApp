package com.example.myapp;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;
import android.widget.Toolbar;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;

public class Connexion extends AppCompatActivity {
    private Button connect;
    private TextInputEditText email;
    private TextInputEditText password;
    String mail,mot_de_passe;
    GoogleSignInClient mGoogleSignInClient;
    private FirebaseAuth firebaseAuth;
    private CallbackManager mCallbackManager;
    private static final String TAG="FACELOG";
    SignInButton signInButton;
    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR2)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connexion);
        // Initialize Facebook Login button
        mCallbackManager = CallbackManager.Factory.create();
        LoginButton loginButton =(LoginButton) findViewById(R.id.login_button);
        loginButton.setPermissions("email", "public_profile");
        loginButton.registerCallback(mCallbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                Log.d(TAG, "facebook:onSuccess:" + loginResult);
                handleFacebookAccessToken(loginResult.getAccessToken());
            }

            @Override
            public void onCancel() {
                Log.d(TAG, "facebook:onCancel");
                // ...
            }

            @Override
            public void onError(FacebookException error) {
                Log.d(TAG, "facebook:onError", error);
                // ...
            }
        });

        firebaseAuth=FirebaseAuth.getInstance();
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();
        email=(TextInputEditText) findViewById(R.id.valid_email);
        password=(TextInputEditText) findViewById(R.id.valid_password);
        connect=(Button) findViewById(R.id.button2);
        mGoogleSignInClient = GoogleSignIn.getClient(this,gso);
         signInButton=(SignInButton) findViewById(R.id.sign_in_button);
         signInButton.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 Intent signIntent= mGoogleSignInClient.getSignInIntent();
                 startActivityForResult(signIntent,101);
             }
         });

        connect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mail=email.getText().toString();
                mot_de_passe=password.getText().toString();
                if(TextUtils.isEmpty(mail)){
                    Toast.makeText(Connexion.this, "Veuillez entrer une adresse", Toast.LENGTH_SHORT).show();
                    return;
                }
                 if(!email.getText().toString().trim().matches(emailPattern)){
                    Toast.makeText(getApplicationContext(),"l'adresse saisie n'est pas valide",Toast.LENGTH_LONG).show();
                }
                 if(TextUtils.isEmpty(mot_de_passe)){
                    Toast.makeText(Connexion.this,"Veuillez Entrer un mot de passe",Toast.LENGTH_SHORT).show();
                    return;
                }
                 firebaseAuth.signInWithEmailAndPassword(mail,mot_de_passe).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                     @Override
                     public void onComplete(@NonNull Task<AuthResult> task) {
                         if(task.isSuccessful()){
                             Toast.makeText(Connexion.this,"successfuly login",Toast.LENGTH_LONG).show();
                         }
                         else{
                             Toast.makeText(Connexion.this,"Login error",Toast.LENGTH_LONG).show();
                         }
                     }
                 });

            }
        });
    }
    public void creer(View v){
        Intent gerline= new Intent(Connexion.this,MainActivity.class);
        startActivity(gerline);
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        mCallbackManager.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == 101) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                // Google Sign In was successful, authenticate with Firebase
                GoogleSignInAccount account = task.getResult(ApiException.class);
                firebaseAuthWithGoogle(account);
            } catch (ApiException e) {
                
            }
        }
    }
    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = firebaseAuth.getCurrentUser();
        if (currentUser!=null){
            updateUI();
        }

    }

    private void updateUI() {
        Toast.makeText(Connexion.this,"you're logged in",Toast.LENGTH_LONG).show();
        Intent inte= new Intent(Connexion.this,Accueil.class);
        startActivity(inte);
        finish();
    }
    private void handleFacebookAccessToken(AccessToken token) {
        Log.d(TAG, "handleFacebookAccessToken:" + token);

        AuthCredential credential = FacebookAuthProvider.getCredential(token.getToken());
        firebaseAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithCredential:success");
                            FirebaseUser user = firebaseAuth.getCurrentUser();
                            updateUI();
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithCredential:failure", task.getException());
                            Toast.makeText(Connexion.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                            updateUI();
                        }

                        // ...
                    }
                });
    }

    private void firebaseAuthWithGoogle(GoogleSignInAccount account) {

            AuthCredential credential = GoogleAuthProvider.getCredential(account.getIdToken(), null);
            firebaseAuth.signInWithCredential(credential)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                // Sign in success, update UI with the signed-in user's information
                                FirebaseUser user = firebaseAuth.getCurrentUser();
                                Intent i= new Intent(Connexion.this,Utilisateurs.class);
                                startActivity(i);
                                finish();
                                Toast.makeText(getApplicationContext(),"User logged is successfully",Toast.LENGTH_LONG).show();

                            } else {
                                Toast.makeText(getApplicationContext(),"error",Toast.LENGTH_LONG).show();

                            }
                        }
                    });
    }

}
