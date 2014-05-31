enum Color {RED, BLACK}

public class RBNode<T extends Comparable<T>> {
	
	public T data;
    public RBNode<T> left;
    public RBNode<T> right;
    public RBNode<T> parent;
   
    public Color color;

    public RBNode(T data, Color color, RBNode<T> left, RBNode<T> right) {
        this.data = data;
        this.color = color;
        this.left = left;
        this.right = right;
       
        if (left != null) 
        	left.parent = this;
        if (right!= null) 
        	right.parent = this;
       
        this.parent = null;
    }
    
    public RBNode<T> grandparent() {
        assert parent != null; // nao e o root
        assert parent.parent != null; // nao e o filho do root
      
        return parent.parent;
    }
    
    public RBNode<T> sibling() {
        
    	assert parent != null; // Root nao tem irmaos
        
    	if (this == parent.left)
            return parent.right;
        else
            return parent.left;
    }
    
    public RBNode<T> uncle() {
        assert parent != null; // Root node nao possui tio
        
        assert parent.parent != null; // filhos do root nao possuem tio
        
        return parent.sibling();
    }
	
    
}
