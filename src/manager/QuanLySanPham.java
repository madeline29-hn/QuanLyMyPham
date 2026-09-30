package manager;
import model.*;
import model.iDocGhiFile;     
import model.iQuanLyDanhSach;  
import java.util.Arrays;
import java.util.Scanner;
import java.io.*;
import java.time.LocalDate;

public class QuanLySanPham implements iQuanLyDanhSach, iDocGhiFile {
    private SanPham[] dsSanPham = new SanPham[100];
    private int soLuongSP;
    private static int tongSanPham = 0;
    Scanner sc = new Scanner(System.in);


    public QuanLySanPham(){
    }

    public QuanLySanPham(SanPham[]dsSanPham, int tongSanPham, int soLuongSP){
        this.dsSanPham = dsSanPham;
        this.soLuongSP = soLuongSP;
    }

    public SanPham[] getDsSanPham(){
        return dsSanPham;
    }

    public void setDsSanPham(SanPham[]dsSanPham){
        this.dsSanPham = dsSanPham;
    }

    public int getTongSanPham(){
        return tongSanPham;
    }

    public void setTongSanPham(int tongSanPham){
        QuanLySanPham.tongSanPham = tongSanPham;
    }

    public int getSoLuong(){
        return soLuongSP;
    }

    public void setSoLuong(int soLuongSP){
        this.soLuongSP = soLuongSP;
    }

    @Override
    public void nhapDanhSach(){
        int m = 0;
        while(true){
            try{
                System.out.println("Nhap so luong can nhap: ");
                m = sc.nextInt();
                sc.nextLine();
                break;
            } catch (Exception e){
                System.out.println("So luong nhap la mot con so!");
                sc.nextLine();
            }
        }
        for(int i = 0; i < m; i++){
            System.out.println("----------- San pham thu " + (i + 1) + " ----------");
            dsSanPham = Arrays.copyOf(dsSanPham, dsSanPham.length + 1);
            dsSanPham[soLuongSP] = new SanPham();
            dsSanPham[soLuongSP].nhap();
            soLuongSP++;
        }
    }

    public boolean kTraTrungMa(String maMoi){
        for(int i = 0; i < soLuongSP; i++){
            if(dsSanPham[i].getMaSP().equalsIgnoreCase(maMoi)){
                return true;
            }
        }
        return false;
    }

    @Override
    public void themVaoDanhSach(){
        System.out.println("-------- THEM SAN PHAM MOI --------");
        int m = 0;
        while(true){
            try{
                System.out.print("Nhap so luong can nhap: ");
                m = sc.nextInt();
                sc.nextLine();
                break;
            } catch (Exception e){
                System.out.println("So luong nhap la mot con so!");
                sc.nextLine();
            }
        }
        for(int i = 0; i < m; i++){
            System.out.println("----------- San pham thu " + (i + 1) + " ----------");
            String maMoi;
            while (true){
                System.out.print("Nhap ma san pham (5 chu so): ");
                maMoi = sc.nextLine().trim();
                if (maMoi.matches("\\d{5}")) {
                    maMoi = "SP" + maMoi;
                    if(kTraTrungMa(maMoi)){
                        System.out.println("Loi: Ma nay da ton tai!");
                    } else {
                        break;
                    }
                } else {
                    System.out.println("Ma san pham gom 5 chu so, vui long nhap lai!");
                }
            }
            dsSanPham = Arrays.copyOf(dsSanPham, dsSanPham.length + 1);
            dsSanPham[soLuongSP] = new SanPham();
            dsSanPham[soLuongSP].setMaSP(maMoi);
            dsSanPham[soLuongSP].nhap();
            soLuongSP++;
            QuanLySanPham.tongSanPham++;
            System.out.println("Da them san pham moi thanh cong!");
        }
    }

    @Override
    public void xoaKhoiDanhSach(String ma){
        for (int i = 0; i < soLuongSP; i++){
            if(dsSanPham[i].getMaSP().equalsIgnoreCase(ma)){
                for(int j = i; j < soLuongSP-1; j++){
                    dsSanPham[j] = dsSanPham[j + 1];
                }
                dsSanPham[soLuongSP - 1] = null;
                soLuongSP--;
                return;
            }
        }
    }

    @Override
    public void suaDanhSach(String ma){
        for (int i = 0; i < soLuongSP; i++){
            if(dsSanPham[i].getMaSP().equalsIgnoreCase(ma)){
                System.out.println("------ Dang sua san pham: " + ma + " ------");
                int chon;
                do{
                    System.out.println("-------- MENU SUA THONG TIN --------");
                    System.out.println("1. Sua ma san pham");
                    System.out.println("2. Sua ten san pham");
                    System.out.println("3. Sua thuong hieu");
                    System.out.println("4. Sua gia");
                    System.out.println("5. Sua ngay san xuat");
                    System.out.println("6. Sua trang thai");
                    System.out.println("0. Hoan tat sua");
                    System.out.println("Moi ban chon (0-6): ");
                    chon = Integer.parseInt(sc.nextLine());
                    switch(chon){
                        case 1:
                            while (true){
                                System.out.println("Nhap ma moi:  ");
                                String maMoi = sc.nextLine().trim();

                                if (maMoi.matches("\\d{5}")) {
                                    maMoi = "SP" + maMoi;
                                } else {
                                    System.out.println("Ma san pham gom 5 chu so, vui long nhap lai!");
                                    continue;
                                }
                                if(kTraTrungMa(maMoi)){
                                    System.out.println("Loi: Ma san pham nay da ton tai trong he thong!");
                                } else {
                                    dsSanPham[i].setMaSP(maMoi);
                                    System.out.println("Cap nhat ma thanh cong!");
                                    break;
                                }
                            }
                            break;
                        case 2:
                            while (true) {
                                System.out.print("Nhap ten san pham: ");
                                String tenSanPhamInput = sc.nextLine().trim();
                                if (tenSanPhamInput.matches("^[^0-9]+$")) { // không chứa số
                                    dsSanPham[i].setTenSanPham(tenSanPhamInput);
                                    break;
                                } else {
                                    System.out.println("Ten san pham khong duoc chua so, vui long nhap lai!");
                                }
                            }
                            break;

                        case 3:
                            while (true){
                                System.out.print("Nhap ten thuong hieu: ");
                                String thuongHieuInput = sc.nextLine().trim();
                                if(thuongHieuInput.matches("[^0-9]+$")){
                                    dsSanPham[i].setThuongHieu(thuongHieuInput);
                                    break;
                                }else {
                                    System.out.println("Ten thuong hieu khong hop le, vui long nhap lai!");
                                }
                            }
                            break;
                        case 4:
                            while (true){
                                System.out.print("Nhap gia nhap hang: ");
                                if(sc.hasNextDouble()){
                                    double gia = sc.nextDouble();
                                    if(gia > 1000){
                                        dsSanPham[i].setGia(gia);
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
                            break;
                        case 5:
                            System.out.println("Nhap ngay san xuat moi: ");
                            LocalDate ngayNhap = LocalDate.now();
                            while (true) {
                                try {
                                    System.out.println("Nhap ngay san xuat (yyyy-mm-dd): ");
                                    LocalDate nsx = LocalDate.parse(sc.nextLine().trim());
                                    if(nsx.isAfter(ngayNhap)){
                                        System.out.println("Loi: Ngay san xuat phai truoc hoac bang ngay nhap kho!");
                                        continue;
                                    }

                                    if(nsx.getYear() < 2023){
                                        System.out.println("Loi: Nam san xuat phai tu 2023 tro ve sau!");
                                        continue;
                                    }

                                    dsSanPham[i].setNgaySX(nsx);
                                    LocalDate nhh = nsx.plusYears(3);
                                    dsSanPham[i].setNgayHH(nhh);
                                    System.out.println("Da cap nhat ngay san xuat va han su dung!");
                                    break;
                                } catch (Exception e) {
                                    System.out.println("Ngay san xuat khong hop le, vui long nhap lai!");
                                }
                            }
                            break;
                        case 6:
                            System.out.print("1. Dang luu hanh / 0. Khong con luu hanh: ");
                            int temp = sc.nextInt();
                            sc.nextLine();
                            dsSanPham[i].setTrangThai(temp == 1);
                            break;
                        case 0:
                            System.out.println("Da luu thay doi!");
                            break;
                        default:
                            System.out.println("Lua chon khong hop le!");
                    }
                } while(chon != 0);
                System.out.println("Sua thanh cong!");
                return;
            }
        }
        System.out.println("Khong tim thay!");
    }

    @Override
    public void timKiemChinhXac(String ma){
        for (int i = 0; i < soLuongSP; i++){
            if(dsSanPham[i].getMaSP().equalsIgnoreCase(ma)){
                dsSanPham[i].xuat();
                return;
            }
        }
        System.out.println("Khong tim thay!");
    }

    @Override
    public void timKiemTuongDoi(String tuKhoa){
        boolean found = false;
        for(int i = 0; i < soLuongSP; i++){
            if(dsSanPham[i].getTenSanPham().toLowerCase().contains(tuKhoa.toLowerCase())){
                dsSanPham[i].xuat();
                found = true;
            }
        }
        if (!found){
            System.out.println("Khong tim thay!");
        }
    }

    @Override
    public void thongKeTheoKhoa(){
        String tuKhoa = sc.nextLine().toLowerCase().trim();
        int tong = 0;
        int dem = 0;

        for (int i = 0; i < soLuongSP; i++){
            if(dsSanPham[i].getTenSanPham().toLowerCase().contains(tuKhoa.toLowerCase())){
                tong += dsSanPham[i].getSoLuong();
                dem++;
            }
        }

        if(dem == 0){
            System.out.println("Khong tim thay san pham!");
        }

        System.out.println("So san pham phu hop: " + dem);
        System.out.println("Tong so luong: " + tong);
        this.kTraHetHan();
        this.kTraTonKho();
    }

    public void kTraHetHan(){
        boolean co = false;
        for (int i= 0; i < soLuongSP; i++){
            if(dsSanPham[i].ktHetHan(LocalDate.now())){
                System.out.println("Het han: " + dsSanPham[i].getTenSanPham());
                co = true;
            }
        }
        if(!co){
            System.out.println("Khong co san pham het han");
        }
    }

    public void kTraTonKho(){
        for (int i = 0; i < soLuongSP; i++){
            if(dsSanPham[i].getSoLuong() <= 0){
                System.out.println("Het hang: " + dsSanPham[i].getTenSanPham());
            }
        }
    }


    @Override
    public void read() {
        try {
            BufferedReader br = new BufferedReader(new FileReader("D:\\Java\\QuanLyMyPham\\dsSanPham.txt"));
            String line;
            int count = 0;

            while ((line = br.readLine()) != null) {
                if (line.trim().isEmpty()) continue;
                String[] data = line.split(";");
                if (data.length < 16) continue;
                try {
                    SanPham sp = new SanPham();
                    sp.setMaSP(data[0].trim());
                    sp.setPhanLoai(data[1].trim());
                    sp.setTenSanPham(data[2].trim());
                    sp.setThuongHieu(data[3].trim());
                    sp.setGia(Double.parseDouble(data[4].trim().replace(",", ".")));

                    NhaCungCap ncc = new NhaCungCap();
                    ncc.setMaNCC(data[5].trim());
                    ncc.setTenNCC(data[6].trim());
                    ncc.setDiaChi(data[7].trim());
                    ncc.setSdt(data[8].trim());
                    ncc.setEmail(data[9].trim());
                    sp.setNhaCungCap(ncc);
                    sp.setNgaySX(LocalDate.parse(data[10].trim()));
                    sp.setNgayHH(LocalDate.parse(data[11].trim()));
                    sp.setNuocSX(data[12].trim());
                    sp.setTrangThai(data[13].trim().equalsIgnoreCase("true"));
                    sp.setCongDung(data[14].trim());
                    sp.setSoLuong(Integer.parseInt(data[15].trim()));

                    dsSanPham[count++] = sp;
                } catch (Exception e) {
                    System.out.println("Lỗi dòng: " + line);
                }
            }
            br.close();
            this.soLuongSP = count;

            System.out.println("Da doc thanh cong " + this.soLuongSP + " san pham tu file!");
        } catch (IOException e) {
            System.out.println("Loi doc file: " + e.getMessage());
        }
    }

    @Override
    public void write() {
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter("D:\\Java\\QuanLyMyPham\\dsSanPham.txt", false));
            for (int i = 0; i < this.soLuongSP; i++) { // Sử dụng soLuongSP thay vì length
                if (dsSanPham[i] != null) {
                    SanPham sp = dsSanPham[i];
                    NhaCungCap ncc = sp.getNhaCungCap();
                    if(ncc == null) ncc = new NhaCungCap();
                    String line = String.format("%s;%s;%s;%s;%.1f;%s;%s;%s;%s;%s;%s;%s;%s;%b;%s;%d",
                            sp.getMaSP(),
                            sp.getPhanLoai(),
                            sp.getTenSanPham(),
                            sp.getThuongHieu(),
                            sp.getGia(),
                            ncc.getMaNCC(),
                            ncc.getTenNCC(),
                            ncc.getDiaChi(),
                            ncc.getSdt(),
                            ncc.getEmail(),
                            sp.getNgaySX(),
                            sp.getNgayHH(),
                            sp.getNuocSX(),
                            sp.getTrangThai(),
                            sp.getCongDung(),
                            sp.getSoLuong());
                    bw.write(dsSanPham[i].outputFile());
                    bw.newLine();
                }
            }
            bw.close();
            System.out.println("Da luu danh sach " + this.soLuongSP + " san pham vao file!");
        } catch (IOException e) {
            System.out.println("Loi ghi file: " + e.getMessage());
        }
    }

    public void menu(){

        int chon;
        do{
            System.out.println("\n======== MENU ========");
            System.out.println("1. Them san pham moi");
            System.out.println("2. Xuat danh sach san pham");
            System.out.println("3. Xoa khoi danh sach");
            System.out.println("4. Sua danh sach");
            System.out.println("5. Tim kiem chinh xac");
            System.out.println("6. Tim kiem tuong doi");
            System.out.println("7. Thong ke");
            System.out.println("8. Tong so luong kho");
            System.out.println("0. Thoat");
            System.out.print("Chon chuc nang (0-8): ");
            chon = sc.nextInt();
            sc.nextLine();

            switch (chon){
                case 1:
                    themVaoDanhSach();
                    write();
                    break;

                case 2:
                    xuatDanhSach();
                    break;
                case 3:
                    System.out.println("Nhap ma san pham can xoa: ");
                    String maXoa = sc.nextLine();
                    xoaKhoiDanhSach(maXoa);
                    write();
                    break;

                case 4:
                    System.out.println("Nhap ma san pham can sua: ");
                    String maSua = sc.nextLine();
                    suaDanhSach(maSua);
                    write();
                    break;

                case 5:
                    System.out.println("Nhap ma san pham can tim: ");
                    String maTim = sc.nextLine();
                    timKiemChinhXac(maTim);
                    break;

                case 6:
                    System.out.println("Nhap ten san pham can tim: ");
                    String tk = sc.nextLine();
                    timKiemTuongDoi(tk);
                    break;

                case 7:
                    System.out.println("Nhap ten san pham can thong ke: ");
                    thongKeTheoKhoa();
                    break;

                case 8:
                    System.out.println("Tong so luong kho: " + tongSoHangTrongKho());
                    break;

                case 0:
                    System.out.println("Thoat chuong trinh!");
                    break;

                default:
                    System.out.println("Lua chon khong hop le!");
            }
        } while (chon != 0);
    }

    @Override
    public void xuatDanhSach(){
        if(soLuongSP == 0){
            System.out.println("Danh sach rong!");
            return;
        }
        System.out.println("=".repeat(30) + " DANH SACH SAN PHAM " + "=".repeat(30));
        int count = 1;
        System.out.println("-".repeat(170));
        System.out.printf("| %-8s | %-15s | %-12s | %-12s | %-4s | %-11s | %-11s | %-9s | %-10s | %-12s | %-11s | %-18s | \n",
                "Ma SP", "Ten san pham", "Thuong Hieu", "Gia Niem Yet", "SL", "Ngay SX", "Ngay HH","Ma NCC" , "Ten NCC", "Dia chi", "Sdt", "Email");
        System.out.println("-".repeat(170));
        boolean coSP = false;
        for (int i = 0; i < soLuongSP; i++){
            System.out.print(dsSanPham[i].toString());
            coSP = true;
            count++;
        }
        if (!coSP){
            System.out.println("Khong co san pham nao con hang va con luu hanh!");
        }
    }

    public int tongSoHangTrongKho(){
        int tong = 0;
        for(int i = 0; i < soLuongSP; i++){
            tong += dsSanPham[i].getSoLuong();
        }
        return tong;
    }
}