package model;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

import javax.persistence.*;


@Entity
@Table(name="UsuarioJPA")
public class Usuario implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	private String usuario;
	private String mail;
	private String contrasena;
	private String telefono;
	private boolean administrador;
	@ManyToMany
	@JoinTable(name="usuario2alineacion",
		joinColumns = @JoinColumn(name="id_usuario"),
		inverseJoinColumns = @JoinColumn(name="id_alineacion"))
	private List<Alineacion> alineaciones;
	
	@ManyToMany
	@JoinTable(name="usuario2partido",
			joinColumns = @JoinColumn(name="id_usuario"),
			inverseJoinColumns = @JoinColumn(name="id_partido"))
	private List<Partido> partidos;
	
	public Usuario(){
		this.administrador = false;
		this.alineaciones = new LinkedList<Alineacion>();
		this.partidos = new LinkedList<Partido>();
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}


	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getContrasena() {
		return contrasena;
	}

	public void setContrasena(String contrasena) {
		this.contrasena = contrasena;
	}

	public List<Alineacion> getAlineaciones() {
		return alineaciones;
	}

	public void setAlineaciones(List<Alineacion> alineaciones) {
		this.alineaciones = alineaciones;
	}

	public boolean isAdministrador() {
		return administrador;
	}

	public void setAdministrador(boolean administrador) {
		this.administrador = administrador;
	}

	public List<Partido> getPartidos() {
		return partidos;
	}

	public void setPartidos(List<Partido> partidos) {
		this.partidos = partidos;
	}
	
	
	
	
}
