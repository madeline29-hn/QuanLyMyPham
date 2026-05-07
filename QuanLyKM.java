package QuanLyMyPham;
import java.io.*;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Scanner;

public class QuanLyKM implements iQuanLyDanhSach, iDocGhiFile{
    private KhuyenMai[] dsKhuyenMai = new KhuyenMai[3];
    private int soLuongKM;
    static Scanner sc = new Scanner(System.in);

    public static final int KM_SAN_PHAM = 1;
    public static final int KM_HOA_DON = 2;

    public QuanLyKM(){
    }

    public QuanLyKM(KhuyenMai[]dsKhuyenMai){
        this.dsKhuyenMai = dsKhuyenMai;
    }

    public KhuyenMai[] getDsKhuyenMai(){
        return dsKhuyenMai;
    }

    public void setDsKhuyenMai(KhuyenMai[]dsKhuyenMai){
        this.dsKhuyenMai = dsKhuyenMai;
    }

    //tduyen them
    
    public KhuyenMai timKM(String ma) {
        for (int i = 0; i < soLuongKM; i++) {
            if (dsKhuyenMai[i] != null &&
                dsKhuyenMai[i].getMaKM().equalsIgnoreCase(ma)) {
                return dsKhuyenMai[i];
            }
        }
        return null;
    }
    @Override
    public void nhapDanhSach(){
        System.out.println("Nhap so luong khuyen mai: ");
        int m = Integer.parseInt(sc.nextLine());
        soLuongKM = 0;
        dsKhuyenMai = Arrays.copyOf(dsKhuyenMai, dsKhuyenMai.length + 1);
        for(int i = 0; i < m; i++){
            dsKhuyenMai = Arrays.copyOf(dsKhuyenMai, dsKhuyenMai.length + 1);
            System.out.println("---------- Khuyen mai thu " + (i + 1) + " ----------");
            dsKhuyenMai[soLuongKM] = new KhuyenMai();
            dsKhuyenMai[soLuongKM].nhap();
            soLuongKM++;
        }
        System.out.println("Nhap danh sach thanh cong!");
    }

    public boolean kTraTrungMa(String maMoi){
        for(int i = 0; i < soLuongKM; i++){
            if(dsKhuyenMai[i].getMaKM().equalsIgnoreCase(maMoi)){
                return true;
            }
        }
        return false;
    }

    @Override
    public void themVaoDanhSach(){
        System.out.println("-------- THEM KHUYEN MAI MOI --------");
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
            dsKhuyenMai = Arrays.copyOf(dsKhuyenMai, dsKhuyenMai.length + 1);
            System.out.println("---------- Khuyen mai thu " + (i + 1) + " ----------");
            String maMoi;
            while (true){
                System.out.print("Nhap ma san pham (5 chu so): ");
                maMoi = sc.nextLine().trim();
                if (maMoi.matches("\\d{5}")) {
                    maMoi = "KM" + maMoi;
                    if(kTraTrungMa(maMoi)){
                        System.out.println("Loi: Ma nay da ton tai!");
                    } else {
                        break;
                     }
                } else {
                    System.out.println("Ma san pham gom 5 chu so, vui long nhap lai!");
                }   
            }
            dsKhuyenMai = Arrays.copyOf(dsKhuyenMai, dsKhuyenMai.length + 1);
            dsKhuyenMai[soLuongKM] = new KhuyenMai();
            dsKhuyenMai[soLuongKM].nhap();
            soLuongKM++;
            System.out.println("Da them san pham moi thanh cong!");
        }
    }


    @Override
    public void suaDanhSach(String ma){
        for (int i = 0; i < soLuongKM; i++){
            if(dsKhuyenMai[i].getMaKM().equalsIgnoreCase(ma)){
                System.out.println("----------Dang sua khuyen mai: " + ma + " ----------");
                int chon;
                do{
                    System.out.println("-------- MENU SUA THONG TIN --------");
                    System.out.println("1. Sua ma khuyen mai");
                    System.out.println("2. Sua ten khuyen mai");
                    System.out.println("3. Sua loai ap dung");
                    System.out.println("4. Sua ngay bat dau, ket thuc");
                    System.out.println("5. Sua gia toi thieu");
                    System.out.println("6. Sua giam toi da");
                    System.out.println("0. Hoan tat sua");
                    System.out.println("Moi ban chon (0-6): ");
                    chon = Integer.parseInt(sc.nextLine());
                    switch(chon){
                        case 1:
                             while (true){
                                System.out.println("Nhap ma moi:  ");
                                String maMoi = sc.nextLine().trim();

                                if (maMoi.matches("\\d{5}")) {
                                    maMoi = "KM" + maMoi;
                                } else {
                                    System.out.println("Ma khuyen mai gom 5 chu so, vui long nhap lai!");
                                    continue;
                                }
                                if(kTraTrungMa(maMoi)){
                                    System.out.println("Loi: Ma khuyen mai nay da ton tai trong he thong!");
                                } else {
                                    dsKhuyenMai[i].setMaKM(maMoi);
                                    System.out.println("Cap nhat ma thanh cong!");
                                    break;
                                }
                            }
                            break;
                        case 2: 
                            System.out.print("Ten KM: ");
                            String tenKM = sc.nextLine().trim();
                            dsKhuyenMai[i].setTenKM(tenKM);
                            break;

                        case 3: 
                            while (true) {
                                try {
                                    System.out.print("Loai (1-SP,2-HD): ");
                                    int loai = Integer.parseInt(sc.nextLine());

                                    if (loai == KM_SAN_PHAM || loai == KM_HOA_DON) {
                                        dsKhuyenMai[i].setLoaiKM(loai);
                                        break;
                                    } else {
                                        System.out.println("Chi duoc nhap 1 hoac 2!");
                                    }
                                } catch (Exception e) {
                                    System.out.println("Nhap so hop le!");
                                }
                            }
                            break;

                        case 4:
                            while (true) {
                                try {
                                    System.out.print("Ngay BD (yyyy-mm-dd): ");
                                    LocalDate ngayBatDau = LocalDate.parse(sc.nextLine());

                                    System.out.print("Ngay KT (yyyy-mm-dd): ");
                                    LocalDate kt = LocalDate.parse(sc.nextLine());

                                    if (kt.isBefore(ngayBatDau)) {
                                        System.out.println("Loi: Ngay ket thuc phai sau ngay bat dau!");
                                    } else {
                                        LocalDate ngayKetThuc = kt;
                                        break;
                                    }
                                } catch (Exception e) {
                                    System.out.println("Sai dinh dang!");
                                }
                            }
                            break;

                            case 5:
                                System.out.print("Giam toi da: ");
                                dsKhuyenMai[i].setGiaTriToiThieu(Double.parseDouble(sc.nextLine()));
                                break;
                            case 6:
                                System.out.print("Giam toi da: ");
                                dsKhuyenMai[i].setGiamToiDa(Double.parseDouble(sc.nextLine()));
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
    public void xoaKhoiDanhSach(String ma){
        for(int i = 0; i < soLuongKM; i++){
            if(dsKhuyenMai[i].getMaKM().equalsIgnoreCase(ma)){
                for(int j = i; j < soLuongKM - 1; j++){
                    dsKhuyenMai[j] = dsKhuyenMai[j + 1];
                }
                dsKhuyenMai[soLuongKM - 1] = null;
                soLuongKM--;
                System.out.println("Da xoa!");
                return;
            }
        }
        System.out.println("Khong tim thay!");
    }

    @Override
    public void timKiemChinhXac(String ma){
        for(int i = 0; i < soLuongKM; i++){
            if(dsKhuyenMai[i].getMaKM().equalsIgnoreCase(ma)){
                dsKhuyenMai[i].xuat();
                return;
            }
        }
        System.out.println("Khong tim thay!");
    }

    @Override
    public void timKiemTuongDoi(String tuKhoa){
        boolean found = false;
        for(int i = 0; i < soLuongKM; i++){
            if(dsKhuyenMai[i].getTenKM().toLowerCase().contains(tuKhoa.toLowerCase())){
                dsKhuyenMai[i].xuat();
                found = true;
            }
        }
        if (!found) System.out.println("Khong tim thay!");
    }

    @Override
    public void thongKeTheoKhoa(){
        System.out.println("====== THONG KE KGUYEN MAI ======");
        LocalDate now = LocalDate.now();
        String tuKhoa = sc.nextLine().toLowerCase().trim();
        int totalFound = 0;
        int conHan = 0;
        for(int i = 0; i < soLuongKM; i++ ){
            if(dsKhuyenMai[i].getTenKM().toLowerCase().contains(tuKhoa.toLowerCase())){
                totalFound++;
                String tinhTrang = "";
                if(dsKhuyenMai[i].conHieuLuc(now)){
                    tinhTrang = "[Con han]";
                    conHan++;
                } else {
                    tinhTrang = "[Het han]";
                }
                System.out.println(tinhTrang + " " + dsKhuyenMai[i].getMaKM() + " - " + dsKhuyenMai[i].getTenKM());
            }
        }
        System.out.println("-------------------------------------");
        System.out.println("Tong so khuyen mai tim thay: " + totalFound);
        System.out.println("Trong do: " + conHan + " dang co hieu luc, " + (totalFound - conHan) + " da het han.");
    }

    @Override
    public void write(){
        try{
            BufferedWriter bw = new BufferedWriter(new FileWriter("D:\\Java\\QuanLyMyPham\\dsKhuyenMai.txt"));
            for(int i = 0; i < soLuongKM; i++){
                KhuyenMai km = dsKhuyenMai[i];
                String line = String.format(java.util.Locale.US,"%s;%s;%s;%s;%s;%.1f;%.1f;%.1f;%d", 
                    km.getMaKM(),
                    km.getTenKM(),
                    km.getMaSanPhamApDung(),
                    km.getNgayBatDau(),
                    km.getNgayKetThuc(),
                    km.getPhanTramGiam(),
                    ///km.getSoLuongMa(),
                    km.getGiamToiDa(),
                    km.getGiaTriToiThieu(),
                    km.getLoaiKM());
                    //km.getDieuKienApDung());
                bw.write(line);
                bw.newLine();
            }
            bw.close();
            System.out.println("Da luu danh sach " + soLuongKM + " san pham vao file thanh cong!");
        } catch (IOException e){
            System.out.println("Loi ghi file san pham: " + e.getMessage());
        }
    }

    @Override
    public void read(){
        try{
            BufferedReader br = new BufferedReader(new FileReader("D:\\Java\\QuanLyMyPham\\dsKhuyenMai.txt"));
            String line;
            soLuongKM = 0;
            while ((line = br.readLine()) != null){
                if(line.trim().isEmpty()) continue;
                String[] data = line.split(";");
                try{
                    KhuyenMai km = new KhuyenMai();
                    km.setMaKM (data[0].trim());
                    km.setTenKM(data[1].trim());
                    String maSP = data[2].trim();

                    if (maSP.equalsIgnoreCase("null")) {
                        km.setMaSanPhamApDung(null);
                    } else {
                        km.setMaSanPhamApDung(maSP);
                    }
                    km.setNgayBatDau(LocalDate.parse(data[3].trim()));
                    km.setNgayKetThuc(LocalDate.parse(data[4].trim()));
                    km.setPhanTramGiam(Double.parseDouble(data[5].trim().replace(",", ".")));
                    //km.setSoLuongMa(Integer.parseInt(data[6].trim()));
                    km.setGiamToiDa(Double.parseDouble(data[6].trim().replace(",", ".")));
                    km.setGiaTriToiThieu(Double.parseDouble(data[7].trim().replace(",", ".")));
                    km.setLoaiKM(Integer.parseInt(data[8].trim()));
                    //km.setDieuKienApDung(data[8].trim());
                    if(soLuongKM >= dsKhuyenMai.length){
                        dsKhuyenMai = Arrays.copyOf(dsKhuyenMai, dsKhuyenMai.length + 1);
                    }
                    dsKhuyenMai[soLuongKM++] = km;
                } catch (Exception e){
                    System.out.println("Loi du lieu tai dong: " + line);
                }
            }
            br.close();
             System.out.println("Da doc thanh cong " + soLuongKM + " khuyen mai!");
            }catch (IOException e){
                System.out.println("Loi doc file: " + e.getLocalizedMessage());
            }
    }

    @Override
    public void xuatDanhSach(){
        if(soLuongKM == 0){
            System.out.println("Danh sach rong!");
            return;
        }

        System.out.println("\n" + "-".repeat(110));
        System.out.printf("| %-9s | %-25s | %-5s | %-3s | %-23s | %-12s | %-5s |\n",
         "Ma KM", "Ten chuong trinh", "Giam", "Loai", "Thoi gian ap dung", "Giam toi da", "Trang thai");
        System.out.println("-".repeat(110));
        for(int i = 0; i < soLuongKM; i++){
            dsKhuyenMai[i].xuat();
        }
        System.out.println("-".repeat(110));
    }


    public void menu(){

        int chon;
        do{
            System.out.println("\n======== MENU ========");
            System.out.println("1. Them khuyen mai");
            System.out.println("2. Xuat danh sach khuyen mai");
            //System.out.println("3. them san pham");
            System.out.println("3. Xoa khoi danh sach");
            System.out.println("4. Sua danh sach");
            System.out.println("5. Tim kiem chinh xac");
            System.out.println("6. Tim kiem tuong doi");
            System.out.println("7. Thong ke");
            System.out.println("0. Thoat");
            System.out.print("Chon chuc nang (0-7): ");
            chon = sc.nextInt();
            sc.nextLine();

            switch(chon){
                case 1: 
                    themVaoDanhSach();
                    write();
                    break;
                case 2:
                    xuatDanhSach();
                    break;
                /*case 3: 
                    themVaoDanhSach();
                    write();
                    break;*/
                case 3:
                    System.out.println("Nhap ma khuyen mai can xoa: ");
                    String maXoa = sc.nextLine();
                    xoaKhoiDanhSach(maXoa);
                    write();
                    break;
                case 4:
                    System.out.println("Nhap ma khuyen mai can sua: ");
                    String maSua = sc.nextLine();
                    suaDanhSach(maSua);
                    write();
                    break;
                case 5: 
                    System.out.println("Nhap ma khuyen mai can tim: ");
                    String maTim = sc.nextLine();
                    timKiemChinhXac(maTim);
                    break;
                case 6:
                    System.out.println("Nhap ten khuyen mai can tim: ");
                    String tk = sc.nextLine();
                    timKiemTuongDoi(tk);
                    break;
                case 7: 
                    System.out.println("Nhap tu khoa khuyen mai can thong ke: ");
                    thongKeTheoKhoa();
                    break;
                case 0:
                    System.out.println("Thoat chuong trinh!");
                    break;
                default:
                    System.out.println("Lua chon khong hop le!");
            }   
        } while (chon != 0);
    }
}
