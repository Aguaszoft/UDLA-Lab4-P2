import javax.swing.*;
import java.awt.*;

public class PieSlice extends JPanel {
    private int radio;
    private int anguloInicio;
    private int angulo;
    private Color color;

    public PieSlice(int radio, int anguloInicio, int angulo, Color color) {
        this.radio = radio;
        this.anguloInicio = anguloInicio;
        this.angulo = angulo;
        this.color = color;
        setPreferredSize(new Dimension(2 * radio, 2 * radio));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(color);
        g.fillArc(0, 0, 2 * radio, 2 * radio, anguloInicio, angulo);
    }
}