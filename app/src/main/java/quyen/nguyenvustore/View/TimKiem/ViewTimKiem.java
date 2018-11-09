package quyen.nguyenvustore.View.TimKiem;

import java.util.List;

import quyen.nguyenvustore.Model.ObjectClass.SanPham;

public interface ViewTimKiem {
    void TimKiemThanhCong(List<SanPham> sanPhamList);
    void TimKiemThatBai();
}
