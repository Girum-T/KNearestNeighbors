/* ---------------------------------------------------------------------------------
Girum Worku 300305611
PriorityQueue1  réalisée avec un simple tableau trié dans lequel l’élément maximum se trouve à la fin du tableau. 
------------------------------------------------------------------------------------*/

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PriorityQueue1 <E extends Comparable <E>> implements PriorityQueueIF<E>{

    List<E> list;
    int capacity;

    public PriorityQueue1(int capacity){

        list = new ArrayList<>(capacity);  //Creation d'une nouvelle liste pour la file
        this.capacity = capacity;

    }
    public void offer(E element){
        list.add(element);              // A chaque fois qu'on ajoute un element, la list est triee pour qu'elle soit toujours en ordre croissant
        Collections.sort(list);
    }

    public E poll(){
        return list.remove(capacity- 1);    //On sort le derniere element (le plus grand)
    }

    public E peek(){
        return list.get(capacity-1);        //On regarde le dernier element (le plus grand)
    }

    public int size(){
        return list.size();                 //taille de la file a prioritee
        
    }

    public boolean isEmpty(){
        return list.isEmpty();              //verifie si elle est vide
    }
    public int getindex(double element){
        return list.indexOf(element);       //fonction qui retourne l'index d'un element dans le tableau
    }
}
    
    








 