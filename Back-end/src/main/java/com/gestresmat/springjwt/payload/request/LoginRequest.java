package com.gestresmat.springjwt.payload.request;

import com.gestresmat.springjwt.models.ListeNoir;

import javax.validation.constraints.NotBlank;

public class LoginRequest {
	@NotBlank
	private String username;

	@NotBlank
	private String password;

    private ListeNoir listeNoir;

	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPassword() {
		return password;
	}


	public ListeNoir getListeNoir() {
		return listeNoir;
	}

	public void setListeNoir(ListeNoir listeNoir) {
		this.listeNoir = listeNoir;
	}
}
