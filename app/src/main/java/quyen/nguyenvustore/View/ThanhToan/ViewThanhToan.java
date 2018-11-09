package quyen.nguyenvustore.View.ThanhToan;

import java.util.List;

import quyen.nguyenvustore.Model.ObjectClass.SanPham;

public interface ViewThanhToan {
    void DatHangThanhCong();
    void DatHangThatBai();
    void LayDanhSachSanPhamTrongGioHang(List<SanPham> sanPhamList);
}
