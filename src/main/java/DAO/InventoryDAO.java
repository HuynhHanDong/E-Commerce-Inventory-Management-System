package DAO;

import models.Inventory;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class InventoryDAO {

    private Connection connection;

    public InventoryDAO(Connection connection) {
        this.connection = connection;
    }

}
