package model;

import java.lang.reflect.Field;

import javax.swing.table.AbstractTableModel;

public class ObjectPropertyTableModel extends AbstractTableModel {

	private final Object targetObject;
	private final Field[] fields;

	public ObjectPropertyTableModel(Object targetObject) {
		this.targetObject = targetObject;
		this.fields = targetObject.getClass().getDeclaredFields();

		for (Field f : fields)
			f.setAccessible(true);
	}

	@Override
	public int getRowCount() {
		return fields.length;
	}

	@Override
	public int getColumnCount() {
		return 2; // Özellik - Değer
	}

	@Override
	public String getColumnName(int column) {
		return column == 0 ? "Özellik" : "Değer";
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {

		Field f = fields[rowIndex];

		try {
			if (columnIndex == 0)
				return f.getName();

			return f.get(targetObject);

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	// getColumnClass metodunu satır bazlı alan tipini döndürecek şekilde
	// güncelliyoruz Eski kodumda farklıydı .
	@Override
	public Class<?> getColumnClass(int columnIndex) {

		if (columnIndex == 0) {
			return String.class;
		}

		// Değer sütunu → ilgili alanın tipi
		return fields[0].getType(); // JTable default renderer için yeterli
	}

	@Override
	public boolean isCellEditable(int row, int col) {
		return col == 1;// sadece 1.index düzenlenebilir
	}

	@Override
	public void setValueAt(Object value, int rowIndex, int columnIndex) {
		Field field = fields[rowIndex];// ilgili alan
		Class<?> type = field.getType();// tip öğrenme

		try {

			if (type == boolean.class || type == Boolean.class) {
				// Checkbox'tan gelen değer zaten Boolean'dır
				field.set(targetObject, (Boolean) value);
			} else if (type.isEnum()) {
				// Combobox'tan gelen değer Enum sabitinin kendisidir, yani direkt olarak
				// field'a atlatır!!!.
				// value.toString() ve Enum.valueOf'a gerek yokmuş****.
				field.set(targetObject, value);
			} else if (type == int.class || type == Integer.class) {
				field.set(targetObject, Integer.parseInt(value.toString()));
			} else if (type == double.class || type == Double.class) {
				field.set(targetObject, Double.parseDouble(value.toString()));
			} else {
				// String veya diğer tipler
				field.set(targetObject, value);
			}

			fireTableCellUpdated(rowIndex, columnIndex);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}