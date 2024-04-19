import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.PriorityQueue;


public class PriorityQueueGUI {
    private JTextArea colaTextArea;
    private JPanel panelBotones;
    private JButton btnAgregar;
    private JButton btnDesencolar;
    private JButton btnGuardar;
    private JButton btnRecuperar;
    private JPanel panelDiagrama;


    private PriorityQueue<ElementoPrioridad> cola = new PriorityQueue<>();

    public static void main(String[] args) {
        JFrame frame = new JFrame("PriorityQueueGUI");
        frame.setContentPane(new PriorityQueueGUI().panelBotones);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    public PriorityQueueGUI() {



        btnAgregar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String elemento = JOptionPane.showInputDialog("Ingrese un elemento:");
                if (elemento != null && !elemento.isEmpty()) {
                    int prioridad = Integer.parseInt(JOptionPane.showInputDialog("Ingrese la prioridad del elemento:"));
                    cola.offer(new ElementoPrioridad(elemento, prioridad));
                    actualizarTextArea();
                    dibujarDiagrama();
                }
            }
        });
        btnDesencolar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!cola.isEmpty()) {
                    cola.poll();
                    actualizarTextArea();
                    dibujarDiagrama();
                } else {
                    JOptionPane.showMessageDialog(null, "La cola está vacía");
                }
            }
        });
        btnGuardar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                guardarColaEnArchivo();
            }
        });
        btnRecuperar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                recuperarColaDesdeArchivo();

            }
        });

    }

    private void actualizarTextArea() {
        colaTextArea.setText("Cola de Prioridad:\n");
        for (ElementoPrioridad elemento : cola) {
            colaTextArea.append(elemento.getElemento() + " - Prioridad: " + elemento.getPrioridad() + "\n");
        }
    }

    private void guardarColaEnArchivo() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("colaprioridad.txt"))) {
            for (ElementoPrioridad elemento : cola) {
                writer.write(elemento.getElemento() + "," + elemento.getPrioridad());
                writer.newLine();
            }
            JOptionPane.showMessageDialog(null, "La cola de prioridad ha sido guardada en el archivo 'colaprioridad.txt'");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void recuperarColaDesdeArchivo() {
        cola.clear();
        try (BufferedReader reader = new BufferedReader(new FileReader("colaprioridad.txt"))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                String[] partes = linea.split(",");
                cola.offer(new ElementoPrioridad(partes[0], Integer.parseInt(partes[1])));
            }
            JOptionPane.showMessageDialog(null, "La cola de prioridad ha sido recuperada desde el archivo 'colaprioridad.txt'");
            actualizarTextArea();
        } catch (IOException e) {
            // Si el archivo no existe o hay un error de lectura, simplemente no hacemos nada
            // JOptionPane.showMessageDialog(null, "Error al leer el archivo 'colaprioridad.txt'");
        }
    }

    private void dibujarDiagrama() {

        panelDiagrama.removeAll();
        int totalPrioridades = 0;
        // Calcular el total de prioridades
        for (ElementoPrioridad elemento : cola) {
            totalPrioridades += elemento.getPrioridad();
        }
        // Variables para dibujar
        int inicioAngulo = 0;
        int radio = 100;
        // Dibujar cada elemento del diagrama de pastel
        for (ElementoPrioridad elemento : cola) {
            // Calcular el ángulo proporcional a la prioridad del elemento
            double angulo = (double) elemento.getPrioridad() / totalPrioridades * 360;
            // Dibujar el sector
            panelDiagrama.add(new PieSlice(radio, inicioAngulo, (int) angulo, Color.getHSBColor((float) Math.random(), 0.8f, 0.8f)));
            inicioAngulo += angulo;
        }
        panelDiagrama.revalidate();
        panelDiagrama.repaint();
    }

    
}
