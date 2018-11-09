package quyen.nguyenvustore.View.TrangChu.Fragment;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import quyen.nguyenvustore.Adapter.AdapterLinhKien;
import quyen.nguyenvustore.Adapter.AdapterThuongHieuLonLinhKien;
import quyen.nguyenvustore.Adapter.AdapterTopChuotLinhKien;
import quyen.nguyenvustore.Model.ObjectClass.LinhKien;
import quyen.nguyenvustore.Model.ObjectClass.SanPham;
import quyen.nguyenvustore.Model.ObjectClass.ThuongHieu;
import quyen.nguyenvustore.Presenter.TrangChu_LinhKien.PresenteLogicLinhKien;
import quyen.nguyenvustore.R;
import quyen.nguyenvustore.View.TrangChu.ViewLinhKien;

public class FragmentLinhKien extends Fragment implements ViewLinhKien {

    RecyclerView recyclerView, recyclerTopCacThuongHieuLon, recyclerHangMoiVe;
    List<LinhKien> linhKienList;
    PresenteLogicLinhKien presenteLogicLinhKien;
    ImageView imSanPham1,imSanPham2,imSanPham3;
    TextView txtSanPham1,txtSanPham2,txtSanPham3;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_linhkien, container, false);

        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerLinhKien);
        recyclerTopCacThuongHieuLon = (RecyclerView) view.findViewById(R.id.recylerTopCacThuongHieuLon);
        recyclerHangMoiVe = (RecyclerView) view.findViewById(R.id.recylerHangMoiVe);

        imSanPham1 = (ImageView) view.findViewById(R.id.imSanPham1);
        imSanPham2 = (ImageView) view.findViewById(R.id.imSanPham2);
        imSanPham3 = (ImageView) view.findViewById(R.id.imSanPham3);

        txtSanPham1 = (TextView) view.findViewById(R.id.txtSanPham1);
        txtSanPham2 = (TextView) view.findViewById(R.id.txtSanPham2);
        txtSanPham3 = (TextView) view.findViewById(R.id.txtSanPham3);

        presenteLogicLinhKien = new PresenteLogicLinhKien(this);

        linhKienList = new ArrayList<>();

        presenteLogicLinhKien.LayDanhSachLinhKien();
        presenteLogicLinhKien.LayDanhSachLogoThuongHieu();
        presenteLogicLinhKien.LayDanhSachSanPhamMoi();

        return view;
    }

    @Override
    public void HienThiDanhSach(List<LinhKien> linhKiens) {

        linhKienList = linhKiens;

        AdapterLinhKien adapterLinhKien = new AdapterLinhKien(getActivity(), linhKienList);//teclass.this
       // RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapterLinhKien);

        adapterLinhKien.notifyDataSetChanged();


    }

    @Override
    public void HienThiLogoThuongHieu(List<ThuongHieu> thuongHieus) {
        AdapterThuongHieuLonLinhKien adapterThuongHieuLonLinhKien = new AdapterThuongHieuLonLinhKien(getContext(), thuongHieus);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getContext(), 2, GridLayoutManager.HORIZONTAL, false);

        recyclerTopCacThuongHieuLon.setLayoutManager(layoutManager);
        recyclerTopCacThuongHieuLon.setAdapter(adapterThuongHieuLonLinhKien);
        adapterThuongHieuLonLinhKien.notifyDataSetChanged();
    }

    @Override
    public void LoiLayDuLieu() {

    }

    @Override
    public void HienThiSanPhamMoiVe(List<SanPham> sanPhams) {
        AdapterTopChuotLinhKien adapterTopChuotLinhKien = new AdapterTopChuotLinhKien(getContext(), R.layout.custom_layout_topchuotvabanphim, sanPhams);
        RecyclerView.LayoutManager layoutManagerTop = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);

        recyclerHangMoiVe.setLayoutManager(layoutManagerTop);
        recyclerHangMoiVe.setAdapter(adapterTopChuotLinhKien);

        adapterTopChuotLinhKien.notifyDataSetChanged();

        Random random = new Random();
        int vitri = random.nextInt(sanPhams.size());

        // Picasso.get().load(thuongHieu.getHINHTHUONGHIEU()).resize(180,90).centerInside().into(holder.imLogoThuongHieuLon);
        Picasso.get().load(sanPhams.get(vitri).getANHLON()).fit().centerInside().into(imSanPham1);
        txtSanPham1.setText(sanPhams.get(vitri).getTENSP());

        int vitri1 = random.nextInt(sanPhams.size());
        Picasso.get().load(sanPhams.get(vitri1).getANHLON()).fit().centerInside().into(imSanPham2);
        txtSanPham2.setText(sanPhams.get(vitri1).getTENSP());

        int vitri2 = random.nextInt(sanPhams.size());
        Picasso.get().load(sanPhams.get(vitri2).getANHLON()).fit().centerInside().into(imSanPham3);
        txtSanPham3.setText(sanPhams.get(vitri2).getTENSP());
    }
}
