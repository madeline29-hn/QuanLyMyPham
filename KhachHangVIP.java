package QuanLyMyPham;
import java.time.LocalDate;

public class KhachHangVIP extends KhachHang{
    private String quyenLoi;
    private String uuDai;

    public KhachHangVIP(){
        super();
        this.quyenLoi = "Chua xac dinh";
        this.uuDai = "Chua co";
    }
    public KhachHangVIP(String maKH, String lichSuMuaHang, String thuHang, double tongChiTieu, String quyenLoi, String uuDai){
        super(maKH, lichSuMuaHang, thuHang, tongChiTieu );
        this.quyenLoi = (quyenLoi != null) ? quyenLoi : "Chua xac dinh";
        this.uuDai = (uuDai != null) ? uuDai : "Chua co";
    }
    public String getQuyenLoi(){
        return quyenLoi;
    }
    public void setQuyenLoi(String quyenLoi){
        this.quyenLoi = quyenLoi;
    }
    public String getUuDai(){
        return uuDai;
    }
    public void setUuDai(String uuDai){
        this.uuDai = uuDai;
    }
    
    @Override 
    public String getVaiTro(){
        return "Khach Hang VIP";
    }
    @Override
    public void nhap(){
        super.nhap();
        capNhatQuyenLoi();
        capNhatUuDai();
        System.out.println("Da cap nhat quyen loi cho KH");
    }
    public void capNhatQuyenLoi() {
        String hangRaw = getThuHang();

        if (hangRaw == null) {
            this.quyenLoi = "Chua xac dinh";
            return;
        } 
        String hang = hangRaw.trim().toLowerCase().replace(" ", "");

        switch (hang) {
            case "dong":
            this.quyenLoi = "Khong co quyen loi dac biet";
            break;

            case "bac":
            this.quyenLoi = "Mua 5 tang 1";
            break;

            case "vang":
            this.quyenLoi = "Mua 7 tang 1";
            break;

            case "kimcuong":
            this.quyenLoi = "Mua 10 tang 3";
            break;

            default:
            this.quyenLoi = "Hang khong hop le";
        }
    }
    
    public void capNhatUuDai(){
        if (getTongChiTieu() >= 2000000) {
            this.uuDai = "Voucher 200k";
        } else if (getTongChiTieu() >= 1000000) {
            this.uuDai = "Voucher 100k";
        } else {
            this.uuDai = "Khong co uu dai";
        }
    }
    
    
    public void quaSN(LocalDate ngayMua){
        if(ngayMua == null || getNgaySinh() == null){
            System.out.println("Du lieu ngay thang chua ro rang!");
            return;
        }
        if (ngayMua.getDayOfMonth() == getNgaySinh().getDayOfMonth() &&
            ngayMua.getMonthValue() == getNgaySinh().getMonthValue()) {
            System.out.println(" Tang qua sinh nhat cho khach VIP!");
        } else {
            System.out.println("Khong phai ngay sinh -> khong tang qua!");
        }
    }

    public void hienThiQuyenLoi(){
        System.out.println("Quyen loi VIP: " + quyenLoi);
    }
    public void uuDaiDacBiet(){
        System.out.println("Uu dai dac biet: " + uuDai);
    }
    public void doiQua() {
        if ("Hang khong hop le".equals(quyenLoi)) {
            System.out.println("Khong the doi qua!");
        } else {
            System.out.println("Doi qua thanh cong!");
        }
    }
    @Override
    public String toString(){
        return super.toString() + String.format("| %-30s | %-20s |",quyenLoi, uuDai);
    }
    @Override
    public void xuat(){
        System.out.println(toString());
    }
}
