package br.com.deivisondfs2.babyborntime.br.com.deivisondfs2.fragments;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.widget.DatePicker;
import android.widget.EditText;

import org.apache.commons.lang3.ObjectUtils;
import org.joda.time.DateTime;

import java.util.Calendar;

import br.com.deivisondfs2.babyborntime.br.com.deivisondfs2.infra.InfraUtils;

/**
 * Created by deivisonfrancisco on 30/04/16.
 */
public class DatePickerFragment extends DialogFragment
        implements DatePickerDialog.OnDateSetListener {

    Calendar calendar = Calendar.getInstance();
    EditText editText;
    DateTime dateMin = new DateTime();
    DateTime dateMax = new DateTime();

    public DatePickerFragment() {

    }

    public static DatePickerFragment newInstance(int year, int month, int day, String dateMin, String dateMax, EditText editText) {
        Bundle bundle = new Bundle();
        bundle.putInt("year", year);
        bundle.putInt("month", month);
        bundle.putInt("day", day);
        bundle.putString("editText", String.valueOf(editText.getId()));


        if (!dateMin.isEmpty()) {
            bundle.putString("dateMin", String.valueOf(InfraUtils.parseDateTimeDayMonthYear(dateMin)));
        }
        if (!dateMax.isEmpty()) {
            bundle.putString("dateMax", String.valueOf(InfraUtils.parseDateTimeDayMonthYear(dateMax)));
        }

        DatePickerFragment datePickerFragment = new DatePickerFragment();
        datePickerFragment.setArguments(bundle);

        return datePickerFragment;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        int year = getArguments().getInt("year");
        int month = getArguments().getInt("month");
        int day = getArguments().getInt("day");

        String dateMinString = getArguments().getString("dateMin");
        String dateMaxString = getArguments().getString("dateMax");

        DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(), this, year, month, day);
        DatePicker datePicker = datePickerDialog.getDatePicker();


        setMinDatePicker(dateMinString, datePicker);

        setMaxDatePicker(dateMaxString, datePicker);

        datePickerDialog.setTitle("");

        return datePickerDialog;
    }


    private void setMinDatePicker(String dateMinString, DatePicker datePicker) {
        if (ObjectUtils.notEqual(dateMinString, null)) {
            dateMin = DateTime.parse(dateMinString);
            calendar.set(dateMin.getYear(), dateMin.getMonthOfYear() - 1, dateMin.getDayOfMonth());
            datePicker.setMinDate(calendar.getTimeInMillis());
        }
    }

    private void setMaxDatePicker(String dateMaxString, DatePicker datePicker) {
        if (ObjectUtils.notEqual(dateMaxString, null)) {
            dateMax = DateTime.parse(dateMaxString);
            calendar.set(dateMax.getYear(), dateMax.getMonthOfYear() - 1, dateMax.getDayOfMonth());
            //calendar.add(Calendar.DAY_OF_MONTH, 6);
            datePicker.setMaxDate(calendar.getTimeInMillis());
        }
    }


    @Override
    public void onDateSet(DatePicker view, int year, int month, int day) {
        String formattedDate = InfraUtils.getDateFormated(year, month, day);
        editText.setText(formattedDate);
    }

    public EditText getEditText() {
        return editText;
    }

    public void setEditText(EditText editText) {
        this.editText = editText;
    }
}