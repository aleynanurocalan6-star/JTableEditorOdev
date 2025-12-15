package view;

import java.awt.Component;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

import javax.swing.DefaultCellEditor;
import javax.swing.JCheckBox;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellEditor;

import model.ObjectPropertyTableModel;

public class ObjectPropertyTable extends JTable {

	private final Map<Integer, TableCellEditor> customEditors = new HashMap<>();
	private final Field[] fields;

	public ObjectPropertyTable(Object targetObject) {

		ObjectPropertyTableModel model = new ObjectPropertyTableModel(targetObject);
		this.setModel(model);

		this.fields = targetObject.getClass().getDeclaredFields();

		setupRowSpecificEditors();
		setupRenderers();

		this.setRowHeight(25);
		this.getColumnModel().getColumn(0).setPreferredWidth(150);
	}

	private void setupRowSpecificEditors() {

		for (int row = 0; row < fields.length; row++) {

			Field f = fields[row];
			f.setAccessible(true);
			Class<?> type = f.getType();

			if (type == boolean.class || type == Boolean.class) {
				JCheckBox checkBox = new JCheckBox();
				checkBox.setHorizontalAlignment(SwingConstants.LEFT);
				customEditors.put(row, new DefaultCellEditor(checkBox));
			} else if (type.isEnum()) {
				customEditors.put(row, new EnumCellEditor(type.getEnumConstants()));
			}
		}
	}

	@Override
	public TableCellEditor getCellEditor(int row, int column) {

		if (column == 1 && customEditors.containsKey(row)) {
			return customEditors.get(row);
		}

		return super.getCellEditor(row, column);
	}

	// RENDERER SADECE BURADA
	private void setupRenderers() {

		DefaultTableCellRenderer booleanRenderer = new DefaultTableCellRenderer() {

			@Override
			public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
					boolean hasFocus, int row, int column) {

				if (!(value instanceof Boolean)) {
					// Eğer bu hücrenin değeri Boolean DEĞİLSE
					// hiç karışmaz , JTable nasıl çiziyorsa öyle çiz
					return super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
				}

				JCheckBox chk = new JCheckBox();
				// Boolean hücre için gösterilecek bileşen
				chk.setHorizontalAlignment(SwingConstants.LEFT);// sola hizala

				chk.setFocusPainted(false);// focus çerçevesi çizmez
				chk.setBorderPainted(false);// Border çizilmez
				chk.setOpaque(true);// arka plan

				boolean selected = (Boolean) value;
				// değer checkboxa atanıyor

				chk.setSelected(selected);

				if (isSelected) {

					chk.setBackground(table.getSelectionBackground());
				} else {
					chk.setBackground(table.getBackground());
				}
				return chk;
				// JTable bu hücreyi checkbox olarak çizer
			}
		};

		// SADECE DEĞER SÜTUNU
		// Renderer sadece “Değer” sütununa atanıyor
		this.getColumnModel().getColumn(1).setCellRenderer(booleanRenderer);
	}
}
