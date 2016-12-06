package br.com.deivisondfs2.babyborntime.br.com.deivisondfs2.domain;

import org.joda.time.DateTime;
import org.joda.time.Period;
import org.joda.time.PeriodType;

import java.io.Serializable;

import br.com.deivisondfs2.babyborntime.br.com.deivisondfs2.infra.ICalculateDPP;

/**
 * Created by deivisonfrancisco on 30/04/16.
 */
public class Pregnant implements Serializable {

    private String name;
    private ICalculateDPP iCalculateDPP;
    private DateTime valueDum;
    private DateTime valueDateParameter;

    public Pregnant() {

    }

    public Pregnant(ICalculateDPP iCalculateDPP, DateTime valueDum, DateTime valueDateParameter) {
        this.iCalculateDPP = iCalculateDPP;
        this.valueDum = valueDum;
        this.valueDateParameter = valueDateParameter;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ICalculateDPP getiCalculateDPP() {
        return iCalculateDPP;
    }

    public void setiCalculateDPP(ICalculateDPP iCalculateDPP) {
        this.iCalculateDPP = iCalculateDPP;
    }

    public DateTime getValueDum() {
        return valueDum;
    }

    public void setValueDum(DateTime valueDum) {
        this.valueDum = valueDum;
    }

    public DateTime calculateDpp() {
        return iCalculateDPP.calculateDPP(this);
    }

    public DateTime getValueDateParameter() {
        return valueDateParameter;
    }

    public void setValueDateParameter(DateTime valueDateParameter) {
        this.valueDateParameter = valueDateParameter;
    }

    public int getNumWeeks(){
        Period period = new Period(valueDum, valueDateParameter, PeriodType.yearWeekDay());
        int weeks = period.getWeeks();
        return weeks;
    }

    public int getNumDays(){
        Period period = new Period(valueDum, valueDateParameter, PeriodType.yearWeekDay());
        int days = period.getDays();
        return days;
    }
}
