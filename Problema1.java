import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class Problema1 {
    private HashMap<Integer, ReturnDTO> M = new HashMap<>();

    // Classe interna para armazenar o valor e a sequência de tarefas
    public class ReturnDTO {
        public int val; 
        public ArrayList<Integer> dp;

        public ReturnDTO(int val, ArrayList<Integer> dp) {
            this.val = val;
            this.dp = dp;
        }
    }

    public void solveP1(int l[], int h[]) {
        ArrayList<Integer> dp = new ArrayList<>();

        // Chama o método opt para encontrar a solução ótima
        ReturnDTO maxValue = opt(l, h, 0);

        // Imprime o resultado em tela
        System.out.println("Plano de valor máximo: " + maxValue.val);
        System.out.println("Semanas: ");
        Collections.reverse(maxValue.dp);
        System.out.println(maxValue.dp);
    }

    public ReturnDTO opt(int l[], int h[], int i) {
        ArrayList<Integer> solution = new ArrayList<>();

        // Cria um objeto ReturnDTO com valor inicial 0 e uma lista vazia de tarefas
        ReturnDTO r = new ReturnDTO(0, solution);

        // Condições base para a recursão
        if (i == l.length - 2) {
            if (l[i] + l[i + 1] > h[i + 1]) {
                r.val = l[i] + l[i + 1];
                r.dp = addDp(l[i], addDp(l[i + 1], solution));

            } else {
                r.val = h[i + 1];
                r.dp = addDp(0, addDp(h[i + 1], solution));
            }

            M.put(i, r);
            return r;

        } else if (i >= l.length - 1) {
            r.val = l[i];
            r.dp = addDp(l[i], solution);
            M.put(i, r);
            return r;
        }

        // Verifica se o resultado já foi calculado e armazenado no HashMap M
        if (M.get(i) != null) {
            return M.get(i);
        }

        // Chamadas recursivas para encontrar o valor ótimo
        ReturnDTO r1 = opt(l, h, i + 1);
        ReturnDTO r2 = opt(l, h, i + 2);

        // Lógica para decidir qual caminho (baixa ou alta dificuldade) é mais vantajoso
        if ((r1.val + l[i]) > (r2.val + h[i + 1])) {
            r.val = r1.val + l[i];
            r.dp = addDp(l[i], r1.dp);
        } else {
            r.val = r2.val + h[i + 1];
            r.dp = addDp(0, addDp(h[i + 1], r2.dp));
        }
        // Armazena o resultado no HashMap M para reutilização futura
        M.put(i, r); 

        // Retorna o resultado calculado
        return r; 
    }

    // Método auxiliar para adicionar elementos na sequência de tarefas
    private ArrayList<Integer> addDp(int val, ArrayList<Integer> dp) {
        ArrayList<Integer> newDp = (ArrayList<Integer>) dp.clone();
        newDp.add(val);
        return newDp;
    }

}
