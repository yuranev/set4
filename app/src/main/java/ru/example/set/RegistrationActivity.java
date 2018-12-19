package ru.example.set;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegistrationActivity extends AppCompatActivity {

    SharedPreferences sharedPreferences;
    Editor editor;
    Button buttonReg2;
    EditText txtUsername, txtPassword, txtEmail;
    UserSession session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        //Окрашиваем статусбар и нижнюю панель
        Window window = this.getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(ContextCompat.getColor(this, R.color.navbar_statbar_colors));

        txtUsername = findViewById(R.id.button1);
        txtPassword = findViewById(R.id.button2);
        txtEmail = findViewById(R.id.button3);
        buttonReg2 = findViewById(R.id.button4);

// creating an shared Preference file for the information to be stored
// first argument is the name of file and second is the mode, 0 is private mode

        sharedPreferences = getApplicationContext().getSharedPreferences("Reg", 0);
// get editor to edit in file
        editor = sharedPreferences.edit();

        buttonReg2.setOnClickListener(new View.OnClickListener() {

            public void onClick (View v) {
                String name = txtUsername.getText().toString();
                String email = txtEmail.getText().toString();
                String pass = txtPassword.getText().toString();

                if(txtUsername.getText().length()<=0){
                    Toast.makeText(RegistrationActivity.this, "Enter name", Toast.LENGTH_SHORT).show();
                }
                else if( txtEmail.getText().length()<=0){
                    Toast.makeText(RegistrationActivity.this, "Enter email", Toast.LENGTH_SHORT).show();
                }
                else if( txtPassword.getText().length()<=0){
                    Toast.makeText(RegistrationActivity.this, "Enter password", Toast.LENGTH_SHORT).show();
                }
                else{

                    // as now we have information in string. Lets stored them with the help of editor
                    editor.putString("Name", name);
                    editor.putString("Email",email);
                    editor.putString("txtPassword",pass);
                    editor.apply();
                }   // commit the values

                // after saving the value open next activity
                Intent ob = new Intent(RegistrationActivity.this, LoginActivity.class);
                startActivity(ob);

            }
        });
    }
}