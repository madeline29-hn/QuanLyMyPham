package QuanLyMyPham;

public class ThuNgan extends NhanVien {
    private String maQuay;
    private int soHoaDon;
    private double caLam;

    public ThuNgan() {
        super();
    }
    public String getMaQuay() { return maQuay; }
    public void setMaQuay(String maQuay) { this.maQuay = maQuay; }

    public int getSoHoaDon() { return soHoaDon; }
    public void setSoHoaDon(int soHoaDon) { this.soHoaDon = soHoaDon; }

    public double getCaLam() { return caLam; }
    public void setCaLam(double caLam) { this.caLam = caLam; }
    public void xuatHD() {
        System.out.println("-> Thu ngan " + getMaNhanVien() + " dang thuc hien: XUAT HOA DON.");
    }

    public double doanhThuCa() {
        return this.soHoaDon * 200000;
    }

    @Override
    public void tinhLuong() {
        double tongLuong = getLuongCoBan() * getNgayCong();
        System.out.printf("Tong luong Thu Ngan [%s]: %,.0f VND\n", getMaNhanVien(), tongLuong);
    }

    @Override
    public String getVaiTro() {
        return "Vai tro: Thu Ngan (Thanh toan tai quay " + this.maQuay + ")";
    }

    @Override
    public void Nhap() {
        super.Nhap();
        while(true) {
            System.out.print("Nhap ma quay (5 chu so): ");
            this.maQuay=sc.nextLine().trim();
            if (this.maQuay.matches("\\d{5}")) {
                setMaQuay("MQ" + maQuay);
                break;
            } else {
                System.out.println("Loi: Ma quay phai co dung 5 so");
            }
        }
        System.out.print("Nhap so hoa don: ");
        this.soHoaDon = sc.nextInt();
        System.out.print("Nhap ca lam : ");
        this.caLam = sc.nextDouble();
        sc.nextLine();
    }
}