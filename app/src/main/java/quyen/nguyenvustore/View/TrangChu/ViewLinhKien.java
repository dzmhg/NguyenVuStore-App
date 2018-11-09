package quyen.nguyenvustore.View.TrangChu;

import java.util.List;

import quyen.nguyenvustore.Model.ObjectClass.LinhKien;
import quyen.nguyenvustore.Model.ObjectClass.SanPham;
import quyen.nguyenvustore.Model.ObjectClass.ThuongHieu;

public interface ViewLinhKien {
    void HienThiDanhSach(List<LinhKien> linhKiens);
    void HienThiLogoThuongHieu(List<ThuongHieu> thuongHieus);
    void LoiLayDuLieu();
    void HienThiSanPhamMoiVe(List<SanPham> sanPhams);


}
