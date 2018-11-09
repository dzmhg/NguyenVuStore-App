package quyen.nguyenvustore.View.DanhGia;

import java.util.List;

import quyen.nguyenvustore.Model.ObjectClass.DanhGia;

public interface ViewDanhGia {
    void DanhGiaThanhCong();
    void DanhGiaThatBai();
    void HienThiDanhSachDanhGiaTheoSanPham(List<DanhGia> danhGiaList);
}
