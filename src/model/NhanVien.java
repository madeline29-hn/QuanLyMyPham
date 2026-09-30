package model;
import manager.*;
import java.util.Scanner;
import java.time.LocalDate;

public abstract class NhanVien extends ConNguoi {
    private String maNhanVien;
    private String soCCCD;
    private double ngayCong;
    private double luongCoBan;
    private String chucVu;
    private boolean trangThai;
    private LocalDate ngayVaoLam;

    static Scanner sc = new Scanner(System.in);

    public NhanVien() {
        super();
    }

    public NhanVien(String hoTen, String sdt, String email, String gioiTinh, LocalDate ngaySinh,
                    String maNhanVien, String soCCCD, double ngayCong, float luongCoBan) {
        super(hoTen, sdt, email, gioiTinh, ngaySinh);
        this.maNhanVien = maNhanVien;
        this.soCCCD = soCCCD;
        this.ngayCong = ngayCong;
        this.luongCoBan = luongCoBan;
    }

    public String getMaNhanVien() { return maNhanVien; }
    public void setMaNhanVien(String maNhanVien) { this.maNhanVien = maNhanVien; }

    public String getSoCCCD() { return soCCCD; }
    public void setSoCCCD(String soCCCD) { this.soCCCD = soCCCD; }

    public double getLuongCoBan() { return luongCoBan; }
    public void setLuongCoBan(double luongCoBan) { this.luongCoBan = luongCoBan; }

    public String getChucVu() { return chucVu; }
    public void setChucVu(String chucVu) { this.chucVu = chucVu; }

    public double getNgayCong() { return ngayCong; }
    public void setNgayCong(double ngayCong) { this.ngayCong = ngayCong; }

    public LocalDate getNgayVaoLam() { return ngayVaoLam; }
    public void setNgayVaoLam(LocalDate ngayVaoLam) { this.ngayVaoLam = ngayVaoLam; }

    public boolean isTrangThai() { return trangThai; }
    public void setTrangThai(boolean trangThai) { this.trangThai = trangThai; }

    public void nhap(NhanVien[] ds, int soLuong) {
        super.nhap(ds, soLuong);
        while (true) {
            System.out.print("Nhap ma nhan vien (5 chu so): ");
            String input = sc.nextLine().trim();
            if (input.matches("\\d{5}")) {
                String Ma = "NV" + input;
                boolean trung = false;
                for (int i = 0; i < soLuong; i++) {
                    if (ds[i] != null && Ma.equalsIgnoreCase(ds[i].getMaNhanVien())) {
                        trung = true;
                        break;
                    }
                }
                if (trung) {
                    System.out.println("Loi: Ma nhan vien da ton tai!");
                } else {
                    this.maNhanVien = Ma;
                    break;
                }
            } else {
                System.out.println("Loi: Ma NV phai co dung 5 chu so!");
            }
        }
        while (true) {
            System.out.print("Nhap so CCCD (12 so): ");
            String inputCCCD = sc.nextLine().trim();
            if (inputCCCD.matches("0\\d{11}")) {
                boolean trungCCCD = false;
                for (int i = 0; i < soLuong; i++) {
                    if (ds[i] != null && inputCCCD.equals(ds[i].getSoCCCD())) {
                        trungCCCD = true;
                        break;
                    }
                }
                if (trungCCCD) {
                    System.out.println("Loi: So CCCD da ton tai!");
                } else {
                    this.soCCCD = inputCCCD;
                    break;
                }
            } else {
                System.out.println("Loi: CCCD phai co dung 12 so!");
            }
        }

        while (true) {
            try {
                System.out.print("Nhap ngay cong (0-31): ");
                this.ngayCong = Double.parseDouble(sc.nextLine());
                if (ngayCong >= 0 && ngayCong <= 31)
                    break;
                System.out.println("Loi: 0-31!");
            } catch (Exception e) {
                System.out.println("Nhap sai!");
            }
        }
        while (true) {
            try {
                System.out.print("Nhap ngay vao lam (yyyy-mm-dd): ");
                LocalDate d = LocalDate.parse(sc.nextLine().trim());
                if (d.isBefore(LocalDate.now())) {
                    this.ngayVaoLam = d;
                    break;
                }
                System.out.println("Phai nho hon hom nay!");
            } catch (Exception e) {
                System.out.println("Sai dinh dang!");
            }
        }
        while (true) {
            try {
                System.out.print("Trang thai (1-Dang lam | 0-Nghi): ");
                int st = Integer.parseInt(sc.nextLine());
                if (st == 0 || st == 1) {
                    this.trangThai = (st == 1);
                    break;
                }
            } catch (Exception e) {
            }
            System.out.println("Nhap 0 hoac 1!");
        }
        this.chucVu = getVaiTro();
    }

    @Override
    public String toString() {
        String ngaySinhStr = (super.getNgaySinh() != null) ? super.getNgaySinh().toString() : "N/A";
        return String.format("| %-8s | %-22s | %-11s | %-13s | %-11s | %-5s | %-22s | %-10s | %-10s |",
                this.maNhanVien,
                this.getHoTen(),
                ngaySinhStr,
                this.soCCCD,
                this.getSdt(),
                this.getGioiTinh(),
                this.getEmail(),
                this.getVaiTro(),
                (this.trangThai ? "Dang lam" : "Nghi")
        );
    }

    public void xuat() {
        System.out.println(this.toString());
    }

    public String toDataString() {
        return getVaiTro() + ";" +
                maNhanVien + ";" +
                getHoTen() + ";" +
                getNgaySinh() + ";" +
                soCCCD + ";" +
                getSdt() + ";" +
                getEmail() + ";" +
                getGioiTinh() + ";" +
                ngayCong + ";" +
                luongCoBan + ";" +
                ngayVaoLam + ";" +
                trangThai;
    }

    public void fromString(String[] data) {
        this.maNhanVien = data[1];
        this.setHoTen(data[2]);
        this.setNgaySinh(LocalDate.parse(data[3]));
        this.soCCCD = data[4];
        this.setSdt(data[5]);
        this.setEmail(data[6]);
        this.setGioiTinh(data[7]);
        this.ngayCong = Double.parseDouble(data[8]);
        this.luongCoBan = Float.parseFloat(data[9]);
        this.ngayVaoLam = LocalDate.parse(data[10]);
        this.trangThai = Boolean.parseBoolean(data[11]);
        this.chucVu = data[0];
    }

    public abstract double tinhLuong();
    public abstract String getVaiTro();
}


