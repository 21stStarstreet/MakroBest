2# 03 – Veri Alma (Scanner)

## Scanner nedir?
Scanner, Java’da kullanıcıdan veri almak için kullanılan sınıftır.

## Scanner kullanımı
import java.util.Scanner;

Scanner scanner = new Scanner(System.in);

Veri alma metodları:
- String → nextLine()
- int → nextInt()
- double → nextDouble()
- boolean → nextBoolean()

## Örnek Snippet
Scanner scanner = new Scanner(System.in);

System.out.print("İsminizi giriniz: ");
String isim = scanner.nextLine();

System.out.print("Yaşınızı giriniz: ");
int yas = scanner.nextInt();

System.out.println("Merhaba " + isim + ", " + yas + " yaşındasın.");

scanner.close();