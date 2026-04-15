package QuanLyMyPham;
import java.util.Scanner;
public class Son extends SanPham {
    private String mauSac;
    private String chatSon;
    private float khoiLuong;
    static Scanner sc = new Scanner(System.in);

    public Son(){

    }

    public Son(String mauSac, String chatSon, float khoiLuong){
        this.mauSac = mauSac;
        this.chatSon = chatSon;
        this.khoiLuong = khoiLuong;
    }

    public String getMauSac(){
        return mauSac;
    }

    public void setMauSac(String mauSac){
        this.mauSac = mauSac;
    }

    public String getChatSon(){
        return chatSon;
    }

    public void setChatSon(String chatSon){
        this.chatSon = chatSon;
    }

    public double getKhoiLuong(){
        return khoiLuong;
    }

    public void setKhoiLuong(float khoiLuong){
        this.khoiLuong = khoiLuong;
    }

    public boolean ktChatSon(){
        return this.chatSon != null && !this.chatSon.isEmpty();
    }

    public boolean ktKhoiLuong(){
        return this.khoiLuong > 0;
    }

    public boolean ktMauSac(){
        return this.mauSac != null && !this.mauSac.isEmpty();
    }

    public String thongTinSon(){
        return "Son [Mau sac: " + mauSac + ", chat son: " + chatSon +", khoi luong: " + khoiLuong + "gram]";
    }

    public void nhap(){
        super.nhap();
        while (true){
            System.out.println("Nhap mau son: ");
            this.mauSac = sc.nextLine().trim();
            if(ktMauSac()){
                break;
            }else{
                System.out.println("Mau son khong duoc de trong, vui long nhap lai!");
            }
        }

        while(true){
            System.out.println("Nhap chat son (vi du: li, bun, bong,...): ");
            this.chatSon = sc.nextLine().trim();
            if(ktChatSon()){
                break;
            }else{
                System.out.println("Chat son khong duoc de trong, vui long nhap lai!");
            }
        }

        while(true){
            System.out.println("Nhap khoi luong san pham (gram): ");
            this.khoiLuong = Float.parseFloat(sc.nextLine());
            if(ktKhoiLuong()){
                break;
            }else{
                System.out.println("Khoi luong phai lon hon 0, vui long nhap lai!");
            }
        }
    }

    @Override
    public String toString(){
        return super.toString() + String.format("|%-10s |%-7s |%-5.2f |", mauSac, chatSon, khoiLuong);
    }

    public void xuat(){
        System.out.println(toString());
    }
}
