package quyen.nguyenvustore.Presenter.TrangChu_LinhKien;

import java.util.ArrayList;
import java.util.List;

import quyen.nguyenvustore.Model.ObjectClass.LinhKien;
import quyen.nguyenvustore.Model.ObjectClass.SanPham;
import quyen.nguyenvustore.Model.ObjectClass.ThuongHieu;
import quyen.nguyenvustore.Model.TrangChu_LinhKien.ModelLinhKien;
import quyen.nguyenvustore.View.TrangChu.ViewLinhKien;

public class PresenteLogicLinhKien implements IPresenterLinhKien {

    ViewLinhKien viewLinhKien;
    ModelLinhKien modelLinhKien;

    public PresenteLogicLinhKien(ViewLinhKien viewLinhKien) {
        this.viewLinhKien = viewLinhKien;
        modelLinhKien = new ModelLinhKien();
    }

    @Override
    public void LayDanhSachLinhKien() {
        List<LinhKien> linhKiens = new ArrayList<>();

        List<ThuongHieu> thuongHieuList = modelLinhKien.LayDanhSachThuongHieuLon("LayDanhSachThuongHieuLon", "DanhSachThuongHieu");

        List<SanPham> sanPhamList = modelLinhKien.LayDanhSachSanPhamTOP("LayDanhSachTopChuotVaBanPhim", "TopChuotVaBanPhim");

        LinhKien linhKien = new LinhKien();
        linhKien.setThuongHieus(thuongHieuList);
        linhKien.setSanPhams(sanPhamList);

        linhKien.setTennoibat("Thương hiệu lớn");
        linhKien.setTentopnoibat("Top Chuột và bàn phím");
        linhKien.setThuonghieu(true);
        linhKiens.add(linhKien);

        List<SanPham> phukienList = modelLinhKien.LayDanhSachSanPhamTOP("LayDanhSachTopPhuKien", "TOPPHUKIEN");
        List<ThuongHieu> topphukienList = modelLinhKien.LayDanhSachThuongHieuLon("LayDanhSachPhuKien", "DANHSACHPHUKIEN");

        LinhKien linhKien1 = new LinhKien();
        linhKien1.setThuongHieus(topphukienList);
        linhKien1.setSanPhams(phukienList);
        linhKien1.setTennoibat("Phụ kiện");
        linhKien1.setTentopnoibat("Top phụ kiện");
        linhKien1.setThuonghieu(false);
        linhKiens.add(linhKien1);

        List<SanPham> tienichList = modelLinhKien.LayDanhSachSanPhamTOP("LayTopTienIch", "TOPTIENICH");
        List<ThuongHieu> toptienichList = modelLinhKien.LayDanhSachThuongHieuLon("LayDanhSachTienIch", "DANHSACHTIENICH");

        LinhKien linhKien2 = new LinhKien();
        linhKien2.setThuongHieus(toptienichList);
        linhKien2.setSanPhams(tienichList);
        linhKien2.setTennoibat("Tiện ích");
        linhKien2.setTentopnoibat("Top Case & Monitor");
        linhKien2.setThuonghieu(false);
        linhKiens.add(linhKien2);

        if (thuongHieuList.size() > 0 && sanPhamList.size() > 0) {
            viewLinhKien.HienThiDanhSach(linhKiens);
        }
    }

    @Override
    public void LayDanhSachLogoThuongHieu() {
        List<ThuongHieu> thuongHieuList = modelLinhKien.LayDanhSachThuongHieuLon("LayLogoThuongHieuLon", "DANHSACHTHUONGHIEU");
        if (thuongHieuList.size() >0) {
            viewLinhKien.HienThiLogoThuongHieu(thuongHieuList);
        }else {
            viewLinhKien.LoiLayDuLieu();
        }

    }

    @Override
    public void LayDanhSachSanPhamMoi() {
        List<SanPham> sanPhamList = modelLinhKien.LayDanhSachSanPhamTOP("LayDanhSachSanPhamMoi", "DANHSACHSANPHAMMOIVE");
        if (sanPhamList.size() > 0) {
            viewLinhKien.HienThiSanPhamMoiVe(sanPhamList);
        }else {
            viewLinhKien.LoiLayDuLieu();
        }
    }
}
