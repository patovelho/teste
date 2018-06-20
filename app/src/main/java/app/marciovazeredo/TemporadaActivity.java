package app.marciovazeredo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class TemporadaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_temporada);
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(TemporadaActivity.this, TelaInicial.class));
        finish();
        return;
    }

}
