package br.com.ufg.si.pw.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class Aluno {
	private Integer matricula;
	private String nome;
	private String fone;
	private String cpf;
	
	public Aluno() {
		super();
	}
	
	public Aluno(Integer matricula, String nome) {
		setMatricula(matricula);
		setNome(nome);
	}

	public Integer getMatricula() {
		return matricula;
	}

	public void setMatricula(Integer matricula) {
		this.matricula = matricula;
	}
	
	public void setMatricula(String matricula) {
		if (matricula != null) {
			try {
				this.matricula = Integer.parseInt(matricula);
			} catch (NumberFormatException e) {
				
			}
			
		}
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getFone() {
		return fone;
	}

	public void setFone(String fone) {
		this.fone = fone;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

}