
public class Main {
	public static void main(String[] args) {
		/*
		RepoClienti r_cl = new RepoClienti();
		RepoConsum r_con = new RepoConsum();
		RepoUltIndex r_ind = new RepoUltIndex();
		Service s = new Service(r_cl, r_con, r_ind);
		Consola c = new Consola(s);
		c.run();
		*/
		RepoClienti r_cl = new RepoClienti();
		RepoConsum r_con = new RepoConsum();
		RepoUltIndex r_ind = new RepoUltIndex();
		Service s = new Service(r_cl, r_con, r_ind);
		GUI gui = new GUI(s);
		gui.run();
	}

}
