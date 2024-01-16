
package DataClass;

public class User {
	private int userId;
    private String fullName;
    private String gmail;

    public User(int userId,String fullName, String gmail) {
        this.fullName = fullName;
        this.gmail = gmail;
        this.userId = userId;
    }

    public String getFullName() {
        return fullName;
    }

    public String getGmail() {
        return gmail;
    }
    public int getId() {
    	return userId;
    }
}
