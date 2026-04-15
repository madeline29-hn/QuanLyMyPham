package QuanLyMyPham;
import java.util.Scanner;

public class DungCu extends SanPham{
    private String chatLieu;
    private String phanLoai;
    Scanner sc = new Scanner(System.in);

    public DungCu(){

    }
    
    public DungCu(String chatLieu, String phanLoai){
        this.chatLieu = chatLieu;
        this.phanLoai = phanLoai;
    }
    
    public String getChatLieu(){
        return chatLieu;
    }

    public void setChatLieu(String chatLieu){
        this.chatLieu = chatLieu;
    }

    public String getPhanLoai(){
        return phanLoai;
    }

    public void setPhanLoai(String phanLoai){
        this.phanLoai = phanLoai;
    }

    public boolean ktChatLieu(){
        return this.chatLieu != null && ! this.chatLieu.isEmpty();
    }

    public boolean ktPhanLoai(){
        return this.phanLoai != null && ! this.phanLoai.isEmpty();
    }

    public String thongTinDungCu(){
        return "Dung cu [Chat lieu: " + chatLieu + ", Phan loai: " + phanLoai + "]";
    }

    public void nhap(){
        super.nhap();
        while(true){
            System.out.println("Nhap chat lieu ban can: ");
            this.chatLieu = sc.nextLine().trim();
            if(ktChatLieu()){
                break;
            } else {
                System.out.println("Chat lieu khong duoc de trong, vui long nhap lai!");
            }
        }

        while(true);
        
    }
}
