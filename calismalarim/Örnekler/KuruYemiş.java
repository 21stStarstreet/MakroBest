import java.util.Scanner;
void main() {
 double L= 3.5,F=15.7,B=22 ;
 double Maliyet=(12*L)+(25*F)+(40*B);
 double Satis=(12*(1.5*L))+(25*(1.4*F))+(40*(1.6*B));
 double kazanc=Satis-Maliyet;
 int kira=500;

 if(kazanc>kira){
  System.out.print("kirayı ödeyebilirsin");

 }
else {
 System.out.print("Kirayı ödeyemezsin");
 }
}