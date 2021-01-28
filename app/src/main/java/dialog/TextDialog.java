package dialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.InputType;
import android.widget.EditText;
import android.widget.NumberPicker;
import androidx.fragment.app.DialogFragment;

public class TextDialog extends DialogFragment {
    private NumberPicker.OnValueChangeListener valueChangeListener;
    private Runnable onOk;
    private EditText input;
    private String fieldName;

    public TextDialog(String fieldName){
        this.fieldName = fieldName;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(fieldName);

        input = new EditText(getActivity());
        input.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_NORMAL);
        builder.setView(input);

        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                onOk.run();
            }
        });

        builder.setNegativeButton("Отмена", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        return builder.create();
    }

    public void setOnOkFunction(Runnable r){
        onOk = r;
    }
    public String getCurrentValue(){
        return String.valueOf(input.getText());
    }
}