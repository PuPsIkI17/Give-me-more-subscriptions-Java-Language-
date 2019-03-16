
// Pislari Vadim
// Clasa care reprezinta intreaga baza de date
import java.util.*;

public class Database {
	private int no_nodes;
	private int max_capacity;
	private ArrayList<Instances> arrlist = new ArrayList<Instances>();
	private Map<String, Nods> map_nodes = new HashMap<String, Nods>();

	// constructor
	public Database(String no_nodes, String max_capacity) {
		this.no_nodes = Integer.parseInt(no_nodes);
		this.max_capacity = Integer.parseInt(max_capacity);
		Instances first_nod = new Instances();
		arrlist.add(first_nod);
	}

	// adaugarea unui nod
	public void add_nod(Nods nod, String entity) {
		map_nodes.put(entity, nod);
	}

	// adaugarea unei inserari
	public void add_full_insertion(Insertions insert) {
		// for - ul se utilizeaza pentru a gasi primul nod care are
		// nr ul de inserari mai mic decat nr maxim de inserari
		for (int l = 0; l != no_nodes; l++) {
			Instances first_nod = arrlist.get(l);

			// se afla rf-ul maxim din-tre toate entitatile care are
			// o instant in acest nod
			int max_rf = first_nod.max_rf();
			if (insert.get_rf() > max_rf)
				max_rf = insert.get_rf();

			if (first_nod.get_nmb_insers() < max_capacity) {

				first_nod.get_list_insert().add(insert);
				first_nod.inc_insers();

				// se adauga inserarea in noduri
				for (int i = l + 1; i < l + 1 + max_rf; i++) {
					Instances clone;
					if (arrlist.size() <= i) {
						clone = new Instances();
						arrlist.add(clone);
					} else
						clone = arrlist.get(i);
					Insertions cl_ins = insert.cloning();
					cl_ins.set_rf(cl_ins.get_rf() - i + l);
					clone.get_list_insert().add(cl_ins);
					clone.inc_insers();
					clone.actualize_rf();
				}
				no_nodes++;
				break;
			}
		}
	}

	// Regasire instanta
	public void get(String entity, String val1) {

		// calcularea nr de noduri din baza de date
		int act_rf = calculare_act();
		Insertions ins2 = null;
		Nods act_nod2 = null;
		String entt = null;
		// se trece prin toata baza de date pentru a gasi entitatea necesara
		for (int i = 0; i <= act_rf; i++) {
			int index_nod = i + 1;
			Instances nod = arrlist.get(i);

			for (int k = nod.get_list_insert().size() - 1; k >= 0; k--) {
				Insertions ins = nod.get_list_insert().get(k);
				Set<Map.Entry<String, Nods>> st = map_nodes.entrySet();

				for (Map.Entry<String, Nods> me : st) {
					if (ins.get_entity().equals(me.getKey())) {
						Nods act_nod = me.getValue();
						if (act_nod.get_rf() <= 0)
							continue;
						// verificarea daca s-a gasit entitatea necesara
						if (ins.get_arrlist().get(0).get_value().equals(val1) && me.getKey().equals(entity)) {
							entt = me.getKey();
							System.out.print("Nod" + index_nod + " ");
							ins2 = ins;
							act_nod2 = act_nod;
						}
					}
				}
			}
		}

		// verificarea daca s-a gasit ceva
		if (ins2 != null && act_nod2 != null) {
			System.out.print(entt);
			// afisarea
			for (int j = 0; j != act_nod2.get_no_attributes(); j++) {
				Nods_Arguments arg = act_nod2.get_arrlist().get(j);
				System.out.print(" " + arg.get_entity() + ":");
				ins2.get_arrlist().get(j).print();
			}
		} else {
			// nu s-a gasit
			System.out.println("NO INSTANCE FOUND");
			return;
		}
		System.out.println();
	}

	// Actualizare instanta
	public void update(String entity, String val1, ArrayList<Nods_Arguments> newa) {

		// calcularea nr de noduri din baza de date
		int act_rf = calculare_act();
		for (int i = 0; i <= act_rf; i++) {
			Instances nod = arrlist.get(i);
			// se trece prin toata baza de date pentru a gasi entitatea necesara
			for (int k = nod.get_list_insert().size() - 1; k >= 0; k--) {
				Insertions ins = nod.get_list_insert().get(k);
				Set<Map.Entry<String, Nods>> st = map_nodes.entrySet();

				for (Map.Entry<String, Nods> me : st) {
					if (ins.get_entity().equals(me.getKey())) {
						Nods act_nod = me.getValue();
						if (act_nod.get_rf() <= 0)
							continue;

						// regasirea instantei care trebuie modificata
						if (ins.get_arrlist().get(0).get_value().equals(val1) && me.getKey().equals(entity)) {
							for (int j = 0; j != act_nod.get_no_attributes(); j++) {
								Nods_Arguments arg = act_nod.get_arrlist().get(j);
								for (int m = 0; m != newa.size(); m++) {
									Nods_Arguments aux = newa.get(m);
									if (arg.get_entity().equals(aux.get_entity())) {
										// modificarea valorii
										ins.get_arrlist().get(j).set_value(aux.get_value());
										// muta de pe pozitia k pe pozitia 0
										nod.actualize(k, 0);
									}
								}
							}
						}
					}
				}
			}
		}
	}

	// Stergere instanta:
	public void delete(String entity, String val1) {
		int r = 0;
		// calcularea nr de noduri din baza de date
		int act_rf = calculare_act();
		for (int i = 0; i <= act_rf; i++) {
			Instances nod = arrlist.get(i);
			// se trece prin toata baza de date pentru a gasi entitatea necesara
			for (int k = nod.get_list_insert().size() - 1; k >= 0; k--) {
				Insertions ins = nod.get_list_insert().get(k);
				Set<Map.Entry<String, Nods>> st = map_nodes.entrySet();

				for (Map.Entry<String, Nods> me : st) {
					if (ins.get_entity().equals(me.getKey())) {
						Nods act_nod = me.getValue();
						if (act_nod.get_rf() <= 0)
							continue;
						// regasirea entiatatea are trebuie stearsa
						if (ins.get_arrlist().get(0).get_value().equals(val1) && me.getKey().equals(entity)) {
							nod.delete(k);
							k--;
							r++;
						}
					}
				}
			}
		}
		if (r == 0) {
			System.out.println("NO INSTANCE TO DELETE");
		}
	}

	// Printare baza de date
	public void print() {
		// calcularea nr de noduri din baza de date
		int act_rf = calculare_act();
		// verificarea ca baza de date sa nu fie goala
		if (act_rf == 0) {
			System.out.println("EMPTY DB");
			return;
		}
		for (int i = 0; i <= act_rf; i++) {
			int index_nod = i + 1;
			System.out.println("Nod" + index_nod);
			Instances nod = arrlist.get(i);
			// se trece prin toata baza de date pentru a se afisa
			for (int k = nod.get_list_insert().size() - 1; k >= 0; k--) {
				Insertions ins = nod.get_list_insert().get(k);
				Set<Map.Entry<String, Nods>> st = map_nodes.entrySet();

				for (Map.Entry<String, Nods> me : st) {
					if (ins.get_entity().equals(me.getKey())) {
						Nods act_nod = me.getValue();
						if (act_nod.get_rf() <= 0)
							continue;
						// afisarea denumirii entitatii
						System.out.print(me.getKey());
						for (int j = 0; j != act_nod.get_no_attributes(); j++) {
							Nods_Arguments arg = act_nod.get_arrlist().get(j);
							// afisarea argumentului
							System.out.print(" " + arg.get_entity() + ":");
							// afisarea valorii
							ins.get_arrlist().get(j).print();
						}
						System.out.println();
					}
				}
			}
		}
	}

	// calcularea nr de noduri din baza de date
	public int calculare_act() {
		int act_rf = 0;
		for (int i = 0; i != arrlist.size(); i++) {
			if (arrlist.get(i).get_nmb_insers() > 0)
				act_rf = i;
		}
		return act_rf;
	}

	// bonus stergerea instantei mai vechi de un anumit timp
	public void clean(String entity, String val1) {
		long time = Long.parseLong(val1);
		for (Instances list : arrlist) {
			if (list.get_nmb_insers() > 0) {
				list.cleanNode(time);
				list.actualize_rf();
			}
		}
	}

	public Map<String, Nods> getmap() {
		return map_nodes;
	}
}
