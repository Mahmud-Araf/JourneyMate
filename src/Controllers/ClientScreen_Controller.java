package Controllers;

import java.sql.SQLException;

import javafx.event.ActionEvent;

public class ClientScreen_Controller extends Basic_Controller {
    
    public String getTotalClientNumber(String UserName)
    {
        String st= "0";

        startDB();

        try {

            setConnection();
            preparedStatement1 = connection.prepareStatement("SELECT COUNT(*) FROM Clients WHERE UserName = ?");
            preparedStatement1.setString(1, UserName);
            resultSet = preparedStatement1.executeQuery();

            if(resultSet.next())
            {
                st=Integer.toString(resultSet.getInt(1));
            }

            
        } catch (SQLException e) {
           e.printStackTrace();
        }
        finally
        {
            closeDB();
        }

        return st;
    }
    
    @Override
    protected void addAction(ActionEvent event)
    {
        
    }

    @Override
    protected void modifyAction(ActionEvent event)
    {
        
    }

    @Override
    protected void deleteAction(ActionEvent event)
    {
      
    }
 
    @Override

    protected void showTable(ActionEvent event)
    {
        
    }


    
}