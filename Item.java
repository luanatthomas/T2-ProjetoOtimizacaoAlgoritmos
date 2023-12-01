public class Item {
	public int valor;
	public int peso;
	public double razao;

    public Item(int valor, int peso) {
        this.valor = valor;
        this.peso = peso;
        this.razao = (double) valor/peso;
    }

	@Override
    public String toString() {
        return "Item [peso= " + peso + ", valor= " + valor + ", razao= " + razao + "]";
    }
}
