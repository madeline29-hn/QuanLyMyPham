package QuanLyMyPham;

import java.time.LocalDate;
import java.util.Scanner;

public class phieu{
    private LocalDate ngayNhap;
    private NhanVien nv;
    private NhaCungCap nCC;
    private String maPhieuNhap;
    private String trangThai;
    
    // SỬA ĐỔI TẠI ĐÂY: Mảng tĩnh với kích thước tối đa
    private static final int MAX_SP = 100; 
    private SanPham[] dsNhapHang;
    private int soLuongThucTe; // Biến này thay thế cho việc check null liên tục

    static Scanner sc = new Scanner(System.in);
    public static final String CHUA_THANH_TOAN = "Chua thanh toan";
    public static final String DA_THANH_TOAN = "Da thanh toan";
    public static final String DA_HUY = "Da huy";

    public PhieuNhap() {
        this.dsNhapHang = new SanPham[MAX_SP];
        this.soLuongThucTe = 0;
        this.trangThai = CHUA_THANH_TOAN;
    }

    public PhieuNhap(String maPhieuNhap, NhanVien nv, NhaCungCap nCC) {
        this.maPhieuNhap = maPhieuNhap;
        this.ngayNhap = LocalDate.now();
        this.nv = nv;
        this.nCC = nCC;
        this.dsNhapHang = new SanPham[MAX_SP];
        this.soLuongThucTe = 0;
        this.trangThai = CHUA_THANH_TOAN;
    }

    // --- CÁC PHƯƠNG THỨC XỬ LÝ MẢNG TĨNH ---

    public void themSanPham(SanPham sp) {
        if (soLuongThucTe >= MAX_SP) {
            System.out.println("Danh sach nhap hang da day, khong the them!");
            return;
        }

        // Kiểm tra trùng mã để cộng dồn số lượng
        for (int i = 0; i < soLuongThucTe; i++) {
            if (dsNhapHang[i].getMaSP().equalsIgnoreCase(sp.getMaSP())) {
                dsNhapHang[i].setSoLuong(dsNhapHang[i].getSoLuong() + sp.getSoLuong());
                return;
            }
        }

        // Nếu không trùng, thêm mới vào vị trí kế tiếp
        dsNhapHang[soLuongThucTe] = sp;
        soLuongThucTe++;
    }

    public void xoaSanPham(String maSPXoa) {
        int index = -1;
        for (int i = 0; i < soLuongThucTe; i++) {
            if (dsNhapHang[i].getMaSP().equalsIgnoreCase(maSPXoa)) {
                index = i;
                break;
            }
        }

        if (index != -1) {
            // Ghi đè phần tử cuối lên vị trí cần xóa và gán cuối bằng null (Kỹ thuật mảng tĩnh)
            dsNhapHang[index] = dsNhapHang[soLuongThucTe - 1];
            dsNhapHang[soLuongThucTe - 1] = null;
            soLuongThucTe--;
            System.out.println("Da xoa san pham: " + maSPXoa);
        } else {
            System.out.println("Khong tim thay ma san pham de xoa!");
        }
    }

    public double tongTien() {
        double tong = 0;
        for (int i = 0; i < soLuongThucTe; i++) {
            tong += dsNhapHang[i].getSoLuong() * dsNhapHang[i].getGia();
        }
        return tong;
    }

    public int tongSoLuong() {
        int tong = 0;
        for (int i = 0; i < soLuongThucTe; i++) {
            tong += dsNhapHang[i].getSoLuong();
        }
        return tong;
    }

    public void nhap() {
        // ... (Giữ nguyên phần nhập mã PN, NV, NCC của bạn) ...
        
        // Sửa phần nhập danh sách sản phẩm
        System.out.println("BAT DAU NHAP DANH SACH SAN PHAM:");
        while (soLuongThucTe < MAX_SP) {
            SanPham sp = new SanPham();
            sp.nhap(); // Giả sử class SanPham có hàm nhap()
            themSanPham(sp);

            System.out.print("Tiep tuc nhap san pham? (Y/N): ");
            if (sc.nextLine().equalsIgnoreCase("N")) break;
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("\n========================= HOA DON NHAP HANG =========================\n");
        sb.append(String.format("Ma phieu: %-15s Ngay nhap: %s\n", maPhieuNhap, ngayNhap));
        sb.append(String.format("Nhan vien: %-15s NCC: %s\n", nv.getHoTen(), nCC.getTenNCC()));
        sb.append("---------------------------------------------------------------------\n");
        sb.append(String.format("%-10s %-20s %-10s %-10s\n", "Ma SP", "Ten SP", "SL", "Gia"));
        
        for (int i = 0; i < soLuongThucTe; i++) {
            sb.append(String.format("%-10s %-20s %-10d %-10.2f\n", 
                dsNhapHang[i].getMaSP(), 
                dsNhapHang[i].getTenSanPham(), 
                dsNhapHang[i].getSoLuong(), 
                dsNhapHang[i].getGia()));
        }
        
        sb.append("---------------------------------------------------------------------\n");
        sb.append("Tong tien: ").append(tongTien()).append("\n");
        sb.append("=====================================================================\n");
        return sb.toString();
    }

    // Các Getter/Setter giữ nguyên...
}

