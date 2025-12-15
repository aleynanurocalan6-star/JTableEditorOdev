package view;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class YeniBilesenPanel extends JPanel {

	private Object model;
	private ObjectPropertyTable table;
	private JButton printButton;

// constructor panel oluşturulurken hangi modell gösterilecek belirlenir uı kurulur 
	public YeniBilesenPanel(Object model) {
		this.model = model; // panel modelle bağlı
		initUI();
	}

	private void initUI() {
		setLayout(new BorderLayout());

		table = new ObjectPropertyTable(model); // modelin fieldlarını yansıtır
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setPreferredSize(new Dimension(400, 250));

		printButton = new JButton("Nesneyi Konsola Yazdır");

		add(scrollPane, BorderLayout.CENTER);
		add(printButton, BorderLayout.SOUTH);
	}

	// Controller buradan butona ulaşabilsin diye
	public void addPrintListener(Runnable action) { // runnable parametre almaz geri dönüş yok sadece bir iş yapar,buton
													// tıklama olayını controller’dan gelen bir davranışa bağlar
		printButton.addActionListener(e -> action.run());
	}
}
