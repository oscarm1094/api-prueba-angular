package com.prueba.modelo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class Usuario {
	private String identificacion;
	private String nombres;
	private String username;
	private String email;
	private String password;
	private String perfil;
}
