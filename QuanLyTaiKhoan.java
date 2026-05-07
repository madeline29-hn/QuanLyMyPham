package QuanLyMyPham;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Arrays;
public class QuanLyTaiKhoan implements iDocGhiFile, iQuanLyDanhSach {
    private TaiKhoan[] dsTaiKhoan = new TaiKhoan[30];
    private int soLuongTK = 0;
    static Scanner sc = new Scanner(System.in);

    public QuanLyTaiKhoan() {
    }

    public QuanLyTaiKhoan(TaiKhoan[] dsTaiKhoan, int soLuongTK) {
        this.dsTaiKhoan = dsTaiKhoan;
        this.soLuongTK = soLuongTK;
    }

    public TaiKhoan[] getDsTaiKhoan() {
        return dsTaiKhoan;
    }

    public void setDsTaiKhoan(TaiKhoan[] dsTaiKhoan) {
        this.dsTaiKhoan = dsTaiKhoan;
    }

    public int getSoLuongTK() {
        return soLuongTK;
    }

    public void setSoLuongTK(int soLuongTK) {
        this.soLuongTK = soLuongTK;
    }

    public boolean kiemTraTen(String tenDN) {
        for (int i = 0; i < soLuongTK; i++) {
            if (dsTaiKhoan[i] != null && dsTaiKhoan[i].getTenDN().equalsIgnoreCase(tenDN)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void nhapDanhSach(){
        nhapDanhSach("Khach Hang");
    }

    public void nhapDanhSach(String vtHienTai) {
        try {
            System.out.print("Nhap so luong tai khoan muon them: ");
            int n = Integer.parseInt(sc.nextLine());

            for (int i = 0; i < n; i++) {
                System.out.println("\n--- Nhap tai khoan thu " + (i + 1) + " ---");
                TaiKhoan tkMoi = new TaiKhoan();
                tkMoi.nhap(vtHienTai);
                while (kiemTraTen(tkMoi.getTenDN())) {
                    System.out.println("(!) Loi: Ten '" + tkMoi.getTenDN() + "' da ton tai!");
                    System.out.print("Vui long nhap lai ten khac: ");
                    String tenMoi = sc.nextLine().trim();
                    tkMoi.setTenDN(tenMoi);
                }
                tkMoi.nhapMatKhau();
                themVaoDanhSach(tkMoi);
                write();
            }
            System.out.println("\n==> Thanh cong!");
        } catch (Exception e) {
            System.out.println("Loi: " + e.getMessage());
        }
    }

    @Override
    public void themVaoDanhSach() {
        nhapDanhSach("Khach Hang");
    }

    public void themVaoDanhSach(TaiKhoan tk) {
        if (soLuongTK >= dsTaiKhoan.length) {
            dsTaiKhoan = Arrays.copyOf(dsTaiKhoan, dsTaiKhoan.length + 10);
        }
        dsTaiKhoan[soLuongTK++] = tk;
    }


    public void xoaKhoiDanhSach(String tenDN) {
        for (int i = 0; i < soLuongTK; i++) {
            if (dsTaiKhoan[i] != null && dsTaiKhoan[i].getTenDN().equalsIgnoreCase(tenDN)) {
                for (int j = i; j < soLuongTK - 1; j++) {
                    dsTaiKhoan[j] = dsTaiKhoan[j + 1];
                }
                dsTaiKhoan[soLuongTK - 1] = null;
                soLuongTK--;
                System.out.println("Da xoa tai khoan co ten: " + tenDN);
                return;
            }
        }
        System.out.println("Khong tim thay tai khoan co ten: " + tenDN);
    }
    public void suaDanhSach(String tenDN, String vtHienTai) {
        for (int i = 0; i < soLuongTK; i++) {
            if (dsTaiKhoan[i] != null && dsTaiKhoan[i].getTenDN().equalsIgnoreCase(tenDN)) {
                dsTaiKhoan[i].nhap(vtHienTai);
                write();
                return;
            }
        }
        System.out.println("Khong tim thay tai khoan co ten: " + tenDN);
    }

    public void thongKeTheoKhoa() {
        int quanLy = 0, thuNgan = 0, cskh = 0, khachHang = 0;

        for (int i = 0; i < soLuongTK; i++) {
            if (dsTaiKhoan[i] != null && dsTaiKhoan[i].getVaiTro() != null) {
                String vaiTro = dsTaiKhoan[i].getVaiTro();
                if (vaiTro.equalsIgnoreCase("Quan Ly")) {
                    quanLy++;
                } else if (vaiTro.equalsIgnoreCase("Thu Ngan")) {
                    thuNgan++;
                } else if (vaiTro.equalsIgnoreCase("CSKH")) {
                    cskh++;
                } else if (vaiTro.equalsIgnoreCase("Khach Hang")) {
                    khachHang++;
                }
            }
        }

        System.out.println("\n--------------------------------------------------------------");
        System.out.println("Tong so tai khoan: " + soLuongTK);
        System.out.println("- Quan Ly: " + quanLy);
        System.out.println("- Thu Ngan: " + thuNgan);
        System.out.println("- CSKH: " + cskh);
        System.out.println("- Khach Hang: " + khachHang);
        System.out.println("--------------------------------------------------------------");
    }

    public void timKiemChinhXac(String tenDN) {
        for (int i = 0; i < soLuongTK; i++) {
            if (dsTaiKhoan[i] != null && dsTaiKhoan[i].getTenDN().equalsIgnoreCase(tenDN)) {
                dsTaiKhoan[i].xuat();
                return;
            }
        }
        System.out.println("Khong tim thay tai khoan co user: " + tenDN);
    }

    public void timKiemTuongDoi(String tuKhoa) {
        boolean found = false;
        String tuKhoaLower = tuKhoa.toLowerCase();

        for (int i = 0; i < soLuongTK; i++) {
            if (dsTaiKhoan[i] != null && (dsTaiKhoan[i].getTenDN().toLowerCase().contains(tuKhoaLower) ||
                    (dsTaiKhoan[i].getVaiTro() != null && dsTaiKhoan[i].getVaiTro().toLowerCase().contains(tuKhoaLower)))) {

                dsTaiKhoan[i].xuat();
                found = true;
            }
        }

        if (!found) {
            System.out.println("Khong tim thay tai khoan nao khop voi tu khoa: " + tuKhoa);
        }
    }

    public void xuatDanhSach() {
        for (int i = 0; i < soLuongTK; i++) {
            if (dsTaiKhoan[i] != null) {
                System.out.println("Thong tin tai khoan thu " + (i + 1) + ":");
                dsTaiKhoan[i].xuat();
            }
        }
    }

    @Override
    public void read() {
        try {
            FileReader fr = new FileReader("D:\\Java\\QuanLyMyPham\\dsTaiKhoan.txt");
            BufferedReader br = new BufferedReader(fr);
            String line;
            soLuongTK = 0;
            int count = 0;

            while ((line = br.readLine()) != null) {
                count++;
                if (line.trim().isEmpty()) {
                    continue;
                }
                try {
                    String[] data = line.split(",");
                    if (data.length < 3) {
                        System.out.println("Loi o dong " + count + ": Du lieu khong dung cot ");
                        continue;
                    }

                    TaiKhoan tk = new TaiKhoan(data[0].trim(), data[1].trim(), data[2].trim());
                    if (soLuongTK >= dsTaiKhoan.length) {
                        dsTaiKhoan = Arrays.copyOf(dsTaiKhoan, dsTaiKhoan.length + 10);
                    }
                    dsTaiKhoan[soLuongTK++] = tk;
                } catch (Exception e) {
                    System.out.println("Loi o dong " + count + ": " + e.getMessage());
                }
            }
            System.out.println("Da doc xong thong tin " + soLuongTK + " tai khoan tu file!");
            br.close();
            fr.close();
        } catch (FileNotFoundException ex) {
            System.out.println("Loi khi doc file : " + ex.getMessage());
        } catch (Exception e) {
            System.out.println("Loi khac: " + e.getMessage());
        }
    }

    @Override
    public void write() {
        try {
            FileWriter fw = new FileWriter("D:\\Java\\QuanLyMyPham\\dsTaiKhoan.txt", false);
            BufferedWriter bw = new BufferedWriter(fw);
            for (int i = 0; i < soLuongTK; i++) {
                if (dsTaiKhoan[i] != null) {
                    bw.write(dsTaiKhoan[i].getTenDN() + "," + dsTaiKhoan[i].getMatKhau() + "," + dsTaiKhoan[i].getVaiTro());
                    if (i < soLuongTK - 1) {
                        bw.newLine();
                    }
                }
            }
            System.out.println("Da ghi thong tin " + soLuongTK + " tai khoan vao file!");
            bw.close();
            fw.close();
        } catch (Exception e) {
            System.out.println("Loi khi ghi file: " + e.getMessage());
        }
    }

    public TaiKhoan timTaiKhoan(String tenDN, String matKhau) {
        for (int i = 0; i < soLuongTK; i++) {
            if (dsTaiKhoan[i] != null && dsTaiKhoan[i].getTenDN().equalsIgnoreCase(tenDN) && dsTaiKhoan[i].getMatKhau().equals(matKhau)) {
                return dsTaiKhoan[i];
            }
        }
        return null;
    }

    public void menu(QuanLySanPham qlsp, QuanLyNhanVien qlnv, QuanLyKhachHang qlkh,
                     QuanLyHoaDon qlhd, QuanLyPhieuNhap qlpn, QuanLyKM qlkm) {
        int chon;
        TaiKhoan tk = new TaiKhoan();
        do {
            System.out.println("\n================ QUAN LY DANH SACH TAI KHOAN ================");
            System.out.println("1. Nhap danh sach tai khoan moi");
            System.out.println("2. Xuat toan bo danh sach tai khoan");
            System.out.println("3. Xoa mot tai khoan (theo Ten DN)");
            System.out.println("4. Thao tac chi tiet (Sua thong tin)");
            System.out.println("5. Thong ke tai khoan");
            System.out.println("6. Tim kiem chinh xac theo Ten DN");
            System.out.println("7. Tim kiem tuong doi theo user/vaitro");
            System.out.println("0. Thoat");
            System.out.print("Nhap lua chon cua ban: ");

            while (true) {
                try {
                    chon = Integer.parseInt(sc.nextLine());
                    break;
                } catch (NumberFormatException e) {
                    System.out.print("Lua chon khong hop le. Vui long nhap lai: ");
                }
            }

            switch (chon) {
                case 1:
                    nhapDanhSach("Quan ly");
                    qlnv.write();
                    break;
                case 2:
                    if (soLuongTK == 0) {
                        System.out.println("Khong co tai khoan nao de xuat!");
                    } else {
                        xuatDanhSach();
                    }
                    break;
                case 3:
                    System.out.print("Nhap ten DN can xoa: ");
                    xoaKhoiDanhSach(sc.nextLine());
                    write();
                    break;
                case 4:
                    menuThaoTacChiTiet(qlnv,qlkh);
                    break;
                case 5:
                    thongKeTheoKhoa();
                    break;
                case 6:
                    if (soLuongTK == 0) {
                        System.out.println("Khong co tai khoan nao de tim kiem!");
                        break;
                    }
                    System.out.print("Nhap User can tim: ");
                    timKiemChinhXac(sc.nextLine());
                    break;
                case 7:
                    if (soLuongTK == 0) {
                        System.out.println("Khong co tai khoan nao de tim kiem!");
                        break;
                    }
                    System.out.print("Nhap tu khoa can tim (user/vaitro): ");
                    timKiemTuongDoi(sc.nextLine());
                    break;
                case 0:
                    System.out.println("Thoat chuong trinh.");
                    break;
                default:
                    System.out.println("Lua chon khong hop le!");
            }
        } while (chon != 0);
    }


    public void menuThaoTacChiTiet(QuanLyNhanVien qlnv, QuanLyKhachHang qlkh) {
    System.out.print("Nhap ten dang nhap ban muon thao tac: ");
    String tenDN = sc.nextLine();
    TaiKhoan tk = null;
    for (int i = 0; i < soLuongTK; i++) {
        if (dsTaiKhoan[i] != null && dsTaiKhoan[i].getTenDN().equalsIgnoreCase(tenDN)) {
            tk = dsTaiKhoan[i];
            break;
        }
    }

    if (tk == null) {
        System.out.println("(!) Khong tim thay tai khoan nay trong he thong!");
        return;
    }

    int chon;
    do {
        System.out.println("\n--- DANG THAO TAC TREN TAI KHOAN: " + tenDN + " ---");
        System.out.println("1. Xem thong tin chi tiet (Tai khoan & Ho so)");
        System.out.println("2. Tao ho so chi tiet moi (Neu chua co)");
        System.out.println("3. Sua thong tin ho so (Ten, SDT, Dia chi...)");
        System.out.println("0. Quay lai menu chinh");
        System.out.print("Nhap lua chon: ");

        while (true) {
            try {
                chon = Integer.parseInt(sc.nextLine());
                break;
            } catch (NumberFormatException e) {
                System.out.print("Lua chon khong hop le. Nhap lai: ");
            }
        }

        switch (chon) {
            case 1:
                System.out.println("\n>> THONG TIN DANG NHAP:");
                tk.xuat();
                if (tk.getVaiTro().equalsIgnoreCase("Khach Hang")) {
                    System.out.println(">> HO SO KHACH HANG:");
                    KhachHang kh = qlkh.timKhachHangTheoMa(tk.getTenDN());
                    if (kh != null) kh.xuat();
                    else System.out.println("(!) Khach hang nay chua cap nhat ho so chi tiet.");
                } else {
                    System.out.println(">> HO SO NHAN VIEN:");
                    qlnv.hienThiHoSoNhanVien(tk.getTenDN());
                }
                break;

            case 2:
                if (tk.getVaiTro().equalsIgnoreCase("Khach Hang")) {
                    if (qlkh.timKhachHangTheoMa(tk.getTenDN()) != null) {
                        System.out.println("(!) Khach hang nay da co ho so chi tiet.");
                    } else {
                        System.out.println("--- Tao ho so Khach hang cho: " + tk.getTenDN() + " ---");
                        KhachHang khMoi = new KhachHang();
                        khMoi.setMaKH(tk.getTenDN());
                        khMoi.nhap(qlkh.getDsKhachHang(), qlkh.getSoLuongKH());
                        qlkh.themVaoDanhSach(khMoi);
                        qlkh.write();
                    }
                } else {
                    NhanVien nvHienTai = qlnv.timNhanVienTheoMa(tk.getTenDN());
                    if (nvHienTai == null) {
                        System.out.println("--- Tao ho so Nhan vien cho: " + tk.getTenDN() + " ---");
                        NhanVien nvMoi;
                        String vt = tk.getVaiTro();
                        if (vt.equalsIgnoreCase("Quan Ly")) nvMoi = new QuanLy();
                        else if (vt.equalsIgnoreCase("Thu Ngan")) nvMoi = new ThuNgan();
                        else nvMoi = new NhanVienCSKH();

                        nvMoi.setMaNhanVien(tk.getTenDN());
                        nvMoi.nhap(qlnv.getDsNhanVien(), qlnv.getSoLuongNV());
                        qlnv.themVaoDanhSach(nvMoi);
                        qlnv.write();
                    } else {
                        System.out.println("(!) Nhan vien nay da co ho so chi tiet.");
                    }
                }
                break;

            case 3:
                if (tk.getVaiTro().equalsIgnoreCase("Khach Hang")) {
                    if (qlkh.timKhachHangTheoMa(tk.getTenDN()) != null) {
                        qlkh.suaDanhSach(tk.getTenDN());
                        qlkh.write();
                    } else {
                        System.out.println("(!) Khach hang chua co ho so. Vui long chon muc 2 de tao.");
                    }
                } else {
                    if (qlnv.timNhanVienTheoMa(tk.getTenDN()) != null) {
                        qlnv.suaDanhSach(tk.getTenDN());
                        qlnv.write();
                    } else {
                        System.out.println("(!) Nhan vien chua co ho so. Vui long chon muc 2 de tao.");
                    }
                }
                break;

            case 0:
                System.out.println("Quay lai...");
                break;
            default:
                System.out.println("Lua chon khong hop le!");
        }
    } while (chon != 0);
}


    public void dangNhap(QuanLySanPham qlsp, QuanLyNhanVien qlnv, QuanLyKhachHang qlkh,
                         QuanLyHoaDon qlhd, QuanLyPhieuNhap qlpn, QuanLyKM qlkm, KhachHang kh) {
        String tenDN = "";
        String matKhau = "";

        while (true) {
            System.out.print("User: ");
            tenDN = sc.nextLine();

            if (!kiemTraTen(tenDN)) {
                System.out.println("Loi: Ten dang nhap khong ton tai trong he thong! Vui long nhap lai.");
                continue;
            }
            break;
        }

        while (true) {
            System.out.print("Pass: ");
            matKhau = sc.nextLine();

            TaiKhoan tk = timTaiKhoan(tenDN, matKhau);

            if (tk == null) {
                System.out.println("Loi: Sai mat khau! Vui long nhap lai.");
                continue;
            }

            String vt = tk.getVaiTro();
            if (vt != null && (vt.equalsIgnoreCase("Quan Ly") || vt.equalsIgnoreCase("QuanLy"))) {
                roleQuanLy(qlsp, qlnv, qlkh, qlhd, qlpn, qlkm, tk);
            } else if (vt != null && vt.equalsIgnoreCase("CSKH")) {
                roleNhanVienCSKH(qlkh, qlhd, qlkm, qlsp, qlnv, tk);
            } else if (vt != null && (vt.equalsIgnoreCase("Thu Ngan") || vt.equalsIgnoreCase("ThuNgan"))) {
                roleNhanVienThuNgan(qlhd, qlpn, qlsp, qlnv, tk);
            } else {
                roleKhachHang(qlkh, kh, tk, qlhd);
            }
            break;
        }
    }

    public void roleQuanLy(QuanLySanPham qlsp, QuanLyNhanVien qlnv, QuanLyKhachHang qlkh, QuanLyHoaDon qlhd,
                           QuanLyPhieuNhap qlpn, QuanLyKM qlkm, TaiKhoan tk) {
        NhanVien nv = qlnv.timNhanVienTheoMa(tk.getTenDN());
        int chonQl;
        do {
            System.out.println("========================== Vai tro Quan Ly ==========================");
            System.out.println("1. Quan ly san pham");
            System.out.println("2. Quan ly phieu nhap");
            System.out.println("3. Quan ly hoa don");
            System.out.println("4. Quan ly nhan vien");
            System.out.println("5. Quan ly khach hang");
            System.out.println("6. Quan ly khuyen mai");
            System.out.println("7. Thong tin ca nhan");
            System.out.println("8. Quan ly tai khoan");
            System.out.println("0. Dang xuat");
            System.out.print("Nhap lua chon cua ban: ");

            while (true) {
                try {
                    chonQl = Integer.parseInt(sc.nextLine());
                    break;
                } catch (NumberFormatException e) {
                    System.out.print("Lua chon khong hop le. Vui long nhap lai: ");
                }
            }
            switch (chonQl) {
                case 1:
                    qlsp.menu();
                    break;
                case 2:
                    qlpn.setNhanVienDangNhap(nv);
                    qlpn.menu();
                    break;
                case 3:
                    qlhd.setNhanVienDangNhap(nv);
                    qlhd.menu();
                    break;
                case 4:
                    qlnv.menu();
                    break;
                case 5:
                    qlkh.menu(qlhd);
                    break;
                case 6:
                    qlkm.menu();
                    break;
                case 7:
                    tk.xuat();
                    qlnv.hienThiHoSoNhanVien(tk.getTenDN());
                    break;
                case 8:
                    this.menu(qlsp, qlnv, qlkh, qlhd, qlpn, qlkm);
                    break;
                case 0:
                    System.out.println("Dang xuat thanh cong!");
                    return;
                default:
                    System.out.println("Lua chon khong hop le!");
            }
        } while (chonQl != 0);
    }

    public void roleNhanVienThuNgan(QuanLyHoaDon qlhd, QuanLyPhieuNhap qlpn, QuanLySanPham qlsp, QuanLyNhanVien qlnv, TaiKhoan tk) {
        int chonTn;
        do {
            System.out.println("========================== Vai tro Nhan Vien Thu Ngan ==========================");
            System.out.println("1. Quan ly hoa don");
            System.out.println("2. Quan ly phieu nhap");
            System.out.println("3. Quan ly san pham");
            System.out.println("4. Hien Thi thong tin ca nhan");
            System.out.println("5. Sua thong tin ca nhan");
            System.out.println("0. Dang xuat");

            chonTn = Integer.parseInt(sc.nextLine());
            NhanVien nv = qlnv.timNhanVienTheoMa(tk.getTenDN());
            switch (chonTn) {
                case 1:
                    qlhd.setNhanVienDangNhap(nv);
                    qlhd.menu();
                    break;
                case 2:
                    qlpn.setNhanVienDangNhap(nv);
                    qlpn.menu();
                    break;
                case 3:
                    qlsp.menu();
                    break;
                case 4:
                    tk.xuat();
                    qlnv.hienThiHoSoNhanVien(tk.getTenDN());
                    break;
                case 5:
                    qlnv.suaThongTinCaNhan(tk.getTenDN());
                    break;
                case 0:
                    System.out.println("Dang xuat thanh cong!");
                    return;
                default:
                    System.out.println("Lua chon khong hop le!");
            }
        } while (chonTn != 0);
    }

    public void roleNhanVienCSKH(QuanLyKhachHang qlkh, QuanLyHoaDon qlhd, QuanLyKM qlkm, QuanLySanPham qlsp, QuanLyNhanVien qlnv, TaiKhoan tk) {
        int chonCskh;
        do {
            System.out.println("========================== Vai tro Nhan Vien CSKH ==========================");
            System.out.println("1. Quan ly khach hang");
            System.out.println("2. Quan ly khuyen mai");
            System.out.println("3. Quan ly san pham");
            System.out.println("4. Hien thi thong tin ca nhan");
            System.out.println("5. Sua thong tin ca nhan");
            System.out.println("0. Dang xuat");

            chonCskh = Integer.parseInt(sc.nextLine());
            switch (chonCskh) {
                case 1:
                    qlkh.menu(qlhd);
                    break;
                case 2:
                    qlkm.menu();
                    break;
                case 3:
                    qlsp.menu();
                    break;
                case 4:
                    tk.xuat();
                    qlnv.hienThiHoSoNhanVien(tk.getTenDN());
                    break;
                case 5:
                    qlnv.suaThongTinCaNhan(tk.getTenDN());
                    break;
                case 0:
                    System.out.println("Dang xuat thanh cong!");
                    return;
                default:
                    System.out.println("Lua chon khong hop le. Vui long chon lai.");
            }
        } while (chonCskh != 0);
    }

    public void roleKhachHang(QuanLyKhachHang qlkh, KhachHang kh, TaiKhoan tk, QuanLyHoaDon qlhd) {
        int chonKh;
        do {
            System.out.println("========================== Vai tro Khach Hang ==========================");
            System.out.println("1. Xem thong tin ca nhan");
            System.out.println("2. Them thong tin ca nhan");
            System.out.println("3. Sua thong tin ca nhan");
            System.out.println("4. Sua mat khau");
            System.out.println("0. Dang xuat");
            System.out.print("Nhap lua chon cua ban: ");
            chonKh = Integer.parseInt(sc.nextLine());
            switch (chonKh) {
                case 1:
                    tk.xuat();
                    KhachHang khachHangHienTai = null;
                    if (qlkh != null && qlkh.getDsKhachHang() != null) {
                        for (int i = 0; i < qlkh.getSoLuongKH(); i++) {
                            if (qlkh.getDsKhachHang()[i] != null &&
                                    qlkh.getDsKhachHang()[i].getMaKH().equalsIgnoreCase(tk.getTenDN())) {
                                khachHangHienTai = qlkh.getDsKhachHang()[i];
                                break;
                            }
                        }
                    }
                    if (khachHangHienTai != null) {
                        System.out.println("\n--- THONG TIN CA NHAN ---");
                        khachHangHienTai.xuat();
                        System.out.println("\n--- LICH SU MUA HANG ---");
                        boolean coDonHang = false;
                        if (qlhd != null && qlhd.getDsHoaDon() != null) {
                            for (int i = 0; i < qlhd.getSoLuongHoaDon(); i++) {
                                HoaDon hd = qlhd.getDsHoaDon()[i];
                                if (hd != null && hd.getThongTinKH() != null &&
                                        hd.getThongTinKH().getMaKH().equalsIgnoreCase(khachHangHienTai.getMaKH())) {
                                    hd.xuat();
                                    coDonHang = true;
                                }
                            }
                        }
                        if (!coDonHang) {
                            System.out.println("Ban chua co lich su mua hang nao.");
                        }
                    } else {
                        System.out.println("(!) Ban chua cap nhat thong tin ca nhan.");
                    }
                    break;
                case 2:
                    boolean daCoThongTin = false;
                    if (qlkh != null && qlkh.getDsKhachHang() != null) {
                        for (int i = 0; i < qlkh.getSoLuongKH(); i++) {
                            if (qlkh.getDsKhachHang()[i] != null &&
                                    qlkh.getDsKhachHang()[i].getMaKH().equalsIgnoreCase(tk.getTenDN())) {
                                daCoThongTin = true;
                                break;
                            }
                        }
                    }

                    if (daCoThongTin) {
                        System.out.println("(!) Ban da co thong tin ca nhan. Vui long chon chuc nang Sua (Case 3).");
                    } else {
                        kh.nhap(qlkh.getDsKhachHang(), qlkh.getSoLuongKH());

                        qlkh.themVaoDanhSach(kh);
                        qlkh.write();
                        System.out.println("Them thong tin ca nhan thanh cong!");
                    }
                    break;
                case 3:
                    if (qlkh != null) {
                        qlkh.suaThongTinCaNhan(tk.getTenDN());
                    } else {
                        System.out.println("(!) He thong quan ly khach hang chua duoc khoi tao.");
                    }
                    break;
                case 4:
                    tk.doiMatKhau();
                    write();
                    break;

                case 0:
                    System.out.println("Dang xuat thanh cong!");
                    return;
                default:
                    System.out.println("Lua chon khong hop le!");
            }
        }while (chonKh != 0);
    }
}
