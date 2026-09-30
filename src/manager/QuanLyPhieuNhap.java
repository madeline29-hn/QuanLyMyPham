package manager;
import model.*;
import model.iDocGhiFile;     
import model.iQuanLyDanhSach;  
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.time.LocalDate;
import java.util.Arrays;

public class QuanLyPhieuNhap implements iQuanLyDanhSach,iDocGhiFile {
    private PhieuNhap[] dsPhieuNhap = new PhieuNhap[50];
    private NhanVien[] dsNhanVien = new NhanVien[15];
    private SanPham[] dsSanPham = new SanPham[20];
    private QuanLySanPham qlsp ;
    private int soLuongPN = 0;
    private NhanVien nhanVienDangNhap;
    static Scanner sc = new Scanner(System.in);
    public QuanLyPhieuNhap() {}

    public QuanLyPhieuNhap(PhieuNhap[] dsPhieuNhap, int soLuongPN, NhanVien[] dsNhanVien,SanPham[] dsSanPham,QuanLySanPham qlsp) {
        this.dsPhieuNhap = dsPhieuNhap;
        this.soLuongPN = soLuongPN;
        this.dsNhanVien = dsNhanVien;
        this.dsSanPham = dsSanPham;
        this.qlsp = qlsp;
    }

    public QuanLySanPham getQlsp() {
        return qlsp;
    }

    public void setQlsp(QuanLySanPham qlsp) {
        this.qlsp = qlsp;
    }

    public PhieuNhap[] getDsPhieuNhap() {
        return dsPhieuNhap;
    }

    public int getSoLuongPN() {
        return soLuongPN;
    }

    public SanPham[] getDsSanPham() {
        return dsSanPham;
    }

    public void setDsSanPham(SanPham[] dsSanPham) {
        this.dsSanPham = dsSanPham;
    }

    public void setDsPhieuNhap(PhieuNhap[] dsPhieuNhap) {
        this.dsPhieuNhap = dsPhieuNhap;
    }

    public void setSoLuongPN(int soLuongPN) {
        this.soLuongPN = soLuongPN;
    }


    public NhanVien[] getDsNhanVien() {
        return dsNhanVien;
    }

    public void setDsNhanVien(NhanVien[] dsNhanVien) {
        this.dsNhanVien = dsNhanVien;
    }


    public NhanVien getNhanVienDangNhap() {
        return nhanVienDangNhap;
    }

    public void setNhanVienDangNhap(NhanVien nhanVienDangNhap) {
        this.nhanVienDangNhap = nhanVienDangNhap;
    }
    public void themVaoDanhSach(PhieuNhap pn) {
        if (soLuongPN >= dsPhieuNhap.length) {
            dsPhieuNhap = Arrays.copyOf(dsPhieuNhap, dsPhieuNhap.length + 10);
        }
        dsPhieuNhap[soLuongPN++] = pn;

        if (pn.getTrangThai()) {
            int slMoi = pn.capNhatKho(qlsp.getDsSanPham(), qlsp.getSoLuong());
            qlsp.setSoLuong(slMoi);
            this.write();
            qlsp.write();
        } else {
            this.write();
            System.out.println("Chua thanh toan, kho hang khong thay doi");
        }
    }
    public boolean kiemTraTrungMa(String ma) {
        for (int i = 0; i < soLuongPN; i++) {
            if (dsPhieuNhap[i] != null && dsPhieuNhap[i].getMaPhieuNhap().equalsIgnoreCase(ma)) {
                return true;
            }
        }
        return false;
    }
    public void xoaKhoiDanhSach(String maPN) {
        for (int i = 0; i < soLuongPN; i++) {
            if (dsPhieuNhap[i].getMaPhieuNhap().equalsIgnoreCase(maPN) && dsPhieuNhap[i].getTrangThai()==false) {
                for (int j = i; j < soLuongPN - 1; j++) {
                    dsPhieuNhap[j] = dsPhieuNhap[j + 1];
                }
                dsPhieuNhap[soLuongPN - 1] = null;
                soLuongPN--;
                System.out.println("Da xoa phieu nhap co ma: " + maPN);
                return;
            }
        }
        System.out.println("Khong tim thay phieu nhap co ma: " + maPN +" hoac phieu nhap da thanh toan khong the xoa");
    }

    public void suaDanhSach(String maPN) {
        for (int i = 0; i < soLuongPN; i++) {
            if (dsPhieuNhap[i].getMaPhieuNhap().equalsIgnoreCase(maPN) ) {
                dsPhieuNhap[i].nhap(dsSanPham);
                return;
            }
        }
        System.out.println("Khong tim thay phieu nhap co ma: " + maPN);
    } 
    public double tongTienNhap() {
        double tongTienNhap = 0;
        for (int i = 0; i < soLuongPN; i++) {
            if (dsPhieuNhap[i] != null && dsPhieuNhap[i].getTrangThai()) {
                tongTienNhap += dsPhieuNhap[i].tongTien();
            }
        }
        return tongTienNhap;
    }
    public void thongKeTheoKhoa() {
        int tongSanPham = 0;
        int daThanhToan = 0;
        int chuaThanhToan = 0;
        System.out.println("\n--------------------------------------------------------------");
        System.out.println("Tong so phieu nhap: " + soLuongPN);
        double tongTienNhap = 0;
        for (int i = 0; i < soLuongPN; i++) {
            tongTienNhap = tongTienNhap();
            tongSanPham += dsPhieuNhap[i].tongSoLuong();
            if (dsPhieuNhap[i].getTrangThai()) {
                daThanhToan++;
            } else {

                System.out.println("Danh sach phieu chua thanh toan: " + dsPhieuNhap[i].getMaPhieuNhap());
                chuaThanhToan++;
            }
        }
        System.out.println("Tong tien nhap: " + tongTienNhap);
        System.out.println("Tong so san pham nhap: " + tongSanPham);
        System.out.println("Phieu nhap da thanh toan: " + daThanhToan);
        System.out.println("Phieu nhap chua thanh toan: " + chuaThanhToan);
        System.out.println("\n--------------------------------------------------------------");
    }
    public void suaPhieuNhap() {
        System.out.print("Nhap ma phieu nhap can sua : ");
        String maPN = sc.nextLine();
        boolean find = false;
        for (int i = 0; i < soLuongPN; i++) {
            if (dsPhieuNhap[i].getMaPhieuNhap().equalsIgnoreCase(maPN)) {
                dsPhieuNhap[i].suaSanPham();
                find = true;
                return;
            }
        }
        if (!find) {
            System.out.println("Khong tim thay phieu nhap co ma: " + maPN);
        }
    }
    public void timKiemChinhXac(String maPN) {
        for (int i = 0; i < soLuongPN; i++) {
            if (dsPhieuNhap[i].getMaPhieuNhap().equalsIgnoreCase(maPN)) {
                dsPhieuNhap[i].xuat();
                return;
            }
        }
        System.out.println("Khong tim thay phieu nhap co ma: " + maPN);
    }
    public void timKiemTuongDoi(String tuKhoa) {
        boolean found = false;
        String tuKhoaLower = tuKhoa.toLowerCase();

        for (int i = 0; i < soLuongPN; i++) {

            String tenNV = dsPhieuNhap[i].getNhanVien().getHoTen().toLowerCase();
            String tenNCC = dsPhieuNhap[i].getNhaCungCap().getTenNCC().toLowerCase();

            String ngayNhapStr = dsPhieuNhap[i].getNgayNhap().toString();

            if (tenNV.contains(tuKhoaLower) ||
                    tenNCC.contains(tuKhoaLower) ||
                    ngayNhapStr.contains(tuKhoaLower)) {

                dsPhieuNhap[i].xuat();
                found = true;
            }
        }

        if (!found) {
            System.out.println("Khong tim thay phieu nhap nao khop voi tu khoa: " + tuKhoa);
        }
    }

    public void nhapDanhSach(NhanVien nvDangNhap) {
        while (true) {
            System.out.print("Nhap ma phieu nhap de kiem tra (PNxxxxx): ");
            String maPN = sc.nextLine().trim();
            if (maPN.matches("PN\\d{5}")) {
                if (kiemTraTrungMa(maPN)) {
                    System.out.println("Ma phieu nhap da ton tai! Vui long nhap ma khac.");
                } else {
                    PhieuNhap pn = new PhieuNhap();
                    pn.setQlsp(qlsp);
                    if (nvDangNhap != null) {
                        pn.ganNhanVienDangNhap(nvDangNhap);
                    }
                    pn.setMaPhieuNhap(maPN);
                    pn.nhapThongTinChung(dsNhanVien);
                    pn.nhap(qlsp.getDsSanPham());
                    themVaoDanhSach(pn);
                    break;
                }
            }
        }
    }
    @Override
    public void nhapDanhSach() {
        nhapDanhSach(this.nhanVienDangNhap);
    }

    public void xuatDanhSach() {
        for (int i = 0; i < soLuongPN; i++) {
            System.out.println("Thong tin phieu nhap thu " + (i + 1) + ":");
            dsPhieuNhap[i].xuat();
        }
    }

    public void read() {

        try {
            FileReader fr = new FileReader("D:\\Java\\QuanLyMyPham\\dsPhieuNhap.txt");
            BufferedReader br = new BufferedReader(fr);
            String line;
            soLuongPN = 0;
            int count = 0;
            while ((line = br.readLine()) != null) {
                count ++;
                if (line.trim().isEmpty()){
                    continue;
                }
                try {
                    String[] data = line.split(";");
                    if (data.length < 9 ) {
                        System.out.println("Loi o dong " + count + ": Du lieu khong dung cot ");
                        continue;
                    }
                    PhieuNhap pn = new PhieuNhap();
                    pn.setMaPhieuNhap(data[0]);
                    pn.setNgayNhap(LocalDate.parse(data[1]));
                    String maNVTuFile = data[2].trim();
                    for (NhanVien nv : dsNhanVien) {
                        if (nv.getMaNhanVien().equalsIgnoreCase(maNVTuFile)) {
                            pn.setNhanVien(nv);
                            break;
                        }
                    }
                    NhaCungCap nCC = new NhaCungCap();
                    nCC.setMaNCC(data[4]);
                    nCC.setTenNCC(data[5]);
                    pn.setNhaCungCap(nCC);
                    pn.setDsSanPham(new SanPham[15]);
                    pn.setSoLuongSP(0);
                    pn.setTongTien(Double.parseDouble(data[7]));
                    pn.setTrangThai(data[8].equals("1"));
                    int startIndex = 9;
                    while (startIndex + 4 < data.length) {
                        SanPham sp = new SanPham();
                        sp.setMaSP(data[startIndex]);
                        sp.setTenSanPham(data[startIndex + 1]);
                        sp.setThuongHieu(data[startIndex + 2]);
                        sp.setSoLuong(Integer.parseInt(data[startIndex + 3]));
                        sp.setGia(Double.parseDouble(data[startIndex + 4]));
                        pn.themSanPham(sp);
                        startIndex += 5;
                    }

                    if (soLuongPN >= dsPhieuNhap.length) {
                        dsPhieuNhap = Arrays.copyOf(dsPhieuNhap, dsPhieuNhap.length + 10);
                    }
                    dsPhieuNhap[soLuongPN++]= pn;
                }
                catch (Exception e) {
                    System.out.println("Loi o dong " + count + ": " + e.getMessage());
                }
            }
            System.out.println("Da doc xong thong tin " + soLuongPN + " phieu nhap tu file!");
            br.close();
            fr.close();
        } catch (FileNotFoundException ex) {
            System.out.println("Loi khi doc file : " + ex.getMessage());
        } catch (Exception e) {
            System.out.println("Loi khac: " + e.getMessage());
        }
    }

    public void write() {
        if (soLuongPN == 0) {
            System.out.println("Khong co phieu nhap nao de ghi vao file!");
            return;
        }
        try {
            FileWriter fw = new FileWriter("D:\\Java\\QuanLyMyPham\\dsPhieuNhap.txt",false );
            BufferedWriter bw = new BufferedWriter(fw);
            for (int i = 0; i < soLuongPN; i++) {
                bw.write(dsPhieuNhap[i].outputFile());
                if (i < soLuongPN - 1) {
                    bw.newLine();
                }

            }
            System.out.println("Da ghi thong tin"+ soLuongPN +" phieu nhap  vao file!");

            bw.close();
            fw.close();
        } catch (Exception e) {
            System.out.println("Loi khi ghi file: " + e.getMessage());
        }

    }
    public void menu() {

        int luaChon;
        do {
            System.out.println("\n================ QUAN LY DANH SACH PHIEU NHAP ================");
            System.out.println("1. Nhap danh sach phieu nhap moi");
            System.out.println("2. Xuat toan bo danh sach phieu nhap");
            System.out.println("3. Xoa mot phieu nhap (theo Ma PN)");
            System.out.println("4. Tim kiem va Thao tac chi tiet (Sua/Xoa SP trong phieu)");
            System.out.println("5. Thong ke doanh thu/so luong");
            System.out.println("6. Tim kiem chinh xac theo ma phieu nhap");
            System.out.println("7. Tim kiem tuong doi theo ngay/thang/nam / nhan vien");
            System.out.println("0. Thoat");
            System.out.print("Nhap lua chon cua ban: ");
            while (true) {
                try {
                    luaChon = Integer.parseInt(sc.nextLine());
                    break;
                } catch (NumberFormatException e) {
                    System.out.print("Lua chon khong hop le. Vui long nhap lai: ");
                }
            }
            switch (luaChon) {
                case 1:
                    nhapDanhSach();
                    System.out.println("Enter de tiep tuc.");
                    sc.nextLine();
                    break;
                case 2:
                    if (soLuongPN == 0) {
                        System.out.println("Khong co phieu nhap nao de xuat!");
                    } else {
                        xuatDanhSach();
                    }
                    write();
                    System.out.println("Enter de tiep tuc.");
                    sc.nextLine();
                    break;
                case 3:
                    System.out.print("Nhap ma PN can xoa: ");
                    xoaKhoiDanhSach(sc.nextLine());
                    write();
                    System.out.println("Enter de tiep tuc.");
                    sc.nextLine();
                    break;
                case 4: menuThaoTacChiTiet(); break;
                case 5: thongKeTheoKhoa();
                    System.out.println("Enter de tiep tuc.");
                    sc.nextLine();
                    break;
                case 6:
                    if (soLuongPN == 0) {
                        System.out.println("Khong co phieu nhap nao de tim kiem!");
                        break;
                    }
                    System.out.print("Nhap ma PN can tim: ");
                    timKiemChinhXac(sc.nextLine());
                    System.out.println("Enter de tiep tuc.");
                    sc.nextLine();
                    break;
                case 7:
                    if (soLuongPN == 0) {
                        System.out.println("Khong co phieu nhap nao de tim kiem!");
                        break;
                    }
                    System.out.print("Nhap tu khoa can tim (ten NV/ ngay hoac thang hoac nam ): ");
                    timKiemTuongDoi(sc.nextLine());
                    System.out.println("Enter de tiep tuc.");
                    sc.nextLine();
                    break;
                case 0:
                    write(); qlsp.write();
                    System.out.println("Enter de tiep tuc.");
                    sc.nextLine();
                    break;
                default: System.out.println("Lua chon khong hop le!");
            }
        } while (luaChon != 0);
    }
    public void menuThaoTacChiTiet() {
        System.out.print("Nhap Ma Phieu Nhap ban muon thao tac: ");
        String maPN = sc.nextLine();
        PhieuNhap pn = null;

        for (int i = 0; i < soLuongPN; i++) {
            if (dsPhieuNhap[i].getMaPhieuNhap().equalsIgnoreCase(maPN)) {
                pn = dsPhieuNhap[i];
                break;
            }
        }

        if (pn == null) {
            System.out.println("Khong tim thay phieu nhap nay!");
            return;
        }

        int chon;
        LocalDate now = LocalDate.now();
        int gioiHanNgay = 1;

        do {
            System.out.println("\n--- DANG THAO TAC TREN PHIEU: " + maPN + " ---");
            System.out.println("1. Xem chi tiet phieu nay");
            System.out.println("2. Sua thong tin chung (NV, NCC, Ngay)");
            System.out.println("3. Them san pham moi vao phieu");
            System.out.println("4. Sua so luong san pham trong phieu (theo Ma SP)");
            System.out.println("5. Xoa mot san pham khoi phieu");
            System.out.println("6. Cap nhat trang thai thanh toan");
            System.out.println("0. Quay lai menu chinh");
            System.out.print("Chon: ");
            while (true) {
                try {
                    chon = Integer.parseInt(sc.nextLine());
                    break;
                } catch (NumberFormatException e) {
                    System.out.print("Lua chon khong hop le. Vui long nhap lai: ");
                }
            }

            switch (chon) {
                case 1: pn.xuat();
                    System.out.println("Enter de tiep tuc.");
                    sc.nextLine();
                    break;
                case 2:

                    if (pn.getTrangThai()) {
                        System.out.println("Phieu da thanh toan, khong the sua");
                        return;
                    }

                    if (pn.getNgayNhap().plusDays(gioiHanNgay).isBefore(now)) {
                        System.out.println("Phieu nhap da qua 1 ngay khong the sua");
                        return;
                    }

                    pn.nhapThongTinChung(dsNhanVien);
                    pn.xuat();
                    write();
                    System.out.println("Enter de tiep tuc.");
                    sc.nextLine();
                    break;

                case 3:
                    if (pn.getTrangThai()) {
                        System.out.println("Phieu da thanh toan, khong the sua");
                        break;
                    }
                    if (pn.getNgayNhap().plusDays(gioiHanNgay).isBefore(now)) {
                        System.out.println("Phieu nhap da qua 1 ngay khong the sua");
                        break;
                    }
                    pn.nhap(qlsp.getDsSanPham());
                    int slMoi = pn.capNhatKho(qlsp.getDsSanPham(), qlsp.getSoLuong());
                    qlsp.setSoLuong(slMoi);
                    write();
                    qlsp.write();
                    pn.xuat();
                    System.out.println("Da cap nhat san pham moi vao phieu va kho hang.");
                    System.out.println("Enter de tiep tuc.");
                    sc.nextLine();
                    break;
                case 4:
                    if (pn.getTrangThai()) {
                        System.out.println("Phieu da thanh toan, khong the sua");
                        return;
                    }
                    if (pn.getNgayNhap().plusDays(gioiHanNgay).isBefore(now)) {
                        System.out.println("Phieu nhap da qua 1 ngay khong the sua");
                        return;
                    }
                    pn.suaSanPham();
                    int slCapNhat = pn.capNhatKho(qlsp.getDsSanPham(), qlsp.getSoLuong());
                    qlsp.setSoLuong(slCapNhat);
                    write();
                    qlsp.write();
                    pn.xuat();
                    System.out.println("Enter de tiep tuc.");
                    sc.nextLine();
                    break;
                case 5:
                    if (pn.getTrangThai()) {
                        System.out.println("Phieu da thanh toan, khong the sua");
                        return;
                    }
                    if (pn.getNgayNhap().plusDays(gioiHanNgay).isBefore(now)) {
                        System.out.println("Phieu nhap da qua 1 ngay khong the sua");
                        return;
                    }

                    System.out.print("Nhap ma SP can xoa: ");
                    pn.xoaSanPham(sc.nextLine());
                    int slUpdate = pn.capNhatKho(qlsp.getDsSanPham(), qlsp.getSoLuong());
                    qlsp.setSoLuong(slUpdate);
                    write();
                    qlsp.write();
                    pn.xuat();

                    System.out.println("Enter de tiep tuc.");
                    sc.nextLine();
                    break;
                case 6:
                    if (pn.getTrangThai()) {
                        System.out.println("Phieu da thanh toan, khong the sua");
                        return;
                    }
                    if (pn.getNgayNhap().plusDays(gioiHanNgay).isBefore(now)) {
                        System.out.println("Phieu nhap da qua 1 ngay khong the sua");
                        return;
                    }

                    System.out.print("Nhap trang thai moi (1: Da TT, 0: Chua TT): ");
                    String input = sc.nextLine().trim();
                    boolean trangThaiMoi = input.equals("1");
                    pn.setTrangThai(trangThaiMoi);
                    if (trangThaiMoi) {
                        int slNew = pn.capNhatKho(qlsp.getDsSanPham(), qlsp.getSoLuong());
                        qlsp.setSoLuong(slNew);
                    }
                    write();
                    qlsp.write();
                    pn.xuat();
                    System.out.println("Enter de tiep tuc.");
                    sc.nextLine();
                    break;
                case 0: System.out.println("Quay lai menu chinh..."); break;
                default: System.out.println("Lua chon khong hop le!"); break;
            }
        } while (chon != 0);
    }

}