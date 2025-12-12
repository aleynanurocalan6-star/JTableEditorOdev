package model;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellEditor;
import java.awt.*;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

public class ObjectPropertyTable extends JTable {

    // Her satır/alan için özel düzenleyicileri tutacak bir Map
    private final Map<Integer, TableCellEditor> customEditors = new HashMap<>();
    private final Field[] fields;

    public ObjectPropertyTable(Object targetObject) {

        ObjectPropertyTableModel model = new ObjectPropertyTableModel(targetObject);
        this.setModel(model);
        
        // Reflection ile alanları alıyoruz
        this.fields = targetObject.getClass().getDeclaredFields();

        setupRowSpecificEditors(targetObject); // Özel düzenleyicileri hazırlıyoruz
        setupRenderers();

        this.setRowHeight(25);
        // Özellik adını gösteren ilk sütunun genişliğini ayarla
        this.getColumnModel().getColumn(0).setPreferredWidth(150); 
    }

    // Bu metod, her bir satır (alan) için uygun düzenleyiciyi hazırlar
    private void setupRowSpecificEditors(Object targetObject) {

        for (int row = 0; row < fields.length; row++) {

            Field f = fields[row];
            f.setAccessible(true);
            Class<?> type = f.getType();

            // BOOLEAN → CHECKBOX
            if (type == boolean.class || type == Boolean.class) {
                JCheckBox checkBox = new JCheckBox();
                // Checkbox için DefaultCellEditor kullanıyoruz
                customEditors.put(row, new DefaultCellEditor(checkBox));
            }

            // ENUM → COMBOBOX (Özel EnumCellEditor'ü kullanıyoruz)
            else if (type.isEnum()) {
                // Özel EnumCellEditor'ü, enum sabitleriyle oluşturuyoruz
                customEditors.put(row, new EnumCellEditor(type.getEnumConstants()));
            }
            // Diğer tipler (String, int, double vb.) için varsayılan TextEditor kullanılacak.
        }
    }

    /**
     * Satır ve sütun bazında düzenleyiciyi döndürür.
     */
    @Override
    public TableCellEditor getCellEditor(int row, int column) {
        // Yalnızca Değer sütunu (index 1) için özel editörleri kontrol et
        if (column == 1 && customEditors.containsKey(row)) {
            // Eğer o satır için özel bir düzenleyici varsa (Boolean, Enum) onu döndür
            return customEditors.get(row);
        }

        // Değer sütunu değilse (index 0) veya özel düzenleyici atanmamışsa varsayılanı kullan
        return super.getCellEditor(row, column);
    }
    
    // Boolean değerlerin tabloda "true"/"false" metinleri olarak görünmesini sağlayan Renderers yeni ekledimm
    private void setupRenderers() {

        // Boolean değerler için özel renderer
        DefaultTableCellRenderer booleanRenderer = new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(
                    JTable table, Object value,
                    boolean isSelected, boolean hasFocus,
                    int row, int column) {

                // Varsayılan render component'i al
                JLabel lbl = (JLabel) super.getTableCellRendererComponent(
                        table, value, isSelected, hasFocus, row, column);

                // Eğer değer Boolean ise metnini ayarla
                if (value instanceof Boolean) {
                    // Boolean değerini "true" veya "false" metnine dönüştür
                    lbl.setText((Boolean) value ? "true" : "false");
                    lbl.setHorizontalAlignment(SwingConstants.CENTER); 
                }

                return lbl;
            }
        };

        // Tablonun Boolean tipli hücrelerini bu render ile göster burda hallettim
        this.setDefaultRenderer(Boolean.class, booleanRenderer);
        this.setDefaultRenderer(boolean.class, booleanRenderer);
    }
}