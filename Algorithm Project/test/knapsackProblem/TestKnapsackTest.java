/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package knapsackProblem;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author harsh
 */
public class TestKnapsackTest {
    
    
    public static Knapsack knapsack = new Knapsack();
    
    
    //TestKnapsackTest knapsackTest = new TestKnapsackTest(knapsack);

    public TestKnapsackTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void test1() {
        
        knapsack.totalItems = 5;
        knapsack.itemValueArrayList.add(1.0);
        knapsack.itemValueArrayList.add(2.0);
        knapsack.itemValueArrayList.add(3.0);
        knapsack.itemValueArrayList.add(4.0);
        knapsack.itemValueArrayList.add(5.0);
        
        knapsack.itemWeightArrayList.add(5.0);
        knapsack.itemWeightArrayList.add(4.0);
        knapsack.itemWeightArrayList.add(3.0);
        knapsack.itemWeightArrayList.add(2.0);
        knapsack.itemWeightArrayList.add(1.0);
        
        knapsack.totalCapacityOfKnapsack = 15;
        knapsack.totalPopulationSize = 10;
        knapsack.totalMaxGeneration = 9;
        knapsack.probabilityOfCrossover = 0.5;
        knapsack.probabilityOfMutation = 0.03;
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
        
        Knapsack.knapsack = this.knapsack;
        
        Knapsack.knapsack.startOver();
        Knapsack.knapsack.finalResult();
        System.out.println("Done :"+Knapsack.knapsack.bestGeneratedFitnessArray);
        //assertEquals(14.0, Knapsack.knapsack.bestGeneratedFitnessArray.get(0));
        //assertEquals(Double.valueOf("13.0"), Knapsack.knapsack.bestGeneratedFitnessArray.get(0));
        assertEquals(true, Knapsack.knapsack.bestGeneratedFitnessArray.contains(Double.valueOf("15.0")));
        
    }
    
    @Test
    public void test2(){
        assertEquals(true, Knapsack.knapsack.bestGeneratedFitnessArray.contains(Double.valueOf("14.0")));
    }
    @Test
    public void test3(){
        assertEquals(false, Knapsack.knapsack.bestGeneratedFitnessArray.contains(Double.valueOf("9.0")));
    }
    @Test
    public void test4(){
        assertEquals(false, Knapsack.knapsack.bestGeneratedFitnessArray.contains(Double.valueOf("8.0")));
    }
    @Test
    public void test5(){
        assertEquals(false, Knapsack.knapsack.bestGeneratedFitnessArray.contains(Double.valueOf("7.0")));
    }
    @Test
    public void test6(){
        assertEquals(false, Knapsack.knapsack.bestGeneratedFitnessArray.contains(Double.valueOf("12.0")));
    }
    
    
}
