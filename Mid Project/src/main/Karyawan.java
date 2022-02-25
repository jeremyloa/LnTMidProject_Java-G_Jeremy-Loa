package main;

public class Karyawan {

	private String kodeKry;
	private String namaKry;
	private String JKKry;
	private String jabatanKry;
	private double gajiKry;
	
	public Karyawan(String kodeKry, String namaKry, String JKKry, String jabatanKry, double gajiKry) {
		super();
		this.kodeKry = kodeKry;
		this.namaKry = namaKry;
		this.JKKry = JKKry;
		this.jabatanKry = jabatanKry;
		this.gajiKry = gajiKry;
	}
	
	public String getKodeKry() {
		return kodeKry;
	}
	public void setKodeKry(String kodeKry) {
		this.kodeKry = kodeKry;
	}
	public String getNamaKry() {
		return namaKry;
	}
	public void setNamaKry(String namaKry) {
		this.namaKry = namaKry;
	}
	public String getJKKry() {
		return JKKry;
	}
	public void setJKKry(String jKKry) {
		JKKry = jKKry;
	}
	public String getJabatanKry() {
		return jabatanKry;
	}
	public void setJabatanKry(String jabatanKry) {
		this.jabatanKry = jabatanKry;
	}
	public double getGajiKry() {
		return gajiKry;
	}
	public void setGajiKry(double gajiKry) {
		this.gajiKry = gajiKry;
	}
	
	

}
