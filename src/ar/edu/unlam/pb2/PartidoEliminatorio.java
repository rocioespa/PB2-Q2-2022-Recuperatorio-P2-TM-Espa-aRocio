package ar.edu.unlam.pb2;

public class PartidoEliminatorio extends Partido {
	
	private Integer penalesLocales;
	private Integer penalesVisitante;

	public PartidoEliminatorio(Integer idPartido, Equipo equipoLocal, Equipo equipoRival) {
		super(idPartido, equipoLocal, equipoRival);
		this.penalesLocales = 0;
		this.penalesVisitante = 0;
	}

	

	public Integer getPenalesLocales() {
		return penalesLocales;
	}

	public void setPenalesLocales(Integer penalesLocales) {
		this.penalesLocales = penalesLocales;
	}

	public Integer getPenalesVisitante() {
		return penalesVisitante;
	}

	public void setPenalesVisitante(Integer penalesVisitante) {
		this.penalesVisitante = penalesVisitante;
	}


	public void registrarResultados(Integer golesLocal, Integer golesRival, Integer penalesLocales, Integer penalesrival) {
		super.setGolesLocal(golesLocal);
		super.setGolesRival(golesRival);
		this.penalesLocales = penalesLocales;
		this.penalesVisitante = penalesrival;
		
	}
	
	@Override
	public TipoResultado obtenerResultado(Partido partido) {
		TipoResultado resultado = null;
		if(partido.getGolesLocal()>partido.getGolesRival()) {
			resultado = TipoResultado.GANA_LOCAL;
		}
		else if(partido.getGolesLocal()<partido.getGolesRival()){
			resultado = TipoResultado.GANA_RIVAL;
		}
		else if(partido.getGolesLocal().equals(partido.getGolesRival())) {
			if(((PartidoEliminatorio)partido).getPenalesLocales()>((PartidoEliminatorio)partido).getPenalesVisitante()) {
				resultado = TipoResultado.GANA_LOCAL;
			}else {
				resultado = TipoResultado.GANA_RIVAL;
			}
		}
			
		return resultado;
	}
	
	

//	public void registrarResultado(TorneoDeEquipos torneo, Partido partido, Integer golesLocal, Integer golesVisitantes,
//			Integer penalesConvertidosLocal, Integer penalesConvertidosVisitante) throws PartidoNoEncontrado {
//
//		Partido partidoBuscado = torneo.buscarPartido(partido);
//		partidoBuscado.setGolesLocal(golesLocal);
//		partidoBuscado.setGolesRival(golesVisitantes);
//		Integer golesDeLocal = partidoBuscado.getGolesLocal();
//		Integer golesDeRival = partidoBuscado.getGolesRival();
//
//		if (golesDeLocal > golesDeRival) {
//			ganaLocal(partidoBuscado, golesLocal, golesVisitantes);
//		} else if (golesDeLocal < golesDeRival) {
//			ganaRival(partidoBuscado, golesLocal, golesVisitantes);
//		} else if (golesDeLocal == golesDeRival) {
//			partidoBuscado.setGolesLocal(penalesConvertidosLocal);
//			partidoBuscado.setGolesRival(penalesConvertidosVisitante);
//			registrarGanador(partidoBuscado, partidoBuscado.getGolesLocal(), partidoBuscado.getGolesRival());
//		}
//
//	}
//
//	private void registrarGanador(Partido partidoBuscado,Integer golesLocal, Integer golesRival) {
//		if(golesLocal>golesRival) {
//			ganaLocal(partidoBuscado, golesLocal, golesRival);
//		}else {
//			ganaRival(partidoBuscado, golesLocal, golesRival);
//		}
//		
//	}
//	
//	public Equipo ObtenerResultados(TorneoDeEquipos torneo,Partido partido) throws PartidoNoEncontrado {
//		Equipo equipoGanador = null;
//		Partido partidoBuscado = torneo.buscarPartido(partido);
//	    Integer resultadosDelPartidoLocal = partidoBuscado.getEquipoLocal().getPuntosDelPartido();
//	    Integer resultadosDelPartidoRival= partidoBuscado.getEquipoRival().getPuntosDelPartido();
//	   if(resultadosDelPartidoLocal>resultadosDelPartidoRival) {
//		   equipoGanador = partidoBuscado.getEquipoLocal();
//	   }else {
//		   equipoGanador = partidoBuscado.getEquipoRival();
//	   }
//		return equipoGanador;
//	}
//
//	public void ganaLocal(Partido partidoBuscado, Integer golesLocal, Integer golesRival) {
//		partidoBuscado.getEquipoLocal().setPuntosDelPartido(partidoBuscado.getEquipoLocal().getPuntosDelPartido()+3);
//		partidoBuscado.getEquipoLocal().setGolesAFavor(golesLocal);
//		partidoBuscado.getEquipoLocal().setGolesEncontra(golesRival);
//		partidoBuscado.setTipoResultado(TipoResultado.GANA_LOCAL);
//	}
//	
//	public void ganaRival(Partido partidoBuscado, Integer golesLocal, Integer golesRival) {
//		partidoBuscado.getEquipoRival().setPuntosDelPartido(partidoBuscado.getEquipoRival().getPuntosDelPartido()+3);
//		partidoBuscado.getEquipoRival().setGolesAFavor(golesRival);
//		partidoBuscado.getEquipoRival().setGolesEncontra(golesLocal);
//		partidoBuscado.setTipoResultado(TipoResultado.GANA_RIVAL);
//	}
}
