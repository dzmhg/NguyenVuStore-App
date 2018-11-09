package quyen.nguyenvustore.Presenter.DanhGia;

import android.widget.ProgressBar;

import quyen.nguyenvustore.Model.ObjectClass.DanhGia;

public interface IPresenterDanhGia {
    void ThemDanhGia(DanhGia danhGia);
    void LayDanhSachDanhGiaCuaSanPham(int masp, int limit, ProgressBar progressBar);
}
