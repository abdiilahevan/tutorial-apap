package apap.tutorial.kebunsafari.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import apap.tutorial.kebunsafari.model.KebunSafariModel;
import apap.tutorial.kebunsafari.service.KebunSafariService;
import java.util.List;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
public class KebunSafariController {
    @Autowired
    private KebunSafariService kebunSafariService;

    @RequestMapping("/kebun-safari/add")
    public String addKebunSafari(
        @RequestParam(value = "id", required = true) String idKebunSafari,
        @RequestParam(value = "nama", required = true) String namaKebunSafari,
        @RequestParam(value = "alamat", required = true) String alamat,
        @RequestParam(value = "noTelepon", required = true) String noTelepon,
        Model model
    ){
        // Membuat objek baru
        KebunSafariModel kebunSafari = new KebunSafariModel(idKebunSafari, namaKebunSafari, alamat, noTelepon);

        // Memanggil service
        kebunSafariService.addKebunSafari(kebunSafari);

        // Menambahkan variabel kebunSafari untuk dirender di thymeleaf
        model.addAttribute("kebunSafari", kebunSafari);

        //Mereturn template html yang dipakai
        return "add-kebun-safari";
    }

    @RequestMapping("/")
    public String listKebunSafari(Model model){
        List<KebunSafariModel>listKebunSafari = kebunSafariService.getKebunSafariList();

        model.addAttribute("listKebunSafari", listKebunSafari);

        return "get-all-kebun-safari";
    }

    @RequestMapping("/kebun-safari")
    public String getKebunSafariById(@RequestParam(value = "id", required = true) String idKebunSafari, Model model){
        KebunSafariModel kebunSafari = kebunSafariService.getKebunSafariByIdKebunSafari(idKebunSafari);

        model.addAttribute("kebunSafari", kebunSafari);

        return "detail-kebun-safari";
    }

    //Latihan1
    @GetMapping(value="/kebun-safari/view/{id}")
    public String viewWithPath(
        @PathVariable(value = "id") String idKebunSafari, Model model
    ){
        return getKebunSafariById(idKebunSafari, model);
    }

    //Lathian2
    @RequestMapping(value="/kebun-safari/update/{id}")
    public String updatePhone(
        @PathVariable(value = "id") String idKebunSafari, Model model,
        @RequestParam(value = "noTelepon", required = true) String noTelepon
    ) {
        kebunSafariService.getKebunSafariByIdKebunSafari(idKebunSafari).setnoTelepon(noTelepon);
        return getKebunSafariById(idKebunSafari, model);
    }
    
    //Latihan3
    @RequestMapping(value="/kebun-safari/delete/{id}")
    public String deleteById(@PathVariable(value = "id", required = true) String idKebunSafari, Model model){
        KebunSafariModel kebunSafari = kebunSafariService.getKebunSafariByIdKebunSafari(idKebunSafari);

        kebunSafariService.getKebunSafariList().remove(kebunSafari);
        return listKebunSafari(model);
    }
    
}
