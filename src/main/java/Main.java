import java.util.Scanner;
import java.util.concurrent.Semaphore;

class Parkinglot {
    private Semaphore semaphore;

    public Parkinglot(int n){
        semaphore = new Semaphore(n);
    }
    public void park(){
        try {
            semaphore.acquire();
            System.out.println("Машина припаркована");
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }
    public void leave(){
        semaphore.release();
        System.out.println("Машина покунила парковку ");
    }
}
class Car extends Thread{
    private Parkinglot parkinglot;

    public Car(Parkinglot parkinglot) {
        this.parkinglot = parkinglot;
    }

    @Override
    public void run() {
        parkinglot.park();
        parkinglot.leave();
    }
}
   public class Main {
        public static void main(String[] args) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Введите Общее количество мест на парковке");
            int n = scanner.nextInt();
            System.out.println("Введите Количество машин, которые не должны занимать место");
            int k = scanner.nextInt();



            Parkinglot parkinglot = new Parkinglot(n);
            for (int i = 0; i < n+k; i++) {
                new Car(parkinglot).start();
            }
        }
    }
