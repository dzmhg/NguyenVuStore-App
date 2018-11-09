package quyen.nguyenvustore.Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

import quyen.nguyenvustore.View.TrangChu.Fragment.FragmentChuongTrinhKhuyenMai;
import quyen.nguyenvustore.View.TrangChu.Fragment.FragmentLinhKien;
import quyen.nguyenvustore.View.TrangChu.Fragment.FragmentNoiBat;
import quyen.nguyenvustore.View.TrangChu.Fragment.FragmentThuongHieu;

public class ViewPagerAdapter extends FragmentPagerAdapter {
    List<Fragment> listFragment = new ArrayList<Fragment>();
    List<String> titleFragment = new ArrayList<String>();

    public ViewPagerAdapter(FragmentManager fm) {
        super(fm);

        listFragment.add(new FragmentLinhKien());
        listFragment.add(new FragmentNoiBat());
        listFragment.add(new FragmentChuongTrinhKhuyenMai());

        listFragment.add(new FragmentThuongHieu());

        titleFragment.add("Linh kiện");
        titleFragment.add("Nổi bật");
        titleFragment.add("Chương trình khuyến mãi");

        titleFragment.add("Thương hiệu");

    }

    @Override
    public Fragment getItem(int position) {
        return listFragment.get(position);
    }


    @Override
    public int getCount() {
        return listFragment.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titleFragment.get(position);
    }
}

