package LoginSystems.Accounts;

public class AdminAccount 
{
    
    private String username;
    private String password;
    private String email;

    public AdminAccount(String username, String password, String email) 
    {
        this.username = username;
        this.password = password;
        this.email = email;
    }

    public String getUsername() 
    {
        return username;
    }

    public String getPassword() 
    {
        return password;
    }
    
    public String getEmail()
    {
        return email;
    }
    
    public void print()
    {
        System.out.println(username + " " + password);
    }
    
}
