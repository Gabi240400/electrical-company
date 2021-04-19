
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.Vector;

public class RepoConsum {
	Vector<ConsumClient> repo_consum;
	
	//constructor
	public RepoConsum(){
		this.repo_consum = new Vector<ConsumClient>();
		citire_fisier_consum();
	}
	
	//returneaza vectorul cu consumul lunar al fiecarui client
	public Vector<ConsumClient> vec(){
		return this.repo_consum;
	}
	
	//returneaza numarul de inregistrari de consum
	public int lung() {
		return this.repo_consum.size();
	}
	
	//adauga date despre consumul unui client intr-o anumita luna
	public void adauga_consum(ConsumClient c) {
		try {
		for(int i=0;i<this.repo_consum.size();i++)
			if(this.repo_consum.get(i).get_id_client() == c.get_id_client() && c.get_luna() == this.repo_consum.get(i).get_luna() && c.get_an() == this.repo_consum.get(i).get_an())
				throw new IllegalArgumentException("Exista deja inregistrat un consum pentru acest client in aceasta luna");
		this.repo_consum.add(c);
		}
		catch(IllegalArgumentException e) {
			System.out.println(e);
		}
	}
	
	//actualizeaza datele despre consumul unui client intr-o anumita luna
	public void update_consum(ConsumClient c) {
		for (int i=0; i<this.repo_consum.size(); i++)
			if (c.get_id_client() == this.repo_consum.get(i).get_id_client() && c.get_luna() == this.repo_consum.get(i).get_luna() && c.get_an() == this.repo_consum.get(i).get_an()) {
				this.repo_consum.insertElementAt(c, i);
				break;
				}
	}
	
	//sterge un consum pentru un client intr-o anumita luna
	public void sterge_consum(int id_client, int luna, int an) {
		for (int i=0; i<this.repo_consum.size(); i++)
			if (id_client == this.repo_consum.get(i).get_id_client() && luna == this.repo_consum.get(i).get_luna() && an == this.repo_consum.get(i).get_an()) {
				this.repo_consum.remove(i);
				break;
				}
	}
	
	//citire din fisier
	private void citire_fisier_consum() {
		try {
			File f = new File("C:\\Users\\Gabi\\eclipse-workspace\\Electrica\\src\\ConsumClienti");
			Scanner s = new Scanner(f);
			while(s.hasNextLine()) {
				String consum = s.nextLine();
				String[] c = consum.split(",");
				int id_client = Integer.valueOf(c[0]);
				int luna = Integer.valueOf(c[1]);
				int an = Integer.valueOf(c[2]);
				float index_vechi = Float.valueOf(c[3]);
				float index_actual = Float.valueOf(c[4]);
				boolean platit = Boolean.valueOf(c[5]);
				ConsumClient cc = new ConsumClient(id_client, luna, an, index_vechi, index_actual, platit);
				this.adauga_consum(cc);
			}
			s.close();
		}
		catch (FileNotFoundException e) {
			System.out.println("Nu s-a putut citi din fisier.");
		}
	}
	
	//scriere in fisier
	public void scrie_fisier_consum() {
		try {
			FileWriter mw = new FileWriter("C:\\Users\\Gabi\\eclipse-workspace\\Electrica\\src\\ConsumClienti");
			for(int i = 0; i < this.repo_consum.size(); i++)
				mw.write(this.repo_consum.get(i).scrie_consum());
			mw.close();
		}
		catch(IOException e) {
			System.out.println("Nu s-a putut scrie in fisier.");
		}
	}
}
