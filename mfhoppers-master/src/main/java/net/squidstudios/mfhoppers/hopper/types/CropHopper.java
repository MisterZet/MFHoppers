package net.squidstudios.mfhoppers.hopper.types;

import net.squidstudios.mfhoppers.MFHoppers;
import net.squidstudios.mfhoppers.hopper.HopperEnum;
import net.squidstudios.mfhoppers.hopper.IHopper;
import net.squidstudios.mfhoppers.util.Methods;
import org.bukkit.Location;
import org.bukkit.inventory.ItemStack;

import java.sql.PreparedStatement;
import java.util.HashMap;
import java.util.Map;

public class CropHopper extends IHopper {
    @Override
    public void save(PreparedStatement stat) {
        try {
            stat.setString(1, getData().get("name").toString());
            stat.setString(2, getData().get("loc").toString());
            stat.setInt(3, Integer.valueOf(getData().get("lvl").toString()));
            stat.setString(4,generateToSaveData());
            stat.addBatch();
        } catch (Exception ex){
            MFHoppers.getInstance().out(ex);
        }
    }

    public CropHopper(Location loc, String name, int lvl){

        getData().put("loc", Methods.toString(loc));
        getData().put("name", name);
        getData().put("lvl", lvl);
        getData().put("type", HopperEnum.Crop.name());

    }
    public CropHopper(Location loc, String name, int lvl,Map<String, Object> data){

        getData().put("loc", Methods.toString(loc));
        data.entrySet().forEach( entry -> getData().put(entry.getKey(), entry.getValue()));
        getData().put("name", name);
        getData().put("lvl", lvl);
        getData().put("type", HopperEnum.Crop.name());

    }

    @Override
    public ItemStack getItem() {
        return null;
    }

    @Override
    public HopperEnum getType() {
        return HopperEnum.Crop;
    }
    public String generateToSaveData(){
        Map<String, Object> ret = new HashMap<>();

        if(getData().containsKey("linked")) {
            ret.put("linked", getData().get("linked"));
        }

        if(getData().containsKey("owner")) {
            ret.put("owner", getData().get("owner"));
        }

        if(getData().containsKey("filter")){
            ret.put("filter", getData().get("filter"));
        }

        return Methods.serialize(ret);
    }


}
