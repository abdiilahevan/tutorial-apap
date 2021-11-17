package apap.tutorial.emsidi.restcontroller;

import apap.tutorial.emsidi.model.PegawaiModel;
import apap.tutorial.emsidi.repository.PegawaiDb;
import apap.tutorial.emsidi.service.PegawaiRestService;
import apap.tutorial.emsidi.service.PegawaiService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Mono;

import javax.validation.Valid;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/api/v1")
public class PegawaiRestController {
    @Autowired
    private PegawaiRestService pegawaiRestService;

    @PostMapping(value = "/pegawai")
    private String createPegawai(@Valid @RequestBody PegawaiModel pegawai, BindingResult bindingResult) {
        if(bindingResult.hasFieldErrors()){
            throw new ResponseStatusException(
                HttpStatus.BAD_REQUEST, "Request body has invalid type or missing field"
            );
        }
        else{
            return "Create Pegawai Sukses ";
        }
    }

    @PutMapping(value = "/pegawai/{noPagawai}")
    private String updatePegawai(@PathVariable("noPegawai") Long noPegawai, @RequestBody PegawaiModel pegawai){
        try {
            return "Update Pegawai Sukses ";
        } catch (NoSuchElementException e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Pegawai with ID " + String.valueOf(noPegawai) + " Not Found!");
        }
    }

    @GetMapping(value = "/pegawai/{noPegawai}")
    private PegawaiModel retrievePegawai(@PathVariable("noPegawai") Long noPegawai){
        try{
            return pegawaiRestService.getPegawaiByNoPegawai(noPegawai);
        }
        catch(NoSuchElementException e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "ID Pegawai" + String.valueOf(noPegawai) + " Not Found.");
        }
    }

    @GetMapping(value = "/list-pegawai")
    private List<PegawaiModel> retrieveListPegawai() {return pegawaiRestService.retrieveListPegawai();}

    @DeleteMapping(value = "/pegawai/{noPegawai}")
    private ResponseEntity deletePegawai(@PathVariable("noPegawai") Long noPegawai){
        try {
            pegawaiRestService.deletePegawai(noPegawai);
            return ResponseEntity.ok("Pegawai with ID " + String.valueOf(noPegawai) + " Deleted!");
        } catch (NoSuchElementException e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Pegawai with ID " + String.valueOf(noPegawai) + " Not Found!");
        } catch (UnsupportedOperationException e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Pegawai is still open or has pegawai");
        }
    }

    @Autowired
    private PegawaiService pegawaiService;
    @GetMapping(value = "/pegawai/umur/{noPegawai}")
    private PegawaiModel retrieveUmurPegawai(@PathVariable("noPegawai") Long noPegawai){
        try{
            pegawaiRestService.usiaPegawai(noPegawai);
            return pegawaiService.getPegawaiByNoPegawai(noPegawai);
        }catch(NoSuchElementException e){
            throw new ResponseStatusException(
                HttpStatus.NOT_FOUND, "No Pegawai " + String.valueOf(noPegawai) + " Not Found"
            );
        }
    }

}
