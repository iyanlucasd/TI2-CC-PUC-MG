package com.ti2cc;

public class Usuario {
    private int codigo;
    private String senha;
    private String login;
    private char sexo;

    public Usuario() {
        this.codigo = -1;
        this.senha = "";
        this.login = "";
        this.sexo = '*';
    }
    public int getCodigo() {
        return codigo;
    }
    public String getSenha() {
        return senha;
    }
    public String getLogin() {
        return login;
    }
    public char getSexo() {
        return sexo;
    }
    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }
    public void setLogin(String login) {
        this.login = login;
    }
    public void setSenha(String senha) {
        this.senha = senha;
    }
    public void setSexo(char sexo) {
        this.sexo = sexo;
    }
    @Override
    public String toString() {
        // TODO Auto-generated method stub
        return super.toString();
    }

    public Usuario(int codigo, String login, String senha, char sexo) {
        this.codigo = codigo;
        this.login = login;
        this.senha = senha;
        this.sexo = sexo;
    }
}