package Members;

import Interfaces.IGame;
import Interfaces.IHeadManager;
import Interfaces.IMember;
import Interfaces.ITeam;
import Misc.Role;

@SuppressWarnings("unused")
public class HeadManager implements IHeadManager
{
    private String name;
    private String password;
    private Role clearanceRole;

    @Override
    public void setup(String name, String password) 
    {
        this.name = name;
        this.password = password;
        this.clearanceRole = Role.HeadManagement;
    }

    @Override
    public void suspendPlayer(IMember memeber, String reason) 
    {
        
        
    }

    @Override
    public void putPlayerOnInjuredList(IMember member) 
    {
        
        
    }

    @Override
    public void offerTrade(ITeam team, IMember member, IMember req) 
    {
        
        
    }

    @Override
    public void runUpdates(ITeam team, IGame game) 
    {
        
        
    }
    
}
