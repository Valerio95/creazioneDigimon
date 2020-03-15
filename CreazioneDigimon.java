package it.dstech.connessionedb.digimon;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CreazioneDigimon {
	static Scanner scanner=new Scanner(System.in);
public static void main(String[] args) throws SQLException, ClassNotFoundException {
	Class.forName("com.mysql.cj.jdbc.Driver");
	String password ="95asroma";
	String username = "root";
	String url = "jdbc:mysql://localhost:3306/digimon?useUnicode=true&useLegacyDatetimeCode=false&serverTimezone=UTC&createDatabaseIfNotExist=true&allowPublicKeyRetrieval=true&useSSL=false";
	Connection connessione = DriverManager.getConnection(url, username, password);
	creazioneDigimon(connessione);
	
}
private static void creazioneDigimon(Connection connessione) throws SQLException {
	String queryInserimentoDigimon= "INSERT INTO digimon (nome, attacco, difesa, resistenza ,vita ,evoluzione) VALUES (?, ?, ?, ?, ?, ?);";
	PreparedStatement prepareStatement = connessione.prepareStatement(queryInserimentoDigimon);
	System.out.println("inserisci il nome");
	prepareStatement.setString(1, scanner.nextLine());
	System.out.println("inserisci l'attacco");
	prepareStatement.setInt(2, scanner.nextInt());
	scanner.nextLine();
	System.out.println("inserisci la difesa");
	prepareStatement.setInt(3, scanner.nextInt());
	scanner.nextLine();
	System.out.println("inserisci la resitenza");
	prepareStatement.setInt(4, scanner.nextInt());
	scanner.nextLine();
	System.out.println("inserisci la vita");
	prepareStatement.setInt(5, scanner.nextInt());
	scanner.nextLine();
	System.out.println("inserisci l'evoluzione");
	prepareStatement.setString(6, scanner.nextLine());
	prepareStatement.execute();
}
private static void rimozioneDigimon(Connection connessione) throws SQLException {
	PreparedStatement prepareStatement = connessione
			.prepareStatement("select * from digimon;");
	ResultSet executeQuery = prepareStatement.executeQuery();
	System.out.println("Ecco i digimon ");
	
	while (executeQuery.next()) {
		int id =executeQuery.getInt(1);
		String nome=executeQuery.getString("nome");
		System.out.println(id+" "+nome);
		
		
	}
	String queryRimozioneDigimon= "DELETE FROM digimon WHERE id= ?";
	PreparedStatement prepareStatement2 = connessione.prepareStatement(queryRimozioneDigimon);
	System.out.println("Inseriscimi l'id del digimon");
	prepareStatement2.setInt(1, scanner.nextInt());
    prepareStatement2.execute();
    System.out.println("digimon eliminato");
}
}
