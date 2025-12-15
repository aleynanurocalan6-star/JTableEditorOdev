package app;

import controller.YeniBilesenController;
import model.Person;
import model.Product;

public class MainApp {

	public static void main(String[] args) {

		new YeniBilesenController(new Person()).display(); // controller yarat controller 1 objeyle ilişkili
															// frame,panel,tablo,pencereyi ekrana getir
		new YeniBilesenController(new Product()).display();

	}
}
