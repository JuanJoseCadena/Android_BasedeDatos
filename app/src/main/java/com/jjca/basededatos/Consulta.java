package com.jjca.basededatos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Consulta extends AppCompatActivity {

    TextView fecha, asunto, actividad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consulta);

        ImageView back = (ImageView) findViewById(R.id.back);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Consulta.this, MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });

        fecha = findViewById(R.id.fechatxt);
        asunto = findViewById(R.id.asuntotxt);
        actividad = findViewById(R.id.actividadtxt);

        EditText ID = (EditText) findViewById(R.id.EditID);

        Button consbtn = (Button) findViewById(R.id.consbtn);

        consbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                consultar("http://192.168.1.82:80/agenda/consultar.php?id_agenda="+ID.getText());
            }
        });

    }

    public void consultar(String link)
    {
        JsonArrayRequest request = new JsonArrayRequest(link, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                JSONObject datos = null;
                for (int i = 0; i < response.length(); i++) {
                    try {
                        datos = response.getJSONObject(i);
                        fecha.setText(datos.getString("fecha"));
                        asunto.setText(datos.getString("asunto"));
                        actividad.setText(datos.getString("actividad"));
                    } catch (JSONException e) {
                        Toast toast = Toast.makeText(getApplicationContext(), "Se ha producido un error", Toast.LENGTH_LONG);
                        toast.show();
                    }
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast toast = Toast.makeText(getApplicationContext(), "Se ha producido un error de conexión", Toast.LENGTH_LONG);
                toast.show();
            }
        });
        RequestQueue queue = Volley.newRequestQueue(this);
        queue.add(request);
    }
}