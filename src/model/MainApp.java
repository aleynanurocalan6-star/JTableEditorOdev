package model;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import javax.swing.*;

public class MainApp {

    public static void main(String[] args) {
        
        SwingUtilities.invokeLater(() -> createAndShowGUI()); // GUI'yi oluştur
    }

    private static void createAndShowGUI() {
        // 1. Veri Nesnesini Oluştur

        Person person = new Person(); // Person nesnesi yorum satırında
        //Product product = new Product(); // Product nesnesi aktif
        
        // Tabloyu oluştur (Şu an Product nesnesi ile)
        //ObjectPropertyTable table = new ObjectPropertyTable(product);
         ObjectPropertyTable table1 = new ObjectPropertyTable(person); 
        
        // Pencereyi oluştur
        JFrame frame = new JFrame("Nesne Özellik Editörü");
        
        JScrollPane scrollPane = new JScrollPane(table1);
        scrollPane.setPreferredSize(new Dimension(450, 250)); 
        
        // Değişiklikleri görmek için bir kontrol paneli ekledim
        JButton printButton = new JButton("Nesneyi Konsola Yazdır");
        printButton.addActionListener((ActionEvent e) -> {
            // Getter metotları - 
            
            // Eğer Person sınıfını kullanmak isterseniz bu bloğu kullanın:
            
            System.out.println("\n--- GÜNCEL PERSON NESNESİ DEĞERLERİ ---");
            System.out.println("İsim: " + person.getIsim());
            System.out.println("Soyad: " + person.getSoyad());
            System.out.println("Yaş: " + person.getYas());
            System.out.println("Çalışıyor Mu?: " + person.isCalisiyorMu());
            System.out.println("Cinsiyet: " + person.getCinsiyet());
            System.out.println("Medeni Durum: " + person.getMedeniDurum());
            System.out.println("Doğum Tarihi: " + person.getDogumTarihi());
            
            
            // Product sınıfı için çıktı
        	/*
            System.out.println("\n--- GÜNCEL PRODUCT NESNESİ DEĞERLERİ ---");
            System.out.println("Adı: " + product.getName());
            System.out.println("Fiyat: " + product.getPrice());
            System.out.println("İndirimli Mi?: " + product.isDiscounted());
            System.out.println("Durum: " + product.getProductStatus());
            System.out.println("Miktar: " + product.getQuantity());
            */
            
        });
        
        frame.add(scrollPane, BorderLayout.CENTER);
        frame.add(printButton, BorderLayout.SOUTH);
        
        // Pencere ayarları
        frame.pack(); 
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null); 
        frame.setVisible(true);
    }
}