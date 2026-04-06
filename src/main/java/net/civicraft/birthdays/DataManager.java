package net.civicraft.birthdays;

import com.google.gson.JsonParser;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class DataManager {
    public LocalDate getBirthdayByPlayer(Player player) {
        LocalDate birthday = null;

        for (Map.Entry<UUID, LocalDate> entry : JsonParser.parseString("resources/birthdays.json").getAsJsonObject().entrySet()) {
            if (entry.getKey().equals(player.getUniqueId())) { //also need a better way to traverse the file
                birthday = entry.getValue();
                break;
            }
        }

        return birthday;
    }

    public List<Player> getPlayersByBirthday(LocalDate date) {
        List<Player> players = new ArrayList<>();
        for (Map.Entry<UUID, LocalDate> entry : JsonParser.parseString("resources/birthdays.json").getAsJsonObject().entrySet()) {
            if (entry.getValue().equals("birthday")) players.add(Bukkit.getPlayer(entry.getKey()));
        } // potentially could use a better way to approach this as birthdays.json begins to grow

        if (players.isEmpty()) {
            return null;
        } else {
            return players;
        }
    }
}
