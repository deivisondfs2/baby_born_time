package br.com.deivisondfs2.babyborntime;

import android.os.Bundle;
import android.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class DialogResultActivity extends DialogFragment {

    private static final String TITLE = "title";
    private static final String MENSAGE = "mensage";

    private
    TextView textView;

    private
    Button closeDialog;


    public DialogResultActivity() {

    }

    public static DialogResultActivity newInstance(String title, String mensage) {
        Bundle bundle = new Bundle();
        bundle.putString(TITLE, title);
        bundle.putString(MENSAGE, mensage);

        DialogResultActivity dialogResultActivity = new DialogResultActivity();
        dialogResultActivity.setArguments(bundle);
        return dialogResultActivity;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_dialog_result, container);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        textView = (TextView) view.findViewById(R.id.textViewCalculateFinal);
        closeDialog = (Button) view.findViewById(R.id.closeDialog);

        String title = getArguments().getString(TITLE);
        String mensage = getArguments().getString(MENSAGE);

        getDialog().setTitle(title);
        textView.setText(mensage);

        closeDialog.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });
    }
}
