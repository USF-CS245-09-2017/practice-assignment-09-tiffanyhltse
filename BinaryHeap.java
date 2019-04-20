import java.util.Arrays;

public class BinaryHeap{
    private int[] heapData;
    private int maxSize;
    private int size;

    public BinaryHeap(){
        maxSize = 10;
        size = 0;
        heapData = new int[maxSize];

    }

    private int leftChild(int pos){
        return 2 * pos + 1;
    }

    private int rightChild(int pos){
        return 2 * pos + 2;
    }

    private int parent(int pos){
        if (pos == 0){ //if node is already a root node; node has no parent
            return 0;
        }
        return (pos - 1)/2;
    }

    private boolean hasLeftChild(int pos){
        return leftChild(pos) <= size - 1;
    }

    private boolean hasRightChild(int pos){
        return rightChild(pos) <= size - 1;
    }

    private boolean hasParent(int pos){
        return pos >= 1;
    }

    private void swap(int pos1, int pos2){
        int temp = heapData[pos1];
        heapData[pos1] = heapData[pos2];
        heapData[pos2] = temp;
    }

    public void add(int value){
        if (size >= heapData.length - 1){ //grow the array if the array of the initial maximum size is filled
            heapData = this.grow_array();
        }
        size++;
        int current = size - 1; //last available position of current array
        heapData[current] = value; //add value in last position on last level

        //when the current node has a parent and its value is smaller than its parent
        while(hasParent(current) && heapData[current] < heapData[parent(current)]){
            swap(current, parent(current)); //replace the parent with the value of the current node
            current = parent(current); //update position of last value inserted after swapping

        }

    }

    public int[] grow_array(){
        return Arrays.copyOf(heapData, heapData.length * 2);
    }

    public int remove(){ //removes the highest priority item - lowest number from priority queue
        if(this.isEmpty()){
            throw new IllegalStateException("Priority queue is empty!");
        }

        swap(0, size - 1); //replace the root with the right most child on the last level of the complete tree
        size--;

        shiftdown(0);

        return heapData[size];

    }

    public void shiftdown(int pos){

        while (hasLeftChild(pos)){  //when the current node has a left child
            int smallerChild = leftChild(pos); //default make it the smaller child

            //if there is a right child, compare left and right child to determine which of 2 children is smaller
            if (hasRightChild(pos) && heapData[leftChild(pos)] > heapData[rightChild(pos)]){
                smallerChild = rightChild(pos);
            }

            if(heapData[pos] > heapData[smallerChild]){ //if value of parent node is greater than value of smallest child
                swap(pos, smallerChild); //replace the parent with the value of the smallest child
            } else {
                break; //otherwise, exit this condition
            }
            pos = smallerChild; //update position of last value
            shiftdown(pos);
        }

    }

    public boolean isEmpty(){
        return size == 0; //return true if nothing is in the priority queue
    }

}

