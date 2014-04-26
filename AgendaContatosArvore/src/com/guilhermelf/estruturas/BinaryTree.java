package com.guilhermelf.estruturas;

import java.util.ArrayList;
import java.util.Random;

public class BinaryTree<T extends Comparable<T>> {
	private BinaryNode<T> root;
	
	public BinaryTree() {
		this.root = null;
	}
	
	public boolean isEmpty() {
		return (this.root != null) ? false : true;
	}
	
	public void insert(T dado) {
		//System.out.println("Inserindo o elemento: " + dado);
		if(this.root == null) {
			this.root = new BinaryNode<T>(dado);
			//System.out.println("Inseriu no Root.");
		} else
			insert(dado, this.root);
	}
	
	private void insert(T dado, BinaryNode<T> current) {
		if(dado.compareTo(current.getDado()) < 0) {
			if(current.getLeft() == null) {
				current.setLeft(new BinaryNode<T>(dado));
				//System.out.println("Inseriu na esquerda do " + current.getDado() + ".");
			} else { 
				insert(dado, current.getLeft());
			}
		} else if(dado.compareTo(current.getDado()) > 0) {
			if(current.getRight() == null) {
				current.setRight(new BinaryNode<T>(dado));
				//System.out.println("Inseriu na direita do " + current.getDado() + ".");
			} else { 
				insert(dado, current.getRight());
			}
		} else {
			System.out.println("Elemento já existe");
		}
	}
	
	
	/**
	 * Metodo que testa se a arvore esta vazia, entao chama o infixTraversal recursivo, mantendo encapsulada a arvore, 
	 * protegendo assim seus elementos.
	 */
	public void infixTraversal() {
		if (!isEmpty()) 
			infixTraversal(root);
		else
			System.out.println("Arvore vazia.");	
	}
	
	/**
	 * Metodo recursivo que percorre os elementos da arvore, de forma ordenados (infix) 
	 * Primeiro avalia o conteúdo dos nodos a esquerda, após avalia o conteúdo do nodo e depois os ﬁlhos a direita.
	 * 
	 * Criterio de parada: BinaryNode ser igual a null.
	 * 
	 * Ordem de execucao: left, node e right
	 * @param BinaryNode
	 */
	private void infixTraversal(BinaryNode<T> node) {
		if(node != null) {
			infixTraversal(node.getLeft());
			System.out.println(node.getDado());
			infixTraversal(node.getRight());
		}
	}
	
	
	/**
	 * Metodo que testa se a arvore esta vazia, entao chama o prefixTraversal recursivo, mantendo encapsulada a arvore, 
	 * protegendo assim seus elementos.
	 */
	public void prefixTraversal() {
		if (!isEmpty()) 
			prefixTraversal(root);
		else
			System.out.println("Arvore vazia.");	
	}
	
	/**
	 * Metodo recursivo que percorre os elementos da arvore (prefix), tambem chamado pre-order
	 * Primeiro avalia o conteúdo do nodo, após, avalia os ﬁlhos a esquerda e após os ﬁlhos a direita.
	 * 
	 * Criterio de parada: BinaryNode ser igual a null.
	 * 
	 * Ordem de execucao: node, left e right
	 * @param BinaryNode
	 */
	private void prefixTraversal(BinaryNode<T> node) {
		if(node != null) {
			System.out.println(node.getDado());
			prefixTraversal(node.getLeft());		
			prefixTraversal(node.getRight());
		}
	}
	
	/**
	 * Metodo que testa se a arvore esta vazia, entao chama o postfixTraversal recursivo, mantendo encapsulada a arvore, 
	 * protegendo assim seus elementos.
	 */
	public void postfixTraversal() {
		if (!isEmpty()) 
			postfixTraversal(root);
		else
			System.out.println("Arvore vazia.");	
	}
	
	/**
	 * Metodo recursivo que percorre os elementos da arvore (postfix), tambem chamado post-order
	 * Primeiro avalia o conteúdo dos nodos a esquerda, após os nodos a direita, e por ﬁm, o conteúdo do próprio nodo
	 * 
	 * Criterio de parada: BinaryNode ser igual a null.
	 * 
	 * Ordem de execucao: left, right e node
	 * @param BinaryNode
	 */
	private void postfixTraversal(BinaryNode<T> node) {
		if(node != null) {
			postfixTraversal(node.getLeft());		
			postfixTraversal(node.getRight());
			System.out.println(node.getDado());
		}
	}
	
	/**
	 * Busca em largura, informa o elemento e atraves do uso de uma fila, a arvore e percorrida, essa busca ocorre por niveis.
	 * 
	 * @param dado
	 * @return elemento
	 */
	public T breadthFirstSearch(T dado) {
		if(!isEmpty()) {
			ArrayList<BinaryNode<T>> fila = new ArrayList<>();
			BinaryNode<T> elemento = null;
			
			fila.add(this.root);
			
			while(!fila.isEmpty()) {			
				
				elemento = fila.remove(0);
				
				if(elemento.getDado().compareTo(dado) == 0){
					return elemento.getDado();
				}
				
				if(elemento.getLeft() != null)
					fila.add(elemento.getLeft());
				
				if(elemento.getRight() != null)
					fila.add(elemento.getRight());
			}
			
			return null;
			
		} else
			return null;
	}
	
	public T depthFirstSearch(T dado) {
		if(!isEmpty()) {
			ArrayList<BinaryNode<T>> pilha = new ArrayList<>();
			BinaryNode<T> elemento = null;
			
			pilha.add(this.root);
			
			while(!pilha.isEmpty()) {			
				
				elemento = pilha.remove(pilha.size() - 1);
				
				if(elemento.getDado().compareTo(dado) == 0){
					return elemento.getDado();
				}
				
				if(elemento.getLeft() != null)
					pilha.add(elemento.getLeft());
				
				if(elemento.getRight() != null)
					pilha.add(elemento.getRight());
			}
			
			return null;
			
		} else
			return null;
	}
	
	public int size() {
		return (size(this.root));
	}

	private int size(BinaryNode<T> node) {
		if (node == null)
			return (0);
		else {
			return (size(node.getLeft()) + 1 + size(node.getRight()));
		}
	}
	
	public int height() {
		return height(this.root);
	}
	
	private int height(BinaryNode<T> current) {
		if(current == null )
			return 0;
		return 1 + Math.max(height(current.getLeft()), height(current.getRight()));
	}

	public static void main(String[] args) {
		BinaryTree<Integer> arvore = new BinaryTree<Integer>();
		Random r = new Random();
		
		for (int i = 0; i < 15; i++) {
			arvore.insert(r.nextInt(101));
		}
		
		System.out.println("Root: " + arvore.root.getDado());
		
		System.out.println("Impressão em ordem: ");
		arvore.infixTraversal();
		
		System.out.println("Impressão pre-order: ");
		arvore.prefixTraversal();	
		
		System.out.println("Impressão post-order: ");
		arvore.postfixTraversal();	
		
		System.out.println("Busca em largura: ");
		System.out.println(arvore.breadthFirstSearch(15));
		
		System.out.println("Busca em profundidade: ");
		System.out.println(arvore.depthFirstSearch(15));
		
		System.out.println("Numero de elementos da arvore: ");
		System.out.println(arvore.size());
		
		System.out.println("Altura do root: ");
		System.out.println(arvore.height());
		
		
	}
}
