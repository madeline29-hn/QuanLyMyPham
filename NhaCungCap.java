package QuanLyMyPham;
import java.util.Scanner;
public class NhaCungCap {
    private String maNCC;
    private String tenNCC;
    private String diaChi;
    private String sdt;
    private String email; 
    static Scanner sc = new Scanner(System.in);

    public NhaCungCap(){

    }

    public NhaCungCap(String maNCC, String tenNCC, String diaChi, String sdt, String email){
        this.maNCC = maNCC;
        this.tenNCC = tenNCC;
        this.diaChi = diaChi;
        this.sdt = sdt;
        this.email = email;
    }

    public String getMaNCC(){
        return maNCC;
    }

    public void setMaNCC(String maNCC){
        this.maNCC = maNCC;
    }

    public String getTenNCC(){
        return tenNCC;
    }

    public void setTenNCC(String tenNCC){
        this.tenNCC = tenNCC;
    }
    
    public String getDiaChi(){
        return diaChi;
    }

    public void setDiaChi(String diaChi){
        this.diaChi = diaChi;
    }

    public String getSdt(){
        return sdt;
    }

    public void setSdt(String sdt){
        this.sdt = sdt;
    }

    public String getEmail(){
        return email;
    }

    public void setEmail(String email){
        this.email = email;
    }

    public void cungCapHang(){
        System.out.println("Nha cung cap: " + this.tenNCC);
        System.out.println("Trang thai: Dang chuan bi hang va xe van tai.");
        System.out.println("Thong bao: Hang se duoc giao den kho trong 24h toi.");
    }

    public void kiemTraDonHang(String maDH){
        System.out.println("Dang kiem tra don hang co ma: "+ maDH);
    }

    public void thanhToan(){
        System.out.println("Doi tuong thu huong: " + this.tenNCC);
        System.out.println("Trang thai: Giao dich thanh cong.");
    }

    public void nhap(){
        while(true){
            System.out.println("Nhap ma nha cung cap (5 chu so): ");
            this.maNCC = sc.nextLine().trim();
            if(this.maNCC.matches("\\d{5}")){
                setMaNCC("NCC" +this.maNCC );
                break;
            }else{
                System.out.println("Ma nha cung cap chi co dung 5 chu so, vui long nhap lai!");
            }
        }

        while(true){
            System.out.println("Nhap ten nha cung cap: ");
            this.tenNCC = sc.nextLine().trim();
            if(this.tenNCC.matches("^[^0-9]+$")){
                setTenNCC(this.tenNCC);
                break;
            }else{
                System.out.println("Ten nha cung cap khong chua so, vui long nhap lai!");
            }
        }

        System.out.println("Nhap dia chi: ");
        setDiaChi(sc.nextLine());

        while (true){
            System.out.println("Nhap so dien thoai: ");
            this.sdt = sc.nextLine().trim();
            if(this.sdt.matches("\\d{10}")){
                setSdt(this.sdt);
                break;
            }else{
                System.out.println("So dien thoai chi co dung 10 chu so, vui long nhap lai!");
            }
        }

        while(true){
            System.out.println("Nhap email: ");
            this.email = sc.nextLine().trim();
            if(this.email.matches("^[A-Za-z0-9+_.-]+$")){
                setEmail(this.email + "@gmail.com");
                break;
            }else{
                System.out.println("Email khong hop le, vui long nhap lai!");
            }
        }
    }

    @Override
    public String toString(){
        return String.format("|%-7s |%-15s |%-30s |%-12s |%-30s |", maNCC, tenNCC, diaChi, sdt, email);
    }
    public void xuat(){
        System.out.println(toString());
    }
}
