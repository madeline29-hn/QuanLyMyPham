package model;
import manager.*;
import java.time.LocalDate;
import java.util.Scanner;

public class PhanPhu extends SanPham {
    private String loaiDa;
    private float khoiLuong;
    private String mauSac;
    static Scanner sc = new Scanner(System.in);

    public PhanPhu(){
    }

    public PhanPhu(String maSP, String tenSanPham, String thuongHieu, String phanLoai, double gia, int soLuong, LocalDate ngaySX,
        LocalDate ngayHH, String nuocSX, NhaCungCap nhaCungCap, boolean trangThai, String congDung, String loaiDa, float khoiLuong, String mauSac){
        super(maSP, tenSanPham, thuongHieu, phanLoai, gia, soLuong, ngaySX, ngayHH, nuocSX, nhaCungCap, trangThai, congDung);
        this.loaiDa =loaiDa;
        this.khoiLuong =khoiLuong;
        this.mauSac = mauSac;
    }

    public String getLoaiDa (){
        return loaiDa;
    }

    public void setLoaiDa(String loaiDa){
        this.loaiDa = loaiDa;
    }

    public float getKhoiLuong (){
        return khoiLuong;
    }

    public void setKhoiLuong(float khoiLuong){
        this.khoiLuong = khoiLuong;
    }

    public String getMauSac(){
        return mauSac;
    }

    public void setMauSac(String mauSac){
        this.mauSac = mauSac;
    }

    public boolean ktLoaiDa(){
        return this.loaiDa != null && !this.loaiDa.isEmpty();
    }

    public boolean ktKhoiLuong(){
        return this.khoiLuong > 0;
    }

    public boolean ktMauSac(){
        return this.mauSac != null && !this.mauSac.isEmpty();
    }

    public String thongTinPhanPhu(){
        return "Phan phu [Loai da: "+ loaiDa + ",Khoi luong: "+ khoiLuong + ", Mau sac: "+ mauSac + "]";
    }

    public void hienThiKiemTraPhanPhu(){
        System.out.println("\n--- Tong hop thong tin ---");
        System.out.println(this.toString());
        System.out.println("Kiem tra loai da: " + (this.ktLoaiDa() ? "Hop le" : "Khong hop le"));
        System.out.println("Kiem tra khoi luong phan phu: " + (this.ktKhoiLuong() ? "Hop le" : "Khong hop le"));
        System.out.println("Kiem tra mau phan: " + (this.ktMauSac() ? "Da nhap" : "Chua nhap"));
        System.out.println("Thong tin chi tiet: " + this.thongTinPhanPhu());
    }

    public void nhap(){
        super.nhap();
        while(true){
            System.out.println("Nhap loai da cua ban: ");
            this.loaiDa = sc.nextLine().trim();
            if(ktLoaiDa()){
                break;
            }else{
                System.out.println("Loai da khong duoc de trong, vui long nhap lai!");
            }
        }

        while(true){
            System.out.println("Nhap khoi luong san pham (gram): ");
            this.khoiLuong = Float.parseFloat(sc.nextLine());
            if(ktKhoiLuong()){
                break;
            } else {
                System.out.println("Khoi luong phai lon hon 0, vui long nhap lai!");
            }
        }

        while(true){
            System.out.println("Nhap mau phan phu: ");
            this.mauSac = sc.nextLine().trim();
            if(ktMauSac()){
                break;
            } else {
                System.out.println("Mau sac khong duoc de trong, vui long nhap lai!");
            }
        }
    }

    
    public String toString(){
        return super.toString()+ String.format("|%-12s |%-5.2f |%-7s |", loaiDa, khoiLuong, mauSac);
    }

    public void xuat(){
        System.out.println(toString());
    }
}
