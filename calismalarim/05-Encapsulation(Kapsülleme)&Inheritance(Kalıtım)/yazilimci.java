public class yazilimci extends calisan {
    public String anaDil;

    public yazilimci(String isim, double maas, String anaDil) {
        this.anaDil = anaDil;
        super(isim, maas);
    }

    @Override
    public void bilgileriGoster() {
        // super.bilgileriGoster() diyerek üst sınıftaki yazdırma işlemini de kullanabiliriz.
        super.bilgileriGoster();
        System.out.println("Ana Dil: " + anaDil);
    }

    @Override
    void maasHesapla() {
        System.out.println(getIsim()+" " + "adlı çalışanın yeni maaşı:"+ getMaas());

    }

    public void kodYaz(){
        System.out.println(getIsim() +" "+ anaDil + " " + "dili ile kod yazıyor");

    }
}