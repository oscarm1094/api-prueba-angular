package com.prueba.controlador;


import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.prueba.modelo.Login;
import com.prueba.modelo.Usuario;
import com.prueba.servicios.UsuarioService;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@AllArgsConstructor
@RestController
@RequestMapping("/usuarios")
@CrossOrigin(origins="*")
@Slf4j
public class UsuarioController {
	
	private UsuarioService userService;
	
	@CrossOrigin(origins="*")
	@GetMapping(value="/listar", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Usuario>> listarUsuarios(){
		List<Usuario> usuarios = userService.listarUsuarios();
		return new ResponseEntity<List<Usuario>>(usuarios, HttpStatus.OK);
	}
	
	@CrossOrigin(origins="*")
	@GetMapping(value="/buscar/{identificacion}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Usuario> buscarPorId(@PathVariable String identificacion){
		Usuario usuario = userService.ListarPorId(identificacion);
		return new ResponseEntity<Usuario>(usuario, HttpStatus.OK);
	}
	
	@PostMapping(value="/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public void crearUsuario(@RequestBody Usuario usuario){
		userService.crearUsuario(usuario);
		log.info("Se ha creado el usuario!" + usuario.getIdentificacion());
		//return new ResponseEntity<String>("Creado", HttpStatus.OK);
	}
	
	@PutMapping(value="/update", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public void actualizarUsuario(@RequestBody Usuario usuario){
		userService.ActualizarUsuario(usuario);
		log.info("Se ha actualizado el usuario!" + usuario.getIdentificacion());
		//return new ResponseEntity<String>("Actualizado", HttpStatus.OK);
	}
	
	@DeleteMapping(value="/delete/{id}")
	public void deleteUser(@PathVariable String id) {
		userService.eliminarUsuario(id);
		log.info("Se ha eliminado el usuario");
	}
	
	@PostMapping(value="/login")
	public ResponseEntity<String> loginUsuario(@RequestBody Login login) {
		List<Usuario> listaUsuarios = userService.listarUsuarios();
		Usuario us = listaUsuarios.stream().filter( u -> u.getUsername().equals(login.getUsername()) 
				&& u.getPassword().equals(login.getPassword())).findAny().orElse(null);
		if(us != null) {
			return new ResponseEntity<String>("0", HttpStatus.OK);
		}else {
			return new ResponseEntity<String>("-1", HttpStatus.BAD_REQUEST);
		}
	}

}
