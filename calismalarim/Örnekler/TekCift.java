import java.util.Scanner;

void main() {
    Scanner inp = new Scanner(System.in);
    System.out.println("Diziniz kaç elemanlı olsun:");
    int elemanS = inp.nextInt();
    inp.nextLine();
    int[] dizi = new int[elemanS];
    for (int i = 0; i < elemanS; i++) {
        i++;
        System.out.println(i + ". elemanı girin:");
        i--;
        dizi[i] = inp.nextInt();
    }
    for (int a = 0; a < elemanS; a++) {
        tc(dizi[a]);
    }
}

public static void tc(int sayi) {
    if (sayi % 2 == 0) {
        System.out.println(sayi + " çift bir sayı");
    } else {
        System.out.println(sayi + " tek bir sayı");
    }
}