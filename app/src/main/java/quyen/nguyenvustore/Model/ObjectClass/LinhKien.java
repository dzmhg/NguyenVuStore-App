package quyen.nguyenvustore.Model.ObjectClass;

import java.util.List;

public class LinhKien {
    List<ThuongHieu> thuongHieus;
    List<SanPham> sanPhams;

    boolean thuonghieu;
    String tennoibat, tentopnoibat;


    public List<ThuongHieu> getThuongHieus() {
        return thuongHieus;
    }

    public void setThuongHieus(List<ThuongHieu> thuongHieus) {
        this.thuongHieus = thuongHieus;
    }

    public List<SanPham> getSanPhams() {
        return sanPhams;
    }

    public void setSanPhams(List<SanPham> sanPhams) {
        this.sanPhams = sanPhams;
    }

    public boolean isThuonghieu() {
        return thuonghieu;
    }

    public void setThuonghieu(boolean thuonghieu) {
        this.thuonghieu = thuonghieu;
    }

    public String getTennoibat() {
        return tennoibat;
    }

    public void setTennoibat(String tennoibat) {
        this.tennoibat = tennoibat;
    }

    public String getTentopnoibat() {
        return tentopnoibat;
    }

    public void setTentopnoibat(String tentopnoibat) {
        this.tentopnoibat = tentopnoibat;
    }

    /*

    public String getHinhSanPham() {
        return HinhSanPham;
    }

    public LinhKien setHinhSanPham(String hinhSanPham) {
        HinhSanPham = hinhSanPham;
        return this;
    }



    public List<SanPham> getSanPhams() {
        return sanPhams;
    }

    public LinhKien setSanPhams(List<SanPham> sanPhams) {
        this.sanPhams = sanPhams;
        return this;
    }

    public List<ThuongHieu> getThuongHieus() {
        return thuongHieus;
    }

    public LinhKien setThuongHieus(List<ThuongHieu> thuongHieus) {
        this.thuongHieus = thuongHieus;
        return this;
    } */
}