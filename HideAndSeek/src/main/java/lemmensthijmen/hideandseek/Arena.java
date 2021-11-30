package lemmensthijmen.hideandseek;

import lemmensthijmen.hideandseek.Enum.GameState;

import lemmensthijmen.hideandseek.config.Config;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.*;

public class Arena {

    private Random random = new Random();
    private int id;
    private ArrayList<UUID> players;
    private ArrayList<UUID> seeker;
    private Location spawn;
    private GameState state;
    private Countdown countdown;
    private Game game;

    public Arena(int id) {
        this.id = id;
        players = new ArrayList<>();
        seeker = new ArrayList<>();
        spawn = Config.getArenaSpawn(id);
        state = GameState.PLAYERJOIN;
        countdown = new Countdown(this);
        game = new Game(this);
    }

    public void start() {
        game.start();
    }

    public void reset() {
        for (UUID uuid : players) {
            Bukkit.getPlayer(uuid).teleport(spawn);
        }

        state = GameState.PLAYERJOIN;
        players.clear();
        countdown = new Countdown(this);
        game = new Game(this);
        seeker.clear();
    }

    public void setSeeker() {
        int index = random.nextInt(players.size());
        UUID seeker = players.get(index);
        this.seeker.add(seeker);
    }

    public void setSeeker(Player player) {
        UUID seeker = player.getUniqueId();
        this.seeker.add(seeker);
    }

    public void sendMessage(String message) {
        for (UUID uuid : players) {
            Bukkit.getPlayer(uuid).sendMessage(message);
        }
    }

    public void addPlayers(Player player) {
        players.add(player.getUniqueId());
        player.teleport(spawn);

        if (players.size() >= Config.getMinPlayers()) {
                        countdown.begin();
        }
    }

    public void removePlayer(Player player) {
        players.remove(player.getUniqueId());
        player.teleport(Config.getLobbySpawn());

        if (players.size() <= Config.getMinPlayers() && state.equals(GameState.COUNTDOWN)) {
            reset();
        }

        if (players.size() == 0 && state.equals(GameState.LIVE)) {
            reset();
        }
    }

    public Player getSeekerPlayer(int index) {
        return Bukkit.getPlayer(seeker.get(index));
    }

    public List<UUID> getSeeker() {
        return seeker;
    }

    public int getId() {
        return id;
    }

    public List<UUID> getPlayers() {
        return players;
    }

    public GameState getGameState() {
        return state;
    }

    public void setGameState(GameState state) {
        this.state = state;
    }

    public Game getGame() {
        return game;
    }

}
