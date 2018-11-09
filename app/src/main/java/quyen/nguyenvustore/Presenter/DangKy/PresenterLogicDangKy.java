package quyen.nguyenvustore.Presenter.DangKy;

import quyen.nguyenvustore.Model.DangNhap_DangKy.ModelDangKy;
import quyen.nguyenvustore.Model.ObjectClass.NhanVien;
import quyen.nguyenvustore.View.DangNhap_DangKy.ViewDangKy;

public class PresenterLogicDangKy implements IPresenterDangKy{
    ViewDangKy viewDangKy;
    ModelDangKy modelDangKy;

    public PresenterLogicDangKy (ViewDangKy viewDangKy) {
        this.viewDangKy = viewDangKy;
        modelDangKy = new ModelDangKy();
    }

    @Override
    public void ThucHienDangKy(NhanVien nhanvien) {
       // modelDangKy.DangKyThanhVien(nhanvien);
        boolean kiemtra = modelDangKy.DangKyThanhVien(nhanvien);
        if (kiemtra) {
            viewDangKy.DangKyThanhCong();
        }else {
            viewDangKy.DangKyThatBai();
        }
    }
}
