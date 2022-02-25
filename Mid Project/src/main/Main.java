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
	
	ArrayList<Karyawan> kry = new ArrayList<Karyawan>();
	
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
		
		kry.add(new Karyawan(tempKodeKry, tempNamaKry, tempJKKry, tempJabatanKry, tempGajiKry));
		
		System.out.println("Berhasil menambahkan karyawan dengan ID: " + tempKodeKry);
		bonusKry(tempJabatanKry);
	}
	
	boolean cekKode(String tempkode) {
		if(!kry.isEmpty()) {
			for (Karyawan karyawan : kry) {
				if (karyawan.getKodeKry().equals(tempkode)) return true;
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
		
		if (!kry.isEmpty()) {
			for (Karyawan karyawan : kry) {
				if (tempjabatan.equals(karyawan.getJabatanKry())) {
					count++;
				}
			}
			
			if (count!=0 && count%3==1) {
				for (int i=0; i<kry.size(); i++) {
					if (countAdd==count-(count%3)) break;
					if (kry.get(i).getJabatanKry().equals(tempjabatan)) {
						kry.set(i, new Karyawan(kry.get(i).getKodeKry(), kry.get(i).getNamaKry(), kry.get(i).getJKKry(), kry.get(i).getJabatanKry(), kry.get(i).getGajiKry()+(kry.get(i).getGajiKry() * percent)));
						kodeAddKry.add(kry.get(i).getKodeKry());
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
		if (!kry.isEmpty() && !(kry.size()==1)) {
			for (int i=0; i<kry.size(); i++) {
				for (int j=0; j<kry.size()-1; j++) {
					if (kry.get(j).getNamaKry().compareToIgnoreCase(kry.get(j+1).getNamaKry())>0) {
						Karyawan temp = kry.get(j);						
						kry.set(j, kry.get(j+1));
						kry.set(j+1, temp);
					}
				}
			}
		}
	}
	
	void viewKry() {
		if (!kry.isEmpty()) {
			sortNamaKry();
			System.out.println("+-----+---------------+-------------------------+---------------+---------------+---------------+");
			System.out.printf("|%-5s|%-15s|%-25s|%-15s|%-15s|%-15s|\n", "No", "Kode Karyawan", "Nama Karyawan", "Jenis Kelamin", "Jabatan", "Gaji Karyawan");
			System.out.println("+-----+---------------+-------------------------+---------------+---------------+---------------+");
			for (int i=0; i<kry.size(); i++) {
				System.out.printf("|%-5d|%-15s|%-25s|%-15s|%-15s|%-15.0f|\n", i+1, kry.get(i).getKodeKry(), kry.get(i).getNamaKry(), kry.get(i).getJKKry(), kry.get(i).getJabatanKry(), kry.get(i).getGajiKry());
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
		} while (tempno<1 || tempno>kry.size());
		
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
		
		String newNamaKry = kry.get(index).getNamaKry();
		String newJKKry = kry.get(index).getJKKry();
		String newJabatanKry = kry.get(index).getJabatanKry();
		double newGajiKry = kry.get(index).getGajiKry();
		
		if (!tempNamaKry.equals("0")) newNamaKry=tempNamaKry;
		if (!tempJKKry.equals("0")) newJKKry=tempJKKry;
		if (!tempJabatanKry.equals("0")) newJabatanKry=tempJabatanKry;
		if (tempGajiKry!=0) newGajiKry=tempGajiKry;
		
		kry.set(index, new Karyawan(kry.get(index).getKodeKry(), newNamaKry, newJKKry, newJabatanKry, newGajiKry));
		
		System.out.println("Berhasil mengupdate karyawan dengan ID: " + kry.get(index).getKodeKry());
		
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
		} while (tempno<1 || tempno>kry.size());
		
		int index = tempno-1;
		tempKodeKry = kry.get(index).getKodeKry();
		kry.remove(index);
		System.out.println("Berhasil menghapus karyawan dengan ID: " + tempKodeKry);
	}
}
