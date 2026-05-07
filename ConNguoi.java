package QuanLyMyPham;
import java.time.LocalDate;
import java.util.Scanner;

public abstract class ConNguoi {
    private String hoTen, sdt, email, gioiTinh;
    private LocalDate ngaySinh;
    static Scanner sc = new Scanner(System.in);
    public abstract String getVaiTro();

    public ConNguoi(){}
    public ConNguoi(String hoTen, String sdt, String email, String gioiTinh, LocalDate ngaySinh) {
        this.hoTen = hoTen;
        this.sdt = sdt;
        this.email = email;
        this.gioiTinh = gioiTinh;
        this.ngaySinh = ngaySinh;
    }
    public String getHoTen() {
        return hoTen;
    }
    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }
    public String getSdt() {
        return sdt;
    }
    public void setSdt(String sdt) {
        this.sdt = sdt;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getGioiTinh() {
        return gioiTinh;
    }
    public void setGioiTinh(String gioiTinh) {
        this.gioiTinh = gioiTinh;
    }
    public LocalDate getNgaySinh() {
        return ngaySinh;
    }
    public void setNgaySinh(LocalDate ngaySinh) {
        this.ngaySinh = ngaySinh;
    }
    public void nhap(ConNguoi[] ds, int soLuong){
        while (true) {
            System.out.println("Nhap ho ten: ");
            String hoTenInput = sc.nextLine().trim();
            if (hoTenInput.matches("^[^0-9]+$")) { 
                setHoTen(hoTenInput);
                break;
            } else {
                System.out.println("Ho ten khong duoc chua so, vui long nhap lai!");
            }
        }

        while (true) {
            System.out.print("Nhap so dien thoai (10 so): ");
            String sdtInput = sc.nextLine().trim();
            if (sdtInput.matches("0\\d{9}")) {
                boolean trung = false;
                for (int i = 0; i < soLuong; i++) {
                    if (ds[i] != null && sdtInput.equals(ds[i].getSdt())) {
                        trung = true;
                        break;
                    }
                }
                if (trung) {
                    System.out.println("Loi: So dien thoai nay da ton tai!");
                } else {
                    setSdt(sdtInput);
                    break;
                }
            } else {
                System.out.println("Loi: SDT phai co 10 so và bat dau bang so 0!");
            }
        }
        while (true) {
            System.out.print("Nhap ten email (truoc @): ");
            String emailName = sc.nextLine().trim();
            if (!emailName.isEmpty() && !emailName.contains(" ")) {
                String fullEmail = emailName + "@gmail.com";
                boolean trung = false;
                for (int i = 0; i < soLuong; i++) {
                    if (ds[i] != null && fullEmail.equalsIgnoreCase(ds[i].getEmail())) {
                        trung = true;
                        break;
                    }
                }
                if (trung) {
                    System.out.println("Loi: Email nay da ton tai!");
                } else {
                    setEmail(fullEmail);
                    break;
                }
            } else {
                System.out.println("Loi: Ten email khong hop le!");
            }
        }

        while (true) {
            System.out.println("Nhap gioi tinh (Nam/Nu): ");
            String gtInput = sc.nextLine().trim();
            if (gtInput.equalsIgnoreCase("Nam") || gtInput.equalsIgnoreCase("Nu")) {
                setGioiTinh(gtInput);
                break;
            } else {
                System.out.println("Gioi tinh chi duoc la 'Nam' hoac 'Nu'!");
            }
        }
        while (true) {
            try {
                System.out.println("Nhap ngay sinh (yyyy-mm-dd): ");
                LocalDate ns = LocalDate.parse(sc.nextLine().trim());
                LocalDate today = LocalDate.now();
                if (today.getYear() - ns.getYear() >= 18 && today.getYear() - ns.getYear() <= 100) {
                    setNgaySinh(ns);
                    break;
                } else {
                    System.out.println("Tuoi phai lon hon hoac bang 18 va nho hon hoac bang 100, vui long nhap lai!");
                }

            } catch (Exception e) {
                System.out.println("Ngay sinh khong hop le dinh dang (yyyy-mm-dd), vui long nhap lai!");
            }
        }
    }

    @Override
    public String toString() {
        return String.format("|%-20s |%-12s |%-25s |%-5s |%-12s|", hoTen, sdt, email, gioiTinh, ngaySinh);
    }
    public void xuat() {
        System.out.println(toString());
    }

}

