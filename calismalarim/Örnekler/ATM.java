import java.util.Scanner;

void main() {
    int bakiye = 4000, miktar, nakit = 0;
    int isim;
    Scanner inp = new Scanner(System.in);
    System.out.println("Bakiye sorgulama(1) / Para çekme (2) / Para gönderme (3) ");
    System.out.println("İşlemi seçiniz:");
    int islem = inp.nextInt();
    switch (islem) {
        case 1:
            System.out.println("Bakiyeniz:" + bakiye);
            break;
        case 3:
            System.out.println("Kime para göndericeksiniz(Nisa(1)/Göktuğ(2)");
            isim = inp.nextInt();
            switch (isim) {
                case 1:
                    System.out.println("Miktar yazın");
                    miktar = inp.nextInt();
                    bakiye = bakiye - miktar;
                    System.out.println("Nisa kişisine" + miktar + " TL gönderildi");
                    System.out.println("Bakiyeniz:" + bakiye);
                    break;
                case 2:
                    System.out.println("Miktar yazın");
                    miktar = inp.nextInt();
                    bakiye = bakiye - miktar;
                    System.out.println("Nisa kişisine" + miktar + " TL gönderildi");
                    System.out.println("Bakiyeniz:" + bakiye);
                    break;
            }
            break;
        case 2:
            System.out.println("Çekmek istediğiniz miktarı yazın");
            miktar = inp.nextInt();
            nakit = miktar;
            bakiye -= miktar;
            System.out.println("Bakiyeniz:" + bakiye);
            break;

    }

}