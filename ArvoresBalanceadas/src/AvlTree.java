public class AvlTree<T extends Comparable<T>> {
	static int comparacoes = 0;
	static int rotacoes = 0;
	static int totalComparacoes = 0;
	static int totalRotacoes = 0;

	private AvlNode<T> root = null;

	public AvlTree() {
		root = null;
	}

	public void clear() {
		root = null;
	}

	public boolean isEmpty() {
		return root == null;
	}

	public AvlNode<T> getRootNode() {
		return root;
	}

	/** Retorna a altura da árvore */
	private static int height(AvlNode node) {
		return node == null ? -1 : node.getHeight();
	}

	/**
	 * Retorna o maior valor ente lhs e rhs.
	 */
	private static int max(int lhs, int rhs) {
		return lhs > rhs ? lhs : rhs;
	}

	/** Retorna o fator de balanceamento da árvore passada */
	private int getFactor(AvlNode<T> node) {
		return height(node.left) - height(node.right);
	}

	public boolean insert(T data) {
		comparacoes = 0;
		rotacoes = 0;
		
		
		root = insert(data, root);
		
		imprimirRotacoesComparacoesAltura((Integer) data);
		totalComparacoes += comparacoes;
		totalRotacoes += rotacoes;
		return true;
	}

	private AvlNode insert(T data, AvlNode<T> node) {

		if (node == null)
			node = new AvlNode(data, null, null);
		else {
			this.comparacoes++;
			
			if (data.compareTo(node.getData()) < 0)
				node.setLeft(insert(data, node.getLeft()));

			else if (data.compareTo(node.getData()) > 0)
				node.setRight(insert(data, node.getRight()));
		}
		
		node = balance(node);

	
		
		return node;
	}

	public AvlNode balance(AvlNode node) {
		if (getFactor(node) == 2) {

			if (getFactor(node.getLeft()) > 0)
				node = doRightRotation(node);
			else
				node = doDoubleRightRotation(node);

		} else if (getFactor(node) == -2) {

			if (getFactor(node.getRight()) < 0)
				node = doLeftRotation(node);
			else
				node = doDoubleLeftRotation(node);
		}
		node.setHeight(max(height(node.getLeft()), height(node.getRight())) + 1);

		return node;
	}

	/** Faz rotação a direita */
	private static AvlNode doRightRotation(AvlNode parent) {
		rotacoes++;
		
		AvlNode novo = parent.getLeft();

		parent.setLeft(novo.getRight());

		novo.setRight(parent);

		parent.setHeight(max(height(parent.getLeft()),
				height(parent.getRight())) + 1);

		novo.setHeight(max(height(novo.getLeft()), parent.getHeight()) + 1);

		return novo;
	}

	/** Rotação a esquerda */
	private static AvlNode doLeftRotation(AvlNode parent) {
		rotacoes++;
		
		AvlNode novo = parent.getRight();

		parent.setRight(novo.getLeft());

		novo.setLeft(parent);

		parent.setHeight(max(height(parent.getLeft()),
				height(parent.getRight())) + 1);

		novo.setHeight(max(height(novo.getRight()), parent.getHeight()) + 1);

		return novo;
	}

	/** Rotação dupla a direita */
	private static AvlNode doDoubleRightRotation(AvlNode node) {

		node.setLeft(doLeftRotation(node.getLeft()));

		return doRightRotation(node);
	}

	/** Rotação dupla a esquerda */
	private static AvlNode doDoubleLeftRotation(AvlNode node) {

		node.setRight(doRightRotation(node.getRight()));

		return doLeftRotation(node);
	}

	public AvlNode search(T data) {
		return search(root, data);
	}

	protected AvlNode search(AvlNode<T> node, T data) {

		while (node != null) {

			/* se valor procuradp == chave do nó retorna referência ao nó */
			if (data.compareTo(node.getData()) == 0)
				return node;

			/*
			 * se valor procurado < chave do nó, procurar na sub-árvore esquerda
			 * deste nó
			 */
			else if (data.compareTo(node.getData()) < 0)
				node = node.getLeft();

			/*
			 * se valor procurado > chave do nó, procurar na sub-árvore direita
			 * deste nó
			 */
			else
				node = node.getRight();
		}

		// caso chave não foi achada, retorna null
		return null;
	}

	public void infix() {
		infix(root);
	}

	protected void infix(AvlNode node) {
		if (node != null) {
			infix(node.getLeft());
			System.out.print(node.getData() + " - ");
			infix(node.getRight());
		}
	}

	public void prefix() {
		prefix(root);
	}

	protected void prefix(AvlNode node) {
		if (node != null) {
			System.out.print(node.getData() + " ");
			prefix(node.getLeft());
			prefix(node.getRight());
		}
	}

	public void postfix() {
		postfix(root);
	}

	protected void postfix(AvlNode node) {
		if (node != null) {
			postfix(node.getLeft());
			postfix(node.getRight());
			System.out.print(node.getData() + " ");
		}
	}

	protected AvlNode searchFather(T data) {

		AvlNode<T> node = root;

		AvlNode parent = null;

		while (node != null && !(node.getData().compareTo(data) == 0)) {

			parent = node;

			if (node.getData().compareTo(data) < 0)
				node = node.getRight();

			else
				node = node.getLeft();
		}

		if (node != null && node.getData().compareTo(data) == 0)
			return parent;

		return null;
	}

	/* método de autoria de Leonardo Zandoná - 2006/2 */
	public void displayTree() {
		if (isEmpty()) {
			System.out.println("Árvore vazia!");
			return;
		}
		String separator = String.valueOf("  |__");
		System.out.println(this.root.getData() + "(" + root.getHeight() + ")");
		displaySubTree(root.getLeft(), separator);
		displaySubTree(root.getRight(), separator);
	}
	
	private void displaySubTree(AvlNode<T> node, String separator) {

		if (node != null) {

			AvlNode father = this.searchFather(node.getData());

			if (node.equals(father.getLeft()) == true) {
				System.out.println(separator + node.getData() + "("
						+ node.getHeight() + ")" + " (ESQ)");

			} else {
				System.out.println(separator + node.getData() + "("
						+ node.getHeight() + ")" + " (DIR)");
			}

			displaySubTree(node.getLeft(), "     " + separator);

			displaySubTree(node.getRight(), "     " + separator);
		}
	}
	
	public void imprimirRotacoesComparacoesAltura(int nodo) {
		height(root);
		System.out.printf("Inserindo o %d  [%d comparacoes] [%d rotacoes] [altura do root = %d]%n", nodo, comparacoes, rotacoes, this.root.getHeight());
	}
}