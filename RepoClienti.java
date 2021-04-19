import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.Vector;

public class RepoClienti {
	Vector<Client> repo_clienti;
	
	//constructor
	public RepoClienti(){
		this.repo_clienti = new Vector<Client>();
		citire_fisier_clienti();
	}
	
	//returneaza vectorul de clienti
	public Vector<Client> vec(){
		return this.repo_clienti;
	}
	
	//returneaza numarul de clienti 
	public int lung() {
		return this.repo_clienti.size();
	}
	
	//adauga un client
	public void adauga_client(Client c) {
		try {
		for(int i=0;i<this.repo_clienti.size();i++)
			if(this.repo_clienti.get(i).get_id() == c.get_id())
				throw new IllegalArgumentException("Exista deja un client cu acest id!");
		this.repo_clienti.add(c);
		}
		catch(IllegalArgumentException e) {
			System.out.println(e);
		}
	}
	
	//actualizeaza atributele unui client in functie de id
	public void update_client(Client c) {
		for (int i=0; i<this.repo_clienti.size(); i++)
			if (c.get_id() == this.repo_clienti.get(i).get_id()) {
				this.repo_clienti.set(i, c);
				break;
				}
	}
	
	//sterge un client dupa id-ul sau
	public void sterge_client(int id) {
		for (int i=0; i<this.repo_clienti.size(); i++)
			if (id == this.repo_clienti.get(i).get_id()) {
				this.repo_clienti.remove(i);
				break;
				}
	}
	
	//citeste clientii dintr-un fisier de tip CSV
	private void citire_fisier_clienti() {
		try {
			File f = new File("C:\\Users\\Gabi\\eclipse-workspace\\Electrica\\src\\Clienti");
			Scanner s = new Scanner(f);
			while(s.hasNextLine()) {
				String clienti = s.nextLine();
				String[] client = clienti.split(",");
				int id = Integer.valueOf(client[0]);
				String nume = client[1];
				String prenume = client[2];
				String adresa = client[3];
				String oras = client[4];
				String judet = client[5];
				String cnp = client[6];
				String tip_contract  = client[7];
				Client c = new Client(id, nume, prenume, adresa, oras, judet, cnp, tip_contract);
				this.adauga_client(c);
			}
			s.close();
		}
		catch (FileNotFoundException e) {
			System.out.println("Nu s-a putut citi din fisier.");
		}
	}
	
	//scrie in fisier clientii
	public void scrie_fisier_clienti() {
		try {
			FileWriter mw = new FileWriter("C:\\Users\\Gabi\\eclipse-workspace\\Electrica\\src\\Clienti");
			for(int i = 0; i < this.repo_clienti.size(); i++)
				mw.write(this.repo_clienti.get(i).scrie_client());
			mw.close();
		}
		catch(IOException e) {
			System.out.println("Nu s-a putut scrie in fisier.");
		}
	}
}
