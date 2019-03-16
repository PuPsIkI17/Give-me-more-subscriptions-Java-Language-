// Pislari Vadim 323CB
// Clasa de baza din care se face citirea si se utilizeaza 
//celalte clase pentru a realiza tema

import java.io.*;
import java.util.*;

public class Main{
	public static void main(String[] args) throws IOException {

		// deschirea fisierului de citire
		File inputfile = new File(args[0]);
		Scanner in = new Scanner(inputfile);

		// deschirea fisierului de printare
		String answer = args[0] + "_out";
		PrintStream fileStream = new PrintStream(answer);
		System.setOut(fileStream);

		// verificarea daca cu prima instructiune se creaza baza de date
		String lines = in.nextLine();
		String[] parameters = lines.split(" ");
		if (!parameters[0].equals("CREATEDB")) {
			System.out.println("Nu este indeplinita conditia");
			in.close();
			return;
		}

		// crearea baza de date
		Database database = new Database(parameters[2], parameters[3]);

		// citirea linie cu linie a instructiunilor
		while (in.hasNext()) {
			lines = in.nextLine();
			parameters = lines.split(" ");
			if (parameters[0].equals("CREATE")) {
				Nods new_no = new Nods(parameters[2], parameters[3]);
				// citirea argumentelor si adaugarea in nod
				for (int i = 4; i < parameters.length; i += 2) {
					new_no.add_argument(parameters[i], parameters[i + 1]);
				}
				database.add_nod(new_no, parameters[1]);
			} else if (parameters[0].equals("INSERT")) {
				Insertions insert = new Insertions(parameters[1], database);
				// citirea inserarilor si adaugarea lor
				for (int i = 2; i < parameters.length; i++) {
					insert.add_ins(parameters[i], i - 2, database);
				}
				database.add_full_insertion(insert);
			} else if (parameters[0].equals("GET")) {
				database.get(parameters[1], parameters[2]);
			} else if (parameters[0].equals("UPDATE")) {
				ArrayList<Nods_Arguments> newarr = new ArrayList<Nods_Arguments>();
				for (int i = 3; i < parameters.length; i += 2) {
					Nods_Arguments arg = new Nods_Arguments(parameters[i], parameters[i + 1]);
					newarr.add(arg);
				}
				database.update(parameters[1], parameters[2], newarr);
			} else if (parameters[0].equals("DELETE")) {
				database.delete(parameters[1], parameters[2]);
			} else if (parameters[0].equals("CLEANUP")) {
				database.clean(parameters[1], parameters[2]);
			} else if (parameters[0].equals("SNAPSHOTDB")) {
				database.print();
			}
		}
		in.close();
	}

}
