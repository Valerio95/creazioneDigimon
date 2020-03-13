package it.dstech.digimon;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;


public class GestioneDigimon{
	
	static Scanner input=new Scanner(System.in);
	static List<Digimon> listaDigimon = new ArrayList<>();

	
	public static void main(String[] args) {
		
		caricaFile();
		
		while(true){
			menu();
			int scelta=input.nextInt();
			input.nextLine();
			switch (scelta) {
				case 1:System.out.println("1. Aggiungi 2. Rimuovi");
					scelta=input.nextInt();
					input.nextLine();
					if(scelta==1) {
						aggiungiDigimon();
					} else {
						rimuoviDigimon();
					}
				break;
				case 2:
					modificaDigimon();
				break;
				case 3:
					ordinaDigimon();
					break;
				case 4:
					stampaLista();
				break;
				case 5:
					salvaSuFile();
				break;
				default:
					salvaSuFile();
					System.exit(0);
				break;
			} 
		}
	}
	
	
	public static void ordinaDigimon() {
		System.out.println("In base a quale statistica vuoi ordinare i Digimon della lista? 1.ATK 2.DEF 3.RES 4.StadioEvolutivo");
		int scelta=input.nextInt();
		
		switch (scelta) {
		case 1:
			ordinaPerAttacco();
			break;
		case 2:
			ordinaPerDifesa();
			break;
		case 3:
			ordinaPerResistenza();
			break;
		
		case 4:
			ordinaPerStadio();
			break;
		}
	}
		
	
	public static void aggiungiDigimon() {
		Digimon nuovoDigimon = new Digimon();
		StadioEvolutivo stadio = null;
		boolean condizione= true;
		
		System.out.println("Inserisci il nome del Digimon da aggiungere");
		String nome = input.nextLine();
		while(condizione) {
		System.out.println("Inserisci il suo stadio evolutivo tra quelli della lista: \r\n"
			  + "   BABY,\r\n" + 
				"	CHILD,\r\n" + 
				"	ADULT,\r\n" + 
				"	PERFECT,\r\n" + 
				"	ULTIMATE;");
		String stad = input.nextLine();
		stadio = StadioEvolutivo.valueOf(stad);
		nuovoDigimon = new Digimon( nome, stadio);
		condizione= controlloNome(nuovoDigimon);
		} 
		System.out.println("Inserisci il valore dell'attacco");
		double ATK = input.nextDouble();
		input.nextLine();
		System.out.println("Inserisci il valore della difesa");
		double DEF= input.nextDouble();
		input.nextLine();
		System.out.println("Inserisci il valore della resistenza");
		double RES= input.nextDouble();
		input.nextLine();
		
		nuovoDigimon = new Digimon( nome, stadio,  ATK,  DEF, RES);
		listaDigimon.add(nuovoDigimon);
	}

	
	public static void rimuoviDigimon(){
		boolean condizione= true;
		while(condizione){
			int indice=0;
			System.out.println("Ecco i digimon che hai aggiunto");
			for(Digimon p : listaDigimon) {
				System.out.println(indice+ "." +p);	
				indice++;
			}
			System.out.println("Quale digimon vuoi rimuovere?");
			int scelta= input.nextInt();
			input.nextLine();
			listaDigimon.remove(scelta);
			System.out.println("Vuoi rimuovere un altro digimon?");
			String siNo =  input.nextLine();
			if(siNo.equals("no")) {
				condizione=false;
			} 
		}
	}
	
	
	public static void menu() {
		System.out.println("1. Inserisci o rimuovi un Digimon");
		System.out.println("2. Modifica statistiche di un Digimon");
		System.out.println("3. Ordina Digimon");
		System.out.println("4. Stampa lista Digimon");
		System.out.println("5. Salva ");
		System.out.println("0. Esci");
	}
	
	
	public static void stampaLista() {
		for(int i=0;i<listaDigimon.size();i++) {
			System.out.println(i+"." + listaDigimon.get(i) );
		}
	}
	
	
	public static boolean controlloNome(Digimon dig){
		for (Digimon digimon : listaDigimon) {
			if(digimon.getNome().equals(dig.getNome())){
				if(	digimon.getStadioEvolutivo().equals(dig.getStadioEvolutivo())) {
				System.out.println("Esiste già un Digimon con questo nome e a questo stadio evolutivo, "
						+ "inserisci un altro stadio evolutivo");
				return true;
			}	
		}
		
	}
		return false;}
	
	
	public static void modificaDigimon() {
		System.out.println("Ecco la lista dei Digimon, quale vuoi aggiornare?");
		stampaLista();
		int sceltaDigi= input.nextInt();
		input.nextLine();
		System.out.println("Quale statistica vuoi aggiornare: 1.ATK 2.DEF 3.RES?");
		int sceltaStat=input.nextInt();
		input.nextLine();
		System.out.println("Inserisci il nuovo valore");
		switch (sceltaStat) {
		case 1:
			listaDigimon.get(sceltaDigi).setATK(input.nextDouble());
			break;
		case 2:
			listaDigimon.get(sceltaDigi).setDEF(input.nextDouble());
			break;
		case 3:
			listaDigimon.get(sceltaDigi).setRES(input.nextDouble());
			break;
		}
	}
	
	
	public static void salvaSuFile() {
		try {
			File file = new File("listaDigimon.txt");
			FileOutputStream out = new FileOutputStream(file);
			ObjectOutputStream stream = new ObjectOutputStream(out);
			stream.writeObject(listaDigimon);
			stream.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
	}
	
	
	@SuppressWarnings("unchecked")
	public static void caricaFile() {
		try {
			File file = new File("listaDigimon.txt");
			FileInputStream in = new FileInputStream(file);
			ObjectInputStream stream = new ObjectInputStream(in);
			listaDigimon = (List<Digimon>) stream.readObject();
			stream.close();
		} catch (IOException ext) {
			System.out.println("Eccezione");
			ext.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	
	public static void ordinaPerResistenza() {
		System.out.println("Vuoi ordinare per resistenza minore o maggiore? 1.Minore 2.Maggiore");
		int scelta = input.nextInt();
		if (scelta==1) {
			ordinaPerResistenzaMinore();
		} else {
			ordinaPerResistenzaMaggiore();
		}
	}
	
	
	public static void ordinaPerDifesa() {
		System.out.println("Vuoi ordinare per difesa minore o maggiore? 1.Minore 2.Maggiore");
		int scelta = input.nextInt();
		if (scelta==1) {
			ordinaPerDifesaMinore();
		} else {
			ordinaPerDifesaMaggiore();
		}
	}
	
	
	public static void ordinaPerAttacco() {
		System.out.println("Vuoi ordinare per attacco minore o maggiore? 1.Minore 2.Maggiore");
		int scelta = input.nextInt();
		if (scelta==1) {
			ordinaPerAttaccoMinore();
		} else {
			ordinaPerAttaccoMaggiore();
		}
	}
	
	
	public static void ordinaPerDifesaMaggiore() {
		Collections.sort(listaDigimon, new Comparator<Digimon>() {
			@Override
			public int compare(Digimon numero1, Digimon numero2) {
				if (numero1.getDEF() < numero2.getDEF())
					return -1;
				else if (numero1.getDEF() > numero2.getDEF()) {
					return 1;
				} 
				return 0;
			}
			
		});
		Collections.reverse(listaDigimon);
		System.out.println("Ecco la lista in ordine di difesa maggiore");
		for (Digimon digi : listaDigimon) {
			System.out.println(digi);
		}
	}
	
	
	public static void ordinaPerResistenzaMinore() {
		Collections.sort(listaDigimon, new Comparator<Digimon>() {
			@Override
			public int compare(Digimon numero1, Digimon numero2) {
				if (numero1.getRES() < numero2.getRES())
					return -1;
				else if (numero1.getRES() > numero2.getRES()) {
					return 1;
				} 
				return 0;
			}
			
		});
		System.out.println("Ecco la lista in ordine di resistenza minore");
		for (Digimon digi : listaDigimon) {
			System.out.println(digi);
		}
	}
	
	
	public static void ordinaPerResistenzaMaggiore() {
		Collections.sort(listaDigimon, new Comparator<Digimon>() {
			@Override
			public int compare(Digimon numero1, Digimon numero2) {
				if (numero1.getRES() < numero2.getRES())
					return -1;
				else if (numero1.getRES() > numero2.getRES()) {
					return 1;
				} 
				return 0;
			}
			
		});
		Collections.reverse(listaDigimon);
		System.out.println("Ecco la lista in ordine di resistenza maggiore");
		for (Digimon digi : listaDigimon) {
			System.out.println(digi);
		}
	}
	
	
	public static void ordinaPerDifesaMinore() {
		Collections.sort(listaDigimon, new Comparator<Digimon>() {
			@Override
			public int compare(Digimon numero1, Digimon numero2) {
				if (numero1.getDEF() < numero2.getDEF())
					return -1;
				else if (numero1.getDEF() > numero2.getDEF()) {
					return 1;
				} 
				return 0;
			}
			
		});
		System.out.println("Ecco la lista in ordine di difesa minore");
		for (Digimon digi : listaDigimon) {
			System.out.println(digi);
		}
	}
	
	
	public static void ordinaPerStadio() {
		Collections.sort(listaDigimon, new Comparator<Digimon>() {
			@Override
			public int compare(Digimon numero1, Digimon numero2) {
				if (numero1.indiceStadio() < numero2.indiceStadio())
					return -1;
				else if (numero1.indiceStadio() > numero2.indiceStadio()) {
					return 1;
				} 
				return 0;
			}
			
		});
		System.out.println("Ecco la lista in ordine di stadio evolutivo");
		for (Digimon digi : listaDigimon) {
			System.out.println(digi);
		}
	}
	
	
	
	public static void ordinaPerAttaccoMaggiore() {
		System.out.println("Ecco la lista in ordine di attacco maggiore");
		Collections.sort(listaDigimon);
		Collections.reverse(listaDigimon);
		for (Digimon digi : listaDigimon) {
			System.out.println(digi);
		}
	}
	

	public static void ordinaPerAttaccoMinore() {
		System.out.println("Ecco la lista in ordine di attacco minore");
		Collections.sort(listaDigimon);
		for (Digimon digi : listaDigimon) {
			System.out.println(digi);
		}
	}
	
}