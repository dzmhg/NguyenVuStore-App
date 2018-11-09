package quyen.nguyenvustore.Adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.List;

import quyen.nguyenvustore.Model.ObjectClass.ChiTietKhuyenMai;
import quyen.nguyenvustore.Model.ObjectClass.SanPham;
import quyen.nguyenvustore.R;
import quyen.nguyenvustore.View.ChiTietSanPham.ChiTietSanPhamActivity;

public class AdapterTopChuotLinhKien extends RecyclerView.Adapter<AdapterTopChuotLinhKien.ViewHolderTopChuot> {

    Context context;
    List<SanPham> sanPhamList;
    int layout;

    public AdapterTopChuotLinhKien(Context context, int layout, List<SanPham> sanPhamList) {
        this.context = context;
        this.sanPhamList = sanPhamList;
        this.layout = layout;
    }

    public class ViewHolderTopChuot extends RecyclerView.ViewHolder  {
        ImageView imHinhSanPham;
        TextView txtTenSP, txtGiaTien, txtGiamGia;
        CardView cardView;

        public ViewHolderTopChuot(View itemView) {
            super(itemView);

            imHinhSanPham = (ImageView) itemView.findViewById(R.id.imTopChuotLinhKien);
            txtTenSP = (TextView) itemView.findViewById(R.id.txtTieuDeTopChuotLinhKien);
            txtGiaTien = (TextView) itemView.findViewById(R.id.txtGiaLinhKien);
            txtGiamGia = (TextView) itemView.findViewById(R.id.txtGiamGiaLinhKien);
            cardView = (CardView) itemView.findViewById(R.id.cardView);
        }
    }

    @Override
    public ViewHolderTopChuot onCreateViewHolder( ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(layout, parent, false);

        ViewHolderTopChuot viewHolderTopChuot = new ViewHolderTopChuot(view);

        return viewHolderTopChuot;
    }

    @Override
    public void onBindViewHolder(ViewHolderTopChuot holder, int position) {
        SanPham sanPham = sanPhamList.get(position);
        Log.d("hinhsanpham", sanPham.getANHLON());
        Picasso.get().load(sanPham.getANHLON()).resize(140, 140).centerInside().into(holder.imHinhSanPham);
        holder.txtTenSP.setText(sanPham.getTENSP());

        ChiTietKhuyenMai chiTietKhuyenMai = sanPham.getChiTietKhuyenMai();
        int giatien = sanPham.getGIA();

        if(chiTietKhuyenMai !=null) {
            int phantramkm = chiTietKhuyenMai.getPHANTRAMKM();

            NumberFormat numberFormat = new DecimalFormat("####,###");
            String gia = numberFormat.format(giatien);
            holder.txtGiamGia.setVisibility(View.VISIBLE);
            holder.txtGiamGia.setPaintFlags(holder.txtGiamGia.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
            holder.txtGiaTien.setText(gia + " VNĐ ");
           // holder.cardView.setTag(sanPham.getMASP());
            giatien = giatien * phantramkm/100;


        }

        NumberFormat numberFormat = new DecimalFormat("###,###");
        //String gia = numberFormat.format(sanPham.getGIA()).toString();
        String gia = numberFormat.format(giatien);
        holder.txtGiaTien.setText(gia + " VNĐ");
        holder.cardView.setTag(sanPham.getMASP());

        //holder.cardView.setTag(sanPham.getMASP());
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v ){
                Intent iChitietsanpham = new Intent(context, ChiTietSanPhamActivity.class);
                iChitietsanpham.putExtra("masp", (int)v.getTag());
                context.startActivity(iChitietsanpham);
            }
        });

    }

    @Override
    public int getItemCount() {
        return sanPhamList.size();
    }

}
