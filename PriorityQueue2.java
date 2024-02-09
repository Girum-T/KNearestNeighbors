/* ---------------------------------------------------------------------------------
Girum Worku 300305611
PriorityQueue12 réalisée avec un tableau structuré en monceau. L’élément maximum se trouve à la racine de ce monceau (i.e. premier élément du tableau). 
Chaque insertion dans le monceau déclenche une percolation vers le haut alors que les retraits déclenche une percolation vers le bas.

------------------------------------------------------------------------------------*/
import java.util.ArrayList;
import java.util.List;

public class PriorityQueue2 <E extends Comparable <E>> implements PriorityQueueIF<E>{

    List<E> list;
    int capacity;

    public PriorityQueue2(int capacity){

        list = new ArrayList<>(capacity);       //Creation d'une nouvelle liste pour la file
        this.capacity = capacity;


    }
    public void offer(E element){
        list.add(element);                  //Ajour d'un element a la fin de l'arbre
    }

    public E poll(){
        return list.remove(0);      //Retrait de la racine (de l'element maximum) de l'arbre
    }

    public E peek(){
        return list.get(0);         //Lecture de l'element racine 
    }

    public int size(){
        return list.size();                // Taille de l'arbre
        
    }

    public boolean isEmpty(){
        return list.isEmpty();             //Verification si l'arbre est vide
    }

    public  void downheap(int N, int i){    //Fonction downheap qui arrange l'ordre d'un sous arbre du monceau
		int maxi = i; // On suppose que le permier element est le plus grand
		int g = 2 * i + 1; // Attribut l'element gauche
		int d = 2 * i + 2; // Attribut l'element droit

		// On verifie si l'enfant gauche est plus grand que son parent
		if (g < N && list.get(g).compareTo( list.get(maxi))>0){
			maxi = g;           //Si oui l'enfant gauche devient le max
        }

		// On verifie si l'enfant droit est plus grand que le max
		if (d < N && list.get(d).compareTo(list.get(maxi))>0){
			maxi = d;           //Si oui l'enfant droit devient le maxi
        }

		
		if (maxi != i) {
			E swap = list.get(i);       //Si le parent n'est pas le max alors on echange les position du max et du parent
            list.set(i, list.get(maxi));
			list.set(maxi, swap);

			
			downheap(N, maxi);      //Reccursion sur les sous arbres
		}
	}
    public void contructheap(int N){
		
		int debut = (N / 2) - 1;    //Commence par le le dernier noeuds qui a un enfant

		for (int i = debut; i >= 0; i--) {
			downheap(N, i);            //On repete jusqu'a tout larbre devient un monceaux ()          
		}
	}
    }
    