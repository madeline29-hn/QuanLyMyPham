package model;
import manager.*;

import java.time.LocalDate;

public class QuanLy extends NhanVien {
    private String kinhNghiem;

    public QuanLy() {
        super();
        this.setLuongCoBan(600000f);
        this.setNgayCong(0);
        this.kinhNghiem = "";
    }

    public QuanLy(String hoTen, String sdt, String email, String gioiTinh, LocalDate ngaySinh,
                 String maNhanVien, String soCCCD, double ngayCong, float luongCoBan, String kinhNghiem) {
        super(hoTen, sdt, email, gioiTinh, ngaySinh, maNhanVien, soCCCD, ngayCong, luongCoBan);
        this.kinhNghiem = (kinhNghiem == null) ? "" : kinhNghiem;
    }

    public String getKinhNghiem() {
        return kinhNghiem;
    }

    public void setKinhNghiem(String kinhNghiem) {
        this.kinhNghiem = (kinhNghiem == null) ? "" : kinhNghiem;
    }

    @Override
    public void nhap(NhanVien[] ds, int soLuong) {
        super.nhap(ds, soLuong);
        System.out.print("Nhap kinh nghiem (mo ta): ");
        this.kinhNghiem = sc.nextLine().trim();
    }

    @Override
    public double tinhLuong() {
        return (double) (getLuongCoBan() * getNgayCong());
    }

    @Override
    public String getVaiTro() {
        return "Quan Ly";
    }

    @Override
    public String toString() {
        return super.toString() + String.format(" %-15s |", kinhNghiem == null ? "" : kinhNghiem);
    }

    @Override
    public void xuat() {
        System.out.println(this.toString());
    }

    @Override
    public String toDataString() {
        return super.toDataString() + ";" + (kinhNghiem == null ? "" : kinhNghiem);
    }

    @Override
    public void fromString(String[] data) {
        super.fromString(data);
        if (data != null && data.length > 12) {
            this.kinhNghiem = data[12] == null ? "" : data[12];
        } else {
            this.kinhNghiem = "";
        }
    }
}
