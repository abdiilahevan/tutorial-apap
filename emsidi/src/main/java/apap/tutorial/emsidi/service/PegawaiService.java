package apap.tutorial.emsidi.service;

import apap.tutorial.emsidi.model.PegawaiModel;

public interface PegawaiService{
    void addPegawai(PegawaiModel pegawai);
    void updatePegawai(PegawaiModel pegawai);
    Boolean jamWaktuTutup(PegawaiModel pegawai);
    PegawaiModel getPegawaiByNoPegawai(Long noPegawai);
    void deletePegawai(PegawaiModel pegawai);
}