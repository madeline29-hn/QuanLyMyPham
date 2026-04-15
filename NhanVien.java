package QuanLyMyPham;
import java.util.Scanner;
import java.time.LocalDate;
public abstract class NhanVien extends ConNguoi {
    private String maNhanVien;
    private String soCCCD;
    private float luongCoBan;
    private Double ngayCong;
    private String chucVu;
    private boolean trangThai;
    private LocalDate ngayVaoLam;
    static  Scanner sc=new Scanner(System.in);
    public NhanVien(){}
    public NhanVien(String maNhanVien,String soCCCD,float luongCoBan,Double ngayCong,String chucVu,LocalDate ngayVaoLam,boolean trangThai){
        this.maNhanVien=maNhanVien;
        this.soCCCD=soCCCD;
        this.luongCoBan=luongCoBan;
        this.ngayCong=ngayCong;
        this.chucVu=chucVu;
        this.ngayVaoLam=ngayVaoLam;
        this.trangThai=trangThai;
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
    public void setLuongCoban(float luongCoBan) {
        this.luongCoBan = luongCoBan;
    }
    public Double getNgayCong() {return ngayCong;}
    public void setNgayCong(Double ngayCong) {
        this.ngayCong = ngayCong;
    }
    public String getChucVu(){return chucVu;}
    public void setChucVu(String chucVu){this.chucVu=chucVu;}
    public LocalDate getNgayVaoLam(){return ngayVaoLam;}
    public void setNgayVaoLam(LocalDate ngayVaoLam){this.ngayVaoLam=ngayVaoLam;}
    public boolean isTrangThai() {return trangThai;}
    public void setTrangThai(boolean trangThai) {this.trangThai = trangThai;}

    public void Nhap() {
        super.nhap();
        while (true) {
            System.out.print("Nhap ma nhan vien (5 chu so) ");
            this.maNhanVien = sc.nextLine().trim();
            if (this.maNhanVien.matches("\\d{5}")) {
                setMaNhanVien("NV" + maNhanVien);
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
        System.out.print("Nhap vao ngay cong ");
        this.ngayCong = sc.nextDouble();
        System.out.println("Chon chuc vu:");
        System.out.println("1. Quan Ly");
        System.out.println("2. Thu Ngan");
        System.out.println("3. Nhan Vien CSKH");
        System.out.print("Lua chon cua ban (1-3): ");
        int luaChon = sc.nextInt();
        sc.nextLine();
        this.luongCoBan(luaChon);
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
    @Override
    public String toString() {
        return super.toString() + String.format("| %-10s | %-13s | %-15s | %-12s | %, -15.0f | %-5d |",
                this.maNhanVien,
                this.soCCCD,
                this.chucVu,
                this.ngayVaoLam, // Cột mới thêm vào
                this.luongCoBan * this.ngayCong,
                this.trangThai ? "Dang lam" : "Nghi lam");
    }

    public void xuat() {
        // Tăng số lượng dấu gạch ngang để bảng không bị vỡ
        super.xuat();
        String line = "---------------------------------------------------------------------------------------------";
        System.out.println(line);
        // Tiêu đề cột cũng phải khớp định dạng với toString()
        System.out.printf("| %-10s | %-13s | %-15s | %-12s | %-15s | %-5s |\n",
                "Ma NV", "So CCCD", "Chuc vu", "Ngay vao", "Luong", "TT");
        System.out.println(line);
        System.out.println(this.toString());
        System.out.println(line);
    }

    public void chamCong(){
        this.ngayCong++;
        System.out.println(this.maNhanVien +" Da cham cong thanh cong");
        System.out.println("So ngay cong hien tai " +this.ngayCong);
    }
    public abstract void tinhLuong();

    //public abstract void hienThiTongLuong();
    public String getVaiTro() {
        return "Chuc vu hien tai: " + this.chucVu;
    }
    public void luongCoBan(int luaChon){
        switch (luaChon) {
            case 1:
                this.chucVu = "Quan Ly";
                this.luongCoBan = 600000f;
                break;
            case 2:
                this.chucVu = "Thu Ngan";
                this.luongCoBan = 300000f;
                break;
            case 3:
                this.chucVu = "Nhan Vien CSKH";
                this.luongCoBan = 300000f;
                break;
            default:
                this.chucVu = "Chua xac dinh";
                this.luongCoBan = 200000f;
                break;
        }
    }
}


