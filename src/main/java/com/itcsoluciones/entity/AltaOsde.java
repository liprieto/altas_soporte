package com.itcsoluciones.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "File1")
public class AltaOsde {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	private String existe = "NO";
	private String operador;
	private String filial;
	private String delegacion;
	private String pos;
	private String codigo_prestador;
	private String nombre;
	private String especialidad;
	private String cuit_prestador;
	private String calle;
	private String numero_domicilio;
	private String piso;
	private String departamento;
	private String localidad;
	private String provincia;
	private String telefono;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getExiste() {
		return existe;
	}

	public void setExiste(String existe) {
		this.existe = existe;
	}

	public String getOperador() {
		return operador;
	}

	public void setOperador(String operador) {
		this.operador = operador;
	}

	public String getFilial() {
		return filial;
	}

	public void setFilial(String filial) {
		this.filial = filial;
	}

	public String getDelegacion() {
		return delegacion;
	}

	public void setDelegacion(String delegacion) {
		this.delegacion = delegacion;
	}

	public String getPos() {
		return pos;
	}

	public void setPos(String pos) {
		this.pos = pos;
	}

	public String getCodigo_prestador() {
		return codigo_prestador;
	}

	public void setCodigo_prestador(String codigo_prestador) {
		this.codigo_prestador = codigo_prestador;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getEspecialidad() {
		return especialidad;
	}

	public void setEspecialidad(String especialidad) {
		this.especialidad = especialidad;
	}

	public String getCuit_prestador() {
		return cuit_prestador;
	}

	public void setCuit_prestador(String cuit_prestador) {
		this.cuit_prestador = cuit_prestador;
	}

	public String getCalle() {
		return calle;
	}

	public void setCalle(String calle) {
		this.calle = calle;
	}

	public String getNumero_domicilio() {
		return numero_domicilio;
	}

	public void setNumero_domicilio(String numero_domicilio) {
		this.numero_domicilio = numero_domicilio;
	}

	public String getPiso() {
		return piso;
	}

	public void setPiso(String piso) {
		this.piso = piso;
	}

	public String getDepartamento() {
		return departamento;
	}

	public void setDepartamento(String departamento) {
		this.departamento = departamento;
	}

	public String getLocalidad() {
		return localidad;
	}

	public void setLocalidad(String localidad) {
		this.localidad = localidad;
	}

	public String getProvincia() {
		return provincia;
	}

	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	@Override
	public String toString() {
		return "AltaOsde [id=" + id + ", existe=" + existe + ", operador=" + operador + ", filial=" + filial
				+ ", delegacion=" + delegacion + ", pos=" + pos + ", codigo_prestador=" + codigo_prestador + ", nombre="
				+ nombre + ", especialidad=" + especialidad + ", cuit_prestador=" + cuit_prestador + ", calle=" + calle
				+ ", numero_domicilio=" + numero_domicilio + ", piso=" + piso + ", departamento=" + departamento
				+ ", localidad=" + localidad + ", provincia=" + provincia + ", telefono=" + telefono + "]";
	}

	
	 
	  
	 

}