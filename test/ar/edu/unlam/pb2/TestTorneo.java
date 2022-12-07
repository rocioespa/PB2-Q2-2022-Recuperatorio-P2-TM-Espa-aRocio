package ar.edu.unlam.pb2;

import static org.junit.Assert.*;

import org.junit.Test;

public class TestTorneo {

	@Test
	public void queSePuedaCrearUnTorneoCon32Equipos() throws Exception {
		TorneoDeEquipos torneo = new TorneoDeEquipos("");
		Equipo equipo1 = new Equipo("Catar", TipoDeGrupo.A);
		torneo.registrarEquipos(equipo1);
		torneo.registrarEquipos(new Equipo("Ecuador", TipoDeGrupo.A));
		torneo.registrarEquipos(new Equipo("Senegal", TipoDeGrupo.A));
		torneo.registrarEquipos(new Equipo("Países Bajos", TipoDeGrupo.A));
		torneo.registrarEquipos(new Equipo("Inglaterra", TipoDeGrupo.B));
		torneo.registrarEquipos(new Equipo("Irán", TipoDeGrupo.B));
		torneo.registrarEquipos(new Equipo("Estados Unidos", TipoDeGrupo.B));
		torneo.registrarEquipos(new Equipo("Gales", TipoDeGrupo.B));

		assertEquals(8, torneo.getEquipos().size());
	}

	@Test
	public void queSePuedaCrearUnPartidoDeGruposConDosEquiposDelMismoGrupo() throws Exception {
		TorneoDeEquipos torneo = new TorneoDeEquipos("");
		Equipo equipo1 = new Equipo("Catar", TipoDeGrupo.A);
		torneo.registrarEquipos(equipo1);
		Equipo equipo2 = new Equipo("Ecuador", TipoDeGrupo.A);
		torneo.registrarEquipos(equipo2);
		Equipo equipo3 = new Equipo("Senegal", TipoDeGrupo.A);
		torneo.registrarEquipos(equipo3);
		Equipo equipo4 = new Equipo("Países Bajos", TipoDeGrupo.A);
		torneo.registrarEquipos(equipo4);

		Partido partidodeGrupos = new PartidoDeGrupo(1, equipo1, equipo2);
		Partido partidodeGrupos1 = new PartidoDeGrupo(2, equipo3, equipo4);

		torneo.registrarPartido(partidodeGrupos);
		torneo.registrarPartido(partidodeGrupos1);
		assertEquals(2, torneo.getPartidos().size());
	}

	@Test
	public void queSePuedaCrearUnPartidoDeEliminatoriasConDosEquiposPertenecientesALaListaDeEquiposEnEliminatorias()
			throws Exception {
		TorneoDeEquipos torneo = new TorneoDeEquipos("");
		Equipo equipo1 = new Equipo("Catar", TipoDeGrupo.A);
		Equipo equipo2 = new Equipo("Inglaterra", TipoDeGrupo.B);
		torneo.agregarEquiposAEliminatorias(equipo1);
		torneo.agregarEquiposAEliminatorias(equipo2);

		Partido partidoEliminatorias = new PartidoEliminatorio(1, equipo1, equipo2);

		torneo.registrarPartido(partidoEliminatorias);

		assertEquals(1, torneo.getPartidos().size());

	}

	@Test(expected = PartidoYaJugado.class)
	public void queAlCrearUnPartidoDondeLosEquiposYaJugaronSeLanceUnaPartidoJugadoException() throws Exception {
		TorneoDeEquipos torneo = new TorneoDeEquipos("");
		Equipo equipo1 = new Equipo("Catar", TipoDeGrupo.A);
		torneo.registrarEquipos(equipo1);
		Equipo equipo2 = new Equipo("Ecuador", TipoDeGrupo.A);
		torneo.registrarEquipos(equipo2);
		Equipo equipo3 = new Equipo("Senegal", TipoDeGrupo.A);
		torneo.registrarEquipos(equipo3);
		Equipo equipo4 = new Equipo("Países Bajos", TipoDeGrupo.A);
		torneo.registrarEquipos(equipo4);

		Partido partidodeGrupos = new PartidoDeGrupo(1, equipo1, equipo2);
		Partido partidodeGrupos1 = new PartidoDeGrupo(2, equipo3, equipo4);

		torneo.registrarPartido(partidodeGrupos);
		torneo.registrarPartido(partidodeGrupos);

	}

	@Test(expected = EquiposDuplicados.class)
	public void queAlCrearUnPartidoDeGruposDondeElEquipoLocalEsElMismoQueElEquipoRivalSeLanceUnaEquipoDuplicadoException()
			throws Exception {
		TorneoDeEquipos torneo = new TorneoDeEquipos("");
		Equipo equipo1 = new Equipo("Catar", TipoDeGrupo.A);
		torneo.registrarEquipos(equipo1);
		Equipo equipo2 = new Equipo("Ecuador", TipoDeGrupo.A);
		torneo.registrarEquipos(equipo2);
		Equipo equipo3 = new Equipo("Senegal", TipoDeGrupo.A);
		torneo.registrarEquipos(equipo3);
		Equipo equipo4 = new Equipo("Países Bajos", TipoDeGrupo.A);
		torneo.registrarEquipos(equipo4);

		Partido partidodeGrupos = new PartidoDeGrupo(1, equipo1, equipo1);
		Partido partidodeGrupos1 = new PartidoDeGrupo(2, equipo3, equipo4);

		torneo.registrarPartido(partidodeGrupos);
		torneo.registrarPartido(partidodeGrupos1);
		assertEquals(1, torneo.getPartidos().size());
	}

	@Test
	public void queAlRegistrarElResultadoDeUnPartidoDeGruposSeAcumulenLosPuntosCorrespondientesACadaEquipo()
			throws Exception {
		final Integer golesEquipoLocal1 = 1;
		final Integer golesEquipoRival1= 3;
		final Integer golesEquipoLocal2 = 1;
		final Integer golesEquipoRival2= 2;
		/**
		 * Cada vez que un equipo gana un partido en la fase de grupos acumula 3 puntos,
		 * en caso de empate se reparten 1 punto para cada uno.
		 */
		TorneoDeEquipos torneo = new TorneoDeEquipos("");
		Equipo equipo1 = new Equipo("Catar", TipoDeGrupo.A);
		torneo.registrarEquipos(equipo1);
		Equipo equipo2 = new Equipo("Ecuador", TipoDeGrupo.A);
		torneo.registrarEquipos(equipo2);
		Equipo equipo3 = new Equipo("Inglaterra", TipoDeGrupo.B);
		torneo.registrarEquipos(equipo3);
		Equipo equipo4 = new Equipo("Irán", TipoDeGrupo.B);
		torneo.registrarEquipos(equipo4);

		Partido partidodeGrupos = new PartidoDeGrupo(1, equipo1, equipo2);
		

		torneo.registrarPartido(partidodeGrupos);
		
		
		torneo.registrarResultadoDelPartidoDeGrupo(1, golesEquipoLocal1, golesEquipoRival1);
		assertEquals(TipoResultado.GANA_RIVAL, torneo.obtenerResultadoDelPartido(1));
		assertEquals((Integer)0, partidodeGrupos.getEquipoLocal().getPuntosDelPartido());
		assertEquals((Integer)3, partidodeGrupos.getEquipoRival().getPuntosDelPartido());
	   
//		((PartidoDeGrupo)partidodeGrupos).registrarResultado(torneo,partidodeGrupos, golesEquipoLocal1, golesEquipoRival1);
//		Equipo equipoEliminatorio1 = ((PartidoDeGrupo)partidodeGrupos).ObtenerResultados(torneo,partidodeGrupos);
//		
//		((PartidoDeGrupo)partidodeGrupos).registrarResultado(torneo,partidodeGrupos1, golesEquipoLocal2, golesEquipoRival2);
//		Equipo equipoEliminatorio2 = ((PartidoDeGrupo)partidodeGrupos).ObtenerResultados(torneo,partidodeGrupos1);
//		
//		//resultados de los dos partidos:
//		System.out.println(partidodeGrupos.getTipoResultado());
//		System.out.println(partidodeGrupos1.getTipoResultado());
		
	}

	@Test 
	public void queAlObtenerElResultadoDeUnPartidoDeGruposSeaElElementoEmpateDelEnum()throws Exception {
		final Integer golesEquipoLocal1 = 1;
		final Integer golesEquipoRival1= 1;
		final Integer golesEquipoLocal2 = 1;
		final Integer golesEquipoRival2= 2;
		/**
		 * Cada vez que un equipo gana un partido en la fase de grupos acumula 3 puntos,
		 * en caso de empate se reparten 1 punto para cada uno.
		 */
		TorneoDeEquipos torneo = new TorneoDeEquipos("");
		Equipo equipo1 = new Equipo("Catar", TipoDeGrupo.A);
		torneo.registrarEquipos(equipo1);
		Equipo equipo2 = new Equipo("Ecuador", TipoDeGrupo.A);
		torneo.registrarEquipos(equipo2);
		Equipo equipo3 = new Equipo("Inglaterra", TipoDeGrupo.B);
		torneo.registrarEquipos(equipo3);
		Equipo equipo4 = new Equipo("Irán", TipoDeGrupo.B);
		torneo.registrarEquipos(equipo4);

		Partido partidodeGrupos = new PartidoDeGrupo(1, equipo1, equipo2);
		

		torneo.registrarPartido(partidodeGrupos);
		
		torneo.registrarResultadoDelPartidoDeGrupo(1, golesEquipoLocal1, golesEquipoRival1);
		assertEquals(TipoResultado.EMPATE, torneo.obtenerResultadoDelPartido(1));
	   
//		((PartidoDeGrupo)partidodeGrupos).registrarResultado(torneo,partidodeGrupos, golesEquipoLocal1, golesEquipoRival1);
//		Equipo equipoEliminatorio1 = ((PartidoDeGrupo)partidodeGrupos).ObtenerResultados(torneo,partidodeGrupos);
//		
//		((PartidoDeGrupo)partidodeGrupos).registrarResultado(torneo,partidodeGrupos1, golesEquipoLocal2, golesEquipoRival2);
//		Equipo equipoEliminatorio2 = ((PartidoDeGrupo)partidodeGrupos).ObtenerResultados(torneo,partidodeGrupos1);
//		
//		//resultados de los dos partidos:
//		
//		System.out.println(partidodeGrupos.getTipoResultado());
//		System.out.println(partidodeGrupos1.getTipoResultado());
		
	}
	
	@Test
	public void queAlObtenerElResultadoDeUnPartidoDeEliminatoriasEnCasoDeEmpateSeObtengaElEnumDelGanadorPorPenales()throws Exception {
		final Integer golesEquipoLocal1 = 1;
		final Integer golesEquipoRival1= 1;
		final Integer penalesEquipoLocal = 3;
		final Integer penalesEquipoRival = 2;
		TorneoDeEquipos torneo = new TorneoDeEquipos("");
		
		Equipo equipo1 = new Equipo("Catar", TipoDeGrupo.A);
		Equipo equipo2 = new Equipo("Inglaterra", TipoDeGrupo.B);
		torneo.agregarEquiposAEliminatorias(equipo1);
		torneo.agregarEquiposAEliminatorias(equipo2);

		Partido partidoEliminatorias = new PartidoEliminatorio(1, equipo1, equipo2);
		torneo.registrarPartido(partidoEliminatorias);
		torneo.registrarResultadoDelPartidoDeEliminatorias(1, golesEquipoLocal1, golesEquipoRival1, penalesEquipoLocal, penalesEquipoRival);
		assertEquals(TipoResultado.GANA_LOCAL, torneo.obtenerResultadoDelPartido(1));

	}
	
	@Test
	public void queAlFinalizarLaFaseDeGruposSeAgreguenLosMejores2EquiposDeCadaGrupoALaColeccionDeEquiposEnEliminatorias()
throws Exception {
		final Integer golesEquipoLocal1 = 1;
		final Integer golesEquipoRival1= 3;
		final Integer golesEquipoLocal2 = 1;
		final Integer golesEquipoRival2= 2;
		
		TorneoDeEquipos torneo = new TorneoDeEquipos("");
		Equipo equipo1 = new Equipo("Catar", TipoDeGrupo.A);
		torneo.registrarEquipos(equipo1);
		Equipo equipo2 = new Equipo("Ecuador", TipoDeGrupo.A);
		torneo.registrarEquipos(equipo2);
		Equipo equipo3 = new Equipo("Inglaterra", TipoDeGrupo.B);
		torneo.registrarEquipos(equipo3);
		Equipo equipo4 = new Equipo("Irán", TipoDeGrupo.B);
		torneo.registrarEquipos(equipo4);

		Partido partidodeGrupos = new PartidoDeGrupo(1, equipo1, equipo2);
		Partido partidodeGrupos1 = new PartidoDeGrupo(2, equipo3, equipo4);

		torneo.registrarPartido(partidodeGrupos);
		torneo.registrarPartido(partidodeGrupos1);
	
	    torneo.registrarResultadoDelPartidoDeGrupo(1, golesEquipoLocal1, golesEquipoRival1);
	    torneo.registrarResultadoDelPartidoDeGrupo(2, golesEquipoLocal2, golesEquipoRival2);
	    assertEquals(TipoResultado.GANA_RIVAL, torneo.obtenerResultadoDelPartido(1));
	    assertEquals(TipoResultado.GANA_RIVAL, torneo.obtenerResultadoDelPartido(2));
	    
	    torneo.finalizarLaFaseDeGrupos(partidodeGrupos,((PartidoDeGrupo)partidodeGrupos).obtenerElEquipoParaEliminatoria(partidodeGrupos));
	    torneo.finalizarLaFaseDeGrupos(partidodeGrupos1,((PartidoDeGrupo)partidodeGrupos).obtenerElEquipoParaEliminatoria(partidodeGrupos1));
		
	    assertEquals(2, torneo.getEquipoEliminatorias().size());
	}
	
	@Test
	public void queAlConsultarPuntajeDeEquiposDeLosGrupoSeObtenganLosEquiposOrdenadosPorGrupoAscendenteYPorPuntosDescendentemente() throws Exception {
		final Integer golesEquipoLocal1 = 1;
		final Integer golesEquipoRival1= 3;
		final Integer golesEquipoLocal2 = 1;
		final Integer golesEquipoRival2= 2;
		
		TorneoDeEquipos torneo = new TorneoDeEquipos("");
		Equipo equipo1 = new Equipo("Catar", TipoDeGrupo.A);
		torneo.registrarEquipos(equipo1);
		Equipo equipo2 = new Equipo("Ecuador", TipoDeGrupo.A);
		torneo.registrarEquipos(equipo2);
		Equipo equipo3 = new Equipo("Inglaterra", TipoDeGrupo.B);
		torneo.registrarEquipos(equipo3);
		Equipo equipo4 = new Equipo("Irán", TipoDeGrupo.B);
		torneo.registrarEquipos(equipo4);

		Partido partidodeGrupos = new PartidoDeGrupo(1, equipo1, equipo2);
		Partido partidodeGrupos1 = new PartidoDeGrupo(2, equipo3, equipo4);

		torneo.registrarPartido(partidodeGrupos);
		torneo.registrarPartido(partidodeGrupos1);
	
	    torneo.registrarResultadoDelPartidoDeGrupo(1, golesEquipoLocal1, golesEquipoRival1);
	    torneo.registrarResultadoDelPartidoDeGrupo(2, golesEquipoLocal2, golesEquipoRival2);
	    assertEquals(TipoResultado.GANA_RIVAL, torneo.obtenerResultadoDelPartido(1));
	    assertEquals(TipoResultado.GANA_RIVAL, torneo.obtenerResultadoDelPartido(2));
	    System.out.println(torneo.obtenerEquiposDeGruposOrdenados().toString());
	}

}
