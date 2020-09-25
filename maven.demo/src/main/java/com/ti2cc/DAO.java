package com.ti2cc;

import java.sql.*;

public class DAO {
	private Connection conexao;
	
	public DAO() {
		conexao = null;
	}
	
	public boolean conectar() {
		String driverName = "org.postgresql.Driver";                    
		String serverName = "localhost";
		String mydatabase = "teste";
		int porta = 5432;
		String url = "jdbc:postgresql://" + serverName + ":" + porta +"/" + mydatabase;
		String username = "ti2cc";
		String password = "ti@cc";
		boolean status = false;

		try {
			Class.forName(driverName);
			conexao = DriverManager.getConnection(url, username, password);
			status = (conexao == null);
			System.out.println("Conexão efetuada com o postgres!");
		} catch (ClassNotFoundException e) { 
			System.err.println("Conexão NÃO efetuada com o postgres -- Driver não encontrado -- " + e.getMessage());
		} catch (SQLException e) {
			System.err.println("Conexão NÃO efetuada com o postgres -- " + e.getMessage());
		}

		return status;
	}
	
	public boolean close() {
		boolean status = false;
		
		try {
			conexao.close();
			status = true;
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}
		return status;
	}
	
	public boolean inserirX(X X) {
		boolean status = false;
		try {  
			Statement st = conexao.createStatement();
			st.executeUpdate("INSERT INTO X (codigo, login, senha, sexo) "
					       + "VALUES ("+X.getCodigo()+ ", '" + X.getLogin() + "', '"  
					       + X.getSenha() + "', '" + X.getSexo() + "');");
			st.close();
			status = true;
		} catch (SQLException u) {  
			throw new RuntimeException(u);
		}
		return status;
	}
	
	public boolean atualizarX(X X) {
		boolean status = false;
		try {  
			Statement st = conexao.createStatement();
			String sql = "UPDATE X SET login = '" + X.getLogin() + "', senha = '"  
				       + X.getSenha() + "', sexo = '" + X.getSexo() + "'"
					   + " WHERE codigo = " + X.getCodigo();
			st.executeUpdate(sql);
			st.close();
			status = true;
		} catch (SQLException u) {  
			throw new RuntimeException(u);
		}
		return status;
	}
	
	public boolean excluirX(int codigo) {
		boolean status = false;
		try {  
			Statement st = conexao.createStatement();
			st.executeUpdate("DELETE FROM X WHERE codigo = " + codigo);
			st.close();
			status = true;
		} catch (SQLException u) {  
			throw new RuntimeException(u);
		}
		return status;
	}
	
	
	public X[] getXs() {
		X[] Xs = null;
		
		try {
			Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			ResultSet rs = st.executeQuery("SELECT * FROM X");		
	         if(rs.next()){
	             rs.last();
	             Xs = new X[rs.getRow()];
	             rs.beforeFirst();

	             for(int i = 0; rs.next(); i++) {
	                Xs[i] = new X(rs.getInt("codigo"), rs.getString("login"), 
	                		                  rs.getString("senha"), rs.getString("sexo").charAt(0));
	             }
	          }
	          st.close();
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return Xs;
	}

	
	public X[] getXsMasculinos() {
		X[] Xs = null;
		
		try {
			Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			ResultSet rs = st.executeQuery("SELECT * FROM X WHERE X.sexo LIKE 'M'");		
	         if(rs.next()){
	             rs.last();
	             Xs = new X[rs.getRow()];
	             rs.beforeFirst();

	             for(int i = 0; rs.next(); i++) {
		                Xs[i] = new X(rs.getInt("codigo"), rs.getString("login"), 
                         		                  rs.getString("senha"), rs.getString("sexo").charAt(0));
	             }
	          }
	          st.close();
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return Xs;
	}
}