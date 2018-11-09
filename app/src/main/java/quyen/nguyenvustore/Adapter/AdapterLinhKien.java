package quyen.nguyenvustore.Adapter;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import quyen.nguyenvustore.Model.ObjectClass.LinhKien;
import quyen.nguyenvustore.R;

public class AdapterLinhKien extends RecyclerView.Adapter<AdapterLinhKien.ViewHolderDienTu> {
    Context context;
    List<LinhKien> linhKienList;

    public AdapterLinhKien(Context context, List<LinhKien> linhKienList) {
        this.context = context;
        this.linhKienList = linhKienList;
    }

    public class ViewHolderDienTu extends RecyclerView.ViewHolder {
        ImageView imHinhKhuyenMaiDienTu;
        RecyclerView recyclerViewThuongHieuLon, recyclerViewTopSanPham;
        TextView txtTieuDeSanPhamNoiBat, txtTopSanPhamNoiBat;

        public ViewHolderDienTu(View itemView) {
            super(itemView);

            recyclerViewThuongHieuLon = (RecyclerView) itemView.findViewById(R.id.recyclerThuongHieuLon);
            recyclerViewTopSanPham = (RecyclerView) itemView.findViewById(R.id.recyclerTopChuotVaBanPhim);
            imHinhKhuyenMaiDienTu = (ImageView) itemView.findViewById(R.id.imKhuyenMaiLinhKien);
            txtTopSanPhamNoiBat = (TextView) itemView.findViewById(R.id.txtTenSanPhamNoiBat);
            txtTieuDeSanPhamNoiBat = (TextView) itemView.findViewById(R.id.txtTenTopSanPhamNoiBat);

        }

    }

    @Override
    public ViewHolderDienTu onCreateViewHolder( ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.custom_layout_recyclerview_linhkien, parent, false);

        ViewHolderDienTu viewHolderDienTu = new ViewHolderDienTu(view);

        return viewHolderDienTu;
    }

    @Override
    public void onBindViewHolder( ViewHolderDienTu holder, int position) {

        LinhKien linhKien = linhKienList.get(position);

        holder.txtTieuDeSanPhamNoiBat.setText(linhKien.getTennoibat().toString());
        holder.txtTopSanPhamNoiBat.setText(linhKien.getTentopnoibat().toString());

        //Xu ly hien thi danh sach thuong hieu lon Recyclerview Thuong Hieu Lon
        AdapterThuongHieuLon adapterThuongHieuLon = new AdapterThuongHieuLon(context, linhKien.getThuongHieus(), linhKien.isThuonghieu());
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(context, 3, GridLayoutManager.HORIZONTAL, false);

        holder.recyclerViewThuongHieuLon.setLayoutManager(layoutManager);

        holder.recyclerViewThuongHieuLon.setAdapter(adapterThuongHieuLon);
        adapterThuongHieuLon.notifyDataSetChanged();

        //Xu ly hien thi danh sach top san pham Recyclerview Top San Pham

        AdapterTopChuotLinhKien adapterTopChuotLinhKien = new AdapterTopChuotLinhKien(context, R.layout.custom_layout_topchuotvabanphim, linhKien.getSanPhams());

        RecyclerView.LayoutManager layoutManagerTop = new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false);

        holder.recyclerViewTopSanPham.setLayoutManager(layoutManagerTop);
        holder.recyclerViewTopSanPham.setAdapter(adapterTopChuotLinhKien);
    }

    @Override
    public int getItemCount() {
        return linhKienList.size();
    }
}
