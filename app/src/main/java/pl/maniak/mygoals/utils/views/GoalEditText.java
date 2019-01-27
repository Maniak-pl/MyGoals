package pl.maniak.mygoals.utils.views;

import android.content.Context;
import android.support.v7.widget.AppCompatEditText;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;

import pl.maniak.mygoals.R;

public class GoalEditText extends AppCompatEditText {

    private boolean mValid = false;
    private OnTextChangeListener listener;

    public GoalEditText(Context context) {
        super(context);
        init();
    }

    public GoalEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public GoalEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {

        this.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                checkValidate();
                if (listener != null) {
                    listener.onTextChanged(editable.toString());
                }
            }
        });
        checkValidate();
    }

    private void checkValidate() {
        if (this.getText().toString().isEmpty()) {
            setErrorIcon();
            mValid = false;
        } else {
            setCheckIcon();
            mValid = true;
        }
    }

    private void setCheckIcon() {
        this.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_check, 0);
        setLayoutBackground(R.drawable.edit_text_green_bg);
    }

    private void setErrorIcon() {
        this.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_error, 0);
        setLayoutBackground(R.drawable.edit_text_red_bg);
    }

    private void setLayoutBackground(int drawableId) {
        this.setBackgroundResource(drawableId);
        int padding = getResources().getDimensionPixelOffset(R.dimen.edit_text_layout_padding);
        this.setCompoundDrawablePadding(padding);
        this.setPadding(padding, padding, padding, padding);
        requestFocusFromTouch();
    }

    public boolean isValid() {
        return mValid;
    }

    public void setListener(OnTextChangeListener listener) {
        this.listener = listener;
    }

    public interface OnTextChangeListener {
        void onTextChanged(String str);
    }
}
