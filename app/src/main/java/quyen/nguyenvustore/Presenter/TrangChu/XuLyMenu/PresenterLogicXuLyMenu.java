package quyen.nguyenvustore.Presenter.TrangChu.XuLyMenu;

import android.os.Bundle;
import android.util.Log;

import com.facebook.AccessToken;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutionException;

import quyen.nguyenvustore.Connectinternet.DownloadJSON;
import quyen.nguyenvustore.Model.DangNhap_DangKy.ModelDangNhap;
import quyen.nguyenvustore.Model.ObjectClass.LoaiSanPham;
import quyen.nguyenvustore.Model.TrangChu.XuLyMenu.XuLyJSONMenu;
import quyen.nguyenvustore.View.TrangChu.TrangChuActivity;
import quyen.nguyenvustore.View.TrangChu.ViewXuLyMenu;

public class PresenterLogicXuLyMenu implements IPresenterXuLyMenu {

    ViewXuLyMenu viewXuLyMenu;
    String tennguoidung = "";


    public PresenterLogicXuLyMenu(ViewXuLyMenu viewXuLyMenu){
        this.viewXuLyMenu = viewXuLyMenu;
    }

    @Override
    public void LayDanhSachMenu() {
        List<LoaiSanPham> loaiSanPhamList;
        String dataJSON = "";
        List<HashMap<String, String>> attrs = new ArrayList<>();

        //Lấy bằng phương thức get
        //String duongdan = "http://192.168.1.35:81/server/loaisanpham.php";

        //DownloadJSON downloadJSON = new DownloadJSON(duongdan);
        // end phương thức get

        //Lấy bằng phương thức post
        String duongdan = TrangChuActivity.SERVER_NAME;

        HashMap<String, String> hsHam = new HashMap<>();
        hsHam.put("ham", "LayDanhSachMenu");
//
        HashMap<String, String> hsMaLoaiCha = new HashMap<>();
        hsMaLoaiCha.put("maloaicha", "0");
//
        attrs.add(hsMaLoaiCha);
        attrs.add(hsHam);

        DownloadJSON downloadJSON = new DownloadJSON(duongdan, attrs);
        //end phương thức post
        downloadJSON.execute();

        try {
            dataJSON = downloadJSON.get();
            XuLyJSONMenu xuLyJSONMenu = new XuLyJSONMenu();
//            xuLyJSONMenu.ParserJSONMenu(dataJSON);
            loaiSanPhamList = xuLyJSONMenu.ParserJSONMenu(dataJSON);
            Log.d("----dũng xinh:",loaiSanPhamList.toString());
            viewXuLyMenu.HienThiDanhSachMenu(loaiSanPhamList);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

    }

    public AccessToken LayTokenDungFacebook() {
        ModelDangNhap modelDangNhap = new ModelDangNhap();
        AccessToken accessToken = modelDangNhap.LayTokenFacebookHienTai();


            return accessToken;
        }

}
