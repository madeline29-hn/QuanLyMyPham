package QuanLyMyPham;
import java.time.LocalDate;

public class ThuNgan extends NhanVien {
    private String maQuay;
    private int caLam;

    public ThuNgan() {
        super();
        this.setLuongCoBan(300000);
        this.setNgayCong(0);
    }

    public ThuNgan(String hoTen, String sdt, String email, String gioiTinh, LocalDate ngaySinh,
                   String maNhanVien, String soCCCD, double ngayCong, float luongCoBan, String maQuay, int caLam) {
        super(hoTen, sdt, email, gioiTinh, ngaySinh, maNhanVien, soCCCD, ngayCong, luongCoBan);
        this.maQuay = maQuay;
        this.caLam = caLam;
    }

    public String getMaQuay() { return maQuay; }
    public void setMaQuay(String maQuay) { this.maQuay = maQuay; }

    public int getCaLam() { return caLam; }
    public void setCaLam(int caLam) { this.caLam = caLam; }

    @Override
    public double tinhLuong() {
        return (double) (getLuongCoBan() * getNgayCong());
    }

    @Override
    public String getVaiTro() {
        return "Thu Ngan";
    }

    @Override
    public void nhap(NhanVien[] ds, int soLuong) {
        super.nhap(ds, soLuong);

        while (true) {
            System.out.print("Nhap ma quay (chon 1-4): ");
            String input = sc.nextLine().trim();
            if (input.matches("[1-4]")) {
                this.maQuay = "MQ" + input;
                break;
            } else {
                System.out.println("Loi: Ma quay chi duoc phep chon tu 1 den 4");
            }
        }

        while (true) {
            try {
                System.out.print("Chon ca lam (1: 8h-15h, 2: 15h-22h): ");
                int chonCa = Integer.parseInt(sc.nextLine());
                if (chonCa == 1 || chonCa == 2) {
                    this.caLam = chonCa;
                    break;
                } else {
                    System.out.println("Loi: Chi co ca 1 hoac ca 2!");
                }
            } catch (Exception e) {
                System.out.println("Loi: Vui long nhap so 1 hoac 2!");
            }
        }
    }

    @Override
    public String toString() {
        return super.toString() + String.format(" %-8s | %-8s |",
                maQuay,
                (caLam == 1 ? "Sang" : "Chieu")
        );
    }

    @Override
    public void xuat() {
        System.out.println(this.toString());
    }

    @Override
    public String toDataString() {
        return super.toDataString() + ";" + maQuay + ";" + caLam;
    }

    @Override
    public void fromString(String[] data) {
        super.fromString(data);
        this.maQuay = data[12];
        this.caLam = Integer.parseInt(data[13]);
    }
}