import java.util.Scanner;

void main() {
    int a, b;
    double c;
    Scanner imp = new Scanner(System.in);
    System.out.print("ilk kenarı gir");
    a = imp.nextInt();
    System.out.print("ikinci kenarı gir");
    b = imp.nextInt();
    c = Math.sqrt((a * a) + (b * b));
    System.out.println("Hipotenüs = " + c);
}