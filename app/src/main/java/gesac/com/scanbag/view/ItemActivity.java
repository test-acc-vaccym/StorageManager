package gesac.com.scanbag.view;

import android.app.AlertDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.ListViewCompat;
import android.text.InputType;
import android.util.Log;
import android.view.KeyEvent;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;

import gesac.com.R;
import gesac.com.scanbag.model.IJournal;
import gesac.com.scanbag.model.Journal;
import gesac.com.scanbag.presenter.IItemPresenter;
import gesac.com.scanbag.presenter.ItemPresenterCompl;
import gesac.com.splitbag.model.IBag;
import gesac.com.splitbag.presenter.ISplitPresenter;
import gesac.com.splitbag.presenter.SplitPresenterCompl;

public class ItemActivity extends AppCompatActivity implements IItemVIew {
    private EditText mItemstr;
    private TextView mJourid;
    private ListView mItemlist;

    private IItemPresenter iItemPresenter;
    private IBag iBag;
    private IJournal iJournal;
    private String strcode;
    private ItemAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item);
        Intent it = getIntent();
        iJournal = new Gson().fromJson(it.getStringExtra("jour"), Journal.class);
        Log.d("debug", String.valueOf(iJournal.getItemlist().size()));
        bindViews();
        iItemPresenter = new ItemPresenterCompl(this);
    }

    private void bindViews() {
        mItemstr = (EditText) findViewById(R.id.itemstr);
        mJourid = (TextView) findViewById(R.id.jourid);
        mItemlist = (ListView) findViewById(R.id.itemlist);
        mJourid.setText(iJournal.getJourid());

        adapter = new ItemAdapter(this, iJournal.getItemlist());
        mItemlist.setAdapter(adapter);
        mItemstr.setInputType(InputType.TYPE_NULL);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_F8) {
            mItemstr.requestFocus();
            mItemstr.selectAll();
            return super.onKeyDown(keyCode, event);
        }
        return super.onKeyDown(keyCode, event);

    }

    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_F8) {
            // 1、获取二维码2、拆分字符串3、匹配物料编号
            mItemstr.postInvalidate();
            strcode = mItemstr.getText().toString();
            iBag = iItemPresenter.subString(strcode);
            //TODO iBag与list比较
            if (iBag != null) {
                int isin = iItemPresenter.isInJour(iBag, iJournal);
                if (isin != 0) {
                    //TODO 若匹配则按钮可按
                    iJournal.getItemlist().get(isin).setIsin(1);
                    adapter.notifyDataSetChanged();
                } else new AlertDialog.Builder(this)
                        .setMessage("匹配错误")
                        .setPositiveButton("确定", null)
                        .show();
            }
        }
        return super.onKeyUp(keyCode, event);
    }
}
