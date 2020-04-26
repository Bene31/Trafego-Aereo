package trafegoaereo;

public class Aviao extends Thread{
    private int id;
    final int chegada = 0;
    final int patio = 1;
    final int saida = 2;
    final int decolar = 3;
    final int aterrissando = 4;
    
    public Aviao(){}
    public Aviao(String nome, int id){
        super(nome);
        this.id = id;
    }
    
    public void Chegada(){
        Trafego.estado[this.id] = 0;
        System.out.println("O " + getName() + " CHEGOU AO PÁTIO");
        try{
            Thread.sleep(2000);
        }catch(InterruptedException e){
            System.out.println(e);
        }
    }
    
    public void Patio(){
        Trafego.estado[this.id] = 1;
        System.out.println("O " + getName() + " ESTÁ ESTACIONADO");
        try{
            Thread.sleep(2000);
        }catch(InterruptedException e){
            System.out.println(e);
        }
    }
    
    public void Saida(){
        Trafego.estado[this.id] = 2;
        System.out.println("O " + getName() + " ESTÁ SAINDO DO PÁTIO");
        try{
            Thread.sleep(2000);
        }catch(InterruptedException e){
            System.out.println(e);
        }
    }
   
    public void Decolar(){
       Trafego.estado[this.id] = 3;
       System.out.println("O " + getName() + " DECOLOU ");
       try{
           Thread.sleep(2000);
       }catch(InterruptedException e){
           System.out.println(e);
       }
   }
   
   public void Aterrissar(){
       Trafego.estado[this.id] = 4;
       System.out.println("O  " + getName() + " ESTÁ ATERRISANDO");
   }
   
    public void SemPermissao() {
        Trafego.mutex.decrementar();
        Patio();
        Trafego.mutex.incrementar();
    }
    
    
    public void Permissao() {
        Trafego.mutex.decrementar();
        Sair();
        Voar();
        Trafego.mutex.incrementar();
        Trafego.semaforos[this.id].decrementar();
        Pousar();
        Trafego.mutex.incrementar();
        Trafego.semaforos[this.id].decrementar();
    }
   
   public void Sair(){
       if(Trafego.estado[this.id] == 1 && Trafego.estado[this.id] != 2){
           Saida();
           Trafego.semaforos[this.id].incrementar();
       }
   }
   
   public void Voar(){
       if(Trafego.estado[this.id] == 2 && Trafego.estado[this.id] != 3){
           Decolar();
           Trafego.semaforos[this.id].incrementar();
       }
   }
   
   public void Pousar(){
       if(Trafego.estado[this.id] == 3){
           Aterrissar();
           Trafego.semaforos[this.id].incrementar();
       }
   }
    
   @Override
   @SuppressWarnings("SleepWhileInLoop")
   public void run(){
       try {
           Chegada();
           Patio();
           do {
                Permissao();
                Thread.sleep(2000);
                SemPermissao();
            }while (true);
        }catch (InterruptedException e) {
            System.out.println(e);
        }
   }
   
}
