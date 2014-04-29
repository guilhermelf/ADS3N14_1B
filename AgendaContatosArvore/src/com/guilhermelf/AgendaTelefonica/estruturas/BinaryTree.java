package com.guilhermelf.AgendaTelefonica.estruturas;

import java.util.ArrayList;

public class BinaryTree<T extends Comparable<T>> {
	private BinaryNode<T> parent;
	private BinaryNode<T> root;
	private int[] alturaQuantidade = new int[2];
	public int comparacoes = 0;	
	private boolean localizado = false;
	
	public BinaryTree() {
		this.root = null;
	}
	
	/**
	 * Metodo que testa se a arvore esta vazia.
	 * @return boolean
	 */
	public boolean isEmpty() {
		return (this.root != null) ? false : true;
	}
	
	/**
	 * Metodo que chama o metodo recursivo responsavel pela insercao na arvore binaria.
	 * @param Object
	 */
	public int[] insert(T dado) {
		//System.out.println("Inserindo o elemento: " + dado);
		if(this.root == null) {
			this.root = new BinaryNode<T>(dado);
			alturaQuantidade[0] = height();
			alturaQuantidade[1] = size();
			//System.out.println("Inseriu no Root.");
		} else 
			insert(dado, this.root);
		return alturaQuantidade;
	}
	
	/**
	 * Metodo recursivo responsavel pela inclusao de um elemento na arvore
	 * @param Object
	 * @param BinaryNode
	 */
	private void insert(T dado, BinaryNode<T> current) {
		alturaQuantidade = new int[2];
		
		if(dado.compareTo(current.getDado()) < 0) {
			if(current.getLeft() == null) {
				current.setLeft(new BinaryNode<T>(dado));
				//System.out.println("Inseriu na esquerda do " + current.getDado() + ".");
				alturaQuantidade[0] = height();
				alturaQuantidade[1] = size();
			} else { 
				insert(dado, current.getLeft());
			}
		} else if(dado.compareTo(current.getDado()) > 0) {
			if(current.getRight() == null) {
				current.setRight(new BinaryNode<T>(dado));
				
				//System.out.println("Inseriu na direita do " + current.getDado() + ".");
				alturaQuantidade[0] = height();
				alturaQuantidade[1] = size();
			} else { 
				insert(dado, current.getRight());
			}
		} else {
			alturaQuantidade = null;
		}
	}
	
	
	/**
	 * Metodo que testa se a arvore esta vazia, entao chama o infixTraversal recursivo, mantendo encapsulada a arvore, 
	 * protegendo assim seus elementos.
	 * 
	 * @return Arraylist<T>
	 */
	public ArrayList<T> infixTraversal() {
		ArrayList<T> dados = new ArrayList<T>();
		if (!isEmpty()) {
			infixTraversal(root, dados);
			return dados;
		} else {
			return null;
		}
	}
	
	/**
	 * Metodo recursivo que percorre os elementos da arvore, de forma ordenados (infix) 
	 * Primeiro avalia o conteúdo dos nodos a esquerda, após avalia o conteúdo do nodo e depois os �?lhos a direita.
	 * 
	 * Criterio de parada: BinaryNode ser igual a null.
	 * 
	 * Ordem de execucao: left, node e right
	 * @param BinaryNode
	 */
	private void infixTraversal(BinaryNode<T> node, ArrayList<T> dados) {	
		if(node != null) {
			infixTraversal(node.getLeft(), dados);
			dados.add(node.getDado());
			infixTraversal(node.getRight(), dados);
		}
	}
	
	
	/**
	 * Metodo que testa se a arvore esta vazia, entao chama o prefixTraversal recursivo, mantendo encapsulada a arvore, 
	 * protegendo assim seus elementos.
	 * 
	 * @return ArrayList<T>
	 */
	public ArrayList<T> prefixTraversal() {
		ArrayList<T> dados = new ArrayList<T>();
		if (!isEmpty()) {
			prefixTraversal(root, dados);
			return dados;
		} else
			return null;	
	}
	
	/**
	 * Metodo recursivo que percorre os elementos da arvore (prefix), tambem chamado pre-order
	 * Primeiro avalia o conteúdo do nodo, após, avalia os �?lhos a esquerda e após os �?lhos a direita.
	 * 
	 * Criterio de parada: BinaryNode ser igual a null.
	 * 
	 * Ordem de execucao: node, left e right
	 * @param BinaryNode
	 */
	private void prefixTraversal(BinaryNode<T> node, ArrayList<T> dados) {
		if(node != null) {
			dados.add(node.getDado());
			prefixTraversal(node.getLeft(), dados);		
			prefixTraversal(node.getRight(), dados);
		}
	}
	
	/**
	 * Metodo que testa se a arvore esta vazia, entao chama o postfixTraversal recursivo, mantendo encapsulada a arvore, 
	 * protegendo assim seus elementos.
	 * 
	 * @return ArrayList<T>
	 */
	public ArrayList<T> postfixTraversal() {
		if (!isEmpty()) {
			ArrayList<T> dados = new ArrayList<T>();
			postfixTraversal(root, dados);
			return dados;
		} else
			return null;	
	}
	
	/**
	 * Metodo recursivo que percorre os elementos da arvore (postfix), tambem chamado post-order
	 * Primeiro avalia o conteúdo dos nodos a esquerda, após os nodos a direita, e por �?m, o conteúdo do próprio nodo
	 * 
	 * Criterio de parada: BinaryNode ser igual a null.
	 * 
	 * Ordem de execucao: left, right e node
	 * @param BinaryNode
	 */
	private void postfixTraversal(BinaryNode<T> node, ArrayList<T> dados) {
		if(node != null) {
			postfixTraversal(node.getLeft(), dados);		
			postfixTraversal(node.getRight(), dados);
			dados.add(node.getDado());
		}
	}
	
	/**
	 * Busca em largura, localiza o elemento atraves do uso de uma fila.
	 * 
	 * @param Object
	 * @return Object
	 */
	public T breadthFirstSearch(T dado) {
		comparacoes = 0;
		if(!isEmpty()) {
			ArrayList<BinaryNode<T>> fila = new ArrayList<>();
			BinaryNode<T> elemento = null;
			
			fila.add(this.root);
			
			while(!fila.isEmpty()) {			
				
				elemento = fila.remove(0);
				
				comparacoes ++;
				
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
	
	/**
	 * Busca em profundidade, localiza o elemento atraves do uso de uma pilha.
	 * 
	 * @param Object
	 * @return Object
	 */
	public T depthFirstSearch(T dado) {
		comparacoes = 0;
		if(!isEmpty()) {
			ArrayList<BinaryNode<T>> pilha = new ArrayList<>();
			BinaryNode<T> elemento = null;
			
			pilha.add(this.root);
			
			while(!pilha.isEmpty()) {			
				
				elemento = pilha.remove(pilha.size() - 1);
				
				comparacoes ++;
				
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
	
	/**
	 * Chama o metodo recursivo que retorna o tamanho da arvore (numero de elementos)
	 * @return int
	 */
	public int size() {
		return (size(this.root));
	}
	
	/**
	 * Metodo retorna o tamanho da arvore (numero de elementos)
	 * @return int
	 */
	private int size(BinaryNode<T> node) {
		if (node == null)
			return (0);
		else {
			return (size(node.getLeft()) + 1 + size(node.getRight()));
		}
	}
	
	/**
	 * Metodo que chama o metodo recursivo que retorna a altura da arvore.
	 * @return int
	 */
	public int height() {
		return (height(this.root) - 1);
	}
	
	/**
	 * Metodo recursivo que retorna a altura de um nodo.
	 * @param BinaryNode
	 * @return int
	 */
	private int height(BinaryNode<T> current) {
		if(current == null )
			return 0;
		return 1 + Math.max(height(current.getLeft()), height(current.getRight()));
	}
	
	
	/**
	 * Método que chama a exclusao, recebendo um elemento, mantendo a arvore encapsulada
	 * 
	 * @param dado
	 * @return boolean
	 */
	public boolean remove(T dado) {
		this.localizado = false;
		if(!isEmpty()) {
			if(remove(root, dado) != null)
				return true;
			else
				return false;
		} else
			return false;
	}
		
	public BinaryNode<T> remove(BinaryNode<T> auxiliar, T dado) {
		
		if(depthFirstSearch(dado) == null)
			return null;
		
		BinaryNode<T> parent;
		BinaryNode<T> parent2;
       
		if (auxiliar.getDado().compareTo(dado) == 0) {
            if (auxiliar.getLeft() == auxiliar.getRight()) {
              
            	return null;
           
            } else if (auxiliar.getLeft() == null) {
              
            	return auxiliar.getRight();
           
            } else if (auxiliar.getRight() == null) {
              
            	return auxiliar.getLeft();
           
            } else {
                parent2 = auxiliar.getRight();
                parent = auxiliar.getRight();
                
                while (parent.getLeft() != null) {
                	parent = parent.getLeft();
                }
               
                parent.setLeft(auxiliar.getLeft());
                
                return parent2;
            }
        } else if (auxiliar.getDado().compareTo(dado) < 0) {
           
        	auxiliar.setRight(remove(auxiliar.getRight(), dado));
        
        } else {
         
        	auxiliar.setLeft(remove(auxiliar.getLeft(), dado));
        }
       
		return auxiliar;
    }
}