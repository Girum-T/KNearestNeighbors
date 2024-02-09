/* ---------------------------------------------------------------------------------
Girum Worku 300305611
Knn3 : PriorityQueue de la classe standard java.PriorityQueue. 
------------------------------------------------------------------------------------*/

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;   //On n'a pas besoin de construire la class PriorityQueue3 on l'importe  

public class KNN3 {
	
    public static List<LabelledPoint> findKNearestNeighbors(LabelledPoint query, List<LabelledPoint> points , int k) {
        // Fonction qui prend en entree un un point query, une liste de points points et in nombre k qui retourne les k voisin du point query

        for(LabelledPoint p : points){        //On associe a chaque point sa distance au point query : key
            p.setKey(p.distanceTo(query));
        }

		// maxHeap est une file de priorite qui contiendra les k  voisin de query 
        PriorityQueue<LabelledPoint> maxHeap = new PriorityQueue<>((a, b) -> Double.compare(b.getKey(), a.getKey())); 
              // On utilise un comparateur de la classe PriorityQueue qui automatiquement va classer les point d'apres leur distance a query

        for(int i=0 ; i<k; i++ ) {     // On suppose que les k premier point de "points" sont les plus proche pour commencer les comparaisons
        maxHeap.offer(points.get(i));
        } 

        for (int i=k; i<points.size(); i++) {

            if(maxHeap.peek().getKey()> points.get(i).getKey()){   //On compare a chaque fois un points de "points" avec l'element le plus grand de la files a priorite
                                                                   // et on l'insere si il est plus petit.
            maxHeap.offer(points.get(i));                           // A chaque fois qu'on isere un points, la file est triee pour mettre la distance la plus grande en head
            }  

            if (maxHeap.size() > k) {  //A chaque fois que la file est rempli on sort l'element le plus grand (la plus grande distance)
                maxHeap.poll();        
            }
            
        }

		// On met tout les points voisin dans un ArrayList final
        List<LabelledPoint> neighbors = new ArrayList<>();
        while (!maxHeap.isEmpty()) {
            neighbors.add(maxHeap.poll());
        }
        return neighbors; //Et on retoune les k voisin de query sous la forme d'un ArrayList de taille k.
    }

    public static void main(String[] args) {     //Programme principal
        // Experience
        PointSet queryPts = new PointSet(PointSet.read_ANN_SIFT("siftsmall_query.fvecs"));  //Lecture des deux documment de test
        PointSet pointSet = new PointSet(PointSet.read_ANN_SIFT("sift_base.fvecs"));

        List<LabelledPoint> points = pointSet.getPointsList(); // On met tout les points dans une List
        int k = 2000; // K est le Nombre de voisin a chercher

        // long startTime = System.currentTimeMillis();              Commence le chronometre 

        for(LabelledPoint p: queryPts.getPointsList()){             //Pour chaque point du fichier, on cherche ses k voisin 
            List<LabelledPoint> nearestNeighbors = findKNearestNeighbors(p, points, k);     //avec la fonction findKNearestNeighbors dans une boucle.
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

