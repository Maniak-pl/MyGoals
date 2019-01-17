package pl.maniak.mygoals.ui;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import butterknife.ButterKnife;

public abstract class BaseActivity extends AppCompatActivity implements BaseContract.Router, BaseContract.View {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        ButterKnife.bind(this);

        initDaggerComponent();
        init();
    }

    protected abstract void init();

    protected abstract void initDaggerComponent();

    @LayoutRes
    protected abstract int getLayoutId();

    @Override
    protected void onDestroy() {
        clear();
        super.onDestroy();
    }

    protected abstract void clear();
}
