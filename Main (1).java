import java.util.Scanner;

public class Main
{
  public static void main (String[]args)
  {
    Scanner reader = new Scanner (System.in);
      System.out.println ("Enter in the mass of the water, in grams. ");
    double mass = reader.nextDouble ();
      System.out.println ("Enter the initial temperature, in celsius");
    double initialTemp = reader.nextDouble ();
    if (initialTemp < -273.14)
        initialTemp = -273.14;
      System.out.println ("Enter the final temperature, in celsius");
    double finalTemp = reader.nextDouble ();
    if (finalTemp < -273.14)
        finalTemp = -273.14;

    String initialPhase = "Liquid";
    if (initialTemp < 0)
        initialPhase = "Solid";
    if (initialTemp > 100)
        initialPhase = "Vapor";

    String finalPhase = "Liquid";
    if (finalTemp < 0)
        finalPhase = "Solid";
    if (finalTemp > 100)
        finalPhase = "Vapor";




      System.out.println ("Mass: " + mass + " g");
      System.out.println ("Initial temperature: " + initialTemp + " C " +
			  "(" + initialPhase + ")");
      System.out.println ("Final temperature: " + finalTemp + " C " + "(" +
			  finalPhase + ")" + "\n");


    Boolean endothermic = false;
    if (finalTemp > initialTemp)
        endothermic = true;


    double heatenergy = 0;
    if (initialPhase.equals ("Solid"))
      {
	heatenergy +=
	  tempChangeSolid (mass, initialTemp, finalTemp, finalPhase,
			   endothermic);
	if (!finalPhase.equals ("Solid"))
	  {
	    heatenergy += fusion (mass, endothermic);
	    heatenergy +=
	      tempChangeLiquid (mass, 0, finalTemp, finalPhase, endothermic);
	  }
	if (finalPhase.equals ("Vapor"))
	  {
	    heatenergy += vaporization (mass, endothermic);
	    heatenergy +=
	      tempChangeVapor (mass, 100, finalTemp, finalPhase, endothermic);
	  }

      }
    if (initialPhase.equals ("Liquid"))
      {
	heatenergy +=
	  tempChangeLiquid (mass, initialTemp, finalTemp, finalPhase,
			    endothermic);
	if (finalPhase.equals ("Solid"))
	  {
	    heatenergy += fusion (mass, endothermic);
	    heatenergy +=
	      tempChangeSolid (mass, 0, finalTemp, finalPhase, endothermic);
	    if (finalPhase.equals ("Vapor"))
	      {
		heatenergy += vaporization (mass, endothermic);
		heatenergy +=
		  tempChangeVapor (mass, 100, finalTemp, finalPhase,
				   endothermic);
	      }
	  }
      }
    if (initialPhase.equals ("Vapor"))
      {
	heatenergy +=
	  tempChangeVapor (mass, initialTemp, finalTemp, finalPhase,
			   endothermic);
	if (!finalPhase.equals ("Vapor"))
	  {
	    heatenergy += vaporization (mass, endothermic);
	    heatenergy +=
	      tempChangeLiquid (mass, 100, finalTemp, finalPhase,
				endothermic);
	  }
	if (finalPhase.equals ("Solid"))
	  {
	    heatenergy += fusion (mass, endothermic);
	    heatenergy +=
	      tempChangeSolid (mass, 0, finalTemp, finalPhase, endothermic);
	  }

      }








    System.out.println ("Total heat energy: " + heatenergy + "kJ");

  }

  public static double tempChangeSolid (double mass, double startTemp,
					double endTemp, String endPhase,
					boolean endothermic)
  {
    if (!endPhase.equals ("Solid"))
      endTemp = 0;
    double energyChange = round (mass * 0.002108 * (endTemp - startTemp));
    if (endothermic)
      System.out.println ("Heating (Solid): " + energyChange + " kJ");
    else
      System.out.println ("Cooling (Solid): " + energyChange + " kJ");
    return energyChange;

  }
  public static double tempChangeLiquid (double mass, double startTemp,
					 double endTemp, String endPhase,
					 boolean endothermic)
  {
    if (endPhase.equals ("Solid"))
      endTemp = 0;
    if (endPhase.equals ("Solid"))
      endTemp = 0;

    double energyChange = round (mass * 0.004184 * (endTemp - startTemp));
    if (endothermic)
      System.out.println ("Heating (Liquid): " + energyChange + " kJ");
    else
      System.out.println ("Cooling (Liquid): " + energyChange + " kJ");
    return energyChange;

  }

  public static double tempChangeVapor (double mass, double startTemp,
					double endTemp, String endPhase,
					boolean endothermic)
  {
    if (!endPhase.equals ("Vapor"))
      endTemp = 100;
    double energyChange = round (mass * 0.001996 * (endTemp - startTemp));
    if (endothermic)
      System.out.println ("Heating (Vapor): " + energyChange + " kJ");
    else
      System.out.println ("Cooling (Vapor): " + energyChange + " kJ");
    return energyChange;

  }
  public static double fusion (double mass, boolean endothermic)
  {
    double energyChange;
    if (endothermic)
      {
	energyChange = round (0.333 * mass);
	System.out.println ("Melting: " + energyChange + "kJ");
      }
    else
      {
	energyChange = round (-0.333 * mass);
	System.out.println ("Freezing: " + energyChange + "kJ");
      }
    return energyChange;




  }
  public static double vaporization (double mass, boolean endothermic)
  {
    double energyChange;
    if (endothermic)
      {
	energyChange = round (2.257 * mass);
	System.out.println ("Evaporation: " + energyChange + "kJ");
      }
    else
      {
	energyChange = round (-2.257 * mass);
	System.out.println ("Condensation: " + energyChange + "kJ");
      }
    return energyChange;




  }
  public static double round (double x)
  {
    x *= 10;
    if (x > 0)
      return (int) (x + 0.5) / 1000.0;
    else
      return (int) (x - 0.5) / 1000.0;
  }
}
