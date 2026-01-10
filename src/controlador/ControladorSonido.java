package controlador;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;

public class ControladorSonido {

    public static float volumen = 0.5f;
    public static Map<String, Clip[]> sonidosGuardados = new HashMap<>();

    public static void cargarSonido(String nombre, String rutaArchivo, int cantidadCopias) {

        try {
            if (sonidosGuardados.containsKey(nombre)) return; // Ya estaba cargado

            File archivo = new File(rutaArchivo);
            if (!archivo.exists()) {
                System.err.println("No se encontró: " + rutaArchivo);
                return;
            }

            Clip[] clips = new Clip[cantidadCopias];

            for (int i = 0; i < cantidadCopias; i++) {
                AudioInputStream audioInput = AudioSystem.getAudioInputStream(archivo);
                Clip clip = AudioSystem.getClip();
                clip.open(audioInput);
                clips[i] = clip;
            }

            sonidosGuardados.put(nombre, clips);
            System.out.println("Sonido cargado: " + nombre + " (" + cantidadCopias + " copias)");

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void reproducir(String nombre) {

        Clip[] clips = sonidosGuardados.get(nombre);

        if (clips != null) {
            // Buscamos el primer clip que no este sonando
            for (Clip clip : clips) {
                if (!clip.isRunning()) {
                    try {
                        clip.setFramePosition(0);
                        ajustarVolumen(clip);
                        clip.start();
                        return;
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
            
        } else {
            System.err.println("Error: Sonido '" + nombre + "' no cargado.");
        }

    }


    public static void setVolumen(int volumenInt) {
        float volumenFloat = ((float) volumenInt);
        volumenFloat = volumenFloat / 100.0f;

        if (volumenFloat < 0f) volumenFloat = 0f;
        if (volumenFloat > 1f) volumenFloat = 1f;

        ControladorSonido.volumen = volumenFloat;

    }


    private static void ajustarVolumen(Clip clip) {
        try {
            FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);

            // Pasamos de 0f - 1f a decibeles
            
            float dB;
            if (volumen <= 0.0f) {
                dB = -80.0f; // Silencio
            } else {
                dB = (float) (Math.log10(volumen) * 60.0);
            }

            gainControl.setValue(dB);

        } catch (Exception e) {
            System.out.println("No se pudo cambiar el volumen de este sonido.");
        }
    }

}
