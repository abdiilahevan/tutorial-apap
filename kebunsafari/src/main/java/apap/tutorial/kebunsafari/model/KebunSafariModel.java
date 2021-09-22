package apap.tutorial.kebunsafari.model;

public class KebunSafariModel {
    private String idKebunSafari;
    private String namaKebunSafari;
    private String alamat;
    private String noTelepon;

    public KebunSafariModel(String idKebunSafari, String namaKebunSafari, String alamat, String noTelepon){
        this.idKebunSafari = idKebunSafari;
        this.namaKebunSafari = namaKebunSafari;
        this.alamat = alamat;
        this.noTelepon = noTelepon;
    }

    public void setIdKebunSafari(String id){
        this.idKebunSafari = id;
    }
    
    public void setNamaKebunSafari (String nama){
        this.namaKebunSafari = nama;
    }

    public void setAlamat (String alamat){
        this.alamat = alamat;
    }

    public void setnoTelepon (String telp){
        this.noTelepon = telp;
    }

    public String getId(){
        return this.idKebunSafari;
    }

    public String getNama(){
        return this.namaKebunSafari;
    }

    public String getAlamat(){
        return this.alamat;
    }

    public String getNoTelp(){
        return this.noTelepon;
    }
}
