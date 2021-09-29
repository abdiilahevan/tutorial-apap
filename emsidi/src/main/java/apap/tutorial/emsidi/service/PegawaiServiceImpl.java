package apap.tutorial.emsidi.service;

import apap.tutorial.emsidi.model.PegawaiModel;
import apap.tutorial.emsidi.repository.CabangDb;
import apap.tutorial.emsidi.repository.PegawaiDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.Optional;

import javax.transaction.Transactional;

@Service
@Transactional
public class PegawaiServiceImpl implements PegawaiService{

    @Autowired
    PegawaiDb pegawaiDb;

    @Override
    public void addPegawai(PegawaiModel pegawai) {pegawaiDb.save(pegawai);}

    @Override
    public void updatePegawai(PegawaiModel pegawai){
        if(jamWaktuTutup(pegawai) == true){
        pegawaiDb.save(pegawai);
        }
    }

    @Override
    public Boolean jamWaktuTutup(PegawaiModel pegawai){
        LocalTime now = LocalTime.now();
        if(now.isBefore(pegawai.getCabang().getWaktuBuka()) || now.isAfter(pegawai.getCabang().getWaktuTutup())){
            return true;
        }
        return false;
    }

    @Override
    public PegawaiModel getPegawaiByNoPegawai(Long noPegawai){
        Optional<PegawaiModel> pegawai = pegawaiDb.findByNoPegawai(noPegawai);
        if (pegawai.isPresent()){
            return pegawai.get();
        }
        return null;
    }

    @Override
    public void deletePegawai(PegawaiModel pegawai){
        if(jamWaktuTutup(pegawai) == true){
            pegawaiDb.delete(pegawai);
        }
    }
}