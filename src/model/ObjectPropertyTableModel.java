package model;

import java.lang.reflect.Field;
import javax.swing.table.AbstractTableModel;
import javax.swing.JOptionPane;

public class ObjectPropertyTableModel extends AbstractTableModel {
    
    private final Object targetObject;
    private final Field[] fields; 
    private final String[] columnNames = {"Özellik Adı", "Değer"}; 

    public ObjectPropertyTableModel(Object object) {
        this.targetObject = object;
        this.fields = targetObject.getClass().getDeclaredFields(); 
    }

    @Override
    public int getRowCount() {
        return fields.length; 
    }

    @Override
    public int getColumnCount() {
        return columnNames.length; 
    }

    @Override
    public String getColumnName(int columnIndex) {
        return columnNames[columnIndex];
    }
    
    /**
      Reflection ile alanın gerçek tipini döndürürüz.
     */
    @Override
    public Class<?> getColumnClass(int rowIndex) {
        if (rowIndex >= 0 && rowIndex < fields.length) {
             
            return fields[rowIndex].getType();
        }
        if (rowIndex == 0) return String.class;
        return Object.class;
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return columnIndex == 1; // Sadece Değer sütunu düzenlenebilir
    }
    
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Field field = fields[rowIndex];
        
        if (columnIndex == 0) {
            return field.getName(); 
        }

        try {
            field.setAccessible(true); 
            return field.get(targetObject); 
        } catch (IllegalAccessException e) {
            return "Hata: Erişim Yok";
        }
    }
    
    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        if (columnIndex != 1) return;
        
        Field field = fields[rowIndex];
        try {
            field.setAccessible(true);
            
            Class<?> targetType = field.getType();
            Object finalValue = aValue;
            
            
            
            // 1. INT TİP DÖNÜŞÜMÜ
            if (targetType == int.class || targetType == Integer.class) {
                finalValue = Integer.parseInt(aValue.toString());
            } 
            
            
            else if (targetType.isEnum() && aValue instanceof String) {
                finalValue = Enum.valueOf((Class<Enum>) targetType, aValue.toString());
            }
            
            
            else if ((targetType == boolean.class || targetType == Boolean.class) && aValue instanceof String) {
                 finalValue = Boolean.parseBoolean(aValue.toString());
            }
            
         
            field.set(targetObject, finalValue); 
            fireTableCellUpdated(rowIndex, columnIndex); 
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Hata: Geçersiz format veya tip uyuşmazlığı!\n" + e.getMessage(), "Hata", JOptionPane.ERROR_MESSAGE);
            
            fireTableCellUpdated(rowIndex, columnIndex); 
        }
    }
}