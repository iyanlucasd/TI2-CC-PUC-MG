package com.ti2cc;

public class Main {
	
	public static void main(String[] args) {
		
		DAO dao = new DAO();
		
		dao.conectar();

		
		//Inserir um elemento na tabela
		X X = new X(11, "pablo", "pablo",'M');
		if(dao.inserirX(X) == true) {
			System.out.println("Inserção com sucesso -> " + X.toString());
		}
		
		//Mostrar usuários do sexo masculino		
		System.out.println("==== Mostrar usuários do sexo masculino === ");
		X[] Xs = dao.getXsMasculinos();
		for(int i = 0; i < Xs.length; i++) {
			System.out.println(Xs[i].toString());
		}

		//Atualizar usuário
		X.setSenha("nova senha");
		dao.atualizarX(X);

		//Mostrar usuários do sexo masculino
		System.out.println("==== Mostrar usuários === ");
		Xs = dao.getXs();
		for(int i = 0; i < Xs.length; i++) {
			System.out.println(Xs[i].toString());
		}
		
		//Excluir usuário
		dao.excluirX(X.getCodigo());
		
		//Mostrar usuários
		Xs = dao.getXs();
		System.out.println("==== Mostrar usuários === ");		
		for(int i = 0; i < Xs.length; i++) {
			System.out.println(Xs[i].toString());
		}
		
		dao.close();
	}
}

