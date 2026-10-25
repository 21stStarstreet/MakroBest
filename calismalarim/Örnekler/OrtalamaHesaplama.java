import java.util.Scanner;

void main(String[] args) {
    //Değişken tanımla
    int mat, Kimya, Fizik, Biyo, Türkçe, Tarih;

//Scanner tanımla
    Scanner inp = new Scanner(System.in);

//Veri Alma
    System.out.println("Matematik notu:");
    mat = inp.nextInt();
    System.out.println("Kimya notu:");
    Kimya = inp.nextInt();
    System.out.println("Fizik notu:");
    Fizik = inp.nextInt();
    System.out.println("Biyo notu:");
    Biyo = inp.nextInt();
    System.out.println("Türkçe notu:");
    Türkçe = inp.nextInt();
    System.out.println("Tarih notu:");
    Tarih = inp.nextInt();
    int Toplam = mat + Kimya + Fizik + Biyo + Türkçe + Tarih;
    Toplam /= 6;
    System.out.println("Ortalamanız:" + Toplam);
}

