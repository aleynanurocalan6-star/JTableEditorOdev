package model;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import javax.swing.*;

public class MainApp {

    public static void main(String[] args) {
        
        SwingUtilities.invokeLater(() -> createAndShowGUI());
    }

    private static void createAndShowGUI() {
        // 1. Veri Nesnesini Oluştur
        Person person = new Person();
        Product product=new Product();
        
      
        ObjectPropertyTable table = new ObjectPropertyTable(person);
        ObjectPropertyTable table1=new ObjectPropertyTable(product);
        
      

        // Pencereyi oluştur
        JFrame frame = new JFrame("sNesne Özellik Editörü");
        
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setPreferredSize(new Dimension(450, 250)); 
        
        // Değişiklikleri görmek için bir kontrol paneli ekledim
        JButton printButton = new JButton("Nesneyi Konsola Yazdır");
        printButton.addActionListener((ActionEvent e) -> {
            // Getter metotları
            System.out.println("\n--- GÜNCEL NESNE DEĞERLERİ ---");
            System.out.println("İsim: " + person.getIsim());
            System.out.println("Soyad: " + person.getSoyad());
            System.out.println("Yaş: " + person.getYas());
            System.out.println("Çalışıyor Mu?: " + person.isCalisiyorMu());
            System.out.println("Cinsiyet: " + person.getCinsiyet());
            System.out.println("Medeni Durum: " + person.getMedeniDurum());
            System.out.println("Doğum Tarihi: " + person.getDogumTarihi());
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