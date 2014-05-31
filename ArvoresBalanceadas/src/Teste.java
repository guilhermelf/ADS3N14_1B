import java.util.Random;
import java.util.Scanner;

public class Teste {
	public static void main(String[] args) {
		Scanner leitor = new Scanner(System.in);
		
		int[] elementos = new int[50];
		Random r = new Random();
		
		
		String op = "";
		System.out.println("Digite a ordem que deseja comparar a AVL com a Red-Black (ordenado ou randomico):");
		
		op = leitor.nextLine();
		switch (op) {
		case "ordenado":
			for (int i = 0; i < elementos.length; i++) {
				elementos[i] = i;
			}
			break;
		case "randomico":
			for (int i = 0; i < elementos.length; i++) {
				elementos[i] = r.nextInt(100);
			}
			break;
		default:
			System.out.println("Opcao inválida, programa finalizado");
			System.exit(0);
		}
	
		RBTree rb = new RBTree<>();
		AvlTree avl = new AvlTree<>();
		System.out.println("-----------------------------------------------------");
		System.out.println("INSERINDO NA AVL");
		for (int i : elementos) {
			avl.insert(i);
		}	

		System.out.println("-----------------------------------------------------");
		System.out.println("INSERINDO NA RED-BLACK");
		for (int i : elementos) {
			rb.insert(i);
		}
		
		System.out.println("\nStatus:");
		System.out.println("\nAVL Tree");
		System.out.println("Total de comparacoes: " + avl.totalComparacoes);
		System.out.println("Total de rotacoes: " + avl.totalRotacoes);
		System.out.println("\nRed-Black Tree");
		System.out.println("Total de comparacoes: " + rb.totalComparacoes);
		System.out.println("Total de rotacoes: " + rb.totalRotacoes);
	}
}
