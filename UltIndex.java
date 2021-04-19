
public class UltIndex {
	int id_client;
	float ultimul_index;
	
	//constructor
	public UltIndex(int id_client, float ultimul_index) {
		this.id_client = id_client;
		this.ultimul_index = ultimul_index;
	}
	
	//getteri
	public int get_id_client() {
		return this.id_client;
	}
	
	public float get_ultimul_index() {
		return this.ultimul_index;
	}
	
	//setteri
	public void set_id_client(int id) {
		this.id_client = id;
	}
	
	public void set_ultimul_index(float ultimul_index) {
		this.ultimul_index = ultimul_index;
	}
	
	//metoda folosita pentru scrierea in fisier a unui obiect de tip UltIndex
	public String scrie_indecsi() {
		String i = "";
		i = String.valueOf(this.id_client)+","+String.valueOf(this.ultimul_index)+"\n";
		return i;
	}
}
