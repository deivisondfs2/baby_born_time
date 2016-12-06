package br.com.deivisondfs2.babyborntime;

import android.app.DialogFragment;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.style.DynamicDrawableSpan;
import android.text.style.ImageSpan;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import java.util.Calendar;

import br.com.deivisondfs2.babyborntime.br.com.deivisondfs2.controller.pregnant.PregnantController;
import br.com.deivisondfs2.babyborntime.br.com.deivisondfs2.domain.Pregnant;
import br.com.deivisondfs2.babyborntime.br.com.deivisondfs2.fragments.DatePickerFragment;
import br.com.deivisondfs2.babyborntime.br.com.deivisondfs2.infra.InfraUtils;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnFocusChange;

//import android.support.v4.app.DialogFragment;

public class CalculateDPPActivity extends AppCompatActivity {


    PregnantController pregnantController = new PregnantController(new Pregnant());


    final Calendar c = Calendar.getInstance();
    int year = c.get(Calendar.YEAR);
    int month = c.get(Calendar.MONTH);
    int day = c.get(Calendar.DAY_OF_MONTH);


    public
    @Bind(R.id.editText)
    EditText dateEdit;

    public
    @Bind(R.id.editDateNow)
    EditText dateEditNow;

    TextInputLayout textInputLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculatedpp);
        ButterKnife.bind(this);
    }

    @OnFocusChange(R.id.editText) void onFocusChanged(boolean hasFocus) {
        if (hasFocus) {
            hideSoftKeyboard();
            dateEdit.setInputType(InputType.TYPE_NULL);
            showDatePickerDialog(dateEdit);
            dateEdit.clearFocus();
        }
    }

    @OnFocusChange(R.id.editDateNow) void onFocusChanged2(boolean hasFocus) {
        if (hasFocus) {
            hideSoftKeyboard();
            dateEditNow.setInputType(InputType.TYPE_NULL);
            showDatePickerDialog(dateEditNow);
            dateEditNow.clearFocus();
        }
    }


    public void showDatePickerDialog(final EditText editText) {
        setValuesDatePicker(editText);
        DatePickerFragment dateFragment = DatePickerFragment.newInstance(year, month, day, dateEdit.getText().toString(), dateEditNow.getText().toString(), editText);
        dateFragment.setEditText(editText);
        dateFragment.show(this.getFragmentManager(), "datePicker");
    }

    @OnClick(R.id.resetValues)
    public void resetValues(View v) {
        dateEdit.setText("");
        dateEditNow.setText("");
    }


    private void setValuesDatePicker(EditText editText) {
        if (!isDateEditEmpty(editText)) {
            String[] dateSplit = editText.getText().toString().split("/");
            day = Integer.parseInt(dateSplit[0]);
            month = Integer.parseInt(dateSplit[1]) - 1;
            year = Integer.parseInt(dateSplit[2]);
        }
    }

    public boolean isDateEditEmpty(EditText dateEditText) {
        return dateEditText.getText().toString().isEmpty();
    }

    @OnClick(R.id.calculateDPP)
    public void buttonCalculateDPP(View v) {
        if (validateFields()) return;
        constructPregnant();

        DialogResultActivity dialogResultActivity = DialogResultActivity.newInstance(getString(R.string.title_congratulations), createMensageResultDialog());
        dialogResultActivity.setStyle(DialogFragment.STYLE_NORMAL, R.style.CustomDialog);
        dialogResultActivity.show(this.getFragmentManager(), "dialog_result");

    }

    private String createMensageResultDialog() {
        String contentResult = getString(R.string.mensage_result_dialog,
                InfraUtils.getDateTimeFormatedToString(pregnantController.getPregnant().calculateDpp()),
                getResources().getQuantityString(R.plurals.numOfWeeks, pregnantController.getPregnant().getNumWeeks(), pregnantController.getPregnant().getNumWeeks()),
                getResources().getQuantityString(R.plurals.numOfDays, pregnantController.getPregnant().getNumDays(), pregnantController.getPregnant().getNumDays()));
        return contentResult;
    }

    private boolean validateFields() {
        boolean erros = false;
        ImageSpan imageSpan = new ImageSpan(this, R.drawable.ic_emergency, DynamicDrawableSpan.ALIGN_BOTTOM);

        SpannableStringBuilder ssbErrorMsg = new SpannableStringBuilder(" " + " " + getString(R.string.field_required));
        imageSpan.getDrawable().setBounds(10, 10, 10, 10);
        ssbErrorMsg.setSpan(imageSpan, 0, 1, 0);

        textInputLayout = (TextInputLayout) dateEdit.getParent();
        if (isDateDumEmpty()) {
            textInputLayout.setErrorEnabled(true);
            textInputLayout.setError(ssbErrorMsg);
            textInputLayout.refreshDrawableState();
            erros = true;
        } else {
            textInputLayout.setErrorEnabled(false);
            textInputLayout.refreshDrawableState();
        }

        textInputLayout = (TextInputLayout) dateEditNow.getParent();
        if (isDateParameterEmpty()) {
            textInputLayout.setErrorEnabled(true);
            textInputLayout.setError(ssbErrorMsg);
            textInputLayout.refreshDrawableState();
            erros = true;
        } else {
            textInputLayout.setErrorEnabled(false);
            textInputLayout.refreshDrawableState();
        }

        return erros;
    }

    public boolean isDateDumEmpty() {
        return TextUtils.isEmpty(dateEdit.getText().toString());
    }

    public boolean isDateParameterEmpty() {
        return TextUtils.isEmpty(dateEditNow.getText().toString());
    }


    public void constructPregnant() {
        pregnantController.getPregnant().setValueDum(InfraUtils.parseDateTimeDayMonthYear(String.valueOf(dateEdit.getText())));
        pregnantController.getPregnant().setValueDateParameter(InfraUtils.parseDateTimeDayMonthYear(String.valueOf(dateEditNow.getText())));
        pregnantController.setMethodCalculateDPP();

    }


    /**
     * Hides the soft keyboard
     */
    public void hideSoftKeyboard() {
        if (getCurrentFocus() != null) {
            InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
        }
    }

    /**
     * Shows the soft keyboard
     */
    public void showSoftKeyboard(View view) {
        InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
        view.requestFocus();
        inputMethodManager.showSoftInput(view, 0);
    }

}
