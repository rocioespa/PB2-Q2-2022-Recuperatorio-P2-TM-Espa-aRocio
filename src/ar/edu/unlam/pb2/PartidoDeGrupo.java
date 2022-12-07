package ar.edu.unlam.pb2;

public class PartidoDeGrupo extends Partido {
	

	public PartidoDeGrupo(Integer idPartido, Equipo equipoLocal, Equipo equipoRival) {
		super(idPartido, equipoLocal, equipoRival);
	
	}

	@Override
	public TipoResultado obtenerResultado(Partido partido) {
		TipoResultado resultado = null;
		
		if(partido.getGolesLocal()>partido.getGolesRival()) {
			resultado = TipoResultado.GANA_LOCAL;
			
		}
		else {
			resultado = TipoResultado.GANA_RIVAL;
			
		}
		
		if(partido.getGolesLocal().equals(partido.getGolesRival()))
			resultado = TipoResultado.EMPATE;
		
		return resultado;
	}

	
	public void registrarResultados(Integer golesLocal, Integer golesRival) {
		super.setGolesLocal(golesLocal);
		super.setGolesRival(golesRival);
		
	}

	public Equipo obtenerElEquipoParaEliminatoria(Partido partido) {
		Equipo equipo = null;
		TipoResultado resultado = obtenerResultado(partido);
		if(resultado.equals(TipoResultado.GANA_LOCAL)) {
			equipo = partido.getEquipoLocal();
		}
		else if(resultado.equals(TipoResultado.GANA_RIVAL)){
			equipo = partido.getEquipoRival();
		}
		
		return equipo;
	}
	



//	public void registrarResultado(TorneoDeEquipos torneo, Partido partido, Integer golesLocal, Integer golesVisitantes) throws PartidoNoEncontrado {
//		
//		Partido partidoBuscado =  torneo.buscarPartido(partido);
//		partidoBuscado.setGolesLocal(golesLocal);
//		partidoBuscado.setGolesRival(golesVisitantes);
//		Integer golesDeLocal = partidoBuscado.getGolesLocal();
//		Integer golesDeRival = partidoBuscado.getGolesRival();
//		
//		if(golesDeLocal>golesDeRival) {
//			ganaLocal(partidoBuscado, golesLocal, golesVisitantes);
//		}else if(golesDeLocal<golesDeRival){
//			ganaRival(partidoBuscado, golesLocal, golesVisitantes);
//		}else if(golesDeLocal == golesDeRival ){
//			partidoBuscado.getEquipoLocal().setPuntosDelPartido(partidoBuscado.getEquipoLocal().getPuntosDelPartido()+1);
//			partidoBuscado.getEquipoRival().setPuntosDelPartido(partidoBuscado.getEquipoRival().getPuntosDelPartido()+1);
//			partidoBuscado.setTipoResultado(TipoResultado.EMPATE);
//		}
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
