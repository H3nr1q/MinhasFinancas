package br.edu.ifspsaocarlos.minhasfinancas.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;

import br.edu.ifspsaocarlos.minhasfinancas.config.ConfiguracaoFirebase;
import br.edu.ifspsaocarlos.minhasfinancas.helper.Base64Custom;
import br.edu.ifspsaocarlos.minhasfinancas.helper.DateCustom;

public class Movimentacao implements Parcelable {

    private String data;
    private String categoria;
    private String descricao;
    private String tipo;
    private double valor;
    private String key;

    public Movimentacao() {
    }

    protected Movimentacao(Parcel in) {
        data = in.readString();
        categoria = in.readString();
        descricao = in.readString();
        tipo = in.readString();
        valor = in.readDouble();
        key = in.readString();
    }

    public static final Creator<Movimentacao> CREATOR = new Creator<Movimentacao>() {
        @Override
        public Movimentacao createFromParcel(Parcel in) {
            return new Movimentacao(in);
        }

        @Override
        public Movimentacao[] newArray(int size) {
            return new Movimentacao[size];
        }
    };

    public void salvar(String dataEscolhida){

        FirebaseAuth autenticacao = ConfiguracaoFirebase.getFirebaseAutenticacao();
        String idUsuario = Base64Custom.codificarBase64( autenticacao.getCurrentUser().getEmail() );
        String mesAno = DateCustom.mesAnoDataEscolhida( dataEscolhida );

        DatabaseReference firebase = ConfiguracaoFirebase.getFirebaseDatabase();
        firebase= firebase.child("movimentacao")
                .child( idUsuario )
                .child( mesAno );

        if(key != null && !key.isEmpty()){
            firebase.child(key).setValue(this);
        }
        else{
            firebase.push().setValue(this);
        }

    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public boolean isDespesa(){
        return tipo.equals("d");
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(data);
        parcel.writeString(categoria);
        parcel.writeString(descricao);
        parcel.writeString(tipo);
        parcel.writeDouble(valor);
        parcel.writeString(key);
    }
}

