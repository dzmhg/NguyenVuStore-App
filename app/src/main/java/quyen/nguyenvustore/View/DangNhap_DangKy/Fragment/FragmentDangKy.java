package quyen.nguyenvustore.View.DangNhap_DangKy.Fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.support.v7.widget.SwitchCompat;
import android.util.Log;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

import java.util.regex.Pattern;

import quyen.nguyenvustore.Model.ObjectClass.NhanVien;
import quyen.nguyenvustore.Presenter.DangKy.PresenterLogicDangKy;
import quyen.nguyenvustore.R;
import quyen.nguyenvustore.View.DangNhap_DangKy.ViewDangKy;

public class FragmentDangKy extends Fragment implements ViewDangKy,View.OnClickListener,View.OnFocusChangeListener {
    PresenterLogicDangKy presenterLogicDangKy;
    Button btnDangKy;
    EditText edHoTen,edMatKhau,edNhapLaiMatKhau,edDiaChiEmail;
    SwitchCompat sEmailDocQuyen;
    TextInputLayout input_edHoTen;
    TextInputLayout input_edMatKhau;
    TextInputLayout input_edNhapLaiMatKhau;
    TextInputLayout input_edDiaChiEmail;
    Boolean kiemtrathongtin = false;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_fragment_dangky,container,false);

        btnDangKy = (Button) view.findViewById(R.id.btnDangKy);
        edHoTen = (EditText) view.findViewById(R.id.edHoTenDK);
        edMatKhau = (EditText) view.findViewById(R.id.edMatKhauDK);
        edNhapLaiMatKhau = (EditText) view.findViewById(R.id.edNhapLaiMatKhauDK);
        edDiaChiEmail = (EditText) view.findViewById(R.id.edDiaChiEmailDK);
        sEmailDocQuyen = (SwitchCompat) view.findViewById(R.id.sEmailDocQuyen);
        input_edHoTen = (TextInputLayout) view.findViewById(R.id.input_edHoTenDK);
        input_edMatKhau = (TextInputLayout) view.findViewById(R.id.input_edMatKhauDK);
        input_edNhapLaiMatKhau = (TextInputLayout) view.findViewById(R.id.input_edNhapLaiMatKhauDK);
        input_edDiaChiEmail = (TextInputLayout)view.findViewById(R.id.input_edDiaChiEmailDK);

        presenterLogicDangKy = new PresenterLogicDangKy(this);

        btnDangKy.setOnClickListener(this);
        edHoTen.setOnFocusChangeListener(this);
        edNhapLaiMatKhau.setOnFocusChangeListener(this);
        edDiaChiEmail.setOnFocusChangeListener(this);

        return view;
    }

    @Override
    public void DangKyThanhCong() {
        Toast.makeText(getActivity(),"Đăng ký thành công !",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void DangKyThatBai() {
        Toast.makeText(getActivity(),"Đăng ký thất bại !",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch (id){

            case R.id.btnDangKy:
                btnDangKy();
                ;break;
        }
    }

    String emaildocquyen = "";
    private void btnDangKy(){
        String hoten = edHoTen.getText().toString();
        String email = edDiaChiEmail.getText().toString();
        String matkhau = edMatKhau.getText().toString();
        String nhaplaimatkhau = edNhapLaiMatKhau.getText().toString();

        sEmailDocQuyen.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                emaildocquyen = b + "";
            }
        });

        if(kiemtrathongtin) {
            NhanVien nhanVien = new NhanVien();
            nhanVien.setTenNV(hoten);
            nhanVien.setTenDN(email);
            nhanVien.setMatKhau(matkhau);
            nhanVien.setEmailDocQuyen(emaildocquyen);
            nhanVien.setMaLoaiNV(2);

            presenterLogicDangKy.ThucHienDangKy(nhanVien);
        }else{
            Log.d("kiemtra","Dang ky that bai ");
        }


    }

    @Override
    public void onFocusChange(View view, boolean b) {
        int id = view.getId();
        switch (id){
            case R.id.edHoTenDK:
                if(!b){
                    String chuoi = ((EditText)view).getText().toString();
                    if(chuoi.trim().equals("") || chuoi.equals(null)){
                        input_edHoTen.setErrorEnabled(true);
                        input_edHoTen.setError("Bạn chưa nhập mục này");
                        kiemtrathongtin = false;
                    }else{
                        input_edHoTen.setErrorEnabled(false);
                        input_edHoTen.setError("");
                        kiemtrathongtin = true;
                    }
                }
                ;break;

            case R.id.edDiaChiEmailDK:
                if(!b){

                    String chuoi = ((EditText)view).getText().toString();

                    if(chuoi.trim().equals("") || chuoi.equals(null)){
                        input_edDiaChiEmail.setErrorEnabled(true);
                        input_edDiaChiEmail.setError("Bạn chưa nhập mục này");
                        kiemtrathongtin = false;
                    }else{

                        Boolean kiemtraemail = Patterns.EMAIL_ADDRESS.matcher(chuoi).matches();
                        if(!kiemtraemail){
                            input_edDiaChiEmail.setErrorEnabled(true);
                            input_edDiaChiEmail.setError("Đây không phải là địa chỉ Email");
                            kiemtrathongtin = false;
                        }else{
                            input_edDiaChiEmail.setErrorEnabled(false);
                            input_edDiaChiEmail.setError("");
                            kiemtrathongtin = true;
                        }
                    }
                }
                ;break;

            case R.id.edMatKhauDK:
                ;break;

            case R.id.edNhapLaiMatKhauDK:
                if(!b){
                    String chuoi = ((EditText)view).getText().toString();
                    String matkhau = edMatKhau.getText().toString();
//                    if(!chuoi.equals(matkhau)){
//                        input_edNhapLaiMatKhau.setErrorEnabled(true);
//                        input_edNhapLaiMatKhau.setError("Mật khẩu không trùng khớp");
//                        kiemtrathongtin = false;
//                    }else{
//                        input_edNhapLaiMatKhau.setErrorEnabled(false);
//                        input_edNhapLaiMatKhau.setError("");
//                        kiemtrathongtin = true;
//                    }
                }

                ;break;

        }
    }


}


//public class FragmentDangKy extends Fragment implements ViewDangKy, View.OnClickListener, View.OnFocusChangeListener {
//    PresenterLogicDangKy presenterLogicDangKy;
//    Button btnDangKy;
//    EditText edHoten, edMatKhau, edNhapLaiMatKhau, edDiaChiEmail;
//    SwitchCompat sEmailDocQuyen;
//    TextInputLayout input_edHoTen, input_edMatKhau, input_edNhapLaiMatKhau, input_edDiaChiEmail;
//
//    @Nullable
//    @Override
//    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        View view = inflater.inflate(R.layout.layout_fragment_dangky, container, false);
//
////        NhanVien nhanVien = new NhanVien();
////        presenterLogicDangKy.ThucHienDangKy(nhanVien);
//        btnDangKy = (Button) view.findViewById(R.id.btnDangKy);
//        edHoten = (EditText) view.findViewById(R.id.edHoTenDK);
//        edMatKhau = (EditText) view.findViewById(R.id.edMatKhauDK);
//        edNhapLaiMatKhau = (EditText) view.findViewById(R.id.edNhapLaiMatKhauDK);
//        sEmailDocQuyen = (SwitchCompat) view.findViewById(R.id.sEmailDocQuyen);
//
//        input_edHoTen = (TextInputLayout) view.findViewById(R.id.input_edHoTenDK);
//        input_edMatKhau = (TextInputLayout) view.findViewById(R.id.input_edMatKhauDK);
//        input_edNhapLaiMatKhau = (TextInputLayout) view.findViewById(R.id.input_edNhapLaiMatKhauDK);
//        input_edDiaChiEmail = (TextInputLayout) view.findViewById(R.id.input_edDiaChiEmailDK);
//
//        presenterLogicDangKy = new PresenterLogicDangKy(this);
//
//        btnDangKy.setOnClickListener(this);
//        edHoten.setOnFocusChangeListener(this);
//        edMatKhau.setOnFocusChangeListener(this);
//        edNhapLaiMatKhau.setOnFocusChangeListener(this);
//        sEmailDocQuyen.setOnFocusChangeListener(this);
//
//        return view;
//    }
//
//    @Override
//    public void DangKyThanhCong() {
//
//    }
//
//    @Override
//    public void DangKyThatBai() {
//
//    }
//
//    @Override
//    public void onClick(View view) {
//        int id = view.getId();
//        switch (id) {
//            case  R.id.btnDangKy:
//
//                ;break;
//        }
//    }
//
//    String emaildocquyen = "";
//    private void btnDangKy() {
//        String hoten = edHoten.getText().toString();
//        String email = edDiaChiEmail.getText().toString();
//        String matkhau = edMatKhau.getText().toString();
//        String nhaplaimatkhau = edNhapLaiMatKhau.getText().toString();
//
//        sEmailDocQuyen.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
//                emaildocquyen = b + "";
//            }
//        });
//
//        if (input_edHoTen.getError().toString().equalsIgnoreCase("")
//                && input_edDiaChiEmail.getError().toString().equalsIgnoreCase("")
//                && input_edMatKhau.getError().toString().equalsIgnoreCase("")
//                && input_edNhapLaiMatKhau.getError().toString().equalsIgnoreCase("")) {
//            NhanVien nhanVien = new NhanVien();
//            nhanVien.setTenNV(hoten);
//            nhanVien.setTenDN(email);
//            nhanVien.setMatKhau(matkhau);
//            nhanVien.setEmailDocQuyen(emaildocquyen);
//            nhanVien.setMaLoaiNV(2);
//
//            presenterLogicDangKy.ThucHienDangKy(nhanVien);
//
//        }
//
//
//    }
//
//    @Override
//    public void onFocusChange(View view, boolean b) {
//        int id = view.getId();
//        switch (id) {
//            case R.id.edHoTenDK:
//                if (!b) {
//                    String chuoi = ((EditText) view).getText().toString();
//                    if (chuoi.trim().equals("") || chuoi.equals(null)) {
//                        input_edHoTen.setErrorEnabled(true);
//                        input_edHoTen.setError("Bạn chưa nhận mục này");
//                    }else {
//                        input_edHoTen.setErrorEnabled(false);
//                        input_edHoTen.setError("");
//                    }
//                }
//                ;break;
//            case R.id.edDiaChiEmailDK:
//                if(!b) {
//                    String chuoi = ((EditText)view).getText().toString();
//
//                    if (chuoi.trim().equals("") || chuoi.equals(null)) {
//                        input_edHoTen.setErrorEnabled(true);
//                        input_edHoTen.setError("Bạn chưa nhập mục này");
//                    }else {
//
//                        Boolean kiemtraemail = Patterns.EMAIL_ADDRESS.matcher(chuoi).matches();
//                        if (!kiemtraemail) {
//                            input_edHoTen.setErrorEnabled(true);
//                            input_edHoTen.setError("Đây không phải địa chỉ Email");
//                        }else {
//                            input_edHoTen.setErrorEnabled(false);
//                            input_edHoTen.setError("");
//                        }
//
//                    }
//                }
//                ;break;
//            case R.id.edMatKhauDK:
//                ;break;
//            case R.id.edNhapLaiMatKhauDK:
//                String chuoi = ((EditText)view).getText().toString();
//                String matkhau = edMatKhau.getText().toString();
//                if (chuoi.equalsIgnoreCase(matkhau)) {
//                    input_edHoTen.setErrorEnabled(true);
//                    input_edHoTen.setError("Mật khẩu không khớp");
//                }else {
//                    input_edHoTen.setErrorEnabled(false);
//                    input_edHoTen.setError("");
//                }
//                ;break;
//        }
//
//    }
//
//
//}
