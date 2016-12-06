package br.com.deivisondfs2.babyborntime.br.com.deivisondfs2.infra;

import org.joda.time.DateTime;

import br.com.deivisondfs2.babyborntime.br.com.deivisondfs2.domain.Pregnant;

/**
 * Created by deivisonfrancisco on 30/04/16.
 */
public interface ICalculateDPP {

    public DateTime calculateDPP(Pregnant pregnant);
}
