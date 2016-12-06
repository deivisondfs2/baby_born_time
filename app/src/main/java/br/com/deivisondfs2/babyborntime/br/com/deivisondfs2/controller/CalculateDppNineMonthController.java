package br.com.deivisondfs2.babyborntime.br.com.deivisondfs2.controller;

import org.joda.time.DateTime;

import br.com.deivisondfs2.babyborntime.br.com.deivisondfs2.domain.Pregnant;
import br.com.deivisondfs2.babyborntime.br.com.deivisondfs2.infra.ICalculateDPP;

/**
 * Created by deivisonfrancisco on 30/04/16.
 */
public class CalculateDppNineMonthController implements ICalculateDPP {

    @Override
    public DateTime calculateDPP(Pregnant pregnant) {
        DateTime datePlusNine = pregnant.getValueDum().plusDays(7).plusMonths(9);
        return datePlusNine;
    }
}
