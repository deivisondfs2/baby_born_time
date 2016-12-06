package br.com.deivisondfs2.babyborntime.br.com.deivisondfs2.controller.pregnant;

import br.com.deivisondfs2.babyborntime.br.com.deivisondfs2.controller.CalculateDppNineMonthController;
import br.com.deivisondfs2.babyborntime.br.com.deivisondfs2.controller.CalculateDppSubtractThreeMonthController;
import br.com.deivisondfs2.babyborntime.br.com.deivisondfs2.domain.Pregnant;

/**
 * Created by deivisonfrancisco on 03/05/16.
 */
public class PregnantController {

    private Pregnant pregnant;



    public PregnantController(Pregnant pregnant) {
        this.pregnant = pregnant;
    }


    public void setMethodCalculateDPP(){
        if (isJanFevMar()) {
            pregnant.setiCalculateDPP(new CalculateDppNineMonthController());
        } else {
            pregnant.setiCalculateDPP(new CalculateDppSubtractThreeMonthController());
        }
    }

    public boolean isJanFevMar() {
        return (pregnant.getValueDum().getMonthOfYear() == 1) || (pregnant.getValueDum().getMonthOfYear() == 2) || (pregnant.getValueDum().getMonthOfYear() == 3);
    }

    public Pregnant getPregnant() {
        return pregnant;
    }

    public void setPregnant(Pregnant pregnant) {
        this.pregnant = pregnant;
    }
}
