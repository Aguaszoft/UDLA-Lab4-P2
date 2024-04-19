public class ElementoPrioridad implements Comparable<ElementoPrioridad>{
    private String elemento;
    private int prioridad;

    public ElementoPrioridad(String elemento, int prioridad) {
        this.elemento = elemento;
        this.prioridad = prioridad;
    }

    public String getElemento() {
        return elemento;
    }

    public int getPrioridad() {
        return prioridad;
    }

    @Override
    public int compareTo(ElementoPrioridad otro) {
        return Integer.compare(this.prioridad, otro.prioridad);
    }
}
