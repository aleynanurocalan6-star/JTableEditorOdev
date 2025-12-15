package controller;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.lang.reflect.Field;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import view.ObjectPropertyTable;

public class YeniBilesenController {

	private Object model; // controllerın yönettiği veri
	private JFrame frame; // controllerın kendi penceresi
	private JPanel panel; // frame içine koyulan ana panel

	public YeniBilesenController(Object model) {
		this.model = model; // hangi obje ile çalışacağı belli olur
	}

	// dışardan çağırılan tek public metot
	public void display() {
		if (frame == null) {
			initUI();
		}
		frame.setVisible(true);
	}

// Arayüz kurulumları burada yapıyorum 
	private void initUI() {

		// Frame
		frame = new JFrame(model.getClass().getSimpleName() + " Özellik Editörü");
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		// Panel o9luşturma
		panel = new JPanel(new BorderLayout());

		ObjectPropertyTable table = new ObjectPropertyTable(model);
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setPreferredSize(new Dimension(400, 250));// boyut ayarla ,kaydırılabilir

		JButton printButton = new JButton("Nesneyi Konsola Yazdır");
		printButton.addActionListener(e -> printObject());

		panel.add(scrollPane, BorderLayout.CENTER);
		panel.add(printButton, BorderLayout.SOUTH);

		frame.add(panel);
		frame.pack(); // içeriğe göre boyut
		frame.setLocationByPlatform(true);
	}

	private void printObject() {
		System.out.println("\n--- " + model.getClass().getSimpleName() + " ---");

		try {
			// reflection kullanımı private olsa bile okur
			for (Field field : model.getClass().getDeclaredFields()) {
				field.setAccessible(true);
				System.out.println(field.getName() + " = " + field.get(model));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
