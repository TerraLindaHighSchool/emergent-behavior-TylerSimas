import greenfoot.*;

/**
 * An ant that collects food.
 * 
 * @author Michael Kölling
 * @version 0.1
 */
public class Ant extends Creature
{
    private boolean carryingFood;
    private GreenfootImage image1;
    private GreenfootImage image2;
    
    private final int MAX_PH_AVAILABLE = 16;
    private final int TIME_FOLLOWING_TRAIL = 30;
    private int pHAvailable = MAX_PH_AVAILABLE;
    private int followTrailTimeRemaining = 0;
    
    private Pheremone pheremone;
    /**
     * Create an ant with a given home hill. The initial speed is zero (not moving).
     */
    public Ant(AntHill home)
    {
        setHomeHill(home);
        image1 = getImage();
        image2 = new GreenfootImage("ant-with-food.gif");
    }

    /**
     * Do what an ant's gotta do.
     */
    public void act()
    {
        status();
    }
    
    private void checkForFood()
    {
        Food food = (Food) getOneIntersectingObject(Food.class);
        if (food != null) 
        {
            food.removeCrumb();
            carryingFood = true;
            setImage(image2);
        }
    }
    
    private boolean atHome()
    {
        if (getHomeHill() != null) {
            return (Math.abs(getX() - getHomeHill().getX()) < 4) && 
                   (Math.abs(getY() - getHomeHill().getY()) < 4);
        }
        else {
                return false;
        }
    }
    
    private void searchForFood()
    {
         if(followTrailTimeRemaining == 0)
         {
             Pheremone pheremone = (Pheremone) getOneIntersectingObject(Pheremone.class);
             if(pheremone != null)
             {
                 headTowards(pheremone);
                }
                else
                {
                    randomWalk(); 
                }
                // if ants smells a pheromone, walk toward center of the pheromone droplet
            // otherwise walk around randomly
        }
        else
        {
            followTrailTimeRemaining =- 1;
            walkAwayFromHome();
        }
        checkForFood();
    }
    
    private void status()
    {
        if(carryingFood)
        {
            walkTowardsHome();
            handlePheremoneDrop();
            if(atHome())
            {
                image2 = image1;
                carryingFood = false;
                getHomeHill().countFood();
            }
        }
        else
        {
            searchForFood();
        }
    }
    
    private void handlePheremoneDrop()
    {
        if(pHAvailable == MAX_PH_AVAILABLE)
        {
            Pheremone pheremone = new Pheremone();
            getWorld().addObject(pheremone, getX(), getY());
            pHAvailable = 0;
        }
        else
        {
            pHAvailable++;
        }
    }
    
    private boolean smellsPheremone()
    {
        Pheremone pheremone = (Pheremone) getOneIntersectingObject(Pheremone.class);
        if(pheremone != null)
        {
            return true;
        }
        else
        {
            return false;
        }
    }
    
    private void walkTowardsPheremoneCenter()
    {
        Pheremone pheremone = (Pheremone) getOneIntersectingObject(Pheremone.class);
        if(pheremone != null)
        {
            headTowards(pheremone);
            if(getX() == pheremone.getX() && getY() == pheremone.getY())
            {
                followTrailTimeRemaining = TIME_FOLLOWING_TRAIL;
            }
        }
    }
}