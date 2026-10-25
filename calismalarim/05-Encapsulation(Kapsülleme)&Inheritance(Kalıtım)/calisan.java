abstract class calisan {
    private String isim;
    protected double temelmaas;

    public calisan(String isim, double temelmaas) {
        this.isim = isim;
        this.temelmaas = temelmaas;
    }

    public double getMaas() {
        return temelmaas;
    }

    public void setMaas(double maas) {
        this.temelmaas = maas;
    }

    public String getIsim() {
        return isim;
    }

    public void setIsim(String isim) {
        this.isim = isim;
    }
    public void bilgileriGoster() {
        System.out.println("Çalışan İsmi : " + isim);
        System.out.println("Çalışan maas : " + temelmaas);
    }
abstract void maasHesapla();
}
