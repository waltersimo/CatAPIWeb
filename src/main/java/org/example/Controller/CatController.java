package org.example.Controller;

import com.squareup.otto.Subscribe;
import org.example.IHM.CatIHM;
import org.example.RequestUtils;
import org.example.beans.CatBeans;

import static org.example.Main.bus;

public class CatController {

    private CatIHM ihm;

    //****************************************** CONSTRUCTEURS ******************************************//
    public CatController(CatIHM ihm) {
        this.ihm = ihm;
        bus.register(this);
    }

    //****************************************** METHODES ******************************************//
    @Subscribe
    public void clickGetCat(String animal){
        ihm.setProgressbar(true);
        if (animal.equals("Cat") || animal.equals("cat")) {
            Thread newThread = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        CatBeans cat = RequestUtils.loadCatBean();
                        ihm.updateIHM(cat);
                    } catch (Exception e) {
                        e.printStackTrace();
                        System.out.println("Une erreur est survenu : " + e.getMessage());
                    }
                    ihm.setProgressbar(false);
                }
            });
            newThread.start();
        }
    }
}
