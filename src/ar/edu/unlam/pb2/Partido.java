package ar.edu.unlam.pb2;

import java.util.*;

public abstract class Partido {
	
	private Integer idPartido;
	private Equipo equipoLocal;
	private Equipo equipoRival;
	private Integer golesLocal;
	private Integer golesRival;
	private TipoResultado tipoResultado;

	public Partido(Integer idPartido,Equipo equipoLocal, Equipo equipoRival) {
		super();
		this.idPartido = idPartido;
		this.equipoLocal = equipoLocal;
		this.equipoRival = equipoRival;
		this.golesLocal = 0;
		this.golesRival = 0;
	}

	public Integer getIdPartido() {
		return idPartido;
	}

	public void setIdPartido(Integer idPartido) {
		this.idPartido = idPartido;
	}

	public Equipo getEquipoLocal() {
		return equipoLocal;
	}

	public void setEquipoLocal(Equipo equipoLocal) {
		this.equipoLocal = equipoLocal;
	}

	public Equipo getEquipoRival() {
		return equipoRival;
	}

	public void setEquipoRival(Equipo equipoRival) {
		this.equipoRival = equipoRival;
	}

	
	public Integer getGolesLocal() {
		return golesLocal;
	}

	public void setGolesLocal(Integer golesLocal) {
		this.golesLocal = golesLocal;
	}

	public Integer getGolesRival() {
		return golesRival;
	}

	public void setGolesRival(Integer golesRival) {
		this.golesRival = golesRival;
	}

	public TipoResultado getTipoResultado() {
		return tipoResultado;
	}

	public void setTipoResultado(TipoResultado tipoResultado) {
		this.tipoResultado = tipoResultado;
	}

	@Override
	public int hashCode() {
		return Objects.hash(equipoLocal, equipoRival, idPartido);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Partido other = (Partido) obj;
		return Objects.equals(equipoLocal, other.equipoLocal) && Objects.equals(equipoRival, other.equipoRival)
				&& Objects.equals(idPartido, other.idPartido);
	}

	public abstract TipoResultado obtenerResultado(Partido partido);

	

	
	
		
	

	

	
	

}
