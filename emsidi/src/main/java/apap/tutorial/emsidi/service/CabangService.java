package apap.tutorial.emsidi.service;

import apap.tutorial.emsidi.model.CabangModel;
import java.util.List;

public interface CabangService{
    void addCabang(CabangModel cabang);
    void updateCabang(CabangModel cabang);
    List<CabangModel> getCabangList();
    CabangModel getCabangByNoCabang(Long noCabang);
    List<CabangModel> getAllCabang();
    Boolean jamWaktuTutupCabang(CabangModel cabang);
    void deleteCabang(CabangModel cabang);
}