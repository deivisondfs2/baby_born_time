package br.com.deivisondfs2.babyborntime.br.com.deivisondfs2.controller;

import org.joda.time.DateTime;

import br.com.deivisondfs2.babyborntime.br.com.deivisondfs2.domain.Pregnant;
import br.com.deivisondfs2.babyborntime.br.com.deivisondfs2.infra.ICalculateDPP;

/**
 * Created by deivisonfrancisco on 30/04/16.
 */
public class CalculateDppSubtractThreeMonthController implements ICalculateDPP {

    @Override
    public DateTime calculateDPP(Pregnant pregnant) {
        DateTime dateSubtractThree = pregnant.getValueDum().plusDays(7).minusMonths(3).plusYears(1);
        return dateSubtractThree;
    }
}
