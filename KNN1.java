/* ---------------------------------------------------------------------------------
Girum Worku 300305611
Knn1 avec PriorityQueue1  réalisée avec un simple tableau trié dans lequel l’élément maximum se trouve à la fin du tableau. 
------------------------------------------------------------------------------------*/

import java.util.ArrayList;
import java.util.List;

public class KNN1 {
	
    public static List<LabelledPoint> findKNearestNeighbors2(LabelledPoint query, List<LabelledPoint> points , int k) {
        // Fonction qui prend en entree un un point query, une liste de points points et in nombre k qui retourne les k voisin du point query

        PriorityQueue1<Double> maxHeapkey;  //File a prioritee pour stocker les k voisin de query
        maxHeapkey= new PriorityQueue1<>(k);

        for(LabelledPoint p : points){
            p.setKey(p.distanceTo(query));  //On associe a chaque point sa distance au point query : key
        }

        for(int i=0 ; i<k; i++ ) {
            maxHeapkey.offer(points.get(i).getKey());    // On suppose que les k premier point de "points" sont les plus proche pour commencer les comparaisons
        }

        for(int i=k; i<points.size(); i++){
            if(maxHeapkey.peek()> points.get(i).getKey()){      //On compare a chaque fois un points de "points" avec l'element le plus grand de la files a priorite
                                                                // Si il est plus petit que la dernier valeur du tableau triee 
                maxHeapkey.poll();                              // on sort le derniere element (plus grande distance) et on insere le nouveau
                maxHeapkey.offer(points.get(i).getKey());       // Apres chaque insertion il yaurait un trie
            }
        }

        
		// On met tout les points voisin dans un ArrayList final
        List<LabelledPoint> neighbors = new ArrayList<>();

        for(LabelledPoint p: points) {
            for(Double m : maxHeapkey.list){        // Pour chaque key dans le tableau triee, on cherche son point pour le stocker dans neighbors
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
        PointSet pointSet = new PointSet(PointSet.read_ANN_SIFT("sift_base.fvecs"));

        List<LabelledPoint> points = pointSet.getPointsList(); // On met tout les points dans une List
        int k = 2000; // K est le Nombre de voisin a chercher

        // long startTime = System.currentTimeMillis();              Commence le chronometre 

        for(LabelledPoint p: queryPts.getPointsList()){             //Pour chaque point du fichier, on cherche ses k voisin 
            List<LabelledPoint> nearestNeighbors = findKNearestNeighbors2(p, points, k);     //avec la fonction findKNearestNeighbors dans une boucle.
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


