package gesac.com.scanbag.model;

import android.databinding.BaseObservable;
import android.view.View;
import android.widget.Toast;

/**
 * Created by GE11522 on 2017/4/14.
 */
public class Item extends BaseObservable {
    String itemid, itemqlty, itemtol, itembc, itemseri, itemqty, itemwt, itemst, itemslc;
    int isin;

    public Item(String itemid, String itemqlty, String itemtol, String itembc, String itemseri, String itemqty, String itemwt, String itemst, String itemslc) {
        this.itemid = itemid; //物料编号
        this.itemqlty = itemqlty; //质量号
        this.itemtol = itemtol; //公差
        this.itembc = itembc; //批处理号
        this.itemseri = itemseri; //序列号
        this.itemqty = itemqty; //数量
        this.itemwt = itemwt; //重量
        this.itemst = itemst; //仓库
        this.itemslc = itemslc; //库位
        isin = 0;
    }

    public Item() {
    }

    public String getItemid() {
        return itemid;
    }

    public void setItemid(String itemid) {
        this.itemid = itemid;
    }

    public String getItemqlty() {
        return itemqlty;
    }

    public void setItemqlty(String itemqlty) {
        this.itemqlty = itemqlty;
    }

    public String getItemtol() {
        return itemtol;
    }

    public void setItemtol(String itemtol) {
        this.itemtol = itemtol;
    }

    public String getItembc() {
        return itembc;
    }

    public void setItembc(String itembc) {
        this.itembc = itembc;
    }

    public String getItemseri() {
        return itemseri;
    }

    public void setItemseri(String itemseri) {
        this.itemseri = itemseri;
    }

    public String getItemqty() {
        return itemqty;
    }

    public void setItemqty(String itemqty) {
        this.itemqty = itemqty;
    }

    public String getItemwt() {
        return itemwt;
    }

    public void setItemwt(String itemwt) {
        this.itemwt = itemwt;
    }

    public String getItemst() {
        return itemst;
    }

    public void setItemst(String itemst) {
        this.itemst = itemst;
    }

    public String getItemslc() {
        return itemslc;
    }

    public void setItemslc(String itemslc) {
        this.itemslc = itemslc;
    }

    public int getIsin() {
        return isin;
    }

    public void setIsin(int isin) {
        this.isin = isin;
    }

    public void onPickClick(View v) {
        Toast.makeText(v.getContext(), getItemid(), Toast.LENGTH_SHORT).show();

    }

}
