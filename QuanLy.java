package QuanLyMyPham;
import java.time.LocalDate;
public class QuanLy extends NhanVien {
    private String kinhNghiem;

    public QuanLy() {
        super();
        this.setLuongCoBan(600000);
        this.setNgayCong(0);
    }

    public QuanLy(String hoTen, String sdt, String email, String gioiTinh, LocalDate ngaySinh,
                  String maNhanVien, String soCCCD, double ngayCong, float luongCoBan, String kinhNghiem) {
        super(hoTen, sdt, email, gioiTinh, ngaySinh, maNhanVien, soCCCD, ngayCong, luongCoBan);
        this.kinhNghiem = kinhNghiem;
    }

    public String getKinhNghiem() {
        return kinhNghiem;
    }

    public void setKinhNghiem(String kinhNghiem) {
        this.kinhNghiem = kinhNghiem;
    }
    @Override
    public void nhap(NhanVien[] ds, int soLuong) {
        super.nhap(ds, soLuong);
        System.out.print("Nhap kinh nghiem (mo ta): ");
        this.kinhNghiem = sc.nextLine().trim();
    }

    @Override
    public double tinhLuong() {
        return (double) (getLuongCoBan() * getNgayCong());
    }

    @Override
    public String getVaiTro() {
        return "Quan Ly";
    }

    @Override
    public String toString() {
        return super.toString() + String.format(" %-15s |", kinhNghiem);
    }

    @Override
    public void xuat() {
        System.out.println(this.toString());
    }

    @Override
    public String toDataString() {
        return super.toDataString() + ";" + kinhNghiem;
    }

    @Override
    public void fromString(String[] data) {
        super.fromString(data);
        this.kinhNghiem = data[12];
    }
}
