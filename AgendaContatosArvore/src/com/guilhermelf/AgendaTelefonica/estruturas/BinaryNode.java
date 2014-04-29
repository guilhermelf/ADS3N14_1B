package com.guilhermelf.AgendaTelefonica.estruturas;

public class BinaryNode<T> {
	private BinaryNode<T> left;
	private BinaryNode<T> right;
	private T dado;
	
	public BinaryNode(T dado) {
		this.dado = dado;
		this.left = null;
		this.right = null;
	}

	public BinaryNode<T> getLeft() {
		return left;
	}

	public void setLeft(BinaryNode<T> left) {
		this.left = left;
	}

	public BinaryNode<T> getRight() {
		return right;
	}

	public void setRight(BinaryNode<T> right) {
		this.right = right;
	}

	public T getDado() {
		return dado;
	}

	public void setDado(T dado) {
		this.dado = dado;
	}
}
