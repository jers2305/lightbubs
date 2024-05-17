import java.util.Scanner;

public class Bombilla {
    private boolean encendida;
    
    public Bombilla() {
        this.encendida = false; // La bombilla se inicializa apagada
    }
    
    public void encender() {
        encendida = true;
        System.out.println("Bombilla encendida");
    }
    
    public void apagar() {
        encendida = false;
        System.out.println("Bombilla apagada");
    }
    
    public boolean estaEncendida() {
        return encendida;
    }
}

class ControladorBombillas {
    private Bombilla[] bombillas;
    private boolean interruptorPrincipal;
    private boolean[] estadoAnterior; // Estado de las bombillas antes de apagar el interruptor principal
    
    public ControladorBombillas(int cantidadBombillas) {
        bombillas = new Bombilla[cantidadBombillas];
        estadoAnterior = new boolean[cantidadBombillas];
        for (int i = 0; i < cantidadBombillas; i++) {
            bombillas[i] = new Bombilla();
            estadoAnterior[i] = false; // Inicialmente, ninguna bombilla está encendida
        }
        interruptorPrincipal = false; // Inicialmente el interruptor principal está apagado
    }
    
    public void encenderInterruptorPrincipal() {
        interruptorPrincipal = true;
        System.out.println("Interruptor principal encendido");
        // Encender solo las bombillas que estaban encendidas antes de apagar el interruptor
        for (int i = 0; i < bombillas.length; i++) {
            if (estadoAnterior[i]) {
                bombillas[i].encender();
            }
        }
    }
    
    public void apagarInterruptorPrincipal() {
        interruptorPrincipal = false;
        System.out.println("Interruptor principal apagado");
        // Guardar el estado actual de las bombillas antes de apagar el interruptor
        for (int i = 0; i < bombillas.length; i++) {
            estadoAnterior[i] = bombillas[i].estaEncendida();
        }
        // Apagar todas las bombillas
        for (Bombilla bombilla : bombillas) {
            bombilla.apagar();
        }
    }
    
    public void encenderBombilla(int indice) {
        if (indice >= 0 && indice < bombillas.length) {
            bombillas[indice].encender();
        } else {
            System.out.println("Índice de bombilla inválido");
        }
    }
    
    public void apagarBombilla(int indice) {
        if (indice >= 0 && indice < bombillas.length) {
            bombillas[indice].apagar();
        } else {
            System.out.println("Índice de bombilla inválido");
        }
    }
    
    public void mostrarEstadoBombillas() {
        System.out.println("--- Estado de las bombillas ---");
        for (int i = 0; i < bombillas.length; i++) {
            System.out.println("Bombilla " + i + ": " + (bombillas[i].estaEncendida() ? "encendida" : "apagada"));
        }
    }
}

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        // Crear un controlador de bombillas con 3 bombillas
        ControladorBombillas controlador = new ControladorBombillas(3);
        
        boolean continuar = true;
        while (continuar) {
            // Mostrar menú al usuario
            System.out.println("--- Menú ---");
            System.out.println("1. Encender bombilla");
            System.out.println("2. Apagar bombilla");
            System.out.println("3. Encender interruptor principal");
            System.out.println("4. Apagar interruptor principal");
            System.out.println("5. Mostrar estado de las bombillas");
            System.out.println("6. Finalizar");
            System.out.print("Seleccione una opción: ");
            
            int opcion = scanner.nextInt();
            scanner.nextLine(); // Limpiar el buffer
            
            switch (opcion) {
                case 1:
                    System.out.print("Ingrese el índice de la bombilla a encender: ");
                    int indiceEncender = scanner.nextInt();
                    controlador.encenderBombilla(indiceEncender);
                    break;
                case 2:
                    System.out.print("Ingrese el índice de la bombilla a apagar: ");
                    int indiceApagar = scanner.nextInt();
                    controlador.apagarBombilla(indiceApagar);
                    break;
                case 3:
                    controlador.encenderInterruptorPrincipal();
                    break;
                case 4:
                    controlador.apagarInterruptorPrincipal();
                    break;
                case 5:
                    controlador.mostrarEstadoBombillas();
                    break;
                case 6:
                    continuar = false;
                    break;
                default:
                    System.out.println("Opción no válida");
                    break;
            }
        }
        
        System.out.println("Programa finalizado.");
        scanner.close();
    }
}
