
public class Client {
	int id;
	String nume;
	String prenume;
	String adresa;
	String oras;
	String judet;
	String cnp;
	String tip_contract;
	
	//constructor
	public Client(int id, String nume, String prenume, String adresa, String oras, String judet, String cnp, String tip_contract) {
		this.id = id;
		this.nume = nume;
		this.prenume = prenume;
		this.adresa = adresa;
		this.oras = oras;
		this.judet = judet;
		this.cnp = cnp;
		this.tip_contract = tip_contract;
	}
	
	//constructor de copier
	public Client(Client c) {
		this.id = c.get_id();
		this.nume = c.get_nume();
		this.prenume = c.get_prenume();
		this.adresa = c.get_adresa();
		this.oras = c.get_oras();
		this.judet = c.get_judet();
		this.cnp = c.get_cnp();
		this.tip_contract = c.get_tip_constract();
	}
	
	//getteri
	public int get_id() {
		return this.id;
	}
	
	public String get_nume() {
		return this.nume;
	}
	
	public String get_prenume() {
		return this.prenume;
	}
	
	public String get_adresa() {
		return this.adresa;
	}
	
	public String get_oras() {
		return this.oras;
	}
	
	public String get_judet() {
		return this.judet;
	}
	
	public String get_cnp() {
		return this.cnp;
	}
	
	public String get_tip_constract() {
		return this.tip_contract;
	}
	
	//setteri
	public void set_id(int id) {
		this.id = id;
	}
	
	public void set_nume(String nume) {
		this.nume = nume;
	}
	
	public void set_prenume(String prenume) {
		this.prenume = prenume;
	}
	
	public void set_adresa(String adresa) {
		this.adresa = adresa;
	}
	
	public void set_oras(String oras) {
		this.oras = oras;
	}
	
	public void set_judet(String judet) {
		this.judet = judet;
	}
	
	public void set_cnp(String cnp) {
		this.cnp = cnp;
	}
	
	public void set_tip_contract(String tip_contract) {
		this.tip_contract = tip_contract;
	}
	
	//metoda pentru scrierea in fisier a unui client
	public String scrie_client() {
		String c = "";
		c = String.valueOf(this.id)+","+this.nume+","+this.prenume+","+this.adresa+","+this.oras+","+this.judet+","+this.cnp+","+this.tip_contract+"\n";
		return c;
	}
	
}

