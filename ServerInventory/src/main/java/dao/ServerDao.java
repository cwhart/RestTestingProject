package dao;

import dto.Server;

import java.util.List;
import java.util.Map;

public interface ServerDao {
    public void addServer(Server server);
    public Server getServer(String name);
    public void removeServer(String name);
    public List<Server> getAllServers();
    public Map<String, List<Server>> getAllServersGroupByManufacturer();
    public List<Server> getServersByManufacturer(String manufacturer);
    public List<Server> getServersOlderThan(int ageInYears);
    public Map<String, List<Server>> getServersOlderThanGroupByManufacturer(int ageInYears);
    public double getAverageServerAge();
}
