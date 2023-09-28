package lab.player;

import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArrayList;

import lab.droids.Droid;


public class Player 
{;
	private String name;
	private CopyOnWriteArrayList<Droid> droids;
	
	public Player(String name) 
	{
		setName(name);
		droids = new CopyOnWriteArrayList<Droid>();
	}
	
	public boolean isHasDroids() 
	{
		return droids.size() != 0;
	}
	
	public void addDroid(Droid droid)
	{
		droids.add(droid);
	}
	
	public Droid getDroid(int index) 
	{
		return droids.get(index);
	}
	
	public void removeDroid(Droid droid) 
	{
		droids.remove(droid);
	}
	
	public String getName() 
	{
		return name;
	}
	
	public void setName(String name) 
	{
		this.name = name;
	}
	
	public void setDroids(CopyOnWriteArrayList<Droid> droids) 
	{
		this.droids = droids;
	}
	
	public CopyOnWriteArrayList<Droid> getDroids()
	{
		return droids;
	}
}
