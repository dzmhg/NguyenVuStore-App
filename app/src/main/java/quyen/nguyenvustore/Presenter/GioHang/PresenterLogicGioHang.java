package quyen.nguyenvustore.Presenter.GioHang;

import android.content.Context;
import java.util.List;

import quyen.nguyenvustore.Model.GioHang.ModelGioHang;
import quyen.nguyenvustore.Model.ObjectClass.SanPham;
import quyen.nguyenvustore.View.GioHang.GioHangActivity;
import quyen.nguyenvustore.View.GioHang.ViewGioHang;

public class PresenterLogicGioHang implements IPresenterGioHang {

    ModelGioHang modelGioHang;
    ViewGioHang viewGioHang;

    public PresenterLogicGioHang(ViewGioHang viewGioHang){
        modelGioHang = new ModelGioHang();
        this.viewGioHang = viewGioHang;
    }

    public PresenterLogicGioHang(GioHangActivity gioHangActivity) {
    }

    @Override
    public void LayDanhSachSanPhamTrongGioHang(Context context) {
        modelGioHang.MoKetNoiSQL(context);
        List<SanPham> sanPhamList = modelGioHang.LayDanhSachSanPhamTrongGioHang();
        if(sanPhamList.size() > 0){
            viewGioHang.HienThiDanhSachSanPhamTrongGioHang(sanPhamList);
        }
    }
}
