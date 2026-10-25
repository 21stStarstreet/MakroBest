import java.util.Scanner;

void main() {
double fiyat, KDV= 0.18;
Scanner inp=new Scanner(System.in);

System.out.print("Fiyat:");
fiyat= inp.nextDouble();
fiyat += fiyat * KDV;
System.out.print("Tutar:"+fiyat);
}