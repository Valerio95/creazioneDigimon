package it.dstech.digimon;

import java.io.Serializable;


@SuppressWarnings("serial")
public class Digimon implements Serializable, Comparable<Digimon> {
	
	private String nome;
	private StadioEvolutivo stadioEvolutivo;
	private double ATK;
	private double DEF;
	private double RES;
	
	
	public Digimon(String nome, StadioEvolutivo stadioEvolutivo, double aTK, double dEF, double rES) {
		super();
		this.nome = nome;
		this.stadioEvolutivo = stadioEvolutivo;
		ATK = aTK;
		DEF = dEF;
		RES = rES;
	}


	public Digimon() {
		super();
	}


	public Digimon(String nome, StadioEvolutivo stadioEvolutivo) {
		super();
		this.nome = nome;
		this.stadioEvolutivo = stadioEvolutivo;
	}


	public String getNome() {
		return nome;
	}


	public void setNome(String nome) {
		this.nome = nome;
	}


	public int indiceStadio() {
		if(this.stadioEvolutivo == StadioEvolutivo.BABY) {
			return 0;
		} else if (this.stadioEvolutivo == StadioEvolutivo.CHILD) {
			return 1;
		} else if (this.stadioEvolutivo == StadioEvolutivo.ADULT) {
			return 2;
		} else if (this.stadioEvolutivo == StadioEvolutivo.PERFECT) {
			return 3;
		} else if (this.stadioEvolutivo == StadioEvolutivo.ULTIMATE) {
			return 4;
		}  else return -1;
	}
	
	public StadioEvolutivo getStadioEvolutivo() {
		return stadioEvolutivo;
	}


	public void setStadioEvolutivo(StadioEvolutivo stadioEvolutivo) {
		this.stadioEvolutivo = stadioEvolutivo;
	}


	public double getATK() {
		return ATK;
	}


	public void setATK(double aTK) {
		ATK = aTK;
	}


	public double getDEF() {
		return DEF;
	}


	public void setDEF(double dEF) {
		DEF = dEF;
	}


	public double getRES() {
		return RES;
	}


	public void setRES(double rES) {
		RES = rES;
	} 
	

	public int compareTo(Digimon o) {
		if (this.ATK < o.getATK())
			return -1;
		else if (this.ATK > o.getATK()) {
			return 1;
		} 
		return 0;
	}


	@Override
	public String toString() {
		return "Digimon [nome=" + nome + ", stadioEvolutivo=" + stadioEvolutivo + ", ATK=" + ATK + ", DEF=" + DEF
				+ ", RES=" + RES + "]";
	}
	
	
	
}
