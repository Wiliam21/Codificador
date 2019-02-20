package com.example.mad.codificador;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    static String texto,codigo;
    static String Normal;
    static String Crip;
    static int encriptado;
    Button btnLimpiar,btnDesencriptar,btnEncriptar;
    EditText txtaNormal,txtaCifrado;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtaCifrado=(EditText)findViewById(R.id.txtaCifrado);
        txtaNormal=(EditText)findViewById(R.id.txtaNormal);
        btnLimpiar=(Button)findViewById(R.id.btnLimpiar);
        btnDesencriptar=(Button)findViewById(R.id.btnDesencriptar);
        btnEncriptar=(Button)findViewById(R.id.btnEncriptar);

        View.OnClickListener cifrar = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Encriptar();
            }
        };
        View.OnClickListener descifrar= new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Descifrar();
            }
        };
        View.OnClickListener Limpiar=new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Clean();
            }
        };
        btnEncriptar.setOnClickListener(cifrar);
        btnDesencriptar.setOnClickListener(descifrar);
        btnLimpiar.setOnClickListener(Limpiar);
    }

    private void Clean() {
        txtaCifrado.setText(null);
        txtaNormal.setText(null);
    }

    private void Descifrar() {
        txtaNormal.setText(null);
        char letra;
        codigo=txtaCifrado.getText().toString();
        String[] temp=codigo.split(" ");
        for(int i=0;i<temp.length;i++){
            encriptado=Integer.parseInt(temp[i]);
            encriptado/=2;
            int x=128, aux=0, n=10000000;
            for(int j=0;j<8;j++){
                if(encriptado>=n) {
                    encriptado-=n;
                    aux+=x;
                }
                n/=10;
                x/=2;
            }
            encriptado=aux;
            encriptado+=20;

            letra= (char)encriptado;

            txtaNormal.setText(Normal+letra);
            Normal=txtaNormal.getText().toString();
        }
    }

    private void Encriptar() {
        String texto=txtaNormal.getText().toString();
        for(int i=0;i<texto.length();i++){
            encriptado=(texto.codePointAt(i));
            encriptado-=20;
            int n=128,temp=0,base=10000000;
            for(int j=0;j<8;j++){
                if(encriptado>=n){
                    encriptado-=n;
                    temp+=base;
                }
                n/=2;
                base/=10;
            }
            encriptado=temp*2;
            txtaCifrado.setText(Crip+encriptado+" ");
            Crip=txtaCifrado.getText().toString();
        }

    }
}
