package com.prueba.servicios;

import java.util.List;

import com.prueba.modelo.Usuario;

public interface UsuarioService {
	public void crearUsuario(Usuario usuario);
	public void ActualizarUsuario(Usuario usuario);
	public void eliminarUsuario(String identificacion);
	public List<Usuario> listarUsuarios();
	public Usuario ListarPorId(String identificacion);
	
}
