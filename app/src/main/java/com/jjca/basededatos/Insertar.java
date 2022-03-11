package com.jjca.basededatos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class Insertar extends AppCompatActivity {

    EditText id, fecha, asunto, actividad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insertar);

        ImageView backarrow = (ImageView) findViewById(R.id.backarrow);

        backarrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Insertar.this, MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });

        id = findViewById(R.id.txtid);
        fecha = findViewById(R.id.txtfecha);
        asunto = findViewById(R.id.txtasunto);
        actividad = findViewById(R.id.txtactividad);

        Button subbtn = (Button) findViewById(R.id.subbtn);

        subbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insertar("http://192.168.1.82:80/agenda/insertar.php");
            }
        });

    }

    public void insertar(String link)
    {
        StringRequest request = new StringRequest(Request.Method.POST, link, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                Toast toast = Toast.makeText(getApplicationContext(), "Se han insertado los datos correctamente", Toast.LENGTH_LONG);
                toast.show();

                Intent intent = new Intent(Insertar.this, MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast toast = Toast.makeText(getApplicationContext(), "Se ha producido un error de conexión", Toast.LENGTH_LONG);
                toast.show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> datos = new HashMap<String, String>();
                datos.put("id_agenda", id.getText().toString());
                datos.put("fecha", fecha.getText().toString());
                datos.put("asunto", asunto.getText().toString());
                datos.put("actividad", actividad.getText().toString());
                return datos;
            }
        };

        RequestQueue queue = Volley.newRequestQueue(this);
        queue.add(request);
    }
}