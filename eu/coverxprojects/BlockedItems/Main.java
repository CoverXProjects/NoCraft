package eu.coverxprojects.BlockedItems;

import java.util.ArrayList;
import java.util.Iterator;
import org.bukkit.Material;
import org.bukkit.inventory.Recipe;
import org.bukkit.plugin.java.JavaPlugin;

public class Main
  extends JavaPlugin
{
  @SuppressWarnings("rawtypes")
public void onEnable()
  {
    saveDefaultConfig();
    
    @SuppressWarnings("unchecked")
	ArrayList<Material> disabled = new ArrayList();
    for (String type : getConfig().getStringList("blocked-items")) {
      if (Material.valueOf(type) != null) {
        disabled.add(Material.valueOf(type));
      }
    }
    Iterator<Recipe> recipes = getServer().recipeIterator();
    while (recipes.hasNext())
    {
      Recipe recipe = (Recipe)recipes.next();
      if (disabled.contains(recipe.getResult().getType())) {
        recipes.remove();
      }
    }
  }
}
