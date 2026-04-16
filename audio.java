package com.mycompany.audio;
import javax.sound.sampled.*;
import java.io.*;
import java.util.Scanner;

public class audio {

    public static void main(String[] args) {
        Thread musicaThread = new Thread(() -> {
            try {
                File cancion = new File("Passionfruit.wav");
                AudioInputStream archivo = AudioSystem.getAudioInputStream(cancion);
                Clip clip = AudioSystem.getClip();
                clip.open(archivo);
                clip.start();
                Thread.sleep(clip.getMicrosecondLength() / 1000);
            } catch (Exception e) {
                System.out.println("Error al reproducir la música: " + e.getMessage());
            }
        });

        Thread entradaDatosThread = new Thread(() -> {
          Scanner scanner = new Scanner(System.in);

       
        System.out.print("Ingresa el primer número: ");
        double numero1 = scanner.nextDouble();

        System.out.print("Ingresa el segundo número: ");
        double numero2 = scanner.nextDouble();

        System.out.println("Selecciona la operación:");
        System.out.println("1. Suma");
        System.out.println("2. Resta");
        System.out.println("3. Multiplicación");
        System.out.println("4. División");
        int opcion = scanner.nextInt();

        double resultado = 0;

        switch (opcion) {
            case 1:
                resultado = numero1 + numero2;
                break;
            case 2:
                resultado = numero1 - numero2;
                break;
            case 3:
                resultado = numero1 * numero2;
                break;
            case 4:
                if (numero2 != 0) {
                    resultado = numero1 / numero2;
                } else {
                    System.out.println("No es posible.");
                    return;
                }
                break;
            default:
                System.out.println("Opción no válida.");
                return;
        }

        System.out.println("El resultado es: " + resultado);
        });

        musicaThread.start();
        entradaDatosThread.start();

        try {
            musicaThread.join();
            entradaDatosThread.join();
        } catch (InterruptedException e) {
            System.out.println("Error al esperar a que los hilos terminen: " + e.getMessage());
        }
    }
}