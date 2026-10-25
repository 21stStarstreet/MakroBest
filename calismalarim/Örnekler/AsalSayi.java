import java.util.Scanner;

void main() {
    int sayi, a = 0;
    Scanner inp = new Scanner(System.in);
    System.out.println("Sayıyı gir");
    sayi = inp.nextInt();
    for (int i = 2; i < sayi; i++) {
        if (sayi % i == 0) {
            a++;
            break;
        }
    }
    if (a == 0) {
        System.out.println("Sayı asal ");

    } else
        System.out.println("Sayı asal değil ");

}