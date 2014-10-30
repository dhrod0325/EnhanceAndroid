package kr.co.saweb.enhance.android.helper.ui;

import android.view.View;
import android.widget.RadioButton;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by OKS on 2014-10-13.
 */
public class RadioButtonEventRegister {
    private static RadioButtonEventRegister instance = new RadioButtonEventRegister();

    public static RadioButtonEventRegister getInstance() {
        return instance;
    }

    public List<RadioButton> radioButtonList = new ArrayList<RadioButton>();

    public void registerRadioButton(RadioButton radioButton) {
        radioButtonList.add(radioButton);
    }

    public void unRegisterRadioButton(RadioButton radioButton) {
        radioButtonList.remove(radioButton);
    }

    public void setAllChecked(boolean checked) {
        for (RadioButton radioButton : radioButtonList) {
            radioButton.setChecked(checked);
        }
    }

    public void setOnCheckedChangeListener(final OnCheckedChangeListener onCheckedChangeListener) {
        for (final RadioButton radioButton : radioButtonList) {
            radioButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    setAllChecked(false);
                    radioButton.setChecked(true);

                    onCheckedChangeListener.changed(radioButton);
                }
            });
        }
    }

    public void clear() {
        radioButtonList.clear();
    }

    public interface OnCheckedChangeListener {
        public void changed(RadioButton radioButton);
    }
}