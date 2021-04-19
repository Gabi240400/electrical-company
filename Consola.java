import java.util.Scanner;

public class Consola {
	Service s;
	
	public Consola(Service s) {
		this.s=s;
	}
	
	//citirea unui intreg de la tastatura
	public static int citire_int(String sir) {                                     
		System.out.print(sir);
		try {
			Scanner s = new Scanner(System.in);
			int i = s.nextInt();
			return i;
		}
		catch(Exception exp){
			System.out.println("Comanda nu este valida!");
			return citire_int(sir);
		}
	}
	
	//citirea unui string fara restrictii de la tastatura (nume, prenume, adresa, localitate, judet)
	public static String citire_sir(String sir) {
		System.out.print(sir);
		Scanner s = new Scanner(System.in);
		String nume = s.nextLine();
		return nume;
	}
	
	public static String citire_cnp(String sir) {
		System.out.print(sir);
		try {
			Scanner s = new Scanner(System.in);
			String cnp = s.nextLine();
			if(cnp.length() != 13)
				throw new IllegalArgumentException("CNP-ul introdus nu este valid.");
			return cnp;
		}
		catch(IllegalArgumentException e) {
			System.out.println(e);
			return citire_cnp(sir);
		}
	}
	
	public static String citire_tip(String sir) {
		System.out.print(sir);
		try {
			Scanner s = new Scanner(System.in);
			String tip = s.nextLine();
			if(tip.equals("social") || tip.equals("standard"))
				return tip;
			else
				throw new IllegalArgumentException("Acest tip de contract nu exista");
		}
		catch(IllegalArgumentException e) {
			System.out.println(e);
			return citire_cnp(sir);
		}
	}
	
	public static int citire_luna(String sir) {
		System.out.print(sir);
		try {
			Scanner s = new Scanner(System.in);
			int i = s.nextInt();
			if(i < 1 || i > 12)
				throw new IllegalArgumentException("Luna introdusa nu este valida.");
			return i;
		}
		catch(IllegalArgumentException e) {
			System.out.println(e);
			return citire_luna(sir);
		}
	}
	
	public static int citire_an(String sir) {
		System.out.print(sir);
		try {
			Scanner s = new Scanner(System.in);
			int i = s.nextInt();
			if(i < 1960 || i > 2021)
				throw new IllegalArgumentException("Anul introdus nu este valid.");
			return i;
		}
		catch(IllegalArgumentException e) {
			System.out.println(e);
			return citire_luna(sir);
		}
	}
	
	public static float citire_index(String sir) {
		System.out.print(sir);
		try {
			Scanner s = new Scanner(System.in);
			float f = s.nextFloat();
			if(f < 0)
				throw new IllegalArgumentException("Indexul nu poate fi negativ.");
			return f;
		}
		catch(IllegalArgumentException e) {
			System.out.println(e);
			return citire_index(sir);
		}
	}
	
	public static boolean citire_platit(String sir) {
		System.out.print(sir);
		try {
			Scanner s = new Scanner(System.in);
			String p = s.nextLine();
			if(p.equals("da"))
				return true;
			else
				if(p.equals("nu"))
					return false;
				else
					throw new IllegalArgumentException("Doar optiunile da si nu sunt valabile.");
		}
		catch(IllegalArgumentException e) {
			System.out.println(e);
			return citire_platit(sir);
		}
	}
	
	public static int citire_id(String sir) {
		System.out.print(sir);
		try {
			Scanner s = new Scanner(System.in);
			int i = s.nextInt();
			if(i <= 0)
				throw new IllegalArgumentException("Id-ul nu poate fi negativ.");
			return i;
		}
		catch(IllegalArgumentException e) {
			System.out.println(e);
			return citire_luna(sir);
		}
	}
	
	public static int citire_opt() {
		System.out.print("Ce optiune alegeti?");
		try {
			Scanner s = new Scanner(System.in);
			int i = s.nextInt();
			if(i < 1 || i > 17)
				throw new IllegalArgumentException("Optiunea aleasa nu este valida.");
			return i;
		}
		catch(IllegalArgumentException e) {
			System.out.println(e);
			return citire_luna("Ce optiune alegeti?");
		}
	}
	
	public static void meniu() {
		System.out.println("Alege una din urmatoarele optiuni:");
		System.out.println("1-Adauga client.");
		System.out.println("2-Update client.");
		System.out.println("3-Sterge client.");
		System.out.println("4-Adauga consum client.");
		System.out.println("5-Update consum client.");
		System.out.println("6-Sterge consum client.");
		System.out.println("7-Adauga indexul curent al unui client.");
		System.out.println("8-Update indexul curent al unui client.");
		System.out.println("9-Sterge indexul curent al unui client.");
		System.out.println("10-Abonatii si suma pe care o au de platit intr-o luna data.");
		System.out.println("11-Instiintare de plata pentru un abonat dat.");
		System.out.println("12-Abonatii care au depasit consumul minim prevazut intr-o luna data.");
		System.out.println("13-Abonatii restantieri.");
		System.out.println("14-Tabel clienti.");
		System.out.println("15-Tabel consum.");
		System.out.println("16-Tabel cu ultimii indecsi de la fiecare client.");
		System.out.println("17-Exit.");
	}
	
	//functia principala pentru citirea din consola
	public void run() {
		meniu();
		int opt = citire_opt();
		int ok = 1;
		while(ok == 1) {
			switch(opt) {
				case 1:
					int id = citire_id("Scrie id: ");
					String nume = citire_sir("Scrie nume: ");
					String prenume = citire_sir("Scrie prenume: ");
					String adresa = citire_sir("Scrie adresa(fara virgule): ");
					String oras = citire_sir("Scrie oras: ");
					String judet = citire_sir("Scrie judet: ");
					String cnp = citire_cnp("Scrie cnp: ");
					String tip = citire_tip("Scrie tipul contractului: ");
					float index = citire_index("Scrie indexul actual al noului client: ");
					this.s.adauga_cl(id, nume, prenume, adresa, oras, judet, cnp, tip, index);
					meniu();
					opt = citire_opt();
					break;
				case 2:
					int id1 = citire_id("Scrie id: ");
					String nume1 = citire_sir("Scrie noul nume: ");
					String prenume1 = citire_sir("Scrie noul prenume: ");
					String adresa1 = citire_sir("Scrie noua adresa(fara virgule): ");
					String oras1 = citire_sir("Scrie noul oras: ");
					String judet1 = citire_sir("Scrie noul judet: ");
					String cnp1 = citire_cnp("Scrie noul cnp: ");
					String tip1 = citire_tip("Scrie noul tipul de contract: ");	
					this.s.update_cl(id1, nume1, prenume1, adresa1, oras1, judet1, cnp1, tip1);
					meniu();
					opt = citire_opt();
					break;
				case 3:
					int id2 = citire_id("Scrie id: ");
					this.s.sterge_cl(id2);
					meniu();
					opt = citire_opt();
					break;
				case 4:
					int id_c = citire_id("Scrie id-ul clientului pentru care vrei sa introduci consumul lunar: ");
					int luna = citire_luna("Scrie luna: ");
					int an = citire_an("Scrie anul: ");
					float ind = citire_index("Scrie indexul curent: ");
					this.s.adauga_con(id_c, luna, an, ind);
					meniu();
					opt = citire_opt();
					break;
				case 5:
					int id_cl = citire_id("Scrie id-ul clientului pentru care vrei sa modifici consumul lunar: ");
					int l = citire_luna("Scrie noua luna: ");
					int a = citire_an("Scrie noul an: ");
					float ind_v = citire_index("Scrie indexul initial modificat: ");
					float ind_a = citire_index("Scrie indexul actual modificat: ");
					boolean p = citire_platit("Este aceasta luna platita? (da/nu) ");
					this.s.update_con(id_cl, l, a, ind_v, ind_a, p);
					meniu();
					opt = citire_opt();
					break;
				case 6:
					int id_c1 = citire_id("Scrie id-ul clientului pentru care vrei sa stergi un consum lunar: ");
					int luna1 = citire_luna("Scrie luna: ");
					int an1 = citire_an("Scrie anul: ");
					this.s.sterge_con(id_c1, luna1, an1);
					meniu();
					opt = citire_opt();
					break;
				case 7:
					int id_cc = citire_id("Scrie id-ul clientului pentru care vrei sa introduci indexul curent: ");
					float i = citire_index("Scrie indexul curent: ");
					this.s.adauga_ind(id_cc, i);
					meniu();
					opt = citire_opt();
					break;
				case 8:
					int id_cc1 = citire_id("Scrie id-ul clientului pentru care vrei sa modifici indexul curent: ");
					float i1 = citire_index("Scrie noul index curent: ");
					this.s.update_ind(id_cc1, i1);
					meniu();
					opt = citire_opt();
					break;
				case 9:
					int id_cc2 = citire_id("Scrie id-ul clientului pentru care vrei sa stergi indexul curent: ");
					this.s.sterge_ind(id_cc2);
					meniu();
					opt = citire_opt();
					break;
				case 10:
					int lun = citire_luna("Scrie luna pentru care sa se genereze facturile: ");
					int ann = citire_an("Scrie anul: ");
					System.out.print(this.s.tabel_plata_dupa_luna(lun, ann));
					meniu();
					opt = citire_opt();
					break;
				case 11:
					int id_cc3 = citire_id("Scrie id-ul clientului pentru care vrei sa se afiseze platile pe care le mai are de facut: ");
					System.out.print(this.s.tabel_plata_client(id_cc3));
					meniu();
					opt = citire_opt();
					break;
				case 12:
					int l1 = citire_luna("Scrie luna pentru care sa se afiseze cine a depasit consumul lunar prevazut in contractele sociale: ");
					int a1 = citire_an("Scrie anul: ");
					System.out.print(this.s.tabel_consum_depasit(l1, a1));
					meniu();
					opt = citire_opt();
					break;
				case 13:
					int l2 = citire_luna("Scrie luna curenta: ");
					int a2 = citire_an("Scrie anul curent: ");
					System.out.print(this.s.tabel_clienti_restantieri(l2, a2));
					meniu();
					opt = citire_opt();
					break;
				case 14:
					System.out.print(this.s.tabel_clienti());
					meniu();
					opt = citire_opt();
					break;
				case 15:
					System.out.print(this.s.tabel_consum());
					meniu();
					opt = citire_opt();
					break;
				case 16:
					System.out.print(this.s.tabel_indecsi());
					meniu();
					opt = citire_opt();
					break;
				case 17:
					ok = 0;
					this.s.actualizeaza_fisiere();
					System.out.print("La revedere!");
					break;
			}
		}
	}
}
