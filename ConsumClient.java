
public class ConsumClient {
	int id_client;
	int luna;
	int an;
	float index_vechi;
	float index_actual;
	boolean platit;
	
	//constructor
	public ConsumClient(int id_client, int luna, int an, float index_actual) {
		this.id_client = id_client;
		this.luna = luna;
		this.an = an;
		this.index_actual = index_actual;
	}
	
	//constructor
	public ConsumClient(int id_client, int luna, int an, float index_vechi, float index_actual, boolean platit) {
		this.id_client = id_client;
		this.luna = luna;
		this.an = an;
		this.index_vechi = index_vechi;
		this.index_actual = index_actual;
		this.platit = platit;
	}
	
	//getteri
	public int get_id_client() {
		return this.id_client;
	}
	
	public int get_luna() {
		return this.luna;
	}
	
	public int get_an() {
		return this.an;
	}
	
	public float get_index_vechi() {
		return this.index_vechi;
	}
	
	public float get_index_actual() {
		return this.index_actual;
	}
	
	public boolean get_platit() {
		return this.platit;
	}
	
	
	//setteri
	public void set_id_client(int id_client) {
		this.id_client = id_client;
	}
	
	public void set_luna(int luna) {
		this.luna = luna;
	}
	
	public void set_an(int an) {
		this.an = an;
	}
	
	public void set_index_vechi(float index_vechi) {
		this.index_vechi = index_vechi;
	}
	
	public void set_index_actual(float index_actual) {
		this.index_actual = index_actual;
	}
	
	public void set_platit(boolean platit) {
		this.platit = platit;
	}
	
	//metoda folosita pentru scrierea in fisier a unui obiect de tip consum
	public String scrie_consum() {
		String c = "";
		c = String.valueOf(this.id_client)+","+String.valueOf(this.luna)+","+String.valueOf(this.an)+","+String.valueOf(this.index_vechi)+","+String.valueOf(this.index_actual)+","+String.valueOf(this.platit)+"\n";
		return c;
	}
}
