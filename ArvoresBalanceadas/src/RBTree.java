public class RBTree<T extends Comparable<T>> {
   // constants
    public RBNode root;  
	
	static int comparacoes = 0;
	static int rotacoes = 0;
	static int totalComparacoes = 0;
	static int totalRotacoes = 0;
    
    public RBTree() {
        root = null;
        verifyProperties();
    }
    
    int altura(RBNode node) {
    	   if (node == null) 
    	      return -1; // altura de árvore vazia é -1
    	   else {
    	      int he = altura(node.left);
    	      int hd = altura(node.right);
    	      if (he < hd) return hd + 1;
    	      else return he + 1;
    	   }
    	}
    
    public void verifyProperties() {
        verifyProperty1(root);
        verifyProperty2(root);
        // Propriedade 3 esta implicito
        verifyProperty4(root);
        verifyProperty5(root);
    }
    
    private static Color nodeColor(RBNode n) {
        return n == null ? Color.BLACK : n.color;
    }
    
    /**
     * Verificar propriedade 1: 
     * 
     * Um nodo de uma árvore Red-Black é preto ou vermelho.
     */
    private static void verifyProperty1(RBNode n) {
        assert nodeColor(n) == Color.RED || nodeColor(n) == Color.BLACK;
        if (n == null) return;
        verifyProperty1(n.left);
        verifyProperty1(n.right);
    }
    
    /**
     * Verificar propriedade 2: 
     * 
     * A raiz de uma árvore Red-Black é um nodo preto.
     */
    private static void verifyProperty2(RBNode root) {
    	assert nodeColor(root) == Color.BLACK;
    }
    
    /**
     * Verificar propriedade 4: 
     * 
     * Todo filho vermelho contém dois filhos pretos (ou seja, não contém filhos vermelhos).
     */
    private static void verifyProperty4(RBNode n) {
        if (nodeColor(n) == Color.RED) {
            assert nodeColor(n.left)   == Color.BLACK;
            assert nodeColor(n.right)  == Color.BLACK;
            assert nodeColor(n.parent) == Color.BLACK;
        }
        if (n == null) return;
        verifyProperty4(n.left);
        verifyProperty4(n.right);
    }
   
    /**
     * Verificar propriedade 5: 
     * 
     * Todo caminho de um nodo até as folhas possui o mesmo número de nodos pretos.
     */
    private static void verifyProperty5(RBNode root) {
        verifyProperty5Helper(root, 0, -1);
    }

    private static int verifyProperty5Helper(RBNode n, int blackCount, int pathBlackCount) {
        if (nodeColor(n) == Color.BLACK) {
            blackCount++;
        }
        if (n == null) {
            if (pathBlackCount == -1) {
                pathBlackCount = blackCount;
            } else {
                assert blackCount == pathBlackCount;
            }
            return pathBlackCount;
        }
        pathBlackCount = verifyProperty5Helper(n.left,  blackCount, pathBlackCount);
        pathBlackCount = verifyProperty5Helper(n.right, blackCount, pathBlackCount);
        return pathBlackCount;
    }
    
    
    private RBNode lookupNode(T data) {
    	RBNode n = root;
        while (n != null) {
        	comparacoes ++;
            int compResult = data.compareTo((T) n.data);
            if (compResult == 0) {
                return n;
            } else if (compResult < 0) {
                n = n.left;
            } else {
                assert compResult > 0;
                n = n.right;
            }
        }
        return n;
    }
    
    public T lookup(T data) {
        RBNode n = lookupNode(data);
        return (T) (n == null ? null : n.data);
    }
    
    private void rotateLeft(RBNode<T> n) {
    	rotacoes ++;
    	RBNode<T> r = n.right;
        replaceNode(n, r);
        n.right = r.left;
        if (r.left != null) {
            r.left.parent = n;
        }
        r.left = n;
        n.parent = r;
    }

    private void rotateRight(RBNode<T> n) {
    	rotacoes ++;
    	RBNode<T> l = n.left;
        replaceNode(n, l);
        n.left = l.right;
        if (l.right != null) {
            l.right.parent = n;
        }
        l.right = n;
        n.parent = l;
    }
    
    private void replaceNode(RBNode<T> oldn, RBNode<T> newn) {
        if (oldn.parent == null) {
            root = newn;
        } else {
            if (oldn == oldn.parent.left)
                oldn.parent.left = newn;
            else
                oldn.parent.right = newn;
        }
        if (newn != null) {
            newn.parent = oldn.parent;
        }
    }
    
    public void insert(T data) {
    	comparacoes = 0;
    	rotacoes = 0;
        RBNode<T> insertedNode = new RBNode<T>(data, Color.RED, null, null);
        if (root == null) {
            root = insertedNode;
        } else {
            RBNode<T> n = root;
            while (true) {
                int compResult = data.compareTo(n.data);
                comparacoes++;
                if (compResult == 0) {
                    n.data = data;
                    return;
                } else if (compResult < 0) {
                    if (n.left == null) {
                        n.left = insertedNode;
                        break;
                    } else {
                        n = n.left;
                    }
                } else {
                    assert compResult > 0;
                    if (n.right == null) {
                        n.right = insertedNode;
                        break;
                    } else {
                        n = n.right;
                    }
                }
            }
            insertedNode.parent = n;
        }
        insertCase1(insertedNode);
        verifyProperties();
        System.out.print("Inserindo o ");
        imprimirRotacoesComparacoesAltura((Integer) data);
        totalComparacoes += comparacoes;
        totalRotacoes += rotacoes;
    }
    
    public void imprimirRotacoesComparacoesAltura(int nodo) {
		System.out.printf("%d  [%d comparacoes] [%d rotacoes] [altura do root = %d]%n", nodo, comparacoes, rotacoes, altura(root));
	}
   
    private void insertCase1(RBNode<T> n) {
        if (n.parent == null)
            n.color = Color.BLACK;
        else
            insertCase2(n);
    }
    
    private void insertCase2(RBNode<T> n) {
        if (nodeColor(n.parent) == Color.BLACK)
            return; // Tree is still valid
        else
            insertCase3(n);
    }
    
    void insertCase3(RBNode<T> n) {
        if (nodeColor(n.uncle()) == Color.RED) {
            n.parent.color = Color.BLACK;
            n.uncle().color = Color.BLACK;
            n.grandparent().color = Color.RED;
            insertCase1(n.grandparent());
        } else {
            insertCase4(n);
        }
    }
    
    void insertCase4(RBNode<T> n) {
        if (n == n.parent.right && n.parent == n.grandparent().left) {
            rotateLeft(n.parent);
            n = n.left;
        } else if (n == n.parent.left && n.parent == n.grandparent().right) {
            rotateRight(n.parent);
            n = n.right;
        }
        insertCase5(n);
    }
    
    void insertCase5(RBNode<T> n) {
        n.parent.color = Color.BLACK;
        n.grandparent().color = Color.RED;
        if (n == n.parent.left && n.parent == n.grandparent().left) {
            rotateRight(n.grandparent());
        } else {
            assert n == n.parent.right && n.parent == n.grandparent().right;
            rotateLeft(n.grandparent());
        }
    }
    
	protected void postfix(AvlNode node) {
		if (node != null) {
			postfix(node.getLeft());
			postfix(node.getRight());
			System.out.print(node.getData() + " ");
		}
	}

	protected RBNode searchFather(T data) {

		RBNode<T> node = root;

		RBNode parent = null;

		while (node != null && !(node.data.compareTo(data) == 0)) {

			parent = node;

			if (node.data.compareTo(data) < 0)
				node = node.right;

			else
				node = node.left;
		}

		if (node != null && node.data.compareTo(data) == 0)
			return parent;

		return null;
	}

	/* método de autoria de Leonardo Zandoná - 2006/2 */
	public void displayTree() {
		if (root == null) {
			System.out.println("Árvore vazia!");
			return;
		}
		String separator = String.valueOf("  |__");
		System.out.println(this.root.data + "(" + altura(root) + ")");
		displaySubTree(root.left, separator);
		displaySubTree(root.right, separator);
	}
	
	private void displaySubTree(RBNode<T> node, String separator) {

		if (node != null) {

			RBNode father = this.searchFather(node.data);

			if (node.equals(father.left) == true) {
				System.out.println(separator + node.data + "("
						+ altura(node) + ")" + " (ESQ)");

			} else {
				System.out.println(separator + node.data + "("
						+ altura(node) + ")" + " (DIR)");
			}

			displaySubTree(node.left, "     " + separator);

			displaySubTree(node.right, "     " + separator);
		}
	}
    
	public void delete(T data) {
		comparacoes = 0;
		rotacoes = 0;
		
	    RBNode<T> n = lookupNode(data);
	    if (n == null)
	        return;  // Key not found, do nothing
	    if (n.left != null && n.right != null) {
	        // Copy data/value from predecessor and then delete it instead
	        RBNode<T> pred = maximumNode(n.left);
	        n.data = pred.data;
	        n = pred;
	    }

	    assert n.left == null || n.right == null;
	    RBNode<T> child = (n.right == null) ? n.left : n.right;
	    if (nodeColor(n) == Color.BLACK) {
	        n.color = nodeColor(child);
	        deleteCase1(n);
	    }
	    replaceNode(n, child);

	    verifyProperties();
	    System.out.print("Removendo ");
	    imprimirRotacoesComparacoesAltura((Integer) data);
        totalComparacoes += comparacoes;
        totalRotacoes += rotacoes;
	}
	
	private static RBNode maximumNode(RBNode n) {
	    assert n != null;
	    while (n.right != null) {
	        n = n.right;
	    }
	    return n;
	}
	
	private void deleteCase1(RBNode<T> n) {
	    if (n.parent == null)
	        return;
	    else
	        deleteCase2(n);
	}
	
	private void deleteCase2(RBNode<T> n) {
	    if (nodeColor(n.sibling()) == Color.RED) {
	        n.parent.color = Color.RED;
	        n.sibling().color = Color.BLACK;
	        if (n == n.parent.left)
	            rotateLeft(n.parent);
	        else
	            rotateRight(n.parent);
	    }
	    deleteCase3(n);
	}
	
	private void deleteCase3(RBNode<T> n) {
	    if (nodeColor(n.parent) == Color.BLACK &&
	        nodeColor(n.sibling()) == Color.BLACK &&
	        nodeColor(n.sibling().left) == Color.BLACK &&
	        nodeColor(n.sibling().right) == Color.BLACK)
	    {
	        n.sibling().color = Color.RED;
	        deleteCase1(n.parent);
	    }
	    else
	        deleteCase4(n);
	}
   
	private void deleteCase4(RBNode<T> n) {
	    if (nodeColor(n.parent) == Color.RED &&
	        nodeColor(n.sibling()) == Color.BLACK &&
	        nodeColor(n.sibling().left) == Color.BLACK &&
	        nodeColor(n.sibling().right) == Color.BLACK)
	    {
	        n.sibling().color = Color.RED;
	        n.parent.color = Color.BLACK;
	    }
	    else
	        deleteCase5(n);
	}
    
	private void deleteCase5(RBNode<T> n) {
	    if (n == n.parent.left &&
	        nodeColor(n.sibling()) == Color.BLACK &&
	        nodeColor(n.sibling().left) == Color.RED &&
	        nodeColor(n.sibling().right) == Color.BLACK)
	    {
	        n.sibling().color = Color.RED;
	        n.sibling().left.color = Color.BLACK;
	        rotateRight(n.sibling());
	    }
	    else if (n == n.parent.right &&
	             nodeColor(n.sibling()) == Color.BLACK &&
	             nodeColor(n.sibling().right) == Color.RED &&
	             nodeColor(n.sibling().left) == Color.BLACK)
	    {
	        n.sibling().color = Color.RED;
	        n.sibling().right.color = Color.BLACK;
	        rotateLeft(n.sibling());
	    }
	    deleteCase6(n);
	}

	private void deleteCase6(RBNode<T> n) {
	    n.sibling().color = nodeColor(n.parent);
	    n.parent.color = Color.BLACK;
	    if (n == n.parent.left) {
	        assert nodeColor(n.sibling().right) == Color.RED;
	        n.sibling().right.color = Color.BLACK;
	        rotateLeft(n.parent);
	    }
	    else
	    {
	        assert nodeColor(n.sibling().left) == Color.RED;
	        n.sibling().left.color = Color.BLACK;
	        rotateRight(n.parent);
	    }
	}
}