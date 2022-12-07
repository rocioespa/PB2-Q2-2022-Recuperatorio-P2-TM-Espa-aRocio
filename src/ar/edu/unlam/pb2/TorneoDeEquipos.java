package ar.edu.unlam.pb2;

import java.util.*;

public class TorneoDeEquipos {

	private String nombre;
	private Set<Partido> partidos;
	private Set<Equipo> equipos;
	private Set<Equipo> equipoEliminatorias;

	public TorneoDeEquipos(String nombre) {
		super();
		this.nombre = nombre;
		this.partidos = new HashSet<>();
		this.equipos = new HashSet<>();
		this.equipoEliminatorias = new HashSet<>();
	}
	
	

	public Set<Equipo> getEquipoEliminatorias() {
		return equipoEliminatorias;
	}



	public void setEquipoEliminatorias(Set<Equipo> equipoEliminatorias) {
		this.equipoEliminatorias = equipoEliminatorias;
	}



	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Set<Partido> getPartidos() {
		return partidos;
	}

	public void setPartidos(Set<Partido> partidos) {
		this.partidos = partidos;
	}

	public Set<Equipo> getEquipos() {
		return equipos;
	}

	public void setEquipos(Set<Equipo> equipos) {
		this.equipos = equipos;
	}

	public void registrarPartido(Partido partido) throws Exception {

		if (validarPartidos(partido)) {
			if (!(buscarPartido(partido))) {
				this.partidos.add(partido);
			} else {
				throw new PartidoYaJugado("Este partido ya fue jugado");
			}

		}

	}

	private boolean buscarPartido(Partido partido) {
		if ((this.partidos.contains(partido))) {
			return true;
		}
		return false;
	}

	public void registrarEquipos(Equipo equipo) throws Exception {
		if (this.equipos.size() <= 32) {
			if (!(buscarEquipo(equipo))) {
				this.equipos.add(equipo);
			}

		}

	}

	public boolean buscarEquipo(Equipo equipo) {
		if (this.equipos.contains(equipo)) {
			return true;
		}
		return false;
	}

	public Boolean validarPartidos(Partido partido) throws EquiposDeDiferentesGrupos, EquiposDuplicados {
		if (!(partido.getEquipoLocal().getNombre().equals(partido.getEquipoRival().getNombre()))) {
			if (partido instanceof PartidoDeGrupo) {
				if (partido.getEquipoLocal().getTipo().equals(partido.getEquipoRival().getTipo())) {
					return true;
				}
				throw new EquiposDeDiferentesGrupos("Equipos de diferentes grupos, no pueden jugar");
			} else {
				if (buscarEquipoEnEliminatorias(partido.getEquipoLocal(), partido.getEquipoRival())) {
					if (!(partido.getEquipoLocal().getTipo().equals(partido.getEquipoRival().getTipo()))) {
						return true;
					}
					throw new EquiposDeDiferentesGrupos("Equipos del mismo grupo no pueden jugar en eliminatorias");
				}
			}

		}
		throw new EquiposDuplicados("El mismo equipo no pueden jugar el partido");
	}

	private boolean buscarEquipoEnEliminatorias(Equipo equipoLocal, Equipo equipoRival) {
		if (this.equipoEliminatorias.contains(equipoLocal) && this.equipoEliminatorias.contains(equipoRival)) {
			return true;
		}
		return false;
	}

	public TipoResultado obtenerResultadoDelPartido(Integer idPartido) throws PartidoNoEncontrado {
		Partido partidoEncontrado = buscarPartidoPorId(idPartido);
		return partidoEncontrado.obtenerResultado(partidoEncontrado);

	}

	public void registrarResultadoDelPartidoDeGrupo(Integer idPartido, Integer golesLocal, Integer golesRival)
			throws Exception {
		Partido partidoEncontrado = buscarPartidoPorId(idPartido);
		if (partidoEncontrado instanceof PartidoDeGrupo) {
			((PartidoDeGrupo) partidoEncontrado).registrarResultados(golesLocal, golesRival);
		} else {
			throw new Exception("No es un partido de grupos");
		}

		TipoResultado resultado = this.obtenerResultadoDelPartido(idPartido);
		partidoEncontrado.getEquipoLocal().acumularGoles(partidoEncontrado.getGolesLocal(),
				partidoEncontrado.getGolesRival());
		partidoEncontrado.getEquipoRival().acumularGoles(partidoEncontrado.getGolesRival(),
				partidoEncontrado.getGolesLocal());

		finalizarRegistroDeResultadoDeGrupos(partidoEncontrado, resultado);

	}

	private void finalizarRegistroDeResultadoDeGrupos(Partido partidoEncontrado, TipoResultado resultado) {
		if (resultado.equals(TipoResultado.EMPATE)) {
			partidoEncontrado.getEquipoLocal().incrementarPunto(1);
			partidoEncontrado.getEquipoRival().incrementarPunto(1);
		} else if (resultado.equals(TipoResultado.GANA_LOCAL)) {
			partidoEncontrado.getEquipoLocal().incrementarPunto(3);
			partidoEncontrado.getEquipoRival().incrementarPunto(0);
		} else if ((resultado.equals(TipoResultado.GANA_RIVAL))) {
			partidoEncontrado.getEquipoLocal().incrementarPunto(0);
			partidoEncontrado.getEquipoRival().incrementarPunto(3);

		}
	}

	public void registrarResultadoDelPartidoDeEliminatorias(Integer idPartido, Integer golesLocal, Integer golesRival,
			Integer penalDeLocal, Integer penalDeRival) throws Exception {

		Partido partidoEncontrado = buscarPartidoPorId(idPartido);
		if (partidoEncontrado instanceof PartidoEliminatorio) {
			((PartidoEliminatorio) partidoEncontrado).registrarResultados(golesLocal, golesRival, penalDeLocal,
					penalDeRival);
		} else {
			throw new Exception("No es un partido de eliminatorias");
		}

		
		partidoEncontrado.getEquipoLocal().acumularGoles(partidoEncontrado.getGolesLocal(),
		partidoEncontrado.getGolesRival());
		partidoEncontrado.getEquipoRival().acumularGoles(partidoEncontrado.getGolesRival(),
		partidoEncontrado.getGolesLocal());
		

	}


	private Partido buscarPartidoPorId(Integer idPartido) throws PartidoNoEncontrado {
		for (Partido partido : this.partidos) {
			if (partido.getIdPartido().equals(idPartido)) {
				return partido;
			}
		}
		throw new PartidoNoEncontrado();
	}

	public void agregarEquiposAEliminatorias(Equipo equipo1) {
		
		this.equipoEliminatorias.add(equipo1);

	}
	
	public void finalizarLaFaseDeGrupos(Partido partido, Equipo equipo) throws PartidoNoEncontrado {
		TipoResultado resultadoFinal = null;
		obtenerResultadoDelPartido(partido.getIdPartido());
		if(resultadoFinal!= TipoResultado.EMPATE) {
			agregarEquiposAEliminatorias(equipo);
		}
		
	}
	
	public TreeSet<Equipo> obtenerEquiposDeGruposOrdenados() {
		TreeSet<Equipo> equiposOrdenados = new TreeSet <> (new OrdenPorPuntajeYDiferenciaDeGoles());
		equiposOrdenados.addAll(this.equipos);
		return equiposOrdenados;
		
	}

}
