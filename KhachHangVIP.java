package QuanLyMyPham;

import java.time.LocalDate;
public class KhachHangVIP extends KhachHang {
    private double phanTramGiam;

    public KhachHangVIP() {
    }

    public KhachHangVIP(String maKH, String lichSuMuaHang, String thuHang,
                        double tongChiTieu, String quyenLoi, String uuDai,
                        double phanTramGiam) {
        super(maKH, lichSuMuaHang, thuHang, tongChiTieu);
        this.phanTramGiam = phanTramGiam;
    }

    public double getPhanTramGiam() { return phanTramGiam; }
    public void setPhanTramGiam(double phanTramGiam) { this.phanTramGiam = phanTramGiam; }

    public boolean laVIP() {
        String hang = getThuHang();
        if (hang == null) return false;
        hang = hang.trim().toLowerCase().replace(" ", "");
        return hang.equals("vang") || hang.equals("kimcuong");
    }

    public double tinhGiamVIP(double tongTien) {
        if (!laVIP()) return 0;
        String hang = getThuHang().trim().toLowerCase().replace(" ", "");
        if (hang.equals("vang")) return tongTien * 0.05;
        if (hang.equals("kimcuong")) return tongTien * 0.1;
        return 0;
    }

    @Override
    public String getVaiTro(){ 
        return laVIP() ? "Khach hang VIP" : "Khach hang thuong"; 
    }

    @Override
    public void nhap(KhachHang[] ds, int soLuong) {
        super.nhap(ds, soLuong);
        System.out.println("Da cap nhat KH");
    }

    public void quaSN(LocalDate ngayMua) {
        if (ngayMua == null || getNgaySinh() == null) {
            System.out.println("Du lieu ngay thang chua ro rang!");
            return;
        }
        if (!laVIP()) {
            System.out.println("Khong du dieu kien VIP de nhan qua!");
            return;
        }
        if (ngayMua.getDayOfMonth() == getNgaySinh().getDayOfMonth() &&
            ngayMua.getMonthValue() == getNgaySinh().getMonthValue()) {
            System.out.println(" Tang qua sinh nhat cho VIP " + getThuHang().toUpperCase() + "!");
        } else {
            System.out.println("Khong phai ngay sinh -> khong tang qua!");
        }
    }
    @Override
    public String toString() {
        return super.toString();
    }
    @Override
    public void xuat() {
        System.out.println(toString());
    }        
}