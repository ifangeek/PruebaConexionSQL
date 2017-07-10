package com.ifangeek.pruebaconexionsql;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    db connexion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        new db.ConexionSQL();
        db.ConexionSQL.ConnectionHelper();


    }
}
