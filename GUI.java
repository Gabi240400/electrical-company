import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.ScrollPane;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.*;


public class GUI {
	Service s;
	
	public GUI(Service s) {
		this.s = s;
	}
	
	public void run() {
		JFrame frame = new JFrame("Electrica");
		frame.setSize(600, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		
		JLayeredPane panel = new JLayeredPane();
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		
		JButton b1 = new JButton("Adauga un client nou");
		b1.setBounds(10,10,170,21);
		panel.add(b1);
		
		JButton b2 = new JButton("Adauga consum lunar");
		b2.setBounds(10,40,170,21);
		panel.add(b2);
		
		JButton b3 = new JButton("Tabel clienti");
		b3.setBounds(10,70,170,21);
		panel.add(b3);
		
		JButton b4 = new JButton("Tabel consum lunar");
		b4.setBounds(10,100,170,21);
		panel.add(b4);
		
		JButton b5 = new JButton("<html>Ultimul index al fiecarui client</html>");
		b5.setBounds(10,130,170,40);
		panel.add(b5);
		
		JButton b6 = new JButton("<html>Factura pentru toti abonatii</html>");
		b6.setBounds(10,179,170,40);
		panel.add(b6);
		
		JButton b7 = new JButton("<html>Facturi restante pentru fiecare abonat</html>");
		b7.setBounds(10,228,170,40);
		panel.add(b7);
		
		JButton b8 = new JButton("<html>Abonatii care au depasit consumul maxim prevazut</html>");
		b8.setBounds(10,277,170,52);
		panel.add(b8);
		
		JButton b9 = new JButton("Abonatii restantieri");
		b9.setBounds(10,338,170,21);
		panel.add(b9);
		
		JButton b10 = new JButton("Salveaza!");
		b10.setBounds(410,420,140,21);
		panel.add(b10);
		
		Component[] components = {b1,b2,b3,b4,b5,b6,b7,b8,b9,b10};
		
		b1.addMouseListener(new MouseAdapter( ) {
			@Override
			public void mouseClicked (MouseEvent m) {
				Component[] comps = panel.getComponents();
				for(Component c1 : comps) {
					boolean ok = false;
					for(Component c2 : components)
						if(c2.equals(c1))
							ok = true;
					if(ok == false)
						panel.remove(c1);
				}
				panel.revalidate();
				panel.repaint();
				JLabel l_id = new JLabel("Id client");
				l_id.setBounds(200,10,140,21);
				panel.add(l_id);
				JLabel l_nume = new JLabel("Nume");
				l_nume.setBounds(200,40,140,21);
				panel.add(l_nume);
				JLabel l_prenume = new JLabel("Prenume");
				l_prenume.setBounds(200,70,140,21);
				panel.add(l_prenume);
				JLabel l_adresa = new JLabel("Adresa");
				l_adresa.setBounds(200,100,140,21);
				panel.add(l_adresa);
				JLabel l_oras = new JLabel("Oras");
				l_oras.setBounds(200,130,140,21);
				panel.add(l_oras);
				JLabel l_judet = new JLabel("Judet");
				l_judet.setBounds(200,160,140,21);
				panel.add(l_judet);
				JLabel l_cnp = new JLabel("CNP");
				l_cnp.setBounds(200,190,140,21);
				panel.add(l_cnp);
				JLabel l_tip = new JLabel("Tipul contractului");
				l_tip.setBounds(200,220,140,21);
				panel.add(l_tip);
				String[] tip = {"standard", "social"};
				JComboBox<String> cb_tip = new JComboBox<String>(tip);
				cb_tip.setBounds(350,220,140,21);
				panel.add(cb_tip);
				JLabel l_index = new JLabel("Index actual");
				l_index.setBounds(200,250,140,21);
				panel.add(l_index);
				
				JTextField t_id = new JTextField();
				t_id.setBounds(350,10,140,21);
				panel.add(t_id);
				JTextField t_nume = new JTextField();
				t_nume.setBounds(350,40,140,21);
				panel.add(t_nume);
				JTextField t_prenume = new JTextField();
				t_prenume.setBounds(350,70,140,21);
				panel.add(t_prenume);
				JTextField t_adresa = new JTextField();
				t_adresa.setBounds(350,100,140,21);
				panel.add(t_adresa);
				JTextField t_oras = new JTextField();
				t_oras.setBounds(350,130,140,21);
				panel.add(t_oras);
				JTextField t_judet = new JTextField();
				t_judet.setBounds(350,160,140,21);
				panel.add(t_judet);
				JTextField t_cnp = new JTextField();
				t_cnp.setBounds(350,190,140,21);
				panel.add(t_cnp);
				JTextField t_index = new JTextField();
				t_index.setBounds(350,250,140,21);
				panel.add(t_index);
				
				JButton b_trimite = new JButton("Trimite!");
				b_trimite.setBounds(390,290,100,21);
				panel.add(b_trimite);
				
				JLabel l_adaugat = new JLabel("<html><font color = red>Clientul a fost adaugat!</html>");
				JLabel l_eroare = new JLabel("<html><font color = red>Una sau mai multe date introduse nu sunt corecte!</html>");
				l_adaugat.setBounds(200,320,140,70);
				l_eroare.setBounds(200,320,140,70);
				
				b_trimite.addMouseListener(new MouseAdapter( ) {
					@Override
					public void mouseClicked (MouseEvent m) {
						for(Component c : panel.getComponents()) {
							if(c.equals(l_adaugat))
								panel.remove(l_adaugat);
							if(c.equals(l_eroare))
								panel.remove(l_eroare);
						}
						try {
							String s_id = t_id.getText();
							String nume = t_nume.getText();
							String prenume = t_prenume.getText();
							String adresa = t_adresa.getText();
							String oras = t_oras.getText();
							String judet = t_judet.getText();
							String cnp = t_cnp.getText();
							String tip = (String) cb_tip.getSelectedItem();
							String s_ind = t_index.getText();
							int id = Integer.valueOf(s_id);
							if(cnp.length() != 13)
								throw new IllegalArgumentException("CNP incorect.");
							float ind = Float.valueOf(s_ind);
							s.adauga_cl(id,nume,prenume,adresa,oras,judet,cnp,tip,ind);
							panel.add(l_adaugat);
						}
						catch(Exception e) {
							panel.add(l_eroare);
						}
					}
				});
			}
		});
		
		b2.addMouseListener(new MouseAdapter( ) {
			@Override
			public void mouseClicked (MouseEvent m) {
				Component[] comps = panel.getComponents();
				for(Component c1 : comps) {
					boolean ok = false;
					for(Component c2 : components)
						if(c2.equals(c1))
							ok = true;
					if(ok == false)
						panel.remove(c1);
				}
				panel.revalidate();
				panel.repaint();
				
				JLabel l_id = new JLabel("Id client");
				l_id.setBounds(200,10,140,21);
				panel.add(l_id);
				JLabel l_luna = new JLabel("Luna");
				l_luna.setBounds(200,40,140,21);
				panel.add(l_luna);
				JLabel l_an = new JLabel("An");
				l_an.setBounds(200,70,140,21);
				panel.add(l_an);
				JLabel l_index = new JLabel("Index actual");
				l_index.setBounds(200,100,140,21);
				panel.add(l_index);
				
				JTextField t_id = new JTextField();
				t_id.setBounds(350,10,140,21);
				panel.add(t_id);
				String[] luna = {"1","2","3","4","5","6","7","8","9","10","11","12"};
				JComboBox<String> cb_luna = new JComboBox<String>(luna);
				cb_luna.setBounds(350,40,50,21);
				panel.add(cb_luna);
				JTextField t_an = new JTextField();
				t_an.setBounds(350,70,140,21);
				panel.add(t_an);
				JTextField t_index = new JTextField();
				t_index.setBounds(350,100,140,21);
				panel.add(t_index);
				
				JButton b_trimite = new JButton("Trimite!");
				b_trimite.setBounds(390,140,100,21);
				panel.add(b_trimite);
				
				JLabel l_adaugat = new JLabel("<html><font color = red>Consumul a fost inregsitrat</html>");
				JLabel l_eroare = new JLabel("<html><font color = red>Una sau mai multe date introduse nu sunt corecte!</html>");
				l_adaugat.setBounds(200,170,140,70);
				l_eroare.setBounds(200,170,140,70);	
				
				b_trimite.addMouseListener(new MouseAdapter( ) {
					@Override
					public void mouseClicked (MouseEvent m) {
						for(Component c : panel.getComponents()) {
							if(c.equals(l_adaugat))
								panel.remove(l_adaugat);
							if(c.equals(l_eroare))
								panel.remove(l_eroare);
						}
						try {
							String s_id = t_id.getText();
							String s_luna = (String) cb_luna.getSelectedItem();
							String s_an = t_an.getText();					
							String s_ind = t_index.getText();
							
							int id = Integer.valueOf(s_id);
							int luna = Integer.valueOf(s_luna);
							int an = Integer.valueOf(s_an);
							float ind = Float.valueOf(s_ind);
							if(an < 1960 || an > 2021)
								throw new IllegalArgumentException("Anul introdus nu este valid.");
							if(ind < 0)
								throw new IllegalArgumentException("Indexul nu poate fi negativ.");
							s.adauga_con(id, luna, an, ind);
							panel.add(l_adaugat);
						}
						catch(Exception e) {
							panel.add(l_eroare);
						}
					}
				});
				
			}
		});
		
		b3.addMouseListener(new MouseAdapter( ) {
			@Override
			public void mouseClicked (MouseEvent m) {
				Component[] comps = panel.getComponents();
				for(Component c1 : comps) {
					boolean ok = false;
					for(Component c2 : components)
						if(c2.equals(c1))
							ok = true;
					if(ok == false)
						panel.remove(c1);
				}
				panel.revalidate();
				panel.repaint();
				Object[] col = {"Id", "Abonat", "Adresa", "Oras", "Judet", "CNP", "tip_contract"};
				Object[][] elem = s.gui_tabel_clienti();
				JTable tabel = new JTable(elem, col);
				JScrollPane s = new JScrollPane(tabel);
				s.setBounds(200,10,300,300);
				panel.add(s);
			}
		});
		
		b4.addMouseListener(new MouseAdapter( ) {
			@Override
			public void mouseClicked (MouseEvent m) {
				Component[] comps = panel.getComponents();
				for(Component c1 : comps) {
					boolean ok = false;
					for(Component c2 : components)
						if(c2.equals(c1))
							ok = true;
					if(ok == false)
						panel.remove(c1);
				}
				panel.revalidate();
				panel.repaint();
				Object[] col = {"Id Client", "Luna", "An", "Index Vechi", "Index Actual", "Platit"};
				Object[][] elem = s.gui_tabel_consum();
				JTable tabel = new JTable(elem, col);
				JScrollPane s = new JScrollPane(tabel);
				s.setBounds(200,10,300,300);
				panel.add(s);
			}
		});
		
		b5.addMouseListener(new MouseAdapter( ) {
			@Override
			public void mouseClicked (MouseEvent m) {
				Component[] comps = panel.getComponents();
				for(Component c1 : comps) {
					boolean ok = false;
					for(Component c2 : components)
						if(c2.equals(c1))
							ok = true;
					if(ok == false)
						panel.remove(c1);
				}
				panel.revalidate();
				panel.repaint();
				Object[] col = {"Id Client", "Ultimul Index"};
				Object[][] elem = s.gui_tabel_indecsi();
				JTable tabel = new JTable(elem, col);
				JScrollPane s = new JScrollPane(tabel);
				s.setBounds(200,10,300,300);
				panel.add(s);
			}
		});
		
		b6.addMouseListener(new MouseAdapter( ) {
			@Override
			public void mouseClicked (MouseEvent m) {
				Component[] comps = panel.getComponents();
				for(Component c1 : comps) {
					boolean ok = false;
					for(Component c2 : components)
						if(c2.equals(c1))
							ok = true;
					if(ok == false)
						panel.remove(c1);
				}
				panel.revalidate();
				panel.repaint();
				
				JLabel l_luna = new JLabel("Luna");
				l_luna.setBounds(200,10,140,21);
				panel.add(l_luna);
				JLabel l_an = new JLabel("An");
				l_an.setBounds(200,40,140,21);
				panel.add(l_an);
				
				String[] luna = {"1","2","3","4","5","6","7","8","9","10","11","12"};
				JComboBox<String> cb_luna = new JComboBox<String>(luna);
				cb_luna.setBounds(350,10,50,21);
				panel.add(cb_luna);
				JTextField t_an = new JTextField();
				t_an.setBounds(350,40,140,21);
				panel.add(t_an);
				
				JButton b_trimite = new JButton("Genereaza facturi!");
				b_trimite.setBounds(350,70,140,21);
				panel.add(b_trimite);
				
				JLabel l_eroare = new JLabel("<html><font color = red>Una sau mai multe date introduse nu sunt corecte!</html>");
				l_eroare.setBounds(200,170,140,70);	
				
				b_trimite.addMouseListener(new MouseAdapter( ) {
					@Override
					public void mouseClicked (MouseEvent m) {
						for(Component c : panel.getComponents()) {
							if(c.equals(l_eroare))
								panel.remove(l_eroare);
						}
						try {
							String s_luna = cb_luna.getSelectedItem().toString();
							String s_an = t_an.getText();					
							int luna = Integer.parseInt(s_luna);
							int an = Integer.parseInt(s_an);
							if(an < 1960 || an > 2021)
								throw new IllegalArgumentException("Anul introdus nu este valid.");
							Object[] col = {"Nume client", "Plata care trebuie efectuata"};
							Object[][] elem = s.gui_tabel_plata_dupa_luna(luna, an);
							JTable tabel = new JTable(elem, col);
							JScrollPane s = new JScrollPane(tabel);
							s.setBounds(200,10,300,300);
							Component[] comps = panel.getComponents();
							for(Component c1 : comps) {
								boolean ok = false;
								for(Component c2 : components)
									if(c2.equals(c1))
										ok = true;
								if(ok == false)
									panel.remove(c1);
							}
							panel.add(s);
						}
						catch(Exception e) {
							panel.add(l_eroare);
						}
					}
				});
			}
		});
		
		b7.addMouseListener(new MouseAdapter( ) {
			@Override
			public void mouseClicked (MouseEvent m) {
				Component[] comps = panel.getComponents();
				for(Component c1 : comps) {
					boolean ok = false;
					for(Component c2 : components)
						if(c2.equals(c1))
							ok = true;
					if(ok == false)
						panel.remove(c1);
				}
				panel.revalidate();
				panel.repaint();
				
				JLabel l_id = new JLabel("Id client");
				l_id.setBounds(200,10,140,21);
				panel.add(l_id);
				
				JTextField t_id = new JTextField();
				t_id.setBounds(350,10,140,21);
				panel.add(t_id);
				
				JButton b_trimite = new JButton("Verifica!");
				b_trimite.setBounds(390,40,100,21);
				panel.add(b_trimite);
				
				JLabel l_eroare = new JLabel("<html><font color = red>Una sau mai multe date introduse nu sunt corecte!</html>");
				l_eroare.setBounds(200,170,140,70);	
				
				b_trimite.addMouseListener(new MouseAdapter( ) {
					@Override
					public void mouseClicked (MouseEvent m) {
						for(Component c : panel.getComponents()) {
							if(c.equals(l_eroare))
								panel.remove(l_eroare);
						}
						try {
							String s_id = t_id.getText();
							int id = Integer.valueOf(s_id);
							Object[] col = {"Luna", "An", "Suma"};
							Object[][] elem = s.gui_tabel_plata_client(id);
							JTable tabel = new JTable(elem, col);
							JScrollPane s = new JScrollPane(tabel);
							s.setBounds(200,10,300,300);
							Component[] comps = panel.getComponents();
							for(Component c1 : comps) {
								boolean ok = false;
								for(Component c2 : components)
									if(c2.equals(c1))
										ok = true;
								if(ok == false)
									panel.remove(c1);
							}
							panel.add(s);
						}
						catch(Exception e) {
							panel.add(l_eroare);
						}
					}
				});
			}
		});
		
		b8.addMouseListener(new MouseAdapter( ) {
			@Override
			public void mouseClicked (MouseEvent m) {
				Component[] comps = panel.getComponents();
				for(Component c1 : comps) {
					boolean ok = false;
					for(Component c2 : components)
						if(c2.equals(c1))
							ok = true;
					if(ok == false)
						panel.remove(c1);
				}
				panel.revalidate();
				panel.repaint();
				
				JLabel l_luna = new JLabel("Luna");
				l_luna.setBounds(200,10,140,21);
				panel.add(l_luna);
				JLabel l_an = new JLabel("An");
				l_an.setBounds(200,40,140,21);
				panel.add(l_an);
				
				String[] luna = {"1","2","3","4","5","6","7","8","9","10","11","12"};
				JComboBox<String> cb_luna = new JComboBox<String>(luna);
				cb_luna.setBounds(350,10,50,21);
				panel.add(cb_luna);
				JTextField t_an = new JTextField();
				t_an.setBounds(350,40,140,21);
				panel.add(t_an);
				
				JButton b_trimite = new JButton("Verifica!");
				b_trimite.setBounds(390,70,100,21);
				panel.add(b_trimite);
				
				JLabel l_eroare = new JLabel("<html><font color = red>Una sau mai multe date introduse nu sunt corecte!</html>");
				l_eroare.setBounds(200,170,140,70);	
				
				b_trimite.addMouseListener(new MouseAdapter( ) {
					@Override
					public void mouseClicked (MouseEvent m) {
						for(Component c : panel.getComponents()) {
							if(c.equals(l_eroare))
								panel.remove(l_eroare);
						}
						try {
							String s_luna = cb_luna.getSelectedItem().toString();
							String s_an = t_an.getText();					
							int luna = Integer.parseInt(s_luna);
							int an = Integer.parseInt(s_an);
							if(an < 1960 || an > 2021)
								throw new IllegalArgumentException("Anul introdus nu este valid.");
							Object[] col = { "Abonat", "Consum kW"};
							Object[][] elem = s.gui_tabel_consum_depasit(luna, an);
							JTable tabel = new JTable(elem, col);
							JScrollPane s = new JScrollPane(tabel);
							s.setBounds(200,10,300,300);
							Component[] comps = panel.getComponents();
							for(Component c1 : comps) {
								boolean ok = false;
								for(Component c2 : components)
									if(c2.equals(c1))
										ok = true;
								if(ok == false)
									panel.remove(c1);
							}
							panel.add(s);
						}
						catch(Exception e) {
							panel.add(l_eroare);
						}
					}
				});
			}
		});
		
		b9.addMouseListener(new MouseAdapter( ) {
			@Override
			public void mouseClicked (MouseEvent m) {
				Component[] comps = panel.getComponents();
				for(Component c1 : comps) {
					boolean ok = false;
					for(Component c2 : components)
						if(c2.equals(c1))
							ok = true;
					if(ok == false)
						panel.remove(c1);
				}
				panel.revalidate();
				panel.repaint();
				
				JLabel l_luna = new JLabel("Luna curenta");
				l_luna.setBounds(200,10,140,21);
				panel.add(l_luna);
				JLabel l_an = new JLabel("An curent");
				l_an.setBounds(200,40,140,21);
				panel.add(l_an);
				
				String[] luna = {"1","2","3","4","5","6","7","8","9","10","11","12"};
				JComboBox<String> cb_luna = new JComboBox<String>(luna);
				cb_luna.setBounds(350,10,50,21);
				panel.add(cb_luna);
				JTextField t_an = new JTextField();
				t_an.setBounds(350,40,140,21);
				panel.add(t_an);
				
				JButton b_trimite = new JButton("Verifica!");
				b_trimite.setBounds(390,70,100,21);
				panel.add(b_trimite);
				
				JLabel l_eroare = new JLabel("<html><font color = red>Una sau mai multe date introduse nu sunt corecte!</html>");
				l_eroare.setBounds(200,170,140,70);	
				
				b_trimite.addMouseListener(new MouseAdapter( ) {
					@Override
					public void mouseClicked (MouseEvent m) {
						for(Component c : panel.getComponents()) {
							if(c.equals(l_eroare))
								panel.remove(l_eroare);
						}
						try {
							String s_luna = cb_luna.getSelectedItem().toString();
							String s_an = t_an.getText();					
							int luna = Integer.parseInt(s_luna);
							int an = Integer.parseInt(s_an);
							if(an < 1960 || an > 2021)
								throw new IllegalArgumentException("Anul introdus nu este valid.");
							Object[] col = { "Abonat restantier", "Luna", "An"};
							Object[][] elem = s.gui_tabel_clienti_restantieri(luna, an);
							JTable tabel = new JTable(elem, col);
							JScrollPane s = new JScrollPane(tabel);
							s.setBounds(200,10,300,300);
							Component[] comps = panel.getComponents();
							for(Component c1 : comps) {
								boolean ok = false;
								for(Component c2 : components)
									if(c2.equals(c1))
										ok = true;
								if(ok == false)
									panel.remove(c1);
							}
							panel.add(s);
						}
						catch(Exception e) {
							panel.add(l_eroare);
						}
					}
				});
			}
		});
		
		b10.addMouseListener(new MouseAdapter( ) {
			@Override
			public void mouseClicked (MouseEvent m) {
				s.actualizeaza_fisiere();
			}
		});
		
	}
}
