package pomestie.minty;

import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.block.data.type.Slab;
import org.bukkit.entity.*;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileHitEvent;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import static org.bukkit.Bukkit.getLogger;
import static org.bukkit.Bukkit.getServer;


public class ArrowLaunched implements Listener {

    @EventHandler
    public void ArrowWasLauched(ProjectileHitEvent event)
    {
        if (event.getEntityType() == EntityType.ARROW)
        {
            if (event.getEntity().getFireTicks()>0) {
                //Checking for damaged entities
                //If any entity was damaged, plugin will not process this event
                Entity hitted = event.getHitEntity();
                if(hitted != null) return;

                //Collecting information about hitted blocks.
                Location locs = event.getEntity().getLocation();
                String wrld = event.getEntity().getWorld().getName();
                Block block = getServer().getWorld(wrld).getBlockAt(locs);
                Location locsdown = new Location(getServer().getWorld(wrld),locs.getX(),locs.getY()-1,locs.getZ());
                Block down = getServer().getWorld(wrld).getBlockAt(locsdown);


                //If under hitted location will be found AIR
                //plugin going to spawn NETHERRACK, to spawn FIRE.
                //NETHERRACK will be dispawned after spawning FIRE.
                //If under hitted location will not be AIR plugin will spawn FIRE.
                if (down.getType() == Material.AIR)
                {
                    down.setType(Material.NETHERRACK);
                    block.setType(Material.FIRE);
                    down.setType(Material.AIR);
                }
                else
                {
                    if (block.getType()!=Material.AIR)
                    {
                        Location locsup = new Location(getServer().getWorld(wrld),locs.getX(),locs.getY()+1,locs.getZ());
                        Block blockup = getServer().getWorld(wrld).getBlockAt(locsup);
                        if (blockup.getType()==Material.AIR)
                        {
                            blockup.setType(Material.FIRE);
                        }
                        event.getEntity().remove();
                        return;
                    }
                    block.setType(Material.FIRE);
                }
                event.getEntity().remove();
            }
        }
    }

}
