package model;
import manager.*;
import java.util.Scanner;
public class NhaCungCap {
    private String maNCC;
    private String tenNCC;
    private String diaChi;
    private String sdt;
    private String email; 
    static Scanner sc = new Scanner(System.in);

    public NhaCungCap(){

    }

    public NhaCungCap(String maNCC, String tenNCC, String diaChi, String sdt, String email){
        this.maNCC = maNCC;
        this.tenNCC = tenNCC;
        this.diaChi = diaChi;
        this.sdt = sdt;
        this.email = email;
    }

    public String getMaNCC(){
        return maNCC;
    }

    public void setMaNCC(String maNCC){
        this.maNCC = maNCC;
    }

    public String getTenNCC(){
        return tenNCC;
    }

    public void setTenNCC(String tenNCC){
        this.tenNCC = tenNCC;
    }
    
    public String getDiaChi(){
        return diaChi;
    }

    public void setDiaChi(String diaChi){
        this.diaChi = diaChi;
    }

    public String getSdt(){
        return sdt;
    }

    public void setSdt(String sdt){
        this.sdt = sdt;
    }

    public String getEmail(){
        return email;
    }

    public void setEmail(String email){
        this.email = email;
    }

    public void cungCapHang(){
        System.out.println("Nha cung cap: " + this.tenNCC);
        System.out.println("Trang thai: Dang chuan bi hang va xe van tai.");
        System.out.println("Thong bao: Hang se duoc giao den kho trong 24h toi.");
    }

    public void kiemTraDonHang(String maDH){
        System.out.println("Dang kiem tra don hang co ma: "+ maDH);
    }

    public void thanhToan(){
        System.out.println("Doi tuong thu huong: " + this.tenNCC);
        System.out.println("Trang thai: Giao dich thanh cong.");
    }

    public void ktDonVaThanhToan(){
        String maDH;
        while (true){
            System.out.println("Nhap ma don hang ban muon kiem tra (DH + 5 chu so): ");
            maDH = sc.nextLine().trim();
            if(maDH.matches("\\d{5}")){
                maDH = ("DH" + maDH);
                break;
            } else {
                System.out.println("Ma don sai dinh dang, vui long nhap lai!");
            }
        }
        this.kiemTraDonHang(maDH);

        System.out.println(this.toString());
        this.cungCapHang();
        this.thanhToan();
    }

    public void nhap(){
        this.maNCC = "12345";
        setMaNCC("NCC" + this.maNCC );

        this.tenNCC = "Unilever";
        setTenNCC(this.tenNCC);

        this.diaChi = "Ho Chi Minh";
        setDiaChi(this.diaChi);

        
        this.sdt = "0912345678";
        setSdt(this.sdt);
                
        this.email = "unilever@gmail.com";
        setEmail(this.email);
    }

    @Override
    public String toString(){
        return String.format(" %-8s | %-10s | %-12s | %-11s | %-16s ", maNCC, tenNCC, diaChi, sdt, email);
    }
    public void xuat(){
        System.out.println(toString());
    }
}
