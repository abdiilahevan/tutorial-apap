package apap.tutorial.emsidi.controller;

import apap.tutorial.emsidi.model.CabangModel;
import apap.tutorial.emsidi.model.MenuModel;
import apap.tutorial.emsidi.model.PegawaiModel;
import apap.tutorial.emsidi.service.CabangService;
import apap.tutorial.emsidi.service.MenuService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

@Controller
public class CabangController{
    @Qualifier("cabangServiceImpl")
    @Autowired
    private CabangService cabangService;

    @Qualifier("menuServiceImpl")
    @Autowired
    private MenuService menuService;

    @GetMapping("/cabang/add")
    public String addCabangForm(Model model){
        model.addAttribute("cabang", new CabangModel());
        List<MenuModel> listMenuModels = menuService.getListMenu();
        model.addAttribute("listMenu", listMenuModels);
        return "form-add-cabang";
    }

//Untuk row
    @PostMapping(value = "/cabang/add", params = "save")
    public String saverow(
        @ModelAttribute CabangModel cabang,
        Model model
    ) {
        cabangService.addCabang(cabang);
        model.addAttribute("noCabang", cabang.getNoCabang());
        return "add-cabang";
    }

    @PostMapping(value = "/cabang/add", params = "addRow")
    public String addCabangRow(
        @ModelAttribute CabangModel cabang,
        BindingResult bindingResult,
        Model model
    ) {
        List<MenuModel> listMenuModels = menuService.getListMenu();

        if(cabang.getListMenu() == null){
            cabang.setListMenu(new ArrayList<MenuModel>());
        }
        List<MenuModel> newListMenu = cabang.getListMenu();
        newListMenu.add(new MenuModel());

        model.addAttribute("listMenu", listMenuModels);
        model.addAttribute("cabang", cabang);
        return "form-add-cabang";
    }

    @RequestMapping(value = "/cabang/add", method = RequestMethod.POST, params = "deleteRow")
    public String deleteCabangRow(
        @ModelAttribute CabangModel cabang,
        final BindingResult bindingResult,
        final HttpServletRequest req,
        Model model
    ) {
        List<MenuModel> listMenuModels = menuService.getListMenu();
        
        final Integer idRow = Integer.valueOf(req.getParameter("deleteRow"));
        cabang.getListMenu().remove(idRow.intValue());

        model.addAttribute("listMenu", listMenuModels);
        model.addAttribute("cabang", cabang);
        cabangService.addCabang(cabang);
        return "form-add-cabang";
    }

    @GetMapping("/cabang/viewall")
    public String listCabang(Model model){
        List<CabangModel> listCabang = cabangService.getCabangList();
        model.addAttribute("listCabang",listCabang);
        return "viewall-cabang";
    }

    @GetMapping("/cabang/view")
    public String viewDetailCabang(
        @RequestParam(value = "noCabang") Long noCabang,
        Model model
    ) {
        CabangModel cabang = cabangService.getCabangByNoCabang(noCabang);
        List<PegawaiModel> listPegawai = cabang.getListPegawai();

        model.addAttribute("cabang", cabang);
        model.addAttribute("listPegawai", listPegawai);
        model.addAttribute("listMenu", cabang.getListMenu());

        return "view-cabang";
    }

    @GetMapping("/cabang/update/{noCabang}")
    public String updateCabangForm(
        @PathVariable Long noCabang,
        Model model
    ) {
        CabangModel cabang = cabangService.getCabangByNoCabang(noCabang);
        model.addAttribute("cabang", cabang);
        return "form-update-cabang";
    }

    @PostMapping("/cabang/update")
    public String updateCabangSubmit(
        @ModelAttribute CabangModel cabang,
        Model model
    ) {
        cabangService.updateCabang(cabang);
        model.addAttribute("noCabang", cabang.getNoCabang());
        return "update-cabang";
    }

    @GetMapping("/cabang/viewalldetail")
    public String getAllCabangInfo(Model model){
        List<CabangModel> listCabang = cabangService.getAllCabang();
        model.addAttribute("listCabang",listCabang);
        return "viewall-cabang";
    }

    @GetMapping("/cabang/delete/{noCabang}")
    public String deleteCabang(
        @PathVariable Long noCabang,
        Model model
    ) {
        CabangModel cabang = cabangService.getCabangByNoCabang(noCabang);
        if(cabangService.jamWaktuTutupCabang(cabang)){
            if(cabang.getListPegawai().isEmpty()){
                cabangService.deleteCabang(cabang);
                model.addAttribute("cabang", cabang);
                return "delete-cabang";
            }
            return "error-page";
        }
        return "error-page";
    }


}