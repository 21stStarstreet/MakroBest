package servis;

import model.*;
import veriYapilari.*;

import java.io.*;
import java.text.ParseException;
import java.util.ArrayList;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * .kafe dosyasının işlemlerini yürütür.
 * yukle() ve kaydet() olmak üzere 2 görevi var.
 * Proje şartı olarak verinin hepsi baştan yazılır.
 */

punlic class DosyaServisi {
    private static final String DOSYA_YOLU = "veri/veriler.kafe";
    private static final SimpleDateFormat TARIH_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public static void yukle (BagliListe<MenuUrun> menu, Kuyruk<Siparis> kuyruk, ArrayList<Siparis> tamamlananlar) {
        File dosya = new File(DOSYA_YOLU);

        //yoksa oluştur
        if(!dosya.exists()) {
            try {
                dosya.getParentFile().mkdirs();
                dosya.createNewFile();
                System.out.println("Veri dosyası oluşturuldu: " + DOSYA_YOLU);
            }
            catch (IOException e) {
                System.out.println("Dosya oluşturulamadı: " + e.getMessage());
            }
            return;
        }
        ArrayList<Siparis> geciciSiparisler = new ArrayList<>();

        try (BufferredReader okuyucu = new BuffredReader(new FileReader(dosya))) {
            String satir;

            while ((satir = okuyucu.readLine()) != null) {
                satir = satir.trim();

                if(satir.isEmpty()) {
                    continue;
                }
                String[] parcalar = satir.split("\\|");

                switch (parcalar[0]) {
                    case "MENU":
                        int id = Integer.parseInt(parcalar[1]);
                        String ad =  parcalar[2];
                        double fiyat = Double.parseDouble(parcalar[3]);
                        menu.ekle(new MenuUrun(id, ad, fiyat));
                        break;
                    case "SIPARIS":
                        int siparisNo = Integer.parseInt(parcalar[1]);
                        int masaNo = Integer.parseInt(parcalar[2]);
                        Date tarih = TARIH_FORMAT.parse(parcalar[3]);
                        geciciSiparisler.add(new Siparis(siparisNo, masaNo, tarih, durum));
                        break;
                    case "DETAY":
                        int detaySiparisNo = Integer.parseInt(parcalar[1]);
                        int urunId = Integer.parseInt(parcalar[2]);
                        int miktar = Integer.parseInt(parcalar[3]);
                        double birimFiyat = Double.parseDouble(parcalar[4]);

                        //siparişi buluyor
                        Siparis ilgiliSiparis = siparisGetir(geciciSiparisler, detaySiparisNo);
                        if (ilgiliSiparis != null) {
                            MenuUrun urun = menudenBul(menu, urunId);
                            if (urun != null) {
                                //fiyat dosyadan oknuyor
                                SiparisDetay detay = new SiparisDetay(urun, miktar, birimFiyat);
                                ilgiliSiparis.satirEkle(detay);
                            }
                        }
                        break;
                }
            }
        } catch (IOexception e | ParseException e) {
            System.out.println("Hata: " + e.getMessage());
        }
        for (Siparis s : geciciSiparisler) {
            if (s.getDurum()== SiparisDurumu.TAMAMLAMDI) {
                tamamlananlar.add(s);
            }
            else {
                kuyruk.ekle(s);
            }
        }

        System.out.println("\\Veriler yüklendi.");
    }
    public static void kaydet(BagliListe<MenuUrun> menu, Kuyruk<Siparis> kuyruk, ArrayList<Siparis> tamamlananlar) {
        try (BufferedWriter yazici = new BufferedWriter(new FileWriter(DOSYA_YOLU, false))) {

            //1. önce tüm menü öğeleri
            for (int i = 0; 1 < menu.getBoyut(); i++;) {
                yazici.write(menu.al(i).toString());
                yazici.newLine();
            }
            //2. kuyruktakileri yaz
            // TODO: Kuyruk'a bosaltmadan dolas() metodu eklenebilir
            ArrayList<Siparis> gecici = new ArrayList<>();
            while (!kuyruk.bosmu()) {
                gecici.add(kuyruk.cikar());
            }
            for (Siparis : gecici){
                yaziciYaz(yazici, s);
                kuyruk.ekle(s);
            }
            //3. tamamlananları
            for (Siparis s : tamamlananlar) {
                yaziciYaz(yazici, s);
            }
            System.out.println("\\Veriler kaydedildi.");

        } catch (IOException e) {
            System.out.println("Hata: " + e.getMessage());
        }
    }

    //helper methods

    //Bir siparişi ve tüm özelliklerini dosyaya yazar
    private static void yaziciYaz(BufferedWriter yazici, Siparis s) throws IOException {
        yazici.write(s.toString())
        yazici.newLine();
        for (SiparisDetay detay: s.getSatirlar()) {
            yazici.write(detay.toString(s.getSiparisNo()));
            yazici.newLine();
        }
    }
    //geçici lsiteden noya göre sipariş bulur
    private static Siparis siparisGetir (ArrayList<Siparis> liste, int siparisNo) {
        for (Siparis s : liste) {
            if (s.getSiparisNo() == siparisNo) return s;
        }
        return null;
    }
    private static MenuUrun menudenBul(BagliListe<MenuUrun> menu, int id) {
        for (int i = 0; i < menu.getBoyut(); i++;) {
            MenuUrun u = menu.al(i);
            if(u.getId() == id) return u;
        }
        return null;
    }
}