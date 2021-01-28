package dialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.NumberPicker;

import androidx.fragment.app.DialogFragment;

import ru.miet.toeat.infoStorage.User;

public class NumberPickerDialog extends DialogFragment {
    private NumberPicker.OnValueChangeListener valueChangeListener;
    private NumberPicker numberPicker;

    private int value;
    private int minValue;
    private int maxValue;
    private String[] displayedStrings;
    private Runnable onOk;

    public NumberPickerDialog(int value, int minValue, int maxValue){
        this.value = value;
        this.minValue = minValue;
        this.maxValue = maxValue;
    }
    public NumberPickerDialog(int value, int minValue, int maxValue, String[] displayedStrings){
        this.value = value;
        this.minValue = minValue;
        this.maxValue = maxValue;
        this.displayedStrings = displayedStrings;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        numberPicker = new NumberPicker(getActivity());

        numberPicker.setValue(value);
        numberPicker.setMaxValue(maxValue);
        numberPicker.setMinValue(minValue);
        if(displayedStrings != null)
            numberPicker.setDisplayedValues(displayedStrings);
        numberPicker.setWrapSelectorWheel(true);

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Choose Value");
        builder.setMessage("Choose a number :");

        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                onOk.run();
            }
        });

        builder.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        builder.setView(numberPicker);
        return builder.create();
    }

    public void setOnOkFunction(Runnable r){
        onOk = r;
    }
}

