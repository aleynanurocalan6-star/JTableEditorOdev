package controller;

import javax.swing.JFrame;

import view.YeniBilesenPanel;

public class YeniBilesenController {

	private Object model;
	private JFrame frame;

	public YeniBilesenController(Object model) {
		this.model = model;
	}

	public void display() {
// UI göstermek için Frame ilk kez açılıyorsa:

//UI oluştur

//Daha önce oluşturulduysa:

//Aynı frame’i tekrar kullan
		if (frame == null) {
			initUI();
		}
		// görünür yapar
		frame.setVisible(true);
	}

	private void initUI() {
		frame = new JFrame(model.getClass().getSimpleName() + " Özellik Editörü");
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		YeniBilesenPanel panel = new YeniBilesenPanel(model);

		// EVENT BAĞLAMA
		panel.addPrintListener(this::printObject);// paneldeki butona basılınca controllerdeki printobject çalışsın
		frame.setContentPane(panel);// frame içeriği panel
		frame.pack();// boutlandırma
		frame.setLocationByPlatform(true);
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
