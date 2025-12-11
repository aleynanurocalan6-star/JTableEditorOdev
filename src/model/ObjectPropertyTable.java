package model;

import javax.swing.JTable;

public class ObjectPropertyTable extends JTable {

    // Constructor: Bir nesneyi alıp tabloyu tamamen hazırlar
    public ObjectPropertyTable(Object targetObject) {
        
        // JTable'ın Object'ten türeyen her şeyi kabul etmesini sağlamak için
        
        // 1. Model'i Oluştursum ve  Ata!!!
        ObjectPropertyTableModel model = new ObjectPropertyTableModel(targetObject);
        this.setModel(model); 
        
        // 2. Enum Editörlerini Otomatik Ata!!!1
        setupDefaultEditors(targetObject);
        
        
        this.setRowHeight(25);
        this.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);
        
        // Sütun genişliklerini ayarlama
        this.getColumnModel().getColumn(0).setPreferredWidth(150);
        this.getColumnModel().getColumn(0).setMaxWidth(200);
        this.getColumnModel().getColumn(0).setMinWidth(100);
    }
    
    // Nesnedeki tüm alanları gezip Enum tiplerini JTable'a atar
    private void setupDefaultEditors(Object targetObject) {
        Class<?> objClass = targetObject.getClass();
        
        for (java.lang.reflect.Field field : objClass.getDeclaredFields()) {
            Class<?> fieldType = field.getType();
            
            // 1. Enum Tipleri İçin Özel Editör Ataması
            if (fieldType.isEnum()) {
                
                try {
                    Enum<?>[] enumConstants = (Enum<?>[]) fieldType.getEnumConstants();
                    
                    if (enumConstants != null && enumConstants.length > 0) {
                        this.setDefaultEditor(fieldType, new EnumCellEditor(enumConstants));
                    }
                } catch (Exception e) {
                    System.err.println("Editor atama hatası: " + fieldType.getName());
                }
            }
           
            // JTable'ın Boolean için Checkbox'ı kesin kullanmasını sağlamak için.
            else if (fieldType == boolean.class || fieldType == Boolean.class) {
                this.setDefaultEditor(fieldType, this.getDefaultEditor(Boolean.class));
            }
        }
    }
}