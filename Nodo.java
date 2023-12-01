import java.util.ArrayList;
import java.util.List;

public class Nodo implements Comparable<Nodo> {
    public int nivel;
    List<Item> itensSelecionados;
    public double ub;
    public double valor;
    public double peso;

    public Nodo() {
		itensSelecionados = new ArrayList<Item>();
        nivel = 0;
        ub = 0;
        valor = 0;
        peso = 0;
	}

    public Nodo(Nodo pai) {
		nivel = pai.nivel + 1;
		itensSelecionados = new ArrayList<Item>(pai.itensSelecionados);
		ub = pai.ub;
		valor = pai.valor;
		peso = pai.peso;
	}

    public void calculaUB(int W, List<Item> itens) {
        int i = nivel;
        double w = peso;
        ub = valor;
        Item item = itens.get(i);
        double pesoItem = (double) item.peso;
        double valorItem = (double) item.valor;
        ub += (W - w) * (valorItem / pesoItem);
    }

    @Override
    public int compareTo(Nodo o) {
        return (int) (o.ub - ub);
    }

}
