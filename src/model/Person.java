package model;

enum Gender {
	Kadın, Erkek
}

enum MaritalStatus {
	Evli, Bekar
}

public class Person {

	private String isim = "Aleyna Nur";
	private String soyad = "Öcalan";
	private int yas = 22;
	private Gender cinsiyet = Gender.Kadın;
	private MaritalStatus medeniDurum = MaritalStatus.Bekar;
	private boolean calisiyorMu = true; // Boolean alan (Checkbox)
	private String dogumTarihi = "26.06.2003";

	// Constructor)
	public Person() {
	}

	// --- Getter Metotları

	public String getIsim() {
		return isim;
	}

	public String getSoyad() {
		return soyad;
	}

	public int getYas() {
		return yas;
	}

	public Gender getCinsiyet() {
		return cinsiyet;
	}

	public MaritalStatus getMedeniDurum() {
		return medeniDurum;
	}

	public boolean isCalisiyorMu() {
		return calisiyorMu;
	}

	public String getDogumTarihi() {
		return dogumTarihi;
	}

	// --- Setter Metotları (Veri yazma - JTable'dan direkt kullanılmaz ama
	// Reflection ile dolaylı kullanılır) ---

	public void setIsim(String isim) {
		this.isim = isim;
	}

	public void setSoyad(String soyad) {
		this.soyad = soyad;
	}

	public void setYas(int yas) {
		this.yas = yas;
	}

	public void setCinsiyet(Gender cinsiyet) {
		this.cinsiyet = cinsiyet;
	}

	public void setMedeniDurum(MaritalStatus medeniDurum) {
		this.medeniDurum = medeniDurum;
	}

	public void setCalisiyorMu(boolean calisiyorMu) {
		this.calisiyorMu = calisiyorMu;
	}

	public void setDogumTarihi(String dogumTarihi) {
		this.dogumTarihi = dogumTarihi;
	}
}