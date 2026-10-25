import java.util.Scanner;

void main() {
    Scanner inp = new Scanner(System.in);
    int elemanS;
    System.out.print("Dizi kaç elemanlı olsun");
    elemanS = inp.nextInt();
    int[] dizi = new int[elemanS];
    for (int i = 0; i < elemanS; i++) {
        i++;
        System.out.println(i + ". elemanı girin:");
        i--;
        dizi[i] = inp.nextInt();
        System.out.println(dizi[i]);

    }
}