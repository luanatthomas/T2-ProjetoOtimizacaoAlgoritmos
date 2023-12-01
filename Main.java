import java.util.*;

public class Main {

	public static void main(String[] args) {
		// Problema 2
		int quantidadeItens = 5;
		int[] pesos = new int[] { 1, 2, 5, 6, 7 };
		int[] valores = new int[] { 1, 6, 18, 22, 28 };
		int pesoMaximo = 11;

		solveP2(quantidadeItens, pesos, valores, pesoMaximo);
	}

	static void solveP2(int n, int wi[], int vi[], int W) {
		List<Item> itens = new LinkedList<Item>();

		for (int i = 0; i < n; i++) {
			Item item = new Item(vi[i], wi[i]);
			itens.add(item);
		}

		Collections.sort(itens, new Comparator<Item>() {
			public int compare(Item item1, Item item2) {
				return Double.compare(item2.razao, item1.razao);
			}
		});

		solve(itens, W);
	}

	static void solve(List<Item> itens, int W) {
		// Cria um nodo para armazenar o melhor valor encontrado até o momento e o nodo raiz
		Nodo melhor = new Nodo();
		Nodo raiz = new Nodo();
		raiz.calculaUB(W, itens);
		
		// Fila de prioridades para resolver o problema
		// Adiciona root na fila
		PriorityQueue<Nodo> q = new PriorityQueue<Nodo>();
		q.offer(raiz);

		// Enquanto ainda tiver nodos ativos/para serem explorados na fila:
		while (!q.isEmpty()) {
			Nodo nodo = q.poll();
			// System.out.println(melhor.valor);

			// Verifica se o ub do nodo é maior que o melhor valor encontrado até o momento e se 
			// o nodo não está no ultimo nível da árvore
			if (nodo.ub > melhor.valor && nodo.nivel < itens.size() - 1) {
				// Nodos da esquerda incluem o item
				// Nodos da direita excluem o item
				Nodo esquerda = new Nodo(nodo);
				Item item = itens.get(nodo.nivel);
				esquerda.peso += item.peso;

				if (esquerda.peso <= W) {
					esquerda.itensSelecionados.add(item);
					esquerda.valor += item.valor;
					esquerda.calculaUB(W, itens);
					System.out.println(esquerda.ub);

					if (esquerda.valor > melhor.valor) {
						melhor = esquerda;
					}
					if (esquerda.ub >= melhor.valor) {
						q.offer(esquerda);
					}
				}

				Nodo direita = new Nodo(nodo);
				direita.calculaUB(W, itens);

				if (direita.ub > melhor.valor) {
					q.offer(direita);
				}
			}
		}

		for (Item i : melhor.itensSelecionados) {
			System.out.println(i.toString());
		}
	}
}
