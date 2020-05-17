import greenfoot.*;  // (World, Actor, GreenfootImage, and Greenfoot)

/**
 * The world where ants live.
 * 
 * @author Michael Kölling
 * @version 0.1
 */
public class AntWorld extends World
{
    public static final int SIZE = 640;

    /**
     * Create a new world. It will be initialised with a few ant hills
     * and food sources
     */
    public AntWorld()
    {
        super(SIZE, SIZE, 1);
        setPaintOrder(Ant.class, AntHill.class);
        prepare();
    }

    /**
     * Prepare the world for the start of the program.
     * That is: create the initial objects and add them to the world.
     */
    private void prepare()
    {
        AntHill antHill = new AntHill();
        addObject(antHill,254,281);
        Food food = new Food();
        addObject(food,466,390);
        Food food2 = new Food();
        addObject(food2,469,116);
        Food food3 = new Food();
        addObject(food3,306,568);
        Food food4 = new Food();
        addObject(food4,67,215);
        AntHill antHill2 = new AntHill();
        addObject(antHill2,428,549);
        Food food5 = new Food();
        addObject(food5,90,462);
        Food food6 = new Food();
        addObject(food6,239,71);
    }
    
    public void act()
    {
        if(getObjects(Food.class).size() == 0 && getObjects(Pheremone.class).size() == 0)
        {
            Greenfoot.stop();
        }
    }
}
