import java.util.ArrayList;
import java.util.List;

// Classe responsável por criar os nodos da arvore de branchAndBound
// Cada nodo tem seu nível, os itens que foram selecionados até o momento, o valor de Upper Bound (ub), 
// valor e peso atual da mochila naquele momento
public class Nodo implements Comparable<Nodo> {
    public int nivel;
    List<Item> itensSelecionados;
    public double ub;
    public double valor;
    public double peso;

    // Inicializa um nodo sem pai, nodo raíz
    public Nodo() {
		itensSelecionados = new ArrayList<Item>();
        nivel = 0;
        ub = 0;
        valor = 0;
        peso = 0;
	}

    // Inicializa um nodo a partir dos dados do pai
    public Nodo(Nodo pai) {
		nivel = pai.nivel + 1;
		itensSelecionados = new ArrayList<Item>(pai.itensSelecionados);
		ub = pai.ub;
		valor = pai.valor;
		peso = pai.peso;
	}

    // Calcula o Upper Bound do nodo, de acordo com a formula aprendida em sala de aula (ub = v + (W - W) * (vi+1/wi+1))
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
