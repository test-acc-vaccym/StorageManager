package gesac.com.home.view;

import android.app.Activity;
import android.app.AlertDialog;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import gesac.com.R;
import gesac.com.chkstock.StockActivity;
import gesac.com.databinding.ActivityHomeBinding;
import gesac.com.home.model.Bluetooth;
import gesac.com.home.presenter.BluettPersentCompl;
import gesac.com.home.presenter.IBluettPersenter;
import gesac.com.locstock.LocStockActivity;
import gesac.com.pickitem.HdwPickActivity;
import gesac.com.scanbag.view.ScanActivity;
import gesac.com.uitity.BHTApplication;

public class HomeActivity extends Activity implements IBluettView {
    // Content View Elements
    ActivityHomeBinding binding;

    private List<Bluetooth> bluetoothList = new ArrayList<>();
    private IBluettPersenter iBluettPersenter;
    private BHTApplication bhtapp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initViews();
        iBluettPersenter = new BluettPersentCompl(this);
    }

    private void initViews() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_home);
        bhtapp = (BHTApplication) getApplication();

        binding.scanHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String[] params = {"产品领料", "五金领料"};
                new AlertDialog.Builder(HomeActivity.this)
                        .setItems(params, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Intent it;
                                switch (which) {
                                    case 0:
                                        it = new Intent(HomeActivity.this, ScanActivity.class);
                                        startActivity(it);
                                        break;
                                    case 1:
                                        it = new Intent(HomeActivity.this, HdwPickActivity.class);
                                        startActivity(it);
                                        break;
                                }
                            }
                        })
                        .show();

            }
        });
        binding.chkHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(HomeActivity.this, StockActivity.class);
                startActivity(it);
            }
        });
        binding.locHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(HomeActivity.this, LocStockActivity.class);
                startActivity(it);
            }
        });

//        mBluetooth.setOnClickListener(new View.OnClickListener() {
//            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
//            @Override
//            public void onClick(View view) {
//                //TODO 打印机设置
//                bluetoothList = iBluettPersenter.getAllbluett();
//                if (bluetoothList != null) {
//                    LayoutInflater mInflater = LayoutInflater.from(HomeActivity.this);
//                    View bluettview = mInflater.inflate(R.layout.dialog_bluett, null);
//                    ListView lv = (ListView) bluettview.findViewById(R.id.bluettlist);
//                    lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//                        @Override
//                        public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
//                            boolean isbind = iBluettPersenter.bindBluett(bluetoothList.get(position).getBtaddr());
//                            if (isbind)
//                                bhtapp.setBhtstr(bluetoothList.get(position).getBtaddr());
//                            //else //TODO 蓝牙绑定错误处理
//                        }
//                    });
//                    BaseAdapter adapter = new BluettAdapter(HomeActivity.this, bluetoothList);
//                    lv.setAdapter(adapter);
//                    new AlertDialog.Builder(HomeActivity.this)
//                            .setView(bluettview)
//                            .create()
//                            .show();
//                } else Toast.makeText(HomeActivity.this, "请打开蓝牙", Toast.LENGTH_SHORT).show();
//            }
//        });

    }

    @Override
    public void openBHT(Intent it) {
        startActivityForResult(it, 0);
    }

    @Override
    public void receiveBHT() {
        BroadcastReceiver mReceive = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                String action = intent.getAction();
                if (action.equals(BluetoothDevice.ACTION_FOUND)) {
                    BluetoothDevice device = intent
                            .getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
                    // 搜索到的蓝牙设备
                    if (device.getBondState() != BluetoothDevice.BOND_BONDED) {
                        bluetoothList.add(new Bluetooth(device.getAddress(), device.getName(), device.getBondState()));
                    }
                    // 搜索完成
                }
            }
        };
    }
}
