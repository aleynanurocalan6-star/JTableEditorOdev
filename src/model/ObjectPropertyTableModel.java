package model;

import java.lang.reflect.Field;

import javax.swing.JOptionPane;
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

		Field field = fields[rowIndex];
		Class<?> type = field.getType();

		Object oldValue;
		try {
			field.setAccessible(true);
			oldValue = field.get(targetObject);
		} catch (Exception ex) {
			return;
		}

		try {

			if (type == boolean.class || type == Boolean.class) {
				field.set(targetObject, (Boolean) value);

			} else if (type.isEnum()) {
				field.set(targetObject, value);

			} else if (type == int.class || type == Integer.class) {

				int val = Integer.parseInt(value.toString());
				if (val < 0)
					throw new IllegalArgumentException("Negatif olamaz");

				field.set(targetObject, val);

			} else if (type == double.class || type == Double.class) {

				double val = Double.parseDouble(value.toString());
				if (val < 0)
					throw new IllegalArgumentException("Negatif olamaz");

				field.set(targetObject, val);

			} else {
				if (value == null || value.toString().trim().isEmpty())
					throw new IllegalArgumentException("Boş olamaz");

				field.set(targetObject, value);
			}

			fireTableCellUpdated(rowIndex, columnIndex);

		} catch (Exception e) {

			// HATA → eski değeri geri koy
			try {
				field.set(targetObject, oldValue);
			} catch (Exception ignore) {
			}

			JOptionPane.showMessageDialog(null, e.getMessage(), "Geçersiz Giriş", JOptionPane.ERROR_MESSAGE);

			fireTableCellUpdated(rowIndex, columnIndex);
		}
	}
}
