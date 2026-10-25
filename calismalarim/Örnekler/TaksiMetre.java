import java.util.Scanner;

void main() {
    int km;
    double tl;
    Scanner imp = new Scanner(System.in);
    System.out.println("Kaç km gittiniz:");
    km = imp.nextInt();
    if (km <= 20) {
        tl = 20;
    } else
        tl = km * 2.20 + 10;


    System.out.println("Ücret=" + tl);
}