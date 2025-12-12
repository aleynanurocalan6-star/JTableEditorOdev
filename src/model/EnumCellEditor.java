package model;

import java.awt.Component;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.DefaultCellEditor;

public class EnumCellEditor extends DefaultCellEditor {

    // JComboBox'ı Enum değerleri ile dolduran tek kurucu***.
    public EnumCellEditor(Object[] enumConstants) {
        // Super constructor, JComboBox'ı düzenleyici olarak atar.
        super(new JComboBox<>(enumConstants)); 
    }

    /**
     * Hücre düzenleme başladığında çağrılır. Mevcut değeri ComboBox'ta seçili hale getirir.
     */
    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, 
                                                 boolean isSelected, int row, int column) {
        
        JComboBox comboBox = (JComboBox) getComponent(); 
        
        if (value != null) {
            comboBox.setSelectedItem(value);
        } else {
            comboBox.setSelectedIndex(-1); 
        }
        
        // SADECE JComboBox'ı döndürün.
        return comboBox; 
    }
    /**
     * Düzenleme bittiğinde ComboBox'ta seçili olan Enum objesini döndürür önemli111.
     */
    @Override
    public Object getCellEditorValue() {
        return ((JComboBox) getComponent()).getSelectedItem();
    }
}