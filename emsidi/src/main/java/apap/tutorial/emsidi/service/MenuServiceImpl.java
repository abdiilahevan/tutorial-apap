package apap.tutorial.emsidi.service;
 
import apap.tutorial.emsidi.model.MenuModel;
import apap.tutorial.emsidi.repository.MenuDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
 
import java.util.List;
import java.util.Optional;
 
@Service
public class MenuServiceImpl implements MenuService{
    @Autowired
    MenuDb menuDb;
 
    @Override
    public List<MenuModel> getListMenu() {
        return menuDb.findAll();
    }
 
    @Override
    public void addMenu(MenuModel menu){
        menuDb.save(menu);
    }
    @Override
    public void deleteMenu(MenuModel menu){
        menuDb.delete(menu);
    }

    @Override
    public void updateMenu(MenuModel menu){
        menuDb.save(menu);
    }

    @Override
    public MenuModel getMenuByNoMenu(Long noMenu){
        Optional<MenuModel> menu = menuDb.findById(noMenu);
        return menu.get();
    }
}