import java.text.DecimalFormat;

public class Service {
	RepoClienti cl;
	RepoConsum con;
	RepoUltIndex ind;
	
	//constructor
	public Service(RepoClienti cl, RepoConsum con, RepoUltIndex ind) {
		this.cl = cl;
		this.con = con;
		this.ind = ind;
	}
	
	//adauga un client nou si adauga si indexul curent pentru acest client
	public void adauga_cl(int id, String nume, String prenume, String adresa, String oras, String judet, String cnp, String tip_contract, float index) {
		Client cl = new Client(id, nume, prenume, adresa, oras, judet, cnp, tip_contract);
		this.cl.adauga_client(cl);
		UltIndex ind = new UltIndex(id, index);
		this.ind.adauga_index(ind);
	}
	
	//actualizeaza atributele unui client
	public void update_cl(int id, String nume, String prenume, String adresa, String oras, String judet, String cnp, String tip_contract) {
		Client cl = new Client(id, nume, prenume, adresa, oras, judet, cnp, tip_contract);
		this.cl.update_client(cl);
	}
	
	//sterge un client dupa id
	public void sterge_cl(int id) {
		this.cl.sterge_client(id);
	}
	
	//adauga un consum lunar pentru un client
	public void adauga_con(int id_client, int luna, int an, float index_actual) {
		float index_vechi = 0;
		for(int i=0; i<ind.lung(); i++)
			if(ind.vec().get(i).get_id_client() == id_client)
				index_vechi = ind.vec().get(i).get_ultimul_index();
		ConsumClient con = new ConsumClient(id_client, luna, an, index_vechi, index_actual, false);
		this.con.adauga_consum(con);
		this.update_ind(id_client, index_actual);
	}
	
	//actualizeaza consumul lunar al unui client
	public void update_con(int id_client, int luna, int an, float index_vechi, float index_actual, boolean platit) {
		ConsumClient con = new ConsumClient(id_client, luna, an, index_vechi, index_actual, platit);
		this.con.update_consum(con);
	}
	
	//stergere consumul lunar al unui client
	public void sterge_con(int id_client, int luna, int an) {
		this.con.sterge_consum(id_client, luna, an);
	}
	
	//adauga ultimul indice al unui client
	public void adauga_ind(int id_client, float ult_index) {
		UltIndex ind = new UltIndex(id_client, ult_index);
		this.ind.adauga_index(ind);
	}
	
	//actualizeaza ultimul indice al unui client
	public void update_ind(int id_client, float ult_index) {
		UltIndex ind = new UltIndex(id_client, ult_index);
		this.ind.update_index(ind);
	}
	
	//sterge ultimul indice al unui client
	public void sterge_ind(int id_client) {
		this.ind.sterge_index(id_client);
	}
	
	//returneaza un tabel cu numele clientului si suma pe care o are acesta de platit in luna respectiva
	public String tabel_plata_dupa_luna(int luna, int an) {
		String tabel = "";
		tabel = String.format("|%-30s|%-20s|\n","Nume client", "Plata pe "+String.valueOf(luna)+"."+String.valueOf(an));
		tabel += "|==============================|====================|\n";
		for(int i=0;i<cl.lung();i++) {
			int id_client = cl.vec().get(i).get_id();
			String tip = cl.vec().get(i).get_tip_constract();
			for(int j=0;j<con.lung();j++)
				if(id_client == con.vec().get(j).get_id_client() && luna == con.vec().get(j).get_luna() && an == con.vec().get(j).get_an()) {
					float dif = con.vec().get(j).get_index_actual()-con.vec().get(j).get_index_vechi();
					System.out.println(dif+" "+con.vec().get(j).get_index_actual()+" "+con.vec().get(j).get_index_vechi());
					if(tip.equals("standard"))
						tabel += String.format("|%-30s|%20.2f|\n", cl.vec().get(i).get_nume()+" "+cl.vec().get(i).get_prenume(), (float) (dif*0.38));
					else {
						float suma = 0;
						if (dif > 100)
							suma = (float) (25 + (dif-100)*0.5);
						else
							suma = 25;
						tabel += String.format("|%-30s|%20.2f|\n", cl.vec().get(i).get_nume()+" "+cl.vec().get(i).get_prenume(), suma);
					}
				}
		}
		return tabel;
	}
	
	//returneaza o matrice cu numele clientilor si suma pe care o au acestia de platit in luna respectiva
	public Object[][] gui_tabel_plata_dupa_luna(int luna, int an){
		Object[][] tabel = new Object [this.cl.lung()][2];
		int i_tabel = 0;
		for(int i=0;i<cl.lung();i++) {
			int id_client = cl.vec().get(i).get_id();
			String tip = cl.vec().get(i).get_tip_constract();
			for(int j=0;j<con.lung();j++)
				if(id_client == con.vec().get(j).get_id_client() && luna == con.vec().get(j).get_luna() && an == con.vec().get(j).get_an()) {
					float dif = con.vec().get(j).get_index_actual()-con.vec().get(j).get_index_vechi();
					System.out.println(dif+" "+con.vec().get(j).get_index_actual()+" "+con.vec().get(j).get_index_vechi());
					if(tip.equals("standard")) {
						DecimalFormat d = new DecimalFormat("#.00");
						tabel[i_tabel][0] = String.valueOf(cl.vec().get(i).get_nume()+" "+cl.vec().get(i).get_prenume());
						tabel[i_tabel][1] = d.format(Float.valueOf((float)(dif*0.38)));
						i_tabel++;
					}
					else {
						float suma = 0;
						if (dif > 100)
							suma = (float) (25 + (dif-100)*0.5);
						else
							suma = 25;
						DecimalFormat d = new DecimalFormat("#.00");
						tabel[i_tabel][0] = String.valueOf(cl.vec().get(i).get_nume()+" "+cl.vec().get(i).get_prenume());
						tabel[i_tabel][1] = d.format(Float.valueOf(suma));
						i_tabel++;
					}
				}
		}
		return tabel;
	}
	
	//returneaza un tabel cu clientii care au facturi restante
	public String tabel_plata_client(int id_client) {
		String tabel = "";
		tabel = String.format("|%-15s|%-15s|%-15s|\n","Luna", "An", "Suma");
		tabel += "|===============|===============|===============|\n";
		String tip = "";
		for(int i=0;i<cl.lung();i++) 
			if(cl.vec().get(i).get_id() == id_client)
				tip = cl.vec().get(i).get_tip_constract();
		for(int i=0;i<con.lung();i++)
			if(id_client == con.vec().get(i).get_id_client()) {
				if(con.vec().get(i).get_platit()==false) {
					if(tip.equals("social")) {
						float dif = con.vec().get(i).get_index_actual()-con.vec().get(i).get_index_vechi();
						float suma = 0;
						if (dif > 100)
							suma = (float) (25 + (dif-100)*0.5);
						else
							suma = 25;
						tabel += String.format("|%-15s|%-15s|%15.2f|\n",con.vec().get(i).get_luna(),con.vec().get(i).get_an(), suma);
					}
					else {
						float dif = con.vec().get(i).get_index_actual()-con.vec().get(i).get_index_vechi();
						float suma = (float) (dif*0.38);
						tabel += String.format("|%15s|%15s|%15.2f|\n",con.vec().get(i).get_luna(),con.vec().get(i).get_an(), suma);
					}
						
				}
			}
		return tabel;
	}
	
	public Object[][] gui_tabel_plata_client(int id_client) {
		Object[][] tabel = new Object[this.con.lung()][3];
		String tip = "";
		int i_tabel=0;
		for(int i=0;i<cl.lung();i++) 
			if(cl.vec().get(i).get_id() == id_client)
				tip = cl.vec().get(i).get_tip_constract();
		for(int i=0;i<con.lung();i++)
			if(id_client == con.vec().get(i).get_id_client()) {
				if(con.vec().get(i).get_platit()==false) {
					if(tip.equals("social")) {
						float dif = con.vec().get(i).get_index_actual()-con.vec().get(i).get_index_vechi();
						float suma = 0;
						if (dif > 100)
							suma = (float) (25 + (dif-100)*0.5);
						else
							suma = 25;
						tabel[i_tabel][0] = con.vec().get(i).get_luna();
						tabel[i_tabel][1] = con.vec().get(i).get_an();
						DecimalFormat d = new DecimalFormat("#.00");
						tabel[i_tabel][2] = d.format(Float.valueOf(suma));
						i_tabel++;
					}
					else {
						float dif = con.vec().get(i).get_index_actual()-con.vec().get(i).get_index_vechi();
						float suma = (float) (dif*0.38);
						tabel[i_tabel][0] = con.vec().get(i).get_luna();
						tabel[i_tabel][1] = con.vec().get(i).get_an();
						DecimalFormat d = new DecimalFormat("#.00");
						tabel[i_tabel][2] = d.format(Float.valueOf(suma));
						i_tabel++;
					}
						
				}
			}
		return tabel;
	}
	
	//returneaza clientii care au contract social si au depasit consumul maxim prevazut(100 kW) in luna data
	public String tabel_consum_depasit(int luna, int an) {
		String tabel = "";
		tabel = String.format("|%-30s|%-15s|\n", "Abonat", "Consum kW");
		tabel += "|==============================|===============|\n";
		for(int i=0;i<cl.lung();i++) {
			if(cl.vec().get(i).get_tip_constract().equals("social")) {
				int id = cl.vec().get(i).get_id();
				for(int j=0;j<con.lung();j++) {
					float dif = con.vec().get(j).get_index_actual()-con.vec().get(j).get_index_vechi();
					if(con.vec().get(j).get_id_client() == id && con.vec().get(j).get_luna() == luna && con.vec().get(j).get_an() == an && dif>100.0)
						tabel +=String.format("|%-30s|%15.2f|\n", cl.vec().get(i).get_nume()+" "+cl.vec().get(i).get_prenume(), dif);
				}
			}
		}
			
		return tabel;
	}
	
	public Object[][] gui_tabel_consum_depasit(int luna, int an) {
		Object[][] tabel = new Object[this.cl.lung()][2];
		int i_tabel=0;
		for(int i=0;i<cl.lung();i++) {
			if(cl.vec().get(i).get_tip_constract().equals("social")) {
				int id = cl.vec().get(i).get_id();
				for(int j=0;j<con.lung();j++) {
					float dif = con.vec().get(j).get_index_actual()-con.vec().get(j).get_index_vechi();
					if(con.vec().get(j).get_id_client() == id && con.vec().get(j).get_luna() == luna && con.vec().get(j).get_an() == an && dif>100.0) {
						tabel[i_tabel][0] = cl.vec().get(i).get_nume()+" "+cl.vec().get(i).get_prenume();
						DecimalFormat d = new DecimalFormat("#.00");
						tabel[i_tabel][1] = d.format(Float.valueOf(dif));
						i_tabel++;
					}
				}
			}
		}
		return tabel;
	}
	
	//returneaza un tabel cu clientii care nu si-au platit facturile in lunile anterioare
	public String tabel_clienti_restantieri(int luna_curr, int an_curr) {
		String tabel = "";
		tabel = String.format("|%-30s|%-15s|%-15s|\n","Abonat restantier", "Luna", "An");
		tabel += "|==============================|===============|===============|\n";
		for(int i=0;i<con.lung();i++) {
			int id_client = con.vec().get(i).get_id_client();
			int luna = con.vec().get(i).get_luna();
			int an = con.vec().get(i).get_an();
			if((an<an_curr || (an==an_curr && luna<luna_curr)) && con.vec().get(i).get_platit() == false)
				for(int j=0;j<cl.lung();j++)
					if(cl.vec().get(j).get_id() == id_client)
						tabel +=String.format("|%-30s|%15s|%15s|\n", cl.vec().get(j).get_nume()+" "+cl.vec().get(j).get_prenume(), luna, an);
		}
		return tabel;
	}
	
	public Object[][] gui_tabel_clienti_restantieri(int luna_curr, int an_curr) {
		Object[][] tabel = new Object[this.con.lung()][3];
		int i_index = 0;
		for(int i=0;i<con.lung();i++) {
			int id_client = con.vec().get(i).get_id_client();
			int luna = con.vec().get(i).get_luna();
			int an = con.vec().get(i).get_an();
			if((an<an_curr || (an==an_curr && luna<luna_curr)) && con.vec().get(i).get_platit() == false)
				for(int j=0;j<cl.lung();j++)
					if(cl.vec().get(j).get_id() == id_client) {
						tabel[i_index][0] = cl.vec().get(j).get_nume()+" "+cl.vec().get(j).get_prenume();
						tabel[i_index][1] = luna;
						tabel[i_index][2] = an;
						i_index++;
					}
		}
		return tabel;
	}
	
	//returneaza un tabel cu toti clientii
	public String tabel_clienti() {
		String tabel = "";
		tabel = String.format("|%-5s|%-30s|%-30s|%-15s|%-15s|%-15s|%-15s|\n","Id", "Abonat", "Adresa", "Oras", "Judet", "CNP", "tip_contract");
		tabel += "|=====|==============================|==============================|===============|===============|===============|===============|\n";
		for(int i=0;i<this.cl.lung();i++)
			tabel += String.format("|%5s|%-30s|%-30s|%-15s|%-15s|%15s|%-15s|\n",cl.vec().get(i).get_id(), cl.vec().get(i).get_nume()+" "+cl.vec().get(i).get_prenume(), cl.vec().get(i).get_adresa(), cl.vec().get(i).get_oras(), cl.vec().get(i).get_judet(), cl.vec().get(i).get_cnp(), cl.vec().get(i).get_tip_constract());
		return tabel;
	}
	
	//retuneaza o matrice cu toate atributele ale tuturor clientilor
	public Object[][] gui_tabel_clienti(){
		Object[][] tabel = new Object[this.cl.lung()][7];
		for(int i=0;i<this.cl.lung();i++) {
			tabel[i][0]=this.cl.vec().get(i).get_id();
			tabel[i][1]=this.cl.vec().get(i).get_nume()+" "+this.cl.vec().get(i).get_prenume();
			tabel[i][2]=this.cl.vec().get(i).get_adresa();
			tabel[i][3]=this.cl.vec().get(i).get_oras();
			tabel[i][4]=this.cl.vec().get(i).get_judet();
			tabel[i][5]=this.cl.vec().get(i).get_cnp();
			tabel[i][6]=this.cl.vec().get(i).get_tip_constract();
		}
		return tabel;
	}
	
	//returneaza un tabel cu fiecare consum lunar inregistrat
	public String tabel_consum() {
		String tabel = "";
		tabel = String.format("|%-10s|%-10s|%-10s|%-15s|%-15s|%-10s|\n", "Id Client", "Luna", "An", "Index Vechi", "Index Actual", "Platit");
		tabel += "|==========|==========|==========|===============|===============|==========|\n";
		for(int i=0; i<this.con.lung();i++)
			tabel += String.format("|%10s|%10s|%10s|%15s|%15s|%-10s|\n", con.vec().get(i).get_id_client(), con.vec().get(i).get_luna(), con.vec().get(i).get_an(), con.vec().get(i).get_index_vechi(), con.vec().get(i).get_index_actual(), con.vec().get(i).get_platit());
		return tabel;
	}
	
	public Object[][] gui_tabel_consum(){
		Object[][] tabel = new Object[this.con.lung()][6];
		for(int i=0;i<this.con.lung();i++) {
			tabel[i][0]=this.con.vec().get(i).get_id_client();
			tabel[i][1]=this.con.vec().get(i).get_luna();
			tabel[i][2]=this.con.vec().get(i).get_an();
			tabel[i][3]=this.con.vec().get(i).get_index_vechi();
			tabel[i][4]=this.con.vec().get(i).get_index_actual();
			tabel[i][5]=this.con.vec().get(i).get_platit();
		}
		return tabel;
	}
	
	//returneaza un tabel ultimul index inregistrat al fiecarui client
	public String tabel_indecsi() {
		String tabel = "";
		tabel = String.format("|%-10s|%-15s|\n", "Id Client", "Ultimul Index");
		tabel += "|==========|===============|\n";
		for(int i=0;i<this.ind.lung();i++)
			tabel += String.format("|%10s|%15s|\n", ind.vec().get(i).get_id_client(), ind.vec().get(i).get_ultimul_index());
		return tabel;
	}
	
	public Object[][] gui_tabel_indecsi(){
		Object[][] tabel = new Object[this.ind.lung()][2];
		for(int i=0;i<this.ind.lung();i++) {
			tabel[i][0]=this.ind.vec().get(i).get_id_client();
			tabel[i][1]=this.ind.vec().get(i).get_ultimul_index();
		}
		return tabel;
	}
	
	//scrie in fisiere toate obiectele din cei 3 vectori corespunzatori celor 3 clase
	public void actualizeaza_fisiere() {
		this.cl.scrie_fisier_clienti();
		this.con.scrie_fisier_consum();
		this.ind.scrie_fisier_ind();
	}
}
