import java.util.Scanner;

public class VeriAlma {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("İsminizi giriniz: ");
        String isim = scanner.nextLine();

        System.out.print("Yaşınızı giriniz: ");
        int yas = scanner.nextInt();

        System.out.println("Merhaba " + isim + ", " + yas + " yaşındasın.");

        scanner.close();
    }
}
