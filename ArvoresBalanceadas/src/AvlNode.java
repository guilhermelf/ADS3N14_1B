public class AvlNode<T> {  
    protected int height;       
    protected T data;  
    protected AvlNode<T> left;  
    protected AvlNode<T> right;
  
    public AvlNode (T data) {  
        this(data, null, null);  
    }  
  
    public AvlNode (T data, AvlNode left, AvlNode right) {  
        this.data = data;  
        this.left = left;  
        this.right = right;  
        this.height = 0;  
    }

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public AvlNode<T> getLeft() {
		return left;
	}

	public void setLeft(AvlNode<T> left) {
		this.left = left;
	}

	public AvlNode<T> getRight() {
		return right;
	}

	public void setRight(AvlNode<T> right) {
		this.right = right;
	}  
}  