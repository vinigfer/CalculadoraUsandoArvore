package source;

public class BinaryTreeOfInteger {

    private static final class Node {

        public Node father;
        public Node left;
        public Node right;
        private String element;

        public Node(String element) {
            father = null;
            left = null;
            right = null;
            this.element = element;
        }
        
        public void setElement(String element) {
            this.element = element;
        }
    }

    private int count; //contagem do número de nodos
    private Node root; //referência para o nodo raiz
    private Node cursor;

    public BinaryTreeOfInteger() {
        count = 0;
        root = null;
    }

    public boolean isEmpty() {
        return (root == null);
    }

    public int size() {
        return count;
    }

    public String getRoot() throws Exception {
        if (isEmpty()) {
            throw new Exception("Árvore está vazia");
        }
        return root.element;
    }

    public void setRoot(String element) throws Exception {
        if (isEmpty()) {
            throw new Exception("Árvore está vazia");
        }
        root.element = element;
    }
    
    public Integer getParent(Integer element) {
        // Implementar
        return null;
    }

    public int count() {
        return count(root);
    }
    
    private int count(Node n) {
        if (n == null) {
            return 0;
        } else {
            return 1 + count(n.left) + count(n.right);
        }
    }

//    public int height() {
//        Queue<Node> fila = new Queue<>();
//        Node aux = null;
//        Node fim = null;
//        if (root != null) {
//            fila.enqueue(root);
//            while(!fila.isEmpty()) {
//                aux = fila.dequeue();
//                if (aux.left != null)
//                    fila.enqueue(aux.left);
//                if (aux.right != null)
//                    fila.enqueue(aux.right);
//                fim = aux;
//            }
//        }                  
//        int cont=0;        
//        while (fim != root) {
//            cont++;
//            fim = fim.father;
//        }        
//        return cont;     
//    }
    
    public boolean addRoot(String element) {
        if (root != null){
            return false;
        }
        
        Node node = new Node(element);
        root = node;
        cursor = root;
        count++;
        return true;
    }

    public boolean addLeft(String element, String father) {
        Node n = searchNodeRef(father, root);
        if (n == null)
            return false;
        if (n.left != null) 
            return false;
        Node left = new Node(element);
        n.left = left;
        left.father = n;
        count++;
        return true;
        
    }
    
    public boolean addRight(String element, String father) {
        Node n = searchNodeRef(father, root);
        if (n == null)
            return false;
        if (n.right != null) 
            return false;
        Node right = new Node(element);
        n.right = right;
        right.father = n;
        count++;
        return true;
    }    

    public boolean removeBranch(String element) {
        Node n = this.searchNodeRef(element, root);
        
        if (n == null) {
            return false;
        }
        
        if (n == root) {
            root = null;
            count = 0;
            return true;
        }
        
        Node father = n.father;
    
        if (father.left == n) {
            father.left = null;
        } else {
            father.right = null;
        }
        n.father = null;
        count = count - this.count(n);
        return true;
    }

    public void setValueOnCursor(String value) {
        cursor.setElement(value);
    }
    
    public boolean isExternal(int element) {
        // Implementar
        return false;
    }

    public boolean isInternal(int element) {
        // Implementar
        return false;
    }
    
    public void addLowerLevel(String valor) {
        Node novoNo = new Node(valor);
        if(hasLeft()) {
            cursor.left = novoNo;
            novoNo.father = cursor;
        }
        else {
            cursor.right = novoNo;
            novoNo.father = cursor;
        }
        cursor = novoNo;
    }
    
    public void returnToUpperLevel() {
        cursor = cursor.father;
    }
    
    public void addOperando(String valor) {
        addLowerLevel(valor);
        returnToUpperLevel();
    }
    
    public boolean hasLeft() {
        if(cursor.left == null)
            return true;
        else
            return false;
    }
    
    public boolean hasRight() {
        if(cursor.right == null)
            return true;
        else
            return false;
    }    
    
    public Integer getLeft(Integer element) {
        // Implementar
        return null;
    }

    public String getRight(String element) {
        // Implementar
        Node n = this.searchNodeRef(element, root);
        if (n != null)
            if ( n.right != null)
                return n.right.element;
        return null;
    }

//    public LinkedListOfInteger positionsPre() {
//        LinkedListOfInteger res = new LinkedListOfInteger();
//        positionsPreAux(root, res);
//        return res;
//    }
//    
//    private void positionsPreAux(Node n, LinkedListOfInteger res) {
//        if(n == null)
//            return;
//        res.add(n.element);
//        if(n.left != null)
//            positionsPreAux(n.left, res);
//        if(n.right != null)
//            positionsPreAux(n.right, res);
//    }
//
//    public LinkedListOfInteger positionsPos() {
//        LinkedListOfInteger res = new LinkedListOfInteger();
//        positionsPosAux(root, res);
//        return res;
//    }
//    
//    private void positionsPosAux(Node n, LinkedListOfInteger res) {
//        if(n == null)
//            return;
//        if(n.left != null)
//            positionsPosAux(n.left, res);
//        if(n.right != null)
//            positionsPosAux(n.right, res);
//        res.add(n.element);
//    }
//
//    public LinkedListOfInteger positionsCentral() {
//        LinkedListOfInteger res = new LinkedListOfInteger();
//        positionsCentralAux(root, res);
//        return res;
//    }
//    
//    private void positionsCentralAux(Node n, LinkedListOfInteger res) {
//        if(n == null)
//            return;
//        if(n.left != null)
//            positionsCentralAux(n.left, res);
//        res.add(n.element);
//        if(n.right != null)
//            positionsCentralAux(n.right, res);
//
//    }
//
//    public LinkedListOfInteger positionsWidth() {
//        LinkedListOfInteger li = new LinkedListOfInteger();
//        Queue<Node> fila = new Queue<>();
//        Node aux = null;
//        if (root != null) {
//            fila.enqueue(root);
//            while(!fila.isEmpty()) {
//                aux = fila.dequeue();
//                if (aux.left != null)
//                    fila.enqueue(aux.left);
//                if (aux.right != null)
//                    fila.enqueue(aux.right);
//                li.add(aux.element);
//            }
//        }        
//        return li;
//    }

    public int level (String element) {
        Node n = this.searchNodeRef(element, root);
        if (n==null)
            return -1;
        int cont=0;        
        while (n!= root) {
            cont++;
            n = n.father;
        }        
        return cont;
    }
            
    public String strPositionsPre() {
        return strPositionsPre(root);
    }
    private String strPositionsPre(Node n) {
        String res = "";
        // Implementar
        return res;
    }

    public String strPositionsPos() {
        return strPositionsPos(root);
    }
    private String strPositionsPos(Node n) {
        String res = "";
        // Implementar
        return res;
    }

    public String strPositionsCentral() {
        return strPositionsCentral(root);
    }
    private String strPositionsCentral(Node n) {
        String res = "";
        // Implementar
        return res;
    }

    public boolean contains(String element) {
        Node nAux = searchNodeRef(element, root);
        return (nAux != null);
    }

    private Node searchNodeRef(String element, Node target) {
        Node res = null;
        if (target != null) {
            if (target.element.equals(element)) {
                res = target;
            } else {
                res = searchNodeRef(element, target.left);
                if (res == null) {
                    res = searchNodeRef(element, target.right);
                }
            }
        }
        return res;
    }
}