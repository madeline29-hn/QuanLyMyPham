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
        if (soLuong >= 0){
            this.soLuong = soLuong;
        }
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

    public boolean getTrangThai(){
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

    public String getLoai(){
        return "SanPham";
    }


    public boolean ktHetHan(LocalDate currentDate){
        if(this.ngayHH == null)
            return false;
        return ngayHH.isBefore(currentDate);
    }

    public void giamGia(double phanTram){
        if(phanTram > 0 && phanTram <= 100){
            this.gia = this.gia * (1 - phanTram/100.0);
        }
    }
    public void nhapHang(int sl){
        if(sl > 0){
            this.soLuong += sl;
        }
    }

    public boolean xuatHang(int sl){
        if (sl > 0 && sl <= soLuong){
            this.soLuong -= sl;
            return true;
        }
        return false;
    }

    public double giaNhapHang(){
        return gia;
    }

    public double giaNiemYet(){
        return gia * 1.3;
    }

    public boolean ktCongDung(){
        return this.congDung != null && !this.congDung.isEmpty();
    }

    public void hienThiKiemTraCongDung(){
        if(this.ktCongDung()){
            System.out.println("Cong dung hop le!");
        } else {
            System.out.println("Cong dung khong hop le!");
        }
    }

    public void hienThiKiemTraHanDung(){
        String tinhTrang = this.ktHetHan(LocalDate.now()) ? "Da het han su dung" : "Con han su dung";
        System.out.println("Kiem tra han dung: " + tinhTrang);
    }

    public void hienThiBangGia(){
        System.out.println("======== BANG GIA ========");
        System.out.printf("Gia nhap vao kho: %,.0f VND\n", this.giaNhapHang());
        System.out.printf("Gia ban niem yet: %,.0f VND\n", this.giaNiemYet());
    }

    public void nhap(){
        while (true){
            System.out.println("===== CHON SAN PHAM =====");
            System.out.println("1. Son ");
            System.out.println("2. Phan phu");
            System.out.println("3. Kem duong");
            System.out.println("4. Dung cu");
            System.out.print("Nhap lua chon (1-4): ");
            int chon = Integer.parseInt(sc.nextLine());
            boolean hopLe = true;
            switch (chon) {
                case 1:
                    this.phanLoai = "Son";
                    break;
                case 2:
                    this.phanLoai = "Phan phu";
                    break;
                case 3:
                    this.phanLoai = "Kem duong";
                    break;
                case 4:
                    this.phanLoai = "Dung cu";
                    break;
                default:
                    System.out.println("Lua chon khong hop le!");
                    hopLe = false;
            }
            if(hopLe){
                break;
            }
        }

        while (true) {
            System.out.print("Nhap ten san pham: ");
            String tenSanPhamInput = sc.nextLine().trim();
            if (tenSanPhamInput.matches("^[^0-9]+$")) {
                setTenSanPham(tenSanPhamInput);
                break;
            } else {
                System.out.println("Ten san pham khong duoc chua so, vui long nhap lai!");
            }
        }

        while (true){
            System.out.print("Nhap ten thuong hieu: ");
            String thuongHieuInput = sc.nextLine().trim();
            if(thuongHieuInput.matches("[^0-9]+$")){
                setThuongHieu(thuongHieuInput);
                break;
            }else {
                System.out.println("Ten thuong hieu khong hop le, vui long nhap lai!");
            }
        }


        while (true){
            System.out.print("Nhap gia nhap hang: ");
            if(sc.hasNextDouble()){
                this.gia = Double.parseDouble(sc.nextLine());
                if(gia > 1000){
                    setGia(gia);
                    break;
                }else{
                    System.out.println("Gia phai > 1000");
                }
            }else{
                System.out.println("Gia phai la so");
            }
        }

        LocalDate ngayNhap = LocalDate.now();
        while (true) {
            try {
                System.out.print("Nhap ngay san xuat (yyyy-mm-dd): ");
                LocalDate nsx = LocalDate.parse(sc.nextLine().trim());
                if(nsx.isAfter(ngayNhap)){
                    System.out.println("Loi: Ngay san xuat phai truoc hoac bang ngay nhap kho!");
                    continue;
                }

                if(nsx.getYear() < 2023){
                    System.out.println("Loi: Nam san xuat phai tu 2023 tro ve sau!");
                    continue;
                }

                setNgaySX(nsx);
                break;
            } catch (Exception e) {
                System.out.println("Ngay san xuat khong hop le, vui long nhap lai!");
            }
        }

        LocalDate nhh = ngaySX.plusYears(3);
        setNgayHH(nhh);

        String nuocsxInput;
        while (true){
            System.out.print("Nhap nuoc san xuat: ");
            nuocsxInput = sc.nextLine().trim();
            if(!nuocsxInput.isEmpty() && nuocsxInput.matches("^[^0-9]+$")){
                setNuocSX(nuocsxInput);
                break;
            } else {
                System.out.println("Nuoc san xuat khong de trong, vui long nhap lai!");
            }
        }

        NhaCungCap nhaCungCap = new NhaCungCap();
        nhaCungCap.nhap();
        setNhaCungCap(nhaCungCap);

        System.out.print("1. Dang luu hanh / 0. Khong con luu hanh: ");
        int temp = sc.nextInt();
        sc.nextLine();
        this.trangThai = (temp == 1);

        while(true){
            System.out.print("Nhap cong dung: ");
            this.congDung = sc.nextLine().trim();
            if(ktCongDung()){
                break;
            }else{
                System.out.println("Cong dung khong duoc de trong, vui long nhap lai!");
            }
        }
    }

    public String outputFile() {
        String maNCC = (nhaCungCap != null) ? nhaCungCap.getMaNCC() : "null";
        String tenNCC = (nhaCungCap != null) ? nhaCungCap.getTenNCC() : "null";
        String diaChiNCC = (nhaCungCap != null) ? nhaCungCap.getDiaChi() : "null";
        String sdtNCC = (nhaCungCap != null) ? nhaCungCap.getSdt() : "null";
        String emailNCC = (nhaCungCap != null) ? nhaCungCap.getEmail() : "null";
        return String.format("%s;%s;%s;%s;%.1f;%s;%s;%s;%s;%s;%s;%s;%s;%b;%s;%d",
                maSP, phanLoai, tenSanPham, thuongHieu, gia,
                maNCC, tenNCC, diaChiNCC, sdtNCC, emailNCC,
                ngaySX, ngayHH, nuocSX, trangThai, congDung, soLuong);
    }

    @Override
    public String toString() {
        String sTrangThai = (this.trangThai)? "Dang luu hanh" : "Da het hang";
        String maNCC = (nhaCungCap != null) ? nhaCungCap.getMaNCC(): "";
        String tenNCC = (nhaCungCap != null) ? nhaCungCap.getTenNCC(): "";
        String diaChi = (nhaCungCap != null) ? nhaCungCap.getDiaChi(): "";
        String sdt = (nhaCungCap != null) ? nhaCungCap.getSdt(): "";
        String email = (nhaCungCap != null) ? nhaCungCap.getEmail(): "";
        String dong1 = String.format("| %-8s | %-15s | %-12s | %-12.1f | %-4d | %-11s | %-11s | %-9s | %-10s | %-12s | %-11s | %-16s |\n",
                maSP, tenSanPham, thuongHieu, giaNiemYet(), soLuong, ngaySX, ngayHH, maNCC, tenNCC, diaChi, sdt, email);
        String noiDungDong2 = String.format(" => Trang thai: %-15s | Cong dung: %-32s |", sTrangThai, congDung);
        String dong2 = String.format("| %-166s |\n", noiDungDong2);
        String duongKe = "-".repeat(170) + "\n";
        return dong1 + dong2 + duongKe;
    }

    public void xuat() {
        System.out.println(toString());
    }
}