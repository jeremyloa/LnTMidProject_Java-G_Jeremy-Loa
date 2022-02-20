package main;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Main {

	Scanner scan = new Scanner(System.in);
	Random rand = new Random();
	
	int menuMain = 0;
	
	String tempKodeKry = "";
	String tempNamaKry = "";
	String tempJKKry = "";
	String tempJabatanKry = "";
	double tempGajiKry = 0;
	
	ArrayList<String> kodeKry = new ArrayList<String>();
	ArrayList<String> namaKry = new ArrayList<String>();
	ArrayList<String> JKKry = new ArrayList<String>();
	ArrayList<String> jabatanKry = new ArrayList<String>();
	ArrayList<Double> gajiKry = new ArrayList<Double>();
	
	public Main() {
		do {
			do {
				printMenuMain();
				try {
					System.out.print(">> ");
					menuMain = scan.nextInt();
					scan.nextLine();
				} catch (Exception e) {
					scan.nextLine();
				}
			} while (menuMain<0 || menuMain>4);
			
			switch (menuMain) {
			case 1:
				System.out.println("");
				inputKaryawan();
				System.out.println("Press ENTER to return.");
				scan.nextLine();
				break;
			case 2:
				System.out.println("");
				viewKry();
				System.out.println("Press ENTER to return.");
				scan.nextLine();
				break;
			case 3:
				System.out.println("");
				updateKry();
				System.out.println("Press ENTER to return.");
				scan.nextLine();
				break;
			case 4:
				System.out.println("");
				deleteKry();
				System.out.println("Press ENTER to return.");
				scan.nextLine();
				break;
			}
		} while (menuMain!=0);
	}

	public static void main(String[] args) {
		new Main();
	}

	void printMenuMain() {
		System.out.println("");
		System.out.println("PT MUSANG");
		System.out.println("==========");
		System.out.println("1. Insert Data Karyawan");
		System.out.println("2. View Data Karyawan");
		System.out.println("3. Update Data Karyawan");
		System.out.println("4. Delete Data Karyawan");
		System.out.println("0. Tutup Menu");
	}
	
	void inputKaryawan() {
		do {
			String tempchar1 = String.valueOf((char) ('a'+rand.nextInt(26)));
			String tempchar2 = String.valueOf((char) ('a'+rand.nextInt(26)));
			int tempint1 = rand.nextInt(9);
			int tempint2 = rand.nextInt(9);
			int tempint3 = rand.nextInt(9);
			int tempint4 = rand.nextInt(9);
			tempKodeKry = tempchar1.toUpperCase() + tempchar2.toUpperCase() + "-" + tempint1 + tempint2 + tempint3 + tempint4;
		} while (cekKode(tempKodeKry)==true);
		
		do {
			System.out.print("Input nama karyawan [minimal 3 huruf]: ");
			tempNamaKry = scan.nextLine();
		} while (tempNamaKry.length()<3);
		
		do {
			System.out.print("Jenis kelamin [Laki-laki/Perempuan] (case sensitive): ");
			tempJKKry = scan.nextLine();
		} while (!tempJKKry.equals("Laki-laki") && !tempJKKry.equals("Perempuan"));
		
		do {
			System.out.print("Jabatan [Manager/Supervisor/Admin] (case sensitive): ");
			tempJabatanKry = scan.nextLine();
		} while (!tempJabatanKry.equals("Manager") && !tempJabatanKry.equals("Supervisor") && !tempJabatanKry.equals("Admin"));
		
		if (tempJabatanKry.equals("Manager")) {
			tempGajiKry = 8000000;
		} else if (tempJabatanKry.equals("Supervisor")) {
			tempGajiKry = 6000000;
		} else if (tempJabatanKry.equals("Admin")) {
			tempGajiKry = 4000000;
		}
		
		kodeKry.add(tempKodeKry);
		namaKry.add(tempNamaKry);
		JKKry.add(tempJKKry);
		jabatanKry.add(tempJabatanKry);
		gajiKry.add(tempGajiKry);
		
		System.out.println("Berhasil menambahkan karyawan dengan ID: " + tempKodeKry);
		bonusKry(tempJabatanKry);
	}
	
	boolean cekKode(String tempkode) {
		if (!kodeKry.isEmpty()) {
			for (String kode : kodeKry) {
				if (tempkode.equals(kode)) return true;
			}
		}
		return false;
	}
	
	void bonusKry(String tempjabatan) {
		int count = 0;
		int countAdd = 0;
		double percent = 0;
		
		ArrayList<String> kodeAddKry = new ArrayList<String>();
		
		if (tempjabatan.equals("Manager")) percent = 0.1;
		else if (tempjabatan.equals("Supervisor")) percent = 0.075;
		else if (tempjabatan.equals("Admin")) percent = 0.05;
		
		if (!jabatanKry.isEmpty()) {
			for (String jabatan : jabatanKry) {
				if (tempjabatan.equals(jabatan)) {
					count++;
				}
			}
			
			if (count!=0 && count%3==1) {
				for (int i=0; i<jabatanKry.size(); i++) {
					if (countAdd==count-(count%3)) break;
					if (jabatanKry.get(i).equals(tempjabatan)) {
						gajiKry.set(i, gajiKry.get(i)+(gajiKry.get(i)*percent));
						kodeAddKry.add(kodeKry.get(i));
						countAdd++;
					}
				}
				
				if (countAdd>=1) {
					System.out.printf("Bonus sebesar %.1f%% telah diberikan kepada karyawan dengan ID: ", percent*100);
					for (int i=0; i<kodeAddKry.size(); i++) {
						if (i!=0) System.out.printf(", ");
						System.out.printf("%s", kodeAddKry.get(i));
					}
					System.out.printf(".\n");
				}
			}
		}
	}
	
	void sortNamaKry() {
		if (!namaKry.isEmpty() && !(namaKry.size()==1)) {
			for (int i=0; i<namaKry.size(); i++) {
				for (int j=0; j<namaKry.size()-1; j++) {
					if (namaKry.get(j).compareToIgnoreCase(namaKry.get(j+1))>0) {
						tempKodeKry = kodeKry.get(j);
						kodeKry.set(j, kodeKry.get(j+1));
						kodeKry.set(j+1, tempKodeKry);
						
						tempNamaKry = namaKry.get(j);
						namaKry.set(j, namaKry.get(j+1));
						namaKry.set(j+1, tempNamaKry);
						
						tempJKKry = JKKry.get(j);
						JKKry.set(j, JKKry.get(j+1));
						JKKry.set(j+1, tempJKKry);
						
						tempJabatanKry = jabatanKry.get(j);
						jabatanKry.set(j, jabatanKry.get(j+1));
						jabatanKry.set(j+1, tempJabatanKry);
						
						tempGajiKry = gajiKry.get(j);
						gajiKry.set(j, gajiKry.get(j+1));
						gajiKry.set(j+1, tempGajiKry);
					}
				}
			}
		}
	}
	
	void viewKry() {
		if (!namaKry.isEmpty()) {
			sortNamaKry();
			System.out.println("+-----+---------------+-------------------------+---------------+---------------+---------------+");
			System.out.printf("|%-5s|%-15s|%-25s|%-15s|%-15s|%-15s|\n", "No", "Kode Karyawan", "Nama Karyawan", "Jenis Kelamin", "Jabatan", "Gaji Karyawan");
			System.out.println("+-----+---------------+-------------------------+---------------+---------------+---------------+");
			for (int i=0; i<namaKry.size(); i++) {
				System.out.printf("|%-5d|%-15s|%-25s|%-15s|%-15s|%-15.0f|\n", i+1, kodeKry.get(i), namaKry.get(i), JKKry.get(i), jabatanKry.get(i), gajiKry.get(i));
			}
			System.out.println("+-----+---------------+-------------------------+---------------+---------------+---------------+");
		}
	}
	
	void updateKry() {
		viewKry();
		int tempno = 0;
		do {
			try {
				System.out.print("Input nomor karyawan untuk diupdate: ");
				tempno = scan.nextInt();
				scan.nextLine();
			} catch (Exception e) {
				scan.nextLine();
			} 
		} while (tempno<1 || tempno>namaKry.size());
		
		int index = tempno-1;
		
		do {
			System.out.print("Input nama karyawan [minimal 3 huruf, 0 untuk menggunakan data lama]: ");
			tempNamaKry = scan.nextLine();
		} while (tempNamaKry.length()<3 && !tempNamaKry.equals("0"));
		
		do {
			System.out.print("Jenis kelamin [Laki-laki/Perempuan, 0 untuk menggunakan data lama] (case sensitive): ");
			tempJKKry = scan.nextLine();
		} while (!tempJKKry.equals("Laki-laki") && !tempJKKry.equals("Perempuan") && !tempJKKry.equals("0"));
		
		do {
			System.out.print("Jabatan [Manager/Supervisor/Admin, 0 untuk menggunakan data lama] (case sensitive): ");
			tempJabatanKry = scan.nextLine();
		} while (!tempJabatanKry.equals("Manager") && !tempJabatanKry.equals("Supervisor") && !tempJabatanKry.equals("Admin") && !tempJabatanKry.equals("0"));
		
		tempGajiKry=0;
		if (tempJabatanKry.equals("Manager")) {
			tempGajiKry = 8000000;
		} else if (tempJabatanKry.equals("Supervisor")) {
			tempGajiKry = 6000000;
		} else if (tempJabatanKry.equals("Admin")) {
			tempGajiKry = 4000000;
		}
		
		if (!tempNamaKry.equals("0")) namaKry.set(index, tempNamaKry);
		if (!tempJKKry.equals("0")) JKKry.set(index, tempJKKry);
		if (!tempJabatanKry.equals("0")) jabatanKry.set(index, tempJabatanKry);
		if (tempGajiKry!=0) gajiKry.set(index, tempGajiKry);
		
		System.out.println("Berhasil mengupdate karyawan dengan ID: " + kodeKry.get(index));
		
	}

	void deleteKry() {
		viewKry();
		int tempno = 0;
		do {
			try {
				System.out.print("Input nomor karyawan untuk dihapus: ");
				tempno = scan.nextInt();
				scan.nextLine();
			} catch (Exception e) {
				scan.nextLine();
			} 
		} while (tempno<1 || tempno>namaKry.size());
		
		int index = tempno-1;
				
		namaKry.remove(index); JKKry.remove(index); jabatanKry.remove(index); gajiKry.remove(index);
		System.out.println("Berhasil menghapus karyawan dengan ID: " + kodeKry.get(index));
		kodeKry.remove(index);		
	}
}
