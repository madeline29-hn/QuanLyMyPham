package model;
import manager.*;
import java.time.LocalDate;
import java.util.Scanner;

public class DungCu extends SanPham{
    private String chatLieu;
    private String phanLoai;
    Scanner sc = new Scanner(System.in);

    public DungCu(){

    }
    
    public DungCu(String maSP, String tenSanPham, String thuongHieu, String phanLoai, double gia, int soLuong,
        LocalDate ngaySX, LocalDate ngayHH, String nuocSX, NhaCungCap nhaCungCap, boolean trangThai, String congDung, String chatLieu){
        super(maSP, tenSanPham, thuongHieu,phanLoai, gia, soLuong, ngaySX, ngayHH, nuocSX, nhaCungCap, trangThai, congDung);
        this.chatLieu = chatLieu;
        this.phanLoai = phanLoai;
    }
    
    public String getChatLieu(){
        return chatLieu;
    }

    public void setChatLieu(String chatLieu){
        this.chatLieu = chatLieu;
    }

    public boolean ktChatLieu(){
        return this.chatLieu != null && ! this.chatLieu.isEmpty();
    }

    public String thongTinDungCu(){
        return "Dung cu [Chat lieu: " + chatLieu + "]";
    }

    public void hienThiKiemTraDungCu(){
        System.out.println("\n--- Tong hop thong tin ---");
        System.out.println(this.toString());
        System.out.println("Kiem tra chat lieu: " + (this.ktChatLieu() ? "Hop le" : "Khong hop le"));
        System.out.println("Thong tin chi tiet: " + this.thongTinDungCu());
    }

    public void nhap(){
        super.nhap();
        while(true){
            System.out.println("Nhap chat lieu ban can: ");
            this.chatLieu = sc.nextLine().trim();
            if(ktChatLieu()){
                break;
            } else {
                System.out.println("Chat lieu khong duoc de trong, vui long nhap lai!");
            }
        }  
    }

    
    public String toString(){
        return super.toString() + String.format("|%-10s |", chatLieu);
    }

    public void xuat(){
        System.out.println(toString());
    }
}
