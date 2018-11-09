package quyen.nguyenvustore.View.ChiTietSanPham;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

import quyen.nguyenvustore.Adapter.AdapterDanhGia;
import quyen.nguyenvustore.Adapter.AdapterViewPagerSlider;
import quyen.nguyenvustore.Model.ObjectClass.ChiTietKhuyenMai;
import quyen.nguyenvustore.Model.ObjectClass.ChiTietSanPham;
import quyen.nguyenvustore.Model.ObjectClass.DanhGia;
import quyen.nguyenvustore.Model.ObjectClass.SanPham;
import quyen.nguyenvustore.Presenter.ChiTietSanPham.FragmentSliderChiTietSanPham;
import quyen.nguyenvustore.Presenter.ChiTietSanPham.PresenterLogicChiTietSanPham;
import quyen.nguyenvustore.R;
import quyen.nguyenvustore.View.DanhGia.DanhSachDanhGiaActivity;
import quyen.nguyenvustore.View.DanhGia.ThemDanhGiaActivity;
import quyen.nguyenvustore.View.GioHang.GioHangActivity;
import quyen.nguyenvustore.View.ThanhToan.ThanhToanActivity;
import quyen.nguyenvustore.View.TrangChu.TrangChuActivity;

public class ChiTietSanPhamActivity extends AppCompatActivity implements ViewChiTietSanPham, ViewPager.OnPageChangeListener,View.OnClickListener {

    ViewPager viewPager;
    PresenterLogicChiTietSanPham presenterLogicChiTietSanPham;
    TextView[] txtDots;
    LinearLayout layoutDots;
    List<Fragment> fragmentList;
    TextView txtTenSanPham,txtGiaTien,txtTenCHDongGoi,txtThongTinChiTiet,txtXemTatCaNhanXet,txtGioHang,txtGiamGia;
    Toolbar toolbar;
    ImageView imXemThemChiTiet,imThemGioHang;
    Button btnMuaNgay;
    boolean kiemtraxochitiet = false;
    LinearLayout lnThongSoKyThuat;
    TextView txtVietDanhGia;
    int masp;
    List<DanhGia> danhGiaList;
    RecyclerView recyclerDanhGiaChiTiet;
    SanPham sanPhamGioHang;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_chitietsanpham);

        viewPager = (ViewPager) findViewById(R.id.viewpagerSlider);
        layoutDots = (LinearLayout) findViewById(R.id.layoutDots);
        txtTenSanPham = (TextView) findViewById(R.id.txtTenSanPham);
        txtGiaTien = (TextView) findViewById(R.id.txtGiaTien);
        txtTenCHDongGoi = (TextView) findViewById(R.id.txtTenCHDongGoi);
        txtGiamGia = (TextView) findViewById(R.id.txtGiamGia);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        txtThongTinChiTiet = (TextView) findViewById(R.id.txtThongTinChiTiet);
        imXemThemChiTiet = (ImageView) findViewById(R.id.imXemThemChiTiet);
        lnThongSoKyThuat = (LinearLayout) findViewById(R.id.lnThongSoKyThuat);
        txtVietDanhGia = (TextView) findViewById(R.id.txtVietDanhGia);
        recyclerDanhGiaChiTiet = (RecyclerView) findViewById(R.id.recyclerDanhGiaChiTiet);
        txtXemTatCaNhanXet = (TextView) findViewById(R.id.txtXemTatCaNhanXet);
        imThemGioHang = (ImageView) findViewById(R.id.imThemGioHang);
        btnMuaNgay = (Button) findViewById(R.id.btnMuaNgay);

        setSupportActionBar(toolbar);

        masp = getIntent().getIntExtra("masp",0);

        presenterLogicChiTietSanPham = new PresenterLogicChiTietSanPham(this);
        presenterLogicChiTietSanPham.LayChiTietSanPham(masp);
        presenterLogicChiTietSanPham.LayDanhSachDanhGiaTheoCuaSanPham(masp,0);

        txtVietDanhGia.setOnClickListener(this);
        txtXemTatCaNhanXet.setOnClickListener(this);
        imThemGioHang.setOnClickListener(this);
        btnMuaNgay.setOnClickListener(this);
    }

    @Override
    public void HienChiTietSanPham(final SanPham sanPham) {
        masp = sanPham.getMASP();

        sanPhamGioHang = sanPham;
        sanPhamGioHang.setSOLUONGTONKHO(sanPham.getSOLUONG());

        txtTenSanPham.setText(sanPham.getTENSP());

        int giatien = sanPham.getGIA();
        ChiTietKhuyenMai chiTietKhuyenMai = sanPham.getChiTietKhuyenMai();

        if(chiTietKhuyenMai !=null){
            int phamtramkm = chiTietKhuyenMai.getPHANTRAMKM();

            if(phamtramkm != 0){
                NumberFormat numberFormat = new DecimalFormat("###,###");
                String gia = numberFormat.format(giatien);
                txtGiamGia.setVisibility(View.VISIBLE);
                txtGiamGia.setPaintFlags(txtGiamGia.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
                txtGiamGia.setText(gia + " VNĐ");

                giatien = giatien * phamtramkm/100;
            }

        }

        NumberFormat numberFormat = new DecimalFormat("###,###");
        String gia = numberFormat.format(giatien);
        txtGiaTien.setText(gia + " VNĐ");
        txtTenCHDongGoi.setText(sanPham.getTENNHANVIEN());
        txtThongTinChiTiet.setText(sanPham.getTHONGTIN().substring(0,100));


        if(sanPham.getTHONGTIN().length() < 100){
            imXemThemChiTiet.setVisibility(View.GONE);
        }else{
            imXemThemChiTiet.setVisibility(View.VISIBLE);

            imXemThemChiTiet.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    kiemtraxochitiet = !kiemtraxochitiet;
                    if(kiemtraxochitiet){
                        //sau khi mở chi tiết
                        txtThongTinChiTiet.setText(sanPham.getTHONGTIN());
                        imXemThemChiTiet.setImageDrawable(getHinhChiTiet(R.drawable.ic_keyboard_arrow_up_black_24dp));
                        lnThongSoKyThuat.setVisibility(View.VISIBLE);
                        HienThiThongSoKyThuat(sanPham);
                    }else{
                        //đóng chi tiết
                        txtThongTinChiTiet.setText(sanPham.getTHONGTIN().substring(0,100));
                        imXemThemChiTiet.setImageDrawable(getHinhChiTiet(R.drawable.ic_keyboard_arrow_down_black_24dp));
                        lnThongSoKyThuat.setVisibility(View.GONE);
                    }
                }
            });
        }


    }

    private void HienThiThongSoKyThuat(SanPham sanPham){
        List<ChiTietSanPham> chiTietSanPhams = sanPham.getChiTietSanPhamList();
        lnThongSoKyThuat.removeAllViews();

        TextView txtTieuDeThongSoKyThuat = new TextView(this);
        txtTieuDeThongSoKyThuat.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        lnThongSoKyThuat.addView(txtTieuDeThongSoKyThuat);

        for (int i=0 ;i<chiTietSanPhams.size(); i++){
            LinearLayout lnChiTiet = new LinearLayout(this);
            lnChiTiet.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            lnChiTiet.setOrientation(LinearLayout.HORIZONTAL);

            TextView txtTenThongSo = new TextView(this);
            txtTenThongSo.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT,1.0f));
            txtTenThongSo.setText(chiTietSanPhams.get(i).getTENCHITIET());

            TextView txtGiaTriThongSo = new TextView(this);
            txtGiaTriThongSo.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT,1.0f));
            txtGiaTriThongSo.setText(chiTietSanPhams.get(i).getGIATRI());

            lnChiTiet.addView(txtTenThongSo);
            lnChiTiet.addView(txtGiaTriThongSo);

            lnThongSoKyThuat.addView(lnChiTiet);
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menutrangchu,menu);

        MenuItem iGioHang = menu.findItem(R.id.itGioHang);
        View giaoDienCustomGioHang = MenuItemCompat.getActionView(iGioHang);
        txtGioHang = (TextView) giaoDienCustomGioHang.findViewById(R.id.txtSoLuongSanPhamGioHang);

        txtGioHang.setText(String.valueOf(presenterLogicChiTietSanPham.DemSanPhamCoTrongGioHang(this)));

        giaoDienCustomGioHang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent iGioHang = new Intent(ChiTietSanPhamActivity.this, GioHangActivity.class);
                startActivity(iGioHang);
            }
        });

        return true;
    }

    @Override
    public void HienSliderSanPham(String[] linkhinhsanpham) {
       fragmentList = new ArrayList<>();

        for (int i=0;i<linkhinhsanpham.length ;i++){
            FragmentSliderChiTietSanPham fragmentSliderChiTietSanPham = new FragmentSliderChiTietSanPham();
            Bundle bundle = new Bundle();
            bundle.putString("linkhinh",TrangChuActivity.SERVER + linkhinhsanpham[i]);
            fragmentSliderChiTietSanPham.setArguments(bundle);

            fragmentList.add(fragmentSliderChiTietSanPham);

        }

        AdapterViewPagerSlider adapterViewPagerSlider = new AdapterViewPagerSlider(getSupportFragmentManager(),fragmentList);
        viewPager.setAdapter(adapterViewPagerSlider);
        adapterViewPagerSlider.notifyDataSetChanged();

        ThemDotSlider(0);
        viewPager.addOnPageChangeListener(this);
    }



    private void ThemDotSlider(int vitrihientai){
        txtDots = new TextView[fragmentList.size()];

        layoutDots.removeAllViews();
        for (int i=0 ; i<fragmentList.size(); i++){
            txtDots[i] = new TextView(this);
            txtDots[i].setText(Html.fromHtml("&#8226;"));
            txtDots[i].setTextSize(40);
            txtDots[i].setTextColor(getIdColor(R.color.colorSliderInActive));

            layoutDots.addView(txtDots[i]);
        }

        txtDots[vitrihientai].setTextColor(getIdColor(R.color.bgToolbar));
    }

    private Drawable getHinhChiTiet(int idDrawable){
        Drawable drawable;
        if(Build.VERSION.SDK_INT > 21){
            drawable = ContextCompat.getDrawable(this,idDrawable);
        }else{
            drawable = getResources().getDrawable(idDrawable);
        }

        return drawable;
    }

    private int getIdColor(int idcolor){

        int color =0;
        if(Build.VERSION.SDK_INT > 21){
            color = ContextCompat.getColor(this,idcolor);
        }else{
            color = getResources().getColor(idcolor);
        }

        return color;
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        ThemDotSlider(position);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id){
            case R.id.txtVietDanhGia:
                Intent iThemDanhGia = new Intent(this, ThemDanhGiaActivity.class);
                iThemDanhGia.putExtra("masp",masp);
                startActivity(iThemDanhGia);
                ;break;

            case R.id.txtXemTatCaNhanXet:
                Intent iDanhSachDanhGia = new Intent(ChiTietSanPhamActivity.this, DanhSachDanhGiaActivity.class);
                iDanhSachDanhGia.putExtra("masp",masp);
                startActivity(iDanhSachDanhGia);
                break;

            case R.id.imThemGioHang:
                Fragment fragment = fragmentList.get(0);
                View view = fragment.getView();
                ImageView imageView = (ImageView) view.findViewById(R.id.imHinhSlider);
                Bitmap bitmap = ((BitmapDrawable)imageView.getDrawable()).getBitmap();

                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.PNG,100,byteArrayOutputStream);
                byte[] hinhsanphamgiohang = byteArrayOutputStream.toByteArray();

                sanPhamGioHang.setHinhgiohang(hinhsanphamgiohang);
                sanPhamGioHang.setSOLUONG(1);

                presenterLogicChiTietSanPham.ThemGioHang(sanPhamGioHang,this);
                break;

            case R.id.btnMuaNgay:
                Fragment fragment1 = fragmentList.get(0);
                View view1 = fragment1.getView();
                ImageView imageView1 = (ImageView) view1.findViewById(R.id.imHinhSlider);
                Bitmap bitmap1 = ((BitmapDrawable)imageView1.getDrawable()).getBitmap();

                ByteArrayOutputStream byteArrayOutputStream1 = new ByteArrayOutputStream();
                bitmap1.compress(Bitmap.CompressFormat.PNG,100,byteArrayOutputStream1);
                byte[] hinhsanphamgiohang1 = byteArrayOutputStream1.toByteArray();

                sanPhamGioHang.setHinhgiohang(hinhsanphamgiohang1);
                sanPhamGioHang.setSOLUONG(1);

                presenterLogicChiTietSanPham.ThemGioHang(sanPhamGioHang,this);

                Intent iThanhToan = new Intent(ChiTietSanPhamActivity.this, ThanhToanActivity.class);
                startActivity(iThanhToan);
                break;
        }
    }

    @Override
    public void HienThiDanhGia(List<DanhGia> danhGiaList) {
        AdapterDanhGia adapterDanhGia = new AdapterDanhGia(this,danhGiaList,3);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerDanhGiaChiTiet.setLayoutManager(layoutManager);
        recyclerDanhGiaChiTiet.setAdapter(adapterDanhGia);

        adapterDanhGia.notifyDataSetChanged();
    }

    @Override
    public void ThemGioHangThanhCong() {
        Toast.makeText(this,"Sản phẩm đã được thêm vào giỏ hàng !",Toast.LENGTH_SHORT).show();
        txtGioHang.setText(String.valueOf(presenterLogicChiTietSanPham.DemSanPhamCoTrongGioHang(this)));
    }

    @Override
    public void ThemGiohangThatBai() {
        Toast.makeText(this,"Sản phẩm đã có trong giỏ hàng !",Toast.LENGTH_SHORT).show();
    }
}
