package com.prueba.servicios;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import org.springframework.stereotype.Service;

import com.prueba.modelo.Usuario;

@Service
public class UsuarioServiceImpl implements UsuarioService {

	private List<Usuario> usuarios = getMockUsuarios();

	@Override
	public void crearUsuario(Usuario usuario) {
		this.usuarios.add(usuario);

	}

	@Override
	public void ActualizarUsuario(Usuario usuario) {
		Usuario usuarioAnterior = this.usuarios.stream()
				.filter(x -> x.getIdentificacion().equals(usuario.getIdentificacion()))
				.findAny()
				.get();
		int index = this.usuarios.indexOf(usuarioAnterior);
		this.usuarios.add(index, usuario);

	}

	@Override
	public void eliminarUsuario(String identificacion) {
		Predicate<Usuario> predicado = x -> x.getIdentificacion().equals(identificacion);
		this.usuarios.removeIf(predicado);

	}

	@Override
	public List<Usuario> listarUsuarios() {
		return usuarios;
	}

	@Override
	public Usuario ListarPorId(String identificacion) {
		return this.usuarios.stream().filter(u -> u.getIdentificacion().equals(identificacion)).findFirst().get();
	}

	private List<Usuario> getMockUsuarios() {
		List<Usuario> lista = new ArrayList<>();
		for (int i = 1; i < 10; i++) {
			lista.add(Usuario.builder().identificacion("178451478" + i).nombres("user-" + i)
					.email("user.s" + i + "@mail.com").password("123").username("us" + i).build());
		}
		return lista;
	}

}
