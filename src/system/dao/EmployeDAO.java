package system.dao;

import system.business.Employe;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class EmployeDAO {
    private String QUERY;
    private ConnectionDAO connectionDAO;

    private List<Employe> employes;

    public EmployeDAO() {
        connectionDAO = new ConnectionDAO();
    }

    public boolean upsert(Employe employe) {
        if (employe.getId() == 0) {
            QUERY = String.format("INSERT INTO employes (id, name, last_name, position) VALUES (null, '%s', '%s', '%s')",
                    employe.getName(), employe.getLastName(), employe.getPosition());
        } else {
            QUERY = String.format("UPDATE employes SET name = '%s', last_name = '%s', position = '%s' WHERE id = %s",
                    employe.getName(), employe.getLastName(), employe.getPosition(), employe.getId());
        }
        return connectionDAO.executeNonQuery(QUERY);
    }

    public boolean delete(Employe employe) {
        QUERY = String.format("DELETE FROM employes WHERE id = %s", employe.getId());
        return connectionDAO.executeNonQuery(QUERY);
    }
    /*
    public List<Employe> listEmployes() {
        employes = new ArrayList<>();
        try {
            QUERY_SELECT = "SELECT * FROM employes;";
            ResultSet resultSet = connectionDAO.reader(QUERY_SELECT);
            while (resultSet.next()) {
                employes.add(new Employe(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getString("last_name"),
                        resultSet.getString("position")
                        )
                );
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return employes;
    }
    */

    public void fillListEmployes() {
        employes = new ArrayList<>();
        try {
            QUERY = "SELECT * FROM employes;";
            ResultSet resultSet = connectionDAO.reader(QUERY);
            while (resultSet.next()) {
                employes.add(new Employe(
                                resultSet.getInt("id"),
                                resultSet.getString("name"),
                                resultSet.getString("last_name"),
                                resultSet.getString("position")
                        )
                );
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void printEmployes() {
        fillListEmployes();
        for (Employe employe : employes)
            System.out.println(employe);
    }
}
