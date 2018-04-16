package knapsackProblem;

import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author harsh
 */
public class Knapsack {

    public boolean mutation = false;
    public int totalCrossovers = 0;
    public int totalClones = 0;
    public int totalItems = 0;
    public int totalPopulationSize = 0;
    public int totalMaxGeneration = 0;
    public int totalCountGeneration = 1;
    public double totalCapacityOfKnapsack = 0;
    public double probabilityOfCrossover = 0;
    public double probabilityOfMutation = 0;
    public double totalFitnessGenerated = 0;
    public ArrayList<Double> itemValueArrayList = new ArrayList<Double>();
    public ArrayList<Double> itemWeightArrayList = new ArrayList<Double>();
    public ArrayList<Double> fitnessArray = new ArrayList<Double>();
    public ArrayList<Double> bestGeneratedFitnessArray = new ArrayList<Double>();
    public ArrayList<Double> meanOfGeneratedFitnessArray = new ArrayList<Double>();
    public ArrayList<String> populationArrayList = new ArrayList<String>();
    public ArrayList<String> evolutionOfBreedArrayList = new ArrayList<String>();
    public ArrayList<String> bestGeneratedArrayList = new ArrayList<String>();


    public static Knapsack knapsack = new Knapsack();
    
    public static void main(String[] args) {

        
        InputUser user = new InputUser();
        user.getInput(knapsack);
        int arr[] = new int [10];
        
        knapsack.startOver();
        knapsack.finalResult();
        
        System.out.println("\nMean array for Fitness");
        for(double d: knapsack.meanOfGeneratedFitnessArray){
            System.out.println(" "+ d);
        }

    }

    
    public Knapsack() {
    }


    public void startOver() {

        knapsack.firstGeneration();
        
        System.out.println("First Level Generation Results:");
        System.out.println("**************");
        System.out.println("Population result for first Level: ");
        for(int i = 0; i < knapsack.totalPopulationSize; i++) {
            System.out.println((i + 1) + " - " + knapsack.populationArrayList.get(i));
            
        }

        knapsack.evaluateFirstLevelPopulation();

        System.out.println("Result : Fitness Array");
        for(int i = 0; i < knapsack.totalPopulationSize; i++) {
            System.out.println((i + 1) + " - " + knapsack.fitnessArray.get(i));
        }

        knapsack.bestGeneratedArrayList.add(knapsack.populationArrayList.get(knapsack.getBestResult()));

        System.out.println("Best result of first generation: " + knapsack.bestGeneratedArrayList.get(0));

        knapsack.meanOfGeneratedFitnessArray.add(knapsack.getFitnessMeanValue());

	System.out.println("Mean fitness value of initial generation:  " + knapsack.meanOfGeneratedFitnessArray.get(0));

        knapsack.bestGeneratedFitnessArray.add(knapsack.evauationOfGene(knapsack.populationArrayList.get(knapsack.getBestResult())));

        System.out.println("Fitness value of best result of next generation: " + knapsack.bestGeneratedFitnessArray.get(0));

        if(knapsack.totalMaxGeneration > 1) {
            nextGenerationMethod();
        }

    }


    public void nextGenerationMethod() {

        for(int i = 1; i < knapsack.totalMaxGeneration; i++) {

	    if((knapsack.totalMaxGeneration > 4) && (i > 4)) {

		double one = knapsack.meanOfGeneratedFitnessArray.get(i - 1);
		double two = knapsack.meanOfGeneratedFitnessArray.get(i - 2);
		double three = knapsack.meanOfGeneratedFitnessArray.get(i - 3);

		if(one == two && two == three) {
		    System.out.println("Stop condition");
		    totalMaxGeneration = i;
		    break;
		}
	    }

            knapsack.totalCrossovers = 0;
            knapsack.totalClones = 0;
            knapsack.mutation = false;
          
            for(int j = 0; j < knapsack.totalPopulationSize / 2; j++) {
                knapsack.evolutionOfBreed();
            }
   
            knapsack.fitnessArray.clear();

            knapsack.evaluateNextGenerationPopulation();

            for(int k = 0; k < knapsack.totalPopulationSize; k++) {
                knapsack.populationArrayList.set(k, knapsack.evolutionOfBreedArrayList.get(k));
            }

            System.out.println("Next Generation " + (i + 1) + ":");

            if((i+1) > 1){
                System.out.println("***************");
            }
            System.out.println("Populate Value:");
            for(int l = 0; l < knapsack.totalPopulationSize; l++) {
                System.out.println((l + 1) + " - " + knapsack.populationArrayList.get(l));
            }

            System.out.println("Next Generation Fitness Value: ");
            for(int m = 0; m < knapsack.totalPopulationSize; m++) {
               System.out.println((m + 1) + " - " + knapsack.fitnessArray.get(m));
            } 

            knapsack.evolutionOfBreedArrayList.clear();

            knapsack.bestGeneratedArrayList.add(knapsack.populationArrayList.get(knapsack.getBestResult()));

            System.out.println("Best result of next generation " + (i + 1) + ": " + knapsack.bestGeneratedArrayList.get(i));

            knapsack.meanOfGeneratedFitnessArray.add(knapsack.getFitnessMeanValue());

	    System.out.println("Mean fitness value of next generation: " + knapsack.meanOfGeneratedFitnessArray.get(i));

            knapsack.bestGeneratedFitnessArray.add(knapsack.evauationOfGene(knapsack.populationArrayList.get(knapsack.getBestResult())));

            
            System.out.println("Fitness value of best result of next generation " + (i + 1) + ": " + knapsack.bestGeneratedFitnessArray.get(i));

            System.out.println("Cross observed" + knapsack.totalCrossovers + " times");
            System.out.println("Clone observed " + knapsack.totalClones + " times");
            if(knapsack.mutation == false) {
                System.out.println("Mutation did not happen");
            }
            if(knapsack.mutation == true) {
                System.out.println("Mutation did happen");
            }
        }
    }

    public void finalResult() {

        System.out.println(" Best list result of items that can be included in knapsack are: ");

        double bestFitnessValue = 0;
        int bestGene = 0;

        
        for(int z = 0; z < knapsack.totalMaxGeneration - 1; z++) {
            if(knapsack.bestGeneratedFitnessArray.get(z) > bestFitnessValue) {
                bestFitnessValue = knapsack.bestGeneratedFitnessArray.get(z);
                bestGene = z;
            }
        }

        String best_list = knapsack.bestGeneratedArrayList.get(bestGene);
                for(int y = 0; y < knapsack.totalItems; y++) {
            if(best_list.substring(y, y + 1).equals("1")) {
                System.out.print((y + 1) + " ");
            }
        }

    }


    public void evolutionOfBreed() {

        int firstgene;
        int secondsgene;

        totalCountGeneration = totalCountGeneration + 1;

        if(totalPopulationSize % 2 == 1) {
            evolutionOfBreedArrayList.add(bestGeneratedArrayList.get(totalCountGeneration - 1));
        }

        firstgene = selectGene();
        secondsgene = selectGene();
        
        geneProduction(firstgene, secondsgene);

    }


    public void mutationOfGene() {
       
        double randomMutation = Math.random();
        if(randomMutation <= probabilityOfMutation) {

            mutation = true;
            String mutedGene;
            String newMutedGene;
            Random generator = new Random();
            int mutationPoint = 0;
            double whichGeneToMutate = Math.random() * 100;

        if(whichGeneToMutate <= 50) {
                mutedGene = evolutionOfBreedArrayList.get(evolutionOfBreedArrayList.size() - 1);
                mutationPoint = generator.nextInt(totalItems);
                if(mutedGene.substring(mutationPoint, mutationPoint + 1).equals("1")) {
                    newMutedGene = mutedGene.substring(0, mutationPoint) + "0" + mutedGene.substring(mutationPoint);
                    evolutionOfBreedArrayList.set(evolutionOfBreedArrayList.size() - 1, newMutedGene);
                }
                if(mutedGene.substring(mutationPoint, mutationPoint + 1).equals("0")) {
                    newMutedGene = mutedGene.substring(0, mutationPoint) + "1" + mutedGene.substring(mutationPoint);
                    evolutionOfBreedArrayList.set(evolutionOfBreedArrayList.size() - 1, newMutedGene);
                }
            }
            if(whichGeneToMutate > 50) {
                mutedGene = evolutionOfBreedArrayList.get(evolutionOfBreedArrayList.size() - 2);
                mutationPoint = generator.nextInt(totalItems);
                if(mutedGene.substring(mutationPoint, mutationPoint + 1).equals("1")) {
                    newMutedGene = mutedGene.substring(0, mutationPoint) + "0" + mutedGene.substring(mutationPoint);
                    evolutionOfBreedArrayList.set(evolutionOfBreedArrayList.size() - 1, newMutedGene);
                }
                if(mutedGene.substring(mutationPoint, mutationPoint + 1).equals("0")) {
                    newMutedGene = mutedGene.substring(0, mutationPoint) + "1" + mutedGene.substring(mutationPoint);
                    evolutionOfBreedArrayList.set(evolutionOfBreedArrayList.size() - 2, newMutedGene);
                }
            }           
        }
    }


    public int selectGene() {

        double rand = Math.random() * totalFitnessGenerated;
        
        for(int i = 0; i < totalPopulationSize; i++) {
            if(rand <= fitnessArray.get(i)) {
                return i;
            }
            rand = rand - fitnessArray.get(i);
        }

	return 0;
    }


    public void geneProduction(int firstgene, int secondgene) {
      
        String newFirstGene;
        String newSecondGene;

        double randomcross = Math.random();
        if(randomcross <= probabilityOfCrossover) {
            totalCrossovers = totalCrossovers + 1;
            Random generator = new Random(); 
            int crossPoint = generator.nextInt(totalItems) + 1;

            newFirstGene = populationArrayList.get(firstgene).substring(0, crossPoint) + populationArrayList.get(secondgene).substring(crossPoint);
            newSecondGene = populationArrayList.get(secondgene).substring(0, crossPoint) + populationArrayList.get(firstgene).substring(crossPoint);

            evolutionOfBreedArrayList.add(newFirstGene);
            evolutionOfBreedArrayList.add(newSecondGene);
        }
        else {
            totalClones = totalClones + 1;
            evolutionOfBreedArrayList.add(populationArrayList.get(firstgene));
            evolutionOfBreedArrayList.add(populationArrayList.get(secondgene));
        }

        mutationOfGene();
    }


    public int getBestResult() {
        int best_index_position = 0;
        double knapsackFit = 0;
        double bestFit = 0;
        for(int i = 0; i < totalPopulationSize; i++) {
            knapsackFit = evauationOfGene(populationArrayList.get(i));
            if(knapsackFit > bestFit) {
                bestFit = knapsackFit;
                best_index_position = i;
            }
        }
        return best_index_position;
    }


    public double getFitnessMeanValue() {
        double totalFitnessValue = 0;
   	double meanFitnessValue = 0;
        for(int i = 0; i < totalPopulationSize; i++) {
	    totalFitnessValue = totalFitnessValue + fitnessArray.get(i);
        }
	meanFitnessValue = totalFitnessValue / totalPopulationSize;
	return meanFitnessValue;
    }


    public void evaluateFirstLevelPopulation() {       
        totalFitnessGenerated = 0;
        for(int i = 0; i < totalPopulationSize; i++) {
            double temp = evauationOfGene(populationArrayList.get(i));
            fitnessArray.add(temp);
            totalFitnessGenerated = totalFitnessGenerated + temp;
        }
    }


    public void evaluateNextGenerationPopulation() {
        totalFitnessGenerated = 0;
        for(int i = 0; i < totalPopulationSize; i++) {
            double tempFit = evauationOfGene(evolutionOfBreedArrayList.get(i));
            fitnessArray.add(tempFit);
            totalFitnessGenerated = totalFitnessGenerated + tempFit;
        }
    }


    public double evauationOfGene(String gene) {
        double totalWeight = 0;
        double totalValue = 0;
        double valuefitnessArray = 0;
        double diff = 0;
        char c = '0';

        for(int j = 0; j < totalItems; j ++) {
            c = gene.charAt(j);
            if(c == '1') {
                totalWeight = totalWeight + itemWeightArrayList.get(j);
                totalValue = totalValue + itemValueArrayList.get(j);
            }
        }
        diff = totalCapacityOfKnapsack - totalWeight;
        if(diff >= 0) {
            valuefitnessArray = totalValue;
        }
        
        return valuefitnessArray;
    }
    
    
    public void firstGeneration() {
        for(int i = 0; i < totalPopulationSize; i++) {
            populationArrayList.add(populateFirstGene());    
            
        }
        
   }


    public String populateFirstGene() {

        StringBuilder firstGene = new StringBuilder(totalItems);

        char c;

        for(int i = 0; i < totalItems; i++) {
            c = '0';
            double rnd = Math.random(); 
            if(rnd > 0.5) {
                c = '1';
            }
            firstGene.append(c);
        }
        return firstGene.toString();
    }


    

    public static boolean isInt(String str) {
        try { 
            Integer.parseInt(str); 
        } 
        catch(NumberFormatException e) { 
            return false; 
        }
        return true;
    }


    public static boolean isDoub(String str) {
        try {
            Double.parseDouble(str);
        }
        catch(NumberFormatException e) {
            return false;
        }
        return true;
    }

}