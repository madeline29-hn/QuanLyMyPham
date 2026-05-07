package QuanLyMyPham;
import java.time.LocalDate;
import java.util.Scanner;

public class KemDuong extends SanPham{
    private String loaiDa;
    private String chatKem; 
    private float khoiLuong;
    Scanner sc = new Scanner(System.in);
    
    public KemDuong(){

    }

    public KemDuong(String maSP, String tenSanPham, String thuongHieu, String phanLoai, double gia, int soLuong,
        LocalDate ngaySX, LocalDate ngayHH, String nuocSX, NhaCungCap nhaCungCap, boolean trangThai, String congDung, String loaiDa, String chatKem, float khoiLuong){
        super(maSP, tenSanPham, thuongHieu,phanLoai, gia, soLuong, ngaySX, ngayHH, nuocSX, nhaCungCap, trangThai, congDung);
        this.loaiDa = loaiDa;
        this.chatKem = chatKem;
        this.khoiLuong = khoiLuong;
    }

    public String getLoaiDa(){
        return loaiDa;
    }

    public void setLoaiDa(String loaiDa){
        this.loaiDa = loaiDa;
    }

    public String getChatKem(){
        return chatKem;
    }

    public void setChatKem(String chatKem){
        this.chatKem = chatKem;
    }

    public float getKhoiLuong(){
        return khoiLuong;
    }

    public void setKhoiLuong(float khoiLuong){
        this.khoiLuong = khoiLuong;
    }

    public boolean ktLoaiDa(){
        return this.loaiDa != null && ! this.loaiDa.isEmpty();
    }

    public boolean ktChatKem(){
        return this.chatKem != null &&! this.chatKem.isEmpty();
    }

    public boolean ktKhoiLuong(){
        return this.khoiLuong > 0;
    }

    public String thongTinKemDuong(){
        return "Kem duong [Loai da: " + loaiDa + ", Chat kem: " + chatKem + ", Khoi luong: " + khoiLuong+ "]" ;
    }

    public void hienThiKiemTraKemDuong(){
        System.out.println("\n--- Tong hop thong tin ---");
        System.out.println(this.toString());
        System.out.println("Kiem tra loai da: " + (this.ktLoaiDa() ? "Hop le" : "Khong hop le"));
        System.out.println("Kiem tra khoi luong : " + (this.ktKhoiLuong() ? "Hop le" : "Khong hop le"));
        System.out.println("Kiem tra chat kem: " + (this.ktChatKem() ? "Da nhap" : "Chua nhap"));
        System.out.println("Thong tin chi tiet: " + this.thongTinKemDuong());
    }

    public void nhap(){
        super.nhap();
        while(true){
            System.out.println("Nhap loai da cua ban: ");
            this.loaiDa = sc.nextLine().trim();
            if(ktLoaiDa()){
                break;
            } else {
                System.out.println("Loai da khong duoc de trong, vui long nhap lai!");
            }
        }

        while(true){
            System.out.println("Nhap chat kem ban can: ");
            this.chatKem = sc.nextLine().trim();
            if(ktChatKem()){
                break;
            } else {
                System.out.println("Chat kem khong duoc de trong, vui long nhap lai!");
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
    }
    @Override
    public String toString(){
        return super.toString() + String.format("|%-12s |%-12s |%-5.2f |", loaiDa, chatKem, khoiLuong);
    } 

    public void xuat(){
        System.out.println(toString());
    }
}

