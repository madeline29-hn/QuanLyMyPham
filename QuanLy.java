package QuanLyMyPham;

public class QuanLy extends NhanVien {
    private double doanhThuCuaHang;
    private double thuongDoanhThu;
    private String kinhNghiem;

    public QuanLy() {
        super();
        this.setLuongCoBan(600000);
        this.setNgayCong(0);
    }

    public double getDoanhThuCuaHang() {
        return doanhThuCuaHang;
    }

    public void setDoanhThuCuaHang(double doanhThuCuaHang) {
        this.doanhThuCuaHang = doanhThuCuaHang;
    }

    public double getThuongDoanhThu() {
        return thuongDoanhThu;
    }

    public void setThuongDoanhThu(double thuongDoanhThu) {
        this.thuongDoanhThu = thuongDoanhThu;
    }

    public String getKinhNghiem() {
        return kinhNghiem;
    }

    public void setKinhNghiem(String kinhNghiem) {
        this.kinhNghiem = kinhNghiem;
    }

    public void danhGiaNhanVien() {
        System.out.println("-> Quan ly " + getMaNhanVien() + " dang thuc hien: DANH GIA NHAN VIEN.");
    }

    public void duyetNghiPhep() {
        System.out.println("-> Quan ly " + getMaNhanVien() + " dang thuc hien: DUYET DON NGHI PHEP.");
    }

    public void tuyenNhanVien() {
        System.out.println("-> Quan ly " + getMaNhanVien() + " dang thuc hien: PHONG VAN TUYEN DUNG.");
    }

    @Override
    public double tinhLuong() {
        this.thuongDoanhThu = this.doanhThuCuaHang * 0.1;// thuong = 10% doanh thu cua hang

        double tongLuong = (getLuongCoBan() * getNgayCong()) + this.thuongDoanhThu;
        //System.out.printf("Tong luong Quan Ly [%s]: %,.0f VND\n", getMaNhanVien(), tongLuong);
        return tongLuong;
    }

    @Override
    public String getVaiTro() {
        return "Quan Ly";
    }

    @Override
    public void nhap() {
        super.nhap();

        while (true) {
            try {
                System.out.print("Nhap doanh thu cua hang: ");
                this.doanhThuCuaHang = Double.parseDouble(sc.nextLine());
                if (doanhThuCuaHang >= 0) break;
                else System.out.println("Phai >= 0");
            } catch (Exception e) {
                System.out.println("Nhap sai!");
            }
        }
        System.out.print("Nhap kinh nghiem (mo ta): ");
        this.kinhNghiem = sc.nextLine();
    }

    //    @Override
//    public String toString() {
//        return super.toString() + String.format(" %-15.0f | %-15s |",
//                doanhThuCuaHang, kinhNghiem);
//    }

    @Override
    public String toString() {
        return super.toString() + String.format(" %-15.0f | %-15.0f | %-20s |",
                doanhThuCuaHang,
                thuongDoanhThu,
                kinhNghiem
        );
    }

    @Override
    public void xuat() {
        super.xuat();

        System.out.printf("| %-15s | %-15s | %-20s |\n",
                "Doanh thu", "Thuong", "Kinh nghiem");

        System.out.printf("| %-15.0f | %-15.0f | %-20s |\n",
                doanhThuCuaHang,
                thuongDoanhThu,
                kinhNghiem
        );

        System.out.println("-----------------------------------------------------------------------");
    }
}