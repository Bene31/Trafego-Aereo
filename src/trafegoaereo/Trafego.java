package trafegoaereo;

public class Trafego extends Thread{

    public Trafego(){
        t();
    }

    Thread voo;

    public static Semaforo mutex = new Semaforo(1);
    public static Semaforo semaforos[] = new Semaforo[5];
    public static int estado[] = new int[5];
    public static Aviao aviao[] = new Aviao[5];

    public void t(){
        for (int i = 0; i < estado.length; i++) {
            estado[i] = 0;
        }if (voo == null){
            voo = new Thread(this);
            voo.start();
        }

        Thread.currentThread().setPriority(1);

        aviao[0] = new Aviao("AVIÃO 01) AZUL ", 0);
        aviao[1] = new Aviao("AVIÃO 02) GOL  ", 1);
        aviao[2] = new Aviao("AVIÃO 03) TAP ", 2);
        aviao[3] = new Aviao("AVIÃO 04) LATAM ", 3);
        aviao[4] = new Aviao("AVIÃO 05) AVIANCA  ", 4);

        semaforos[0] = new Semaforo(0);
        semaforos[1] = new Semaforo(0);
        semaforos[2] = new Semaforo(0);
        semaforos[3] = new Semaforo(0);
        semaforos[4] = new Semaforo(0);

        aviao[0].start();
        aviao[1].start();
        aviao[2].start();
        aviao[3].start();
        aviao[4].start();
    }

}
