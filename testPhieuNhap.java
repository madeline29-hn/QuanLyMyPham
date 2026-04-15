package QuanLyMyPham;

import java.util.Scanner;

public class testPhieuNhap {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        PhieuNhap pn = new PhieuNhap(); 
        int luaChon;

        do {
            System.out.println("\n--- Quan Ly Phieu Nhap ---");
            System.out.println("1. Nhap thong tin phieu nhap moi");
            System.out.println("2. Them san pham moi vao phieu");
            System.out.println("3. Xoa san pham khoi phieu");
            System.out.println("4. Sua thong tin san pham trong phieu");
            System.out.println("5. Xuat thong tin phieu nhap");
            System.out.println("0. Thoat");
            System.out.print("Chon chuc nang (0-4): ");
            
            luaChon = Integer.parseInt(sc.nextLine());

            switch (luaChon) {
                case 1:
                    pn.nhap();
                    break;
                case 2:
                    System.out.println("Nhap thong tin san pham moi:");
                    SanPham spMoi = new SanPham();
                    spMoi.nhap();
                    pn.themSanPham(spMoi);
                    break;
                case 3:
                    System.out.print("Nhap ma san pham can xoa: ");
                    String maXoa = sc.nextLine();
                    pn.xoaSanPham(maXoa);
                    break;
                case 4: 
                    System.out.println("Nhap ma san pham can sua (SPxxxxx):");
                    String maSPSua = sc.nextLine();
                    pn.timKiemSP(maSPSua);
                    pn.suaSanPham(maSPSua);
                    break;
                case 5:
                    pn.xuat();
                    break;
                case 0:
                    System.out.println("Ket thuc chuong trinh!");
                    break;
                default:
                    System.out.println("Lua chon khong hop le. Vui long chon lai!");
            }
        } while (luaChon != 0);
    }
}
