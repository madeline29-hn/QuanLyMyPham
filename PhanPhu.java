package QuanLyMyPham;
import java.util.Scanner;

public class PhanPhu extends SanPham {
    private String loaiDa;
    private float khoiLuong;
    private String mauSac;
    static Scanner sc = new Scanner(System.in);

    public PhanPhu(){
    }

    public PhanPhu(String loaiDa, float khoiLuong, String mauSac){
        this.loaiDa =loaiDa;
        this.khoiLuong =khoiLuong;
        this.mauSac = mauSac;
    }

    public String getLoaiDa (){
        return loaiDa;
    }

    public void setLoaiDa(String loaiDa){
        this.loaiDa = loaiDa;
    }

    public float getKhoiLuong (){
        return khoiLuong;
    }

    public void setKhoiLuong(float khoiLuong){
        this.khoiLuong = khoiLuong;
    }

    public String getMauSac(){
        return mauSac;
    }

    public void setMauSac(String mauSac){
        this.mauSac = mauSac;
    }

    public boolean ktLoaiDa(){
        return this.loaiDa != null && !this.loaiDa.isEmpty();
    }

    public boolean ktKhoiLuong(){
        return this.khoiLuong > 0;
    }

    public boolean ktMauSac(){
        return this.mauSac != null && !this.mauSac.isEmpty();
    }

    public String thongTinPhanPhu(){
        return "Phan phu [Loai da: "+ loaiDa + ",Khoi luong: "+ khoiLuong + ", Mau sac: "+ mauSac + "]";
    }

    public void nhap(){
        super.nhap();
        while(true){
            System.out.println("Nhap loai da cua ban: ");
            this.loaiDa = sc.nextLine().trim();
            if(ktLoaiDa()){
                break;
            }else{
                System.out.println("Loai da khong duoc de trong, vui long nhap lai!");
            }
        }

        while(true){
            System.out.println("Nhap khoi luong san pham (gram): ");
            this.khoiLuong = Float.parseFloat(sc.nextLine());
            if(ktKhoiLuong()){
                break;
            } else {
                System.out.println("Khoi luong phai lon hon 0, vui long nhap lai!");
            }
        }

        while(true){
            System.out.println("Nhap mau phan phu: ");
            this.mauSac = sc.nextLine().trim();
            if(ktMauSac()){
                break;
            } else {
                System.out.println("Mau sac khong duoc de trong, vui long nhap lai!");
            }
        }
    }

    @Override
    public String toString(){
        return super.toString()+ String.format("|%-12s |%-5.2f |%-7s |", loaiDa, khoiLuong, mauSac);
    }

    public void xuat(){
        System.out.println(toString());
    }
}
