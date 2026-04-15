package QuanLyMyPham;
import java.util.Scanner;
import java.time.LocalDate;

public class SanPham {
    private String maSP;
    private String tenSanPham;
    private String thuongHieu;
    private String phanLoai;
    private double gia;
    private int soLuong;
    private LocalDate ngaySX;
    private LocalDate ngayHH;
    private String nuocSX;
    private NhaCungCap nhaCungCap;
    private boolean trangThai;
    private String congDung;
    static Scanner sc = new Scanner (System.in);

    public SanPham(){

    }

    public SanPham(String maSP, String tenSanPham, String thuongHieu, String phanLoai, double gia, int soLuong,
        LocalDate ngaySX, LocalDate ngayHH, String nuocSX, NhaCungCap nhaCungCap, boolean trangThai, String congDung){
            this.maSP = maSP;
            this.tenSanPham = tenSanPham;
            this.thuongHieu = thuongHieu;
            this.phanLoai = phanLoai;
            this.gia = gia;
            this.soLuong = soLuong;
            this.ngaySX = ngaySX;
            this.ngayHH = ngayHH;
            this.nuocSX = nuocSX;
            this.nhaCungCap = nhaCungCap; 
            this.trangThai = trangThai;
            this.congDung = congDung;
    }

    public String getMaSP(){
        return maSP;
    }

    public void setMaSP(String maSP){
        //thêm ràng buộc (5 số)
        this.maSP = maSP;
    }

    public String getTenSanPham(){
        return tenSanPham;
    }

    public void setTenSanPham(String tenSanPham){
        this.tenSanPham = tenSanPham;
    }

    public String getThuongHieu(){
        return thuongHieu;
    }

    public void setThuongHieu(String thuongHieu){
        this.thuongHieu = thuongHieu;
    }

    public String getPhanLoai (){
        return phanLoai;
    }

    public void setPhanLoai (String phanLoai){
        this.phanLoai = phanLoai;
    }

    public double getGia(){
        return gia;
    }

    public void setGia(double gia){
        this.gia = gia;
    }

    public int getSoLuong (){
        return soLuong;
    }

    public void setSoLuong(int soLuong){
        this.soLuong = soLuong;
        
    }

    public LocalDate getNgaySX(){
        return ngaySX;
    }

    public void setNgaySX(LocalDate ngaySX){
        this.ngaySX = ngaySX;
    }

    public LocalDate getNgayHH(){
        return ngayHH;
    }

    public void setNgayHH(LocalDate ngayHH){
        this.ngayHH = ngayHH;
    }

    public String getNuocSX(){
        return nuocSX;
    }

    public void setNuocSX(String nuocSX){
        this.nuocSX = nuocSX;
    }

    public NhaCungCap getNhaCungCap(){
        return nhaCungCap;
    }

    public void setNhaCungCap(NhaCungCap nhaCungCap){
        this.nhaCungCap = nhaCungCap;
    }

    public boolean isTrangThai(){
        return trangThai;
    }

    public void setTrangThai(boolean trangThai){
        this.trangThai = trangThai;
    }

    public String getCongDung(){
        return congDung;
    }

    public void setCongDung(String congDung){
        this.congDung = congDung;
    }

    public boolean ktHetHan(LocalDate currentDate){
        return ngayHH.isBefore(currentDate);
    }

    public void giamGia(double phanTram){
        if(phanTram > 0 && phanTram <= 100){
            this.gia = this.gia * (1 - phanTram/100.0);
        }
    }

    public void soLuongTonKho(int soLuong){
        if(soLuong >= 0 ){
            this.soLuong = soLuong;
            System.out.println("So luong ton kho hien tai: " + this.soLuong);
        } else {
            System.out.println("So luong khong the am!");
        }
    }

    public void nhapHang(int slNhap){
        this.soLuong += slNhap;
    }

    public void xuatHang(int soLuong){
        if (soLuong > 0 && soLuong <= this.soLuong){
            this.soLuong -= soLuong;
        }else{
            System.out.println("Khong du hang de xuat!");
        }
    }

    public double tinhGiaCuoiCung(){
        return gia;
    }

    public double tinhTongTien(int soLuongMua){
        return tinhGiaCuoiCung() * soLuongMua;
    }

    public boolean ktCongDung(){
        return this.congDung != null && !this.congDung.isEmpty();
    }

    public void nhap(){
         while (true) {
            System.out.println("Nhap ma san pham (5 chu so): ");
            this.maSP = sc.nextLine().trim();
            if (this.maSP.matches("\\d{5}")) {
                setMaSP("SP" + this.maSP);
                break;
            } else {
                System.out.println("Ma san pham phai co dung 5 chu so, vui long nhap lai!");
            }
        }

        while (true) {
            System.out.println("Nhap ten san pham: ");
            String tenSanPhamInput = sc.nextLine().trim();
            if (tenSanPhamInput.matches("^[^0-9]+$")) { // không chứa số
                setTenSanPham(tenSanPhamInput);
                break;
            } else {
                System.out.println("Ten san pham khong duoc chua so, vui long nhap lai!");
            }
        }

        while (true){
            System.out.println("Nhap ten thuong hieu: ");
            String thuongHieuInput = sc.nextLine().trim();
            if(thuongHieuInput.matches("[^0-9]+$")){
                setThuongHieu(thuongHieuInput);
                break;
            }else {
                System.out.println("Ten thuong hieu khong duoc chua so, vui long nhap lai!");
            }
        }
        System.out.println("Nhap phan loai: ");
        setPhanLoai(sc.nextLine());
        while (true){
            System.out.println("Nhap gia niem yet: ");
            if(sc.hasNextDouble()){
                double gia = sc.nextDouble();
                if(gia > 1000){
                    setGia(gia);
                    sc.nextLine();
                    break;
                }else{
                    System.out.println("Gia phai > 1000");
                }
            }else{
                System.out.println("Gia phai la so");
                sc.next();
            }
        }

        while (true){
            System.out.println("Nhap so luong trong kho: ");
            if (sc.hasNextInt()){
                int soLuong = sc.nextInt();
                if( soLuong >=0){
                    setSoLuong(soLuong);
                    sc.nextLine();
                    break;
                }else{
                    System.out.println("So luong >= 0");
                }
            }else{
                System.out.println("So luong phai la so.");
                sc.next();
            }
        }
        
        LocalDate ngayNhap = LocalDate.now();
        while (true) {
            try {
                System.out.println("Nhap ngay san xuat (yyyy-mm-dd): ");
                LocalDate nsx = LocalDate.parse(sc.nextLine().trim());
                if(nsx.isAfter(ngayNhap)){
                    System.out.println("Loi: Ngay san xuat phai truoc hoac bang ngay nhap kho!");
                    continue;
                }
                setNgaySX(nsx);
                break;
            } catch (Exception e) {
                System.out.println("Ngay san xuat khong hop le, vui long nhap lai!");
            }
        }

        while (true) {
            try {
                System.out.println("Nhap ngay het han (yyyy-mm-dd): ");
                LocalDate nhh = LocalDate.parse(sc.nextLine().trim());
                if (nhh.isAfter(this.ngaySX)){
                setNgayHH(nhh);
                break;
                }else{
                    System.out.println("Loi: Ngay het han phai sau ngay san xuat (" + this.ngaySX + ")!");
                }
            } catch (Exception e) {
                System.out.println("Ngay het han khong hop le, vui long nhap lai!");
            }
        }

        System.out.println("Nhap nuoc san xuat: ");
        setNuocSX(sc.nextLine());

        NhaCungCap nhaCungCap = new NhaCungCap();
        nhaCungCap.nhap();
        setNhaCungCap(nhaCungCap);

        System.out.println("1. Dang luu hanh / 0. Da het hang ");
        int temp = sc.nextInt();
        sc.nextLine();
        this.trangThai = (temp == 1);

        while(true){
            System.out.println("Nhap cong dung: ");
            this.congDung = sc.nextLine().trim();
            if(ktCongDung()){
                break;
            }else{
                System.out.println("Cong dung khong duoc de trong, vui long nhap lai!");
            }
        }
    }
    @Override
    public String toString() {
        String sTrangThai = (this.trangThai)? "Dang luu hanh" : "Da het hang";
        return String.format("|%-10s |%-25s |%-25s |%-15s |%-15.2f |%-5d |%-15s |%-15s |%-15s |%-20s |%-15s |%-15s |", maSP, tenSanPham, thuongHieu, phanLoai, gia, 
        soLuong, ngaySX, ngayHH, nuocSX, nhaCungCap, sTrangThai, congDung);
    }
    public void xuat() { 
        System.out.println(toString());
    }
}
