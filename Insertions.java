
// Pislari Vadim
// Clasa care reprezinta o Inserare din baza de date 

import java.util.*;

public class Insertions implements Cloneable {
	private String entity;
	private ArrayList<Cell> arrlist = new ArrayList<Cell>();
	private long time;
	private int rf;

	public Insertions(String entity) {
		this.entity = entity;
	}

	public Insertions(String entity, Database data) {
		this.entity = entity;
		Nods nod = data.getmap().get(entity);
		this.rf = nod.get_rf();
		this.time = System.nanoTime();
	}

	public String get_entity() {
		return entity;
	}

	public void add_ins(String value, int i, Database data) {
		Nods nod = data.getmap().get(entity);
		String type = nod.get_arrlist().get(i).get_value();
		Cell cell = new Cell(value, type);
		arrlist.add(cell);
	}

	public ArrayList<Cell> get_arrlist() {
		return arrlist;
	}

	public long get_time() {
		return time;
	}

	public int get_rf() {
		return rf;
	}

	public void set_rf(int new_rf) {
		rf = new_rf;
	}

	// clonarea
	public Insertions cloning() {
		try {
			return (Insertions) super.clone();
		} catch (CloneNotSupportedException e) {
			System.out.println("CloneNotSupportedException");
			return this;
		}
	}
}
