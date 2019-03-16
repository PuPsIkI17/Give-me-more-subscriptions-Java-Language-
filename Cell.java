
// Pislari Vadim
// Clasa care reprezinta o celula dintr-o inserare

public class Cell {
	private String str_value;
	private int int_value;
	private float float_value;
	private String type;

	public Cell(String value, String type) {
		if (type.equals("Integer")) {
			int_value = Integer.parseInt(value);
		} else if (type.equals("Float")) {
			float_value = Float.parseFloat(value);
		} else {
			str_value = value;
		}
		this.type = type;
	}

	public String get_value() {
		if (type.equals("Integer")) {
			return Integer.toString(int_value);
		} else if (type.equals("Float")) {
			return Float.toString(float_value);
		} else {
			return str_value;
		}
	}

	public void set_value(String value) {
		if (type.equals("Integer")) {
			int_value = Integer.parseInt(value);
		} else if (type.equals("Float")) {
			float_value = Float.parseFloat(value);
		} else {
			str_value = value;
		}
	}

	public void print() {
		if (type.equals("Integer")) {
			System.out.print(int_value);
		} else if (type.equals("Float")) {
			System.out.print(fmt(float_value));
		} else {
			System.out.print(str_value);
		}
	}

	public static String fmt(float d) {
		if (d == (long) d)
			return String.format("%d", (long) d);
		else
			return String.format("%s", d);
	}

	public String get_type() {
		return type;
	}
}
