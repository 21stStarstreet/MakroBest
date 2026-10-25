package data;

public class calısan1 {

    private int no;
    private String isim;
    private String soyisim;
    private int tecrube;
    private double maas;

    public calısan1() {

    }

    public void calisanBilgileriGoster() {
        System.out.println("Çalışan no:" + no);
        System.out.println("Çalışan isim:" + isim);
        System.out.println("Çalışan soyisim:" + soyisim);
        System.out.println("Çalışan tecrübe:" + tecrube);
        System.out.println("Çalışan maas:" + maas);
    }

    public void zamYap(int zamdeğeri){
        System.out.println("maaşınıza "+zamdeğeri+" kadar zam yapıldı");
        System.out.println("Şuanki güncel maaşınız"+ (maas+zamdeğeri));




    }

    public calısan1(int no, String isim, String soyisim, int tecrube, double maas) {
        this.no = no;
        this.isim = isim;
        this.soyisim = soyisim;
        this.tecrube = tecrube;
        this.maas = maas;
    }
}
