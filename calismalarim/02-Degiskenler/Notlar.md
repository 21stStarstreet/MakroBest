# Java – Değişkenler (Variables)

## Değişken Nedir?
Değişken, program içinde veri saklamak için kullanılan yapılardır.  
Bir değişken 3 bölümden oluşur:
- Veri tipi
- Değişken adı
- Değer

Örnek:
int yas = 25;

---

## Tam Sayı Veri Tipleri
Ondalık içermeyen sayılar için kullanılır.

byte   → 8 bit  
short  → 16 bit  
int    → 32 bit  
long   → 64 bit  

Örnek:
byte b = 10;
short s = 100;
int i = 1000;
long l = 100000L;

Not: long kullanırken sonuna L eklenmesi önerilir.

---

## Ondalıklı Sayı Veri Tipleri

float  → ondalıklı sayı (f veya F zorunlu)  
double → ondalıklı sayı (daha hassas)

Örnek:
float f = 1.23f;
double d = 3.14159;

Not: float kullanırken f yazılmazsa Java hata verir.

---

## Karakter Veri Tipi (char)
Tek bir karakter tutar.  
Tek tırnak (' ') kullanılır.

Örnek:
char c1 = 'J';
char c2 = 'A';
char c3 = 'V';
char c4 = 'A';

Karakterleri yazdırma:
System.out.println("" + c1 + c2 + c3 + c4);
// Çıktı: JAVA

Not: Baştaki "" karakterlerin string olarak birleşmesini sağlar.

---

## Mantıksal Veri Tipi (boolean)
Sadece iki değer alır:
true veya false

Örnek:
boolean ogrenciMi = true;
boolean mezunMu = false;

---

## Metin Veri Tipi (String)
Yazı (metin) tutar.  
Çift tırnak (" ") kullanılır.

Örnek:
String isim = "Goktug";
String mesaj = "Java ogreniyorum";

---

## Özet
- Tam sayılar: byte, short, int, long
- Ondalıklı sayılar: float, double
- Karakter: char
- Mantıksal: boolean
- Metin: String

Gerçek projelerde en sık kullanılan veri tipleri:
int, double, boolean, String

