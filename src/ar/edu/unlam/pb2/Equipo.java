package ar.edu.unlam.pb2;

import java.util.Objects;

public class Equipo {
	
	private String nombre;
	private TipoDeGrupo tipo;
	private Integer golesAFavor;
	private Integer golesEncontra;
	private Integer puntosDelPartido;
	
	public Equipo(String nombre, TipoDeGrupo tipo) {
		super();
		this.nombre = nombre;
		this.tipo = tipo;
		this.golesAFavor = 0;
		this.puntosDelPartido = 0;
		this.golesEncontra = 0;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public TipoDeGrupo getTipo() {
		return tipo;
	}
	public void setTipo(TipoDeGrupo tipo) {
		this.tipo = tipo;
	}
	
	public Integer getGolesAFavor() {
		return golesAFavor;
	}
	public void setGolesAFavor(Integer golesAFavor) {
		this.golesAFavor = golesAFavor;
	}
	public Integer getPuntosDelPartido() {
		return puntosDelPartido;
	}
	public void setPuntosDelPartido(Integer puntosDelPartido) {
		this.puntosDelPartido = puntosDelPartido;
	}
	
	public Integer getGolesEncontra() {
		return golesEncontra;
	}
	public void setGolesEncontra(Integer golesEncontra) {
		this.golesEncontra = golesEncontra;
	}
	@Override
	public int hashCode() {
		return Objects.hash(nombre, tipo);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Equipo other = (Equipo) obj;
		return Objects.equals(nombre, other.nombre) && tipo == other.tipo;
	}
	@Override
	public String toString() {
		return "Equipo [nombre=" + nombre + ", tipo=" + tipo + ", golesAFavor=" + golesAFavor + ", golesEncontra="
				+ golesEncontra + ", puntosDelPartido=" + puntosDelPartido + "]";
	}
	
	public void incrementarPunto(int i) {
		this.puntosDelPartido+=i;
		
	}
	public void acumularGoles(Integer golesAFavor, Integer golesEncontra) {
		this.golesAFavor = golesAFavor;
		this.golesEncontra = golesEncontra;
		
	}
	
	

}
