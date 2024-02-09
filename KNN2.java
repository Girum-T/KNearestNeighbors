/* ---------------------------------------------------------------------------------
Girum Worku 300305611
Knn2 avec PriorityQueue2 réalisée avec un tableau structuré en monceau. L’élément maximum se trouve à la racine de ce monceau (premier élément du
tableau).
------------------------------------------------------------------------------------*/

import java.util.ArrayList;
import java.util.List;

public class KNN2 {
	
    public static List<LabelledPoint> findKNearestNeighbors3(LabelledPoint query, List<LabelledPoint> points , int k) {
         // Fonction qui prend en entree un un point query, une liste de points points et in nombre k qui retourne les k voisin du point query
        PriorityQueue2<Double> maxHeapkey;
        maxHeapkey= new PriorityQueue2<>(k); //File a prioritee pour stocker les k voisin de query

        for(LabelledPoint p : points){
            p.setKey(p.distanceTo(query)); //On associe a chaque point sa distance au point query : key
        }

        for(int i=0 ; i<k; i++ ) {
            maxHeapkey.offer(points.get(i).getKey());       // On suppose que les k premier point de "points" sont les plus proche pour commencer les comparaisons
            maxHeapkey.contructheap(maxHeapkey.size());
        }

        for(int i=k; i<points.size(); i++){
            if(maxHeapkey.peek()> points.get(i).getKey()){              //On compare a chaque fois un points de "points" avec l'element le plus grand de la files a priorite
            
                maxHeapkey.offer(points.get(i).getKey());               // Si il est plus petit que la dernier valeur du tableau triee 
                maxHeapkey.poll();                                       // on sort le head du monceau  (plus grande distance) et on insere le nouveau point
                maxHeapkey.contructheap(maxHeapkey.size());             // Apres chaque insertion on reconstitue le monceau avec un donwheap pour que la distance max soit au head
            }
        }
		// On met tout les points voisin dans un ArrayList final
            List<LabelledPoint> neighbors = new ArrayList<>();
            for(LabelledPoint p: points) {
                for(Double m : maxHeapkey.list){       // Pour chaque key dans l'arbre de monceau, on cherche son point pour le stocker dans neighbors
                    if(p.getKey()==m){                  // Au final neighbors contient donc les k voisin de query
                        neighbors.add(p);
                    }
                }
            }
        return neighbors;
    }

    public static void main(String[] args) {     //Programme principal
        // Experience
        PointSet queryPts = new PointSet(PointSet.read_ANN_SIFT("siftsmall_query.fvecs"));  //Lecture des deux documment de test
        PointSet pointSet = new PointSet(PointSet.read_ANN_SIFT("siftsmall_base.fvecs"));

        List<LabelledPoint> points = pointSet.getPointsList(); // On met tout les points dans une List
        int k = 2000; // K est le Nombre de voisin a chercher

        // long startTime = System.currentTimeMillis();              Commence le chronometre 

        for(LabelledPoint p: queryPts.getPointsList()){             //Pour chaque point du fichier, on cherche ses k voisin 
            List<LabelledPoint> nearestNeighbors = findKNearestNeighbors3(p, points, k);     //avec la fonction findKNearestNeighbors dans une boucle.
            System.out.print(p.getLabel());
            System.out.print(" : ");

            for (LabelledPoint neighbor : nearestNeighbors) {       // On affiche nos resultats.
            System.out.print(neighbor.getLabel());
            System.out.print(", ");
            }
        System.out.println("");
        }
        //long endTime = System.currentTimeMillis(); // end timer                           On arrete le chronometre
        //System.out.println("Execution time: " + (endTime - startTime) + " milliseconds"); On affiche le temps d'execution (fin-debut)
    }
}


