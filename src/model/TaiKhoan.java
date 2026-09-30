package model;
import manager.*;
import java.util.Scanner;
public class TaiKhoan {
    private String tenDN;
    private String matKhau;
    private String vaiTro;
    private NhanVien nv ;
    public static Scanner sc = new Scanner(System.in);

    public TaiKhoan() {
    }

    public NhanVien getNv() {
        return nv;
    }

    public void setNv(NhanVien nv) {
        this.nv = nv;
    }

    public TaiKhoan(String tenDN, String matKhau, String vaiTro) {
        this.tenDN = tenDN;
        this.matKhau = matKhau;
        this.vaiTro = vaiTro;
    }

    public String getVaiTro() {
        return vaiTro;
    }

    public void setVaiTro(String vaiTro) {
        this.vaiTro = vaiTro;
    }

    public String getTenDN() {
        return tenDN;
    }

    public void setTenDN(String tenDN) {
        this.tenDN = tenDN;
    }

    public String getMatKhau() {
        return matKhau;
    }
    public NhanVien getNhanVien() {
        return this.nv;
    }
    public void setMatKhau(String matKhau) {
        this.matKhau = matKhau;
    }

    public boolean ktDangNhap(String tenDN, String matKhau) {
        return this.tenDN.equalsIgnoreCase(tenDN) && this.matKhau.equalsIgnoreCase(matKhau);
    }
    public void nhap(String vtHienTai) {
        try {
            if (vtHienTai != null && vtHienTai.equalsIgnoreCase("Quan ly")) {
                while (true) {
                    System.out.println("Chon vai tro:");
                    System.out.println("1. Quan ly ");
                    System.out.println("2. Thu Ngan ");
                    System.out.println("3. CSKH");
                    System.out.println("4. Khach hang");
                    int chon = Integer.parseInt(sc.nextLine());

                    boolean tiepTuc = false;
                    switch (chon) {
                        case 1:
                            setVaiTro("Quan Ly");
                            tiepTuc = true;
                            break;
                        case 2:
                            setVaiTro("Thu Ngan");
                            tiepTuc = true;
                            break;
                        case 3:
                            setVaiTro("CSKH");
                            tiepTuc = true;
                            break;
                        case 4:
                            setVaiTro("Khach Hang");
                            tiepTuc = true;
                            break;
                        default:
                            System.out.println("Chon khong hop le! Vui long chon tu 1 den 4.");
                    }

                    if (tiepTuc) {
                        break;
                    }
                }

                while (true) {
                    System.out.println("Nhap ten dang nhap: ");
                    String user = sc.nextLine().trim();
                    if (user.contains(" "))
                        System.out.println("Ten dang nhap khong duoc co khoang trang");
                    else {
                        setTenDN(user);
                        break;
                    }
                }
            } else {
                System.out.println("Ban khong co quyen thay doi vai tro khach hang");
                setVaiTro("Khach Hang");
                System.out.println("Nhap ten dang nhap: ");
                setTenDN(sc.nextLine().trim());
            }
        }
        catch (Exception e) {
            System.out.println("Co loi xay ra: " + e.getMessage());
        }
    }
            public void nhapMatKhau () {
                while (true) {
                    System.out.println("Nhap mat khau");
                    String pass = sc.nextLine();
                    if (pass.contains(" "))
                        System.out.println("Mat khau khong duoc co khoang trang");
                    else {
                        setMatKhau(pass);
                        break;
                    }
                }
            }
    public void doiMatKhau() {
        System.out.println("--- THAY DOI MAT KHAU ---");
        while (true) {
            System.out.print("Nhap mat khau moi: ");
            String pass = sc.nextLine();
            if (pass.contains(" ")) {
                System.out.println("Loi: Mat khau khong duoc chua khoang trang!");
            } else {
                this.matKhau = pass;
                System.out.println("Cap nhat mat khau thanh cong!");
                break;
            }
        }
    }

    
    public String toString() {
        String result = "\n--------------------------------------------\n";
        result += String.format("|%-13s|%-15s|%-12s|", "TEN DANG NHAP", "MAT KHAU", "VAI TRO");
        result += "\n--------------------------------------------\n";
        result += String.format("|%-13s|%-15s|%-12s|", tenDN, matKhau, vaiTro);
        result += "\n--------------------------------------------\n";
        return result;
    }

    public void xuat() {
        System.out.println(toString());
    }

    public static Scanner getSc() {
        return sc;
    }

    public static void setSc(Scanner sc) {
        TaiKhoan.sc = sc;
    }
}