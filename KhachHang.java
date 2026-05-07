package QuanLyMyPham;
import java.util.Arrays;
import java.time.LocalDate;

public class KhachHang extends ConNguoi {
    private String maKH, lichSuMuaHang, thuHang;
    private double tongChiTieu = 0;
    private double diemTichLuy = 0;
    private HoaDon[] lichSuMua = new HoaDon[20];
    private int soLuongHD = 0;

    public KhachHang() {
        this.lichSuMuaHang = "";
    }

    public KhachHang(String maKH, String lichSuMuaHang, String thuHang, double tongChiTieu) {
        this.maKH = maKH;
        this.lichSuMuaHang = (lichSuMuaHang != null) ? lichSuMuaHang : "";
        this.thuHang = thuHang;
        this.tongChiTieu = tongChiTieu;
        this.diemTichLuy = tongChiTieu * 0.01;
    }

    public double getDiemTichLuy() { return diemTichLuy; }
    public void setDiemTichLuy(double diemTichLuy) { this.diemTichLuy = diemTichLuy; }

    public String getMaKH() { return maKH; }
    public void setMaKH(String maKH) { this.maKH = maKH; }

    public String getLichSuMuaHang() { return lichSuMuaHang; }
    public void setLichSuMuaHang(String lichSuMuaHang) { this.lichSuMuaHang = lichSuMuaHang; }

    public String getThuHang() { return thuHang; }
    public void setThuHang(String thuHang) { this.thuHang = thuHang; }

    public double getTongChiTieu() { return tongChiTieu; }
    public void setTongChiTieu(double tongChiTieu) { this.tongChiTieu = tongChiTieu; }

    public String getVaiTro() { return "Khach Hang Thuong"; }

    public HoaDon[] getLichSuMua() { return lichSuMua; }
    public int getSoLuongHD() { return soLuongHD; }

    public void themHoaDon(HoaDon hd) {
        if (hd == null) return;
        if (soLuongHD >= lichSuMua.length) {
            lichSuMua = Arrays.copyOf(lichSuMua, lichSuMua.length + 10);
        }
        lichSuMua[soLuongHD++] = hd;
        if (this.lichSuMuaHang == null || this.lichSuMuaHang.isEmpty() || this.lichSuMuaHang.equals("null")) {
            this.lichSuMuaHang = hd.getMaHD();
        } else if (!this.lichSuMuaHang.contains(hd.getMaHD())) {
            this.lichSuMuaHang += "," + hd.getMaHD();
        }
    }

    public void capNhatHang() {
        this.diemTichLuy = this.tongChiTieu * 0.01;

        if (this.diemTichLuy < 5000) {
            this.thuHang = "Dong";
        } else if (this.diemTichLuy < 20000) {
            this.thuHang = "Bac";
        } else if (this.diemTichLuy < 50000) {
            this.thuHang = "Vang";
        } else {
            this.thuHang = "Kim cuong";
        }
    }

    public void capNhatSauMua(double soTien) {
        if (soTien <= 0) return;
        this.tongChiTieu += soTien;
        capNhatHang();
    }

    public void nhap(KhachHang[] ds, int soLuong) {
        super.nhap(ds, soLuong);

        while (true) {
            System.out.print("Nhap ma khach hang (5 chu so): ");
            String input = sc.nextLine().trim();
            if (input.matches("\\d{5}")) {
                String Ma = "KH" + input;
                boolean trung = false;
                for (int i = 0; i < soLuong; i++) {
                    if (ds[i] != null && Ma.equalsIgnoreCase(ds[i].getMaKH())) {
                        trung = true;
                        break;
                    }
                }
                if (trung) {
                    System.out.println("Loi: Ma khach hang nay da ton tai!");
                } else {
                    this.maKH = Ma;
                    break;
                }
            } else {
                System.out.println("Loi: Ma khach hang phai co dung 5 chu so!");
            }
        }

        System.out.print("Nhap lich su mua hang (Neu co, VD: HD001,HD002): ");
        this.lichSuMuaHang = sc.nextLine().trim();

        System.out.print("Nhap tong chi tieu: ");
        this.tongChiTieu = Double.parseDouble(sc.nextLine().trim());

        capNhatHang();
    }
    @Override
    public String toString() {
        String ngaySinhStr = (super.getNgaySinh() != null) ? super.getNgaySinh().toString() : "N/A";
        return String.format(
                "| %-8s | %-20s | %-12s | %-25s | %-12s | %-18s | %-10s | %-15.2f | %-12.2f |",
                maKH,
                super.getHoTen(),
                super.getSdt(),
                super.getEmail(),
                ngaySinhStr,
                getVaiTro(),
                thuHang,
                tongChiTieu,
                diemTichLuy
        );
    }

    public void xuat() {
        System.out.println(toString());
    }


    public String toDataString() {
        return maKH + ";"
                + super.getHoTen() + ";"
                + super.getSdt() + ";"
                + super.getEmail() + ";"
                + (super.getNgaySinh() != null ? super.getNgaySinh().toString() : "") + ";"
                + (lichSuMuaHang != null && !lichSuMuaHang.isEmpty() ? lichSuMuaHang : "null") + ";"
                + thuHang + ";"
                + tongChiTieu + ";"
                + diemTichLuy;
    }

    public void fromDataString(String data) {
        String[] parts = data.split(";");
        if (parts.length < 9) {
            throw new RuntimeException("Du lieu loi: " + data);
        }

        this.maKH = parts[0];
        super.setHoTen(parts[1]);
        super.setSdt(parts[2]);
        super.setEmail(parts[3]);

        if (!parts[4].trim().isEmpty()) {
            super.setNgaySinh(java.time.LocalDate.parse(parts[4].trim()));
        }

        this.lichSuMuaHang = parts[5].equals("null") ? "" : parts[5];
        this.thuHang = parts[6];
        this.tongChiTieu = Double.parseDouble(parts[7]);
        this.diemTichLuy = Double.parseDouble(parts[8]);
    }
}