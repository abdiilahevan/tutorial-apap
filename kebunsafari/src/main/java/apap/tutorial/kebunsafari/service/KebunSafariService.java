package apap.tutorial.kebunsafari.service;

import apap.tutorial.kebunsafari.model.KebunSafariModel;

import java.util.List;

public interface KebunSafariService {
    // Method untuk menambahkan kebunSafari baru
    void addKebunSafari(KebunSafariModel kebunSafari);

    // Method untuk mendapatkan seluruh daftar
    List<KebunSafariModel> getKebunSafariList();

    // Method untuk mendapatkan data dari Id
    KebunSafariModel getKebunSafariByIdKebunSafari(String idKebunSafari);
}
