package pomestie.minty;


import org.bukkit.plugin.java.JavaPlugin;

public class FireFly extends JavaPlugin {


    ArrowLaunched eventer = new ArrowLaunched();
    @Override
    public void onEnable()
    {
        getServer().getPluginManager().registerEvents(eventer,this);
        getLogger().info("FireFly plugin was successfully loaded");
        getLogger().info("FireFly was developed especially for Unicorn Estate");
    }

    @Override
    public void onDisable() {
        getLogger().warning("FireFly plugin was disabled");
    }


}


