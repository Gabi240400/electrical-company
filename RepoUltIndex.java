import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.Vector;

public class RepoUltIndex {
	Vector<UltIndex> repo_ind;
	
	//constructor
	public RepoUltIndex(){
		this.repo_ind = new Vector<UltIndex>();
		citire_fisier_ind();
	}
	
	//returneaza vectorul cu ultimii indecsi ai fiecarui client
	public Vector<UltIndex> vec(){
		return this.repo_ind;
	}
	
	//returneaza lungimea vectorului cu ultimii indecsi ai fiecarui client
	public int lung() {
		return this.repo_ind.size();
	}
	
	//adauga ultimul index inregistrat al unui client
	public void adauga_index(UltIndex ind) {
		int ok=0;
		for(int i=0;i<this.repo_ind.size();i++)
			if(this.repo_ind.get(i).get_id_client() == ind.get_id_client()) {
				this.update_index(ind);
				ok=1;
			}
		if(ok==0)
			this.repo_ind.add(ind);
	}
	
	//actualizeaza ultimul index inregistrat al unui client
	public void update_index(UltIndex ind) {
		for(int i=0;i<this.repo_ind.size();i++) {
			if(this.repo_ind.get(i).get_id_client() == ind.get_id_client())
				this.repo_ind.set(i, ind);
		}
	}
	
	//sterge ultimul index al unui client
	public void sterge_index(int id) {
		for (int i=0; i<this.repo_ind.size(); i++)
			if (id == this.repo_ind.get(i).get_id_client()) {
				this.repo_ind.remove(i);
				break;
				}
	}
	
	//citire din fisier
	private void citire_fisier_ind() {
		try {
			File f = new File("C:\\Users\\Gabi\\eclipse-workspace\\Electrica\\src\\UltimulIndex");
			Scanner s = new Scanner(f);
			while(s.hasNextLine()) {
				String indecsi = s.nextLine();
				String[] ind = indecsi.split(",");
				int id_client = Integer.valueOf(ind[0]);
				float index = Float.valueOf(ind[1]);
				UltIndex i = new UltIndex(id_client, index);
				this.adauga_index(i);
			}
			s.close();
		}
		catch (FileNotFoundException e) {
			System.out.println("Nu s-a putut citi din fisier.");
		}
	}
	
	//scriere in fisier
	public void scrie_fisier_ind() {
		try {
			FileWriter mw = new FileWriter("C:\\Users\\Gabi\\eclipse-workspace\\Electrica\\src\\UltimulIndex");
			for(int i = 0; i < this.repo_ind.size(); i++)
				mw.write(this.repo_ind.get(i).scrie_indecsi());
			mw.close();
		}
		catch(IOException e) {
			System.out.println("Nu s-a putut scrie in fisier.");
		}
	}
}
