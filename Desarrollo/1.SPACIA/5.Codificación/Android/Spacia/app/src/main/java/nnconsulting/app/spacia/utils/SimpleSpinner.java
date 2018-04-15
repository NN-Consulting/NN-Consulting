package nnconsulting.app.spacia.utils;

import android.content.Context;
import android.support.annotation.ArrayRes;
import android.util.AttributeSet;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

public class SimpleSpinner extends Spinner {

    public SimpleSpinner(Context context) {
        super(context);
    }

    public SimpleSpinner(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void populate(Context context, String item) {
        List<String> list = new ArrayList<>();
        list.add(item);
        populate(context, list);
    }

    public void populate(Context context, List<String> spinnerItems) {
        ArrayAdapter<String> adapter = new ArrayAdapter<>(context, android.R.layout.simple_spinner_item, spinnerItems);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        this.setAdapter(adapter);
    }

    public void populate(Context context, @ArrayRes int textArrayResId) {
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(context, textArrayResId, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        this.setAdapter(adapter);
    }

}