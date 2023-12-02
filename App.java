public class App {

	public static void main(String[] args) {
		// Problema 1
		System.out.println("Problema 1:\n");

    	int l[] = { 10, 1, 10, 11, 5, 6 };
    	int h[] = { 5, 50, 5, 1, 12, 70 };

		Problema1 p1 = new Problema1();
    	p1.solveP1(l, h);

		System.out.println("\n\n");

		// Problema 2
		System.out.println("Problema 2:\n");

		int quantidadeItens = 5;
		int[] pesos = new int[] { 1, 2, 5, 6, 7 };
		int[] valores = new int[] { 1, 6, 18, 22, 28 };
		int pesoMaximo = 11;

		Problema2.solveP2(quantidadeItens, pesos, valores, pesoMaximo);
	}
}
