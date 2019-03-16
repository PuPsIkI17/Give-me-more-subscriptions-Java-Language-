// Pislari Vadim
// Clasa care reprezinta totalitatea instantelor dintr-un nod 

import java.util.*;

public class Instances {
	private int insertions;
	private ArrayList<Insertions> list_insert = new ArrayList<>();

	public Instances() {
		this.insertions = 0;
	}

	public int get_nmb_insers() {
		return insertions;
	}

	public void inc_insers() {
		insertions++;
	}

	public ArrayList<Insertions> get_list_insert() {
		return list_insert;
	}

	// actualizeaza rf-ului
	public void actualize_rf() {
		// calcularea numarului de inserari
		insertions = 0;
		for (int k = list_insert.size() - 1; k >= 0; k--) {
			Insertions ins = list_insert.get(k);
			if (ins.get_rf() > 0)
				insertions++;
		}

		// calcularea noului rf
		for (int k = list_insert.size() - 1; k >= 0; k--) {
			Insertions ins = list_insert.get(k);
			if (ins.get_rf() <= 0)
				list_insert.remove(ins);
		}
	}

	// calcularea rf-ului maxim din instantele unui nod
	public int max_rf() {
		int max = 0;
		for (int k = list_insert.size() - 1; k >= 0; k--) {
			Insertions ins = list_insert.get(k);
			if (ins.get_rf() > max)
				max = ins.get_rf();
		}
		return max;
	}

	// tranportarea instantei actualizate pe prima pozitie
	public void actualize(int inc, int sf) {
		Insertions ins = list_insert.get(inc);
		delete(inc);
		list_insert.add(ins);
	}

	// stergerea unei instante dintrun nod
	public void delete(int inc) {
		// crearea unui nou arraylist si modificarea celui initial
		ArrayList<Insertions> new_list = new ArrayList<>();
		for (int i = 0; i != list_insert.size(); i++) {
			if (i != inc)
				new_list.add(list_insert.get(i));
		}
		list_insert = new_list;
		insertions--;
		actualize_rf();
	}

	// bonus, stergerea instantelor mai vechi de un anumit timestamp
	public void cleanNode(long time) {
		ArrayList<Insertions> new_list = new ArrayList<Insertions>();

		for (Insertions list : list_insert) {
			if (list.get_time() >= time) {
				new_list.add(list);
			}
		}
		list_insert = new_list;
	}
}
