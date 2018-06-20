package app.marciovazeredo;

import android.content.Intent;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class TelaCadastro extends AppCompatActivity {

    private FirebaseAuth firebaseAuth;
    private EditText edtNome, edtSobrenome, edtEmail, edtTelefone, edtSenha, edtConfSenha;
    private Button btnCriarConta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_cadastro);

        firebaseAuth = FirebaseAuth.getInstance();

        edtNome = (EditText) findViewById(R.id.edtNome);
        edtSobrenome = (EditText) findViewById(R.id.edtSobrenome);
        edtEmail = (EditText) findViewById(R.id.edtEmail);
        edtTelefone = (EditText) findViewById(R.id.edtTelefone);
        edtSenha = (EditText) findViewById(R.id.edtSenha);
        edtConfSenha = (EditText) findViewById(R.id.edtConfSenha);

    }

    public void cadastro(View view) {
        if (!edtEmail.getText().toString().equals("")) {
            if (edtSenha.getText().toString().equals(edtConfSenha.getText().toString())) {
                firebaseAuth.createUserWithEmailAndPassword(edtEmail.getText().toString(), edtSenha.getText().toString()).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (!task.isSuccessful()) {
                            Toast.makeText(TelaCadastro.this, "Erro no cadastro!", Toast.LENGTH_LONG).show();
                        } else {
                            FirebaseDatabase database = FirebaseDatabase.getInstance();
                            DatabaseReference databaseReference = database.getReference("users").child(FirebaseAuth.getInstance().getCurrentUser().getUid());
                            databaseReference.child("Nome").setValue(edtNome.getText().toString());
                            databaseReference.child("Sobrenome").setValue(edtSobrenome.getText().toString());
                            databaseReference.child("Email").setValue(edtEmail.getText().toString());
                            databaseReference.child("Telefone").setValue(edtTelefone.getText().toString());
                            databaseReference.child("Senha").setValue(edtSenha.getText().toString());

                            Toast.makeText(TelaCadastro.this, "Usuario criado com sucesso!", Toast.LENGTH_LONG).show();

                            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                            user.sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()) {
                                        Toast.makeText(TelaCadastro.this, "Email de verificação enviado!", Toast.LENGTH_LONG).show();
                                    }
                                }
                            });

                            startActivity(new Intent(TelaCadastro.this, MainActivity.class));
                            finish();
                        }
                    }
                });
            } else {
                Toast.makeText(TelaCadastro.this, "Senhas diferentes!", Toast.LENGTH_LONG).show();
            }
        } else {
            Toast.makeText(TelaCadastro.this, "Preencha o campo do e-mail!", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(TelaCadastro.this, MainActivity.class));
        finish();
        return;
    }
}
