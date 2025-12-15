package controller;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import model.ModelValidator;
import view.ObjectPropertyTable;

public class YeniBilesenController {

	private Object model;
	private JFrame frame;
	private YeniBilesenPanel view;

	public YeniBilesenController(Object model) {
		this.model = model; // hangi model
		initView(); // viewi hazırlar
	}

	private void initView() {

		ObjectPropertyTable objectTable = new ObjectPropertyTable(model);
		view = new YeniBilesenPanel(objectTable); // view modeli bilmiyor

		view.getBtnPrint().addActionListener(e -> {

			// VALIDATION KONTROLÜ
			if (!ModelValidator.isValid(model)) {
				JOptionPane.showMessageDialog(frame, "Modelde hatalı veya eksik alanlar var!", "Validation Hatası",
						JOptionPane.WARNING_MESSAGE);
				return;
			}

			// MODEL GEÇERLİYSE
			printObject();
		});
	}

	public void display() {

		if (frame == null) {
			frame = new JFrame(model.getClass().getSimpleName() + " Özellik Editörü");
			frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

			frame.setContentPane(view);
			frame.pack();
			frame.setLocationByPlatform(true);
		}

		frame.setVisible(true);
	}

	private void printObject() {

		System.out.println("\n--- " + model.getClass().getSimpleName() + " ---");

		try {
			for (var field : model.getClass().getDeclaredFields()) {
				field.setAccessible(true);
				System.out.println(field.getName() + " = " + field.get(model));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
//Controller kullanıcı aksiyonlarını yönetir, modelin doğruluğunu kontrol eder ve sonucu view üzerinden kullanıcıya yansıtır.
