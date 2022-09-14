package Members;

/**
 * (ABSTRACT) A member of the ballclub
 */
@SuppressWarnings("unused")
public abstract class Member 
{

    private enum Role
    {
        HeadManagement,
        MiddleManagement,
        Player,
        Other
    }

    /**
     * Name of the memeber of the ball club
     */
    private String name;

    /**
     * Using a custom GUID to track user uniqueness 
     */
    private String GUID;

    /**
     * Role in the ball club: Head Management, Middle Management, Player, or Other
     */
    private Role role;

    /**
     * User password for authentication in the site
     */
    private String password;

    
}
