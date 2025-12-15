package controller;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import view.ObjectPropertyTable;

@SuppressWarnings("serial")
public class YeniBilesenPanel extends JPanel {

	private JButton btnPrint;

	public YeniBilesenPanel(ObjectPropertyTable table) {

		setLayout(new BorderLayout());

		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setPreferredSize(new Dimension(400, 250));

		btnPrint = new JButton("Nesneyi Konsola Yazdır");

		add(scrollPane, BorderLayout.CENTER);
		add(btnPrint, BorderLayout.SOUTH);
	}

	public JButton getBtnPrint() {

		return btnPrint;
	}
}
