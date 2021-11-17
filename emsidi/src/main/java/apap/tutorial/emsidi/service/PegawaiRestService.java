package apap.tutorial.emsidi.service;

import java.util.List;

import apap.tutorial.emsidi.model.PegawaiModel;

public interface PegawaiRestService {
    PegawaiModel createPegawai(PegawaiModel model);
    List<PegawaiModel> retrieveListPegawai();
    PegawaiModel getPegawaiByNoPegawai(Long noPegawai);
    PegawaiModel updatePegawai(Long noPegawai, PegawaiModel pegawaiUpdate);
    void deletePegawai(Long noPegawai);
    public void usiaPegawai(Long noPegawai);
}
