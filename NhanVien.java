package QuanLyMyPham;
import java.util.Scanner;
import java.time.LocalDate;
public abstract class NhanVien extends ConNguoi {
    private String maNhanVien;
    private String soCCCD;
    private double ngayCong;
    private float luongCoBan;
//    private String chucVu;
    private boolean trangThai;
    private LocalDate ngayVaoLam;
    static  Scanner sc=new Scanner(System.in);
    public NhanVien(){
        super();
    }

//    public NhanVien(String maNhanVien,String soCCCD,float luongCoBan,Double ngayCong,String chucVu,LocalDate ngayVaoLam,boolean trangThai){
//        this.maNhanVien=maNhanVien;
//        this.soCCCD=soCCCD;
//        this.luongCoBan=luongCoBan;
//        this.ngayCong=ngayCong;
//        this.chucVu=chucVu;
//        this.ngayVaoLam=ngayVaoLam;
//        this.trangThai=trangThai;
//    }

    public NhanVien(String hoTen, String sdt, String email, String gioiTinh,LocalDate ngaySinh,
                    String maNhanVien, String soCCCD,double ngayCong,float luongCoBan) {
        super(hoTen, sdt, email, gioiTinh, ngaySinh);
        this.maNhanVien = maNhanVien;
        this.soCCCD = soCCCD;
        this.ngayCong= ngayCong;
        this.luongCoBan= luongCoBan;
    }

    public String getMaNhanVien() {
        return maNhanVien;
    }
    public void setMaNhanVien(String maNhanVien) {
        this.maNhanVien = maNhanVien;
    }
    public String getSoCCCD() {
        return soCCCD;
    }
    public void setSoCCCD(String soCCCD) {
        this.soCCCD = soCCCD;
    }
    public float getLuongCoBan() {return luongCoBan;}
    public void setLuongCoBan(float luongCoBan) {
        this.luongCoBan = luongCoBan;
    }
//    public String getChucVu(){return chucVu;}
//    public void setChucVu(String chucVu){this.chucVu=chucVu;}
    public double getNgayCong() {return ngayCong;}
    public void setNgayCong(double ngayCong) {
        this.ngayCong = ngayCong;
    }
//
    public LocalDate getNgayVaoLam(){return ngayVaoLam;}
    public void setNgayVaoLam(LocalDate ngayVaoLam){this.ngayVaoLam=ngayVaoLam;}
    public boolean isTrangThai() {return trangThai;}
    public void setTrangThai(boolean trangThai) {this.trangThai = trangThai;}

    public void nhap() {
        super.nhap();
        while (true) {
            System.out.print("Nhap ma nhan vien (5 chu so) ");
//            this.maNhanVien = sc.nextLine().trim();
//            if (this.maNhanVien.matches("\\d{5}")) {
//                setMaNhanVien("NV" + maNhanVien);
//                break;
            String input = sc.nextLine().trim();
            if (input.matches("\\d{5}")) {
                this.maNhanVien = "NV" + input;
                break;
            } else {
                System.out.println("Loi: Ma nhan vien phai co dung 5 so");
            }
        }
        while (true) {
            System.out.print("Nhap so CCCD (12 so) ");
            this.soCCCD = sc.nextLine();
            if (this.soCCCD.matches("\\d{12}")) {
                break;
            } else {
                System.err.println("Loi: So CCCD phai co dung 12 so");
            }
        }
        while (true) {
            try {
                System.out.print("Nhap vao ngay cong (0 - 31): ");
                this.ngayCong = Double.parseDouble(sc.nextLine());

                if (this.ngayCong >= 0 && this.ngayCong <= 31) {
                    break;
                } else {
                    System.out.println("Loi: Ngay cong phai tu 0 den 31");
                }
            } catch (Exception e) {
                System.out.println("Nhap sai dinh dang, vui long nhap lai!");
            }
        }
       while (true) {
           try {
                System.out.print("Nhap ngay vao lam (yyyy-mm-dd): ");
                String inputDate = sc.nextLine().trim();
               LocalDate parsedDate = LocalDate.parse(inputDate);
               if (parsedDate.isBefore(LocalDate.now())) {
                   this.ngayVaoLam = parsedDate;
                   break;
                } else {
                    System.out.println("Loi: Ngay vao lam phai nho hon ngay hom nay (" + LocalDate.now() + ")!");
                }
           } catch (Exception e) {
               System.out.println("Dinh dang ngay khong hop le, vui long nhap lai (yyyy-mm-dd)!");
           }
       }
        System.out.print("Nhap vao trang thai (1 - Dang lam, 0 - Nghi lam) ");
        int st = sc.nextInt();
        this.trangThai = (st == 1);
        sc.nextLine();
    }

//    @Override
//    public String toString() {
//        return String.format("| %-10s | %-12s | %-12s | %-10s | %-10.0f |",
//                this.getMaNhanVien(),
//                this.getSoCCCD(),
//                this.getNgayVaoLam(),
//                this.getVaiTro(),
//                this.getLuongCoBan() * this.getNgayCong()
//        );
//    }

    @Override
    public String toString() {
        return String.format("| %-10s | %-12s | %-12s | %-10s | %-10s | %-12.0f |",
                this.getMaNhanVien(),
                this.getSoCCCD(),
                this.getNgayVaoLam(),
                this.getVaiTro(),
                (this.isTrangThai() ? "Dang lam" : "Nghi"),
                this.getLuongCoBan() * this.getNgayCong()
        );
    }

    public void xuat() {
        // Tăng số lượng dấu gạch ngang để bảng không bị vỡ
        super.xuat();
        String line = "---------------------------------------------------------------------------------------------";
        System.out.println(line);
        // Tiêu đề cột cũng phải khớp định dạng với toString()
        System.out.printf("| %-10s | %-13s | %-12s | %-5s |\n",
                "Ma NV", "So CCCD", "Ngay vao", "TT");
        System.out.println(line);
        System.out.println(this.toString());
        System.out.println(line);
    }

//    @Override
//    public String toString() {
//    return String.format("| %-10s | %-13s | %-12s | %-10.2f | %-10b|",
//            maNhanVien, soCCCD, ngayVaoLam, ngayCong,trangThai);
//}
//
//    public void xuat() {
//        System.out.println(this.toString()); // Các lớp con sẽ tự động dùng toString của riêng nó
//    }

    public void chamCong() {
        if (this.ngayCong < 31) {
            this.ngayCong++;
            System.out.println(this.maNhanVien + " Da cham cong thanh cong");
            System.out.println("So ngay cong hien tai " + this.ngayCong);
        }
    }
    public abstract double tinhLuong();
    public abstract String getVaiTro();
}


