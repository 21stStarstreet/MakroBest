import java.util.Scanner;

void main() {
    Scanner inp = new Scanner(System.in);
    int sayi;

    System.out.println("Haftanın kaçıncı günü?");
    sayi = inp.nextInt();
    if (sayi <= 7) {
        switch (sayi) {
            case 1:
                System.out.println("Pazartesi");
                break;
            case 2:
                System.out.println("Salı");
                break;
            case 3:
                System.out.println("Çarşamba");
                break;
            case 4:
                System.out.println("Perşembe");
                break;
            case 5:
                System.out.println("Cuma");
                break;
            case 6:
                System.out.println("Cumartesi");
                break;
            case 7:
                System.out.println("Pazar");
                break;

        }
    } else {
        sayi = sayi % 7;
        switch (sayi) {
            case 0:
                System.out.println("Pazartesi");
                break;
            case 1:
                System.out.println("Salı");
                break;
            case 2:
                System.out.println("Çarşamba");
                break;
            case 3:
                System.out.println("Perşembe");
                break;
            case 4:
                System.out.println("Cuma");
                break;
            case 5:
                System.out.println("Cumartesi");
                break;
            case 6:
                System.out.println("Pazar");
                break;
        }
    }
}