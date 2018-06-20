package app.marciovazeredo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.google.firebase.auth.FirebaseAuth;

public class TelaInicial extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_inicial);

        Button btn_Anual = (Button) findViewById(R.id.btn_Anual);
        Button btn_Temp = (Button) findViewById(R.id.btn_Temp);
        Button btn_Venda = (Button) findViewById(R.id.btn_Venda);

        btn_Anual.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(TelaInicial.this, AnualActivity.class));
                finish();

            }
        });

        btn_Temp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(TelaInicial.this, TemporadaActivity.class));
                finish();

            }
        });

        btn_Venda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(TelaInicial.this, VendaActivity.class));
                finish();

            }
        });


        Button btn_Logoff = (Button) findViewById(R.id.btn_Logoff);
        btn_Logoff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(TelaInicial.this, MainActivity.class));
                finish();
            }
        });
    }
}
