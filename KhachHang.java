package QuanLyMyPham;

public class KhachHang extends ConNguoi {
    private String maKH, lichSuMuaHang, thuHang; 
    private double tongChiTieu; 
    private double diemTichLuy;

    public KhachHang() {
    }
    public KhachHang(String maKH, String lichSuMuaHang, String thuHang, double tongChiTieu) {
        this.maKH = maKH;
        this.lichSuMuaHang = lichSuMuaHang;
        this.thuHang = thuHang;
        this.tongChiTieu = tongChiTieu;
    }
    public double getDiemTichLuy(){
        return diemTichLuy;
    }

    public void setDiemTichLuy(double diemTichLuy){
        this.diemTichLuy = diemTichLuy;
    }
    public String getMaKH() {
        return maKH;
    }
    public void setMaKH(String maKH) {
        this.maKH = maKH;
    }
   
    public String getLichSuMuaHang() {
        return lichSuMuaHang;
    }
    public void setLichSuMuaHang(String lichSuMuaHang) {
        this.lichSuMuaHang = lichSuMuaHang;
    }
    public String getThuHang() {
        return thuHang;
    }
    public void setThuHang(String thuHang) {
        this.thuHang = thuHang;
    }
    public double getTongChiTieu() {
        return tongChiTieu;
    }
    public void setTongChiTieu(double tongChiTieu) {
        this.tongChiTieu = tongChiTieu;
    }
    public String getVaiTro() {
        return "Khach Hang";
    }
    @Override
    public void nhap(){
        super.nhap();
        while (true) {
            System.out.println("Nhap ma khach hang (5 chu so): ");
            this.maKH = sc.nextLine().trim();
            if (this.maKH.matches("\\d{5}")) {
                setMaKH("KH" + this.maKH);
                break;
            } else {
                System.out.println("Ma khach hang phai co dung 5 chu so!");
            }
        }
        System.out.println("Nhap lich su mua hang: ");
        setLichSuMuaHang(sc.nextLine().trim());

        System.out.println("Nhap tong chi tieu: ");
        setTongChiTieu(Double.parseDouble(sc.nextLine().trim())); 
        capNhatDiemVaThuHang(getTongChiTieu());
    }

    public void capNhatDiemVaThuHang(double tongChiTieu) {
        double diemMoi = tongChiTieu * 0.01;
        this.diemTichLuy += diemMoi;
        //this.tongChiTieu += tongChiTieu;

        if (diemTichLuy < 5000) {
            this.thuHang = "Dong";
        } else if (diemTichLuy < 20000) {
            this.thuHang = "Bac";
        } else if (diemTichLuy < 50000) {
            this.thuHang = "Vang";
        } else {
            this.thuHang = "Kim cuong";
        }
    }

 /*   @Override
    public String toString() {
    return String.format(
        "| %-12s | %-10s | %-20s | %-10s | %12.2f | %10.2f | %-20s |",
        getVaiTro(), maKH, super.getHoTen(), thuHang, tongChiTieu, diemTichLuy, lichSuMuaHang
    );
}

@Override
public void xuat() {
    String header = String.format(
        "| %-12s | %-10s | %-20s | %-10s | %-12s | %-10s | %-20s |",
        "VaiTro", "MaKH", "HoTen", "ThuHang", "TongChiTieu", "DiemTL", "LichSuMuaHang"
    );
    String line = new String(new char[header.length()]).replace("\0", "-");
    System.out.println(line);
    System.out.println(header);
    System.out.println(line);
    System.out.println(toString());
    System.out.println(line);
} */


public String toString() {
    String result = "\n=====================================THONG TIN KHACH HANG=====================================\n";
    result += "\n---------------------------------------------------------------------------------------------------------\n";
    result += String.format(
        "| %-12s | %-10s | %-20s | %-10s | %-12s | %-10s | %-20s |",
        "VaiTro", "MaKH", "HoTen", "ThuHang", "TongChiTieu", "DiemTL", "LichSuMuaHang"
    );
    result += "\n---------------------------------------------------------------------------------------------------------\n";
    result += String.format(
        "| %-12s | %-10s | %-20s | %-10s | %12.2f | %10.2f | %-20s |",
        getVaiTro(), maKH, super.getHoTen(), thuHang, tongChiTieu, diemTichLuy, lichSuMuaHang);
    result += "\n---------------------------------------------------------------------------------------------------------\n";
    return result;
}
public void xuat() {
    System.out.println(toString());

}
}