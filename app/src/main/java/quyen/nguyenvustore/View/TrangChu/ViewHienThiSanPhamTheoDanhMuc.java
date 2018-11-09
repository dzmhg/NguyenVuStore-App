package quyen.nguyenvustore.View.TrangChu;
import java.util.List;

import quyen.nguyenvustore.Model.ObjectClass.SanPham;

public interface ViewHienThiSanPhamTheoDanhMuc {
    void HienThiDanhSachSanPham(List<SanPham> sanPhamList);
    void LoiHienThiDanhSachSanPham();
}
