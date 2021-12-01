package apap.tutorial.emsidi.controller;
 
import apap.tutorial.emsidi.model.CabangModel;
import apap.tutorial.emsidi.model.MenuModel;
import apap.tutorial.emsidi.service.CabangService;
import apap.tutorial.emsidi.service.MenuService;

import java.time.LocalTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
 
@Controller
public class MenuController {
    @Qualifier("menuServiceImpl")
    @Autowired
    MenuService menuService;
 
    @GetMapping("/menu/add")
    public String addMenuForm(Model model){
        model.addAttribute("menu", new MenuModel());
        return "form-add-menu";
    }
 
    @PostMapping("/menu/add")
    public String addMenuSubmit(
        @ModelAttribute MenuModel menu,
        Model model
    ){
        menuService.addMenu(menu);
        model.addAttribute("noMenu", menu.getNoMenu());
        return "add-menu";
    }
 
    @GetMapping("/menu/viewall")
    public String viewAllMenu(
        Model model
    ){
        model.addAttribute("listMenu", menuService.getListMenu());
        return "viewall-menu";
    }

    @GetMapping("/menu/update/{noMenu}")
    public String updateMenuForm(
        @PathVariable Long noMenu,
        Model model
    ) {
        MenuModel menu = menuService.getMenuByNoMenu(noMenu);
        model.addAttribute("menu", menu);
        return "form-update-menu";
    }

    @PostMapping("/menu/update")
    public String updateMenuSubmit(
        @ModelAttribute MenuModel menu,
        Model model
    ) {
        menuService.updateMenu(menu);
        model.addAttribute("menu", menu.getNoMenu());
        return "update-menu";
    }

    @GetMapping("/menu/delete/{noMenu}")
    public String deleteMenu(
        @PathVariable Long noMenu,
        Model model
    ) {
        MenuModel menu = menuService.getMenuByNoMenu(noMenu);
        model.addAttribute("menu", menu);
        menuService.deleteMenu(menu);
        return "delete-menu";
    }

    @PostMapping("/menu/delete")
    public String deleteMenuSubmit(
        @ModelAttribute CabangModel cabang,
        Model model) {
        LocalTime now = LocalTime.now();
        if(now.isBefore(cabang.getWaktuBuka()) || now.isAfter(cabang.getWaktuTutup())){
            for (MenuModel menu: cabang.getListMenu()){
                menuService.deleteMenu(menu);
            }
            model.addAttribute("noCabang", cabang.getNoCabang());
            return "delete-menu";
        }
        return "error-page";
    }
}