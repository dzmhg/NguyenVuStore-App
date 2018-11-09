package quyen.nguyenvustore.View.ChiTietSanPham;

import java.util.List;

import quyen.nguyenvustore.Model.ObjectClass.DanhGia;
import quyen.nguyenvustore.Model.ObjectClass.SanPham;

public interface ViewChiTietSanPham {
    void HienChiTietSanPham(SanPham sanPham);
    void HienSliderSanPham(String[] linkhinhsanpham);
    void HienThiDanhGia(List<DanhGia> danhGiaList);
    void ThemGioHangThanhCong();
    void ThemGiohangThatBai();
}
