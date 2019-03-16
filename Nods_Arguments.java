
// Pislari Vadim
// Clasa care reprezinta o "celula" din nod 
// cu tipurile atributilor si denumirea entitatii

public class Nods_Arguments {
	private String entity;
	private String value;

	public Nods_Arguments(String entity, String value) {
		this.entity = entity;
		this.value = value;
	}

	public String get_entity() {
		return entity;
	}

	public String get_value() {
		return value;
	}
}
