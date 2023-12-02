// Classe responsável por criar itens que serão armazenados no problema da mochila
// Possui valor, peso e a razao entre valor e peso, usado para ordenar os itens
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
