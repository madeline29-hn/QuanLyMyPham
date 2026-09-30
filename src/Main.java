package QuanLyMyPham;
import model.*;
import manager.*;
import java.util.Scanner;
import model.HoaDon;
import model.PhieuNhap;
import manager.QuanLyHoaDon;
import manager.QuanLyPhieuNhap;
import manager.QuanLyKM;
import manager.QuanLyTaiKhoan;


    public class Main {
        public static void main(String[] args) {
            Scanner sc = new Scanner(System.in);

            QuanLyNhanVien qlnv = new QuanLyNhanVien();
            qlnv.read();

            QuanLyKhachHang qlkh = new QuanLyKhachHang();
            qlkh.read();
            KhachHang kh = new KhachHang();

            QuanLySanPham qlsp = new QuanLySanPham();
            qlsp.read();
            
            PhieuNhap[] dsPN = new PhieuNhap[50];
            QuanLyPhieuNhap qlpn = new QuanLyPhieuNhap();
            qlpn.setQlsp(qlsp);
            qlpn.setDsNhanVien(qlnv.getDsNhanVien());
            qlpn.setDsSanPham(qlsp.getDsSanPham());
            qlpn.read();

            QuanLyKM qlkm = new QuanLyKM();
            qlkm.read();

            HoaDon[] dsHD = new HoaDon[50];
            QuanLyHoaDon qlhd = new QuanLyHoaDon(dsHD, 0, qlkh.getDsKhachHang(), qlsp.getDsSanPham(), qlnv.getDsNhanVien(), qlkm.getDsKhuyenMai(), qlsp);
            qlhd.read();

            QuanLyTaiKhoan qltk = new QuanLyTaiKhoan();
            qltk.read();

            int luaChon = -1; 
            
            do {
                System.out.println("=========== HE THONG QUAN LY MY PHAM ===========");
                System.out.println("1. Dang nhap he thong");
                System.out.println("0. Thoat chuong trinh");
                System.out.print("Nhap lua chon cua ban: ");
                
                try {
                    luaChon = Integer.parseInt(sc.nextLine());

                    switch (luaChon) {
                        case 1:
                            qltk.dangNhap(qlsp, qlnv, qlkh, qlhd, qlpn, qlkm, kh);
                            System.out.println("Nhan Enter de tiep tuc...");
                            sc.nextLine();
                            break;
                            
                        case 0:
                            System.out.println("Ket thuc chuong trinh. Hen gap lai!");
                            sc.close();
                            System.exit(0); // Dừng hoàn toàn chương trình
                            
                        default:
                            System.out.println("Lua chon khong hop le, vui long chon lai.");
                    }
                } catch(NumberFormatException e) {
                    System.out.println("Vui long nhap so!");
                }
            } while (true); 
        }
    }
