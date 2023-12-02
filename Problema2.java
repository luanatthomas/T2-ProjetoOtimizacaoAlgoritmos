import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;

public class Problema2 {

    public static void solveP2(int n, int wi[], int vi[], int W) {
        // Transforma os arrays vi e wi em um unico array de Itens e ordena os itens pelo valor da razão
        // valor/peso, em ordem descrescente.
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

        // Chama o método que aplica Branch-and-bound
		branchAndBound(itens, W);
	}

    static void branchAndBound(List<Item> itens, int W) {
		// Cria um nodo para armazenar o melhor valor encontrado até o momento e o nodo raiz
		Nodo melhor = new Nodo();
		Nodo raiz = new Nodo();
		raiz.calculaUB(W, itens);
		
		// Fila de prioridades para resolver o problema, adiciona root na fila
		PriorityQueue<Nodo> q = new PriorityQueue<Nodo>();
		q.offer(raiz);

		// Enquanto ainda tiver nodos ativos/para serem explorados na fila:
		while (!q.isEmpty()) {
			Nodo nodo = q.poll();

			// Verifica se o ub do nodo sellecionado é maior que o melhor valor encontrado até o momento e se 
			// o nodo não está no ultimo nível da árvore
			if (nodo.ub > melhor.valor && nodo.nivel < itens.size() - 1) {
				// Nodos da esquerda incluem o item
				Nodo esquerda = new Nodo(nodo);
				Item item = itens.get(nodo.nivel);
				esquerda.peso += item.peso;

                // Se o peso do nodo for menor ou igual o peso maximo da mochila:
				if (esquerda.peso <= W) {
					esquerda.itensSelecionados.add(item);
					esquerda.valor += item.valor;
					esquerda.calculaUB(W, itens);

                    // Atualiza o nodo com melhor valor se necessário
					if (esquerda.valor > melhor.valor) {
						melhor = esquerda;
					}

                    // Caso o upper bound do novo nodo for maior que o melhor valor, entra na fila para ser análisado
					if (esquerda.ub >= melhor.valor) {
						q.offer(esquerda);
					}
				}

                // Nodos da direita excluem o item
				Nodo direita = new Nodo(nodo);
				direita.calculaUB(W, itens);
                
                // Como o valor do novo nodo é igual ao seu nodo pai, não preciso verificar se ele é maior que o melhor valor
                // Caso o upper bound do novo nodo for maior que o melhor valor, entra na fila para ser análisado
				if (direita.ub > melhor.valor) {
					q.offer(direita);
				}
			}
		}

        // Impressão da saída na tela
        System.out.println("Melhor valor da mochila: " + melhor.valor);
        System.out.println("Peso da mochila com este valor: " + melhor.peso);
        System.out.println("Itens selecionados: ");
		for (Item i : melhor.itensSelecionados) {
			System.out.println(i.toString());
		}
	}
}
