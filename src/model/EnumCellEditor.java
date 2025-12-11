package model;

import java.awt.Component;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.DefaultCellEditor;

public class EnumCellEditor extends DefaultCellEditor {

    // BU TEK YAPICIMIZDIR
    public EnumCellEditor(Enum<?>[] enumValues) {
       
        super(new JComboBox<>(enumValues)); 
    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
        ((JComboBox) getComponent()).setSelectedItem(value);
        return super.getTableCellEditorComponent(table, value, isSelected, row, column);
    }
}